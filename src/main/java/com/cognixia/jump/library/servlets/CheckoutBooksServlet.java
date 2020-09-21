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

import com.cognixia.jump.library.dao.BookDAO;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.model.Patron;

/**
 * Servlet implementation class CheckoutBooksServlet
 */
@WebServlet("/CheckoutBooks")
public class CheckoutBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatronDAO pdao;
	private BookDAO bdao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		pdao = new PatronDAO();
		bdao = new BookDAO();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String[] isbn = request.getParameterValues("book");
//		String[] titles = new String[isbn.length];
		List<String> titles = new ArrayList<>();
		Patron patron = pdao.getPatronByUsername((String)request.getSession().getAttribute("user"));
		for(int i = 0; i < isbn.length; ++i) {
			pdao.checkoutBook(patron.getId(), isbn[i]);
//			titles[i] = bdao.getBookByIsbn(isbn[i]).getTitle();
			titles.add(bdao.getBookByIsbn(isbn[i]).getTitle());
		}
		
		request.setAttribute("rentedBooks", titles);
		request.setAttribute("allBooks", pdao.getAllBooks());
//		RequestDispatcher dispatcher = request.getRequestDispatcher("rented.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("allbooks.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
