package models.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.Status;

import  static commons.Status.*;
import models1.Kanban;
import models1.KanbanDao;

public class ListService {

	private KanbanDao dao;
	
	public ListService(KanbanDao dao) {
		this.dao=dao;
	}
	
	public Map<Status, List<Kanban>> gets() {
		List<Kanban> list= dao.gets();
		List<Kanban> ready= new ArrayList<> ();
		List<Kanban> progress = new ArrayList<> ();
		List<Kanban> done= new ArrayList<> ();
		
		for (Kanban kanban : list) {
			switch (kanban.getStatus()) {
			case READY:
				ready.add(kanban);
				break;
			case PROGRESS:
				progress.add(kanban);
				break;
			case DONE:
				done.add(kanban);
				break;
			} //end switch
		}//end for
		
		Map<Status, List<Kanban>> kanbans = new HashMap<> ();
		kanbans.put(READY, ready);
		kanbans.put(PROGRESS, progress);
		kanbans.put(DONE, done);
		
		return kanbans;
 	}

}
