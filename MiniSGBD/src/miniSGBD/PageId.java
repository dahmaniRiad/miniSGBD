package miniSGBD;

public class PageId implements Comparable<PageId> {
	
	
	private int fileIdx;
	private int pageIdx;
	
	
	public PageId(int fileIdx, int pageIdx) {
		fileIdx = fileIdx;
		pageIdx = pageIdx;
	}


	public int getFileIdx() {
		return fileIdx;
	}


	public void setFileIdx(int fileIdx) {
		fileIdx = fileIdx;
	}


	public int getPageIdx() {
		return pageIdx;
	}


	public void setPageIdx(int pageIdx) {
		pageIdx = pageIdx;
	}


	@Override
	public String toString() {
		return "PageId [FileIdx=" + fileIdx + ", PageIdx=" + pageIdx + "]";
	}


	@Override
	public int compareTo(PageId o) {
		if(o.getFileIdx()==fileIdx && o.getPageIdx()==pageIdx) return 0;
		return -1;
	}
	
	 
	
	

}
