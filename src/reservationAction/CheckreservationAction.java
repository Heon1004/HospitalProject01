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

import model.ReservationBean;
import model.ReservationDAO;

@WebServlet("/checkreservationAction")
public class CheckreservationAction extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false); //既にsessionが存在している場合そのsessionを返す。ない場合はnullを返す
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReservationDAO dao = new ReservationDAO();
		ArrayList<ReservationBean>reservationList = new ArrayList<ReservationBean>();
		String userID = (String) session.getAttribute("userID");
		int available = Integer.parseInt(request.getParameter("available"));
		
		if(available != 2) {
			reservationList = dao.getReservationList(userID,available);
		}else if(available == 2){
			reservationList = dao.getAllReservationList(userID);
		}else { 
			out.println("<script>");
			out.println("alert('予約記録がありません。')");
			out.println("history.back();");
			out.println("</script>");	
		}
		
		session.setAttribute("reservationList", reservationList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/serachedReservationform.jsp");//後でここには予約状況確認ページへ
		dispatcher.forward(request, response);
	}
}
