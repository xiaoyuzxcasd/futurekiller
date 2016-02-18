package com.baicai.futurekiller.data;

import java.util.Date;

public class Tick {
	// 合约id
	private int contractId;
	// 合约月份
	private int month;
	private Date time;
	private int price;
	// 成交量（当前累计）
	private int tradingVolume;
	// 持仓量（当前累计）
	private int openInterest;
	// 买量
	private int buyVol;
	// 卖量
	private int sellVol;

	public Tick(int contractId, int month, Date time, int price, int tradingVolume, int openInterest, int buyVol,
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

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTradingVolume() {
		return tradingVolume;
	}

	public void setTradingVolume(int tradingVolume) {
		this.tradingVolume = tradingVolume;
	}

	public int getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(int openInterest) {
		this.openInterest = openInterest;
	}

	public int getBuyVol() {
		return buyVol;
	}

	public void setBuyVol(int buyVol) {
		this.buyVol = buyVol;
	}

	public int getSellVol() {
		return sellVol;
	}

	public void setSellVol(int sellVol) {
		this.sellVol = sellVol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contractId;
		result = prime * result + month;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
}
