package com.baicai.futurekiller.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baicai.futurekiller.util.StringUtil;

public class ContractConfig {
	private int id;
	private int settlementDay;
	private String mainMonth;
	private Set<Integer> mainMonthSet;

	public ContractConfig(int id, int settlementDay, String mainMonth) {
		super();
		this.id = id;
		this.settlementDay = settlementDay;
		this.mainMonth = mainMonth;
	}

	public int getId() {
		return id;
	}

	public int getSettlementDay() {
		return settlementDay;
	}

	public Set<Integer> getMainMonthSet() {
		if (mainMonthSet == null) {
			mainMonthSet = new HashSet<Integer>(StringUtil.splitToIntList(mainMonth, ","));
		}
		return mainMonthSet;
	}

	public static ContractConfig getContractConfig(int id) {
		// TODO
		return null;
	}

	public static List<ContractConfig> getAllContractConfig() {
		// TODO
		return Arrays.asList(new ContractConfig(EContract.M.getId(), 25, "1,5,9"));
	}
}
