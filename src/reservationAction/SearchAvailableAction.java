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

import model.ReservationBean;
import model.ReservationDAO;

@WebServlet("/searchAvailableAction") //予約時間変更で日付を検索するアクション
public class SearchAvailableAction extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String>infolist = new ArrayList<String>();
		HttpSession session = request.getSession(true);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReservationBean reservation = new ReservationBean();
		reservation = (ReservationBean) session.getAttribute("reservation");
		int medicineCode = Integer.parseInt(request.getParameter("medicineCode")); //パラメータで持ってこないと検索した後診察科コードが更新ができない
		String fulldate = request.getParameter("date");
		String date = fulldate.substring(0, 10); // 2021-05-12 00:00:00 //日付の検索をした後、キャレンダー日付を設定するため
		
		ReservationDAO dao = new ReservationDAO(); 
		infolist = dao.reserveCheckList(date, medicineCode); //DateTimeを全部入れる
		//何時に予約があるか分別
		
		String[] time = TimeCheck.timeCheck(infolist);
		
		date = fulldate.substring(5,10); //日付
		session.removeAttribute("date");
		session.removeAttribute("time");
		session.setAttribute("date", date);
		session.setAttribute("time", time);
		request.setAttribute("fulldate", fulldate); //予約する時のActionページで必要。年度を指定するため
		request.setAttribute("medicineCode", medicineCode);	//予約する時診察科コードを引き渡すため
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Member/UpdateReserve/CheckUpdateTimeForm.jsp");
		dispatcher.forward(request, response);
		
	}

}
