package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import entity.Lecture;
import global.Constants;
import view.RegistrationPanel.ActionHandlerRegis;

public class BasketView extends JPanel  {
	private static final long serialVersionUID = 1L;

	private JButton btMoveToRight, btMoveToLeft, btSave;
	private JButton btRemoveBasket, btRemoveSincheong;
	
	private JLabel lbBasket, lbSincheong;
	
	private Vector<Lecture> baskets, sincheongs, selectedBaskets, selectedSincheongs;
	
	private LectureList basketList;
	private LectureList sincheongList;
	
	public BasketView(ActionHandlerRegis actionHandlerRegis) {
		// Listener
		ActionHandler actionHandler = new ActionHandler();
		MouseHandler mouseHandler = new MouseHandler();
		TableModelHandler tableModelHandler = new TableModelHandler();
		FocusHandler focusHandler = new FocusHandler();
		KeyHandler keyHandler = new KeyHandler();
		
		// attributes
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // BoxLayout(parent, 정렬방식)
		this.add(Box.createHorizontalStrut(Constants.BASKETVIEW_BOX_W));
			
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_LEFTPANEL_BOX_H));
			
			this.lbBasket = new JLabel(Constants.LABEL_BASKET);
			this.lbBasket.setAlignmentX(Component.CENTER_ALIGNMENT);
			leftPanel.add(this.lbBasket);
			leftPanel.add(Box.createVerticalStrut(Constants.BAKSETVIEW_LBBASKET_BOX_H));
		
			this.basketList = new LectureList(null);
			this.basketList.setBackground(Color.LIGHT_GRAY);
			this.basketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.basketList.getTableModel().addTableModelListener(tableModelHandler);
			this.basketList.addMouseListener(mouseHandler);
			this.basketList.addFocusListener(focusHandler);
			this.basketList.addKeyListener(keyHandler);
			JScrollPane basketListScrollPane = new JScrollPane();
			basketListScrollPane.setViewportView(this.basketList);
			leftPanel.add(basketListScrollPane);
			leftPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_BASKETLIST_BOX_H));
			
			this.btRemoveBasket = new JButton(Constants.BTTITLE_REMOVEBASKET);
			this.btRemoveBasket.setEnabled(false);
			this.btRemoveBasket.addActionListener(actionHandler);
			this.btRemoveBasket.setAlignmentX(Component.CENTER_ALIGNMENT);
			leftPanel.add(btRemoveBasket);
			leftPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_BTREMOVEBASKET_BOX_H));
			
			this.add(leftPanel);	
			this.add(Box.createHorizontalStrut(Constants.BASKETVIEW_BOX_W));

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_CENTERPANEL_BOX_H));
		
			this.btMoveToRight = new JButton(Constants.BTTITLE_MOVETORIGHT);
			this.btMoveToRight.setEnabled(false);
			this.btMoveToRight.addActionListener(actionHandler);
			this.btMoveToRight.setAlignmentX(Component.CENTER_ALIGNMENT);
			centerPanel.add(this.btMoveToRight);
			centerPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_BTMOVETORIGHT_BOX_H));
		
			this.btMoveToLeft = new JButton(Constants.BTTITLE_MOVETOLEFT);
			this.btMoveToLeft.setEnabled(false);
			this.btMoveToLeft.addActionListener(actionHandler);
			this.btMoveToLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
			centerPanel.add(this.btMoveToLeft);
			centerPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_BTMOVETOLEFT_BOX_H));
			
			this.btSave = new JButton(Constants.BTITLE_SAVE);
			this.btSave.addActionListener(actionHandler);
			this.btSave.setAlignmentX(Component.CENTER_ALIGNMENT);
			centerPanel.add(this.btSave);
			centerPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_BTSAVE_BOX_H));
			
			this.add(centerPanel);
			this.add(Box.createHorizontalStrut(Constants.BASKETVIEW_BOX_W));

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_RIGHTPANEL_BOX_H));
		
			this.lbSincheong = new JLabel(Constants.LABEL_SINCHEONG);
			this.lbSincheong.setAlignmentX(Component.CENTER_ALIGNMENT);
			rightPanel.add(this.lbSincheong);
			rightPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_LBSINCHEONG_BOX_H));
			
			this.sincheongList = new LectureList(null);
			this.sincheongList.setBackground(Color.PINK);
			this.sincheongList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.sincheongList.getTableModel().addTableModelListener(tableModelHandler);
			this.sincheongList.addMouseListener(mouseHandler);
			this.sincheongList.addFocusListener(focusHandler);
			this.sincheongList.addKeyListener(keyHandler);
			JScrollPane sincheongScrollPane = new JScrollPane();
			sincheongScrollPane.setViewportView(this.sincheongList);
			rightPanel.add(sincheongScrollPane);
			rightPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_SINCHEONGLIST_BOX_H));
			
			this.btRemoveSincheong = new JButton(Constants.BTTITLE_REMOVESINCHEONG);
			this.btRemoveSincheong.setEnabled(false);
			this.btRemoveSincheong.addActionListener(actionHandler);
			this.btRemoveSincheong.setAlignmentX(Component.CENTER_ALIGNMENT);
			rightPanel.add(btRemoveSincheong);
			rightPanel.add(Box.createVerticalStrut(Constants.BASKETVIEW_BTREMOVESINCHEONG_BOX_H));
		
			this.add(rightPanel);
			this.add(Box.createHorizontalStrut(Constants.BASKETVIEW_BOX_W));
		
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////

			this.baskets = new Vector<Lecture>();
			this.sincheongs = new Vector<Lecture>();
			
	}
	
	public LectureList getBasketList() { return this.basketList; }
	public LectureList getSincheongList() { return this.sincheongList; }
	
	public JButton getBtSave() { return this.btSave;}
	
	public void initialize() {
//		try {
//			this.basketList.showBaskets(Constants.FILENAME_SAVE_BASKET);
//			this.vectorBasket(this.basketList.vectorBasket());
//			this.sincheongList.showSincheongs(Constants.FILENAME_SAVE_SINCHEONG);
//			this.vectorSincheong(this.sincheongList.vectorSincheong());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public Vector<Lecture> setBaskets(Vector<Lecture> lectures) {
		for (Lecture lecture : lectures) {
			boolean k = false;
			for (Lecture basket : this.baskets) {
				if (lecture.getId() == basket.getId()) {
					k = true;
					JOptionPane.showMessageDialog(this, Constants.BASKETVIEW_JOPTIONPANE_BASKET, null, JOptionPane.ERROR_MESSAGE);
				}
			}
			if (!k) {
				this.baskets.addElement(lecture);
			}
		}
		return this.baskets;
	}
	
	public Vector<Lecture> vectorBasket(Vector<Lecture> basket) {
		this.baskets = basket;
		return this.baskets;
	}
	
	public Vector<Lecture> setSincheongs(Vector<Lecture> baskets){
		for (Lecture basket : baskets) {
			boolean k = false;
			for (Lecture sincheong : this.sincheongs) {
				if (basket.getId() == sincheong.getId()) {
					k = true;
					JOptionPane.showMessageDialog(null, Constants.BASKETVIEW_JOPTIONPANE_SINCHEONG_ERROR, null, JOptionPane.ERROR_MESSAGE);
				}
			}if (!k) {
				this.sincheongs.addElement(basket);
				JOptionPane.showMessageDialog(this, Constants.BASKETVIEW_JOPTIONPANE_SINCHEONG_CONFIRM, null, JOptionPane.INFORMATION_MESSAGE);
			}
		}
		return this.sincheongs;
	}
	
	public Vector<Lecture> vectorSincheong(Vector<Lecture> sincheong) {
		this.sincheongs = sincheong;
		return this.sincheongs;
	}
	
	public Vector<Lecture> getSelectedBasket() {
		this.selectedBaskets = new Vector<Lecture>();
		for (int i = 0; i < this.basketList.getRowCount(); i++) {
			if (this.basketList.isRowSelected(i)) {
				this.selectedBaskets.addElement(this.baskets.get(i));
			}
		}
		return this.selectedBaskets;
	}
	
	public Vector<Lecture> getSelectedSincheong() {
		this.selectedSincheongs = new Vector<Lecture>();
		for (int i = 0; i < this.sincheongList.getRowCount(); i++) {
			if (this.sincheongList.isRowSelected(i)) {
				this.selectedSincheongs.addElement(this.sincheongs.get(i));
			}
		}
		return this.selectedSincheongs;
	}

	public void addBasket() {
		this.basketList.showLectures(this.baskets);
	}
	
	public void returnBasket() {
		Vector<Lecture> selectedSincheong = this.getSelectedSincheong();
		this.setBaskets(selectedSincheong);
		this.addBasket();
	}
	
	public void showSincheong() {
		Vector<Lecture> selectedBaskets = this.getSelectedBasket();
		this.setSincheongs(selectedBaskets);
		this.sincheongList.showLectures(this.sincheongs);
	}
	
	
	public void saveRegistration(String basketFileName, String sicheongFileName) throws FileNotFoundException {
		this.basketList.saveLectures(basketFileName, this.baskets);
		this.sincheongList.saveLectures(sicheongFileName, this.sincheongs);

	}	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listener
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btMoveToRight)) {
				showSincheong();
				basketList.removeLecture(baskets);
			}else if (e.getSource().equals(btMoveToLeft)) {
				returnBasket();
				sincheongList.removeLecture(sincheongs);
			}else if (e.getSource().equals(btRemoveBasket)) {
				basketList.removeLecture(baskets);
			}else if (e.getSource().equals(btRemoveSincheong)) {
				sincheongList.removeLecture(sincheongs);
			}
		}
	}

	private class MouseHandler implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == basketList) {
				if (e.getClickCount() == 2) {
					showSincheong();
					basketList.removeLecture(baskets);
				}
			}else if (e.getSource() == sincheongList) {
				if (e.getClickCount() == 2) {
					returnBasket();
					sincheongList.removeLecture(sincheongs);
				}
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
	
	private class FocusHandler implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() == basketList) {
				btMoveToRight.setEnabled(true);
				btRemoveBasket.setEnabled(true);
			}else if (e.getSource() == sincheongList) {
				btMoveToLeft.setEnabled(true);
				btRemoveSincheong.setEnabled(true);
			}
			
		}
		@Override
		public void focusLost(FocusEvent e) {
		}
		
	}
	
	private class TableModelHandler implements TableModelListener{
		@Override
		public void tableChanged(TableModelEvent e) {
			if (e.getSource() == basketList.getTableModel()) {
				if (e.getType() == TableModelEvent.ALL_COLUMNS) {
					btMoveToRight.setEnabled(false);
					btRemoveBasket.setEnabled(false);
				}
			}else if (e.getSource() == sincheongList.getTableModel()) {
				 if (e.getType() == TableModelEvent.ALL_COLUMNS) {
					btMoveToLeft.setEnabled(false);
					btRemoveSincheong.setEnabled(false);
				}
			}
		}
	}
	
	private class KeyHandler implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getSource() == basketList) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					basketList.removeLecture(baskets);
				}
			}else if (e.getSource() == sincheongList) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					sincheongList.removeLecture(sincheongs);
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}
}
