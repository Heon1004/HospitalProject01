package memberAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;
import model.UserDAO;

@WebServlet("/memberloginAction")
public class MemberLoginAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		Boolean result;
				
		UserBean user = new UserBean();
		UserDAO dao = new UserDAO();
		
			try {
				result = dao.login(userID,userPW);
				if(!result) {
					out.println("<script>");
					out.println("alert('IDまたはパスワード間違っています。')");
					out.println("history.back();");
					out.println("</script>");
//					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginform.jsp");
//					dispatcher.forward(request, response);
				}else {
					user = dao.getUser(userID);
					session.setAttribute("user",user);
					RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
					dispatcher.forward(request, response);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	}
}
