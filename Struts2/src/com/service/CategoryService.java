package com.service;

import static java.lang.System.out;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.model.Category;
import com.opensymphony.xwork2.ActionContext;
import com.util.DB;

public class CategoryService {
	public List<Category> list(){
		Connection conn=DB.createConn();
		String sql="select * from _category";
		PreparedStatement ps=DB.prepare(conn, sql);	
		List<Category> categories=new ArrayList<Category>();
		try {
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Category c=new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setContext(rs.getString("context"));
				c.setTestIn(rs.getString("testIn"));
				c.setTestOut(rs.getString("testOut"));
				categories.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
		return categories;
	}

	public boolean add(Category c){
		boolean flag=false;
		Connection conn=DB.createConn();
		String sql="insert into _category values(?,?,?,?,?)";
		PreparedStatement ps=DB.prepare(conn, sql);	
		try {
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());
			ps.setString(3, c.getContext());
			ps.setString(4, c.getTestIn());
			ps.setString(5,c.getTestOut());
			ps.executeUpdate();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag=false;
		}
		DB.close(ps);
		DB.close(conn);	
		return flag;
	}
	public void delete(Category c){
		deleteById(c.getId());
	}
	public void deleteById(int id){
		Connection conn=DB.createConn();
		String sql="delete from _category where id = ?";
		PreparedStatement ps=DB.prepare(conn, sql);	
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
	}
	public boolean update(Category c){
		boolean flag=false;
		Connection conn=DB.createConn();
		String sql="update _category set name = ?, context = ?,testIn = ?,testOut = ? where id = ?";
		PreparedStatement ps=DB.prepare(conn, sql);	
		try {
			ps.setString(1, c.getName());
			ps.setString(2, c.getContext());
			ps.setString(3, c.getTestIn());
			ps.setString(4, c.getTestOut());
			ps.setInt(5,c.getId());
			ps.executeUpdate();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag=false;
		}
		DB.close(ps);
		DB.close(conn);		
		
		return flag;
	}

	public Category loadById(int id){
		Connection conn=DB.createConn();
		String sql="select * from _category where id = ?";
		PreparedStatement ps=DB.prepare(conn, sql);	
		Category c=null;
		try {
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				c=new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setContext(rs.getString("context"));
				c.setTestIn(rs.getString("testIn"));
				c.setTestOut(rs.getString("testOut"));
				String prolist=String.valueOf(c.getId());
				String pname=c.getName();
				ActionContext.getContext().getSession().put("pro", prolist);
				ActionContext.getContext().getSession().put("name", pname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
		
		return c;
	}
	

	//写入in out 文件
	public boolean InOut(Category c){
		boolean res=true;
		String str=c.getTestIn();
		String str2=c.getTestOut();
		String prolist=String.valueOf(c.getId());
		System.out.println(c.getId());
		File f1 = new File("F:\\Tool\\Myeclipse\\workspace\\Struts2\\judge\\problem",
				prolist+".in");
		File f2 = new File("F:\\Tool\\Myeclipse\\workspace\\Struts2\\judge\\problem",
				prolist+".out");
		String filename1 = f1.getAbsolutePath();
		String filename2 = f2.getAbsolutePath();

		try {
			if (!f1.exists())// 如果文件不存，则建立
			{
				f1.createNewFile();
			}
			if (!f2.exists())// 如果文件不存，则建立
			{
				f2.createNewFile();
			}
			PrintWriter pw = new PrintWriter(new FileOutputStream(filename1));
			PrintWriter pw2 = new PrintWriter(new FileOutputStream(filename2));
			pw.println(str);
			pw2.println(str2);// 写内容
			pw.close();
			pw2.close();
		} catch (IOException e) {
			out.println(e.getMessage());
			res=false;
		}
		
		return res;
	}
	
	//删除in out 文件
	public void fileDelete(int id){
		String prolist=String.valueOf(id);
		File f1 = new File("F:\\Tool\\Myeclipse\\workspace\\Struts2\\judge\\problem",
				prolist+".in");
		File f2 = new File("F:\\Tool\\Myeclipse\\workspace\\Struts2\\judge\\problem",
				prolist+".out");
		if (f1.exists())// 如果文件存在，则删除
		{
			f1.delete();
		}
		if (f2.exists())// 如果文件存在，则删除
		{
			f2.delete();
		}
	}
	
}
