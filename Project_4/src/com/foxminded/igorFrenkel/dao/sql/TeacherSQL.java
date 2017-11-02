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
import com.foxminded.igorFrenkel.domain.Teacher;

public class TeacherSQL implements GenericDAO<Teacher>{
	
	private static final String DELETE = "DELETE FROM TEACHERS WHERE teacher_id = ?";
	private static final String UPDATE = "UPDATE TEACHERS SET name = ? WHERE teacher_id = ?";
	private static final String RETRIEVE_BY_ID = "SELECT name, teacher_id FROM TEACHERS WHERE teacher_id = ?";
	private static final String RETRIEVE = "SELECT teacher_id, name FROM TEACHERS";
	private static final String SAVE = "INSERT INTO TEACHERS (name) VALUES (?) RETURNING teacher_id";
	
	private static final Logger logger = Logger.getLogger(TeacherSQL.class
			.getName());

	@Override
	public Teacher save(Teacher teacher) throws DAOException {

		logger.trace("Saving new teacher");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(SAVE);

			statement.setString(1, teacher.getName());

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				teacher.setId(resultSet.getLong("teacher_id"));
			}
		} catch (SQLException e) {
			logger.error("Cannot save teacher ", e);
			throw new DAOException("Cannot save teacher ", e);
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
		logger.trace("teacher with id " + teacher.getId() + " is saved");
		return teacher;
	}

	@Override
	public List<Teacher> retrieve() throws DAOException {

		logger.trace("Retrieving teachers from DataBase");
		List<Teacher> teachers = new ArrayList<Teacher>();

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
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getLong("teacher_id"));
				teacher.setName(resultSet.getString("name"));

				teachers.add(teacher);
			}
		} catch (SQLException e) {
			logger.error("Cannot retieve teachers", e);
			throw new DAOException("Cannot retrieve teachers", e);
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
		logger.trace("teachers are retrieved");
		return teachers;
	}

	@Override
	public Teacher retrieveById(long id) throws DAOException {

		logger.trace("Retrieving teacher from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Teacher teacher = new Teacher();

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(RETRIEVE_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				teacher.setId(resultSet.getLong("teacher_id"));
				teacher.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			logger.error("Cannot retieve teacher with id " + teacher.getId(), e);
			throw new DAOException("Cannot retrieve teacher with id " + teacher.getId(), e);
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
		logger.trace("teacher with id " + teacher.getId() + " is retrieved");
		return teacher;
	}

	@Override
	public void update(Teacher teacher) throws DAOException {

		logger.trace("Updating teacher in DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, teacher.getName());
			statement.setLong(2, teacher.getId());

			statement.executeUpdate();
			logger.trace("teacher with id " + teacher.getId() + " is updated");
		} catch (SQLException e) {
			logger.error("Cannot update teacher with id " + teacher.getId(), e);
			throw new DAOException("Cannot update teacher with id " + teacher.getId(), e);
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
	public void remove(Teacher teacher) throws DAOException {

		logger.trace("Deleting teacher from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, teacher.getId());

			statement.executeUpdate();
			logger.trace("teacher with id " + teacher.getId() + " is deleted");
		} catch (SQLException e) {
			logger.error("Cannot delete teacher with id " + teacher.getId(), e);
			throw new DAOException("Cannot delete teacher with id " + teacher.getId(), e);
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
