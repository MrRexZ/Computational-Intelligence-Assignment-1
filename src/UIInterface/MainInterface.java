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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import main.*;

public class MainInterface {

	private JFrame frame;
	private JTextField inputField;
	private JTextField genField;
	public JTextArea mkeyTextArea;
	public JTextArea mutationTextArea;
	public JTextArea crossoverTextArea;
	public JTextArea encryptionList;
	public JTextArea ckeyTextArea;
	
	
	public JTextArea decryptCrossover;
	public JTextArea decryptionList;
	public JTextArea decryptMutation;
	public JTextArea categorizationTrainingIterationList;
	public JTextArea swapTrainingIteration;
	public MainEncryptionDecryption main;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface window = new MainInterface();
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
	public MainInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblGaEncryption = new JLabel("GA Encryption");
		lblGaEncryption.setHorizontalAlignment(SwingConstants.CENTER);
		lblGaEncryption.setBounds(250, 6, 122, 16);
		frame.getContentPane().add(lblGaEncryption);
		
		inputField = new JTextField();
		inputField.setToolTipText("Enter desired text/number here");
		inputField.setBounds(79, 42, 203, 26);
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);
		
		JLabel lblInput = new JLabel("Input:");
		lblInput.setBounds(6, 47, 61, 16);
		frame.getContentPane().add(lblInput);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(127, 79, 152, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblbitRepresentation = new JLabel("8-bit Representation of Encryption : ");
		lblbitRepresentation.setBounds(10, 108, 191, 16);
		frame.getContentPane().add(lblbitRepresentation);
		
		JLabel lblCrossover = new JLabel("Crossover:");
		lblCrossover.setBounds(277, 108, 67, 16);
		frame.getContentPane().add(lblCrossover);
		
		JLabel lblMutation = new JLabel("Mutation:");
		lblMutation.setBounds(565, 108, 67, 16);
		frame.getContentPane().add(lblMutation);
		
		crossoverTextArea = new JTextArea();
		crossoverTextArea.setBounds(79, 113, 203, 120);
		crossoverTextArea.setEditable(false);
		frame.getContentPane().add(crossoverTextArea);
		
		mutationTextArea = new JTextArea();
		mutationTextArea.setBounds(361, 113, 203, 120);
		mutationTextArea.setEditable(false);
		frame.getContentPane().add(mutationTextArea);
		
		JScrollPane scrollPane = new JScrollPane(crossoverTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(350, 108, 203, 120);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(mutationTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(632, 108, 203, 120);
		frame.getContentPane().add(scrollPane_1);
		
		genField = new JTextField();
		genField.setToolTipText("");
		genField.setColumns(10);
		genField.setBounds(378, 42, 67, 26);
		frame.getContentPane().add(genField);
		
		JLabel lblGenerations = new JLabel("Generations:");
		lblGenerations.setBounds(294, 47, 89, 16);
		frame.getContentPane().add(lblGenerations);
		
		ckeyTextArea = new JTextArea();
		ckeyTextArea.setEditable(false);
		ckeyTextArea.setBounds(79, 238, 203, 26);
		frame.getContentPane().add(ckeyTextArea);
		
		JScrollPane scrollPane_2 = new JScrollPane(ckeyTextArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(350, 233, 203, 40);
		frame.getContentPane().add(scrollPane_2);
		
		mkeyTextArea = new JTextArea();
		mkeyTextArea.setBounds(361, 238, 204, 26);
		frame.getContentPane().add(mkeyTextArea);
		
		JScrollPane scrollPane_3 = new JScrollPane(mkeyTextArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBounds(632, 233, 203, 40);
		frame.getContentPane().add(scrollPane_3);
		
		JLabel lblCrossoverKeys = new JLabel("C keys:");
		lblCrossoverKeys.setBounds(305, 238, 67, 16);
		frame.getContentPane().add(lblCrossoverKeys);
		
		JLabel lblMKeys = new JLabel("M keys:");
		lblMKeys.setBounds(577, 238, 67, 16);
		frame.getContentPane().add(lblMKeys);
		
		JButton encryptButton = new JButton("Encrypt");
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				int generation = Integer.parseInt(genField.getText());
				main = new MainEncryptionDecryption(MainInterface.this ,inputField.getText() , generation);

				main.encrypt();
				
			}
		});
		
		encryptButton.setBounds(695, 41, 117, 29);
		frame.getContentPane().add(encryptButton);
		
		JLabel lblAnnDecryption = new JLabel("ANN Decryption");
		lblAnnDecryption.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnDecryption.setBounds(360, 284, 122, 16);
		frame.getContentPane().add(lblAnnDecryption);
		
		JScrollPane scrollPane_4 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_4.setBounds(6, 135, 273, 120);
		frame.getContentPane().add(scrollPane_4);
		
		encryptionList = new JTextArea();
		encryptionList.setEditable(false);
		scrollPane_4.setViewportView(encryptionList);
		
		JLabel lblDecryptCrossover = new JLabel("Decrypt Crossover :");
		lblDecryptCrossover.setBounds(317, 311, 119, 16);
		frame.getContentPane().add(lblDecryptCrossover);
		
		JLabel lblDecryptMutation = new JLabel("Decrypt Mutation :");
		lblDecryptMutation.setBounds(565, 311, 100, 16);
		frame.getContentPane().add(lblDecryptMutation);
		
		JScrollPane scrollPane_5 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_5.setBounds(277, 335, 243, 120);
		frame.getContentPane().add(scrollPane_5);
		
		decryptCrossover = new JTextArea();
		decryptCrossover.setEditable(false);
		scrollPane_5.setViewportView(decryptCrossover);
		
		JScrollPane scrollPane_6 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_6.setBounds(530, 335, 305, 120);
		frame.getContentPane().add(scrollPane_6);
		
		decryptMutation = new JTextArea();
		decryptMutation.setEditable(false);
		scrollPane_6.setViewportView(decryptMutation);
		
		JLabel lblbitRepresentationOf = new JLabel("8-bit Representation of Decryption :");
		lblbitRepresentationOf.setBounds(2, 308, 199, 16);
		frame.getContentPane().add(lblbitRepresentationOf);
		
		JScrollPane scrollPane_7 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_7.setBounds(-2, 335, 272, 120);
		frame.getContentPane().add(scrollPane_7);
		
		decryptionList = new JTextArea();
		decryptionList.setEditable(false);
		scrollPane_7.setViewportView(decryptionList);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				main.decrypt();
			}
		});
		btnDecrypt.setBounds(718, 284, 117, 29);
		frame.getContentPane().add(btnDecrypt);
		
		JButton btnTrain = new JButton("Train Network");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainEncryptionDecryption.trainANN();
			}
		});
		btnTrain.setBounds(593, 284, 117, 29);
		frame.getContentPane().add(btnTrain);
		
		JScrollPane scrollPane_8 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_8.setBounds(6, 495, 393, 120);
		frame.getContentPane().add(scrollPane_8);
		
		categorizationTrainingIterationList = new JTextArea();
		scrollPane_8.setViewportView(categorizationTrainingIterationList);
		categorizationTrainingIterationList.setEditable(false);
		
		JLabel lblTrainingIteratioin = new JLabel("CategorizationTrainingIteration");
		lblTrainingIteratioin.setBounds(8, 468, 247, 16);
		frame.getContentPane().add(lblTrainingIteratioin);
		
		JScrollPane scrollPane_9 = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_9.setBounds(420, 495, 393, 120);
		frame.getContentPane().add(scrollPane_9);
		
		swapTrainingIteration = new JTextArea();
		swapTrainingIteration.setEditable(false);
		scrollPane_9.setViewportView(swapTrainingIteration);
		
		JLabel lblSwapelementTrainingIteration = new JLabel("SwapElement Training Iteration");
		lblSwapelementTrainingIteration.setBounds(420, 466, 184, 16);
		frame.getContentPane().add(lblSwapelementTrainingIteration);
	}
}
