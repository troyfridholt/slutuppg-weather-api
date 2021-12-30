package model;

import java.time.Instant;



public class weatherBean {

	private String cityStr;

	private String countryStr;

	private String temperatureStr;
	
	private String timeStr;
	
	public weatherBean(String cityStr, String countryStr) {

		this.cityStr = cityStr;
		this.countryStr = countryStr;

	}

	public String getCityStr() {
		return cityStr;
	}

	public String getCountryStr() {
		return countryStr;
	}

	public String getTemperatureStr() {
		return temperatureStr;
	}

	public void setTemperatureStr(String temperatureStr) {
		this.temperatureStr = (temperatureStr);
	}
	
	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		
		long unixTime = System.currentTimeMillis()/1000;
		
		Instant localtime = Instant.ofEpochSecond(unixTime+Integer.parseInt(timeStr));
		
		this.timeStr = String.valueOf(localtime);
	}
	
	
	
	
}