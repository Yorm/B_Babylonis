/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
    public JPanel panel;
    
    public MainPanel(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout()); 
     
        JTextArea textArea = new JTextArea(20, 20);
        textArea.setEditable(false);
        textArea.setText("qweqweqwe\nfe");

        
        panel.add(textArea);

        add(panel);
        setVisible(true);
    }
    
}
