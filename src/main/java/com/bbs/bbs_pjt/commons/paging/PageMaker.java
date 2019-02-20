package com.bbs.bbs_pjt.commons.paging;

public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Criteria criteria;
	
	
	private void calcData() { 
		setEndPage( ( int ) ( Math.ceil( criteria.getPage() / (double) displayPageNum ) * displayPageNum ) );
		setStartPage( ( endPage - displayPageNum ) + 1 );
		
		int tempEndPage = ( int ) ( Math.ceil( totalCount / ( double) criteria.getPerPageNum() ) );
		
		if ( getEndPage() > tempEndPage ) {
			setEndPage( tempEndPage );
		}
		
		prev = getStartPage() == 1 ? false : true;
		next = getEndPage() * criteria.getPerPageNum() >= totalCount ? false : true;
	
	}
	public void setCriteria( Criteria criteria ) {
		this.criteria = criteria;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount( int totalCount ) {
		this.totalCount = totalCount;
		calcData();
	}
	
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage( int endPage ) {
		this.endPage = endPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage( int startPage ) {
		this.startPage = startPage;
	}
	
	public boolean hasPrev() {
		return prev;
	}
	public void setPrev( boolean prev ) {
		this.prev = prev;
	}
	
	public boolean hasNext() { 
		return next;
	}
	public void setNext( boolean next ) {
		this.next = next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum( int displayPageNum ) {
		this.displayPageNum = displayPageNum;
	}
	
}
