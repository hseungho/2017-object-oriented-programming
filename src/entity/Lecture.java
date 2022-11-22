package entity;

import java.io.PrintWriter;
import java.util.Scanner;

public class Lecture {
	
	private int id;
	private String name;
	private String professorName;
	private String credit;
	private String time;
	
	public Lecture() {
		
	}

	public int getId() { return id;}
	public void setId(int id) { this.id = id;}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getProfessorName() { return professorName; }
	public void setProfessorName(String professorName) { this.professorName = professorName; }

	public String getCredit() { return credit; }
	public void setCredit(String credit) { this.credit = credit; }

	public String getTime() { return time; }
	public void setTime(String time) { this.time = time; }

	public void readFromFile(Scanner scanner) {
		this.id = scanner.nextInt();
		this.name = scanner.next();
		this.professorName = scanner.next();
		this.credit = scanner.next();
		this.time = scanner.next();
		
	}

	public void writeToFile(PrintWriter printWriter) {
		printWriter.write(this.id+" ");
		printWriter.write(this.name+" ");
		printWriter.write(this.professorName+" ");
		printWriter.write(this.credit+" ");
		printWriter.write(this.time);
		
		printWriter.println();
	}
}
