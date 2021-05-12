package reservationAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReservationBean;
import model.ReservationDAO;
import model.UserBean;

@WebServlet("/reservationAction")
public class ReservationAction extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false); // 既にsessionが存在している場合そのsessionを返す。ない場合はnullを返す
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		ReservationBean reservation = new ReservationBean();
		ReservationDAO dao = new ReservationDAO();
		UserBean user = (UserBean) session.getAttribute("user");
		String time = TimeCheck.timeSet(request.getParameter("checked")); // inputの値を時間に変換させる
		String date = request.getParameter("fulldate").substring(0, 10) + " " + time; // YY-MM-DD,Time空白はないため " "追加

		reservation.setUserID(user.getUserID());
		reservation.setUserName(user.getUserName());
		reservation.setHiragana(user.getHiragana());
		reservation.setGender(user.getGender());
		reservation.setUserTel(user.getUserTel());
		reservation.setDob(user.getDob());
		reservation.setMedicineCode(Integer.parseInt(request.getParameter("medicine")));
		reservation.setDate(date); // 時間を受けてそこにYY-MM-DD全部あるfulldateと合わせる
		try {
			int result = 0;
			result = dao.reserve(reservation);
			if (result == -1) {
				out.println("<script>");
				out.println("alert('予約出来ませんでした。')");
				out.println("history.back();");
				out.println("</script>");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
}
