package com.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.attendance.dao.StuDao;
import com.attendance.daoimpl.StuUserDaoImpl;
import com.attendance.entity.UniversalPage;

/**
 * Servlet implementation class FindStuInfo
 */
@WebServlet("/FindStuInfo")
public class FindStuInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StuDao dao=new StuUserDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindStuInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//登陆
		//用哈希表将登陆查询的信息存储然后再添加到list集合中
		request.setCharacterEncoding("utf-8");
		//创建session对象
		//HttpSession session = request.getSession();
		String type=request.getParameter("typestr");
		String keystring=request.getParameter("keystr");
		/*if(keystring.equals("男")||keystring=="男") {
			keystring="1";
		}else if(keystring.equals("女")||keystring=="女"){
			keystring="0";
		}*/
		// 获取页号 
		//默认 初始值为1 
		int pageNo = 1; //从页面获取页号 数据
		if (request.getParameter("pageNo") != null) { 
			pageNo = Integer.parseInt(request.getParameter("pageNo")); 
		}
		String typeString=request.getParameter("findtype");
		String keyString=request.getParameter("bycontent");
		if(keyString==null||keyString.equals("")) {
			typeString="0";
		}/*else {
			typeString="2";
		}*/
		//CoursePage coursePage=new CoursePage();
		//每页显示的数据
		int pagesize=10;
		//从页面获取 name 数据(要查询的姓名) 
		//String keystr = request.getParameter("keystr");
		//查询类型
		//String typestr=request.getParameter("typestr");
		UniversalPage universalPage=dao.universalPage(typeString,keyString,pageNo, pagesize);
		//当前页   limit?,? 第一个数是从第几行开始查，不包括所在行，第二个数字是每页查询多少条数据
		//数据---->调用业务类的   根据 姓名---当前页页号(2个参数) 获取当前页的用户        
		//request.setAttribute("list", list);
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/listVideo.jsp");
		dispatcher .forward(request, response);*/
		//HashMap<String, Object> map=new HashMap<String,Object>();
		//map.put("list", list);
		/*for(int i=0;i<list.size();i++) {
			HashMap<String, Object> map=new HashMap<String, Object>();
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(i).get("create_time").toString());
			map.replace("create_time", timeStamp);
			list.add(map);
		}*/
		/*将list集合装换成json对象*/
		//String json=JSON.toJSONString(universalPage);
		Gson gson=new Gson();
		String json=gson.toJson(universalPage);
		out.print(json);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
