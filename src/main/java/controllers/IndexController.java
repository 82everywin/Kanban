package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.Status;
import models.service.ListService;
import models.service.ServiceManager;
import models1.Kanban;

@WebServlet("/index.jsp")
public class IndexController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ListService service = ServiceManager.getInstance().getListService();
		
		Map<Status, List<Kanban>> kanbans = service.gets();
		req.setAttribute("kanbans", kanbans);
		
		/**
		List<Kanban> list = service.gets();
		req.setAttribute("list", list);
		*/
	
		RequestDispatcher rd = req.getRequestDispatcher("main/index.jsp");
		rd.forward(req, resp);
	}

}
