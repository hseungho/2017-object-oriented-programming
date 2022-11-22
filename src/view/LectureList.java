package view;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import control.LectureManager;
import entity.Lecture;
import global.Constants;
import view.DirectoryPanel.ListSelectionHandler;

public class LectureList extends JTable {
	private static final long serialVersionUID = 1L;
	// controller
	private LectureManager lectureManager;
	private Vector<Lecture> lectures, baskets, sincheongs;
	// data model
	private CannotEditModel tableModel; // real Data, not View.
	// selected row index
	private Vector<Lecture> selectedLectures;
	
	
	public LectureList(ListSelectionHandler listSelectionHandler) {
		// controller
		this.lectureManager = new LectureManager();
		// data model
		Vector<String> header = new Vector<String>();
		header.addElement(Constants.TABLE_HEADER_LECTURENAME);
		header.addElement(Constants.TABLE_HEADER_PROFESSORNAME);
		header.addElement(Constants.TABLE_HEADER_LECTURECREDIT);
		header.addElement(Constants.TABLE_HEADER_LECTURETIME);
		
		this.tableModel = new CannotEditModel(header, 0);
		this.setModel(this.tableModel);
		
		DefaultTableCellRenderer tablecenter = new DefaultTableCellRenderer();
		tablecenter.setHorizontalAlignment(JLabel.CENTER);
		
		this.getColumn(Constants.TABLE_HEADER_LECTURENAME).setPreferredWidth(Constants.LECTURELIST_HEADER_LECTURENAME_SIZE);
		this.getColumn(Constants.TABLE_HEADER_LECTURENAME).setCellRenderer(tablecenter);
		this.getColumn(Constants.TABLE_HEADER_PROFESSORNAME).setPreferredWidth(Constants.LECTURELIST_HEADER_PROFESSORNAME_SIZE);
		this.getColumn(Constants.TABLE_HEADER_PROFESSORNAME).setCellRenderer(tablecenter);
		this.getColumn(Constants.TABLE_HEADER_LECTURECREDIT).setPreferredWidth(Constants.LECTURELIST_HEADER_LECTURECREDIT_SIZE);
		this.getColumn(Constants.TABLE_HEADER_LECTURECREDIT).setCellRenderer(tablecenter);
		this.getColumn(Constants.TABLE_HEADER_LECTURETIME).setPreferredWidth(Constants.LECTURELIST_HEADER_LECTURETIME_SIZE);
		this.getColumn(Constants.TABLE_HEADER_LECTURETIME).setCellRenderer(tablecenter);
		
		this.baskets = new Vector<Lecture>();
		this.sincheongs = new Vector<Lecture>();
		

	}
	
	public CannotEditModel getTableModel() { return this.tableModel; }
	
	public void initialize() {
	}
	
	public Vector<Lecture> getSelectedLectures() {
		this.selectedLectures = new Vector<Lecture>();
		for (int i = 0; i < this.getRowCount(); i++) {
			if (this.isRowSelected(i)) {
				this.selectedLectures.addElement(lectures.get(i));
			}
		}
		return this.selectedLectures;
	}
	
	public void showLectures(Vector<Lecture> lectures) {
		this.lectures = new Vector<Lecture>();
		this.tableModel.setRowCount(0);
		Vector<String> rowData = null;
		for (Lecture lecture : lectures) {
			rowData = new Vector<String>();
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			rowData.addElement(lecture.getCredit());
			rowData.addElement(lecture.getTime());
			
			this.tableModel.addRow(rowData);
			this.lectures.addElement(lecture);
		}
		this.updateUI();
	}

	public void showLectures(String fileName) throws FileNotFoundException {
		this.tableModel.setRowCount(0);
		this.lectures = this.lectureManager.getLectures(fileName);
		Vector<String> rowData = null;
		for (Lecture lecture : lectures) {
			rowData = new Vector<String>();
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			rowData.addElement(lecture.getCredit());
			rowData.addElement(lecture.getTime());
			
			this.tableModel.addRow(rowData);
		}
		this.updateUI();
	}
	
	public void showBaskets(String fileName) throws FileNotFoundException {
		this.baskets = this.lectureManager.getLectures(fileName);
		Vector<String> rowData = null;
		for (Lecture lecture : baskets) {
			rowData = new Vector<String>();
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			rowData.addElement(lecture.getCredit());
			rowData.addElement(lecture.getTime());
			
			this.tableModel.addRow(rowData);
		}
		this.updateUI();
	}
	
	public Vector<Lecture> vectorBasket(){
		return this.baskets;
	}
	
	public void showSincheongs(String fileName) throws FileNotFoundException {
		this.sincheongs = this.lectureManager.getLectures(fileName);
		Vector<String> rowData = null;
		for (Lecture lecture : sincheongs) {
			rowData = new Vector<String>();
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			rowData.addElement(lecture.getCredit());
			rowData.addElement(lecture.getTime());
			
			this.tableModel.addRow(rowData);
		}
		this.updateUI();
	}
	
	public Vector<Lecture> vectorSincheong(){
		return this.sincheongs;
	}
	
	public void removeLecture(Vector<Lecture> removeLecture) {
		try {
			int i = this.getSelectedRow();
			this.tableModel.removeRow(i);
			removeLecture.removeElementAt(i);
		}catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, Constants.LECTURELIST_JOPTIONPANE, null, JOptionPane.ERROR_MESSAGE);
		}
	}

	public void saveLectures(String fileName, Vector<Lecture> lectures) throws FileNotFoundException {
		this.lectureManager.saveLectures(fileName, lectures);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public class CannotEditModel extends DefaultTableModel {
		private static final long serialVersionUID = 1L;
		public CannotEditModel(Vector<String> header, int row) {
			this.setColumnIdentifiers(header);
		}
		public boolean isCellEditable(int column, int row) {
			return false;
		}
	}
}
