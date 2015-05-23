package com.nyimaweather.app.db;

public class Weather_data {
	private String date;

	private String dayPictureUrl;

	private String nightPictureUrl;

	private String weather;

	private String wind;

	private String temperature;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayPictureUrl() {
		return dayPictureUrl;
	}

	public void setDayPictureUrl(String dayPictureUrl) {
		this.dayPictureUrl = dayPictureUrl;
	}

	public String getNightPictureUrl() {
		return nightPictureUrl;
	}

	public void setNightPictureUrl(String nightPictureUrl) {
		this.nightPictureUrl = nightPictureUrl;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	
	public String showtoday(){
		return "今天的天气:"+date+"                                   温度范围："+temperature+"    天气:"+weather+"    风力:"+wind;
	}
	
	public String shownnnday(){
		return date+"的天气:温度范围："+temperature+"    天气:"+weather+"    风力:"+wind;
	}
	
	
	
	@Override
	public String toString() {
		return "" + date + ",dayPictureUrl=" + dayPictureUrl  + ",nightPictureUrl="+ nightPictureUrl+",weather="+ weather+",wind="+ wind+",temperature="+ temperature+"";
	}
}
