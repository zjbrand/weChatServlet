package login;

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
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		

		response.setContentType("text/html;charset=UTF-8");		
		
		String msg="";		
		
		try {
			
			Connection conn=DBUtil.getConnection();
			
			Statement statement = conn.createStatement();
			
			String sql="SELECT `email`, `PASSWORD` FROM `user2` WHERE email='"+email+"' AND password='"+password+"'";
			
			ResultSet resultSet = statement.executeQuery(sql);
			//int num = statement.executeUpdate(sql);
			
			int count=0;
			
			while(resultSet.next()) {
				//var email1=resultSet.getString("email");
				//var name=resultSet.getString("student_name");
				//System.out.println(email1);
				count=count+1;
			}
			
			conn.close();
			
			if(count==0) {
				
				msg="メールかパスワードは正しくない。" ;
				
			}else {
				
				msg="ログインに成功しました。" + "<a href=\"my.html\">ユーザー情報変更</a>";
				
				HttpSession session=request.getSession(true);
				
				session.setAttribute("email", email);
				
			}			
			
		}catch(Exception e) {
			System.out.println(e);
			msg="JDBCのロードに失敗した";
		}

		response.getWriter().append(msg);
	}

}
