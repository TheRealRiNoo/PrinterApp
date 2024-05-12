package tn.printer.models;

public class Utilisateur {
    private int idUtilisateur;
    private String nomUtilisateur;
    private String email;
    private String motDePasse;
    private int role; // New attribute for role
    
    public Utilisateur(int idUtilisateur, String nomUtilisateur, String email, String motDePasse, int role) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role; // Initialize the role
    }

    public Utilisateur() {
		// TODO Auto-generated constructor stub
	}

	public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nomUtilisateur=" + nomUtilisateur + ", email=" + email
                + ", motDePasse=" + motDePasse + ", role=" + role + "]";
    }
}
