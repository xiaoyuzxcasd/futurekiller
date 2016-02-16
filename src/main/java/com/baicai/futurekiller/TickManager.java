package com.baicai.futurekiller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.baicai.futurekiller.data.EContract;
import com.baicai.futurekiller.data.Tick;
import com.baicai.futurekiller.data.TickFactory;

public class TickManager {
	private static final String Url = "http://hq.sinajs.cn";

	private static final String ParamPattern = "list=%s";

	private static final TickManager instance = new TickManager();

	public static TickManager instance() {
		return instance;
	}

	public Tick getTick(EContract contract, int month) {
		String contractName = createContractName(contract, month);
		String response = HttpRequest.sendGet(Url, String.format(ParamPattern, contractName));
		System.out.println(response);
		return TickFactory.createTick(contract, month, response);
	}

	private String createContractName(EContract contract, int month) {
		// TODO
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.MONTH) > month) {
			cal.add(Calendar.YEAR, 1);
		}
		cal.set(Calendar.MONTH, month - 1);
		// 设置日期输出的格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		// 格式化输出
		String monthStr = df.format(cal.getTime());
		return String.format("%s%s", contract.name(), monthStr.substring(2, monthStr.length()));
	}
}
