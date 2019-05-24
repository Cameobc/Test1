package com.iu.page;

public class SearchMakePage {
	private Search search;
	private int perPage;
	private int curPage;
	
	public SearchMakePage(int curPage, String kind, String search) {
		this(curPage, 10, kind, search);
	}
	
	public SearchMakePage(int curPage, int perPage, String kind, String search) {
		this.curPage= curPage;
		this.perPage=perPage;
		this.search = new Search();
		this.search.setKind(kind);
		this.search.setSearch(search);
	}

	public SearchRow makeRow() {
		int startRow = (curPage-1)*perPage+1;
		int lastRow = curPage*perPage;
		SearchRow searchRow = new SearchRow();
		searchRow.setSearch(search);
		searchRow.setStartRow(startRow);
		searchRow.setLastRow(lastRow);
		return searchRow;
	}
	
	public SearchPager makePage(int totalCount) {
		int totalPage= totalCount/perPage;
		if(totalCount%perPage!=0) {
			totalPage++;
		}
		int perBlock = 5;
		int totalBlock = totalPage/perBlock;
		if(totalPage%perBlock!=0) {
			totalBlock++;
		}
		int curBlock = curPage/perBlock;
		if(curPage%perBlock!=0) {
			curBlock++;
		}
		int startNum = (curBlock-1)*perBlock+1;
		int lastNum = curBlock*perBlock;
		
		if(curBlock==totalBlock) {
			lastNum=totalPage;
		}
		SearchPager searchPager = new SearchPager();
		searchPager.setCurPage(curPage);
		searchPager.setCurBlock(curBlock);
		searchPager.setTotalBlock(totalBlock);
		searchPager.setSearch(search);
		searchPager.setLastNum(lastNum);
		searchPager.setStartNum(startNum);
		return searchPager;
	}
}
