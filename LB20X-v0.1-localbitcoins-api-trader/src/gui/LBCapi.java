package gui;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.TextArea;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
public class LBCapi {

	private JFrame frame;
	private static JLabel lblUsername_1;
	private static JLabel lblTradeCounts;
	private static JLabel lblTradeCounts_1;
	private static JLabel lblPeople;
	private static JLabel label;
	private static JLabel lblPartners;
	private static JLabel lblCount ;
	private static JRadioButton rdbtnAd;
	private static JRadioButton rdbtnAd_1;
	private static JRadioButton rdbtnAd_2;
	private static JRadioButton rdbtnAd_3;
	private static JRadioButton rdbtnAd_4;
	private static JRadioButton rdbtnExistingAd;
	private static JRadioButton rdbtnExistingAd_1;
	private static JRadioButton rdbtnExistingAd_2;
	private static JRadioButton rdbtnExistingAd_3;
	private static JRadioButton rdbtnExistingAd_4;
	private static JTextArea txtrDontChangeMy;
	private static JButton btnSave;
	
	
	private final static String USER_AGENT = "Mozilla/5.0";
	private static String api_endpoint ;
	private static String host ="https://localbitcoins.com";
	private static String ApiauthKey;
	private static String ApiauthKeySecret;
	private static final String ALGO = "AES";
	private static final byte[] keyValue=new byte[]{'3','y','u','3','!','C','2','K','Q','&','x','C','2','F','u','X'};
	static String get_or_post_params_urlencoded = "?";
	static String message;
	static long ApiauthNonce=System.currentTimeMillis();
	static String hmac;
	static String response;
	static String username;
	static boolean changeRate=false;
	static String buyData[][]=new String[5][2];
	static String myAdsList[]=new String[5];
	static int position=1;
	private static int adChangePosition=-1;
	private static int adChangeId;
	private static double oldRateBelow = 0,oldRateAbove = 0;
	private static String presentUser;
	private static double safeLimit = 0;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private static JTextArea txtrConsole;
	private static JScrollPane scrollPane;
	private JMenu mnConfiguration;
	private JMenuItem mntmTimeConfiguration;
	private static JTextField textField;
	private static JLabel lblPleaseFillThis;
	private JLabel lblLbxvC;
	private JLabel lblCopyrightua;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LBCapi window = new LBCapi();
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
	public LBCapi() {
		initialize();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblPleaseFillThis = new JLabel("please fill this filed with only numbers!");
		lblPleaseFillThis.setBackground(Color.WHITE);
		lblPleaseFillThis.setForeground(Color.RED);
		lblPleaseFillThis.setBounds(401, 238, 375, 16);
		frame.getContentPane().add(lblPleaseFillThis);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1262, 26);
		frame.getContentPane().add(menuBar);
		
		JMenu mnKeys = new JMenu("Keys");
		menuBar.add(mnKeys);
		
