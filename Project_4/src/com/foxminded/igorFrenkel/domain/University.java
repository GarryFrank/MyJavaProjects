package com.foxminded.igorFrenkel.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class University {

	private static final Logger logger = Logger.getLogger(University.class
			.getName());

	private String name;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	private List<Group> groups = new ArrayList<Group>();
	private List<Classroom> classrooms = new ArrayList<Classroom>();
	private List<Schedule> schedules = new ArrayList<Schedule>();

	private Map<Teacher, Schedule> teacherDaySchedule = new HashMap<Teacher, Schedule>();
	private Map<Group, Schedule> studentDaySchedule = new HashMap<Group, Schedule>();
	private Map<Teacher, Map<Teacher, Schedule>> teacherMonthSchedule = new HashMap<Teacher, Map<Teacher, Schedule>>();
	private Map<Group, Map<Group, Schedule>> studentMonthSchedule = new HashMap<Group, Map<Group, Schedule>>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void addGroup(Group group) {
		groups.add(group);
	}

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void addClassroom(Classroom classroom) {
		classrooms.add(classroom);
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void addSchedule(Schedule schedule) {
		schedules.add(schedule);
	}

	public Map<Group, Schedule> getStudentDaySchedule() {
		return studentDaySchedule;
	}

	public void setStudentDaySchedule(Group group, Schedule schedule) {
		studentDaySchedule.put(group, schedule);
	}

	public Map<Teacher, Schedule> getTeacherDaySchedule() {
		return teacherDaySchedule;
	}

	public void setTeacherDaySchedule(Teacher teacher, Schedule schedule) {
		teacherDaySchedule.put(teacher, schedule);
	}

	public Map<Teacher, Map<Teacher, Schedule>> getTeacherMonthSchedule() {
		return teacherMonthSchedule;
	}

	public void setTeacherMonthSchedule(Teacher teacher,
			Map<Teacher, Schedule> teacherDaySchedule) {
		teacherMonthSchedule.put(teacher, teacherDaySchedule);
	}

	public Map<Group, Map<Group, Schedule>> getStudentMonthSchedule() {
		return studentMonthSchedule;
	}

	public void setStudentMonthSchedule(Group group,
			Map<Group, Schedule> studentDaySchedule) {
		studentMonthSchedule.put(group, studentDaySchedule);
	}

	public Student enrollStudent() {
		return new Student();
	}

	public Group createGroup() {
		return new Group();
	}

	public Teacher enrollTeacher(String name) {
		logger.info("teacher is enrolled");
		return new Teacher();
	}

	public LessonItem createLessonItem() {
		logger.info("lessonItem is created");
		return new LessonItem();
	}

	public Classroom createClassroom() {
		logger.info("classroom is created");
		return new Classroom();
	}

	public Schedule createSchedule(String name) {
		logger.info("schedule is created");
		return new Schedule();
	}


}
