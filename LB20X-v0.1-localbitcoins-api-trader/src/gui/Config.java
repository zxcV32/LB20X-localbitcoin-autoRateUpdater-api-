package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Config extends JFrame {

	private JPanel contentPane;
	private JLabel lblSeconds;
	private static JSlider slider;
	/**
	 * Launch the application.
	 */
/*
 * 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Config frame = new Config();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 * */

	/**
	 * Create the frame.
	 */
	public Config() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSeconds = new JLabel("5 seconds");
		lblSeconds.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSeconds.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeconds.setBounds(274, 91, 114, 62);
		contentPane.add(lblSeconds);
		
		JLabel lblCompleteCycleTime = new JLabel("Wait between cycles:");
		lblCompleteCycleTime.setBounds(12, 13, 249, 16);
		contentPane.add(lblCompleteCycleTime);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSeconds.setText((Integer.toString(slider.getValue())+" seconds"));
			}
		});
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinimum(1);
		slider.setValue(5);
		slider.setMaximum(15);
		slider.setMajorTickSpacing(1);
		slider.setBounds(164, 13, 256, 45);
		contentPane.add(slider);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "1 cycle includes these steps", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(6, 75, 220, 112);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUserInfoUpdate = new JLabel("1) user info update");
		lblUserInfoUpdate.setBounds(12, 26, 196, 16);
		panel.add(lblUserInfoUpdate);
		
		JLabel lblOnlineAds = new JLabel("2) download online ads");
		lblOnlineAds.setBounds(12, 55, 196, 16);
		panel.add(lblOnlineAds);
		
		JLabel lblGetYour = new JLabel("3) get your ad and update them");
		lblGetYour.setBounds(12, 84, 208, 16);
		panel.add(lblGetYour);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter bw=new BufferedWriter(new FileWriter("tempTime"));
					bw.write(Integer.toString(slider.getValue()));
					bw.close();
					lblSeconds.setText("Saved!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(164, 215, 97, 25);
		contentPane.add(btnSave);
		
		JLabel lblUserInfoWill = new JLabel("user info will be updated after every 10 cycles");
		lblUserInfoWill.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserInfoWill.setBounds(12, 186, 408, 16);
		contentPane.add(lblUserInfoWill);
		Thread drive=new Thread(){
			public void run(){
				try {
					driver();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		drive.start();
	}
	private static void driver() {
		try {
			BufferedReader br=new BufferedReader(new FileReader("tempTime"));
			try {
				slider.setValue(Integer.parseInt(br.readLine()));
				br.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
