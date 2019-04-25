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
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import hr.matlazar.ecc.algoritams.ECDSA;
import hr.matlazar.ecc.algoritams.ECIES;
import hr.matlazar.ecc.algoritams.ElGamal;
import hr.matlazar.ecc.domains.ECCDHKeyPair;
import hr.matlazar.ecc.domains.ECDSASignature;
import hr.matlazar.ecc.domains.ECIESMessage;
import hr.matlazar.ecc.domains.ElGamalSend;
import hr.matlazar.ecc.domains.KeyPair;
import hr.matlazar.ecc.fileRW.ReadEncrytedText;
import hr.matlazar.ecc.fileRW.ReadFile;
import hr.matlazar.ecc.fileRW.ReadKey;
import hr.matlazar.swing.buttonAction.GenerateKeys;
import hr.matlazar.swing.comboBox.Algoritham;
import hr.matlazar.swing.comboBox.KeySize;
import hr.matlazar.swing.components.PopUp;

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
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("pictures\\iconLock.png"));
		frame.getContentPane().setBackground(Color.WHITE);
		PopUp popUp = new PopUp();

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
		
		JComboBox algorithamBox = new JComboBox(algoritham.getAlgoritham().toArray());
		algorithamBox.setBackground(Color.WHITE);
		
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
		
		JLabel lblPrivatniKljuc = new JLabel("Privatni klju\u010D");
		
		JLabel lblJavniKljuc = new JLabel("Javni klju\u010D");
		
		JLabel lblNisteUnijeliNista = new JLabel("Niste unijeli ni\u0161ta za enkripciju");
		lblNisteUnijeliNista.setForeground(Color.RED);
		lblNisteUnijeliNista.setVisible(false);
		
		JLabel lblUcitajteTekst = new JLabel("U\u010Ditajte tekst ili enkriptirajte novi tekst");
		lblUcitajteTekst.setForeground(Color.RED);
		lblUcitajteTekst.setVisible(false);
		
		JButton btnKeyGenerator = new JButton("Izgeneriraj klju\u010Deve");
		btnKeyGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(algorithamBox.getSelectedItem().toString().equals("El-Gamal") || algorithamBox.getSelectedItem().toString().equals("ECIES")) {
					KeyPair keyPair = GenerateKeys.getKeys(keyLength.getSelectedItem().toString());
					txtPrivateKey.setText(keyPair.getPrivateKey());
					txtPubicKey.setText(keyPair.getPublicKey());				
				} else {
					ECCDHKeyPair keyPair = GenerateKeys.getECCDHKey(keyLength.getSelectedItem().toString());
					txtPrivateKey.setText(keyPair.getPrivateKey());
					txtPubicKey.setText(keyPair.getPublicKeyPoint().getX() + "-" + keyPair.getPublicKeyPoint().getY());
				}
			}
		});
		
		JButton btnEncrypt = new JButton("Enkriptiraj");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(txtToEncryt.getText().replaceAll(" ", "").isEmpty()) {
					lblNisteUnijeliNista.setVisible(true);
				}else {
					lblNisteUnijeliNista.setVisible(false);
					File file = new File("files/publicKey.txt");
					if(algorithamBox.getSelectedItem().toString().equals("El-Gamal")) {
						File elgamal  = new File("files/elgamal.txt");
						ElGamal elGamal = new ElGamal(keyLength.getSelectedItem().toString());
						elGamal.encrypt(ReadKey.read(file), txtToEncryt.getText());
						ElGamalSend elGamalSend = (ElGamalSend) ReadFile.readFile(elgamal, "ElGamal");
						encryptedText.setText(elGamalSend.getSharedSecret());
					} else {
						File eciesFile = new File("files/ecies.txt");
						ECIES ecies = new ECIES(keyLength.getSelectedItem().toString());
						ecies.encryptECIES(txtToEncryt.getText(), ReadKey.read(file));
						ECIESMessage eciesMessage = (ECIESMessage) ReadFile.readFile(eciesFile, "ECIES");
						encryptedText.setText(eciesMessage.getMessage());
					}
				}
			}
		});
		
		JButton btnDekriptiraj = new JButton("Dekriptiraj");
		btnDekriptiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(encryptedText.getText().replaceAll(" ", "").isEmpty()) {
					lblUcitajteTekst.setVisible(true);
				}else {
					lblUcitajteTekst.setVisible(false);
					File file = new File("files/privateKey.txt");
					if(algorithamBox.getSelectedItem().toString().equals("El-Gamal")) {
						File elgamal  = new File("files/elgamal.txt");
						ElGamal elGamal = new ElGamal(keyLength.getSelectedItem().toString());
						ElGamalSend elGamalSend = (ElGamalSend) ReadFile.readFile(elgamal, "ElGamal");
						decryptedText.setText(elGamal.decrypt(elGamalSend, ReadKey.read(file)));
					} else {
						File eciesFile = new File("files/ecies.txt");
						ECIES ecies = new ECIES(keyLength.getSelectedItem().toString());
						ECIESMessage eciesMessage = (ECIESMessage) ReadFile.readFile(eciesFile, "ECIES");
						decryptedText.setText(ecies.decrypt(eciesMessage, ReadKey.read(file)));
					}
					
				}
			}
		});
		
		JButton btnUcitajKljuc = new JButton("U\u010Ditaj klju\u010Deve");
		btnUcitajKljuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File privateKey = new File("files/privateKey.txt");
				File publicKey = new File("files/publicKey.txt");
				txtPrivateKey.setText(ReadKey.read(privateKey));
				txtPubicKey.setText(ReadKey.read(publicKey));
			}
		});
		
		JButton btnUcitajEnkriptiraniTeskt = new JButton("U\u010Ditaj enkriptirani teskt");
		btnUcitajEnkriptiraniTeskt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File eciesFile = new File("files/ecies.txt");
				File elgamal  = new File("files/elgamal.txt");
				if(algorithamBox.getSelectedItem().toString().equals("El-Gamal")) {
					encryptedText.setText(ReadEncrytedText.readEcnryptionTxt(elgamal, 0));
				}else {
					encryptedText.setText(ReadEncrytedText.readEcnryptionTxt(eciesFile, 1));
				}
				
				
			}
		});
		
		JButton btnPotpisi = new JButton("Potpi\u0161i");
		btnPotpisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtToEncryt.getText().replaceAll(" ", "").isEmpty()) {
					lblNisteUnijeliNista.setVisible(true);
				}else {
					if(txtPrivateKey.getText().isEmpty()) {
						popUp.popUpWindow("Uèitaj kljuèeve");
					} else {
						lblNisteUnijeliNista.setVisible(false);
						ECDSA ecdsa = new ECDSA(keyLength.getSelectedItem().toString());
						ecdsa.signMessage(encryptedText.getText(), txtPrivateKey.getText());
						popUp.popUpWindow("Potpisano");
					}
				}
			}
		});
		
		JButton btnProvijeriPotpis = new JButton("Provijeri potpis");
		btnProvijeriPotpis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtPubicKey.getText().isEmpty() || encryptedText.getText().isEmpty()) {
					popUp.popUpWindow("Uèitaj kljuèeve");
				} else {
					File file = new File("files/ECDSA.txt");
					ECDSA ecdsa = new ECDSA(keyLength.getSelectedItem().toString());
					ECDSASignature ecdsaSignature = (ECDSASignature) ReadFile.readFile(file, "ECDSASignature");
					if(ecdsa.dehashString(ecdsaSignature, txtPubicKey.getText())) {
						btnProvijeriPotpis.setBackground(Color.GREEN);
					} else {
						btnProvijeriPotpis.setBackground(Color.RED);
					}
				}
			}
		});
		
		JButton btnDiffiehellmanAlgoritham = new JButton("Diffie-Hellman algoritam");
		btnDiffiehellmanAlgoritham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DiffieHellmanWindow dhFrame = new DiffieHellmanWindow();
				dhFrame.DiffieHellmanScreen();
				
			}
		});
		frame.getContentPane().add(btnDiffiehellmanAlgoritham);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblPrivatniKljuc, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtPrivateKey, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtPubicKey, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(lblJavniKljuc, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblTekstZaEnkripciju, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtToEncryt, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnEncrypt, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnPotpisi, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblEnkriptiraniTekst, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addComponent(encryptedText, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnDekriptiraj)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnUcitajEnkriptiraniTeskt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addComponent(lblUcitajteTekst, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblDekriptiraniTekst, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
											.addComponent(decryptedText, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(94)
										.addComponent(btnProvijeriPotpis, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
								.addGap(4))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(48)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnUcitajKljuc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnKeyGenerator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
								.addGap(267)
								.addComponent(btnDiffiehellmanAlgoritham, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNisteUnijeliNista, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(2))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtPrivateKey)
								.addComponent(txtPubicKey, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPrivatniKljuc)
							.addComponent(lblJavniKljuc))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnKeyGenerator)))
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUcitajKljuc)
						.addComponent(btnDiffiehellmanAlgoritham))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDekriptiraniTekst)
						.addComponent(lblEnkriptiraniTekst)
						.addComponent(lblTekstZaEnkripciju))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtToEncryt, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
						.addComponent(encryptedText, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
						.addComponent(decryptedText, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEncrypt)
						.addComponent(btnDekriptiraj)
						.addComponent(btnUcitajEnkriptiraniTeskt)
						.addComponent(btnPotpisi)
						.addComponent(btnProvijeriPotpis))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNisteUnijeliNista)
						.addComponent(lblUcitajteTekst))
					.addGap(207))
		);
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(algorithamBox, Alignment.TRAILING, 0, 115, Short.MAX_VALUE)
								.addComponent(keyLength, Alignment.TRAILING, 0, 115, Short.MAX_VALUE))))
					.addGap(26))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(keyLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(algorithamBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 1039, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
