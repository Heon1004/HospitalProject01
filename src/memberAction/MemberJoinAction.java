package memberAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserDAO;

@WebServlet("/memberjoinAction")
public class MemberJoinAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UserBean user = new UserBean();
		UserDAO dao = new UserDAO();
		
		String year = request.getParameter("yy");
		String month = request.getParameter("mm");
		String day = request.getParameter("dd");
		
		user.setUserID(request.getParameter("userID"));
		user.setUserPW(request.getParameter("userPW"));
		user.setUserName(request.getParameter("userName"));
		user.setHiragana(request.getParameter("hiragana"));
		user.setUserEmail(request.getParameter("userEmail"));
		user.setGender(request.getParameter("gender"));
		user.setAddress(request.getParameter("address"));
		user.setUserTel(request.getParameter("userTel"));
		user.setDob(year+"-"+month+"-"+day);
		if(user.getUserID().equals("") || user.getUserPW().equals("") || user.getUserName().equals("") || user.getHiragana().equals("") ||
				user.getUserEmail().equals("") || user.getGender().equals("") || user.getAddress().equals("") || user.getUserTel().equals("") ||
				user.getUserID() == null || user.getUserPW() == null || user.getUserName() == null || user.getHiragana() == null ||
				user.getUserEmail() == null || user.getGender() == null || user.getAddress() == null || user.getUserTel() == null) {
			out.println("<script>");
			out.println("alert('入力してない所があります。')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			try {
				dao.join(user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginform.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
