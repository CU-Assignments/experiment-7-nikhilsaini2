import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String jdbcURL = "jdbc:mysql://localhost:3306/company";
        String dbUser = "root";
        String dbPassword = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            String empId = request.getParameter("id");
            String sql = (empId == null) ? "SELECT * FROM employees" : "SELECT * FROM employees WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (empId != null) stmt.setInt(1, Integer.parseInt(empId));
            
            ResultSet rs = stmt.executeQuery();
            
            out.println("<h2>Employee Details</h2>");
            out.println("<form action='EmployeeServlet' method='get'>");
            out.println("Search by ID: <input type='text' name='id'><input type='submit' value='Search'></form>");
            
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" 
                    + rs.getString("name") + "</td><td>" 
                    + rs.getString("department") + "</td></tr>");
            }
            out.println("</table>");

            conn.close();
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
