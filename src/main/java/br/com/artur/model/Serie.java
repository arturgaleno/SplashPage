package br.com.artur.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Serie implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 6241410043376206289L;
	
	private String id;
	
	private String name;
	
	private String color;

	private List<BigDecimal> data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<BigDecimal> getData() {
		return data;
	}

	public void setData(List<BigDecimal> data) {
		this.data = data;
	}
}
