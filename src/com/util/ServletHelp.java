package com.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ServletHelp {
	public static List<Class> getAllClass(String pkg) {

		try {
			String pkg1 = pkg.replace(".", "/");
			// ********
			Enumeration<URL> res = Thread.currentThread().getContextClassLoader().getResources(pkg1);
			if (res.hasMoreElements()) {
				String path = res.nextElement().getPath();

				File file = new File(path);
				String[] list = file.list();
				List<Class> clist = new ArrayList<>();

				for (String cn : list) {
					String cname = cn.substring(0, cn.lastIndexOf("."));
					Class cls = Class.forName(pkg + "." + cname);

					clist.add(cls);
				}
				return clist;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
