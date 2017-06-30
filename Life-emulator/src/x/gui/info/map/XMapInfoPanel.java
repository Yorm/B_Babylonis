package x.gui.info.map;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class XMapInfoPanel extends JPanel {
	
	private JLabel info;
	
	public XMapInfoPanel() {
		setupView();
		setupInfo();
		setVisible(true);
	}
	
	public final void update(int days) {	
		// TODO
	}
	
	public void reset() {
		this.info.setText("-");
	}
	
	private void setupView() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(new TitledBorder("Map Info"));
		setPreferredSize(new Dimension(200, 0));
	}
	
	private void setupInfo() {
		this.info = new JLabel("-", SwingConstants.LEFT);
		add(this.info);
	}

}
