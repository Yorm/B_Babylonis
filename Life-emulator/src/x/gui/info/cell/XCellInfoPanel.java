package x.gui.info.cell;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import x.data.ucf.XUcfCoder;
import x.gui.format.XFormatter;
import x.gui.main.XMainPanel;

@SuppressWarnings("serial")
public class XCellInfoPanel extends JPanel {
	
	private JLabel info;
	
	public XCellInfoPanel() {
		setupView();
		setupInfo();
		setVisible(true);
	}
	
	public void update(int y, int x) {
		long cellData = XMainPanel.mapPanel.getRawDataAt(y, x);		
		String info = String.format("<html>Raw: %s"
				+ "<br>"
				+ "<br> Landscape type: %s"
				+ "<br>"
				+ "<br> Human type: %s"
				+ "<br> Human age: %s"
				+ "<br> Human energy: %s"
				+ "<br> Human satiety: %s"
				+ "<br> Human pregnancy: %s"
				+ "<br>"
				+ "<br> Plant type: %s"
				+ "<br> Plant fruits: %s"
				+ "<br>"
				+ "<br> Active flag: %s</html>",
				XFormatter.formatRaw(cellData),
				XUcfCoder.decodeLandscapeType(cellData),
				XUcfCoder.decodeHumanType(cellData),
				XFormatter.formatDate(XUcfCoder.decodeHumanAge(cellData)),
				XUcfCoder.decodeHumanEnergy(cellData),
				XUcfCoder.decodeHumanSatiety(cellData),
				XFormatter.formatDate(XUcfCoder.decodeHumanPregnancy(cellData)),
				XUcfCoder.decodePlantType(cellData),
				XUcfCoder.decodePlantFruits(cellData),
				XUcfCoder.decodeActiveFlag(cellData));
		this.info.setText(info);
	}
	
	public void reset() {
		this.info.setText("-");
	}
	
	private void setupView() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(new TitledBorder("Cell Info"));
		setPreferredSize(new Dimension(500, 0));
	}
	
	private void setupInfo() {
		this.info = new JLabel("-");
		add(this.info);
	}

}
