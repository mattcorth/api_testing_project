package com.sparta.endpointtesting.pojoconfig.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandsItem{

	@JsonProperty("id")
	private int id;

	@JsonProperty("brand")
	private String brand;

	public int getId(){
		return id;
	}

	public String getBrand(){
		return brand;
	}

	public boolean isNotNull(){
		return id >0 && brand != null;
	}
}