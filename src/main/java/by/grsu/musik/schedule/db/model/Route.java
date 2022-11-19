package by.grsu.musik.schedule.db.model;

public class Route {

	private int id;
	private String name;
	private int countStops;
	private String duration;

	@Override
	public String toString() {
		return "Route [id=" + id + ", name=" + name + ", countStops=" + countStops + ", duration=" + duration + "]";
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

	public int getCountStops() {
		return countStops;
	}

	public void setCountStops(int countStops) {
		this.countStops = countStops;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
