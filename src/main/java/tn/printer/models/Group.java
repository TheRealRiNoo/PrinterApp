package tn.printer.models;

import java.util.List;

public class Group {

	private int groupeId;
	private String groupeName;
	private List<Matiere> matieresGroupe;
	
	public Group(int groupeId, String groupeName, List<Matiere> matieresGroupe) {
		super();
		this.groupeId = groupeId;
		this.groupeName = groupeName;
		this.matieresGroupe = matieresGroupe;
	}

	public int getGroupeId() {
		return groupeId;
	}

	public void setGroupeId(int groupeId) {
		this.groupeId = groupeId;
	}

	public String getGroupeName() {
		return groupeName;
	}

	public void setGroupeName(String groupeName) {
		this.groupeName = groupeName;
	}

	public List<Matiere> getMatieresGroupe() {
		return matieresGroupe;
	}

	public void setMatieresGroupe(List<Matiere> matieresGroupe) {
		this.matieresGroupe = matieresGroupe;
	}
	
	
	
	
}
