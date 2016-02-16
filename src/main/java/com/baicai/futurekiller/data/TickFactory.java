package com.baicai.futurekiller.data;

public class TickFactory {
	public static Tick createTick(EContract contract, String data) {
		String value = data.split("=")[1].replace("\"", "");
		String[] termArr = value.split(",");
		// TODO
		int month = Integer.parseInt(termArr[0].subSequence(termArr[0].length() - 2, termArr[0].length()).toString());
		long time = 0;
		int price = Integer.parseInt(termArr[8]);
		int tradingVolume = Integer.parseInt(termArr[14]);
		int openInterest = Integer.parseInt(termArr[13]);
		int buyVol = Integer.parseInt(termArr[11]);
		int sellVol = Integer.parseInt(termArr[12]);
		return new Tick(contract.getId(), month, time, price, tradingVolume, openInterest, buyVol, sellVol);
	}
}
