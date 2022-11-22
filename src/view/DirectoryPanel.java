 package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import global.Constants;
import view.RegistrationPanel.ActionHandlerRegis;
import view.RegistrationPanel.LectureHandler;

public class DirectoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private DirectoryList campusList;
	private DirectoryList collegeList;
	private DirectoryList departmentList;
	private LectureList lectureList;
	
	private JButton btSincheong;
	
	private ListSelectionHandler listSelectionHandler;

	public DirectoryPanel(LectureHandler lectureHandler, ActionHandlerRegis actionHandlerRegis) {
		// Listener
		this.listSelectionHandler = new ListSelectionHandler();
		FocusHandler_1 focusHandler_1 = new FocusHandler_1();
		
		//layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel upperPanel = new JPanel();
		this.add(upperPanel);
		JPanel controlPanel = new JPanel();
		this.add(controlPanel);
		JPanel lowerPanel = new JPanel();
		this.add(lowerPanel);

		// attributes
		lowerPanel.setLayout(new BorderLayout());
		upperPanel.setLayout(new GridLayout(Constants.DIRECTORYPANEL_UPPERPANEL_GRID_ROW, Constants.DIRECTORYPANEL_UPPERPANEL_GRID_COLUMN));
		controlPanel.setLayout(new BorderLayout());

		JScrollPane scrollPane = null;
		
		this.campusList = new DirectoryList(this.listSelectionHandler);
		this.campusList.addFocusListener(focusHandler_1);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.campusList);
		upperPanel.add(scrollPane);
		
		this.collegeList = new DirectoryList(this.listSelectionHandler);
		this.collegeList.addFocusListener(focusHandler_1);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.collegeList);
		upperPanel.add(scrollPane);
		
		this.departmentList = new DirectoryList(this.listSelectionHandler);
		this.departmentList.addFocusListener(focusHandler_1);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.departmentList);
		upperPanel.add(scrollPane);
		
		// lower Panel
		
		this.lectureList = new LectureList(this.listSelectionHandler);
		this.lectureList.addMouseListener(lectureHandler);
		this.lectureList.addFocusListener(focusHandler_1);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.lectureList);
		lowerPanel.add(scrollPane, BorderLayout.CENTER);
		
		// control Panel
		this.btSincheong = new JButton(Constants.BTITLE_SINCHEONG);
		this.btSincheong.setEnabled(false);
		this.btSincheong.addFocusListener(focusHandler_1);
		this.btSincheong.addActionListener(actionHandlerRegis);
		controlPanel.add(this.btSincheong, BorderLayout.EAST);
		
	}
	
	public JButton getBtSincheong() { return btSincheong; }
	
	public LectureList getLectureList() { return this.lectureList; }

	public void initialize() {
		this.campusList.initialize();
	}
	
	public void showDirectories(Object source) {
		try {
			String fileName = null;
			if (source.equals(this.campusList)) {
				fileName = this.campusList.getSelectedFileName();
				this.collegeList.showDirectories(fileName);
				fileName = this.collegeList.getSelectedFileName();
				this.departmentList.showDirectories(fileName);
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			}else if (source.equals(this.collegeList)) {
				fileName = this.collegeList.getSelectedFileName();
				this.departmentList.showDirectories(fileName);
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			}else if (source.equals(this.departmentList)) {
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public class ListSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			showDirectories(event.getSource());
		}
	}

	public class FocusHandler_1 implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() == campusList) {
				btSincheong.setEnabled(false);
			}else if (e.getSource() == collegeList) {
				btSincheong.setEnabled(false);
			}else if (e.getSource() == departmentList) {
				btSincheong.setEnabled(false);
			}else if (e.getSource() == lectureList) {
				btSincheong.setEnabled(true);
			}
		}
		@Override
		public void focusLost(FocusEvent e) {
		}
	}
}
