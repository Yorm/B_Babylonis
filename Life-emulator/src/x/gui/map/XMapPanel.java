package x.gui.map;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import x.data.ucf.XUcfCoder;

@SuppressWarnings("serial")
public class XMapPanel extends JTable {
	
	public final static int MAP_SIZE = 65;
	public final static int CELL_SIZE = 10;
	
	public XMapPanel() {
		setupView();
		setupCells();
		setVisible(true);
	}
	
	public void setRawDataAt(long uc, int y, int x) {
		setValueAt(uc, y, x);
	}
	
	public long getRawDataAt(int y, int x) {
		return (long) getValueAt(y, x);
	}
	
	public void setLandscapeTypeAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodeLandscapeType((long) getValueAt(y, x), u), y, x);
	}
	
	public int getLandscapeTypeAt(int y, int x) {
		return XUcfCoder.decodeLandscapeType((long) getValueAt(y, x));
	}
	
	public void setHumanTypeAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodeHumanType((long) getValueAt(y, x), u), y, x);
	}
	
	public int getHumanTypeAt(int y, int x) {
		return XUcfCoder.decodeHumanType((long) getValueAt(y, x));
	}
	
	public void setHumanAgeAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodeHumanAge((long) getValueAt(y, x), u), y, x);
	}
	
	public int getHumanAgeAt(int y, int x) {
		return XUcfCoder.decodeHumanAge((long) getValueAt(y, x));
	}
	
	public void setHumanEnergyAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodeHumanEnergy((long) getValueAt(y, x), u), y, x);
	}
	
	public int getHumanEnergyAt(int y, int x) {
		return XUcfCoder.decodeHumanEnergy((long) getValueAt(y, x));
	}
	
	public void setHumanSatietyAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodeHumanSatiety((long) getValueAt(y, x), u), y, x);
	}
	
	public int getHumanSatietyAt(int y, int x) {
		return XUcfCoder.decodeHumanSatiety((long) getValueAt(y, x));
	}
	
	public void setHumanPregnancyAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodeHumanPregnancy((long) getValueAt(y, x), u), y, x);
	}
	
	public int getHumanPregnancyAt(int y, int x) {
		return XUcfCoder.decodeHumanPregnancy((long) getValueAt(y, x));
	}
	
	public void setPlantTypeAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodePlantType((long) getValueAt(y, x), u), y, x);
	}
	
	public int getPlantTypeAt(int y, int x) {
		return XUcfCoder.decodePlantType((long) getValueAt(y, x));
	}
	
	public void setPlantFruitsAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodePlantFruits((long) getValueAt(y, x), u), y, x);
	}
	
	public int getPlantFruitsAt(int y, int x) {
		return XUcfCoder.decodePlantFruits((long) getValueAt(y, x));
	}
	
	public void setActiveFlagAt(int u, int y, int x) {
		setValueAt(XUcfCoder.encodeActiveFlag((long) getValueAt(y, x), u), y, x);
	}
	
	public int getActiveFlagAt(int y, int x) {
		return XUcfCoder.decodeActiveFlag((long) getValueAt(y, x));
	}
	
	private void setupView() {
		setModel(new DefaultTableModel(MAP_SIZE, MAP_SIZE) {
			@Override
			public boolean isCellEditable(int row, int column){  
				return false;
			}
		});
		setRowHeight(CELL_SIZE);
		for (int i = 0; i < MAP_SIZE; i++) {
			this.getColumnModel().getColumn(i).setMinWidth(CELL_SIZE);
			this.getColumnModel().getColumn(i).setMaxWidth(CELL_SIZE);
		}
		setBorder(BorderFactory.createLineBorder(new Color(0x333333)));
		setDefaultRenderer(Object.class, new XCellRenderer());
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\resources\\gui\\fonts\\map-units.ttf")).deriveFont(7f);
			setFont(customFont);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setupCells() {
		for (int x = 0; x < MAP_SIZE; x++) {
			for (int y = 0; y < MAP_SIZE; y++) {
				setValueAt(0x0000L, y, x);
			}
		}
	}
}
