package by.grsu.musik.schedule.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.musik.schedule.db.dao.AbstractDao;
import by.grsu.musik.schedule.db.dao.IDao;
import by.grsu.musik.schedule.db.model.Transport;

public class TransportDaoImpl extends AbstractDao implements IDao<Integer, Transport> {
	public static final TransportDaoImpl INSTANCE = new TransportDaoImpl();

	private TransportDaoImpl() {
		super();
	}

	@Override
	public void insert(Transport entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"insert into transport(name, cityId, routId, yearRelease, inspection, number) values(?,?,?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setInt(2, entity.getCityId());
			// owner_id has type Integer, but if it can be null we have to use setObject()
			// instead of setInt()
			pstmt.setObject(3, entity.getRoutId());
			pstmt.setDate(4, entity.getYearRelease());
			pstmt.setInt(5, entity.getInspection());
			pstmt.setInt(6, entity.getNumber());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "transport"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Transport entity", e);
		}

	}

	@Override
	public void update(Transport entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update transport set cityId=?,yearRelease =?,  inspection=? where id=?");
			pstmt.setInt(1, entity.getCityId());
			pstmt.setDate(2, entity.getYearRelease());
			pstmt.setInt(3, entity.getInspection());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Transport entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from transport where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Transport entity", e);
		}
	}

	@Override
	public Transport getById(Integer id) {
		Transport entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from transport where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Transport entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Transport> getAll() {
		List<Transport> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from transport");
			while (rs.next()) {
				Transport entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Transport entities", e);
		}

		return entitiesList;
	}

	private Transport rowToEntity(ResultSet rs) throws SQLException {
		Transport entity = new Transport();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setCityId(rs.getInt("cityId"));
		// getObject() is unsupported by current JDBC driver. We will get "0" if field
		// is NULL in DB
		entity.setRoutId(rs.getInt("routId"));
		entity.setYearRelease(rs.getDate("yearRelease"));
		entity.setInspection(rs.getInt("inspection"));
		return entity;
	}
}
