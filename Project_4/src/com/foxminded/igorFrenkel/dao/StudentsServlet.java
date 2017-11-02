package com.foxminded.igorFrenkel.dao;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.foxminded.igorFrenkel.dao.sql.StudentSQL;
import com.foxminded.igorFrenkel.domain.Student;

public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LessonServlet.class.getName());
       
    public StudentsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String error = "There are no students in the list";
		
		logger.trace("show students' info");
		response.setContentType("text/ html;charset=UTF-8");
		StudentSQL studentSQL = new StudentSQL();
		try {
			List<Student> students = studentSQL.retrieve();
			if (!students.isEmpty()){
				request.setAttribute("students", students);
				getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);
			} else {
				logger.trace("There are no students in the list");
				request.setAttribute("error", error);
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (DAOException e) {
			logger.warn("cannot show sudents' info", e);
			System.out.println("Cannot retrieve students from DataBase");
		}
	}
}
