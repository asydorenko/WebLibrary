package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.dao.LibrarianDAO;
import com.cognixia.jump.library.model.Patron;

/**
 * Servlet implementation class AllPatronsServlet
 */
@WebServlet("/AllPatrons")
public class AllPatronsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibrarianDAO ldao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		ldao = new LibrarianDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Patron> allPatrons = ldao.getAllPatrons();
		request.setAttribute("allPatrons", allPatrons);
		request.getRequestDispatcher("allpatrons.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
