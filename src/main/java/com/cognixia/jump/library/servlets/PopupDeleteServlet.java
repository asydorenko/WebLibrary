package com.cognixia.jump.library.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.dao.BookDAO;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.model.Book;

/**
 * Servlet implementation class PopupDeleteServlet
 */
@WebServlet("/PopupDelete")
public class PopupDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("deleteBook");
		request.setAttribute("allBooks", new PatronDAO().getAllBooks());
		Book book = new BookDAO().getBookByIsbn(isbn);
		HttpSession session = request.getSession(false);
		session.setAttribute("bookToDelete", book);
		session.setAttribute("showDeleteForm", true);
		response.sendRedirect("LibrarianBooks");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
