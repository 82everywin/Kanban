package commons;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Library {
	
	public static void alertError(HttpServletResponse res, Exception e)throws IOException{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		e.printStackTrace();
		out.printf("<script>alert('%s');</script>",e.getMessage());
	}
	
	public static void reload(HttpServletResponse res,String target) throws IOException{
	res.setContentType("text/html;charset=utf-8");
	PrintWriter out = res.getWriter();
	
	out.printf("<script> %s.location.reload();</script>", target);
	}
	
	public static void reload(HttpServletResponse res) throws IOException{
		reload(res,"self");
	}
}
