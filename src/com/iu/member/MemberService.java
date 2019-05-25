package com.iu.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iu.action.Action;
import com.iu.action.ActionForward;

public class MemberService implements Action {
	
	private MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
		// TODO Auto-generated constructor stub
	}
	
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		HttpSession session = request.getSession();
		session.invalidate();
		actionForward.setCheck(false);
		actionForward.setPath("../index.do");
		return actionForward;
	}
	
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(request.getParameter("id"));
		memberDTO.setPw(request.getParameter("pw"));
		HttpSession session = request.getSession();
		String remember = request.getParameter("remember");
		
		String method = request.getMethod();
		String path = "../WEB-INF/views/member/memberLogin.jsp";
		boolean check = true;
		if(method.equals("POST")) {
			try {
				memberDTO = memberDAO.logIn(memberDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(memberDTO!=null) {
				check = false;
				path = "../index.do";
				session.setAttribute("member", memberDTO);
			}
			if(remember!=null) {
				Cookie cookie = new Cookie("c", memberDTO.getId());
				response.addCookie(cookie);
			}else {
				Cookie cookie = new Cookie("c", "");
				response.addCookie(cookie);
			}
		}
		actionForward.setCheck(check);
		actionForward.setPath(path);
		return actionForward;
	}

	@Override
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
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
