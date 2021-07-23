package servlet7_ajax_idcheck;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/","*.do"})
public class MyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
	void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		
		String requestURI=request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/")+1;
		String command = requestURI.substring(cmdIdx); // "idCheckAjax.do"
		String jspPage = "";
		System.out.println("command:"+command);
		if (command.equals("")||command.equals("/")) {
			response.sendRedirect("join.jsp");
		}else if(command.equals("idCheckAjax.do")) {
			String user_id = (String)request.getParameter("user_id");
			
			//DB검색해서 같은 아이디의 회원이 있는지 조사
			
			int result = 0; // 1이면 중복됨. 0이면 중복안됨.
			
			if(user_id.equals("hong")) {
				result = 1;
			}
			
			result = 0; // DAO.idCheck()
			
			if(result == 1) {
				response.getWriter().print("1"); //중복
			}else {
				response.getWriter().print("0"); //중복 안됨
			}
		}	if (!jspPage.equals("")) {
			RequestDispatcher dp = request.getRequestDispatcher(jspPage);
			dp.forward(request, response);
	}
	
	
}
}
