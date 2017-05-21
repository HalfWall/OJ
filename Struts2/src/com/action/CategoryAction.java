package com.action;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CategoryService;
import com.model.Category;

public class CategoryAction extends ActionSupport{
	
	private List<Category> categories;
	private CategoryService categoryService=new CategoryService();
	private Category category;
	private int id;
	
	public String list(){
		categories=categoryService.list();
		return SUCCESS;
	}
	
	
	public String add(){
		boolean flag=categoryService.add(category);
		if(flag){
			categoryService.InOut(category);
			return SUCCESS;
		}else
			return "wrong";
	}
	
	public String delete(){
		categoryService.deleteById(id);
		categoryService.fileDelete(id);
		return SUCCESS;
	}
	
	public String update(){
		boolean flag=categoryService.update(category);
		if(flag){
			categoryService.InOut(category);
			return SUCCESS;
		}else
			return "wrong";
	}
	
	public String addInput(){
		return INPUT;
	}
	
	public String updateInput(){
		this.category=this.categoryService.loadById(id);
		return INPUT;
	}
	
	
	
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
