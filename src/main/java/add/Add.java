package add;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import com.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add
 */
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Add() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/add.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//feature ブランチで修正しました
		//response.getWriter().append("posttttt ");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password_confirm");

		response.setContentType("text/html;charset=UTF-8");		
		
		String msg="";
		
		if (password.equals( password_confirm )) {			
		
//		Connection conn=null;
//		String url="jdbc:mysql://localhost/wechat";
//		String user="root";
//		String passwordConnect="123456";
		
		try {
			
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			conn=DriverManager.getConnection(url, user,passwordConnect);
			
			Connection conn=DBUtil.getConnection();
;			
			Statement statement = conn.createStatement();
			
			String sql="INSERT INTO `user2` (`email`, `PASSWORD`, `NAME`, `birthday`, `gender`, `STATUS`, `updateTime`) VALUES\r\n"
					               + "('"+email+"', '"+password+"',NULL,NULL,NULL,NULL,CURRENT_TIMESTAMP())";
			
			//ResultSet resultSet = statement.executeQuery(s);
			int num = statement.executeUpdate(sql);
			
//			while(resultSet.next()) {
//				var id=resultSet.getInt("student_id");
//				var name=resultSet.getString("student_name");
//				System.out.println(id+name);
//			}
			conn.close();
			
			msg="ユーザー登録しました" + "<a href='login.html'>トップへ戻る</a>";
			
		}catch(Exception e) {
			System.out.println(e);
			msg="JDBCのロードに失敗した";
		}
		
		
     }else {
    	 
    	 msg = "パスワードが一致しません。";

	}
//		if (password.equals( password_confirm )) {
//
//			result = "ユーザー登録しました" + "<a href='login.html'>トップへ戻る</a>";
//
//		} else {
//
//			result = "パスワードが一致しません。";
//
//		}

		response.getWriter().append(msg);

	}

}
