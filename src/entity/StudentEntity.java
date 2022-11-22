package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import global.Constants;

public class StudentEntity {
	
	private Vector<Student> studentList;
	
	public StudentEntity() throws FileNotFoundException {
		this.studentList = new Vector<Student>();
		this.readFromFile();
	}
	private void readFromFile() throws FileNotFoundException {
		File file = new File(Constants.FILENAME_STUDENT);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			Student student = new Student();
			student.readFromFile(scanner);
			this.studentList.addElement(student);
		}
		scanner.close();
		
	}
	
	public Vector<Student> getStudentList() { 
		return this.studentList;
	}
	
	public Student login(String userID, String password) {
		for(Student student : this.studentList) {
			if (student.getUserID().equals(userID) && student.getPassword().equals(password)) {
				return student;				
			}
		}
		return null;
	}
}
