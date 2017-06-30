package x.gui.info.events;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class XEventsInfoPanel extends JScrollPane {
	
	private JList<String> events = new JList<String>(new DefaultListModel<String>());
	
	public XEventsInfoPanel() {
		setupView();
		setupEvents();		
		setVisible(true);
	}
	
	public void update(String event) {
		((DefaultListModel<String>)this.events.getModel()).addElement(event);
		int lastIndex = events.getModel().getSize() - 1;
		if (lastIndex >= 0) {
			events.ensureIndexIsVisible(lastIndex);
		}
	}
	
	public void reset() {
		((DefaultListModel<String>)this.events.getModel()).clear();
	}
	
	private void setupView() {
		setBorder(new TitledBorder("Events Info"));
		setPreferredSize(new Dimension(200, 0));
	}
	
	private void setupEvents() {
		this.events.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.events.setLayoutOrientation(JList.VERTICAL);
		this.events.setBackground(new Color(0xEEEEEE));
		setViewportView(this.events);
	}

}
