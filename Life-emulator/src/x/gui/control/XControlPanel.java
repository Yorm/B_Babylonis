package x.gui.control;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class XControlPanel extends JPanel {
	
	private JButton startButton;
	private JButton pauseButton;
	private JButton stopButton;
	
	public XControlPanel() {
		setupView();
		setupButtons();
		setVisible(true);
	}
	
	private void setupView() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	private void setupButtons() {
		// START
		this.startButton = new JButton();
		startButton.setIcon(new ImageIcon("src\\resources\\gui\\icons\\control-start.png"));
		startButton.setPreferredSize(new Dimension(25, 25));
		add(startButton);
		// PAUSE
		this.pauseButton = new JButton();
		pauseButton.setIcon(new ImageIcon("src\\resources\\gui\\icons\\control-pause.png"));
		pauseButton.setPreferredSize(new Dimension(25, 25));
		pauseButton.setEnabled(false);
		add(pauseButton);
		// STOP
		this.stopButton = new JButton();
		stopButton.setIcon(new ImageIcon("src\\resources\\gui\\icons\\control-stop.png"));
		stopButton.setPreferredSize(new Dimension(25, 25));
		add(stopButton);	
	}

}
