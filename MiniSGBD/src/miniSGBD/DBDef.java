package miniSGBD;

import java.util.ArrayList;

public class DBDef {
	private ArrayList<RelDef> listRelDef;
	private static int compRel;// compteur
	private static final DBDef INSTANCE =new DBDef();
	
	
	private DBDef() {
		
		listRelDef =new ArrayList();
	}
	
	
	


	public  void addRelation(RelDef rd) {
		listRelDef.add(rd);
		System.out.println(listRelDef);
		compRel++;
	}
	
	public static int getCompRel() {
		return compRel;
	}


	public static void setCompRel(int compRel) {
		DBDef.compRel = compRel;
	}


	public ArrayList<RelDef> getListRelDef() {
		return listRelDef;
	}
	
	public static DBDef getDBDef() {
		return INSTANCE;
	}
	
	public static void init() {
		
	}
	
	public static void finish() {
		
	}
	
}
