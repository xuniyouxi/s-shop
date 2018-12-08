package com.vg.entity;

import java.util.Date;

public class Team {
	private Integer team_id;

	private Integer team_sum;

	private Date create_time;

	public Integer getTeam_id() {
		return team_id;
	}

	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}

	public Integer getTeam_sum() {
		return team_sum;
	}

	public void setTeam_sum(Integer team_sum) {
		this.team_sum = team_sum;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Team(Integer team_id, Integer team_sum, Date create_time) {
		super();
		this.team_id = team_id;
		this.team_sum = team_sum;
		this.create_time = create_time;
	}

	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}

}