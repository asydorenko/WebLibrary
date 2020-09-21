package com.cognixia.jump.library.model;

public class BookCheckout {
	
	private int checkoutId;
	private int patron_id;
	private String isbn;
	private String checkoutDate;
	private String dueDate;
	private String returnedDate;
	
	public BookCheckout(int checkoutId, int patron_id, String isbn, String checkoutDate, String dueDate,
			String returnedDate) {
		super();
		this.checkoutId = checkoutId;
		this.patron_id = patron_id;
		this.isbn = isbn;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.returnedDate = returnedDate;
	}

	public int getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(int checkoutId) {
		this.checkoutId = checkoutId;
	}

	public int getPatron_id() {
		return patron_id;
	}

	public void setPatron_id(int patron_id) {
		this.patron_id = patron_id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getReturnedDate() {
		String test = returnedDate + "";
		if(test.equals("null")) {
			return "---";
		}
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	@Override
	public String toString() {
		return "BookCheckout [checkoutId=" + checkoutId + ", patron_id=" + patron_id + ", isbn=" + isbn
				+ ", checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + ", returnedDate=" + returnedDate + "]";
	}
	
	

}
