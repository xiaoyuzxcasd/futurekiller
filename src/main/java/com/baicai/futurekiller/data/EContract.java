package com.baicai.futurekiller.data;

import java.util.HashMap;
import java.util.Map;

public enum EContract {
	/** 螺纹钢 */
	RB(1)
	/** 白银 */
	,AG(2)
	/** 黄金 */
	,AU(3)
	/** 沪铜 */
	,CU(4)
	/** 沪铝 */
	,AL(5)
	/** 沪锌 */
	,ZN(6)
	/** 沪铅 */
	,PB(7)
	/** 橡胶 */
	,RU(8)
	/** 燃油 */
	,FU(9)
	/** 线材 */
	,WR(10)
	/** 大豆 */
	,A(11)
	/** 豆粕 */
	,M(12)
	/** 豆油 */
	,Y(13)
	/** 焦炭 */
	,J(14)
	/** 玉米 */
	,C(15)
	/** 乙烯 */
	,L(16)
	/** 棕油 */
	,P(17)
	/** PVC */
	,V(18)
	/** 菜籽 */
	,RS(19)
	/** 菜粕 */
	,RM(20)
	/** 玻璃 */
	,FG(21)
	/** 棉花 */
	,CF(22)
	/** 强麦 */
	,WS(23)
	/** 籼稻 */
	,ER(24)
	/** 甲醇 */
	,ME(25)
	/** 菜油 */
	,RO(26)
	/** 甲酸 */
	,TA(27)
	;

	private static final Map<Integer, EContract> id2Contract = initId2Contract();

	private int id;

	private EContract(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	private static Map<Integer, EContract> initId2Contract() {
		Map<Integer, EContract> map = new HashMap<Integer, EContract>();
		for (EContract contract : EContract.values()) {
			map.put(contract.getId(), contract);
		}
		return map;
	}

	public static EContract getEContractById(int id) {
		return id2Contract.get(id);
	}
}
