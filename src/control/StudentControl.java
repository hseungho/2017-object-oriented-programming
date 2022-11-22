package control;

import java.io.FileNotFoundException;
import java.util.Vector;

import entity.Student;
import entity.StudentEntity;

public class StudentControl {

	private StudentEntity studentEntity;
	
	public StudentControl() throws FileNotFoundException {
		this.studentEntity = new StudentEntity();
	}
	public Student login(String userID, String password) {
		Student student = this.studentEntity.login(userID, password);
		return student;
	}
	public Vector<Student> getStudentList() {
		return this.studentEntity.getStudentList();
	}
}
