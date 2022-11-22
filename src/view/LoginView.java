package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.StudentControl;
import entity.Student;
import global.Constants;

public class LoginView extends JDialog  {
	private static final long serialVersionUID = 1L;
	// controller
	private StudentControl studentControl;
	// sub-components
	private JLabel lbUserID, lbPassword;
	private JTextField tfUserID; 
	private JPasswordField tfPassword;
	private JButton btOk, btCancel;
	
	private String userID;
	
	public LoginView(JFrame frame) throws FileNotFoundException {
		super(frame, Constants.LOGINVIEW_TITLE, true);
		// Listener
		ActionHandler actionHandler = new ActionHandler();
		KeyHandler keyHandler = new KeyHandler();
		
		// attributes
		this.setLocationRelativeTo(frame);
		this.setSize(Constants.LOGINVIEW_W, Constants.LOGINVIEW_H);
		this.setLayout(new BorderLayout());;
		this.setResizable(false);
		// add sub-components
		JPanel northPanel = new JPanel();
		this.add(northPanel, BorderLayout.CENTER);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(Box.createHorizontalStrut(Constants.LOGINVIEW_LEFTPANEL_BOX_W));
		northPanel.add(leftPanel);
		
		this.lbUserID = new JLabel(Constants.LABEL_ID);
		this.lbUserID.setAlignmentY(Component.CENTER_ALIGNMENT);
		leftPanel.add(this.lbUserID);
		leftPanel.add(Box.createVerticalStrut(Constants.LOGINVIEW_LBUSERID_BOX_H));
		
		this.lbPassword = new JLabel(Constants.LABEL_PASSWORD);
		this.lbPassword.setAlignmentY(Component.CENTER_ALIGNMENT);
		leftPanel.add(this.lbPassword);
		leftPanel.add(Box.createVerticalStrut(Constants.LOGINVIEW_LBPASSWORD_BOX_H));
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(Box.createHorizontalStrut(Constants.LOGINVIEW_RIGHTPANEL_BOX_W));
		northPanel.add(rightPanel);
		
		this.tfUserID = new JTextField(Constants.TFUSERID);
		this.tfUserID.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.tfUserID.addKeyListener(keyHandler);
		rightPanel.add(this.tfUserID);	
		rightPanel.add(Box.createVerticalStrut(Constants.LOGINVIEW_TFUSERID_BOX_H));

		this.tfPassword = new JPasswordField(Constants.TFPASSWORD);
		this.tfPassword.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.tfPassword.addKeyListener(keyHandler);
		rightPanel.add(this.tfPassword);
		rightPanel.add(Box.createVerticalStrut(Constants.LOGINVIEW_TFPASSWORD_BOX_H));

		JPanel southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);
		
		this.btOk = new JButton(Constants.BTTITLE_OK);
		this.btOk.setActionCommand(Constants.BTACTIONCOMMAND_OK);
		this.btOk.addActionListener(actionHandler);
		this.btOk.addKeyListener(keyHandler);
		southPanel.add(this.btOk);
		
		this.btCancel = new JButton(Constants.BTTITLE_CANCEL);	
		this.btCancel.setActionCommand(Constants.BTACTIONCOMMAND_CANCEL);
		this.btCancel.addActionListener(actionHandler);
		southPanel.add(this.btCancel);

		this.studentControl = new StudentControl();
	}
	
	public String getUserID() { return this.userID; }
	
	private void login() {
		this.userID = this.tfUserID.getText();
		String password = new String(this.tfPassword.getPassword());
		Student student = this.studentControl.login(this.userID, password);
		if (student == null) {
			JOptionPane.showMessageDialog(this, Constants.LOGINVIEW_JOPTIONPANE);
		} else {
			this.dispose();
			
		}
	}
	private void cancel() {
		this.dispose();
	}
	
	public Vector<Student> getStudentList(){
		return this.studentControl.getStudentList();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listener
	
	class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			if (actionEvent.getActionCommand().equals(Constants.BTACTIONCOMMAND_OK)) {
				login();
			} else if (actionEvent.getActionCommand().equals(Constants.BTACTIONCOMMAND_CANCEL)) {
				cancel();
			}
		}		
	}
	
	class KeyHandler implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				login();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
}
