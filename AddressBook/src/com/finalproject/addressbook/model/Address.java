package com.finalproject.addressbook.model;

import java.io.Serializable;

/**
 * JAVA Class to hold the Address
 * 
 * @author
 *
 */
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 10009L;
	private String streetName;
	private String hNo;
	private String cityName;
	private String stateName;
	private String zipCode;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String gethNo() {
		return hNo;
	}

	public void sethNo(String hNo) {
		this.hNo = hNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
