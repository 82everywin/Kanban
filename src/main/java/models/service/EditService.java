package models.service;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.Status;
import models1.Kanban;
import models1.KanbanDao;

public class EditService {
	private KanbanDao dao;
	
	public EditService(KanbanDao dao) {
		this.dao=dao;
	}
	
	public void edit(HttpServletRequest req) {
		String _id= req.getParameter("id");
		String _status=req.getParameter("status");
		String subject = req.getParameter("subject");
		
		/**
		 * 1.유효성 검사-id,status, subject 필수 항목 
		 * null "" " "
		 */
		if(_id ==null || _id.isBlank()) {
			throw new RuntimeException("작업 번호(id)는필수 항목입니다.");
		}
		
		if(_status==null|| _status.isBlank()) {
			throw new RuntimeException("작업 상태를 선택하세요.");
		}
		
		if(subject ==null|| subject.isBlank()) {
			throw new RuntimeException("작업 내용을 입력하세요.");
		}
		/**
		 * 2. DB 수정처리
		 */
		int id=Integer.parseInt(_id);
		Status status=Status.valueOf(_status);
		Kanban kanban =Kanban.builder()
										.id(id)
										.status(status)
										.subject(subject)
										.build();
		
	boolean result = dao.edit(kanban);
	if(!result) {
		throw new RuntimeException("작업 수정 실패하였습니다.");
	}
	}
}
