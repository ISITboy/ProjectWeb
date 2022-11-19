package by.grsu.musik.schedule.db.model;

import java.sql.Date;

public class Transport {

	private int id;
	private String name;
	private int cityId;
	private int routId;
	private Date yearRelease;
	private int inspection;
	private int number;

	@Override
	public String toString() {
		return "Transport [id=" + id + ", name=" + name + ", cityId=" + cityId + ", routId=" + routId + ", yearRelease="
				+ yearRelease + ", inspection=" + inspection + ", number=" + number + "]";
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

	public Date getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(java.util.Date date) {
		this.yearRelease = (Date) date;
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
