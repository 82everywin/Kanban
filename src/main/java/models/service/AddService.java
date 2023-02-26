package models.service;

import javax.servlet.http.HttpServletRequest;

import commons.Status;
import models1.*;;

public class AddService {
	private KanbanDao dao;
	
	public AddService (KanbanDao dao) {
		this.dao=dao;
	}
	public void add(HttpServletRequest request) {
		
		String _status=request.getParameter("status");
		Status status = _status == null ? Status.READY : Status.valueOf(_status);
		String subject=request.getParameter("subject");
		
		/**데이터 유효성 검사 S
		 *  - 필수 항목 ( 작업내용 - subject)
		 */
		if(subject ==null || subject.isBlank()) {
			throw new RuntimeException("작업 내용을 입력하세요.");
		}
		/** 데이터 유효성 검사 E*/
		
		Kanban kanban = Kanban.builder()
											.status(status)
											.subject(subject)
											.build();
		boolean result = dao.add(kanban);
		if( !result ) { //추가 실패
			throw new RuntimeException("작업 추가 실패했습니다.");
		}
	}
}