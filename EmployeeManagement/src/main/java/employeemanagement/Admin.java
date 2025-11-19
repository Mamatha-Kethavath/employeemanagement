package employeemanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<html>");
        out.println("<body bgcolor='lightblue'>");
        out.println("<h2>ALL EMPLOYEE DETAILS</h2>");

        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr>"
                + "<th>EID</th>"
                + "<th>Name</th>"
                + "<th>Age</th>"
                + "<th>Designation</th>"
                + "<th>Salary</th>"
                + "<th>Contact</th>"
                + "<th>Address</th>"
                + "<th>Email</th>"
                + "<th>Password</th>"
                + "<th>Edit</th>"
                + "<th>Delete</th>"
                + "</tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps", "root", "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employee");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("eid") + "</td>");
                out.println("<td>" + rs.getString("ename") + "</td>");
                out.println("<td>" + rs.getInt("eage") + "</td>");
                out.println("<td>" + rs.getString("edesg") + "</td>");
                out.println("<td>" + rs.getDouble("esal") + "</td>");
                out.println("<td>" + rs.getString("econtact") + "</td>");
                out.println("<td>" + rs.getString("eadd") + "</td>");
                out.println("<td>" + rs.getString("eemail") + "</td>");
                out.println("<td>" + rs.getString("epswrd") + "</td>");

                out.println("<td><a href='EditEmployee?eid=" + rs.getInt("eid") + "'>Edit</a></td>");
                out.println("<td><a href='DeleteEmployee?eid=" + rs.getInt("eid") + "'>Delete</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
