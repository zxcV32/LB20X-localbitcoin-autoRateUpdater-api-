package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.security.Key;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Keys extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblSaved;
	private static final String ALGO = "AES";
	private static final byte[] keyValue=new byte[]{'3','y','u','3','!','C','2','K','Q','&','x','C','2','F','u','X'};
	String buff,tempUser = null,ApiauthKey = null,ApiauthKeySecret = null;
	/**
	 * Launch the application.
	 */
/*
 * 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Keys frame = new Keys();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Keys() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSaved = new JLabel("");
		lblSaved.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaved.setBounds(567, 16, 95, 16);
		contentPane.add(lblSaved);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(12, 13, 170, 22);
		contentPane.add(lblUsername);
		
		JLabel lblHmacKey = new JLabel("HMAC KEY:");
		lblHmacKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblHmacKey.setBounds(12, 48, 170, 22);
		contentPane.add(lblHmacKey);
		
		JLabel lblHmacSecret = new JLabel("HMAC SECRET:");
		lblHmacSecret.setHorizontalAlignment(SwingConstants.CENTER);
		lblHmacSecret.setBounds(12, 83, 170, 22);
		contentPane.add(lblHmacSecret);
		
		textField = new JTextField();
		textField.setBounds(194, 13, 322, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(194, 48, 322, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(194, 83, 322, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JTextPane txtpnHttpslocalbitcoinscomaccountsapi = new JTextPane();
		txtpnHttpslocalbitcoinscomaccountsapi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                try {
                    Desktop.getDesktop().browse(new URI("https://localbitcoins.com/accounts/api/"));
            } catch (Exception ex) {
                    //It looks like there's a problem
            }
			}
		});
		txtpnHttpslocalbitcoinscomaccountsapi.setText("To get your key go to this url https://localbitcoins.com/accounts/api/ \r\nGive permission read,write no need to give money and money_pin.The read permission lets this bot make authenticated request and write permission is only used when price equation is to be changed.");
		txtpnHttpslocalbitcoinscomaccountsapi.setEditable(false);
		txtpnHttpslocalbitcoinscomaccountsapi.setBounds(12, 118, 678, 122);
		contentPane.add(txtpnHttpslocalbitcoinscomaccountsapi);
		
		JButton btnSaveMyKeys = new JButton("save my keys locally");
		btnSaveMyKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter bw=new BufferedWriter(new FileWriter("temp"));
					tempUser=textField.getText().trim();
					ApiauthKey=textField_1.getText().trim();
					ApiauthKeySecret=textField_2.getText().trim();
					if(tempUser.length()>1)
						bw.write(encrypt(tempUser)+"\n");
					else {
						JOptionPane.showMessageDialog(null,"Input username!"); 
						throw new Exception();
					}
						
					if(ApiauthKey.length()>1)
						bw.write(encrypt(ApiauthKey)+"\n");
					else {
						JOptionPane.showMessageDialog(null,"Input HMAC KEY!"); 
						throw new Exception();
					}
					if(ApiauthKeySecret.length()>1)
						bw.write(encrypt(ApiauthKeySecret)+"\n");
					else {
						JOptionPane.showMessageDialog(null,"Input HMAC SECRET!"); 
						throw new Exception();
					}
					bw.close();
					lblSaved.setText("Saved");
					
				} catch (Exception e1) {
					lblSaved.setText(" ");
						JOptionPane.showMessageDialog(null,"Could not save keys make sure you have filled all fields and have write permission to disk!"); 
					e1.printStackTrace();
				}
			}
		});
		btnSaveMyKeys.setBounds(528, 47, 162, 25);
		contentPane.add(btnSaveMyKeys);
		
		try {
			BufferedReader br=new BufferedReader(new FileReader("temp"));
			try {
				if((buff=br.readLine())!=null)
					tempUser=buff;
				if((buff=br.readLine())!=null)
					ApiauthKey=buff;
				if((buff=br.readLine())!=null)
					ApiauthKeySecret=buff;
				while((buff=br.readLine())!=null)
					ApiauthKeySecret+=buff;
				br.close();
				if(tempUser.length()>1)
					tempUser=decrypt(tempUser);
				if(ApiauthKey.length()>1)
					ApiauthKey=decrypt(ApiauthKey);
				if(ApiauthKeySecret.length()>1)
					ApiauthKeySecret=decrypt(ApiauthKeySecret);
				
				textField.setText(tempUser);
				textField_1.setText(ApiauthKey);
				textField_2.setText(ApiauthKeySecret);
				
			} catch (Exception e) {
				lblSaved.setText(" ");
				System.out.println("something wrong with keys");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
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
