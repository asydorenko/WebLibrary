package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.dao.PatronDAO;
import com.cognixia.jump.library.model.Patron;

/**
 * Servlet implementation class ApplyChangesPatronServlet
 */
@WebServlet("/ApplyChangesPatron")
public class ApplyChangesPatronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private PatronDAO pdao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		conn = ConnectionManager.getConnection();
		pdao = new PatronDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Patron patron = pdao.getPatronByUsername((String)request.getSession().getAttribute("user"));
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		RequestDispatcher dispatcher = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from patron where username = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(!(patron.getUsername().equals(username)) && rs.next()) {
				request.setAttribute("errorMessage", 5);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				if(pdao.updatePatron(patron.getId(), firstName, lastName, username, password)) {
					request.getSession().setAttribute("user", username);
					request.setAttribute("errorMessage", 6);
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage", 4);
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
