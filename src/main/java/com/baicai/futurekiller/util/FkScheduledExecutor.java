package com.baicai.futurekiller.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.baicai.futurekiller.PoolThreadFactory;
import com.baicai.futurekiller.ServerLogger;

public class FkScheduledExecutor {
	private static final FkScheduledExecutor instance = new FkScheduledExecutor();

	private ScheduledExecutorService executor;

	private FkScheduledExecutor() {
		this.executor = Executors.newScheduledThreadPool(3, new PoolThreadFactory("ScheduledExecutor"));
	}

	public static FkScheduledExecutor instance() {
		return instance;
	}

	public void submit(final Runnable command) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				execute(command);
			}
		});
	}

	private void execute(Runnable command) {
		try {
			command.run();
		} catch (Exception e) {
			ServerLogger.error("ScheduleExecutor error:", e);
		}
	}

	public void scheduleAtFixedRate(final Runnable command, long initialDelay, long period, TimeUnit unit) {
		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				execute(command);
			}
		}, initialDelay, period, unit);
	}

	public void shutdown() {
		executor.shutdown();
		try {
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				ServerLogger.error("FkScheduledExecutor did not terminate");
			}
		} catch (InterruptedException e) {
			ServerLogger.error("FkScheduledExecutor shutdown interrupted");
		}
	}
}
