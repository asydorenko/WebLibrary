package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.dao.BookDAO;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.model.Book;

/**
 * Servlet implementation class LibrarianBooksServlet
 */
@WebServlet("/LibrarianBooks")
public class LibrarianBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDAO bdao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		bdao = new BookDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> allBooks = new PatronDAO().getAllBooks();
		request.setAttribute("allBooks", allBooks);
		List<String[]> renterInfo = new ArrayList<>(); 
		
		for(int i = 0; i < allBooks.size(); ++i) {
			if(allBooks.get(i).isRented() == 1) {
				// [renter username], [rent date], [due date]
				renterInfo.add(new String[] {bdao.getRenterInfo(allBooks.get(i).getIsbn()), 
						bdao.getCheckoutDate(allBooks.get(i).getIsbn()), 
						bdao.getDueDate(allBooks.get(i).getIsbn())});
			} else {
				renterInfo.add(null);
			}
		}
		
		request.setAttribute("renterInfo", renterInfo);
		request.getRequestDispatcher("librarianbooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
