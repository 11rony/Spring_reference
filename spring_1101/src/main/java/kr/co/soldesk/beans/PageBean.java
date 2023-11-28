package kr.co.soldesk.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PageBean {

	// 최소 페이지 번호
	private int min;
	// 최대 페이지 번호
	private int max;
	// 이전 버튼의 페이지 번호
	private int prevPage;
	// 다음 버튼의 페이지 번호
	private int nextPage;
	// 전체 페이지 개수
	private int pageCnt;
	// 현재 페이지 번호
	private int currentPage;
	
	// contentCnt : 전체글 갯수(오라클 table에서 가져옴)
	// currentPage : 현재 페이지 번호(param)
	// contnetPageCnt : 페이지당 글의 갯수(property)
	// painationCnt : 페이지 개수 (property)
	public PageBean(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		//현재 페이지 번호
		this.currentPage = currentPage;
		
		//전체 페이지 갯수
		pageCnt = contentCnt/contentPageCnt; //533/10 = 54
		
		if(contentCnt%contentPageCnt>0) {
			pageCnt++;
		}
		
		
		//최대 10,20,30 이므로 결국 최소에 9를 더한것과 같음
		/*
		 0~9 : 1 
		 10 ~ 19 : 2 
		 20 ~ 29 : 3
		 */
		
		//페이지당 글의 갯수
		/*
		 * 0 : 1 /10 1 : 11 / 10 2 : 21 / 10
		 */
		
		min = ((currentPage - 1) / contentPageCnt) * contentPageCnt + 1;
	    max = min + paginationCnt - 1;
	    
	    if(max>pageCnt) {
	    	max = pageCnt;
	    }
	    
	    prevPage = min - 1;
	    nextPage = max + 1;
	    
	    if(nextPage>pageCnt) {
	    	nextPage=pageCnt;
	    }
	    
	}
	
}





