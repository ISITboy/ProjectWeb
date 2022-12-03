package by.grsu.musik.schedule.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.model.Country;
import by.grsu.musik.schedule.db.model.Route;

public class RouteDaoTest extends AbstractTest {
	private static final IDao<Integer, Route> dao = RouteDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Route entity = new Route();
		entity.setName("Дубко-Автовакзал");
		entity.setCountStops(10);
		entity.setDuration(40);
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Route entity = new Route();
		entity.setName("Дубко-Автовакзал");
		entity.setCountStops(10);
		entity.setDuration(40);
		
		dao.insert(entity);

		String newName = "Вишнявец-Дубко";
		entity.setName(newName);
		entity.setCountStops(18);
		dao.update(entity);

		Route updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
		Assertions.assertNotEquals(updatedEntity.getName(), updatedEntity.getCountStops());
	}

	@Test
	public void testDelete() {
		Route entity = new Route();
		entity.setName("Дубко-Автовакзал");
		entity.setCountStops(10);
		entity.setDuration(40);
		
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Route entity = new Route();
		entity.setName("Дубко-Автовакзал");
		entity.setCountStops(10);
		entity.setDuration(40);
		;
		dao.insert(entity);

		Route selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getCountStops(), selectedEntity.getCountStops());
		Assertions.assertEquals(entity.getDuration(), selectedEntity.getDuration());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Route entity = new Route();
			entity.setName("Дубко-Автовакзал");
			entity.setCountStops(10);
			entity.setDuration(40);
			;
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
