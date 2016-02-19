package com.baicai.futurekiller.data;

import java.util.Calendar;
import java.util.Date;

import com.baicai.futurekiller.ServerLogger;

public class TickFactory {
	public static Tick createTick(EContract contract, int month, String data) {
		try {
			return doCreateTick(contract, month, data);
		} catch (Exception e) {
			ServerLogger.error(String.format("createTick error of data=%s", data), e);
			return null;
		}
	}

	private static Tick doCreateTick(EContract contract, int month, String data) {
		String value = data.split("=")[1].replace("\"", "");
		String[] termArr = value.split(",");
		String timeStr = termArr[1];
		Date time = createDateTime(timeStr);
		int price = Integer.parseInt(termArr[8]);
		int buyPirce = Integer.parseInt(termArr[6]);
		int sellPirce = Integer.parseInt(termArr[7]);
		int tradingVolume = Integer.parseInt(termArr[14]);
		int openInterest = Integer.parseInt(termArr[13]);
		int buyVol = Integer.parseInt(termArr[11]);
		int sellVol = Integer.parseInt(termArr[12]);
		return new Tick(contract.getId(), month, time, price, buyPirce, sellPirce, tradingVolume, openInterest, buyVol,
				sellVol);
	}

	private static Date createDateTime(String timeStr) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, Integer.parseInt(timeStr.substring(0, 2)));
		cal.set(Calendar.MINUTE, Integer.parseInt(timeStr.substring(2, 4)));
		cal.set(Calendar.SECOND, Integer.parseInt(timeStr.substring(4, 6)));
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
