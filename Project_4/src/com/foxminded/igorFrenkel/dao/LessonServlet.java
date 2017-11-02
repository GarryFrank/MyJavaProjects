package com.foxminded.igorFrenkel.dao;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.foxminded.igorFrenkel.dao.sql.LessonItemSQL;
import com.foxminded.igorFrenkel.domain.LessonItem;

public class LessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LessonServlet.class.getName());
       
    public LessonServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "There are no lessons in the list";
		
		logger.trace("show lessons' info");
		response.setContentType("text/ html;charset=UTF-8");
		LessonItemSQL lessonItemSQL = new LessonItemSQL();
		try {
			List<LessonItem> lessons = lessonItemSQL.retrieve();
			if (!lessons.isEmpty()) {
				request.setAttribute("lessons", lessons);
				getServletContext().getRequestDispatcher("/lesson.jsp").forward(request, response);
            } else {
				logger.trace("There are no lessons in the list");
				request.setAttribute("error", error);
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (DAOException e) {
			logger.warn("cannot show lessons' info", e);
			System.out.println("Cannot retrieve lessons from DataBase");
		}
	}
}
