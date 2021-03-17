package gcm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gcm.model.Patient;

public class PatientDaoJDBC implements IPatientDao {

	Statement stmt;
	
	
	public PatientDaoJDBC() {
		// TODO Auto-generated constructor stub
		 try {
				stmt=JdbcConnexion.connecter().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	@Override
	public void add(Patient p) {
	// TODO Auto-generated method stub	
		try {
		String sql="insert into patient(nss,nom,prenom,adresse,datenaissance,ville) values("+p.getNss()+",'"+p.getNom()+"','"+p.getPrenom()+"','"+p.getAdresse()+"',"+p.getDateNaissance()+",'"+p.getVille()+"')";	
		stmt.execute(sql);
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	@Override
	public void delete(int nss) {
		// TODO Auto-generated method stub
		try {
		
			stmt.execute("delete from patient where nss="+nss);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void update(int nss,String ville,String adresse) {
		// TODO Auto-generated method stub
		try {
			
			String sql="update patient set ville='"+ville+"',adresse='"+adresse+"' where nss="+nss+" ";
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Patient> findAll() {
		List<Patient> list=new ArrayList<Patient>();
		
		try {
			  ResultSet rs=	stmt.executeQuery("select * from patient");
			   
			   while(rs.next()) {
				   int nss=rs.getInt("nss");
				   String nom=rs.getString("nom");
				   String prenom=rs.getString("prenom");
				   String adr=rs.getString("adresse");
				   String ville=rs.getString("ville");
				   Date datenaissance=rs.getDate("datenaissance");
                   // remplir l'objet patient
                   Patient patient=new Patient();
                   patient.setNss(nss);
                   patient.setNom(nom);
                   patient.setPrenom(prenom);
                   patient.setAdresse(adr);
                   patient.setDateNaissance(datenaissance);
                   patient.setVille(ville);
                   
                   list.add(patient);	   
			   }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return list;
	}
	
	
	public boolean findUserByUsernameAndPassword(String username,String password) {
					
		try {
					
			ResultSet rs=	stmt.executeQuery("select * from gcm_users where username='"+username+"' and password='"+password+"'");
			if(rs.next())
				return true;
					
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		
		return false;
	}
	
	
	public static void main(String[] args) {
		PatientDaoJDBC patientDaoJDBC=new PatientDaoJDBC();
		Patient p=new Patient();
		p.setNss(15);
		p.setPrenom("Xx");
		p.setNom("YY");
		p.setAdresse("ZZ");
	//	p.setDateNaissance(new Date());
		p.setVille("Paris");
		//patientDaoJDBC.add(p);
		boolean resultat= patientDaoJDBC.findUserByUsernameAndPassword("test", "test");
		System.out.println("resultat="+resultat);
	}

}
