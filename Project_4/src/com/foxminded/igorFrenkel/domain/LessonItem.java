package com.foxminded.igorFrenkel.domain;

import java.util.Date;


public class LessonItem {

	private String lesson;
	private long id;
	private Classroom classroom;
	private Group group;
	private Teacher teacher;
	private Date date;

	public void setId(long id) {
		this.id = id;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public Group getGroup() {
		return group;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public Date getDate() {
		return date;
	}

	public String getLesson() {
		return lesson;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LessonItem other = (LessonItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LessonItem [lesson=" + lesson + ", id=" + id + ", classroom="
				+ classroom + ", group=" + group + ", teacher=" + teacher
				+ ", date=" + date + "]";
	}

	
}

