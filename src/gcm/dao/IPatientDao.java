package gcm.dao;

import java.util.List;

import gcm.model.Patient;

public interface IPatientDao {
	
	public void add(Patient p);
	
	public void delete (int nss);
	
	public void update(int nss,String ville,String adresse);
	//public void update(int nss);
	public List<Patient> findAll();
	

}
