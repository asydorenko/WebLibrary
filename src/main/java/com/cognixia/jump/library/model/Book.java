package com.cognixia.jump.library.model;

import java.time.LocalDate;

public class Book {
	
	private String isbn;
	private String title;
	private String description;
	private int rented;
	private LocalDate date;
	
	public Book(String isbn, String title, String description, int rented, LocalDate date) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.rented = rented;
		this.date = date;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int isRented() {
		return rented;
	}

	public void setRented(int rented) {
		this.rented = rented;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", rented=" + rented
				+ ", date=" + date + "]";
	}
	
	
	
	

}
