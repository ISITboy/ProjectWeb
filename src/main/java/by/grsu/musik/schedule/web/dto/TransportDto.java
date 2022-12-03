package by.grsu.musik.schedule.web.dto;

import java.sql.Timestamp;

public class TransportDto {

	private int id;
	private String name;
	private int cityId;
	private int routId;
	private Timestamp yearRelease;
	private int inspection;
	private int number;
	private String nameCity;
	private String nameRoute;
	
	public String getNameRoute() {
		return nameRoute;
	}
	public void setNameRoute(String nameRoute) {
		this.nameRoute = nameRoute;
	}
	public String getNameCity() {
		return nameCity;
	}
	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getRoutId() {
		return routId;
	}
	public void setRoutId(int routId) {
		this.routId = routId;
	}
	public Timestamp getYearRelease() {
		return yearRelease;
	}
	public void setYearRelease(Timestamp yearRelease) {
		this.yearRelease = yearRelease;
	}
	public int getInspection() {
		return inspection;
	}
	public void setInspection(int inspection) {
		this.inspection = inspection;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
	
	
}
