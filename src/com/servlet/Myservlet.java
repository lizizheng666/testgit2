package com.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ann.RequestParam;
import com.ann.ResponseBody;
import com.util.ServletHelp;

public class Myservlet extends HttpServlet {

	private Map<String, Class> map = new HashMap<String, Class>();
	String pkg = "com.control";

	@Override
	public void init() throws ServletException {

		List<Class> list = ServletHelp.getAllClass(pkg);
		for (Class class1 : list) {
			Method[] ms = class1.getDeclaredMethods();
			for (Method m : ms) {
				String name = m.getName();
				map.put(name, class1);
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getServletPath();
		url = url.substring(1,url.lastIndexOf("."));
		
		Class clas = map.get(url);
		try {
		Method[] mt = clas.getDeclaredMethods();
		boolean flag = false;
		for (Method method : mt) {
			if(url.equals(method.getName())) {
				flag = true;
				Object[] obj_pa = new Object[method.getParameterCount()];
				int idx = 0;
				System.out.println("参数个数。。。" + obj_pa.length);
				// 获取参数对象数组
				Parameter[] ps = method.getParameters();
				for (Parameter p : ps) {
//                        System.out.println("参数。。。"+p.getName());
					// 遍历每一个参数
					// 判断是否有@RequestParam
					if (p.isAnnotationPresent(RequestParam.class)) {
						// 我们需要解析的参数类型 如：User
						Class pcls = p.getType(); // User.class
						// 获取所有属性名
						Field[] fs = pcls.getDeclaredFields();
						Object obj = pcls.newInstance();

						for (Field f : fs) {
							System.out.println(f.getName());

							if (req.getParameter(f.getName()) == null) {
								continue;
							}
							String na = req.getParameter(f.getName());
							f.setAccessible(true);
							f.set(obj, na);
						}

						obj_pa[idx++] = obj;

					} else if (p.getType() == HttpServletRequest.class) {
						// 如果需要的参数是REQ
						System.out.println("有个参数是HttpServletRequest");
						obj_pa[idx++] = req;
					} else if (p.getType() == String.class) {
						obj_pa[idx++] = "";
					} else {
						obj_pa[idx++] = 0;
					}

				}
				System.out.println("参数列表：" + obj_pa);
				// 调用该方法，并且传参数
				String ret = (String) method.invoke(clas.newInstance(), obj_pa);
				System.out.println("返回===" + ret);

				if (ret != null) {
					if (method.isAnnotationPresent(ResponseBody.class)) {
						// 如果方法有ResponseBody，当作普通字符串返回
						// 例如 ajax请求
						resp.getWriter().write(ret);
					} else if (ret.startsWith("redirect:")) {
						// "redirect:index.jsp";
						resp.sendRedirect(ret.substring(ret.lastIndexOf(":") + 1));
					} else {
						// "index.jsp";
						System.out.println("else............");
						req.getRequestDispatcher(ret).forward(req, resp);
					}
				}
			}
		if (flag == false) {
			System.out.println("未定义该方法" + url + "请去com.control添加该方法");
		}
		}

	} catch (Exception e) {
		System.out.println("在doPost执行时错误");
	}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
