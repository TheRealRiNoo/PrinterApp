package tn.printer.models;

public class Document {
	
	private int idDocument;
    private String documentNom;
    private String documentUrl;
    private Matiere idMatiere;
    
    
	public Document(int idDocument, String documentNom, String documentUrl, Matiere idMatiere) {
		super();
		this.idDocument = idDocument;
		this.documentNom = documentNom;
		this.documentUrl = documentUrl;
		this.idMatiere = idMatiere;
	}
	public int getIdDocument() {
		return idDocument;
	}
	public void setIdDocument(int idDocument) {
		this.idDocument = idDocument;
	}
	public String getDocumentNom() {
		return documentNom;
	}
	public void setDocumentNom(String documentNom) {
		this.documentNom = documentNom;
	}
	public String getDocumentUrl() {
		return documentUrl;
	}
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
	public Matiere getIdMatiere() {
		return idMatiere;
	}
	public void setIdMatiere(Matiere idMatiere) {
		this.idMatiere = idMatiere;
	}
    
}
