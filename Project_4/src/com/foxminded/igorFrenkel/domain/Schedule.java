package com.foxminded.igorFrenkel.domain;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

	private String name;
	private long id;
	private List<LessonItem> lessonItems = new ArrayList<LessonItem>();

	public String getName() {
		return name;
	}

	public List<LessonItem> getLessonItems() {
		return lessonItems;
	}

	void addLessonItem(LessonItem lessonItem) {
		lessonItems.add(lessonItem);
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	void removeLessonItem(LessonItem lessonItem) {
		lessonItems.remove(lessonItems);
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
		Schedule other = (Schedule) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Schedule [name=" + name + ", id=" + id + ", lessonItems="
				+ lessonItems + "]";
	}

	
}

