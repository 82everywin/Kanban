package commons;

public enum Status {

/** 
 * 작업 상태
 */
	READY, //준비중
	PROGRESS, // 진행중
	DONE;  //완료
	
	public static String getStatus(Status status) {
		switch(status){
		case PROGRESS: 
			return "진행중";
		case DONE:
			return "완료";
		default:
			return "준비중";
		
		}
	}
	}
