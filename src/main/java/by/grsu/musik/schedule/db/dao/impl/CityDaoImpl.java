package by.grsu.musik.schedule.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.musik.schedule.db.dao.AbstractDao;
import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.model.City;

public class CityDaoImpl extends AbstractDao implements IDao<Integer, City> {

	// single instance of this class to be used by the all consumers
	public static final CityDaoImpl INSTANCE = new CityDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private CityDaoImpl() {
		super();
	}

	@Override
	public void insert(City entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into city(name, countryId, countStreets) values(?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getCountryId());
			pstmt.setInt(3, entity.getCountStreets());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "city"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert City entity", e);
		}
	}

	@Override
	public void update(City entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update city set name=?, countStreets=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(3, entity.getCountryId());
			pstmt.setInt(2, entity.getCountStreets());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update City entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from city where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete City entity", e);
		}

	}

	@Override
	public City getById(Integer id) {
		City entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from city where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get City entity by id", e);
		}

		return entity;
	}

	@Override
	public List<City> getAll() {
		List<City> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from city");
			while (rs.next()) {
				City entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select City entities", e);
		}

		return entitiesList;
	}

	private City rowToEntity(ResultSet rs) throws SQLException {
		City entity = new City();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setCountryId(rs.getInt("countryId"));
		entity.setCountStreets(rs.getInt("countStreets"));
		return entity;
	}

}
