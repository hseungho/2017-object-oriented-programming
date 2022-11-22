package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entity.Student;
import global.Constants;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

	private RegistrationPanel registrationPanel;
	private ControlPanel controlPanel;
	private LoginView loginView;
	
	private Vector<Student> studentList;
	
	public MainView() {
		// Listener
		ActionHandler actionHandler = new ActionHandler();
		
		// attribute
		this.setLocation(Constants.MAINVIEW_X, Constants.MAINVIEW_Y);
		this.setSize(Constants.MAINVIEW_W, Constants.MAINVIEW_H);
		this.setTitle(Constants.MAINVIEW_TITLE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// components
		
		this.controlPanel = new ControlPanel(actionHandler);
		this.add(this.controlPanel, BorderLayout.NORTH);
		
		this.registrationPanel = new RegistrationPanel();
		this.add(this.registrationPanel);
		
		try {
			loginView = new LoginView(this);
			loginView.setVisible(true);
			addLabel();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addLabel() {
		this.studentList = this.loginView.getStudentList();
		try {
			for (Student student : this.studentList) {
				if (loginView.getUserID().equals(student.getUserID())) {
					this.controlPanel.getLbUser().setText(Constants.LABEL_UNIVERSITY + " " + student.getDepartment() + " " + student.getUserName());
				}
			}	
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, Constants.MAINVIEW_JOPTIONPANE_EXITPROGRAM, null, JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
	}
	
	public void initialize() {
		this.controlPanel.initialize();
		this.registrationPanel.initialize();
		try {
			this.studentList = this.loginView.getStudentList();
			for (Student student : this.studentList) {
				if (this.loginView.getUserID().equals(student.getUserID())) {
					this.registrationPanel.getBasketView().getBasketList().showBaskets(Constants.FILENAME_SAVE_BASKET + student.getUserID());
					this.registrationPanel.getBasketView().vectorBasket(this.registrationPanel.getBasketView().getBasketList().vectorBasket());
					this.registrationPanel.getBasketView().getSincheongList().showSincheongs(Constants.FILENAME_SAVE_SINCHEONG + student.getUserID());
					this.registrationPanel.getBasketView().vectorSincheong(this.registrationPanel.getBasketView().getSincheongList().vectorSincheong());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exit() {
		String[] exit = {Constants.MAINVIEW_JOPTIONPANE_EXIT, Constants.MAINVIEW_JOPTIONPANE_SAVE, Constants.MAINVIEW_JOPTIONPANE_CANCEL};
		int i = JOptionPane.showOptionDialog(registrationPanel, Constants.MAINVIEW_JOPTIONPANE_QUESTION_EXIT, null, 0, JOptionPane.YES_NO_CANCEL_OPTION, null, exit, exit[0]);
		if (i == 0) {
			System.exit(0);
		}else if (i == 1) {
			this.saveRegistration();
			System.exit(0);
		}
	}
	
	public void saveRegistration() {
		try {
			this.studentList = this.loginView.getStudentList();
			for (Student student : this.studentList) {
				if (loginView.getUserID().equals(student.getUserID())) {
				this.registrationPanel.getBasketView().saveRegistration(Constants.FILENAME_SAVE_BASKET + student.getUserID(), Constants.FILENAME_SAVE_SINCHEONG + student.getUserID());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listener
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == controlPanel.getBtLogout()) {
				exit();
			}
		}
		
	}
}
