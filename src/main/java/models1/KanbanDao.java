package models1;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import commons.Status;

import java.sql.*;

/**
 * java.sql.* 패키지 임포트
1. JDBC 드라이버 로딩
2. 데이터베이스 접속을 위한 Connection객체 생성
3. 쿼리문을 실행하기 위한 Statement/PreparedStatement/CallableStatement 객체 생성
4. 쿼리 실행
5. 쿼리 실행 결과 값(int, ResultSet) 사용
6. 사용된 객체(ResultSet, Statement/PreparedStatement/CallableStatement, Connection) 종료
 */
public class KanbanDao {
	
	private Connection conn;
	
	// DB 가져오기 
	private Connection getConnection() {
		//이미 Connection 객체가 있다면 재활용
		if(conn!=null) {
			return conn;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/kanban?user=root&password=82everywin";
			conn = DriverManager.getConnection(url);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 추가 -> 4. 쿼리문을 실행하기 위한 Statement/PreparedStatement/CallableStatement 객체 생성
	 * @param kanban
	 * @return
	 */
	public boolean add(Kanban kanban) { 
		
		String sql = "INSERT INTO works(status, subject) VALUE(?, ? )";
		try(PreparedStatement pstmt=getConnection().prepareStatement(sql)){
			pstmt.setString(1,kanban.getStatus().toString());
			pstmt.setString(2,kanban.getSubject());
			
			int cnt = pstmt.executeUpdate();
			
			return cnt>0;
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 수정
	 * 
	 * @param kanban
	 * @return
	 */
	public boolean edit(Kanban kanban) {
		String sql = "UPDATE works "+"SET "+" status=?,"+" subject=?  "+ "modDt = ? "
				+" WHERE id = ?";
		try(PreparedStatement pstmt= getConnection().prepareStatement(sql)){
			pstmt.setString(1,kanban.getStatus().toString());
			pstmt.setString(2,kanban.getSubject());
			pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); //java.sql.Timestamp
			pstmt.setInt(4, kanban.getId());
			
			int cnt = pstmt.executeUpdate();
			
			return cnt>0;
			}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	/** 
	 * 삭제
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		
		String sql= "DELETE FROM works WHERE id = ?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)){
			pstmt.setInt(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			return cnt>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 목록
	 * @return
	 */
	public List<Kanban> gets() {
		List<Kanban> kanbans =new ArrayList<>();
		String sql = "SELECT * FROM works ORDER BY regDt";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Kanban kanban = getKanban(rs);
				kanbans.add(kanban);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return kanbans;
	}
	
	/**
	 * 개별 조회
	 * @param id
	 * @return
	 */
	public Kanban get(int id ) {
		Kanban kanban = null;
		String sql = "SELECT * FROM works WHERE id = ?";
		try(PreparedStatement pstmt= getConnection().prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				kanban = getKanban(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return kanban;
	}

// SETTER를 대신하여 만든 메서드 BUILDER => BUILDER 패턴
	private Kanban getKanban(ResultSet rs ) throws SQLException{
	Timestamp _modDt=rs.getTimestamp("modDt");
	LocalDateTime modDt=_modDt==null? null : _modDt.toLocalDateTime();
	Kanban kanban = Kanban.builder()
							.id(rs.getInt("id"))
							.status(Status.valueOf(rs.getString("status")))
							.subject(rs.getString("subject"))
							.regDt(rs.getTimestamp("regDt").toLocalDateTime())
							.modDt(modDt)
							.build();
	
	return kanban;
	}
}
