package com.vg.entity;

public class Biscuits {
	private Integer bis_id;
	private String bis_name;
	private String bis_content;
	private Integer bis_state;

	public Integer getBis_id() {
		return bis_id;
	}

	public void setBis_id(Integer bis_id) {
		this.bis_id = bis_id;
	}

	public String getBis_name() {
		return bis_name;
	}

	public void setBis_name(String bis_name) {
		this.bis_name = bis_name;
	}

	public String getBis_content() {
		return bis_content;
	}

	public void setBis_content(String bis_content) {
		this.bis_content = bis_content;
	}

	public Integer getBis_state() {
		return bis_state;
	}

	public void setBis_state(Integer bis_state) {
		this.bis_state = bis_state;
	}

	public Biscuits(Integer bis_id, String bis_name, String bis_content, Integer bis_state) {
		super();
		this.bis_id = bis_id;
		this.bis_name = bis_name;
		this.bis_content = bis_content;
		this.bis_state = bis_state;
	}

	public Biscuits() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Biscuits [bis_id=" + bis_id + ", bis_name=" + bis_name + ", bis_content=" + bis_content + ", bis_state="
				+ bis_state + "]";
	}

}
