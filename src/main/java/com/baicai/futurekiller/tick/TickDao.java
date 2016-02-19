package com.baicai.futurekiller.tick;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.baicai.futurekiller.data.Tick;
import com.baicai.futurekiller.db.DbManager;

public class TickDao {
	private static String SelectLastTickList = "SELECT * FROM Tick WHERE ContractId=? AND Month=? ORDER BY Time DESC LIMIT ?";
	
	private static String ReplaceTick = "REPLACE Tick(ContractId, Month, Time, Price, BuyPrice, SellPrice, TradingVolume, OpenInterest, BuyVol, SellVol) VALUES(?,?,?,?,?,?,?,?,?,?)";

	public List<Tick> getLastTickList(int contractId, int month, int size) {
		return DbManager.selectList(SelectLastTickList, Tick.class, contractId, month, size);
	}

	public void replaceTicks(Collection<Tick> tickList) {
		List<Object[]> paramsList = new ArrayList<Object[]>(tickList.size());
		for (Tick tick : tickList) {
			paramsList.add(new Object[] { tick.getContractId(), tick.getMonth(), tick.getTime(), tick.getPrice(),
					tick.getBuyPrice(), tick.getSellPrice(), tick.getTradingVolume(), tick.getOpenInterest(),
					tick.getBuyVol(), tick.getSellVol() });
		}
		DbManager.execSql(ReplaceTick, paramsList);
	}

	// public List<Tick> getLastTickList(int size) {
	// return new ArrayList<Tick>(0);
	// }
	//
	// public boolean replaceTicks(Collection<Tick> tickList) {
	// for (Tick tick : tickList) {
	// ServerLogger.info(tick.buildString());
	// }
	// return false;
	// }
}
