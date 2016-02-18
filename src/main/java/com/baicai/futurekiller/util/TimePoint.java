package com.baicai.futurekiller.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baicai.futurekiller.HttpRequest;

public class TimePoint {
	private final SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");

	private int hour;
	private int minute;
	private boolean isBeginTime;
	private TimePoint next;
	private long time;

	public TimePoint(int hour, int minute, boolean isBeginTime, TimePoint next) {
		this.hour = hour;
		this.minute = minute;
		this.isBeginTime = isBeginTime;
		this.next = next;
		this.time = 0L;
	}

	public boolean isBeginTime() {
		return isBeginTime;
	}

	public void refreshTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if (hour == 2) {
			do {
				cal.add(Calendar.DAY_OF_YEAR, 1);
			} while (isBeginTime() && isHoliday(cal));
		}
		time = cal.getTimeInMillis();
	}

	public TimePoint getNext() {
		return next;
	}

	public void setNext(TimePoint next) {
		this.next = next;
	}

	public long getTime() {
		return time;
	}

	private boolean isHoliday(Calendar cal) {
		String date = ymd.format(cal.getTime());
		String response = HttpRequest.sendGet("http://www.easybots.cn/api/holiday.php", String.format("d=%s", date));
		JSONObject jsonObject = JSON.parseObject(response);
		return jsonObject.getIntValue(date) > 0;
	}
}
