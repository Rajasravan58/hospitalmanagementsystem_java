

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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
            String insertPatientQuery = "INSERT INTO patients (first_name, last_name, date_of_birth, gender, address, phone,password) VALUES (?, ?, ?, ?, ?, ?,?)";
     

            PreparedStatement patientStatement = conn.prepareStatement(insertPatientQuery);
            patientStatement.setString(1, firstName);
            patientStatement.setString(2, lastName);
            patientStatement.setString(3, dob);
            patientStatement.setString(4, gender);
            patientStatement.setString(5, address);
            patientStatement.setString(6, phone);
            patientStatement.setString(7, password);

            patientStatement.executeUpdate();


            response.sendRedirect("Login.html");

            patientStatement.close();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
	}

}
