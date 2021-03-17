package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import gcm.dao.PatientDaoJDBC;
import gcm.model.Patient;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import java.awt.Panel;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldNSS;
	private JTextField textFieldAge;
	private JTable table;
	String[] colonnes= {"Nom","Prénom","Adresse","N° SS","Age","Ville"};
	String[][] donnees= {{"LGS","Farid","rue de joie","1010120304054","21","LILLE"}};
	
	JPanel panelAjoutPatient;
	JPanel panelConsultation;
	JScrollPane scrollPane;
	int nss;
	
	String ville;
	String adresse;
	/**
	 * @wbp.nonvisual location=18,-31
	 */
	private final JToolBar toolBar = new JToolBar();
	/**
	 * @wbp.nonvisual location=51,609
	 */
	private final JButton buttonAdd = new JButton("Ajouter");
	/**
	 * @wbp.nonvisual location=331,619
	 */
	private final JButton buttonShow = new JButton("Afficher");
	/**
	 * Launch the application.
	 */
	static JDialog jdialog;
	JPanel panelAuthentification=new JPanel();
	JLabel lblUsername=new JLabel("Nom d'utilisateur");
     static	JTextField txtUsername=new JTextField(10);
	JLabel lblPassword=new JLabel("Mot de passe");
	static JPasswordField txtpwd=new JPasswordField(10);
	
	static JButton btnLogin=new JButton("Valider");
	static JButton btnReset=new JButton("Annuler");
	private JTextField textFieldVilleEdit;
	JTextArea textAreaAdrEdit;
	JDialog jdialogUpdate;
	
	static PatientDaoJDBC patientDaoJdbc=new PatientDaoJDBC();
	
	public  void createDialogAuthentification() {
		panelAuthentification.add(lblUsername);
		panelAuthentification.add(txtUsername);
		panelAuthentification.add(lblPassword);
		panelAuthentification.add(txtpwd);
		panelAuthentification.add(btnLogin);
		panelAuthentification.add(btnReset);
		jdialog.getContentPane().add(panelAuthentification);
		jdialog.setBounds(300, 300, 250, 200);
		
	}
	public static void main(String[] args) {
		
		MainUI frame = new MainUI();
		jdialog=new JDialog(frame, "Authentification");
		frame.createDialogAuthentification();
		jdialog.setVisible(true);
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user=txtUsername.getText();
				String pwd=txtpwd.getText();
				
				
				
				if(patientDaoJdbc.findUserByUsernameAndPassword(user, pwd)==true) {
					frame.setBounds(300, 300, 400, 400);
					frame.setVisible(true);
					jdialog.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Veuillez réessayer une autre fois ","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		
		setTitle("Gestion Cabinet M\u00E9dical");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1077, 838);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Application");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Quitter");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Patient");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consulter la liste des patients");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("consultation");
				//PatientDaoJDBC patientDao=new PatientDaoJDBC();
				List<Patient> listePatient= patientDaoJdbc.findAll();
				donnees=new String[listePatient.size()][6];
				
				for (int i = 0; i < listePatient.size(); i++) {
					Patient p=listePatient.get(i);// récupération de patient numéro i
					
					donnees[i][0]=""+p.getNom();
					donnees[i][1]=""+p.getPrenom();
					donnees[i][2]=""+p.getAdresse();
					donnees[i][3]=""+p.getNss();
					donnees[i][4]=""+p.getDateNaissance();
					donnees[i][5]=""+p.getVille();
					
					
				}
				table=new JTable(donnees, colonnes);
				scrollPane.setViewportView(table);
				panelConsultation.updateUI();
				
				contentPane.remove(panelAjoutPatient);// supprimer panelAjoutPatient de panel principal contenPane
				contentPane.add(panelConsultation, BorderLayout.CENTER);// ajouter panelConsultation dans le centre de contentPane
				contentPane.updateUI();// mettre à jour le contentPane àprés la modification
	
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ajouter un nouveau patient");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ajout");
				contentPane.remove(panelConsultation);// supprimer panelConsultation de panel principal contenPane
				contentPane.add(panelAjoutPatient, BorderLayout.CENTER);// ajouter panelAjoutPatient dans le centre de contentPane
				contentPane.updateUI();// mettre à jour le contentPane àprés la modification
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		 panelAjoutPatient = new JPanel();
		panelAjoutPatient.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ajout de patient", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		
		panelAjoutPatient.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(191, 113, 45, 20);
		panelAjoutPatient.add(lblNewLabel);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(335, 113, 146, 26);
		panelAjoutPatient.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(167, 166, 69, 20);
		panelAjoutPatient.add(lblNewLabel_1);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(335, 163, 146, 26);
		panelAjoutPatient.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Num\u00E9ro s\u00E9curit\u00E9 sociale");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(44, 217, 192, 20);
		panelAjoutPatient.add(lblNewLabel_2);
		
		textFieldNSS = new JTextField();
		textFieldNSS.setBounds(335, 215, 310, 26);
		panelAjoutPatient.add(textFieldNSS);
		textFieldNSS.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Adresse");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(167, 276, 69, 20);
		panelAjoutPatient.add(lblNewLabel_3);
		
		JTextArea textAreaAdresse = new JTextArea();
		textAreaAdresse.setBounds(335, 286, 310, 110);
		panelAjoutPatient.add(textAreaAdresse);
		
		JLabel lblNewLabel_4 = new JLabel("Age");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(202, 462, 34, 20);
		panelAjoutPatient.add(lblNewLabel_4);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(335, 475, 146, 26);
		panelAjoutPatient.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Ville");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(191, 426, 45, 20);
		panelAjoutPatient.add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Sartrouville");
		comboBox.addItem("Sceaux");
		comboBox.addItem("Paris");
		comboBox.addItem("Bourg la reine");
		comboBox.addItem("Clamart");
		comboBox.addItem("Montrouge");
		comboBox.addItem("Meudon");
		comboBox.addItem("Noisy champs");
		comboBox.addItem("Valenciennes");
		comboBox.addItem("Lille");
		
		comboBox.setBounds(335, 417, 156, 26);
		panelAjoutPatient.add(comboBox);
		
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Patient patient=new Patient();
				
				String val1=textFieldNSS.getText();
				int nss=Integer.parseInt(val1);//Convertir String en entier
				
				String nom=textFieldNom.getText();
				String prenom=textFieldPrenom.getText();
				String adresse=textAreaAdresse.getText();
				
				String val2=textFieldAge.getText();
				int age=Integer.parseInt(val2);
				
				String ville=comboBox.getSelectedItem().toString();
				
				patient.setNss(nss);
				patient.setNom(nom);
				patient.setPrenom(prenom);
				patient.setAdresse(adresse);
			    //patient.setDateNaissance(new Date());
				patient.setVille(ville);
				
				System.out.println(patient);
				//PatientDaoJDBC patientDao=new PatientDaoJDBC();
				
				patientDaoJdbc.add(patient);
				
				JOptionPane.showMessageDialog(MainUI.this, "Patient est bien ajouté", "Succés", JOptionPane.INFORMATION_MESSAGE);
			
			}
		});
		
		
		btnAdd.setBounds(264, 557, 115, 29);
		panelAjoutPatient.add(btnAdd);
		
		JButton btnReset = new JButton("Annuler");
		btnReset.setBounds(470, 557, 115, 29);
		panelAjoutPatient.add(btnReset);
		
		panelConsultation = new JPanel();
		contentPane.add(panelConsultation, BorderLayout.CENTER);
		buttonAdd.setIcon(new ImageIcon("C:\\workspaceafpa\\GCM\\images\\add.png"));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(panelConsultation);// supprimer panelConsultation de panel principal contenPane
				contentPane.add(panelAjoutPatient, BorderLayout.CENTER);// ajouter panelAjoutPatient dans le centre de contentPane
				contentPane.updateUI();// mettre à jour le contentPane àprés la modification
	
			}
		});
		toolBar.add(buttonAdd);
		buttonShow.setIcon(new ImageIcon("C:\\workspaceafpa\\GCM\\images\\show.png"));
		buttonShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("consultation");
				//PatientDaoJDBC patientDao=new PatientDaoJDBC();
				List<Patient> listePatient= patientDaoJdbc.findAll();
				donnees=new String[listePatient.size()][6];
				
				for (int i = 0; i < listePatient.size(); i++) {
					Patient p=listePatient.get(i);// récupération de patient numéro i
					
					donnees[i][0]=""+p.getNom();
					donnees[i][1]=""+p.getPrenom();
					donnees[i][2]=""+p.getAdresse();
					donnees[i][3]=""+p.getNss();
					donnees[i][4]=""+p.getDateNaissance();
					donnees[i][5]=""+p.getVille();
					
					
				}
				table=new JTable(donnees, colonnes);
				scrollPane.setViewportView(table);
				panelConsultation.updateUI();
				
				contentPane.remove(panelAjoutPatient);// supprimer panelAjoutPatient de panel principal contenPane
				contentPane.add(panelConsultation, BorderLayout.CENTER);// ajouter panelConsultation dans le centre de contentPane
				contentPane.updateUI();// mettre à jour le contentPane àprés la modification
	
				
			}
		});
		toolBar.add(buttonShow);
		contentPane.add(toolBar, BorderLayout.NORTH);
		 panelConsultation.setLayout(new BorderLayout(0, 0));
		
		 scrollPane = new JScrollPane();
				
		table = new JTable(donnees,colonnes);
		scrollPane.setViewportView(table);
		panelConsultation.add(scrollPane);
		getContentPane().add(contentPane);
		
		JPanel panelAction = new JPanel();
		contentPane.add(panelAction, BorderLayout.SOUTH);
		
		JButton btnDelete = new JButton("Supprimer");
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 int row	=table.getSelectedRow();
			 System.out.println("row="+row);
			 if(row==-1) {
				 JOptionPane.showMessageDialog(null ,"Veuillez sélectionner un patient","Attention",JOptionPane.WARNING_MESSAGE);
			 }
			 else {
			 String value= table.getValueAt(row, 3).toString();
			 System.out.println("value="+value);
			// PatientDaoJDBC patientdao=new PatientDaoJDBC();
			 int nss=Integer.parseInt(value);
			 patientDaoJdbc.delete(nss);
			 }
			 
			}
		});
		
		panelAction.add(btnDelete);
		Panel panelModification = new Panel();
		
		JButton btnUpdate = new JButton("Modifier");
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row =table.getSelectedRow();
				 System.out.println("row="+row);
				 if(row==-1) {
					 
					 JOptionPane.showMessageDialog(null ,"Veuillez sélectionner un patient","Attention",JOptionPane.WARNING_MESSAGE);
				 }
				 else {
					 
				 adresse=table.getValueAt(row, 2).toString();
				 ville=table.getValueAt(row, 5).toString();
				
				String nsstext=table.getValueAt(row, 3).toString();
				nss=Integer.parseInt(nsstext.trim());
				
				textAreaAdrEdit.setText(adresse);
				textFieldVilleEdit.setText(ville);
				
				 jdialogUpdate=new JDialog(MainUI.this, "Modification du patient");
				jdialogUpdate.getContentPane().add(panelModification);
				jdialogUpdate.setBounds(200, 200, 800, 600);
				jdialogUpdate.setVisible(true);
				 
				 }
			
			}
		});
		panelAction.add(btnUpdate);
		
		
		//contentPane.add(panelModification, BorderLayout.CENTER);
		panelModification.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Adresse");
		lblNewLabel_6.setBounds(250, 108, 69, 20);
		panelModification.add(lblNewLabel_6);
		
		 textAreaAdrEdit = new JTextArea();
		textAreaAdrEdit.setBounds(368, 108, 312, 130);
		panelModification.add(textAreaAdrEdit);
		
		JLabel lblNewLabel_7 = new JLabel("Ville");
		lblNewLabel_7.setBounds(250, 327, 69, 20);
		panelModification.add(lblNewLabel_7);
		
		textFieldVilleEdit = new JTextField();
		textFieldVilleEdit.setBounds(361, 324, 303, 26);
		panelModification.add(textFieldVilleEdit);
		textFieldVilleEdit.setColumns(10);
		
		JButton btnValideModification = new JButton("Valider");
		btnValideModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("modifier patient"+nss);
				//PatientDaoJDBC patientDaojdbc=new PatientDaoJDBC();
				patientDaoJdbc.update(nss,textFieldVilleEdit.getText(),textAreaAdrEdit.getText());
				jdialogUpdate.setVisible(false);
				
			}
		});
		btnValideModification.setBounds(384, 422, 97, 25);
		panelModification.add(btnValideModification);
		
		JButton btnreset = new JButton("Annuler");
		btnreset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetFields();
			}
		});
		
		btnreset.setBounds(532, 422, 97, 25);
		panelModification.add(btnreset);
		
	}
	

	public void resetFields() {
		textAreaAdrEdit.setText("");
		textFieldVilleEdit.setText("");
	}
}
