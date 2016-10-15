package CI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Label;
import java.awt.List;
import java.awt.Choice;

public class Main implements ActionListener{

	TextField weatherText, aircondText;
	static TextField coldResult;
	static List aircondList, weatherList, coldnessList, coldList, aircondTempList, weatherTempList;
	static Choice aircondChoice, weatherChoice;
	private JFrame frame;
	private Label label_3;

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
		frame.setBounds(100, 100, 427, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Alpha Coldness Fuzzy System");
		
		Button button = new Button("Calculate!");
		button.setBounds(10, 156, 390, 56);
		frame.getContentPane().add(button);
		button.addActionListener(this);
		
		Label AircondLabel = new Label("Air-cond Temp:");
		AircondLabel.setBounds(236, 72, 88, 22);
		frame.getContentPane().add(AircondLabel);
		
		aircondText = new TextField();
		aircondText.setBounds(330, 72, 70, 22);
		frame.getContentPane().add(aircondText);
		
		Label WeatherLabel = new Label("Weather Temp:");
		WeatherLabel.setBounds(236, 100, 88, 22);
		frame.getContentPane().add(WeatherLabel);
		
		weatherText = new TextField();
		weatherText.setBounds(330, 100, 70, 22);
		frame.getContentPane().add(weatherText);
		
		coldResult = new TextField();
		coldResult.setBounds(330, 128, 70, 22);
		frame.getContentPane().add(coldResult);
		coldResult.setEditable(false);
		
		aircondList = new List();
		aircondList.setEnabled(false);
		aircondList.setBounds(10, 250, 110, 60);
		frame.getContentPane().add(aircondList);
		
		Label label = new Label("Aircond Membership");
		label.setBounds(10, 222, 110, 22);
		frame.getContentPane().add(label);
		
		weatherList = new List();
		weatherList.setEnabled(false);
		weatherList.setBounds(139, 250, 110, 60);
		frame.getContentPane().add(weatherList);
		
		Label label_1 = new Label("Weather Membership");
		label_1.setBounds(140, 222, 110, 22);
		frame.getContentPane().add(label_1);
		
		coldList = new List();
		coldList.setBounds(10, 316, 390, 66);
		frame.getContentPane().add(coldList);
		
		aircondTempList = new List();
		aircondTempList.setEnabled(false);
		aircondTempList.setBounds(10, 6, 164, 60);
		frame.getContentPane().add(aircondTempList);
		
		weatherTempList = new List();
		weatherTempList.setEnabled(false);
		weatherTempList.setBounds(191, 6, 209, 60);
		frame.getContentPane().add(weatherTempList);
		
		coldnessList = new List();
		coldnessList.setEnabled(false);
		coldnessList.setBounds(278, 250, 110, 60);
		frame.getContentPane().add(coldnessList);
		
		Label label_2 = new Label("Coldness Membership");
		label_2.setBounds(278, 222, 122, 22);
		frame.getContentPane().add(label_2);
		
		label_3 = new Label("Coldness Level: ");
		label_3.setBounds(236, 128, 88, 22);
		frame.getContentPane().add(label_3);
		
		aircondChoice = new Choice();
		aircondChoice.setBounds(10, 72, 220, 20);
		frame.getContentPane().add(aircondChoice);
		
		weatherChoice = new Choice();
		weatherChoice.setBounds(10, 102, 220, 20);
		frame.getContentPane().add(weatherChoice);
		
		aircondTempList.add("Cold Temp: 16 <= x < 20");
		aircondTempList.add("Normal Temp: 17 < x < 24");
		aircondTempList.add("Hot Temp: 21 < x <= 25");
		
		weatherTempList.add("Raining Temp: 24 <= x < 28");
		weatherTempList.add("Cloudy Temp: 25 < x < 32");
		weatherTempList.add("Sunny Temp: 29 < x <= 33");
		
		aircondChoice.add("Select one (Optional)");
		aircondChoice.add("Cold");
		aircondChoice.add("Cold & Normal");
		aircondChoice.add("Normal");
		aircondChoice.add("Normal & Hot");
		aircondChoice.add("Hot");
		
		weatherChoice.add("Select one (Optional)");
		weatherChoice.add("Raining");
		weatherChoice.add("Raining & Cloudy");
		weatherChoice.add("Cloudy");
		weatherChoice.add("Cloudy & Sunny");
		weatherChoice.add("Sunny");
		
		aircondChoice.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				String choices = aircondChoice.getSelectedItem();
				
				switch (choices){
				case "Cold" :
					aircondText.setText(String.format("%.2f",doubleRandom(16,17)));
					break;
				case "Cold & Normal" :
					aircondText.setText(String.format("%.2f",doubleRandom(17.01,19.99)));
					break;
				case "Normal" :
					aircondText.setText(String.format("%.2f",doubleRandom(20,21)));
					break;
				case "Normal & Hot" :
					aircondText.setText(String.format("%.2f",doubleRandom(21.01,23.99)));
					break;
				case "Hot":
					aircondText.setText(String.format("%.2f",doubleRandom(24,25)));
					break;
				}
			}
		});
		
		weatherChoice.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				String choices = weatherChoice.getSelectedItem();
				
				switch (choices){
				case "Raining" :
					weatherText.setText(String.format("%.2f",doubleRandom(24,25)));
					break;
				case "Raining & Cloudy" :
					weatherText.setText(String.format("%.2f",doubleRandom(25.01,27.99)));
					break;
				case "Cloudy" :
					weatherText.setText(String.format("%.2f",doubleRandom(28,29)));
					break;
				case "Cloudy & Sunny" :
					weatherText.setText(String.format("%.2f",doubleRandom(29.01,31.99)));
					break;
				case "Sunny":
					weatherText.setText(String.format("%.2f",doubleRandom(32,33)));
					break;
				}
			}
		});
	}
	
	public Double doubleRandom(double start, double end){
		return ThreadLocalRandom.current().nextDouble(start,end);
	}
	
	public void actionPerformed(ActionEvent e){
		aircondList.removeAll();
		weatherList.removeAll();
		coldnessList.removeAll();
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