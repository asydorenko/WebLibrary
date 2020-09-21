package com.cognixia.jump.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.model.Book;
import com.cognixia.jump.library.model.Patron;
import com.mysql.cj.protocol.Resultset;

public class BookDAO {
	
	public static final Connection conn = ConnectionManager.getConnection();

	public Book getBookByIsbn(String isbn) {
		Book book = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select * from book where isbn = ?");
			ps.setString(1, isbn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), LocalDate.parse(rs.getString(5)));
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return book;
	}
	
	public String getCheckoutDate(String isbn) {
		String result = "";
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("select checkedout from book_checkout where isbn = ? and returned is null");
			ps.setString(1, isbn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	public String getDueDate(String isbn) {
		String result = "";
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("select due_date from book_checkout where isbn = ? and returned is null");
			ps.setString(1, isbn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getRenterInfo(String isbn) {
			String patron = null;
			PreparedStatement ps;
			
			try {
				ps = conn.prepareStatement("select patron_id from book_checkout where isbn = ? and returned is null");
				ps.setString(1, isbn);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					ps = conn.prepareStatement("select username from patron where patron_id = ?");
					ps.setInt(1, rs.getInt(1));
					rs = ps.executeQuery();
					if(rs.next()) {
						patron = rs.getString(1);
					}
				}
				
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return patron;
	}
	
	public List<String[]> getRentHistory(String isbn) {
		List<String[]> bookHistory = new ArrayList<>();
		PatronDAO pdao = new PatronDAO();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("select patron_id, checkedout, due_date, returned from book_checkout where isbn = ?");
			ps.setString(1, isbn);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bookHistory.add(new String[] {pdao.getPatronByID(rs.getInt(1)).getUsername(), rs.getString(2), rs.getString(3), rs.getString(4)});
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookHistory;
	}
}
