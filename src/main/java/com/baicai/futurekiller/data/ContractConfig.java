package com.baicai.futurekiller.data;

import java.util.Set;

public class ContractConfig {
	private int id;
	private int settlementDay;
	private String mainMonth;
	private Set<Integer> mainMonthSet;

	public int getId() {
		return id;
	}

	public int getSettlementDay() {
		return settlementDay;
	}

	public Set<Integer> getMainMonthSet() {
		return mainMonthSet;
	}

	public static ContractConfig getContractConfig(int id) {
		// TODO
		return null;
	}
}
