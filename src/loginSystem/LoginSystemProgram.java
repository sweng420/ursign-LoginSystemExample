package loginSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import databasePackage.dbClass;



public class LoginSystemProgram {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSystemProgram window = new LoginSystemProgram();
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
	public LoginSystemProgram() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginTitle = new JLabel("Login Portal");
		lblLoginTitle.setBounds(215, 11, 64, 14);
		frame.getContentPane().add(lblLoginTitle);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(59, 84, 54, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(59, 131, 64, 14);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(130, 81, 119, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(130, 129, 119, 17);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get text field values entered by user
				@SuppressWarnings("deprecation")
				String password = txtPassword.getText();
				String username = txtUsername.getText();
				
				//Launch database interfacing calss
				dbClass db = new dbClass();
				
				//Check if username and password match values in database
				boolean loginSucc = db.checkLoginDetails(username, password);
				
				//If logic is successful
				if (loginSucc == true ){
					//Clear all text fields
					txtPassword.setText(null);
					txtUsername.setText(null);
					//Inform user of successful login
					JOptionPane.showMessageDialog(null, "Login Successful");
				
				}
				//If login unsuccessful
				else if (loginSucc == false){
					JOptionPane.showMessageDialog(null, "Invalid Login", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnLogin.setBounds(24, 215, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Clear all text fields
				txtUsername.setText(null);
				txtPassword.setText(null);
				
			}
		});
		btnClear.setBounds(140, 215, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Exit program
				JFrame frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm exit?", "Login Systems", 
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}	
			}
		});
		btnQuit.setBounds(256, 215, 89, 23);
		frame.getContentPane().add(btnQuit);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Launch registration window
				newUserRegister.main(null);
			}
		});
		btnRegister.setBounds(366, 215, 89, 23);
		frame.getContentPane().add(btnRegister);
	}
}
