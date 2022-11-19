package by.grsu.musik.schedule.db.model;

public class City {

	private int id;
	private String name;
	private int countryId;
	private int countStreets;

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", countryId=" + countryId + ", countStreets=" + countStreets
				+ "]";
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

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getCountStreets() {
		return countStreets;
	}

	public void setCountStreets(int countStreets) {
		this.countStreets = countStreets;
	}

}
