package miniSGBD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DiskManager {
	
	private static final DiskManager INSTANCE =new DiskManager();
	private PageId oPageId;
	
	private DiskManager() {}

	public static DiskManager getInstance() {
		return INSTANCE;
	}
	
	
	public void CreateFile(int iFileIdx) throws IOException {
		new File("./BD").mkdirs();
		FileWriter file1=new FileWriter("BD/Data_"+iFileIdx+".rf");
		file1.close();
		
	}
	
	public  void AddPage(int iFileIdx) {
		File file=new File("BD/Data_"+iFileIdx+".rf");
		long taille=file.length();
		oPageId.setFileIdx(iFileIdx);
		oPageId.setPageIdx((int)taille/Constants.pageSize);
		System.out.println(oPageId);
		file.exists();
	}
	public ByteBuffer ReadPage(PageId iPageId) throws IOException {
		RandomAccessFile File = new RandomAccessFile("BD/Data_"+iPageId.getFileIdx()+".rf", "r");
		FileChannel inChannel = File.getChannel().position(iPageId.getPageIdx()+1);
		ByteBuffer oBuffer = ByteBuffer.allocate(Constants.pageSize);
		int bytesRead = inChannel.read(oBuffer); //read into buffer.
		/*while (bytesRead != -1) {

		  oBuffer.flip();  //make buffer ready for read

		  while(oBuffer.hasRemaining()){
		      System.out.print((char) oBuffer.get()); // read 1 byte at a time
		  }
		}*/
		File.close();
		return oBuffer;
	}
	public void WritePage(PageId iPageId,ByteBuffer iBuffer) throws IOException{
		RandomAccessFile File = new RandomAccessFile("BD/Data_"+iPageId.getFileIdx()+".rf", "w");
		FileChannel inChannel = File.getChannel().position(iPageId.getPageIdx()*Constants.pageSize+1);
		int byteswritten = inChannel.write(iBuffer);
		File.close();
	}
	
	
}
