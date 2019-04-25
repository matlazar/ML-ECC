package hr.matlazar.swing.main;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import hr.matlazar.ecc.algoritams.DiffieHellman;
import hr.matlazar.ecc.arithmetic.PointEC;
import hr.matlazar.ecc.domains.ECCDHKeyPair;
import hr.matlazar.ecc.domains.KeyPair;
import hr.matlazar.ecc.fileRW.ClearFile;
import hr.matlazar.ecc.fileRW.ReadFile;
import hr.matlazar.ecc.fileRW.ReadKey;
import hr.matlazar.ecc.fileRW.WriteFile;
import hr.matlazar.ecc.keyGenerator.KeyGenerator;
import hr.matlazar.swing.buttonAction.GenerateKeys;
import hr.matlazar.swing.comboBox.DHType;
import hr.matlazar.swing.comboBox.KeySize;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import java.util.Base64;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DiffieHellmanWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void DiffieHellmanScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiffieHellmanWindow window = new DiffieHellmanWindow();
					window.frame.setVisible(true);
					window.frame.setTitle("Diffie-Hellman algoritam");
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
	public DiffieHellmanWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 705);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("pictures\\iconLock.png"));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		KeySize keySize = new KeySize();
		DHType dhType = new DHType();
		
		JComboBox keyLength = new JComboBox(keySize.getKeySize().toArray());
		keyLength.setBounds(378, 11, 181, 20);
		
		JComboBox eccDlpCbx = new JComboBox(dhType.getDhType().toArray());
		eccDlpCbx.setBounds(378, 41, 181, 20);
		
		JPanel alicePanel = new JPanel();
		alicePanel.setBounds(0, 0, 329, 714);
		alicePanel.setBackground(Color.WHITE);
		
		JPanel bobPanel = new JPanel();
		bobPanel.setBounds(606, 0, 329, 714);
		bobPanel.setBackground(Color.WHITE);
		
		JLabel labelBob = new JLabel("Bob");
		labelBob.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		JTextArea txtAreaPrivateBob = new JTextArea();
		txtAreaPrivateBob.setWrapStyleWord(true);
		txtAreaPrivateBob.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtAreaPrivateBob.setLineWrap(true);
		txtAreaPrivateBob.setEditable(false);
		txtAreaPrivateBob.setBackground(Color.LIGHT_GRAY);
		
		JTextArea txtAreaPublicBob = new JTextArea();
		txtAreaPublicBob.setWrapStyleWord(true);
		txtAreaPublicBob.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtAreaPublicBob.setLineWrap(true);
		txtAreaPublicBob.setEditable(false);
		txtAreaPublicBob.setBackground(Color.LIGHT_GRAY);
		
		JTextArea txtGetBobKey = new JTextArea();
		txtGetBobKey.setWrapStyleWord(true);
		txtGetBobKey.setLineWrap(true);
		txtGetBobKey.setEditable(false);
		txtGetBobKey.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtGetBobKey.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblPrivateKeyBob = new JLabel("Private Key");
		
		JLabel lblPublicKeyBob = new JLabel("Public Key");
		
		JButton btnKeysBob = new JButton("Izgeneriraj klju\u010Deve");
		btnKeysBob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(eccDlpCbx.getSelectedItem().toString().equals("Compressed-Diffie-Helman")) {
					KeyPair keyPair = GenerateKeys.getDHKey(keyLength.getSelectedItem().toString());
					WriteFile.writePublicKeys(new File("files/publicKeys.txt"), keyPair.getPublicKey() ,"Bob");
					WriteFile.write(new File("files/bobPrivateKey.txt"), keyPair.getPrivateKey());
					txtAreaPrivateBob.setText(keyPair.getPrivateKey());
					txtAreaPublicBob.setText(keyPair.getPublicKey());
				}else {
					ECCDHKeyPair keyPair = GenerateKeys.getECCDHKey(keyLength.getSelectedItem().toString());
					WriteFile.writePublicKeys(new File("files/publicKeys.txt"), keyPair.getPublicKeyPoint().getX()+"-"+keyPair.getPublicKeyPoint().getY(), "Bob");
					WriteFile.write(new File("files/bobPrivateKey.txt"), keyPair.getPrivateKey());
					txtAreaPrivateBob.setText(keyPair.getPrivateKey());
					txtAreaPublicBob.setText( keyPair.getPublicKeyPoint().getX()+"-"+keyPair.getPublicKeyPoint().getY());
				}
			}
		});
		
		JButton btnUcitajKljuceveBob = new JButton("U\u010Ditaj klju\u010Deve");
		btnUcitajKljuceveBob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAreaPublicBob.setText(ReadFile.getPublicKey(new File("files/publicKeys.txt"), "Bob"));
				txtAreaPrivateBob.setText(ReadKey.read(new File("files/bobPrivateKey.txt")));
			}
		});
		
		JTextArea txtGetAliceKey = new JTextArea();
		txtGetAliceKey.setWrapStyleWord(true);
		txtGetAliceKey.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtGetAliceKey.setLineWrap(true);
		txtGetAliceKey.setEditable(false);
		txtGetAliceKey.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblAlicesPublicKey = new JLabel("Alice's Public Key");
		
		JTextArea txtSSBob = new JTextArea();
		txtSSBob.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtSSBob.setWrapStyleWord(true);
		txtSSBob.setLineWrap(true);
		txtSSBob.setEditable(false);
		txtSSBob.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblSharedSecretBob = new JLabel("Shared Secred");
		
		JButton btnBobGenerateSS = new JButton("Izgeneriraj zajedni\u010Dku tajnu");
		btnBobGenerateSS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eccDlpCbx.getSelectedItem().toString().equals("Compressed-Diffie-Helman")) {
					String alicePublicKey = ReadFile.getPublicKey(new File("files/publicKeys.txt"), "Alice");
					String bobPrivateKey = ReadKey.read(new File("files/bobPrivateKey.txt"));
					DiffieHellman dh = new DiffieHellman(keyLength.getSelectedItem().toString());
					txtSSBob.setText(dh.computeSharedSecret(alicePublicKey, bobPrivateKey));
				} else {
					String[] coordinates = ReadFile.getPublicKey(new File("files/publicKeys.txt"), "Alice").split("-");
					BigInteger x = new BigInteger(coordinates[0]);
					BigInteger y = new BigInteger(coordinates[1]);
					String bobPrivateKey = ReadKey.read(new File("files/bobPrivateKey.txt"));
					DiffieHellman dh = new DiffieHellman(keyLength.getSelectedItem().toString());
					PointEC alicePublicKey = new PointEC(x, y, dh.a, dh.b, dh.p);
					PointEC ss = dh.sharedSecret(alicePublicKey, bobPrivateKey);
					txtSSBob.setText(ss.getX() + "-" + ss.getY());
				}
			}
		});
		
		GroupLayout gl_bobPanel = new GroupLayout(bobPanel);
		gl_bobPanel.setHorizontalGroup(
			gl_bobPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bobPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_bobPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_bobPanel.createSequentialGroup()
							.addGroup(gl_bobPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(labelBob, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAreaPrivateBob, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAreaPublicBob, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_bobPanel.createSequentialGroup()
									.addComponent(btnKeysBob, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUcitajKljuceveBob, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtGetAliceKey, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_bobPanel.createSequentialGroup()
									.addComponent(lblSharedSecretBob, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnBobGenerateSS, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtSSBob, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAlicesPublicKey, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPublicKeyBob, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_bobPanel.createSequentialGroup()
							.addComponent(lblPrivateKeyBob, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addGap(171))))
		);
		gl_bobPanel.setVerticalGroup(
			gl_bobPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bobPanel.createSequentialGroup()
					.addComponent(labelBob)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_bobPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnKeysBob)
						.addComponent(btnUcitajKljuceveBob))
					.addGap(12)
					.addComponent(lblPrivateKeyBob)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtAreaPrivateBob, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPublicKeyBob)
					.addGap(3)
					.addComponent(txtAreaPublicBob, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(lblAlicesPublicKey)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtGetAliceKey, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_bobPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_bobPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblSharedSecretBob))
						.addGroup(gl_bobPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBobGenerateSS)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSSBob, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		bobPanel.setLayout(gl_bobPanel);
		
		JButton btnPodaljiBobuJavni = new JButton("Po\u0161alji Bob-u javni klju\u010D >>");
		btnPodaljiBobuJavni.setBounds(364, 432, 208, 23);
		btnPodaljiBobuJavni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtGetAliceKey.setText(ReadFile.getPublicKey(new File("files/publicKeys.txt"), "Alice"));
			}
		});
		
		JButton btnSendAliceKey = new JButton("<<  Po\u0161alji Alice javni klju\u010D");
		btnSendAliceKey.setBounds(364, 492, 208, 23);
		btnSendAliceKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtGetBobKey.setText(ReadFile.getPublicKey(new File("files/publicKeys.txt"), "Bob"));
			}
		});
		
		JLabel lblAlice = new JLabel("Alice");
		lblAlice.setFont(new Font("Tahoma", Font.BOLD, 11));
		

		
		JTextArea txtAreaPrivateAlice = new JTextArea();
		txtAreaPrivateAlice.setEditable(false);
		txtAreaPrivateAlice.setBackground(Color.LIGHT_GRAY);
		txtAreaPrivateAlice.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtAreaPrivateAlice.setLineWrap(true);
		txtAreaPrivateAlice.setWrapStyleWord(true);
		
		JTextArea txtAreaPublicAlice = new JTextArea();
		txtAreaPublicAlice.setWrapStyleWord(true);
		txtAreaPublicAlice.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtAreaPublicAlice.setLineWrap(true);
		txtAreaPublicAlice.setEditable(false);
		txtAreaPublicAlice.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblPrivateKeyAlice = new JLabel("Private Key");
		
		JLabel lblPublicKeyAlice = new JLabel("Public Key");
		
		JButton btnKeysAlice = new JButton("Izgeneriraj klju\u010Deve");
		btnKeysAlice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(eccDlpCbx.getSelectedItem().toString().equals("Compressed-Diffie-Helman")) {
					KeyPair keyPair = GenerateKeys.getDHKey(keyLength.getSelectedItem().toString());
					WriteFile.writePublicKeys(new File("files/publicKeys.txt"), keyPair.getPublicKey() ,"Alice");
					WriteFile.write(new File("files/alicePrivateKey.txt"), keyPair.getPrivateKey());
					txtAreaPrivateAlice.setText(keyPair.getPrivateKey());
					txtAreaPublicAlice.setText(keyPair.getPublicKey());
				}else {
					ECCDHKeyPair keyPair = GenerateKeys.getECCDHKey(keyLength.getSelectedItem().toString());
					WriteFile.writePublicKeys(new File("files/publicKeys.txt"), keyPair.getPublicKeyPoint().getX()+"-"+keyPair.getPublicKeyPoint().getY(), "Alice");
					WriteFile.write(new File("files/alicePrivateKey.txt"), keyPair.getPrivateKey());
					txtAreaPrivateAlice.setText(keyPair.getPrivateKey());
					txtAreaPublicAlice.setText( keyPair.getPublicKeyPoint().getX()+"-"+keyPair.getPublicKeyPoint().getY());
				}
			}
		});
		
		JButton btnUcitajKljueveAlice = new JButton("U\u010Ditaj klju\u010Deve");
		btnUcitajKljueveAlice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAreaPublicAlice.setText(ReadFile.getPublicKey(new File("files/publicKeys.txt"), "Alice"));
				txtAreaPrivateAlice.setText(ReadKey.read(new File("files/alicePrivateKey.txt")));
			}
		});
		
		
		JLabel lblBobsPublicKey = new JLabel("Bob's Public Key");
		
		JTextArea txtSSAlice = new JTextArea();
		txtSSAlice.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtSSAlice.setWrapStyleWord(true);
		txtSSAlice.setLineWrap(true);
		txtSSAlice.setEditable(false);
		txtSSAlice.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblSharedSecredAlice = new JLabel("Shared Secred");
		
		JButton btnAliceGenerateSS = new JButton("Izgeneriraj zajedni\u010Dku tajnu");
		btnAliceGenerateSS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eccDlpCbx.getSelectedItem().toString().equals("Compressed-Diffie-Helman")) {
					String bobPublicKey = ReadFile.getPublicKey(new File("files/publicKeys.txt"), "Bob");
					String alicePrivateKey = ReadKey.read(new File("files/alicePrivateKey.txt"));
					DiffieHellman dh = new DiffieHellman(keyLength.getSelectedItem().toString());
					txtSSAlice.setText(dh.computeSharedSecret(bobPublicKey, alicePrivateKey));
				} else {
					String[] coordinates = ReadFile.getPublicKey(new File("files/publicKeys.txt"), "Bob").split("-");
					BigInteger x = new BigInteger(coordinates[0]);
					BigInteger y = new BigInteger(coordinates[1]);
					String alicePrivateKey = ReadKey.read(new File("files/alicePrivateKey.txt"));
					DiffieHellman dh = new DiffieHellman(keyLength.getSelectedItem().toString());
					PointEC bobPublicKey = new PointEC(x, y, dh.a, dh.b, dh.p);
					PointEC ss = dh.sharedSecret(bobPublicKey, alicePrivateKey);
					txtSSAlice.setText(ss.getX() + "-" + ss.getY());
				}
			}
		});
		frame.getContentPane().setLayout(null);
		
		GroupLayout gl_alicePanel = new GroupLayout(alicePanel);
		gl_alicePanel.setHorizontalGroup(
			gl_alicePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_alicePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_alicePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_alicePanel.createSequentialGroup()
							.addGroup(gl_alicePanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_alicePanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_alicePanel.createSequentialGroup()
										.addComponent(btnKeysAlice, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnUcitajKljueveAlice, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblAlice, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtAreaPublicAlice, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
									.addComponent(lblPublicKeyAlice, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPrivateKeyAlice, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtAreaPrivateAlice))
								.addComponent(txtGetBobKey, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_alicePanel.createSequentialGroup()
							.addComponent(lblSharedSecredAlice)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAliceGenerateSS, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addGap(34))
						.addGroup(gl_alicePanel.createSequentialGroup()
							.addComponent(txtSSAlice, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_alicePanel.createSequentialGroup()
							.addComponent(lblBobsPublicKey, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(119, Short.MAX_VALUE))))
		);
		gl_alicePanel.setVerticalGroup(
			gl_alicePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_alicePanel.createSequentialGroup()
					.addComponent(lblAlice)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_alicePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnKeysAlice)
						.addComponent(btnUcitajKljueveAlice))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPrivateKeyAlice)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtAreaPrivateAlice, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPublicKeyAlice)
					.addGap(1)
					.addComponent(txtAreaPublicAlice, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(lblBobsPublicKey)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtGetBobKey, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_alicePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAliceGenerateSS)
						.addComponent(lblSharedSecredAlice))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSSAlice, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		alicePanel.setLayout(gl_alicePanel);
		frame.getContentPane().add(alicePanel);
		frame.getContentPane().add(keyLength);
		frame.getContentPane().add(eccDlpCbx);
		frame.getContentPane().add(btnSendAliceKey);
		frame.getContentPane().add(btnPodaljiBobuJavni);
		frame.getContentPane().add(bobPanel);
		
		
		JTextArea validationArea = new JTextArea();
		validationArea.setBounds(339, 83, 257, 238);
		validationArea.setWrapStyleWord(true);
		validationArea.setLineWrap(true);
		validationArea.setEditable(false);
		frame.getContentPane().add(validationArea);
		
		JButton btnValidation = new JButton("Provijeri");
		btnValidation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sSAlice = txtSSAlice.getText();
				String sSBob = txtSSBob.getText();
				if(eccDlpCbx.getSelectedItem().toString().equals("Compressed-Diffie-Helman")) {
					BigInteger sSAliceNumber = new BigInteger(Base64.getDecoder().decode(sSAlice.getBytes()));
					BigInteger sSBobNumber = new BigInteger(Base64.getDecoder().decode(sSBob.getBytes()));
					String txtOutput = "Number Alice: " + sSAliceNumber + "\n ==> Number Bob: " + sSBobNumber;
					validationArea.setText(txtOutput);
					if(DiffieHellman.verifySharedSecert(sSAlice, sSBob)) {
						btnValidation.setBackground(Color.GREEN);
					}else {
						btnValidation.setBackground(Color.RED);
					}
				} else {
					String txtOutput = "Point Alice: " + sSAlice + "\n ==> Point Bob: " + sSBob;
					validationArea.setText(txtOutput);
					if(DiffieHellman.verify(sSAlice, sSBob)) {
						btnValidation.setBackground(Color.GREEN);
					}else {
						btnValidation.setBackground(Color.RED);
					}
				}
			}
		});
		btnValidation.setBounds(423, 332, 95, 23);
		frame.getContentPane().add(btnValidation);
		
		JButton btnResetiraj = new JButton("Resetiraj");
		btnResetiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClearFile.clearFile("files/alicePrivateKey.txt");
				ClearFile.clearFile("files/bobPrivateKey.txt");
				ClearFile.clearFile("files/publicKeys.txt");
				txtAreaPrivateAlice.setText("");
				txtAreaPrivateBob.setText("");
				txtSSAlice.setText("");
				txtSSBob.setText("");
				txtAreaPublicAlice.setText("");
				txtAreaPublicBob.setText("");
				txtGetAliceKey.setText("");
				txtGetBobKey.setText("");
				validationArea.setText("");
				btnValidation.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnResetiraj.setBounds(423, 362, 95, 23);
		frame.getContentPane().add(btnResetiraj);
		
		
	}
}
