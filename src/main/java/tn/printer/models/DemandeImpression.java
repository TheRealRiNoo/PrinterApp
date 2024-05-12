package tn.printer.models;

import java.util.Date;

public class DemandeImpression {
    
    private int idDemande;
    private String document;
    private Date date;
    private int nombreCopies;
    private Utilisateur enseignant;
    private Group groupe;
    
    public DemandeImpression(String document, Date date, int nombreCopies,
                             Utilisateur enseignant, Group groupe) {
        this.document = document;
        this.date = date;
        this.nombreCopies = nombreCopies;
        this.enseignant = enseignant;
        this.groupe = groupe;
    }

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Group getGroupe() {
			return groupe;
		}

		public void setGroupe(Group groupe) {
			this.groupe = groupe;
		}

		public int getIdDemande() {
			return idDemande;
		}

		public void setIdDemande(int idDemande) {
			this.idDemande = idDemande;
		}

		public String getDocument() {
			return document;
		}

		public void setDocument(String document) {
			this.document = document;
		}

		public Date getDateDemande() {
			return date;
		}

		public void setDateDemande(Date date) {
			this.date = date;
		}

		public int getNombreCopies() {
			return nombreCopies;
		}

		public void setNombreCopies(int nombreCopies) {
			this.nombreCopies = nombreCopies;
		}

		public Utilisateur getEnseignant() {
			return enseignant;
		}

		public void setEnseignant(Utilisateur enseignant) {
			this.enseignant = enseignant;
		}
	

}
