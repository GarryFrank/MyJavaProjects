package com.foxminded.igorFrenkel.dao.sql;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.foxminded.igorFrenkel.dao.DAOException;
import com.foxminded.igorFrenkel.dao.DaoFactory;
import com.foxminded.igorFrenkel.dao.GenericDAO;
import com.foxminded.igorFrenkel.domain.Classroom;

public class ClassroomSQL implements GenericDAO<Classroom>{
	
	private static final String DELETE = "DELETE FROM CLASSROOMS WHERE classroom_id = ?";
	private static final String UPDATE = "UPDATE CLASSROOMS SET NUMBER = ? WHERE classroom_id = ?";
	private static final String RETRIEVE_BY_ID = "SELECT * FROM CLASSROOMS WHERE classroom_id =?";
	private static final String RETRIEVE = "SELECT classroom_id, number FROM CLASSROOMS";
	private static final String SAVE = "INSERT INTO CLASSROOMS (number) VALUES (?) RETURNING classroom_id";
	
	private static final Logger logger = Logger.getLogger(ClassroomSQL.class.getName());
	
	@Override
	public Classroom save(Classroom classroom) throws DAOException {

		logger.trace("Saving new classroom");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(SAVE);
			statement.setString(1, classroom.getNumber());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				classroom.setId(resultSet.getLong("classroom_id"));
			}
		} catch (SQLException e) {
			logger.error("Cannot create classroom", e);
			throw new DAOException("Cannot create classroom", e);
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
		logger.trace("classroom with id " + classroom.getId() + " is saved");
		return classroom;
	}

	@Override
	public List<Classroom> retrieve() throws DAOException {
		
		
		logger.trace("Retrieving classrooms from DataBase");
		List<Classroom> classrooms = new ArrayList<Classroom>();
		
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
				Classroom classroom = new Classroom();
				classroom.setId(resultSet.getLong("number"));
				classroom.setNumber(resultSet.getString("classroom_id"));
				
				classrooms.add(classroom);
			}
		} catch (SQLException e){
			logger.error("Cannot retrieve classrooms", e);
			throw new DAOException("Cannot retrieve classrooms", e);
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
		logger.trace("classrooms are retrieved");
		return classrooms;
	}

	@Override
	public Classroom retrieveById(long id) throws DAOException {

		logger.trace("Retrieving classroom from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Classroom classroom = new Classroom();

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(RETRIEVE_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while(resultSet.next()){
			classroom.setId(resultSet.getLong("classroom_id"));
			classroom.setNumber(resultSet.getString("number"));
			}
		} catch (SQLException e) {
			logger.error("Cannot retrieve classroom witn id " + classroom.getId(), e);
			throw new DAOException("Cannot retrieve classroom with id " + classroom.getId(), e);
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
		logger.trace("classroom witn id " + classroom.getId() + " is retrieved");
		return classroom;
	}

	@Override
	public void update(Classroom classroom) throws DAOException {

		logger.trace("Updating classroom in DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, classroom.getNumber());
			statement.setLong(2, classroom.getId());
			
			statement.executeUpdate();
			logger.trace("classroom with id " + classroom.getId() + " is updated");
		} catch (SQLException e) {
			logger.error("Cannot update classroom with id " + classroom.getId(), e);
			throw new DAOException("Cannot update classroom with id " + classroom.getId(), e);
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
	public void remove(Classroom classroom) throws DAOException {

		logger.trace("Deleting classroom from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, classroom.getId());

			statement.executeUpdate();
			logger.trace("classroom with " + classroom.getId() + " is deleted");
		} catch (SQLException e) {
			logger.error("Cannot delete classroom witn id " + classroom.getId(), e);
			throw new DAOException("Cannot delete classroom with id " + classroom.getId(), e);
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
