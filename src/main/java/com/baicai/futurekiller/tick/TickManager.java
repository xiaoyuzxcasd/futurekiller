package com.baicai.futurekiller.tick;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

import com.baicai.futurekiller.data.EContract;
import com.baicai.futurekiller.data.Tick;
import com.baicai.futurekiller.util.FkScheduledExecutor;
import com.baicai.futurekiller.util.RandomUtil;
import com.baicai.futurekiller.util.TimePoint;

public class TickManager {
	private static final int MaxSize = 1000;

	private static final ITickCollector tickCollector = new SinaTickCollector();

	private static final TickDao tickDao = new TickDao();
	
	private static final int CollectPeroid = 15 * 1000;
	
	private static final int WriteBackPeroid = 5 * 60;

	private EContract contract;
	private int month;
	private ConcurrentLinkedDeque<Tick> tickList;
	private long lastWriteBackTime;
	private TimePoint timePoint;

	public TickManager(EContract contract, int month) {
		this.contract = contract;
		this.month = month;
		this.tickList = new ConcurrentLinkedDeque<Tick>();
		initTickList();
		Tick last = tickList.peekLast();
		if (last == null) {
			this.lastWriteBackTime = 0L;
		} else {
			this.lastWriteBackTime = last.getTime().getTime();
		}
		this.timePoint = new TimePoint(8, 59, true, null);
		this.timePoint.setNext(new TimePoint(11, 32, false, new TimePoint(13, 29, true, new TimePoint(15, 32, false,
				new TimePoint(20, 59, true, new TimePoint(2, 32, false, timePoint))))));
		this.timePoint.refreshTime();
		FkScheduledExecutor.instance().scheduleAtFixedRate(() -> collectTick(), RandomUtil.randomBetween(0, CollectPeroid),
				CollectPeroid, TimeUnit.MILLISECONDS);
		FkScheduledExecutor.instance().scheduleAtFixedRate(() -> writeBack(), RandomUtil.randomBetween(0, WriteBackPeroid),
				WriteBackPeroid, TimeUnit.SECONDS);
	}

	private void initTickList() {
		List<Tick> list = tickDao.getLastTickList(MaxSize);
		for (Tick tick : list) {
			tickList.addFirst(tick);
		}
	}

	private void collectTick() {
		long now = System.currentTimeMillis();
		if (!isTradingHours(now)) {
			return;
		}
		
		Tick tick = tickCollector.getTick(contract, month);
		if (tick == null) {
			return;
		}

		if (tickList.isEmpty()) {
			tickList.add(tick);
		} else {
			Tick last = tickList.getLast();
			if (tick.getTime().getTime() > last.getTime().getTime()) {
				tickList.add(tick);
			}
		}

		if (tickList.size() > MaxSize) {
			tickList.removeFirst();
		}
	}
	
	private boolean isTradingHours(long now) {
		boolean result;
		if (now >= timePoint.getTime()) {
			timePoint = timePoint.getNext();
			timePoint.refreshTime();
			result = isTradingHours(now);
		} else {
			result = !timePoint.isBeginTime();
		}
		return result;
	}

	private void writeBack() {
		LinkedList<Tick> updateList = new LinkedList<Tick>();
		Iterator<Tick> iterator = tickList.descendingIterator();
		long lastTime = 0L;
		while (iterator.hasNext()) {
			Tick tick = iterator.next();
			if (lastTime == 0) {
				lastTime = tick.getTime().getTime();
			}

			if (tick.getTime().getTime() > lastWriteBackTime) {
				updateList.addFirst(tick);
			}
		}

		if (!updateList.isEmpty()) {
			tickDao.replaceTicks(updateList);
			lastWriteBackTime = lastTime;
		}
	}

	/**
	 * 获取快照
	 */
	public List<Tick> getTickListSnapshot(int size) {
		size = Math.min(size, tickList.size());
		ArrayList<Tick> result = new ArrayList<Tick>(size);
		int count = 0;
		Iterator<Tick> iterator = tickList.descendingIterator();
		while (iterator.hasNext()) {
			Tick tick = iterator.next();
			result.set(size - 1 - count++, tick);
			if (count >= size) {
				break;
			}
		}
		return result;
	}
}
