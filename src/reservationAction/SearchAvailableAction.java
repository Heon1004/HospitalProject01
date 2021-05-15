package reservationAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/searchAvailableAction") //予約時間変更で日付を検索するアクション
public class SearchAvailableAction extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReservationBean reservation = (ReservationBean) session.getAttribute("reservation");
		String submit = request.getParameter("submit-btn");
		if(submit == null) {
			int medicineCode = Integer.parseInt(request.getParameter("medicineCode")); //パラメータで持ってこないと検索した後診察科コードが更新ができない
			String fulldate = request.getParameter("date");
			String date = fulldate.substring(0, 10); // 2021-05-12 00:00:00 //日付の検索をした後、キャレンダー日付を設定するため
			ReservationDAO dao = new ReservationDAO(); 
			List<String>infolist = new ArrayList<String>();
	
			infolist = dao.reserveCheckList(date, medicineCode); //DateTimeを全部入れる
			String[] time = TimeCheck.timeCheck(infolist); //何時に予約があるか分別 0 0 1 0 だと10:00時
			date = fulldate.substring(5,10); //日付
			session.setAttribute("date", date);
			session.setAttribute("time", time);
			request.setAttribute("fulldate", fulldate); //予約する時のActionページで必要。年度を指定するため
			request.setAttribute("medicineCode", medicineCode);	//予約する時診察科コードを引き渡すため
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Member/UpdateReserve/CheckUpdateTimeForm.jsp");
			dispatcher.forward(request, response);
		}else {
			try {
				ReservationDAO dao = new ReservationDAO();
				String time = TimeCheck.timeSet(request.getParameter("checked")); // inputの値を時間に変換させる
				String date = request.getParameter("fulldate").substring(0, 10) + " " + time; // YY-MM-DD,Time空白はないため " "追加
				reservation.setMedicineCode(Integer.parseInt(request.getParameter("medicineCode")));
				reservation.setDate(date);
				int result = dao.update(reservation);
				if (result !=1) {
					out.println("<script>");
					out.println("alert('変更が出来ませんでした。')");
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

}
