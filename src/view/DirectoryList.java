package view;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import control.DirectoryManager;
import entity.Directory;
import global.Constants;
import view.DirectoryPanel.ListSelectionHandler;

public class DirectoryList extends JList<String> {
	private static final long serialVersionUID = 1L;
	// controller
	private DirectoryManager directoryManager;
	
	private Vector<Directory> directories;
	// view
	private Vector<String> listData; 
	
	
	public DirectoryList(ListSelectionHandler listSelectionHandler) {
		// controller
		this.directoryManager = new DirectoryManager();
		// subject
		this.listData = new Vector<String>(); 
		this.setListData(this.listData); 
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.addListSelectionListener(listSelectionHandler);
		
		this.directories = new Vector<Directory>();
	}
	
	public String getSelectedFileName() {
		int index = this.getSelectedIndex();
		Directory directory = this.directories.get(index);
		return directory.getFileName();
	}
	
	public void initialize() {
		try {
			showDirectories(Constants.FILENAME_ROOT);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showDirectories(String fileName) throws FileNotFoundException {
		this.listData.clear();
		if (fileName == null) {
			return;
		}
		this.directories = this.directoryManager.getDirectories(fileName);	
			for (Directory directory: directories) {
				this.listData.add(directory.getName());
			}
		this.setSelectedIndex(0);
		this.updateUI(); 	
	}


}
