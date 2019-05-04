package miniSGBD;

import java.nio.ByteBuffer;

public class Frame implements Comparable<Frame> {

	private ByteBuffer buffer = ByteBuffer.allocate(Constants.pageSize);
	private PageId pageId;
	private int pinCount;
	private int dirty;
	private long lastUsed;
	
	
	public Frame(ByteBuffer buffer, PageId pageId) {
		this.buffer = buffer;
		this.pageId = pageId;
		this.pinCount++;
		this.dirty = 0;
		lastUsed=System.currentTimeMillis();
	}


	public ByteBuffer getBuffer() {
		incPinCount();
		return buffer;
	}


	public void setBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}


	public PageId getPageId() {
		return pageId;
	}


	public void setPageId(PageId pageId) {
		this.pageId = pageId;
	}


	public void incPinCount() {
		pinCount++;
	}
	public int getPinCount() {
		return pinCount;
	}


	public long getLastUsed() {
		return lastUsed;
	}


	public void setLastUsed(long lastUsed) {
		this.lastUsed = lastUsed;
	}


	public void decPinCount() {
		if(pinCount>0)pinCount--;
		if(pinCount==0)lastUsed=System.currentTimeMillis();	
	}


	public int getDirty() {
		return dirty;
	}


	public void setDirty(int dirty) {
		this.dirty = dirty;
	}


	@Override
	public int compareTo(Frame o) {
		if(o.getPageId()==this.pageId)return 0;
		else return -1;
	}
	
	
	
	
	
	

}
