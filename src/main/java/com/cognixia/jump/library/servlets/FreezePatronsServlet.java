package com.cognixia.jump.library.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.dao.LibrarianDAO;

/**
 * Servlet implementation class FreezePatronsServlet
 */
@WebServlet("/FreezePatrons")
public class FreezePatronsServlet extends HttpServlet {
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
		String[] patrons = request.getParameterValues("patron");
		for(int i = 0; i < patrons.length; ++i) {
			ldao.togglePatrons(patrons[i]);
		}
		
		request.setAttribute("modifiedPatrons", patrons);
		request.setAttribute("allPatrons", ldao.getAllPatrons());
		request.getRequestDispatcher("allpatrons.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
