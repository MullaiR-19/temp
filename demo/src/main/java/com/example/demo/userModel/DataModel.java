package com.example.demo.userModel;

public class DataModel {

	private String NAME;
	private String PHONE;
	private String EMAIL;

	public DataModel(int iD, String nAME, String pHONE, String eMAIL) {
		NAME = nAME;
		PHONE = pHONE;
		EMAIL = eMAIL;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public DataModel() {
		super();
	}
}
