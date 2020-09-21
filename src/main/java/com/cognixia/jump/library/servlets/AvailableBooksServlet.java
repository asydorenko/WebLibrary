package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.model.Book;

/**
 * Servlet implementation class AvailableBooksServlet
 */
@WebServlet("/AvailableBooksServlet")
public class AvailableBooksServlet extends HttpServlet {
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
		List<Book> availableBooks = pdao.getAvailableBooks();
		request.setAttribute("availableBooks", availableBooks);
		request.setAttribute("rentedBooks", new ArrayList<String>());
		RequestDispatcher dispatcher = request.getRequestDispatcher("availablebooks.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
