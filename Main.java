import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Label;
import java.awt.List;

public class Main implements ActionListener{

	TextField weatherText;
	TextField aircondText;
	static TextField coldResult;
	static List aircondList;
	static List weatherList;
	static List coldList;
	private JFrame frame;
	static List aircondTempList;
	static List weatherTempList;

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
		frame.setBounds(100, 100, 427, 312);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Alpha Coldness Fuzzy System");
		
		Button button = new Button("Calculate!");
		button.setBounds(165, 116, 70, 46);
		frame.getContentPane().add(button);
		button.addActionListener(this);
		
		Label AircondLabel = new Label("Air-cond Temp:");
		AircondLabel.setBounds(10, 72, 88, 22);
		frame.getContentPane().add(AircondLabel);
		
		aircondText = new TextField();
		aircondText.setBounds(104, 72, 70, 22);
		frame.getContentPane().add(aircondText);
		
		Label WeatherLabel = new Label("Outside Weather Temp:");
		WeatherLabel.setBounds(191, 72, 133, 22);
		frame.getContentPane().add(WeatherLabel);
		
		weatherText = new TextField();
		weatherText.setBounds(330, 72, 70, 22);
		frame.getContentPane().add(weatherText);
		
		coldResult = new TextField();
		coldResult.setBounds(165, 168, 70, 22);
		frame.getContentPane().add(coldResult);
		coldResult.setEditable(false);
		
		aircondList = new List();
		aircondList.setEnabled(false);
		aircondList.setBounds(10, 130, 110, 60);
		frame.getContentPane().add(aircondList);
		
		Label label = new Label("Aircond Memberships");
		label.setBounds(10, 100, 126, 22);
		frame.getContentPane().add(label);
		
		weatherList = new List();
		weatherList.setEnabled(false);
		weatherList.setBounds(290, 130, 110, 60);
		frame.getContentPane().add(weatherList);
		
		Label label_1 = new Label("Weather Memberships");
		label_1.setBounds(274, 100, 126, 22);
		frame.getContentPane().add(label_1);
		
		coldList = new List();
		coldList.setBounds(10, 196, 390, 66);
		frame.getContentPane().add(coldList);
		
		aircondTempList = new List();
		aircondTempList.setEnabled(false);
		aircondTempList.setBounds(10, 6, 164, 60);
		frame.getContentPane().add(aircondTempList);
		
		weatherTempList = new List();
		weatherTempList.setEnabled(false);
		weatherTempList.setBounds(191, 6, 209, 60);
		frame.getContentPane().add(weatherTempList);
		
		aircondTempList.add("Cold Temp: x < 20");
		aircondTempList.add("Normal Temp: 17 < x < 24");
		aircondTempList.add("Hot Temp: x > 21");
		
		weatherTempList.add("Raining Temp: x < 28");
		weatherTempList.add("Cloudy Temp: 25 < x < 32");
		weatherTempList.add("Hot Temp: x > 29");
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
