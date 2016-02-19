package com.baicai.futurekiller.indicator;

import java.util.List;

import com.baicai.futurekiller.data.Tick;

public interface IndicatorHandler {
	public int check(List<Tick> tickList);
}
