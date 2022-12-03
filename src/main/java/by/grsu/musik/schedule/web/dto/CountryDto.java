package by.grsu.musik.schedule.web.dto;
import java.sql.Timestamp;

public class CountryDto {
	
	private Integer id;
	private String name;
	private Integer countCity;
	private Timestamp dataSave;
	
	
	
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

	public Integer getCountCity() {
		return countCity;
	}

	public void setCountCity(Integer countCity) {
		this.countCity = countCity;
	}

	
}
