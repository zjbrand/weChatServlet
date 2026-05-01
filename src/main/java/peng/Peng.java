package peng;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Peng
 */
public class Peng extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Peng() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/peng.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");		
		
		String msg="";		
		
		HttpSession session=request.getSession(true);
		
		String from= (String)session.getAttribute("email");
		
		try {
			
			Connection conn=DBUtil.getConnection();
			
			Statement statement = conn.createStatement();
			
//			String sql="SELECT `pengcontent`,`pengfrom` FROM `friendgroup2` WHERE `pengfrom`='"+email+"'";
//			
//			ResultSet resultSet = statement.executeQuery(sql);
//			//int num = statement.executeUpdate(sql);
//			
//			String from=null;
//			String content=null;			
//			
//			while(resultSet.next()) {
//				
//				from=resultSet.getString("pengfrom");
//				
//				content=resultSet.getString("pengcontent");
//				
//				msg+=content+"<br>"+from+"<br><hr><br>";
//				
//			}			
				
			String sql1="SELECT `accepto` FROM `friend2` WHERE addfrom='"+from+"'";			
			
			ResultSet resultList1 = statement.executeQuery(sql1);
			
			//int num = statement.executeUpdate(sql);
			
			List<String> list=new ArrayList<>();		
			
			while(resultList1.next()) {
				
				list.add(resultList1.getString("accepto"));
				
			}
			
			String sql2="SELECT `addfrom` FROM `friend2` WHERE accepto='"+from+"'";
			
			ResultSet resultList2 = statement.executeQuery(sql2);
			
			while(resultList2.next()) {
				
				list.add(resultList2.getString("addfrom"));
				
			}
			
			String pengfrom="";
			String pengcontent="";
			
			for(String s:list) {
				String sqlall="SELECT `pengcontent`,`pengfrom` FROM `friendgroup2` WHERE `pengfrom`='"+s+"'";
				
				ResultSet result = statement.executeQuery(sqlall);						
				
				while(result.next()) {
					
					pengfrom=result.getString("pengfrom");
					
					pengcontent=result.getString("pengcontent");
					
					msg+=pengcontent+"<br>"+pengfrom+"<br><hr><br>";
					
				}	
			}
			
			conn.close();
			
						
		}catch(Exception e) {
			System.out.println(e);
			msg="JDBCのアクセスに失敗した";
		}

		response.getWriter().append(msg);
	}

}
