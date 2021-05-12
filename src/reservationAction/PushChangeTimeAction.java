package reservationAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import model.ReservationBean;
import model.ReservationDAO;
import model.UserBean;

@WebServlet("/pushChangeTimeAction")
public class PushChangeTimeAction extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		if (request.getParameter("changeTime") != null) {
			int medicineCode = Integer.parseInt(request.getParameter("medicineCode"));
			request.setAttribute("medicineCode", medicineCode);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Member/UpdateReserve/DetailUpdateTimeForm.jsp");// 後でここには予約状況確認ページへ
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession(true); // 既にsessionが存在している場合そのsessionを返す。false=ない場合はnullを返す
			// true=New session
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			UserBean user = new UserBean();
			ReservationBean reservation = new ReservationBean();
			user = (UserBean) session.getAttribute("user");
			int reservationNumber = Integer.parseInt(request.getParameter("number"));

			if (user.getUserID() != null || !user.getUserID().equals("")) {
				ReservationDAO reservationDAO = new ReservationDAO();
				reservation = reservationDAO.getReservation(reservationNumber, user.getUserID());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Member/UpdateReserve/DetailUpdateTimeForm.jsp");// 後でここには予約状況確認ページへ
				dispatcher.forward(request, response);
			} else {
				out.println("<script>");
				out.println("alert('アクセスできません。ログインをしてください。')");
				out.println("location.href = 'main.jsp'");
				out.println("</script>");
			}

		}
	}
}
