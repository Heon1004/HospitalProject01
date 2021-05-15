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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false); //既にsessionが存在している場合そのsessionを返す。ない場合はnullを返す
		ReservationDAO dao = new ReservationDAO();
		List<ReservationBean>reservationList = new ArrayList<ReservationBean>();
		List<ReservationBean>pageList = new ArrayList<ReservationBean>();
		UserBean user =  (UserBean) session.getAttribute("user");
		int available = Integer.parseInt(request.getParameter("available"));
		int p;
		if(session.getAttribute("reservationList") == null) {
			if(available != 2) {
				reservationList = dao.getReservationList(user.getUserID(),available);
			}else{
				reservationList = dao.getAllReservationList(user.getUserID());
			}
		}
		//10個ずつ表示する
		int listsize = reservationList.size()-1; // sizeが25だとしたらforでendlenは25まで行っちゃうから-1
		p = (request.getParameter("p") == null ? 1 : Integer.parseInt(request.getParameter("p")));
		
		int startNum = (p-1)*10; // 0 -> 10 -> 20-> 30
		int endNum = p*10-1; // 9 -> 19 -> 29 -> 39
		int endPage = listsize/10+1; // 表示するPageの番号 リストが3番目までしたない場合は1-2-3まで表示する。
		if(listsize < endNum) {
			endNum = listsize;
		}
		for(int i= startNum; i<=endNum; i++ ) {
			pageList.add(reservationList.get(i));
		}
		request.setAttribute("listsize", listsize);
		request.setAttribute("p", p);
		request.setAttribute("endPage", endPage);
		request.setAttribute("available", available);
		session.setAttribute("pageList", pageList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Member/UpdateReserve/MyReservationListForm.jsp");//予約状況確認ページへ
		dispatcher.forward(request, response);
	}
}
