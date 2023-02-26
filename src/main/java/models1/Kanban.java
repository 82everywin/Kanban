package models1;

import java.time.LocalDateTime;
import commons.Status; 

public class Kanban {
	private int id;// 작업번호
	private Status status; // 작업 상태
	private String subject;  //작업 내용
	private LocalDateTime	regDt; // 등록 일시
	private LocalDateTime	modDt; // 수정 일시
	
	public int getId() {
		return id;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public LocalDateTime getRegDt() {
		return regDt;
	}
	
	public LocalDateTime getModDt() {
		return modDt;
	}
	
	public static Builder builder()	{
		return new Builder();
	}

	public static class Builder{
		private Kanban kanban = new Kanban();
		
		public Builder id(int id) {
			kanban.id=id;
			return this;
		}
		public Builder status(Status status) {
			kanban.status=status;
			return this;
		}
		
		public Builder subject(String subject) {
			kanban.subject=subject;
			return this;
		}
		
		public Builder regDt(LocalDateTime regDt) {
			kanban.regDt=regDt;
			return this;
		}
		
		public Builder modDt(LocalDateTime modDt) {
			kanban.modDt=modDt;
			return this;
		}
		
		public Kanban build() {
			return kanban;
		}
	}
	
}