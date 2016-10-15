package UIInterface;
import java.awt.EventQueue;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import main.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class MainEncryptionInterface {

	private JFrame frame;
	public JTextArea encryptionList;
	public JTextArea secretKeyOutput;
	public MainEncryption main;
	public JTextArea cipherTextOutput;
	public JTextArea mutationTextArea;
	public JTextArea crossoverTextArea;
	public JTextArea inputField;
	public JButton btnCopyKey;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainEncryptionInterface window = new MainEncryptionInterface();
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
	public MainEncryptionInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1077, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblGaEncryption = new JLabel("GA Encryption");
		lblGaEncryption.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGaEncryption.setHorizontalAlignment(SwingConstants.CENTER);
		lblGaEncryption.setBounds(434, 2, 122, 16);
		frame.getContentPane().add(lblGaEncryption);
		
		JLabel lblInput = new JLabel("Input:");
		lblInput.setForeground(Color.RED);
		lblInput.setBounds(6, 47, 61, 16);
		frame.getContentPane().add(lblInput);
		
		JLabel lblbitRepresentation = new JLabel("Encryption Steps :");
		lblbitRepresentation.setBounds(572, 505, 258, 16);
		frame.getContentPane().add(lblbitRepresentation);
		
		JLabel lblCrossover = new JLabel("Crossover:");
		lblCrossover.setBounds(330, 505, 67, 16);
		frame.getContentPane().add(lblCrossover);
		
		JLabel lblMutation = new JLabel("Mutation:");
		lblMutation.setBounds(96, 505, 67, 16);
		frame.getContentPane().add(lblMutation);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(313, 520, 230, 120);
		frame.getContentPane().add(scrollPane);
		
		crossoverTextArea= new JTextArea();
		crossoverTextArea.setEditable(false);
		scrollPane.setViewportView(crossoverTextArea);
		
		JScrollPane scrollPane_1 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setBounds(24, 520, 266, 120);
		frame.getContentPane().add(scrollPane_1);
		
		mutationTextArea = new JTextArea();
		mutationTextArea.setEditable(false);
		scrollPane_1.setViewportView(mutationTextArea);
		
		secretKeyOutput = new JTextArea();
		secretKeyOutput.setEditable(false);
		secretKeyOutput.setBounds(79, 238, 203, 26);
		frame.getContentPane().add(secretKeyOutput);
		
		JScrollPane scrollPane_2 = new JScrollPane(secretKeyOutput, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(6, 454, 1026, 40);
		frame.getContentPane().add(scrollPane_2);
		
		JLabel C = new JLabel("Secret Key :");
		C.setForeground(Color.BLUE);
		C.setBounds(6, 433, 191, 16);
		frame.getContentPane().add(C);
		
		JButton encryptButton = new JButton("Encrypt");
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				main = new MainEncryption(MainEncryptionInterface.this ,inputField.getText());

				main.encrypt();
				
			}
		});
		
		encryptButton.setBounds(919, 3, 117, 29);
		frame.getContentPane().add(encryptButton);
		
		JScrollPane scrollPane_4 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_4.setBounds(571, 520, 465, 120);
		frame.getContentPane().add(scrollPane_4);
		
		encryptionList = new JTextArea();
		encryptionList.setEditable(false);
		scrollPane_4.setViewportView(encryptionList);
		
		JLabel CipherText = new JLabel("Cipher Text :");
		CipherText.setForeground(Color.BLUE);
		CipherText.setBounds(6, 348, 157, 16);
		frame.getContentPane().add(CipherText);
		
		JScrollPane scrollPane_3 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBounds(10, 375, 1026, 40);
		frame.getContentPane().add(scrollPane_3);
		
		cipherTextOutput = new JTextArea();
		cipherTextOutput.setEditable(false);
		scrollPane_3.setViewportView(cipherTextOutput);
		
		inputField = new JTextArea();
		inputField.setBounds(57, 43, 979, 290);
		frame.getContentPane().add(inputField);
		
		JButton btnCopyCipher = new JButton("Copy Cipher");
		btnCopyCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String toBeCopyString = Arrays.deepToString(main.getCipherText());
				StringSelection stringSelection = new StringSelection(toBeCopyString);
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				btnCopyCipher.setText("Cipher Copied!");
				btnCopyKey.setText("Copy Key");
			}
		});
		btnCopyCipher.setBounds(919, 342, 117, 29);
		frame.getContentPane().add(btnCopyCipher);
		
		btnCopyKey = new JButton("Copy Key");
		btnCopyKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String toBeCopyString = Arrays.deepToString(main.getSecretKey());
				StringSelection stringSelection = new StringSelection(toBeCopyString);
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				btnCopyCipher.setText("Copy Cipher");
				btnCopyKey.setText("Key Copied!");
			}
		});
		btnCopyKey.setBounds(919, 420, 117, 29);
		frame.getContentPane().add(btnCopyKey);
	}
}
