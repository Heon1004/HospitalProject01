package memberAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;
import model.UserDAO;

@WebServlet("/memberupdateAction")
public class MemberUpdateAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false); //既にsessionが存在している場合そのsessionを返す。ない場合はnullを返す
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UserBean user = new UserBean();
		UserDAO dao = new UserDAO();
		
		user.setUserID(request.getParameter("userID"));
		user.setUserPW(request.getParameter("userPW"));
		user.setUserName(request.getParameter("userName"));
		user.setHiragana(request.getParameter("hiragana"));
		user.setUserEmail(request.getParameter("userEmail"));
		user.setGender(request.getParameter("gender"));
		user.setAddress(request.getParameter("address"));
		user.setUserTel(request.getParameter("userTel"));
		
		if(user.getUserPW().equals("") || user.getUserName().equals("") || user.getHiragana().equals("") ||
				user.getUserEmail().equals("") || user.getGender().equals("") || user.getAddress().equals("") || user.getUserTel().equals("") ||
				user.getUserPW() == null || user.getUserName() == null || user.getHiragana() == null ||
				user.getUserEmail() == null || user.getGender() == null || user.getAddress() == null || user.getUserTel() == null) {
			out.println("<script>");
			out.println("alert('入力してない所があります。')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			try {
				int result = dao.update(user);
				if(result != -1) {
					user = dao.getUser(user.getUserID()); //既にuserIDは持ってるし、変えることが出来ない為??05/09:もう一度確認すこと
					session.setAttribute("user",user);
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}else {
					out.println("<script>");
					out.println("alert('アップデートに失敗しました')");
					out.println("history.back();");
					out.println("</script>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
