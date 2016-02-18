package com.baicai.futurekiller.tick;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.baicai.futurekiller.data.Tick;

public class TickDao {
	private static String SelectLastTickList = "";
	
	public List<Tick> getLastTickList(int size) {
		return new ArrayList<Tick>(0);
	}

	public boolean replaceTicks(Collection<Tick> tickList) {
		return false;
	}
}
