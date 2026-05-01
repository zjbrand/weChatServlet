package friends;

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
 * Servlet implementation class Friends
 */
public class Friends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Friends() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/friends.html").forward(request, response);
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
			
			String html="";
			
			for(String s:list) {
				html+="<a href='message.html?email="+s+"'>"+s+"</a><br>";
			}
			
			response.getWriter().append(html);
			
			conn.close();
			
						
		}catch(Exception e) {
			System.out.println(e);
			msg="JDBCのアクセスに失敗した";
		}

		response.getWriter().append(msg);
	}

}
