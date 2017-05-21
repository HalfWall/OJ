package com.action;

import java.io.IOException;
import java.util.List;

import com.model.Category;
import com.model.Status;
import com.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CategoryService;
import com.service.StatusService;
import com.service.UserService;

public class UserAction extends ActionSupport{
	private List<User> users;
	private User user;
	private UserService userservice=new UserService();
	private String userid;
	
	private Category category;
	private List<Status> statues;
	private StatusService statusService=new StatusService();
	
	

	public String list(){
		users=userservice.list();
		return SUCCESS;
	}
	
	
	public String login(){		
		return INPUT;
	}
	public String regist(){
		return INPUT;
	}
	public String loginSuccess(){	
		boolean flag=userservice.login(user);
		boolean result=userservice.checkPIN(user);
		boolean str=userservice.loginManager(user);
		if(flag&&result&&str)
			return "limit";
		else if(flag&&result)
			return SUCCESS;
		else
			return ERROR;
	}
	public String registSuccess(){
		boolean result=userservice.registCheck(user);
		if(result)
			return SUCCESS;
		else 
			return ERROR;
	}
	
	public String delete(){
		userservice.deleteById(userid);
		return SUCCESS;
	}
	
	public String judgeCode() throws IOException, InterruptedException{
		userservice.judgeCode(user);
		userservice.sum();
		statusService.add();
		statues=statusService.list();
		return SUCCESS;
	}
	public String status(){
		statues=statusService.list();
		return SUCCESS;
	}
	
	public String reset(){
		userservice.reset(userid);
		return SUCCESS;
	}
	
	public String updateInput(){
		this.user=this.userservice.loadById();
		return INPUT;
	}
	public String update(){
		userservice.update(user);
		return SUCCESS;
	}
	
	
	
	
 	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Status> getStatues() {
		return statues;
	}

	public void setStatues(List<Status> statues) {
		this.statues = statues;
	}

	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	
}
