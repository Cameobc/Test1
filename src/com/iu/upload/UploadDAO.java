package com.iu.upload;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UploadDAO {
	
	public int insert(Connection con, UploadDTO uploadDTO) throws Exception {
		String sql ="insert into upload values (profile_seq.nextval, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, uploadDTO.getNum());
		st.setString(2, uploadDTO.getOname());
		st.setString(3, uploadDTO.getFname());
		int result =st.executeUpdate();
		st.close();
		return result;
	}

}
