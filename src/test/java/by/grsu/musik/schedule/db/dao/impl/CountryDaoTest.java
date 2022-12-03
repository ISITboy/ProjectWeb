package by.grsu.musik.schedule.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.model.Country;

public class CountryDaoTest extends AbstractTest {
	private static final IDao<Integer, Country> dao = CountryDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Country entity = new Country();
		entity.setName("Belarus");
		entity.setCountCity(100);
		;
		entity.setDataSave(getCurrentTime());
		;
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Country entity = new Country();
		entity.setName("Belarus");
		entity.setCountCity(100);
		;
		entity.setDataSave(getCurrentTime());
		;
		dao.insert(entity);

		String newName = "Russia";
		entity.setName(newName);
		entity.setCountCity(450);
		;
		dao.update(entity);

		Country updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
		Assertions.assertNotEquals(updatedEntity.getName(), updatedEntity.getCountCity());
	}

	@Test
	public void testDelete() {
		Country entity = new Country();
		entity.setName("Belarus");
		entity.setCountCity(100);
		;
		entity.setDataSave(getCurrentTime());
		;
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Country entity = new Country();
		entity.setName("Belarus");
		entity.setCountCity(100);
		;
		entity.setDataSave(getCurrentTime());
		;
		dao.insert(entity);

		Country selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getCountCity(), selectedEntity.getCountCity());
		Assertions.assertEquals(entity.getDataSave(), selectedEntity.getDataSave());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Country entity = new Country();
			entity.setName("Belarus");
			entity.setCountCity(100);
			;
			entity.setDataSave(getCurrentTime());
			;
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
