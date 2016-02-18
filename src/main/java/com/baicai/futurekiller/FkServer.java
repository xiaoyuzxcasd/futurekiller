package com.baicai.futurekiller;

import java.util.LinkedList;
import java.util.List;

import com.baicai.futurekiller.data.ContractConfig;
import com.baicai.futurekiller.data.EContract;
import com.baicai.futurekiller.tick.TickManager;
import com.baicai.futurekiller.util.FkScheduledExecutor;

public class FkServer {
	private List<TickManager> managerList;

	FkServer() {
		this.managerList = new LinkedList<TickManager>();
	}

	public void start() {
		ServerLogger.initLogger();
		for (ContractConfig config : ContractConfig.getAllContractConfig()) {
			for (int month : config.getMainMonthSet()) {
				TickManager manager = new TickManager(EContract.getEContractById(config.getId()), month);
				managerList.add(manager);
			}
		}
	}

	public void shutdown() {
		FkScheduledExecutor.instance().shutdown();
	}
}
