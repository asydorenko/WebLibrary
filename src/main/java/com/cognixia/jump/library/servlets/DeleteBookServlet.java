package com.cognixia.jump.library.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.dao.BookDAO;
import com.cognixia.jump.library.dao.LibrarianDAO;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.model.Book;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/DeleteBook")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Book book = (Book) session.getAttribute("bookToDelete");
		session.setAttribute("deletedTitle", book.getTitle());
		boolean bookDeleted = new LibrarianDAO().deleteBook(book.getIsbn());
		session.setAttribute("bookDeleted", bookDeleted);
		session.removeAttribute("bookToDelete");
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
