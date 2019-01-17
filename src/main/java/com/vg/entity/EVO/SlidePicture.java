package com.vg.entity.EVO;

public class SlidePicture {

	private Integer pic_id;
	private String picturePath;
	private Integer pic_rank;
	public SlidePicture() {}
	public SlidePicture(String picturePath) {
		super();
		this.picturePath = picturePath;
	}
	public SlidePicture(Integer pic_id, String picturePath, Integer pic_rank) {
		super();
		this.pic_id = pic_id;
		this.picturePath = picturePath;
		this.pic_rank = pic_rank;
	}
	public Integer getPic_id() {
		return pic_id;
	}
	public void setPic_id(Integer pic_id) {
		this.pic_id = pic_id;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public Integer getPic_rank() {
		return pic_rank;
	}
	public void setPic_rank(Integer pic_rank) {
		this.pic_rank = pic_rank;
	}
	
}
