package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserBean;
import util.DBconnection;
import util.Close;

public class UserDAO {
	public void join(UserBean user) throws SQLException {
		String SQL = "INSERT INTO USER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, false)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getHiragana());
			pstmt.setString(5, user.getUserEmail());
			pstmt.setString(6, user.getGender());
			pstmt.setString(7, user.getAddress());
			pstmt.setString(8, user.getUserTel());
			pstmt.setString(9, user.getDob());

			// クエリ実行
			pstmt.executeUpdate();
			// 完了しコミット
			conn.commit();
		} catch (SQLException sqle) {
			// 오류시 롤백
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
	} // end insertMember()
	
	public int update(UserBean user) {
		String SQL = "UPDATE user SET userPW =?, userName =?, hiragana = ?, userEmail =?, gender =?, address = ?, userTel = ? WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, user.getUserPW());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getHiragana());
			pstmt.setString(4, user.getUserEmail());
			pstmt.setString(5, user.getGender());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getUserTel());
			pstmt.setString(8, user.getUserID());

			// クエリ実行
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Connection, PreparedStatement를 닫는다.
			try {
				Close.close(conn, pstmt, null);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch
		return -1;
	} // end insertMember()
	
	public boolean login(String userID, String userPW) {
		String SQL = "SELECT userPW FROM user WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPW)) {//디비에서 추출한 값과 유저가 입력한 비번과 일치하면
					return true;
				}
			}
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
		// Connection, PreparedStatement를 닫는다.
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch
		return false;
	} // end insertMember()
	
	public UserBean getUser(String userID)  {
		String SQL = "SELECT * FROM user WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				UserBean user = new UserBean();
				user.setUserID(rs.getNString(1));
				user.setUserPW(rs.getString(2));
				user.setUserName(rs.getNString(3));
				user.setHiragana(rs.getNString(4));
				user.setUserEmail(rs.getNString(5));
				user.setGender(rs.getNString(6));
				user.setAddress(rs.getNString(7));
				user.setUserTel(rs.getNString(8));
				user.setDob(rs.getNString(9));
				user.setIs_admin(rs.getBoolean(10));
				return user;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		// Connection, PreparedStatement를 닫는다.
			try {
				Close.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch
		return null;
	} // end insertMember()
	

	
//	public String getUserEmail(String userName, String userEmail) {
//		String SQL = "SELECT userEmail FROM user WHERE userName = ? AND userEmail = ?";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = DBconnection.getConnection();
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setString(1, userName);
//			pstmt.setString(2, userEmail);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				return rs.getString(1);
//			}
//		
//		}catch (Exception e) {
//			throw new RuntimeException(e.getMessage());
//		}finally {
//		// Connection, PreparedStatement를 닫는다.
//			try {
//				Close.close(conn, pstmt, rs);
//			} catch (Exception e) {
//				throw new RuntimeException(e.getMessage());
//			}
//		} // end try~catch
//		return null; //DB ERROR
//	}
	
}
