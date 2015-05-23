package com.nyimaweather.app.db;

public class Index {
	private String title;

	private String zs;

	private String tipt;

	private String des;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public String getTipt() {
		return tipt;
	}

	public void setTipt(String tipt) {
		this.tipt = tipt;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	public String tieshi(){
		return tipt+":"+zs+"。"+des;
	}
	
	@Override
	public String toString() {
		return "" + title + ":" + zs  + ","+ tipt+":"+ des+"";
	}
	
}
