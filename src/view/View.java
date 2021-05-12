package view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String display = request.getParameter("display");
		System.out.println(display);
		String page = "/WEB-INF/view/";

		switch (display) {
		case "joinForm":
			page += "joinForm.jsp";
			break;
		case "loginForm":
			page += "loginForm.jsp";
			break;
		case "logoutForm":
			page += "logoutForm.jsp";
			break;

		// Member
		case "myprofileForm":
			page += "Member/myprofileForm.jsp";
			break;
		case "myprofileUpdateForm":
			page += "Member/myprofileUpdateForm.jsp";
			break;

		// Member Reservation
		case "CheckTimeReservationForm":
			page += "Member/Reserve/CheckTimeReservationForm.jsp";
			break;
		case "ReservationForm":
			page += "Member/Reserve/ReservationForm.jsp";
			break;
		// Member MyReservation
		case "CheckMyReservationForm":
			page += "Member/UpdateReserve/CheckMyReservationForm.jsp";
			break;

		default:
			page = "main.jsp";
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
