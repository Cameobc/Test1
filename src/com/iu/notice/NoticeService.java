package com.iu.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.page.SearchMakePage;
import com.iu.page.SearchPager;
import com.iu.page.SearchRow;

public class NoticeService implements Action{
	private NoticeDAO noticeDAO;
	public NoticeService() {
		noticeDAO = new NoticeDAO();
		// TODO Auto-generated constructor stub
	}
	@Override
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		int curPage=1;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String kind=request.getParameter("kind");
		String search=request.getParameter("search");
		SearchMakePage s= new SearchMakePage(curPage, kind, search);
		//startRow lastRow
		SearchRow searchRow =s.makeRow();
		ArrayList<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		try {
			ar = noticeDAO.selectList(searchRow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//paging
		try {
			int totalCount = noticeDAO.getTotalCount(searchRow);
			SearchPager searchPager=s.makePage(totalCount);
			request.setAttribute("pager", searchPager);
			request.setAttribute("list", ar);
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/views/notice/noticeList.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			actionForward.setCheck(false);
			actionForward.setPath("../index.do");
		}
		return actionForward;
	}

	@Override
	public ActionForward select(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
