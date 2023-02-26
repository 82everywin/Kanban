package models.service;

import models1.KanbanDao;

/**
 * 각각의 서비스를 모두 모아서 관리
 * @author LG
 *
 */
public class ServiceManager {
	
	static private ServiceManager instance;
	
	private ServiceManager() {};
	
	public KanbanDao kanbanDao() {
		return new KanbanDao();
	}
	
	public AddService getAddService() {
		return new AddService(kanbanDao());
	}
	
	public EditService getEditService() {
		return new EditService(kanbanDao());
	}
	
	public DeleteService getDeleteService() {
		return new DeleteService(kanbanDao());
	}
	
	public ListService getListService() {
		return new ListService(kanbanDao());
	}
	
	public InfoService getInfoService() {
		return new InfoService(kanbanDao());
	}
	
	public static ServiceManager getInstance() {
		if(instance==null) {
			instance =new ServiceManager();
		}
		return instance;
	}
}
