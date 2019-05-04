package miniSGBD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class BufferManager {
	
	private static final BufferManager INSTANCE =new BufferManager();
	private ArrayList<Frame> bufferPool=new ArrayList<>();
	private BufferManager() {}

	public static BufferManager getInstance() {
		return INSTANCE;
	}

	ByteBuffer getPage(PageId iPageId) throws IOException {
		if(bufferPool.contains(new Frame(null, iPageId))) {
			
			return bufferPool.get(bufferPool.indexOf(new Frame(null, iPageId))).getBuffer();
		
			}
		else if(bufferPool.size()<Constants.frameCount) {
			RandomAccessFile File = new RandomAccessFile("BD/Data_"+iPageId.getFileIdx()+".rf", "r");
			FileChannel inChannel = File.getChannel().position(iPageId.getPageIdx()+1);
			ByteBuffer Buffer = ByteBuffer.allocate(Constants.pageSize);
			int bytesRead = inChannel.read(Buffer); //read into buffer.
			bufferPool.add(new Frame(Buffer, iPageId));
			File.close();
			return Buffer;
		}
			else {
				int replaceIndex=LRU.replace(bufferPool);
				RandomAccessFile File = new RandomAccessFile("BD/Data_"+iPageId.getFileIdx()+".rf", "r");
				FileChannel inChannel = File.getChannel().position(iPageId.getPageIdx()+1);
				ByteBuffer Buffer = ByteBuffer.allocate(Constants.pageSize);
				int bytesRead = inChannel.read(Buffer); //read into buffer.
				bufferPool.set(replaceIndex,new Frame(Buffer, iPageId));
				File.close();
				return Buffer;	
			}
	}
	
	void freePage(PageId iPageId,int iIsDirty) {
		int tmp=bufferPool.indexOf(new Frame(null, iPageId));
		bufferPool.get(tmp).decPinCount();
		bufferPool.get(tmp).setDirty(iIsDirty);;
	}
	
	void flushAll() throws IOException {
		LRU.flush(LRU.dirty(bufferPool));
	}
	
}
