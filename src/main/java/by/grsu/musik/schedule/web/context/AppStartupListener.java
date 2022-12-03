package by.grsu.musik.schedule.web.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.musik.schedule.db.dao.AbstractDao;
import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.dao.impl.CountryDaoImpl;
import by.grsu.musik.schedule.db.dao.impl.CityDaoImpl;
import by.grsu.musik.schedule.db.dao.impl.TransportDaoImpl;
import by.grsu.musik.schedule.db.dao.impl.RouteDaoImpl;
import by.grsu.musik.schedule.db.model.Country;
import by.grsu.musik.schedule.db.model.City;
import by.grsu.musik.schedule.db.model.Route;
import by.grsu.musik.schedule.db.model.Transport;

public class AppStartupListener implements ServletContextListener {
	private static final IDao<Integer, Country> countryDao = CountryDaoImpl.INSTANCE;
	private static final IDao<Integer, City> cityDao = CityDaoImpl.INSTANCE;
	private static final IDao<Integer, Transport> transportDao = TransportDaoImpl.INSTANCE;
	private static final IDao<Integer, Route> routeDao = RouteDaoImpl.INSTANCE;

	private static final String DB_NAME = "schedule3-db";

	private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

	private void loadInitialData() {
		Country countryEntity = new Country();
		countryEntity.setName("Belarus");
		countryEntity.setCountCity(100);
		countryEntity.setDataSave(getCurrentTime());
		countryDao.insert(countryEntity);
		System.out.println("created: " + countryEntity);

		City cityEntity = new City();
		cityEntity.setName("Брест");
		cityEntity.setCountryId(countryEntity.getId());
		cityEntity.setCountStreets(40);
		cityEntity.setDataSave(getCurrentTime());
		cityDao.insert(cityEntity);
		System.out.println("created: " + cityEntity);

		Route routeEntity = new Route();
		routeEntity.setName("Street1-Street2");
		routeEntity.setCountStops(40);
		routeEntity.setDuration(40);
		routeDao.insert(routeEntity);
		System.out.println("created: " + routeEntity);

		Transport transportEntity = new Transport();
		transportEntity.setName("Bus");
		transportEntity.setCityId(cityEntity.getId());
		transportEntity.setRoutId(routeEntity.getId());
		transportEntity.setYearRelease(getCurrentTime());
		transportEntity.setInspection(1);
		transportEntity.setNumber(7);
		transportDao.insert(transportEntity);
		System.out.println("created: " + transportEntity);
		System.out.println("initial data created");
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	private Date getDateFromString(String dateStr) {
		try {
			return new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dateStr).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}