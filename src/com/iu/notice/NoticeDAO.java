package com.iu.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.iu.page.Search;
import com.iu.page.SearchRow;
import com.iu.util.DBConnector;

public class NoticeDAO {
	
	//seq번호 가져오기
/*	public int getNum() throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "";
		int result = 0;
		return result;
	}*/
	
	public int getTotalCount(SearchRow searchRow) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from notice where "+searchRow.getSearch().getKind()+" like ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		int result = Integer.parseInt(rs.getString(1));
		DBConnector.disConnect(con, st, rs);
		return result;
	}
	
	/*public static void main(String[] args) {
	NoticeDAO noticeDAO = new NoticeDAO();
	NoticeDTO noticeDTO = new NoticeDTO();
	for(int i=0;i<100;i++) {
	noticeDTO.setTitle("a"+i);
	noticeDTO.setContents("aa"+i);
	noticeDTO.setWriter("abc"+i);
	try {
		noticeDAO.insert(noticeDTO);
		Thread.sleep(100);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}*/
	public ArrayList<NoticeDTO> selectList(SearchRow searchRow) throws Exception {
		ArrayList<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		Connection con = DBConnector.getConnect();
		String sql = " select * from "
				+ "(select rownum r, n.* from "
				+ "(select num, title, writer, reg_date, hit from notice where "+ searchRow.getSearch().getKind() +" like ? order by num desc) n) "
				+ "where r between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		st.setInt(2, searchRow.getStartRow());
		st.setInt(3, searchRow.getLastRow());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setReg_date(rs.getString("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			ar.add(noticeDTO);
		}
		DBConnector.disConnect(con, st, rs);
		return ar;
	}

}
