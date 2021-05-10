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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String display = request.getParameter("display");
		String page = "/WEB-INF/view/";
		
		switch(display) {
		case "joinform":
			page += "joinform.jsp";
			break;
		case "loginform":
			page += "loginform.jsp";
			break;
			
		case "logoutform":
			page += "logoutform.jsp";
			break;
		case "myprofileform":
			page += "myprofileform.jsp";
			break;
		case "myprofileUpdateform":
			page+= "myprofileUpdateform.jsp";
			break;
		case "reservationInfoform":
			page += "reservationInfoform.jsp";
			break;
		case "reservationform":
			page += "reservationform.jsp";
			break;
		case "checkReservationform":
			page += "checkReservationform.jsp";
			break;
			
			default:
				page = "index.jsp";
				break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
