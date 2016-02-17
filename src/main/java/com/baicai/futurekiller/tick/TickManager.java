package com.baicai.futurekiller.tick;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

import com.baicai.futurekiller.data.EContract;
import com.baicai.futurekiller.data.Tick;
import com.baicai.futurekiller.util.FkScheduledExecutor;
import com.baicai.futurekiller.util.RandomUtil;

public class TickManager {
	private static final int MaxSize = 1000;

	private static final ITickCollector tickCollector = new SinaTickCollector();

	private static final TickDao tickDao = new TickDao();

	private EContract contract;
	private int month;
	private ConcurrentLinkedDeque<Tick> tickList;
	private long lastWriteBackTime;

	public TickManager(EContract contract, int month) {
		this.contract = contract;
		this.month = month;
		this.tickList = new ConcurrentLinkedDeque<Tick>();
		this.lastWriteBackTime = 0L;
		FkScheduledExecutor.instance().scheduleAtFixedRate(() -> collectTick(), RandomUtil.randomBetween(0, 30 * 1000),
				30 * 1000, TimeUnit.MILLISECONDS);
		FkScheduledExecutor.instance().scheduleAtFixedRate(() -> writeBack(), RandomUtil.randomBetween(0, 10 * 60),
				10 * 60, TimeUnit.SECONDS);
	}

	private void collectTick() {
		Tick tick = tickCollector.getTick(contract, month);
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

	private void writeBack() {
		List<Tick> updateList = new LinkedList<Tick>();
		Iterator<Tick> iterator = tickList.descendingIterator();
		long lastTime = 0L;
		while (iterator.hasNext()) {
			Tick tick = iterator.next();
			if (lastTime == 0) {
				lastTime = tick.getTime().getTime();
			}

			if (tick.getTime().getTime() > lastWriteBackTime) {
				updateList.add(tick);
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
	public List<Tick> getTickListSnapshot() {
		List<Tick> result = new LinkedList<Tick>();
		for (Tick tick : tickList) {
			result.add(tick);
		}
		return result;
	}
}
