package com.baicai.futurekiller.tick;

import com.baicai.futurekiller.data.EContract;
import com.baicai.futurekiller.data.Tick;

public interface ITickCollector {
	public Tick getTick(EContract contract, int month);
}
