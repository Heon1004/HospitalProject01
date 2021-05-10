package reservationAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReservationDAO;

@WebServlet("/reservationInfoAction")
public class ReservationInfoAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String>infolist = new ArrayList<String>();
		HttpSession session = request.getSession(false);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!request.getParameter("date").equals("") && request.getParameter("date") != null && 
				!request.getParameter("medicine").equals("") && request.getParameter("medicine") != null) {
			int medicine = Integer.parseInt(request.getParameter("medicine")); 
			String fulldate = request.getParameter("date");
			String date = fulldate.substring(0, 10); // 2021-05-12 00:00:00 //日付の検索をした後、キャレンダー日付を設定するため
			
			
			ReservationDAO dao = new ReservationDAO(); 
			infolist = dao.reserveInfo(date, medicine); //DateTimeを全部入れる
			//何時に予約があるか分別
			
			String[] time = TimeCheck.timeCheck(infolist);
			
			date = fulldate.substring(5,10); //日付
			
			session.setAttribute("date", date);
			session.setAttribute("time", time);
			request.setAttribute("fulldate", fulldate); //予約する時のActionページで必要。年度を指定するため
			request.setAttribute("medicine", medicine);	//予約する時診察科コードを引き渡すため
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reservationform.jsp");
			dispatcher.forward(request, response);
		}else {
			out.println("<script>");
			out.println("alert('選択されてない項目があります。')");
			out.println("history.back();");
			out.println("</script>");	
		}
		
	}
}
