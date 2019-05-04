package miniSGBD;

import java.util.ArrayList;
import java.util.List;

public class RelDef {
	
	private String nomRel;
	private int nbColonne;
	private List<String> liste;
	
	public RelDef() {
		liste =new ArrayList();
	}
	
	public String getNomRel() {return nomRel;}
	public void setNomRel(String nom) {nomRel=nom;}
	
	public int getNbColonne() {return nbColonne;}
	public void setNbColonne(int nbC) {nbColonne=nbC;}
	
		
	public List<String> getListe() {
		return liste;
	}
	
	public void setListeAll(List<String> liste) {
		this.liste.addAll(liste);
	}
	
	public void setListe(String element) {
		if(liste.isEmpty())liste =new ArrayList();
		liste.add(element);
	}

	@Override
	public String toString() {
		return "RelDef [nomRel=" + nomRel + ", nbColonne=" + nbColonne + ", liste=" + liste + "]";
	}
	
	
}
