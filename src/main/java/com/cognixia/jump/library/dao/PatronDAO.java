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
import com.cognixia.jump.library.model.BookCheckout;
import com.cognixia.jump.library.model.Patron;

public class PatronDAO {
	
	public static final Connection conn = ConnectionManager.getConnection();
	
	
	public boolean checkoutBook(int patronId, String isbn) {
		PreparedStatement ps = null;
		int res = 0;
		
		try {
			ps = conn.prepareStatement("insert into book_checkout values(null, ?, ?, ?, ?, null)");
			ps.setInt(1, patronId);
			ps.setString(2, isbn);
			ps.setString(3, LocalDate.now().toString());
			ps.setString(4, LocalDate.now().plusDays(7).toString());
			
			res = ps.executeUpdate();
			
			if(res > 0) {
				ps = conn.prepareStatement("update book set rented = 1 where isbn = ?");
				ps.setString(1, isbn);
				res = ps.executeUpdate();
			}
			
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean returnBook(int patronId, String isbn) {
		PreparedStatement ps = null;
		int res = 0;
		
		try {
			ps = conn.prepareStatement("update book_checkout set returned = current_date() where isbn = ? and patron_id = ? and returned is null");
			ps.setString(1, isbn);
			ps.setInt(2, patronId);
			res = ps.executeUpdate();
			if(res > 0) {
				ps = conn.prepareStatement("update book set rented = 0 where isbn = ?");
				ps.setString(1, isbn);
				res = ps.executeUpdate();
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(res > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean updatePatron(int id, String firstName, String lastName, String username, String password) {
		
		PreparedStatement ps = null;
		int res = 0;
		
		try {
			ps = conn.prepareStatement("update patron set first_name = ? where patron_id = ?");
			ps.setString(1, firstName);
			ps.setInt(2, id);
			res += ps.executeUpdate();
			
			ps = conn.prepareStatement("update patron set last_name = ? where patron_id = ?");
			ps.setString(1, lastName);
			ps.setInt(2, id);
			res += ps.executeUpdate();
			
			ps = conn.prepareStatement("update patron set username = ? where patron_id = ?");
			ps.setString(1, username);
			ps.setInt(2, id);
			res += ps.executeUpdate();
			
			ps = conn.prepareStatement("update patron set password = ? where patron_id = ?");
			ps.setString(1, password);
			ps.setInt(2, id);
			res += ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(res == 4) {
			return true;
		} else {
			return false;
		}
	}
	
	public Patron getPatronByUsername(String username) {
		Patron patron = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from patron where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				patron = new Patron(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				rs.close();
				ps.close();
				return patron;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patron;
	}
	
	public Patron getPatronByID(int id) {
		Patron patron = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from patron where patron_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				patron = new Patron(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				rs.close();
				ps.close();
				return patron;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patron;
	}
	
	public List<Book> getAllBooks(){
		List<Book> allBooks = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from book");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Book book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), LocalDate.parse(rs.getString(5)));
				allBooks.add(book);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allBooks;
	}
	
	public List<Book> getAvailableBooks(){
		List<Book> availableBooks = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from book where rented = 0 ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Book book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), LocalDate.parse(rs.getString(5)));
				availableBooks.add(book);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return availableBooks;
	}
	
	public List<Book> getRentedBooks(int userId){
		List<Book> rentedBooks = new ArrayList<>();
		ResultSet rs2 = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select isbn from book_checkout where patron_id = ? and returned is null");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ps = conn.prepareStatement("select * from book where isbn = ?");
				ps.setString(1, rs.getString(1));
				rs2 = ps.executeQuery();
				rs2.next();
				Book book = new Book(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getInt(4), LocalDate.parse(rs2.getString(5)));
				rentedBooks.add(book);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rentedBooks;
	}
	
	public List<BookCheckout> getRentalHistory(int patronId) {
		
		List<BookCheckout> history = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from book_checkout where patron_id = ?");
			ps.setInt(1, patronId);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookCheckout record = new BookCheckout(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				history.add(record);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return history;
	}
	
	

}
