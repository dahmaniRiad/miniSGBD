package miniSGBD;

import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
/*
	DBManager db=DBManager.getInstance();
	Scanner sc=new Scanner(System.in);
	String command;
	
	do {
		command=sc.nextLine();
		db.processCommand(command);
		
		
	}while(!command.equals("exit"));*/
		
		
		DiskManager tmp =DiskManager.getInstance();
		tmp.CreateFile(2018);
		tmp.AddPage(2018);
	}
		

}
