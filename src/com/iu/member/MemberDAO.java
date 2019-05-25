package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.util.DBConnector;

public class MemberDAO {
	//로그인 select
	public MemberDTO logIn(MemberDTO memberDTO) throws Exception {
		MemberDTO dto = null;
		Connection con = DBConnector.getConnect();
		String sql = "select id, name, email, phone, age from memeber where id=? and pw=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setPhone(rs.getString("phone"));
			dto.setAge(rs.getInt("age"));
		}
		DBConnector.disConnect(con, st, rs);
		return dto;
	}

}
