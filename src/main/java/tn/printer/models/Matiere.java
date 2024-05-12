package tn.printer.models;

import java.util.List;

public class Matiere {
	
	private int idMatiere;
    private String nom;
    private List<Document> documentsMatiere;
    
	
	public Matiere(int idMatiere, String nom, List<Document> documentsMatiere) {
		this.idMatiere = idMatiere;
		this.nom = nom;
		this.documentsMatiere = documentsMatiere;
	}
	
	public Matiere(int idMatiere, String nom) {
		this.idMatiere = idMatiere;
		this.nom = nom;
	}

	public int getIdMatiere() {
		return idMatiere;
	}
	
	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Matiere [idMatiere=" + idMatiere + ", nom=" + nom + "]";
	}

	public List<Document> getDocumentsMatiere() {
		return documentsMatiere;
	}

	public void setDocumentsMatiere(List<Document> documentsMatiere) {
		this.documentsMatiere = documentsMatiere;
	}
    

}
