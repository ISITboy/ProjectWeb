package by.grsu.musik.schedule.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.model.City;
import by.grsu.musik.schedule.db.model.Country;

public class CityDaoTest extends AbstractTest {
	private static final IDao<Integer, City> dao = CityDaoImpl.INSTANCE;
	private static final IDao<Integer, Country> countryDao = CountryDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		City entity = new City();
		entity.setName("Брест");
		entity.setCountryId(saveCountry("Belarus").getId());
		entity.setCountStreets(40);
		entity.setDataSave(getCurrentTime());
		
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		City entity = new City();
		entity.setName("Brest");
		entity.setCountryId(saveCountry("Belarus").getId());
		entity.setCountStreets(40);
		entity.setDataSave(getCurrentTime());

		dao.insert(entity);

		String newName = "Grodno";
		entity.setName(newName);
		entity.setCountStreets(45);
		;
		dao.update(entity);

		City updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
		Assertions.assertNotEquals(updatedEntity.getName(), updatedEntity.getCountStreets());
	}

	@Test
	public void testDelete() {
		City entity = new City();
		entity.setName("Брест");
		entity.setCountryId(saveCountry("Belarus").getId());
		entity.setCountStreets(40);
		entity.setDataSave(getCurrentTime());

		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		City entity = new City();
		entity.setName("Брест");
		entity.setCountryId(saveCountry("Belarus").getId());
		entity.setCountStreets(40);
		entity.setDataSave(getCurrentTime());

		dao.insert(entity);

		City selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getCountryId(), selectedEntity.getCountryId());
		Assertions.assertEquals(entity.getCountStreets(), selectedEntity.getCountStreets());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			City entity = new City();
			entity.setName("Брест");
			entity.setCountryId(saveCountry("Belarus").getId());
			entity.setCountStreets(40);
			entity.setDataSave(getCurrentTime());

			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}

	private Country saveCountry(String name) {
		Country entity = new Country();
		entity.setName(name);
		entity.setCountCity(10);
		entity.setDataSave(getCurrentTime());
		countryDao.insert(entity);
		return entity;
	}
}
