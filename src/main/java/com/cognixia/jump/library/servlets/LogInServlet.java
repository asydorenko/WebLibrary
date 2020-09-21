package com.cognixia.jump.library.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.dao.LibrarianDAO;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.model.Librarian;
import com.cognixia.jump.library.model.Patron;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogIn")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PatronDAO pdao;
	private LibrarianDAO ldao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		pdao = new PatronDAO();
		ldao = new LibrarianDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Patron patron = pdao.getPatronByUsername(request.getParameter("username"));
		Librarian librarian = ldao.getLibrarianByUsername(request.getParameter("username"));
		
		// if no such patron and librarian exist
		if(patron == null && librarian == null) {
			request.setAttribute("errorMessage", 1);
			request.getRequestDispatcher("error.jsp").forward(request, response);;
			
//			if patron username found
		} else if (patron != null) {
//			check the password
			if(patron.getPassword().equals(request.getParameter("password"))) {
		
				if(patron.isFrozen() == 1) {
					request.setAttribute("errorMessage", 2);
					request.getRequestDispatcher("error.jsp").forward(request, response);;
				} else {
					HttpSession session = request.getSession(true);
//					session.setMaxInactiveInterval(60);
					session.setAttribute("user", patron.getUsername());
					request.getRequestDispatcher("patron.jsp").forward(request, response);;
				}
//				wrong password
			} else {
				request.setAttribute("errorMessage", 1);
				request.getRequestDispatcher("error.jsp").forward(request, response);;
			}
			//if librarian username found
		} else {
			if(librarian.getPassword().equals(request.getParameter("password"))) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", librarian.getUsername());
				request.getRequestDispatcher("librarian.jsp").forward(request, response);;
			} else {
				request.setAttribute("errorMessage", 1);
				request.getRequestDispatcher("error.jsp").forward(request, response);;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
