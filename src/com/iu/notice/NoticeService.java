package com.iu.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.page.SearchMakePage;
import com.iu.page.SearchPager;
import com.iu.page.SearchRow;
import com.iu.upload.UploadDAO;
import com.iu.upload.UploadDTO;
import com.iu.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeService implements Action{
	private NoticeDAO noticeDAO;
	private UploadDAO uploadDAO;
	public NoticeService() {
		noticeDAO = new NoticeDAO();
		uploadDAO = new UploadDAO();
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
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		String path ="../WEB-INF/views/notice/noticeWrite.jsp";
		boolean check =true;
		if(method.equals("POST")) {
			String saveDirectory = request.getServletContext().getRealPath("upload");
			int maxPostSize = 1024*1024*10;
			String encoding ="utf-8";
			MultipartRequest multi = null;
			Connection con = null;
			try {
				multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
				Enumeration<String> e = multi.getFileNames();
				ArrayList<UploadDTO> ar = new ArrayList<UploadDTO>();
				while(e.hasMoreElements()) {
					UploadDTO uploadDTO = new UploadDTO();
					String names = e.nextElement();
					uploadDTO.setFname(multi.getFilesystemName(names));
					uploadDTO.setOname(multi.getOriginalFileName(names));
					ar.add(uploadDTO);
				}
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setTitle(multi.getParameter("title"));
				noticeDTO.setContents(multi.getParameter("contents"));
				noticeDTO.setWriter(multi.getParameter("writer"));
				//1.sequence
				int num =noticeDAO.getNum();
				noticeDTO.setNum(num);
				con = DBConnector.getConnect();
				con.setAutoCommit(false);
				//2.notice insert
				num =noticeDAO.insert(noticeDTO, con);
				//3.upload insert
				for(UploadDTO uploadDTO : ar) {
					uploadDTO.setNum(noticeDTO.getNum());
					num = uploadDAO.insert(con, uploadDTO);
					if(num<1) {
						throw new Exception();
					}
				}
				con.commit();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}finally {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			check=false;
			path="./noticeList";
		}
		actionForward.setCheck(check);
		actionForward.setPath(path);
		return actionForward;
	}

}
