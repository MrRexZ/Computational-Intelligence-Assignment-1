import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.TextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.List;

public class Main implements ActionListener{

	TextField weatherText;
	TextField aircondText;
	static Label aircondSetLabel;
	static TextField coldResult;
	static List aircondList;
	static List weatherList;
	static List coldList;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Alpha Coldness Fuzzy System");
		
		Button button = new Button("Calculate!");
		button.setBounds(165, 59, 70, 46);
		frame.getContentPane().add(button);
		button.addActionListener(this);
		
		Label AircondLabel = new Label("Air-cond Temp:");
		AircondLabel.setBounds(10, 10, 88, 22);
		frame.getContentPane().add(AircondLabel);
		
		aircondText = new TextField();
		aircondText.setBounds(104, 10, 70, 22);
		frame.getContentPane().add(aircondText);
		
		Label WeatherLabel = new Label("Outside Weather Temp:");
		WeatherLabel.setBounds(191, 10, 133, 22);
		frame.getContentPane().add(WeatherLabel);
		
		weatherText = new TextField();
		weatherText.setBounds(330, 10, 70, 22);
		frame.getContentPane().add(weatherText);
		
		aircondSetLabel = new Label("");
		aircondSetLabel.setBounds(208, 10, 116, 22);
		frame.getContentPane().add(aircondSetLabel);
		
		coldResult = new TextField();
		coldResult.setBounds(165, 111, 70, 22);
		frame.getContentPane().add(coldResult);
		coldResult.setEditable(false);
		
		aircondList = new List();
		aircondList.setEnabled(false);
		aircondList.setBounds(10, 73, 110, 60);
		frame.getContentPane().add(aircondList);
		
		Label label = new Label("Aircond Memberships");
		label.setBounds(10, 43, 126, 22);
		frame.getContentPane().add(label);
		
		weatherList = new List();
		weatherList.setEnabled(false);
		weatherList.setBounds(290, 73, 110, 60);
		frame.getContentPane().add(weatherList);
		
		Label label_1 = new Label("Weather Memberships");
		label_1.setBounds(274, 43, 126, 22);
		frame.getContentPane().add(label_1);
		
		coldList = new List();
		coldList.setBounds(10, 147, 390, 104);
		frame.getContentPane().add(coldList);
	}
	
	public void actionPerformed(ActionEvent e){
		
		aircondList.removeAll();
		weatherList.removeAll();
		coldList.removeAll();
		
		try{
			String aircondStringValue = aircondText.getText();
			String weatherStringValue = weatherText.getText();
			
			double aircondValue = Double.parseDouble(aircondStringValue);
			double weatherValue = Double.parseDouble(weatherStringValue);
			
			new Rules(new AircondGraph(aircondValue),new WeatherGraph(weatherValue));
		}
		catch (NumberFormatException error){
			JOptionPane.showMessageDialog(null, "Please fill all the inputs","Empty Inputs!",1);
		}
	}
}
