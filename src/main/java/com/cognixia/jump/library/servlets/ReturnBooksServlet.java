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
 * Servlet implementation class ReturnBooksServlet
 */
@WebServlet("/ReturnBooks")
public class ReturnBooksServlet extends HttpServlet {
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
		
		String[] isbn = request.getParameterValues("book");
		List<String> titles = new ArrayList<>();
		Patron patron = pdao.getPatronByUsername((String)request.getSession().getAttribute("user"));
		for(int i = 0; i < isbn.length; ++i) {
			pdao.returnBook(patron.getId(), isbn[i]);
			titles.add(bdao.getBookByIsbn(isbn[i]).getTitle());
		}
		
		request.setAttribute("returnedBooks", titles);
		request.setAttribute("rentedBooks", pdao.getRentedBooks(patron.getId()));
		request.setAttribute("bdao", new BookDAO());
		request.getRequestDispatcher("rentedlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
