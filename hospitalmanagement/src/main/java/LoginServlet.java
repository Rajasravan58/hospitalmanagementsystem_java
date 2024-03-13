

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String first_name = request.getParameter("firstName");
        String password = request.getParameter("password");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
            String query = "SELECT patient_id FROM patients WHERE first_name = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, first_name);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                response.sendRedirect("hospital.html");
            } else {
                response.sendRedirect("login.jsp?error=invalid");
            }

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
	}

}
