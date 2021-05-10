package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Date {
	public static String getDate() {
		String SQL = "SELECT NOW()";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery(); //sql을 실행하였을때 결과를 나오도록함
			if(rs.next()) {//결과가 있는경우
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Close.close(con, pstmt, rs);
		}
		return ""; //DB Error
	}
}
