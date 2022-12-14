package by.grsu.musik.schedule.web.dto;

import java.sql.Timestamp;

public class CityDto {

	private Integer id;
	private String name;
	private Integer countryId;
	private Integer countStreets;
	private Timestamp dataSave;
	private String countryName;
	
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Timestamp getDataSave() {
		return dataSave;
	}
	public void setDataSave(Timestamp dataSave) {
		this.dataSave = dataSave;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getCountStreets() {
		return countStreets;
	}
	public void setCountStreets(Integer countStreets) {
		this.countStreets = countStreets;
	}
	
	
}
