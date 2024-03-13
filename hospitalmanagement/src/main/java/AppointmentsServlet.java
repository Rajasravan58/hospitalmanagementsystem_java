

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AppointmentsServlet
 */
@WebServlet("/AppointmentsServlet")
public class AppointmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("appouintment");
		String appointmentDateTime = request.getParameter("datetime");
        String reason = request.getParameter("reason");
 		 
        System.out.print( appointmentDateTime+reason);
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
            String query = "INSERT INTO appointments ( appointment_datetime, reason) VALUES ( ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
             statement.setString(1, appointmentDateTime);
            statement.setString(2, reason);
            statement.executeUpdate();

            response.sendRedirect("success.html");

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
	}
	}


