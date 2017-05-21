package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Status;
import com.opensymphony.xwork2.ActionContext;
import com.util.DB;

public class StatusService {
	public List<Status> list(){
		Connection conn=DB.createConn();
		String sql="select * from _status order by rankid desc";
		PreparedStatement ps=DB.prepare(conn, sql);	
		List<Status> status=new ArrayList<Status>();
		try {
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Status s=new Status();
				s.setRankid(rs.getInt("rankid"));
				s.setUserid(rs.getString("userid"));
				s.setId(rs.getString("id"));
				s.setName(rs.getString("name"));
				s.setTime(rs.getString("time"));
				s.setDate(rs.getString("date"));
				s.setResult(rs.getString("result"));
				
				status.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
		return status;
	}
	
	public void add(){
		Connection conn=DB.createConn();
		String sql="insert into _status values(null,?,?,?,?,?,?)";
		PreparedStatement ps=DB.prepare(conn, sql);	
		try {
			//get from UserService().login()
			String userid = (String)ActionContext.getContext().getSession().get("userid");
			//get from CategoryService().loadById()
			String proid = (String)ActionContext.getContext().getSession().get("pro");
			String proname = (String)ActionContext.getContext().getSession().get("name");
			//get from UserService().judgeCode()
			String time = (String)ActionContext.getContext().getSession().get("time");
			String date = (String)ActionContext.getContext().getSession().get("date");
			String result = (String)ActionContext.getContext().getSession().get("ans");
			
			ps.setString(1, userid);
			ps.setString(2, proid);
			ps.setString(3, proname);
			ps.setString(4, time);
			ps.setString(5, date);
			ps.setString(6, result);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
	}

}
