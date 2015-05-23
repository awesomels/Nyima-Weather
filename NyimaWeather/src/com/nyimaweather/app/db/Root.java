package com.nyimaweather.app.db;

import java.util.List;

public class Root {
	private String error;

	private String status;

	private String date;

	private List<Results> results ;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}
	
	public String showdate(){
		return "今天是"+date;
	}
	@Override
	public String toString() {
		return "今天是：" + date  + ","+ results +"";
	}
}
