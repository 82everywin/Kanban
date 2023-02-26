package models.service;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import models1.Kanban;
import models1.KanbanDao;

//개별 조회
public class InfoService {
	private KanbanDao dao;

	public InfoService(KanbanDao dao) {
		this.dao = dao;
	}

	public Kanban get(HttpServletRequest req) {
		String _id = req.getParameter("id");
		if (_id == null || _id.isBlank()) {
			throw new RuntimeException("잘못된 접근입니다.");
		}

		int id = Integer.parseInt(_id);
		Kanban kanban = dao.get(id);
		if(kanban==null) {
			throw new RuntimeException("작업 내용이 없습니다.");
		}
		return kanban;
	}
}
