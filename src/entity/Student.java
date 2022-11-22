package entity;

import java.util.Scanner;

public class Student {
	// attributes
	private int id;
	private String userID;
	private String userName;
	private String password;
	private String department;
	
	// constructors & destructor
	public Student(){
	}
	// read & write
	void readFromFile(Scanner scanner) {
		this.setId(scanner.nextInt());
		this.setUserID(scanner.next());
		this.setUserName(scanner.next());
		this.setPassword(scanner.next());
		this.setDepartment(scanner.next());
	}
	void writeToFile() {}
	
	// getters & setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}//end Student