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
import com.foxminded.igorFrenkel.domain.Student;
import com.foxminded.igorFrenkel.domain.Group;

public class StudentSQL implements GenericDAO<Student> {

	private static final String DELETE = "DELETE FROM STUDENTS WHERE student_id = ?";
	private static final String UPDATE = "UPDATE STUDENTS SET name = ? WHERE student_id = ?";
	private static final String RETRIEVE_BY_ID = "SELECT name, student_id, group_id FROM STUDENTS WHERE student_id = ?";
	private static final String RETRIEVE = "SELECT student_id, name, group_id FROM STUDENTS ";
	private static final String SAVE = "INSERT INTO STUDENTS (name, group_id) VALUES (?,?) RETURNING student_id";

	private static final Logger logger = Logger.getLogger(StudentSQL.class
			.getName());

	@Override
	public Student save(Student student) throws DAOException {

		logger.trace("Saving new student");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(SAVE);

			GroupSQL groupSQL = new GroupSQL();
			Group group = groupSQL.save(student.getGroup());

			statement.setString(1, student.getName());
			statement.setLong(2, group.getId());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				student.setId(resultSet.getLong("student_id"));
			}
		} catch (SQLException e) {
			logger.error("Cannot save student ", e);
			throw new DAOException("Cannot save student ", e);
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
		logger.trace("student with id " + student.getId() + " is saved");
		return student;
	}

	@Override
	public List<Student> retrieve() throws DAOException {

		logger.trace("Retrieving students from DataBase");
		List<Student> students = new ArrayList<Student>();

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
				Student student = new Student();
				GroupSQL groupSQL = new GroupSQL();
				long groupId = resultSet.getLong("group_id");
				Group group = groupSQL.retrieveById(groupId);

				student.setId(resultSet.getLong("student_id"));
				student.setName(resultSet.getString("name"));
				student.setGroup(group);

				students.add(student);
			}
		} catch (SQLException e) {
			logger.error("Cannot retieve students", e);
			throw new DAOException("Cannot retrieve students", e);
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
		logger.trace("students are retrieved");
		return students;
	}

	@Override
	public Student retrieveById(long id) throws DAOException {

		logger.trace("Retrieving student from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Student student = new Student();

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(RETRIEVE_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				GroupSQL groupSQL = new GroupSQL();
				long groupId = resultSet.getLong("group_id");
				Group group = groupSQL.retrieveById(groupId);

				student.setId(resultSet.getLong("student_id"));
				student.setName(resultSet.getString("name"));
				student.setGroup(group);
			}
		} catch (SQLException e) {
			logger.error("Cannot retieve student with id " + student.getId(), e);
			throw new DAOException("Cannot retrieve student with id " + student.getId(), e);
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
		logger.trace("student with id " + student.getId() + " is retrieved");
		return student;
	}

	@Override
	public void update(Student student) throws DAOException {

		logger.trace("Updating student in DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(UPDATE);

			GroupSQL groupSQL = new GroupSQL();
			groupSQL.save(student.getGroup());

			statement.setString(1, student.getName());
			statement.setLong(2, student.getId());

			statement.executeUpdate();
			logger.trace("student with id " + student.getId() + " is updated");
		} catch (SQLException e) {
			logger.error("Cannot update student with id " + student.getId(), e);
			throw new DAOException("Cannot update student with id " + student.getId(), e);
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
	public void remove(Student student) throws DAOException {

		logger.trace("Deleting student from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, student.getId());

			statement.executeUpdate();
			logger.trace("student with id " + student.getId() + " is deleted");
		} catch (SQLException e) {
			logger.error("Cannot delete student with id " + student.getId(), e);
			throw new DAOException("Cannot delete student with id " + student.getId(), e);
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
