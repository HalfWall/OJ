package com.service;

import java.io.*;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.model.Category;
import com.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.util.DB;
import com.util.Judge;

public class UserService {
	public List<User> list() {
		Connection conn = DB.createConn();
		String sql = "select * from _user order by sum desc";
		PreparedStatement ps = DB.prepare(conn, sql);
		List<User> users = new ArrayList<User>();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUserid(rs.getString("userid"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setSex(rs.getString("sex"));
				u.setSum(rs.getInt("sum"));
				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return users;
	}

	public boolean login(User user) {
		boolean flag = false;
		Connection conn = DB.createConn();
		String sql = "select * from _user where userid = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setString(1, user.getUserid());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String password = new String(rs.getString("password"));
				String sum = new String(rs.getString("sum"));
				if (password.equals(user.getPassword())) {
					ActionContext.getContext().getSession().put("userid", user.getUserid());
					ActionContext.getContext().getSession().put("sum", sum);
					System.out.println("login sum:"+sum);
					flag = true;
				} else {
					ActionContext.getContext().getSession().put("tip2", "密码错误");
					System.out.println(1);
					flag = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return flag;
	}

	public boolean checkPIN(User user) {
		boolean result = false;
		String validateC = new String();
		// HttpServletResponse response = null;
		// 将session里面的验证码提取出来
		HttpSession sessions = ServletActionContext.getRequest().getSession();
		validateC = (String) sessions.getAttribute("checkCode");
		if (user.getPINcode().equals(validateC)) {
			result = true;
		} else {
			ActionContext.getContext().getSession().put("tip2", "验证码错误");
			result = false;
		}
		return result;

	}

	public boolean loginManager(User user) {
		boolean str = false;
		if (user.getUserid().equals("admin")
				&& user.getPassword().equals("123456")) {
			ActionContext.getContext().getSession()
					.put("userid", user.getUserid());
			str = true;
		}
		return str;
	}

	public boolean regist(User user) {
		boolean result = false;
		Connection conn = DB.createConn();
		String sql = "insert into _user values(?,?,?,?,?)";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getSex());
			ps.setInt(5, 0);
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			ActionContext.getContext().getSession().put("tip", "用户名已存在");
		}
		DB.close(ps);
		DB.close(conn);
		return result;
	}

	public boolean registCheck(User user) {
		boolean result = false;
		try {
			if (!this.checkUser(user.getUserid()))
				return false;
			if (!this.checkPwd(user.getPassword()))
				return false;
			if (!this.confirmPwd(user.getPassword(), user.getPassword_Confirm()))
				return false;
			if (!this.checkEmail(user.getEmail()))
				return false;

			result = regist(user);
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

	}

	public boolean checkUser(String user) {
		try {
			if (user.length()==0) {
				ActionContext.getContext().getSession().put("tip", "用户名不能为空");
				return false;
			} else
				return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public boolean checkPwd(String pwd) {
		try {
			if (pwd.length()==0) {
				ActionContext.getContext().getSession().put("tip", "密码不能为空");
				return false;
			} else
				return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public boolean confirmPwd(String pwd, String confirm) {
		try {
			if (!pwd.equals(confirm)) {
				ActionContext.getContext().getSession().put("tip", "两次密码输入不一致");
				return false;
			} else
				return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public boolean checkEmail(String email) {
		try {
			if (email.indexOf("@") == -1) {
				ActionContext.getContext().getSession().put("tip", "邮箱格式错误");
				return false;
			} else
				return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public void delete(User u) {
		deleteById(u.getUserid());
	}

	public void deleteById(String userid) {
		Connection conn = DB.createConn();
		String sql = "delete from _user where userid = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setString(1, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
	}

	
	
	public void judgeCode(User user) throws IOException, InterruptedException {
		String str = user.getCode();
		File f1 = new File("F:\\Tool\\Myeclipse\\workspace\\Struts2\\judge",
				"test.cpp");
		String filename1 = f1.getAbsolutePath();

		try {
			if (!f1.exists())// 如果文件不存，则建立
			{
				f1.createNewFile();
			}
			PrintWriter pw = new PrintWriter(new FileOutputStream(filename1));
			pw.println(str);// 写内容
			pw.close();
		} catch (IOException e) {
			out.println(e.getMessage());
		}
		//得到题号
		String pro = ActionContext.getContext().getSession().get("pro").toString();
		System.out.println("判题题号："+pro);
		
		//判题
		Judge judge=new Judge("F:\\Tool\\MyEclipse\\workspace\\Struts2\\judge\\");
		judge.setpro("F:\\Tool\\MyEclipse\\workspace\\Struts2\\judge\\problem\\");
		
		String ans=judge.judge(pro,1000);
		String time=String.valueOf(judge.time);
		Date dates=new Date();
		String date=dates.toString();
		
		System.out.println(judge.judge(pro,1000));
		System.out.println("运行时间："+judge.time);
		System.out.println("提交时间："+date);
		
		ActionContext.getContext().getSession().put("ans", ans);
		ActionContext.getContext().getSession().put("time", time);
		ActionContext.getContext().getSession().put("date", date);
	}

	//更新AC的题目数
	public void sum(){
		Connection conn=DB.createConn();
		String sql="update _user set sum = ? where userid = ?";
		PreparedStatement ps=DB.prepare(conn, sql);	
		//get from judgeCode()
		String ans=ActionContext.getContext().getSession().get("ans").toString();
		//get from login
		String strSum=String.valueOf(ActionContext.getContext().getSession().get("sum"));
		int sum=Integer.valueOf(strSum);
		String userid=ActionContext.getContext().getSession().get("userid").toString();
		
		if(ans.equals("Accepted")){
			sum=sum+1;
			System.out.println("提交后:"+sum);
		}
		try {
			ps.setInt(1, sum);
			ps.setString(2, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
	}
	
	//重置密码
	public void reset(String userid){
		Connection conn=DB.createConn();
		String sql="update _user set password = ? where userid = ?";
		PreparedStatement ps=DB.prepare(conn, sql);	
		try {
			ps.setString(1,"1234" );
			ps.setString(2, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
		
	}

	//更新用户信息
	public void update(User user){
		Connection conn=DB.createConn();
		String sql="update _user set password = ?,email = ? where userid = ?";
		PreparedStatement ps=DB.prepare(conn, sql);	
		try {
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
	}
	public User loadById(){
		Connection conn=DB.createConn();
		String sql="select * from _user where userid = ?";
		PreparedStatement ps=DB.prepare(conn, sql);	
		User user=null;
		String userid=ActionContext.getContext().getSession().get("userid").toString();
		System.out.println("load:"+userid);
		try {
			ps.setString(1, userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				user=new User();
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setSum(rs.getInt("sum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);		
		return user;
		
	}
}
