package by.grsu.musik.schedule.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.model.City;
import by.grsu.musik.schedule.db.model.Country;
import by.grsu.musik.schedule.db.model.Transport;
import by.grsu.musik.schedule.db.model.Route;

public class TransportDaoTest extends AbstractTest {
	private static final IDao<Integer, Transport> transportDao = TransportDaoImpl.INSTANCE;
	private static final IDao<Integer, City> cityDao = CityDaoImpl.INSTANCE;
	private static final IDao<Integer, Country> countryDao = CountryDaoImpl.INSTANCE;
	private static final IDao<Integer, Route> routeDao = RouteDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Transport entity = new Transport();
		entity.setName("Bus");
		entity.setCityId(saveCity("Belarus", "Brest").getId());
		entity.setRoutId(saveRoute().getId());
		entity.setYearRelease(getCurrentTime());
		entity.setInspection(1);
		entity.setNumber(7);
		transportDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testInsertWithoutOwner() {
		Transport entity = new Transport();
		entity.setName("Bus");
		entity.setCityId(saveCity("Belarus", "Brest").getId());
		entity.setRoutId(saveRoute().getId());
		entity.setYearRelease(getCurrentTime());
		entity.setInspection(1);
		entity.setNumber(7);
		transportDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Transport entity = new Transport();
		entity.setName("Bus");
		entity.setCityId(saveCity("Belarus", "Brest").getId());
		entity.setRoutId(saveRoute().getId());
		entity.setYearRelease(getCurrentTime());
		entity.setInspection(1);
		entity.setNumber(7);
		transportDao.insert(entity);

		int newr = 0;
		City newCity = saveCity("Grodno", "gorod");
		entity.setCityId(newCity.getId());
		entity.setYearRelease(getCurrentTime());
		entity.setInspection(newr);
		transportDao.update(entity);

		Transport updatedEntity = transportDao.getById(entity.getId());
		Assertions.assertEquals(newCity.getId(), updatedEntity.getCityId());
		Assertions.assertEquals(newr, updatedEntity.getInspection()); // VIN should stay unchanged as DAO doesn't update
																		// it
		Assertions.assertNotEquals(updatedEntity.getYearRelease(), updatedEntity.getInspection());
	}

	@Test
	public void testDelete() {
		Transport entity = new Transport();
		entity.setName("Bus");
		entity.setCityId(saveCity("Belarus", "Brest").getId());
		entity.setRoutId(saveRoute().getId());
		entity.setYearRelease(getCurrentTime());
		entity.setInspection(1);
		entity.setNumber(7);
		transportDao.insert(entity);

		transportDao.delete(entity.getId());

		Assertions.assertNull(transportDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Transport entity = new Transport();
		entity.setName("Bus");
		entity.setCityId(saveCity("Belarus", "Brest").getId());
		entity.setRoutId(saveRoute().getId());
		entity.setYearRelease(getCurrentTime());
		entity.setInspection(1);
		entity.setNumber(0);
		transportDao.insert(entity);

		Transport selectedEntity = transportDao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getCityId(), selectedEntity.getCityId());
		Assertions.assertEquals(entity.getRoutId(), selectedEntity.getRoutId());
		Assertions.assertEquals(entity.getYearRelease(), selectedEntity.getYearRelease());
		Assertions.assertEquals(entity.getInspection(), selectedEntity.getInspection());
		Assertions.assertEquals(entity.getNumber(), selectedEntity.getNumber());

	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Transport entity = new Transport();
			entity.setName("Bus");
			entity.setCityId(saveCity("Belarus", "Brest").getId());
			entity.setRoutId(saveRoute().getId());
			entity.setYearRelease(getCurrentTime());
			entity.setInspection(1);
			entity.setNumber(7);
			transportDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, transportDao.getAll().size());
	}

	private Route saveRoute() {
		Route entity = new Route();
		entity.setName("Дубко-Автовакзал");
		entity.setCountStops(10);
		entity.setDuration(40);
		routeDao.insert(entity);
		return entity;
	}

	private City saveCity(String country, String city) {
		Country countryEntity = new Country();
		countryEntity.setName(country);
		countryEntity.setCountCity(100);
		countryEntity.setDataSave(getCurrentTime());
		countryDao.insert(countryEntity);

		City cityEntity = new City();
		cityEntity.setName(city);
		cityEntity.setCountryId(countryEntity.getId());
		cityEntity.setCountStreets(45);
		cityEntity.setDataSave(getCurrentTime());
		cityDao.insert(cityEntity);

		return cityEntity;
	}
}
