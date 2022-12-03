package by.grsu.musik.schedule.db.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Country {

	private int id;
	private String name;
	private int countCity;
	private Timestamp dataSave;

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", countCity=" + countCity + ", dataSave=" + dataSave + "]";
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

	public int getCountCity() {
		return countCity;
	}

	public void setCountCity(int countCity) {
		this.countCity = countCity;
	}

	public Timestamp getDataSave() {
		return dataSave;
	}

	public void setDataSave(Timestamp dataSave) {
		this.dataSave = dataSave;
	}

	

}
