package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import entity.Lecture;
import global.Constants;

public class RegistrationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private DirectoryPanel directoryPanel;
	private BasketView basketView;

	private JPanel centerPanel;
	private JButton btLectureSelect, btBasket;
	
	public RegistrationPanel() {
		
		this.setLayout(new BorderLayout());
		
		ActionHandlerRegis actionHandler = new ActionHandlerRegis();
		LectureHandler lectureHandler = new LectureHandler();
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		
			this.btLectureSelect = new JButton(Constants.BTTITLE_LECTURESELECT);
			this.btLectureSelect.addActionListener(actionHandler);
			northPanel.add(this.btLectureSelect, Box.createHorizontalStrut(10));
			
			this.btBasket = new JButton(Constants.BTTITLE_BASKET);
			this.btBasket.addActionListener(actionHandler);
			northPanel.add(this.btBasket, Box.createHorizontalStrut(10));
			
		this.add(northPanel, BorderLayout.NORTH);
			
		this.centerPanel = new JPanel(new BorderLayout());
			
			this.basketView = new BasketView(actionHandler);
			centerPanel.add(this.basketView);
		
			this.directoryPanel = new DirectoryPanel(lectureHandler, actionHandler);
			centerPanel.add(this.directoryPanel);
			
		
		this.add(this.centerPanel, BorderLayout.CENTER);
	}
	
	public BasketView getBasketView() {
		return this.basketView;
	}
	
	public void initialize() {
		this.directoryPanel.initialize();
		this.basketView.initialize();
	}
	
	public void changePanel(String panelName) {
		if (panelName.equals(Constants.PANELNAME_DIRECTORYPANEL)) {
			this.centerPanel.removeAll();
			this.centerPanel.add(this.directoryPanel);
			this.revalidate();
			this.repaint();
		}else if (panelName.equals(Constants.PANELNAME_BASKETVIEW)) {
			this.centerPanel.removeAll();
			this.centerPanel.add(this.basketView);
			this.revalidate();
			this.repaint();
		}
	}
	
	public void showRegistration() {
		Vector<Lecture> selectedLectures = this.directoryPanel.getLectureList().getSelectedLectures();
		basketView.setBaskets(selectedLectures);
		basketView.addBasket();

	}

//	public void saveRegistration() {
//		try {
//			basketView.saveRegistration(Constants.FILENAME_SAVE_BASKET, Constants.FILENAME_SAVE_SINCHEONG);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	class ActionHandlerRegis implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btLectureSelect)) {
				changePanel(Constants.PANELNAME_DIRECTORYPANEL);
			}else if (e.getSource().equals(btBasket)) {
				changePanel(Constants.PANELNAME_BASKETVIEW);
			}else if (e.getSource().equals(directoryPanel.getBtSincheong())) {
				showRegistration();
			}else if (e.getSource().equals(basketView.getBtSave())) {
//				saveRegistration();
//				JOptionPane.showMessageDialog(directoryPanel, Constants.REGISTRATIONPANEL_JOPTIONPANE);
			}
		}
	}

	public class LectureHandler implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				showRegistration();
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
}