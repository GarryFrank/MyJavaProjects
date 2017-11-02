package com.foxminded.igorFrenkel.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.foxminded.igorFrenkel.dao.DAOException;
import com.foxminded.igorFrenkel.dao.DaoFactory;
import com.foxminded.igorFrenkel.dao.GenericDAO;
import com.foxminded.igorFrenkel.domain.Classroom;
import com.foxminded.igorFrenkel.domain.Group;
import com.foxminded.igorFrenkel.domain.LessonItem;
import com.foxminded.igorFrenkel.domain.Teacher;

public class LessonItemSQL implements GenericDAO<LessonItem> {

	private static final String DELETE = "DELETE FROM LESSONITEMS WHERE lessonitem_id = ?";
	private static final String UPDATE = "UPDATE LESSONITEMS SET classroom_id = ? WHERE lessonitem_id = ?";
	private static final String RETRIEVE_BY_ID = "SELECT * FROM LESSONITEMS WHERE lessonitem_id =?";
	private static final String RETRIEVE = "select lessonItem_id, lesson, lessonitems.classroom_id, "
			+ "lessonitems.group_id, lessonitems.teacher_id, date, classrooms.number, teachers.name, groups.number "
			+ "from lessonitems inner join "
			+ "classrooms on (lessonItems.classroom_id = classrooms.classroom_id)"
			+ "inner join teachers on (lessonitems.teacher_id = teachers.teacher_id)"
			+ "inner join groups on (lessonitems.group_id = groups.group_id)";
	private static final String SAVE = "INSERT INTO LESSONITEMS (lesson, classroom_id, group_id, teacher_id, date "
			+ "VALUES (?,?,?,?,?)) RETURNING lessonItem_id";

	private static final Logger logger = Logger.getLogger(LessonItemSQL.class
			.getName());

	@Override
	public LessonItem save(LessonItem lessonItem) throws DAOException {

		logger.trace("Saving new lessonItem");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(SAVE);

			GroupSQL groupSQL = new GroupSQL();
			ClassroomSQL classroomSQL = new ClassroomSQL();
			TeacherSQL teacherSQL = new TeacherSQL();

			Group group = groupSQL.save(lessonItem.getGroup());
			Classroom classroom = classroomSQL.save(lessonItem.getClassroom());
			Teacher teacher = teacherSQL.save(lessonItem.getTeacher());

			statement.setString(1, lessonItem.getLesson());
			statement.setLong(2, classroom.getId());
			statement.setLong(3, group.getId());
			statement.setLong(4, teacher.getId());
			statement.setTimestamp(5, new Timestamp(lessonItem.getDate()
					.getTime()));

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lessonItem.setId(resultSet.getLong("lessonItem_id"));
			}
		} catch (SQLException e) {
			logger.error("Cannot save lessonItem", e);
			throw new DAOException("Cannot save lessonItem", e);
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
		logger.trace("LessonItem with id " + lessonItem.getId() + " is saved");
		return lessonItem;
	}

	@Override
	public List<LessonItem> retrieve() throws DAOException {

		logger.trace("Retrieving lessonItems from DataBase");
		List<LessonItem> lessonItems = new ArrayList<LessonItem>();

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

				LessonItem lessonItem = new LessonItem();
				ClassroomSQL classroomSQL = new ClassroomSQL();
				GroupSQL groupSQL = new GroupSQL();
				TeacherSQL teacherSQL = new TeacherSQL();

				long classroomId = resultSet
						.getLong("classroom_id");
				Classroom classroom = classroomSQL.retrieveById(classroomId);
				long groupId = resultSet.getLong("group_id");
				Group group = groupSQL.retrieveById(groupId);
				long teacherId = resultSet.getLong("teacher_id");
				Teacher teacher = teacherSQL.retrieveById(teacherId);

				lessonItem.setId(resultSet.getLong("lessonItem_id"));
				lessonItem.setLesson(resultSet.getString("lesson"));
				lessonItem.setDate(resultSet.getDate("date"));

				lessonItem.setClassroom(classroom);
				lessonItem.setGroup(group);
				lessonItem.setTeacher(teacher);

				lessonItems.add(lessonItem);
			}
		} catch (SQLException e) {
			logger.error("Cannot retieve lessonItems", e);
			throw new DAOException("Cannot retrieve lessonItems", e);
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
		logger.trace("lessonItems are retrieved");
		return lessonItems;
	}

	@Override
	public LessonItem retrieveById(long id) throws DAOException {

		logger.trace("Retrieving lessonItem from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		LessonItem lessonItem = new LessonItem();

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(RETRIEVE_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				GroupSQL groupSQL = new GroupSQL();
				ClassroomSQL classroomSQL = new ClassroomSQL();
				TeacherSQL teacherSQL = new TeacherSQL();

				long teacherId = resultSet.getLong("teacher_id");
				long groupId = resultSet.getLong("group_id");
				long classroomId = resultSet.getLong("classroom_id");

				Teacher teacher = teacherSQL.retrieveById(teacherId);
				Group group = groupSQL.retrieveById(groupId);
			    Classroom classroom = classroomSQL.retrieveById(classroomId);

				lessonItem.setId(resultSet.getLong("lessonitem_id"));
				lessonItem.setLesson(resultSet.getString("lesson"));
				lessonItem.setDate(resultSet.getDate("date"));
				lessonItem.setClassroom(classroom);
				lessonItem.setGroup(group);
				lessonItem.setTeacher(teacher);
			}
		} catch (SQLException e) {
			logger.error("Cannot retrieve lessonItem with id " + lessonItem.getId(), e);
			throw new DAOException("Cannot retrieve lessonItem with id " + lessonItem.getId(), e);
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
		logger.trace("lessonItem with id " + lessonItem.getId() + " is retrieved");
		return lessonItem;
	}

	@Override
	public void update(LessonItem lessonItem) throws DAOException {

		logger.trace("Updating lessonItem in DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(UPDATE);

			GroupSQL groupSQL = new GroupSQL();
			ClassroomSQL classroomSQL = new ClassroomSQL();
			TeacherSQL teacherSQL = new TeacherSQL();

			groupSQL.save(lessonItem.getGroup());
			Classroom classroom = classroomSQL.save(lessonItem.getClassroom());
			teacherSQL.save(lessonItem.getTeacher());

			statement.setLong(1, classroom.getId());
			statement.setLong(2, lessonItem.getId());

			statement.executeUpdate();
			logger.trace("lessonItem with id "+lessonItem.getId()+ " is updated");
		} catch (SQLException e) {
			logger.error("Cannot update lessonItem with id " + lessonItem.getId(), e);
			throw new DAOException("Cannot update lessonItem with id " + lessonItem.getId(), e);
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
	public void remove(LessonItem lessonItem) throws DAOException {

		logger.trace("Deleting group from DataBase");
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = DaoFactory.getConnection();
			logger.trace("Create statement");
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, lessonItem.getId());

			statement.executeUpdate();
			logger.trace("lessonItem with id "+lessonItem.getId()+ " is deleted");
		} catch (SQLException e) {
			logger.error("Cannot delete lessonItem with id " + lessonItem.getId(), e);
			throw new DAOException("Cannot delete lessonItem with id " + lessonItem.getId(), e);
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
