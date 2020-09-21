package com.cognixia.jump.library.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.model.Patron;

/**
 * Servlet implementation class EditPatronServlet
 */
@WebServlet("/EditPatron")
public class EditPatronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatronDAO pdao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		pdao = new PatronDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Patron patron = pdao.getPatronByUsername((String)request.getSession().getAttribute("user"));
		request.setAttribute("patron", patron);
		request.getRequestDispatcher("editpatron.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
