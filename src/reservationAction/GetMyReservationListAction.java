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

import model.MedicineDAO;
import model.ReservationBean;
import model.ReservationDAO;
import model.UserBean;

@WebServlet("/getMyReservationListAction")
public class GetMyReservationListAction extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false); //既にsessionが存在している場合そのsessionを返す。ない場合はnullを返す
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReservationDAO dao = new ReservationDAO();
		List<ReservationBean>reservationList = new ArrayList<ReservationBean>();
		UserBean user =  (UserBean) session.getAttribute("user");
		int available = Integer.parseInt(request.getParameter("available"));
		
		if(available != 2) {
			reservationList = dao.getReservationList(user.getUserID(),available);
		}else if(available == 2){
			reservationList = dao.getAllReservationList(user.getUserID());
		}else { 
			out.println("<script>");
			out.println("alert('予約記録がありません。')");
			out.println("history.back();");
			out.println("</script>");	
		}
		request.setAttribute("available", available);
		session.setAttribute("reservationList", reservationList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Member/UpdateReserve/MyReservationListForm.jsp");//予約状況確認ページへ
		dispatcher.forward(request, response);
	}
}
