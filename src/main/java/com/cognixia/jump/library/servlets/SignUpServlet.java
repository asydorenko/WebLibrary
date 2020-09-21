package com.cognixia.jump.library.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.library.connection.ConnectionManager;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn;
	
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		conn = ConnectionManager.getConnection();
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
//		RequestDispatcher dispatcher = null;
		
		// if user tries to go directly to SignUpServlet link, redirect to error page
		if(firstName == null) {
			request.setAttribute("errorMessage", 4);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		
//		Check if a user with such username already exists
		try {
			ps = conn.prepareStatement("select * from patron where username = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				request.setAttribute("errorMessage", 5);
				request.getRequestDispatcher("error.jsp").forward(request, response);;
			} else {
				// create new user in the database
				ps = conn.prepareStatement("insert into patron values(null, ?, ?, ?, ?, 1)");
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				ps.setString(3, username);
				ps.setString(4, password);
				//if success
				if(ps.executeUpdate() != 0) {
					request.setAttribute("errorMessage", 3);
					request.getRequestDispatcher("error.jsp").forward(request, response);;
					//if error
				} else {
					request.setAttribute("errorMessage", 4);
					request.getRequestDispatcher("error.jsp").forward(request, response);;
				}
			}
			
			rs.close();
			ps.close();
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
