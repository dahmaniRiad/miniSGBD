package miniSGBD;

import java.io.IOException;
import java.util.ArrayList;

public class LRU {
	
	public static int replace(ArrayList<Frame> listFrames) throws IOException {
		ArrayList<Frame> tmp = pinCountNull(listFrames);
		Frame tmpFrame=indexLRU(tmp);
		if(tmpFrame.getDirty()==1) DiskManager.getInstance().WritePage(tmpFrame.getPageId(), tmpFrame.getBuffer());
		return listFrames.indexOf(tmpFrame);
	}
	
	
	
	public static ArrayList<Frame> pinCountNull(ArrayList<Frame> listFrames){
		ArrayList<Frame> tmp = new ArrayList<>();
		for(int i=0;i<listFrames.size();i++) {
			if(listFrames.get(i).getPinCount()==0)tmp.add(listFrames.get(i));		
		}
		return tmp;
	}
	
	public static ArrayList<Frame> dirty(ArrayList<Frame> listFrames){
		ArrayList<Frame> tmp = new ArrayList<>();
		for(int i=0;i<listFrames.size();i++) {
			if(listFrames.get(i).getDirty()!=0)tmp.add(listFrames.get(i));		
		}
		return tmp;
	}
	
	public static void flush(ArrayList<Frame> listFrames) throws IOException {
		for(int i=0;i<listFrames.size();i++) {
			DiskManager.getInstance().WritePage(listFrames.get(i).getPageId(), listFrames.get(i).getBuffer());
		}
	}
	
	
	public static Frame indexLRU(ArrayList<Frame> listFrames) {
		Frame tmp =listFrames.get(0);
		for(int i=1;i<listFrames.size();i++)
			if(listFrames.get(i).getLastUsed()<tmp.getLastUsed())tmp=listFrames.get(i);
		return tmp;
	}

}
