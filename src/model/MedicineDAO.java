package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Close;
import util.DBconnection;

public class MedicineDAO {

	public int reserve(MedicineBean medicine) throws SQLException {
		String SQL = "INSERT INTO medinice VALUES(?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, medicine.getCode());
			pstmt.setString(2, medicine.getMedicineName());

			int result = pstmt.executeUpdate();
			if(result != 0) {
				conn.commit();
				return 1;
			}
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch
		return -1;
	}
	
}
