package hr.matlazar.swing.components;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PopUp {
	
	public void popUpWindow(String note) {
		JLabel jLabel = new JLabel(note, JLabel.CENTER);
		jLabel.setForeground(Color.GREEN);
	    JOptionPane optionPane = new JOptionPane(jLabel);
	    JDialog dialog = optionPane.createDialog("");
	    dialog.setModal(true);
	    dialog.setVisible(true);
	    dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("pictures\\error.png"));
	    
	}
	
}
