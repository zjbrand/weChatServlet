package my;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class My
 */
public class My extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public My() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/my.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");		
		
		String msg="";
		
		HttpSession session=request.getSession(true);
		
		String email= (String)session.getAttribute("email");
		
		try {
			
			Connection conn=DBUtil.getConnection();
			
			Statement statement = conn.createStatement();
			
			String sql="SELECT `email`, `PASSWORD` FROM `user2` WHERE email='"+email+"'";
			
			ResultSet resultSet = statement.executeQuery(sql);
			//int num = statement.executeUpdate(sql);
			
			String email1=null;
			String password=null;			
			
			while(resultSet.next()) {
				
				email1=resultSet.getString("email");
				
				password=resultSet.getString("password");
				
			}
			
			if(email1==null) {
				
				msg="このメールアドレスが登録されない。";
				
			}
			response.getWriter().append(email1+"/"+password);
			
			conn.close();
			
						
		}catch(Exception e) {
			System.out.println(e);
			msg="JDBCのアクセスに失敗した";
		}

		response.getWriter().append(msg);
	
	}

}
