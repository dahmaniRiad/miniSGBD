package miniSGBD;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DBManager {
	private String nom;
	private static final DBManager INSTANCE = new DBManager();
	
	
	private DBManager() {
	}
	
	public void init() {
		DBDef.init();
	}
	
	public void finish() {
		DBDef.finish();
	}
	
	public  void processCommand(String commande) {
		
		StringTokenizer st=new StringTokenizer(commande," ");
		ArrayList<String> commandList=new ArrayList<String>();
		while(st.hasMoreElements())commandList.add(st.nextToken());
		switch(commandList.get(0)) {
		case "create":
			CreateRelation(commandList.get(1), Integer.parseInt(commandList.get(2)),commandList.subList(3, commandList.size()));
			break;
		}
		
		
	}
	
	public static DBManager getInstance() {
		return INSTANCE;
	}

	public void CreateRelation(String nomRelation,int nombreColonnes,List<String> typesDesColonnes) {
		RelDef tmp=new RelDef();
		tmp.setNomRel(nomRelation);
		tmp.setNbColonne(nombreColonnes);
		tmp.setListeAll(typesDesColonnes);
		DBDef.getDBDef().addRelation(tmp);
	}

	
}
