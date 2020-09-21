package com.cognixia.jump.library.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.dao.LibrarianDAO;

/**
 * Servlet implementation class UnfreezePatronsServlet
 */
@WebServlet("/UnfreezePatrons")
public class UnfreezePatronsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LibrarianDAO ldao;

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
		String[] patrons = request.getParameterValues("book");
		for(int i = 0; i < patrons.length; ++i) {
			ldao.unfreezeAccount(patrons[i]);
		}
		
//		request.setAttribute("unfrozenAccounts", patrons);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("unfreezecomplete.jsp");
		request.setAttribute("frozenPatrons", ldao.getFrozenPatrons());
		RequestDispatcher dispatcher = request.getRequestDispatcher("frozenpatrons.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
