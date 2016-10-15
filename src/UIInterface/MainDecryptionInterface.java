package UIInterface;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import main.MainDecryption;
import main.MainEncryption;
import util.ParsingRegex;
import java.awt.Color;
import java.awt.Font;


public class MainDecryptionInterface {

	private JFrame frame;
	public JTextArea decryptionList;
	public JTextArea categorizationTrainingIterationList;
	public JTextArea swapTrainingIteration;
	public JTextArea cipher_input ;
	public MainDecryption main;
	public JTextArea decryptedText;
	public JTextArea secretkey_input;
	public JButton btnCopyDecryptedMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDecryptionInterface window = new MainDecryptionInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainDecryptionInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 819);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		JLabel lblAnnDecryption = new JLabel("ANN Decryption");
		lblAnnDecryption.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAnnDecryption.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnDecryption.setBounds(313, 9, 199, 16);
		frame.getContentPane().add(lblAnnDecryption);
		
		JLabel lblbitRepresentationOf = new JLabel("Decryption Steps :");
		lblbitRepresentationOf.setBounds(8, 356, 199, 16);
		frame.getContentPane().add(lblbitRepresentationOf);
		
		JScrollPane scrollPane_7 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_7.setBounds(10, 373, 803, 238);
		frame.getContentPane().add(scrollPane_7);
		
		decryptionList = new JTextArea();
		decryptionList.setEditable(false);
		scrollPane_7.setViewportView(decryptionList);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[][] parsedCipherIn = ParsingRegex.retCipherText(cipher_input.getText());
				String[] secretKey 		= ParsingRegex.retSecKey(secretkey_input.getText());
				main = new MainDecryption(MainDecryptionInterface.this, parsedCipherIn , secretKey);
				decryptedText.setText("");
				main.decrypt();
			}
		});
		btnDecrypt.setBounds(718, 5, 117, 29);
		frame.getContentPane().add(btnDecrypt);
		
		JButton btnTrain = new JButton("Train Network");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainDecryption.trainANN(MainDecryptionInterface.this);
			}
		});
		btnTrain.setBounds(591, 5, 117, 29);
		frame.getContentPane().add(btnTrain);
		
		JScrollPane scrollPane_8 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_8.setBounds(8, 649, 393, 120);
		frame.getContentPane().add(scrollPane_8);
		
		categorizationTrainingIterationList = new JTextArea();
		scrollPane_8.setViewportView(categorizationTrainingIterationList);
		categorizationTrainingIterationList.setEditable(false);
		
		JLabel lblTrainingIteratioin = new JLabel("CategorizationTrainingIteration");
		lblTrainingIteratioin.setBounds(10, 622, 247, 16);
		frame.getContentPane().add(lblTrainingIteratioin);
		
		JScrollPane scrollPane_9 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_9.setBounds(420, 649, 393, 120);
		frame.getContentPane().add(scrollPane_9);
		
		swapTrainingIteration = new JTextArea();
		swapTrainingIteration.setEditable(false);
		scrollPane_9.setViewportView(swapTrainingIteration);
		
		JLabel lblSwapelementTrainingIteration = new JLabel("SwapElement Training Iteration");
		lblSwapelementTrainingIteration.setBounds(420, 622, 184, 16);
		frame.getContentPane().add(lblSwapelementTrainingIteration);
		
		JLabel lblCiphertextInput = new JLabel("Ciphertext input :");
		lblCiphertextInput.setForeground(Color.RED);
		lblCiphertextInput.setBounds(8, 24, 199, 16);
		frame.getContentPane().add(lblCiphertextInput);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 38, 807, 40);
		frame.getContentPane().add(scrollPane_1);
		
		cipher_input = new JTextArea();
		scrollPane_1.setViewportView(cipher_input);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 180, 807, 175);
		frame.getContentPane().add(scrollPane);
		
		decryptedText = new JTextArea();
		decryptedText.setEditable(false);
		scrollPane.setViewportView(decryptedText);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(8, 102, 807, 40);
		frame.getContentPane().add(scrollPane_2);
		
		secretkey_input = new JTextArea();
		scrollPane_2.setViewportView(secretkey_input);
		
		JLabel lblSecretKeyInput = new JLabel("Secret Key input :");
		lblSecretKeyInput.setForeground(Color.RED);
		lblSecretKeyInput.setBounds(8, 89, 199, 16);
		frame.getContentPane().add(lblSecretKeyInput);
		
		JLabel lblDecryptedMessage = new JLabel("Decrypted Message :");
		lblDecryptedMessage.setForeground(Color.BLUE);
		lblDecryptedMessage.setBounds(8, 153, 199, 16);
		frame.getContentPane().add(lblDecryptedMessage);
		
		btnCopyDecryptedMessage = new JButton("Copy Decrypted Message");
		btnCopyDecryptedMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String toBeCopyString = main.getDecryptedMessage();
				StringSelection stringSelection = new StringSelection(toBeCopyString);
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				btnCopyDecryptedMessage.setText("Decrypted Message Copied!");
			}
		});
		btnCopyDecryptedMessage.setBounds(608, 153, 205, 23);
		frame.getContentPane().add(btnCopyDecryptedMessage);
	}
}
