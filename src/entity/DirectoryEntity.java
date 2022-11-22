package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import global.Constants;

public class DirectoryEntity {	
	private Vector<Directory> directories;	
	public DirectoryEntity() {
		this.directories = new Vector<Directory>();
	}
	
	public Vector<Directory> readFromFile(String fileName) throws FileNotFoundException {
		File file = new File(Constants.FILENAME_DIRECTORY+fileName);
		Scanner scanner = new Scanner(file);
		this.directories.clear();
		while (scanner.hasNext()) {
			Directory directory = new Directory();
			directory.readFromFile(scanner);
			directories.add(directory);
		}
		return directories;		
	}

}
