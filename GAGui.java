import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class GAGui {

	private JFrame frame;
	private JTextField inputField;
	private JTextField genField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GAGui window = new GAGui();
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
	public GAGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 315);
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
		lblNewLabel.setBounds(142, 75, 152, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblbitRepresentation = new JLabel("8-bit Representation:");
		lblbitRepresentation.setBounds(6, 75, 135, 16);
		frame.getContentPane().add(lblbitRepresentation);
		
		JLabel lblCrossover = new JLabel("Crossover:");
		lblCrossover.setBounds(6, 113, 67, 16);
		frame.getContentPane().add(lblCrossover);
		
		JLabel lblMutation = new JLabel("Mutation:");
		lblMutation.setBounds(294, 113, 67, 16);
		frame.getContentPane().add(lblMutation);
		
		JTextArea crossoverTextArea = new JTextArea();
		crossoverTextArea.setBounds(79, 113, 203, 120);
		crossoverTextArea.setEditable(false);
		frame.getContentPane().add(crossoverTextArea);
		
		JTextArea mutationTextArea = new JTextArea();
		mutationTextArea.setBounds(361, 113, 203, 120);
		mutationTextArea.setEditable(false);
		frame.getContentPane().add(mutationTextArea);
		
		JScrollPane scrollPane = new JScrollPane(crossoverTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(79, 113, 203, 120);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(mutationTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(361, 113, 203, 120);
		frame.getContentPane().add(scrollPane_1);
		
		genField = new JTextField();
		genField.setToolTipText("");
		genField.setColumns(10);
		genField.setBounds(378, 42, 67, 26);
		frame.getContentPane().add(genField);
		
		JLabel lblGenerations = new JLabel("Generations:");
		lblGenerations.setBounds(294, 47, 89, 16);
		frame.getContentPane().add(lblGenerations);
		
		JTextArea ckeyTextArea = new JTextArea();
		ckeyTextArea.setEditable(false);
		ckeyTextArea.setBounds(79, 238, 203, 26);
		frame.getContentPane().add(ckeyTextArea);
		
		JScrollPane scrollPane_2 = new JScrollPane(ckeyTextArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(79, 238, 203, 40);
		frame.getContentPane().add(scrollPane_2);
		
		JTextArea mkeyTextArea = new JTextArea();
		mkeyTextArea.setBounds(361, 238, 204, 26);
		frame.getContentPane().add(mkeyTextArea);
		
		JScrollPane scrollPane_3 = new JScrollPane(mkeyTextArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBounds(361, 238, 203, 40);
		frame.getContentPane().add(scrollPane_3);
		
		JLabel lblCrossoverKeys = new JLabel("C keys:");
		lblCrossoverKeys.setBounds(6, 238, 67, 16);
		frame.getContentPane().add(lblCrossoverKeys);
		
		JLabel lblMKeys = new JLabel("M keys:");
		lblMKeys.setBounds(294, 240, 67, 16);
		frame.getContentPane().add(lblMKeys);
		
		JButton encryptButton = new JButton("Encrypt");
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] inputString = inputField.getText().split("");
				int generation = Integer.parseInt(genField.getText());
				int input = inputString[0].charAt(0);
				
				int[] intInput = Main.conversion(input);
				lblNewLabel.setText(Arrays.toString(intInput));
				
				crossoverTextArea.setText("");
				mutationTextArea.setText("");
				ckeyTextArea.setText("");
				mkeyTextArea.setText("");
				
				//int[] oriInput = intInput;
				//boolean exit = false;
				
				for(int a = 0; a < generation; a++)
				{
					//int counter = 1;
					ckeyTextArea.append(Main.Crossover(intInput) + " ");
					crossoverTextArea.append("Gen " + (a+1) + " | " + Arrays.toString(intInput) + "\n");
					
					mkeyTextArea.append(Main.Mutation(intInput) + " ");
					mutationTextArea.append("Gen " + (a+1) + " | " + Arrays.toString(intInput) + "\n");
					//exit = Main.comparison(oriInput,intInput);
					//counter++;
				}
			}
		});
		encryptButton.setBounds(447, 42, 117, 29);
		frame.getContentPane().add(encryptButton);
	}
}
