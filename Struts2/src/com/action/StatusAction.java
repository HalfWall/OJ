package com.action;


import java.util.List;

import com.model.Status;
import com.opensymphony.xwork2.ActionSupport;
import com.service.StatusService;

public class StatusAction extends ActionSupport{
	private List<Status> statues;
	private Status status;
	private StatusService statusService=new StatusService();
	
	public String list(){
		statues=statusService.list();
		return SUCCESS;
	}
	public String add(){
		statusService.add();
		return SUCCESS;
	}
	
	
	
	public List<Status> getStatues() {
		return statues;
	}
	public void setStatues(List<Status> statues) {
		this.statues = statues;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public StatusService getStatusService() {
		return statusService;
	}
	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}
	

}
