package com.baicai.futurekiller.data;

public class Tick {
	// 合约id
	private int contractId;
	// 合约月份
	private int month;
	private long time;
	private int price;
	// 成交量（当前累计）
	private int tradingVolume;
	// 持仓量（当前累计）
	private int openInterest;
	// 买量
	private int buyVol;
	// 卖量
	private int sellVol;

	public Tick(int contractId, int month, long time, int price, int tradingVolume, int openInterest, int buyVol,
			int sellVol) {
		super();
		this.contractId = contractId;
		this.month = month;
		this.time = time;
		this.price = price;
		this.tradingVolume = tradingVolume;
		this.openInterest = openInterest;
		this.buyVol = buyVol;
		this.sellVol = sellVol;
	}

	public int getContractId() {
		return contractId;
	}

	public int getMonth() {
		return month;
	}

	public long getTime() {
		return time;
	}

	public int getPrice() {
		return price;
	}

	public int getTradingVolume() {
		return tradingVolume;
	}

	public int getOpenInterest() {
		return openInterest;
	}

	public int getBuyVol() {
		return buyVol;
	}

	public int getSellVol() {
		return sellVol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contractId;
		result = prime * result + month;
		result = prime * result + (int) (time ^ (time >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tick other = (Tick) obj;
		if (contractId != other.contractId)
			return false;
		if (month != other.month)
			return false;
		if (time != other.time)
			return false;
		return true;
	}

}
