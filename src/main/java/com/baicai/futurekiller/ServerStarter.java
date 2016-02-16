package com.baicai.futurekiller;

import com.baicai.futurekiller.data.EContract;
import com.baicai.futurekiller.data.Tick;

public class ServerStarter {

	public static void main(String[] args) {
		Tick tick = TickManager.instance().getTick(EContract.M, 5);
		System.out.println(tick.getMonth());
	}
}
