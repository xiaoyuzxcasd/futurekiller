package com.baicai.futurekiller.data;

public enum EContract {
	;

	private int id;

	private EContract(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
