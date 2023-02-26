<%@ page contentType="text/html;charset=utf-8"%>

<%@ page import="java.util.*,commons.Status,models1.Kanban" %> 

<%
	// 컬렉션 프레인 워크  
	/**
	Map.Entry<Status,List<Kanban>> : 인터페이스 key-value pair(쌍) 으로 가져옴 
	kanbans.entrySet() : Key-Value pair를 집합형태로 표현한 것 
	*/
	Map<Status,List<Kanban>> kanbans = (Map<Status,List<Kanban>>)request.getAttribute("kanbans");
	for(Map.Entry<Status,List<Kanban>> entry:kanbans.entrySet()){
		Status status= entry.getKey();
		List<Kanban> list= entry.getValue();
	%>
	<ul class= "items">
		<li class="tit"> <%=Status.getStatus(status) %> </li>
		<% for ( Kanban kanban : list ) {%>
		<li class="item" data-id="<%=kanban.getId()%>"><%=kanban.getSubject()%></li>
		<%} %>
		</ul>
<%}%>