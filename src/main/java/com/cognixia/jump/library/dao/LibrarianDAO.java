package com.cognixia.jump.library.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.library.connection.ConnectionManager;
import com.cognixia.jump.library.model.Librarian;
import com.cognixia.jump.library.model.Patron;

public class LibrarianDAO {

	public static final Connection conn = ConnectionManager.getConnection();
	
	public boolean addBook(String isbn, String title, String description) {
			PreparedStatement ps;
			
			try {
				ps = conn.prepareStatement("insert into book values(?, ?, ?, 0, current_date())");
				ps.setString(1, isbn);
				ps.setString(2, title);
				ps.setString(3, description);
				int res = ps.executeUpdate();
				ps.close();
				if(res != 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public boolean deleteBook(String isbn) {
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("delete from book where isbn = ?");
			ps.setString(1, isbn);
			int res = ps.executeUpdate();
			ps.close();
			if(res != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateBook(String isbn, String title, String description) {
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("update book set title = ?, descr = ? where isbn = ?");
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setString(3, isbn);
			int res = ps.executeUpdate();
			ps.close();
			if(res != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean unfreezeAccount(String username) {
		
		PreparedStatement ps = null;
		int res = 0;
		
		try {
			ps = conn.prepareStatement("update patron set account_frozen = 0 where username = ?");
			ps.setString(1, username);
			res = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean freezeAccount(String username) {
		PreparedStatement ps = null;
		int res = 0;
		
		try {
			ps = conn.prepareStatement("update patron set account_frozen = 1 where username = ?");
			ps.setString(1, username);
			res = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (res > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean updateUsername(String newUsername) {
		
		return false;
	}
	
	public boolean updatePassword(String newPassword) {
		
		return false;
	}
	
	public Librarian getLibrarianByUsername(String username) {
		Librarian librarian = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from librarian where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				librarian = new Librarian(rs.getInt(1), rs.getString(2), rs.getString(3));
				rs.close();
				ps.close();
				return librarian;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return librarian;
	}
	
	public List<Patron> getAllPatrons(){
		List<Patron> allPatrons = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from patron");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Patron patron = new Patron(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getNString(5), rs.getInt(6));
				allPatrons.add(patron);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allPatrons;
	}
	
	public List<Patron> getFrozenPatrons(){
		List<Patron> frozenPatrons = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from patron where account_frozen = 1");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Patron patron = new Patron(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getNString(5), rs.getInt(6));
				frozenPatrons.add(patron);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return frozenPatrons;
	}
	
	public List<Patron> getActivePatrons(){
		List<Patron> activePatrons = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from patron where account_frozen = 0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Patron patron = new Patron(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getNString(5), rs.getInt(6));
				activePatrons.add(patron);
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return activePatrons;
	}
	
	public void togglePatrons(String patron) {
		
		if(new PatronDAO().getPatronByUsername(patron).isFrozen() == 0) {
			freezeAccount(patron);
		} else {
			unfreezeAccount(patron);
		}
	}

}
