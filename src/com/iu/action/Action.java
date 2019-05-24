package com.iu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	//list
	public ActionForward list(HttpServletRequest request, HttpServletResponse response);
	
	//select
	public ActionForward select(HttpServletRequest request, HttpServletResponse response);
	
	//update
	public ActionForward update(HttpServletRequest request, HttpServletResponse response);
	
	//delete
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response);
	
	//insert
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response);
}
