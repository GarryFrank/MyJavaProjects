package com.foxminded.igorFrenkel.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.foxminded.igorFrenkel.dao.DAOException;
import com.foxminded.igorFrenkel.dao.DaoFactory;
import com.foxminded.igorFrenkel.dao.GenericDAO;
import com.foxminded.igorFrenkel.domain.Schedule;

public class ScheduleSQL implements GenericDAO<Schedule> {

	private static final String DELETE = "DELETE FROM SCHEDULES WHERE schedule_id = ?";
	private static final String UPDATE = "UPDATE SCHEDULES SET name = ? WHERE schedule_id = ?";
	private static final String RETRIEVE_BY_ID = "SELECT name, schedule_id FROM SCHEDULES WHERE id = ?";
	private static final String RETRIEVE = "SELECT schedule_id, name FROM SCHEDULES";
	private static final String SAVE = "INSERT INTO SCHEDULES (name) VALUES (?) RETURNING schedule_id";

	private static final Logger logger = Logger.getLogger(ScheduleSQL.class
			.getName());

	@Override
	public Schedule save(Schedule schedule) throws DAOException {

		logger.trace("Saving new schedule");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(SAVE);
			statement.setString(1, schedule.getName());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				schedule.setId(resultSet.getLong("schedule_id"));
			}
		} catch (SQLException e) {
			logger.error("Cannot save schedule ", e);
			throw new DAOException("Cannot save schedule ", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					logger.trace("resultset closed");
				}
				if (statement != null) {
					statement.close();
					logger.trace("statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("connection closed");
				}
			} catch (SQLException e) {
				logger.warn("Cannot close connection ", e);
			}
		}
		logger.trace("schedule with id " + schedule.getId() + " is saved");
		return schedule;
	}

	@Override
	public List<Schedule> retrieve() throws DAOException {

		logger.trace("Retrieving schedules from DataBase");
		List<Schedule> schedules = new ArrayList<Schedule>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(RETRIEVE);

			while (resultSet.next()) {
				Schedule schedule = new Schedule();
				schedule.setId(resultSet.getLong("schedule_id"));
				schedule.setName(resultSet.getString("name"));

				schedules.add(schedule);
			}
		} catch (SQLException e) {
			logger.error("Cannot retieve schedules", e);
			throw new DAOException("Cannot retrieve schedules", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					logger.trace("resultset closed");
				}
				if (statement != null) {
					statement.close();
					logger.trace("statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("connection closed");
				}
			} catch (SQLException e) {
				logger.warn("Cannot close connection ", e);
			}
		}
		logger.trace("schedules are retrieved");
		return schedules;
	}

	@Override
	public Schedule retrieveById(long id) throws DAOException {

		logger.trace("Retrieving schedule from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Schedule schedule = new Schedule();

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(RETRIEVE_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				schedule.setId(resultSet.getLong("schedule_id"));
				schedule.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			logger.error("Cannot retieve schedule with id " + schedule.getId(), e);
			throw new DAOException("Cannot retrieve schedule with id " + schedule.getId(), e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					logger.trace("resultset closed");
				}
				if (statement != null) {
					statement.close();
					logger.trace("resultset closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("resultset closed");
				}
			} catch (SQLException e) {
				logger.warn("Cannot close connection ", e);
			}
		}
		logger.trace("schedule with id " + schedule.getId() + " is retrieved");
		return schedule;
	}

	@Override
	public void update(Schedule schedule) throws DAOException {

		logger.trace("Updating lessonItem in DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, schedule.getName());
			statement.setLong(2, schedule.getId());

			statement.executeUpdate();
			logger.trace("schedule with id " + schedule.getId() + " is updated");
		} catch (SQLException e) {
			logger.error("Cannot update schedule with id " + schedule.getId(), e);
			throw new DAOException("Cannot update schedule with id " + schedule.getId(), e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					logger.trace("statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("connection closed");
				}
			} catch (SQLException e) {
				logger.warn("Cannot close connection ", e);
			}
		}
	}

	@Override
	public void remove(Schedule schedule) throws DAOException {

		logger.trace("Deleting schedule from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, schedule.getId());

			statement.executeUpdate();
			logger.trace("schedule with id " + schedule.getId() + " is deleted");
		} catch (SQLException e) {
			logger.error("Cannot delete schedule with id " + schedule.getId(), e);
			throw new DAOException("Cannot delete schedule with id " + schedule.getId(), e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					logger.trace("statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("connection closed");
				}
			} catch (SQLException e) {
				logger.warn("Cannot close connection ", e);
			}
		}
	}

}
