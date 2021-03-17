package gcm.model;

import java.util.Date;

public class Patient {
	
	 	private int nss;
	    private String nom;
	    private String prenom;
	    private String adresse;
	    private Date dateNaissance;
	    private String ville;
	    
		public int getNss() {
			return nss;
		}
		public void setNss(int nss) {
			this.nss = nss;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}
		
		public String getVille() {
			return ville;
		}
		public void setVille(String ville) {
			this.ville = ville;
		}
		@Override
		public String toString() {
			return "Patient [nss=" + nss + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", date de naissance="
					+ dateNaissance + ", ville=" + ville + "]";
		}
		public Date getDateNaissance() {
			return dateNaissance;
		}
		public void setDateNaissance(Date dateNaissance) {
			this.dateNaissance = dateNaissance;
		}
	
	    
}
