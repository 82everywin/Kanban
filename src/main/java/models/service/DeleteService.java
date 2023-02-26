package models.service;

import javax.servlet.http.HttpServletRequest;

import models1.KanbanDao;

public class DeleteService {
	private KanbanDao dao;
	
	public DeleteService(KanbanDao dao) {
		this.dao=dao;
	}
	
	public void delete(HttpServletRequest req) {
		
		String _id = req.getParameter("id");
		/**유효성 검사 -> id 		 */
		if(_id==null || _id.isBlank()) {
			throw new RuntimeException("잘못된 요청입니다. ");
		}
		
		/** DB삭제 처리 */
		int id =Integer.parseInt(_id);
		boolean result= dao.delete(id);
		if(!result) {
			throw new RuntimeException("삭제에 실패하였습니다.");
		}
	}
}
