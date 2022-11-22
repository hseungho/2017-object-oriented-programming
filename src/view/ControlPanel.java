package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import global.Constants;
import view.MainView.ActionHandler;

public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton btLogout;
	private JLabel lbUser;
	
	public ControlPanel(ActionHandler actionHandler) {
		
		this.setLayout(new BorderLayout());
		
		JPanel westPanel = new JPanel();
		this.add(westPanel, BorderLayout.WEST);
		
		JLabel lb = new JLabel();
		lb.setIcon(new ImageIcon("image/myom.png"));
		westPanel.add(lb);
		
		JPanel eastPanel = new JPanel();
		this.add(eastPanel, BorderLayout.EAST);
		
		this.lbUser = new JLabel();
		this.lbUser.setHorizontalAlignment(SwingConstants.CENTER);
		eastPanel.add(this.lbUser);
		
		this.btLogout = new JButton(Constants.BTITLE_LOGOUT);
		this.btLogout.addActionListener(actionHandler);
		eastPanel.add(this.btLogout, BorderLayout.EAST);
		
	}
	public void initialize() {
	}
	
	public JButton getBtLogout() { return this.btLogout; }
	public JLabel getLbUser() { return this.lbUser; }
	
}
