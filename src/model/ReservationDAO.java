package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Close;
import util.DBconnection;

public class ReservationDAO {
	public ArrayList<String> reserveInfo(String date, int medicine) {
		ArrayList<String> info = new ArrayList<String>();
		String SQL = "SELECT Date FROM reservation WHERE Date LIKE ? AND medicineCode = ? ORDER BY Date ASC";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, date+"%");
			pstmt.setInt(2, medicine);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				info.add(rs.getString(1));
			}
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch
		
		return null;
	}
	
	public int reserve(ReservationBean reservation) throws SQLException {
		String SQL = "INSERT INTO Reservation VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, reservation.getUserID());
			pstmt.setString(2, reservation.getUserName());
			pstmt.setString(3, reservation.getHiragana());
			pstmt.setString(4, reservation.getGender());
			pstmt.setString(5, reservation.getUserTel());
			pstmt.setString(6, reservation.getDob());
			pstmt.setInt(7, reservation.getMedicineCode());
			pstmt.setString(8, reservation.getDate());

			// クエリ実行
			int result = pstmt.executeUpdate();
			// 完了しコミット
			if(result != 0) {
				conn.commit();
				return 1;
			}
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} finally {
			// Connection, PreparedStatement를 닫는다.
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch
			return 0;
	} // end insertMember()
	
	public ArrayList<ReservationBean> getReservationList(String userID,int available) {
		String SQL = "SELECT * FROM Reservation WHERE userID = ? AND availalbe = ? ORDER BY Date DESC";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReservationBean>reservation = new ArrayList<ReservationBean>();
		
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setInt(2, available);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ReservationBean bean = new ReservationBean();
				bean.setNumber(rs.getInt(1));
				bean.setUserID(rs.getString(2));
				bean.setUserName(rs.getNString(3));
				bean.setHiragana(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setUserTel(rs.getString(6));
				bean.setDob(rs.getString(7));
				bean.setMedicineCode(rs.getInt(8));
				bean.setDate(rs.getString(9).substring(0,16));
				bean.setAvailable(rs.getInt(10));
				reservation.add(bean);
			}
			return reservation;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
	public ArrayList<ReservationBean> getAllReservationList(String userID) {
		String SQL = "SELECT * FROM Reservation WHERE userID = ? ORDER BY Date DESC";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReservationBean>reservation = new ArrayList<ReservationBean>();
		
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ReservationBean bean = new ReservationBean();
				bean.setNumber(rs.getInt(1));
				bean.setUserID(rs.getString(2));
				bean.setUserName(rs.getNString(3));
				bean.setHiragana(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setUserTel(rs.getString(6));
				bean.setDob(rs.getString(7));
				bean.setMedicineCode(rs.getInt(8));
				bean.setDate(rs.getString(9).substring(0,16));
				bean.setAvailable(rs.getInt(10));
				reservation.add(bean);
			}
			return reservation;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
	
	public int reserveCheck(String userID, String date) { //1日1回予約確認
		String SQL = "SELECT * FROM Reservation WHERE userID = ? AND Date LIKE ? AND available = 1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, date+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 0;
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return 0;
	}
	
	public int delete(ReservationBean reservation) throws SQLException {
		String SQL = "DELETE FROM reservation WHERE number = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, reservation.getNumber());
			pstmt.executeUpdate();
				conn.commit();
			return 1;
		} catch (SQLException sqle) {
			conn.rollback();
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} 
	} 
	
}