		JMenuItem mntmManageKeys = new JMenuItem("Manage Keys");
		mntmManageKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				Thread keysFrameThread=new Thread(){
					public void run(){					
						Keys keysFrame = null;
						keysFrame = new Keys();
						keysFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						keysFrame.setVisible(true);
					}
				};
				keysFrameThread.start();
			}
		});
		mnKeys.add(mntmManageKeys);		
		JSeparator separator = new JSeparator();
		mnKeys.add(separator);
		
		JMenuItem mntmDeleteKeys = new JMenuItem("Delete Keys");
		mntmDeleteKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("temp");

					if(file.delete())
						JOptionPane.showMessageDialog(null,"Keys Deleted!");

				} catch (Exception e1) {					
					e1.printStackTrace();
				} 	

			}
		});
		mnKeys.add(mntmDeleteKeys);
		
		mnConfiguration = new JMenu("Configuration");

		menuBar.add(mnConfiguration);
		
		mntmTimeConfiguration = new JMenuItem("Time Configuration");
		mntmTimeConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread Config=new Thread(){
					public void run(){					
						Config conf = null;
						conf = new Config();
						conf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						conf.setVisible(true);
					}
				};
				Config.start();
			}
		});
		mnConfiguration.add(mntmTimeConfiguration);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "About Me", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(16, 39, 374, 215);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(6, 18, 135, 16);
		panel.add(lblUsername);
		
		JLabel lblFeedbackScore = new JLabel("Feedback Score:");
		lblFeedbackScore.setBounds(6, 134, 135, 16);
		panel.add(lblFeedbackScore);
		
		JLabel lblTrustedBy = new JLabel("Trusted By:");
		lblTrustedBy.setBounds(6, 105, 135, 16);
		panel.add(lblTrustedBy);
		
		JLabel lblUnconfirmed = new JLabel("Unconfirmed Trades:");
		lblUnconfirmed.setBounds(6, 76, 135, 16);
		panel.add(lblUnconfirmed);
		
		JLabel lblConfirmedTrades = new JLabel("Confirmed Trades:");
		lblConfirmedTrades.setBounds(6, 47, 135, 16);
		panel.add(lblConfirmedTrades);
		
		lblUsername_1 = new JLabel("username");
		lblUsername_1.setBounds(153, 18, 215, 16);
		panel.add(lblUsername_1);
		
		lblTradeCounts = new JLabel("trade counts");
		lblTradeCounts.setBounds(153, 47, 215, 16);
		panel.add(lblTradeCounts);
		
		lblTradeCounts_1 = new JLabel("trade counts");
		lblTradeCounts_1.setBounds(153, 76, 215, 16);
		panel.add(lblTradeCounts_1);
		
		lblPeople = new JLabel("people");
		lblPeople.setBounds(153, 105, 215, 16);
		panel.add(lblPeople);
		
		label = new JLabel("%");
		label.setBounds(153, 134, 215, 16);
		panel.add(label);
		
		JLabel lblTradingPartners = new JLabel("Trading Partners:");
		lblTradingPartners.setBounds(6, 163, 135, 16);
		panel.add(lblTradingPartners);
		
		JLabel lblBlockedBy = new JLabel("Blocked By:");
		lblBlockedBy.setBounds(6, 192, 135, 16);
		panel.add(lblBlockedBy);
		
		lblPartners = new JLabel("partners");
		lblPartners.setBounds(153, 163, 215, 16);
		panel.add(lblPartners);
		
		lblCount = new JLabel("count");
		lblCount.setBounds(153, 192, 215, 16);
		panel.add(lblCount);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Over Throw", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(16, 267, 374, 170);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		rdbtnAd = new JRadioButton("wait for online ad 1");
		rdbtnAd.setEnabled(false);
		rdbtnAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				position=1;
				oldRateBelow = 0;oldRateAbove = 0;
			}
		});
		rdbtnAd.setBounds(6, 18, 360, 25);
		panel_1.add(rdbtnAd);
		rdbtnAd.setSelected(true);
		buttonGroup.add(rdbtnAd);
		
		rdbtnAd_1 = new JRadioButton("wait for online ad 2");
		rdbtnAd_1.setEnabled(false);
		rdbtnAd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				position=2;
				oldRateBelow = 0;oldRateAbove = 0;
			}
		});
		rdbtnAd_1.setBounds(6, 48, 360, 25);
		panel_1.add(rdbtnAd_1);
		buttonGroup.add(rdbtnAd_1);
		
		rdbtnAd_2 = new JRadioButton("wait for online ad 3");
		rdbtnAd_2.setEnabled(false);
		rdbtnAd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				position=3;
				oldRateBelow = 0;oldRateAbove = 0;
			}
		});
		rdbtnAd_2.setBounds(6, 78, 360, 25);
		panel_1.add(rdbtnAd_2);
		buttonGroup.add(rdbtnAd_2);
		
		rdbtnAd_3 = new JRadioButton("wait for online ad 4");
		rdbtnAd_3.setEnabled(false);
		rdbtnAd_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				position=4;
				oldRateBelow = 0;oldRateAbove = 0;
			}
		});
		rdbtnAd_3.setBounds(6, 108, 360, 25);
		panel_1.add(rdbtnAd_3);
		buttonGroup.add(rdbtnAd_3);
		
		rdbtnAd_4 = new JRadioButton("wait for online ad 5");
		rdbtnAd_4.setEnabled(false);
		rdbtnAd_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				position=5;
				oldRateBelow = 0;oldRateAbove = 0;
			}
		});
		rdbtnAd_4.setBounds(6, 138, 360, 25);
		panel_1.add(rdbtnAd_4);
		buttonGroup.add(rdbtnAd_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "My Ads - select the to be updated", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(402, 267, 374, 170);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		rdbtnExistingAd = new JRadioButton("Existing ad 1");
		rdbtnExistingAd.setEnabled(false);
		rdbtnExistingAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			adChangePosition=1;
			}
		});
		rdbtnExistingAd.setBounds(6, 18, 360, 25);
		panel_2.add(rdbtnExistingAd);
		buttonGroup_1.add(rdbtnExistingAd);
		
		rdbtnExistingAd_1 = new JRadioButton("Existing ad 2");
		rdbtnExistingAd_1.setEnabled(false);
		rdbtnExistingAd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adChangePosition=2;
			}
		});
		rdbtnExistingAd_1.setBounds(6, 48, 360, 25);
		panel_2.add(rdbtnExistingAd_1);
		buttonGroup_1.add(rdbtnExistingAd_1);
		
		rdbtnExistingAd_2 = new JRadioButton("Existing ad 3");
		rdbtnExistingAd_2.setEnabled(false);
		rdbtnExistingAd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adChangePosition=3;
			}
		});
		rdbtnExistingAd_2.setBounds(6, 78, 360, 25);
		panel_2.add(rdbtnExistingAd_2);
		buttonGroup_1.add(rdbtnExistingAd_2);
		
		rdbtnExistingAd_3 = new JRadioButton("Existing ad 4");
		rdbtnExistingAd_3.setEnabled(false);
		rdbtnExistingAd_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adChangePosition=4;
			}
		});
		rdbtnExistingAd_3.setBounds(6, 108, 360, 25);
		panel_2.add(rdbtnExistingAd_3);
		buttonGroup_1.add(rdbtnExistingAd_3);
		
		rdbtnExistingAd_4 = new JRadioButton("Existing ad 5");
		rdbtnExistingAd_4.setEnabled(false);
		rdbtnExistingAd_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adChangePosition=5;
			}
		});
		rdbtnExistingAd_4.setBounds(6, 138, 360, 25);
		panel_2.add(rdbtnExistingAd_4);
		buttonGroup_1.add(rdbtnExistingAd_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Console", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(16, 450, 760, 210);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 15, 736, 182);
		panel_3.add(scrollPane);
		
		txtrConsole = new JTextArea();
		txtrConsole.setEditable(false);
		txtrConsole.setLineWrap(true);
		scrollPane.setViewportView(txtrConsole);
		txtrConsole.setDisabledTextColor(new Color(64, 64, 64));
		txtrConsole.setBackground(Color.BLACK);
		txtrConsole.setForeground(Color.LIGHT_GRAY);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setBounds(401, 203, 160, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtrDontChangeMy = new JTextArea();
		txtrDontChangeMy.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtrDontChangeMy.setWrapStyleWord(true);
		txtrDontChangeMy.setBackground(Color.LIGHT_GRAY);
		txtrDontChangeMy.setEditable(false);
		txtrDontChangeMy.setLineWrap(true);
		txtrDontChangeMy.setText("Don't change my rate if the rate on lbc for selected position goes below:");
		txtrDontChangeMy.setBounds(401, 145, 388, 53);
		frame.getContentPane().add(txtrDontChangeMy);
		
		JLabel lblInr = new JLabel("INR");
		lblInr.setBounds(573, 206, 56, 16);
		frame.getContentPane().add(lblInr);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					safeLimit=Double.parseDouble(textField.getText());
					lblPleaseFillThis.setText("saved");
				} catch (NumberFormatException e) {
					e.printStackTrace();					
					lblPleaseFillThis.setText("please fill this filed with only numbers!");
				}

			}
		});
		btnSave.setBounds(641, 202, 97, 25);
		frame.getContentPane().add(btnSave);
		
		lblCopyrightua = new JLabel("Copyright \u00A9 2017 zxcV32 | Email: c34r534w@gmail.com");
		lblCopyrightua.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyrightua.setBounds(402, 116, 387, 16);
		frame.getContentPane().add(lblCopyrightua);
		
		lblLbxvC = new JLabel("LB20X-v0.1 c1");
		lblLbxvC.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblLbxvC.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbxvC.setBounds(401, 39, 375, 93);
		frame.getContentPane().add(lblLbxvC);
	}
	
	//You need to enter username and first 20 characters of HAMC key below in 2d matric to verify registration and stop piracy
	private static int isRegistered(String username,String key) {		
		String data[][]= {
				{"username1","username2"},
				{"first20characterofHMACforusername1","first20characterofHMACforusername2"}
		};
		int i;
		int registeredUsers=2;		//update this accourding to registered user
		char response=0;
		/*
		 * 0=user not registered
		 * 1=user registered but key does not match
		 * 2=all good
		 * 
		 * */ 
		try {
			for(i=0;i<registeredUsers;++i) {
				if(username.equals(data[0][i])) {
					response=1;
					if(key.substring(0,20).equals(data[1][i]))
						response=2;
					else
						return response;
					return response;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			txtrConsole.append("HMAC keys lengths are not in expected length,please update them in keys menu.\n");
		}
		return response;
	}

	private static void driver() {
		BufferedReader br = null;
		Boolean loop=true;
		String read,tempUser = null;
		long cycleTime = 5;
		int executed=10;
		boolean isProperSafeLimit=true;
		while(loop) {
			try {
				br = new BufferedReader(new FileReader("temp"));
				try {
					if((read=br.readLine())!=null)
						tempUser=read;
					if((read=br.readLine())!=null)
						ApiauthKey=read;
					if((read=br.readLine())!=null)
						ApiauthKeySecret=read;
					while((read=br.readLine())!=null)
						ApiauthKeySecret+=read;
				} catch (Exception e) {
					txtrConsole.setText("Some thing's wrong with your HMAC keys.please update them in 'manage keys' from menu.");
					e.printStackTrace();
				}
				txtrConsole.append("decrypting username...\n");
				tempUser=decrypt(tempUser);
				txtrConsole.append("decrypting HMAC key...\n");				
				ApiauthKey=decrypt(ApiauthKey);
				txtrConsole.append("decrypting HMAC Secret...\n");				
				ApiauthKeySecret=decrypt(ApiauthKeySecret);
				
				
//				System.out.println(tempUser);
//				System.out.println(ApiauthKey);
//				System.out.println(ApiauthKeySecret);
//				System.out.println("-----------------------------");
				loop=false;
				br.close();
			} catch (Exception e1) {
				txtrConsole.setText("HMAC Keys not found!\ngo to 'manage keys' from menu and save your username and HMAC keys.");
			}
		}
		
		if(isRegistered(tempUser,ApiauthKey)==0) {
			txtrConsole.append("username: "+tempUser+" not registered...\ncontact Ashwani Sharma.\nhttps://www.ashwanisharma.me\n");
			try {
				Desktop.getDesktop().browse(new URI("https://www.ashwanisharma.me"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			while(true);
		}
		else if(isRegistered(tempUser,ApiauthKey)==1) {
			txtrConsole.append("username: "+tempUser+" is registered but...\nHMAC key starting with "+ApiauthKey.substring(0, 20)+" does not exists by this username\nContact Ashwani Sharma\n");
			try {
				Desktop.getDesktop().browse(new URI("https://www.ashwanisharma.me"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(true);
		}
				if(isRegistered(tempUser,ApiauthKey)==2)		
			txtrConsole.append("username: "+tempUser+" is registered\nHMAC key starting with "+ApiauthKey.substring(0, 20)+" exists\nyou are ready to go!\n");
		
		while(true) {			
			try {								
				if(executed==10) {					
					try {
						BufferedReader br1=new BufferedReader(new FileReader("tempTime"));
						try {
							cycleTime=Long.parseLong(br1.readLine());
							br1.close();				
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {				
						e.printStackTrace();
					}
					
					txtrConsole.append("updating user info!\n");
					txtrConsole.setCaretPosition(txtrConsole.getDocument().getLength());
					api_endpoint="/api/myself/";
					ApiauthNonce=System.currentTimeMillis();
					me(api_endpoint,Long.toString(ApiauthNonce));
					executed=0;
				}
				++executed;
								
				txtrConsole.append("downloading buy ads...\n");
				txtrConsole.setCaretPosition(txtrConsole.getDocument().getLength());
				api_endpoint="/buy-bitcoins-online/inr/.json";
				ApiauthNonce=System.currentTimeMillis();
				get_ads(api_endpoint, Long.toString(ApiauthNonce));
				
				username=tempUser;
				presentUser = buyData[position-1][0];
				presentUser=presentUser.substring(0, buyData[position-1][0].indexOf(' '));
				
				if(!presentUser.equals(username)) 
					changeRate=true;
				else
					txtrConsole.append("no need to change rate...\n");
				
				if (position==1 && oldRateBelow < Double.parseDouble(buyData[1][1])) {
					changeRate=true;
				}
				else if ( position>1 ) {
					if( oldRateAbove > Double.parseDouble(buyData[position-1][1]))
						changeRate=true;
				}

				lblPleaseFillThis.setText("");
				
				rdbtnExistingAd.setBackground(new Color(240, 240, 240));
				rdbtnExistingAd_1.setBackground(new Color(240, 240, 240));
				rdbtnExistingAd_2.setBackground(new Color(240, 240, 240));
				rdbtnExistingAd_3.setBackground(new Color(240, 240, 240));
				rdbtnExistingAd_4.setBackground(new Color(240, 240, 240));
				
				if(Double.parseDouble(buyData[position-1][1]) < safeLimit) {
					changeRate=false;
					txtrConsole.append("rate below safe limit..not updating rate...\n");
				}
				
				if(changeRate) {
					changeRate=false;
					txtrConsole.append("need to change your rates...\n");
					txtrConsole.setCaretPosition(txtrConsole.getDocument().getLength());
					txtrConsole.append("downloading your ads...\n");
					txtrConsole.setCaretPosition(txtrConsole.getDocument().getLength());
					api_endpoint="/api/ads/";
					ApiauthNonce=System.currentTimeMillis();
					adChangeId=getMyAdsList(api_endpoint,Long.toString(ApiauthNonce));			

					txtrConsole.append("updating your ad id:"+adChangeId+"\n");
					txtrConsole.setCaretPosition(txtrConsole.getDocument().getLength());
					api_endpoint="/api/ad-equation/"+adChangeId+"/";
					ApiauthNonce=System.currentTimeMillis();			

					changeBuyRate(api_endpoint,Long.toString(ApiauthNonce));		
					if(adChangePosition==1)
						rdbtnExistingAd.setBackground(new Color(0, 255, 102));
					else if(adChangePosition==2)
						rdbtnExistingAd_1.setBackground(new Color(0, 255, 102));
					else if(adChangePosition==3)
						rdbtnExistingAd_2.setBackground(new Color(0, 255, 102));
					else if(adChangePosition==4)
						rdbtnExistingAd_3.setBackground(new Color(0, 255, 102));
					else if(adChangePosition==5)
						rdbtnExistingAd_4.setBackground(new Color(0, 255, 102));
					txtrConsole.append("rate updated...");
					txtrConsole.setCaretPosition(txtrConsole.getDocument().getLength());
					api_endpoint="/api/ads/";
					ApiauthNonce=System.currentTimeMillis();
					adChangeId=getMyAdsList(api_endpoint,Long.toString(ApiauthNonce));						
					txtrConsole.setCaretPosition(txtrConsole.getDocument().getLength());					
				}
				
				if(position==1) {
					oldRateBelow=Double.parseDouble(buyData[1][1]);
				}
				else if (position>1) {
					oldRateAbove=Double.parseDouble(buyData[position-1][1]);							
				}
				txtrConsole.append("waiting for next cycle...\n");
				try {
					Thread.sleep(cycleTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				txtrConsole.append("there is something wrong with your keys or server.");
				e.printStackTrace();
			}
		}			
	}
	  private static int getMyAdsList(String endpoint, String nonce) throws Exception {
			int i;long ad_count=0;
			JSONObject profile;
		    int adids[]=new int[5];
		    String adText[]=new String[5];
			message = nonce + ApiauthKey + endpoint;		
			hmac = hmacDigest(message, ApiauthKeySecret,"HmacSHA256").toUpperCase();		
			response = sendGet(endpoint,"",hmac);
			JSONParser parser = new JSONParser();
	        Object obj = parser.parse(response);
	        JSONObject obj2 = (JSONObject)obj;   
	        obj2 = (JSONObject)obj2.get("data");
	        ad_count= (long) obj2.get("ad_count");
	        JSONArray arr = (JSONArray)obj2.get("ad_list");
	        for(i=0;i<ad_count;++i) {
	        	profile=(JSONObject)((JSONObject)arr.get(i)).get("data");
	        	adids[i]=Integer.parseInt((profile.get("ad_id")).toString());
	        	adText[i]=((boolean) profile.get("visible")?"Enabled ":"Disabled ")+" "+profile.get("trade_type")+" "+profile.get("online_provider")+" "+profile.get("price_equation");
	        }	        
	        if(ad_count==1) {
	        	rdbtnExistingAd.setEnabled(true);
	        	rdbtnExistingAd.setText(adText[0]);
	        	
	        }
	        else if(ad_count==2) {
	        	rdbtnExistingAd.setEnabled(true);
	        	rdbtnExistingAd_1.setEnabled(true);
	        	rdbtnExistingAd.setText(adText[0]);
	        	rdbtnExistingAd_1.setText(adText[1]);		        
	        }
	        else if(ad_count==3) {
	        	rdbtnExistingAd.setEnabled(true);
	        	rdbtnExistingAd_1.setEnabled(true);
	        	rdbtnExistingAd_2.setEnabled(true);
	        	rdbtnExistingAd.setText(adText[0]);
	         	rdbtnExistingAd_1.setText(adText[1]);		        
	         	rdbtnExistingAd_2.setText(adText[2]);		        
	 	        }
	        else if(ad_count==4) {
	        	rdbtnExistingAd.setEnabled(true);
	        	rdbtnExistingAd_1.setEnabled(true);
	        	rdbtnExistingAd_2.setEnabled(true);
	        	rdbtnExistingAd_3.setEnabled(true);
	        	rdbtnExistingAd.setText(adText[0]);
	        	rdbtnExistingAd_1.setText(adText[1]);		        
	          	rdbtnExistingAd_2.setText(adText[2]);		        
	          	rdbtnExistingAd_3.setText(adText[3]);		        
	        }
	        else if(ad_count==4) {
	        	rdbtnExistingAd.setEnabled(true);
	        	rdbtnExistingAd_1.setEnabled(true);
	        	rdbtnExistingAd_2.setEnabled(true);
	        	rdbtnExistingAd_3.setEnabled(true);
	        	rdbtnExistingAd_4.setEnabled(true);
	        	rdbtnExistingAd.setText(adText[0]);
	        	rdbtnExistingAd_1.setText(adText[1]);		        
	          	rdbtnExistingAd_2.setText(adText[2]);		        
	          	rdbtnExistingAd_3.setText(adText[3]);		        
	          	rdbtnExistingAd_4.setText(adText[4]);		        
		        }
	        String temp=txtrConsole.getText();
	        while(adChangePosition<0)
	        	txtrConsole.setText(temp+"please select which ad you want to update...\n");
	        return adids[adChangePosition-1];
	}

	private static void changeBuyRate(String endpoint, String nonce) throws Exception {
		float rateMax=4,rateMin=1,difference = 0,newRate=0;
		if(rateMax-rateMin>0) {
			difference = (float) (Math.random()*(rateMax-rateMin)+rateMin);
			if(position==1) 
				newRate=Float.parseFloat(buyData[0][1])-difference;
			else 
				newRate=Float.parseFloat(buyData[position-1][1])-difference;
		}
		message = nonce + ApiauthKey + endpoint + "price_equation="+Float.toString(newRate);		
		hmac = hmacDigest(message, ApiauthKeySecret,"HmacSHA256").toUpperCase();
		sendPost(endpoint,"price_equation="+Float.toString(newRate),hmac);	
		txtrConsole.append("new Rate:"+Float.toString(newRate)+"\n");
			
	}

	public static String hmacDigest(String msg, String keyString, String algo) {
		    String digest = null;
		    try {
		      SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
		      Mac mac = Mac.getInstance(algo);
		      mac.init(key);
		      
		      byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

		      StringBuffer hash = new StringBuffer();
		      for (int i = 0; i < bytes.length; i++) {
		        String hex = Integer.toHexString(0xFF & bytes[i]);
		        if (hex.length() == 1) {
		          hash.append('0');
		        }
		        hash.append(hex);
		      }
		      digest = hash.toString();
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    return digest;
	  }
	

	static void me(String endpoint,String nonce) throws Exception {	
		message = nonce + ApiauthKey + endpoint;		
		hmac = hmacDigest(message, ApiauthKeySecret,"HmacSHA256").toUpperCase();		
		response = sendGet(endpoint,"",hmac);
		 JSONParser parser = new JSONParser();
	     try{
	        Object obj = parser.parse(response);
	        JSONObject obj2 = (JSONObject)obj;   
	        obj2 = (JSONObject)obj2.get("data");
	        username=(String) obj2.get("username");
	        lblUsername_1.setText(username);
	        lblTradeCounts.setText((String) obj2.get("confirmed_trade_count_text"));
	        lblTradeCounts_1.setText(Long.toString((long)obj2.get("feedbacks_unconfirmed_count")));
	        lblPeople.setText(Long.toString((long)obj2.get("trusted_count")));
	        lblPartners.setText(Long.toString((long)obj2.get("trading_partners_count")));
	        lblCount.setText(Long.toString((long)obj2.get("blocked_count"))); 	        
	        label.setText(Long.toString((long)obj2.get("feedback_score")) +"% from "+Long.toString((long)obj2.get("feedback_count")) +" feedbacks");
	     }catch(ParseException pe){
	    	 pe.printStackTrace();
	     }
	     
	}
	static void get_ads(String endpoint,String nonce) throws Exception {
		int i,arrPointer=0;
		message = nonce + ApiauthKey + endpoint;	
		hmac = hmacDigest(message, ApiauthKeySecret,"HmacSHA256").toUpperCase();		
		response = sendGet(endpoint,"",hmac);
		 JSONParser parser = new JSONParser();
	     try{
	        Object obj = parser.parse(response);
	        JSONObject obj2 = (JSONObject)obj;
	        JSONArray arr ;
      	obj2 = (JSONObject)obj2.get("data");
      	arr = (JSONArray)obj2.get("ad_list");
	        for(i=0;arrPointer<5;++i) {
	        	obj2 = (JSONObject) ((JSONObject)arr.get(i)).get("data");
	        	if((obj2.get("online_provider")).equals("NATIONAL_BANK") || (obj2.get("online_provider")).equals("BANK_TRANSFER_IMPS")) {
	        		buyData[arrPointer][0]=(String) ((JSONObject)obj2.get("profile")).get("name");
	        		buyData[arrPointer][1]=(String) obj2.get("temp_price");
	        		++arrPointer;
	        	}
	        	else {
	        		continue;	
	        	}
	        }
	       }catch(ParseException pe){
	    	   pe.printStackTrace();
	     } 
			rdbtnAd.setEnabled(true);
			rdbtnAd_1.setEnabled(true);
			rdbtnAd_2.setEnabled(true);
			rdbtnAd_3.setEnabled(true);
			rdbtnAd_4.setEnabled(true);
	     rdbtnAd.setText(buyData[0][0]+" "+buyData[0][1]+" INR");
	     rdbtnAd_1.setText(buyData[1][0]+" "+buyData[1][1]+" INR");
	     rdbtnAd_2.setText(buyData[2][0]+" "+buyData[2][1]+" INR");
	     rdbtnAd_3.setText(buyData[3][0]+" "+buyData[3][1]+" INR");
	     rdbtnAd_4.setText(buyData[4][0]+" "+buyData[4][1]+" INR");
	     
	}
	private static String sendGet(String apiEndpoint,String params,String sig) throws Exception {

		URL obj = new URL(host+apiEndpoint);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Apiauth-key", ApiauthKey);
		con.setRequestProperty("Apiauth-Nonce", Long.toString(ApiauthNonce));
		con.setRequestProperty("Apiauth-Signature", sig);
		
//		System.out.println("User-Agent: "+USER_AGENT);
//		System.out.println("Apiauth-key: "+ApiauthKey);
//		System.out.println("Apiauth-Nonce: "+Long.toString(ApiauthNonce));
//		System.out.println("Apiauth-Signature: "+sig);
//		
		int responseCode = con.getResponseCode();
		if(responseCode!=200)
			txtrConsole.append("error code: "+responseCode+"\n");
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return(response.toString());
	}

	// HTTP POST request
	private static void sendPost(String apiEndpoint,String params,String sig) throws Exception {
		URL obj = new URL(host+apiEndpoint);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		con.setRequestProperty("Apiauth-key", ApiauthKey);
		con.setRequestProperty("Apiauth-Nonce", Long.toString(ApiauthNonce));
		con.setRequestProperty("Apiauth-Signature", sig);
		
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString( params.length() ));
		con.setUseCaches( false );
		
//		System.out.println("User-Agent: "+USER_AGENT);
//		System.out.println("Apiauth-key: "+ApiauthKey);
//		System.out.println("Apiauth-Nonce: "+Long.toString(ApiauthNonce));
//		System.out.println("Apiauth-Signature: "+sig);
//		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(params);
		wr.flush();
		wr.close();

		 String urlPsarameters ="fName=" + URLEncoder.encode("???", "UTF-8") +"&lName=" + URLEncoder.encode("???", "UTF-8");
		
		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'POST' request to URL : " + apiEndpoint);
//		System.out.println("Post parameters : " + params);
//		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}

	
	
	   public static String encrypt(String data) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encVal = c.doFinal(data.getBytes());
	        return new BASE64Encoder().encode(encVal);
	    }
	   public static String decrypt(String encryptedData) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.DECRYPT_MODE, key);
	        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
	        byte[] decValue = c.doFinal(decordedValue);
	        return new String(decValue);
	    }
	    private static Key generateKey() throws Exception {
	        return new SecretKeySpec(keyValue, ALGO);
	    }
	    
	    
}
