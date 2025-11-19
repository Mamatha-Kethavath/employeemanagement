package employeemanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String eemail = request.getParameter("eemail").trim();
        String epswrd = request.getParameter("epswrd").trim();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps", "root", "root");
            String query = "SELECT * FROM employee WHERE eemail=? AND epswrd=?";
            ps = con.prepareStatement(query);
            ps.setString(1, eemail);
            ps.setString(2, epswrd);
            rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("eid", rs.getInt("eid"));
                session.setAttribute("ename", rs.getString("ename"));
                session.setAttribute("eemail", rs.getString("eemail"));

                out.println("<h2>Login Successful!</h2>");
                out.println("<h3>Welcome, " + rs.getString("ename") + "</h3>");
                out.println("<p>Employee ID: " + rs.getInt("eid") + "</p>");
                out.println("<p>Salary: " + rs.getDouble("esal") + "</p>");
                out.println("<p>Designation: " + rs.getString("edesg") + "</p>");
                out.println("<p>Contact: " + rs.getString("econtact") + "</p>");
                out.println("<p>Address: " + rs.getString("eadd") + "</p>");
                out.println("<p>Age: " + rs.getInt("eage") + "</p>");
                out.println("<p>Email: " + rs.getString("eemail") + "</p>");
            } else {
                out.println("<html><body bgcolor='lightblue'><h2>Login Failed! Invalid Email or Password.</h2></body></html>");
            }

        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            e.printStackTrace(out);
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ex) {}
            try { if (ps != null) ps.close(); } catch (SQLException ex) {}
            try { if (con != null) con.close(); } catch (SQLException ex) {}
        }
    }
}
