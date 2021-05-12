package model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Close;
import util.DBconnection;

public class ReservationDAO {
	public List<String> reserveCheckList(String date, int medicine) {
		List<String> info = new ArrayList<String>();
		String SQL = "SELECT date FROM reservation WHERE date LIKE ? AND medicineCode = ? ORDER BY date ASC";
		
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
	
	public List<ReservationBean> getReservationList(String userID,int available) {
		String SQL = "SELECT * FROM Reservation WHERE userID = ? AND available = ? ORDER BY date DESC";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReservationBean>reservation = new ArrayList<ReservationBean>();
		
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setInt(2, available);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
		return reservation;
	}
	public List<ReservationBean> getAllReservationList(String userID) {
		String SQL = "SELECT * FROM Reservation WHERE userID = ? ORDER BY date DESC";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReservationBean> reservation = new ArrayList<ReservationBean>();
		
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
		return reservation;
	}
	
	public ReservationBean getReservation(int reservationNumber,String userID) { 
		String SQL = "SELECT * FROM Reservation WHERE number = ? AND userID = ? AND available = 1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, reservationNumber);
			pstmt.setString(2, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ReservationBean bean = new ReservationBean();
				bean.setNumber(rs.getInt(1));
				bean.setUserID(rs.getString(2));
				bean.setUserName(rs.getString(3));
				bean.setHiragana(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setUserTel(rs.getString(6));
				bean.setDob(rs.getString(7));
				bean.setMedicineCode(rs.getInt(8));
				bean.setDate(rs.getString(9).substring(0,16));
				bean.setAvailable(rs.getInt(10));
				return bean;
			}
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
	
//	public int reserveCheck(String userID, String date) { //1日1回予約確認
//		String SQL = "SELECT * FROM Reservation WHERE userID = ? AND date LIKE ? AND available = 1";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBconnection.getConnection();
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setString(1, userID);
//			pstmt.setString(2, date+"%");
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				return 0;
//			}
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				Close.close(conn, pstmt, rs);
//			} catch (Exception e) {
//				throw new RuntimeException(e.getMessage());
//			}
//		}
//		return 0;
//	}
	
	public int delete(ReservationBean reservation) throws SQLException {
		String SQL = "UPDATE reservation SET available = 0 WHERE number = ?";
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
	
	public int update(ReservationBean reservation) throws SQLException {
		String SQL = "UPDATE reservation SET medicineCode = ?, date = ? WHERE number = ? AND userID = ? AND available = 1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			conn.setAutoCommit(false);
			pstmt.setInt(1, reservation.getMedicineCode());
			pstmt.setString(2, reservation.getDate());
			pstmt.setInt(3, reservation.getNumber());
			pstmt.setString(4, reservation.getUserID());
			
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
		}
		return -1;
	} 
}
