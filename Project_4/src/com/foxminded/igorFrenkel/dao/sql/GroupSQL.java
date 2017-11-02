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
import com.foxminded.igorFrenkel.domain.Group;

public class GroupSQL implements GenericDAO<Group>{

	private static final String DELETE = "DELETE FROM GROUPS WHERE group_id = ?";
	private static final String UPDATE = "UPDATE GROUPS SET number = ? WHERE group_id = ?";
	private static final String RETRIEVE_BY_ID = "SELECT number, group_id FROM GROUPS WHERE group_id =?";
	private static final String RETRIEVE = "SELECT group_id, number FROM GROUPS";
	private static final String SAVE = "INSERT INTO GROUPS (number) VALUES (?) RETURNING group_id";
	
	private static final Logger logger = Logger.getLogger(GroupSQL.class.getName());
		
	@Override
	public Group save(Group group) throws DAOException {
		
		logger.trace("Saving new group");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(SAVE);
			statement.setString(1, group.getNumber());

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				group.setId(resultSet.getLong("group_id"));
			}
		} catch (SQLException e) {
			logger.error("Cannot save group", e);
			throw new DAOException("Cannot save group", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					logger.trace("resultset closed");
				}
				if (statement != null) {
					logger.trace("statement closed");
					statement.close();
				}
				if (connection != null) {
					connection.close();
					logger.trace("connection closed");
				}
			} catch (SQLException e) {
				logger.warn("Cannot close connection ", e);
			}
		}
		logger.trace("group with id " + group.getId() + " is saved");
		return group;
	}

		@Override
		public List<Group> retrieve() throws DAOException {
			
			logger.trace("Retrieving groups from DataBase");
			List<Group> groups = new ArrayList<Group>();
			
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
					Group group = new Group();
					group.setId(resultSet.getLong("group_id"));
					group.setNumber(resultSet.getString("number"));
					
					groups.add(group);
				}
			} catch (SQLException e){
				logger.error("Cannot retieve groups", e);
				throw new DAOException("Cannot retrieve groups", e);
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
			logger.trace("groups are retrieved");
			return groups;
		}

	@Override
	public Group retrieveById(long id) throws DAOException {

		logger.trace("Retrieving group from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Group group = new Group();

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(RETRIEVE_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				group.setId(resultSet.getLong("group_id"));
				group.setNumber(resultSet.getString("number"));
			}
		} catch (SQLException e) {
			logger.error("Cannot retieve group with id " + group.getId(), e);
			throw new DAOException("Cannot retrieve group with id " + group.getId(), e);
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
		logger.trace("group with id " + group.getId() + " is retrieved");
		return group;
	}

		@Override
		public void update(Group group) throws DAOException {

			logger.trace("Updating group in DataBase");
			Connection connection = null;
			PreparedStatement statement = null;

			try {
				logger.trace("Open connection");
				connection = DaoFactory.getConnection();
				logger.trace("Create statement");
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, group.getNumber());
				statement.setLong(2, group.getId());
				
				statement.executeUpdate();
				logger.trace("group with id "+group.getId()+" is updated");
			} catch (SQLException e) {
				logger.error("Cannot update group with id " + group.getId(), e);
				throw new DAOException("Cannot update group with id " + group.getId(), e);
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
		public void remove(Group group) throws DAOException {

			logger.trace("Deleting group from DataBase");
			Connection connection = null;
			PreparedStatement statement = null;

			try {
				logger.trace("Open connection");
				connection = DaoFactory.getConnection();
				logger.trace("Create statement");
				statement = connection.prepareStatement(DELETE);
				statement.setLong(1, group.getId());

				statement.executeUpdate();
				logger.trace("group with id " + group.getId() + " is deleted");
			} catch (SQLException e) {
				logger.error("Cannot delete group with id " + group.getId(), e);
				throw new DAOException("Cannot delete group with id " + group.getId(), e);
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

