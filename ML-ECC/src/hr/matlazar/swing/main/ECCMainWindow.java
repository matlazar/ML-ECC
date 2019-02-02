package hr.matlazar.swing.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import hr.matlazar.ecc.domains.KeyPair;
import hr.matlazar.swing.buttonAction.GenerateKeys;
import hr.matlazar.swing.comboBox.Algoritham;
import hr.matlazar.swing.comboBox.KeySize;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ECCMainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ECCMainWindow window = new ECCMainWindow();
					window.frame.setTitle("Kriptografija eliptièkim krivuljama");
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ECCMainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.DARK_GRAY);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Korisnik\\Desktop\\Workspace\\ML-ECC\\ML-ECC\\pictures\\iconLock.png"));
		frame.getContentPane().setBackground(Color.WHITE);
		
		
		KeySize keySize = new KeySize();
		Algoritham algoritham = new Algoritham();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		JPanel panel = new JPanel();
		
		JLabel lblTekstZaEnkripciju = new JLabel("Tekst za enkripciju");
		
		JLabel lblEnkriptiraniTekst = new JLabel("Enkriptirani tekst");
		
		JLabel lblDekriptiraniTekst = new JLabel("Dekriptirani tekst");
		
		JLabel label_1 = new JLabel("Veli\u010Dina klju\u010Da: ");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label = new JLabel("Algoritam:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JComboBox keyLength = new JComboBox(keySize.getKeySize().toArray());
		keyLength.setBackground(Color.WHITE);
		
		JComboBox comboBox_1 = new JComboBox(algoritham.getAlgoritham().toArray());
		comboBox_1.setBackground(Color.WHITE);
		
		JButton btnKeyGenerator = new JButton("Izgeneriraj klju\u010Deve");
		btnKeyGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KeyPair keyPair = GenerateKeys.getKeys(keyLength.getSelectedItem().toString());
				
			}
		});
		
		JTextArea txtToEncryt = new JTextArea();
		txtToEncryt.setBackground(UIManager.getColor("Button.disabledShadow"));
		txtToEncryt.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtToEncryt.setLineWrap(true);
		txtToEncryt.setWrapStyleWord(true);
		
		JTextArea encryptedText = new JTextArea();
		encryptedText.setEditable(false);
		encryptedText.setBackground(Color.LIGHT_GRAY);
		encryptedText.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		encryptedText.setLineWrap(true);
		encryptedText.setWrapStyleWord(true);
		
		JTextArea decryptedText = new JTextArea();
		decryptedText.setBackground(Color.WHITE);
		decryptedText.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		decryptedText.setLineWrap(true);
		decryptedText.setWrapStyleWord(true);
		
		JTextArea txtPrivateKey = new JTextArea();
		txtPrivateKey.setEditable(false);
		txtPrivateKey.setBackground(Color.LIGHT_GRAY);
		txtPrivateKey.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtPrivateKey.setLineWrap(true);
		txtPrivateKey.setWrapStyleWord(true);
		
		JTextArea txtPubicKey = new JTextArea();
		txtPubicKey.setEditable(false);
		txtPubicKey.setBackground(Color.LIGHT_GRAY);
		txtPubicKey.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtPubicKey.setLineWrap(true);
		txtPubicKey.setWrapStyleWord(true);
		
		JLabel lblPrivatniKlju = new JLabel("Privatni klju\u010D");
		
		JLabel lblJavniKlju = new JLabel("Javni klju\u010D");
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTekstZaEnkripciju, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtToEncryt, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(encryptedText, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addComponent(lblEnkriptiraniTekst, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(decryptedText, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDekriptiraniTekst, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtPrivateKey, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPrivatniKlju, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblJavniKlju, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtPubicKey)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(59)
							.addComponent(btnKeyGenerator, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(82, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPrivateKey, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtPubicKey, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnKeyGenerator))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblJavniKlju)
								.addComponent(lblPrivatniKlju))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTekstZaEnkripciju)
						.addComponent(lblEnkriptiraniTekst)
						.addComponent(lblDekriptiraniTekst))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtToEncryt, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
						.addComponent(encryptedText, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
						.addComponent(decryptedText, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
					.addGap(274))
		);
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_1, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(keyLength, Alignment.TRAILING, 0, 95, Short.MAX_VALUE))))
					.addGap(25))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(keyLength))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(comboBox_1))
					.addGap(15))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 1039, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
