package by.grsu.musik.schedule.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.musik.schedule.db.dao.AbstractDao;
import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.model.Route;

public class RouteDaoImpl extends AbstractDao implements IDao<Integer, Route> {

	// single instance of this class to be used by the all consumers
	public static final RouteDaoImpl INSTANCE = new RouteDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private RouteDaoImpl() {
		super();
	}

	@Override
	public void insert(Route entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into route(name, countStops, duration) values(?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getCountStops());
			pstmt.setString(3, entity.getDuration());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "route"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Route entity", e);
		}
	}

	@Override
	public void update(Route entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update route set name=?, countStops=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getCountStops());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Route entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from route where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Route entity", e);
		}

	}

	@Override
	public Route getById(Integer id) {
		Route entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from route where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Route entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Route> getAll() {
		List<Route> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from route");
			while (rs.next()) {
				Route entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Route entities", e);
		}

		return entitiesList;
	}

	private Route rowToEntity(ResultSet rs) throws SQLException {
		Route entity = new Route();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setCountStops(rs.getInt("countStops"));
		entity.setDuration(rs.getString("duration"));
		return entity;
	}

}
