package employeemanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AdminViewEmployees extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps", "root", "root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee");
            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<body bgcolor='lightblue'>");
            out.println("<h2 style='text-align:center;'>ALL EMPLOYEE RECORDS</h2>");
            out.println("<table border='1' cellpadding='10' cellspacing='0' "
                    + "align='center' style='background:white; border-radius:5px;'>");
            out.println("<tr>"
                    + "<th>ID</th>"
                    + "<th>Name</th>"
                    + "<th>Email</th>"
                    + "<th>Salary</th>"
                    + "<th>Designation</th>"
                    + "<th>Contact</th>"
                    + "<th>Address</th>"
                    + "<th>Age</th>"
                    + "<th>Edit</th>"
                    + "<th>Delete</th>"
                    + "</tr>");

            
            while (rs.next()) {
                int eid = rs.getInt("eid");
                out.println("<tr>");
                out.println("<td>" + eid + "</td>");
                out.println("<td>" + rs.getString("ename") + "</td>");
                out.println("<td>" + rs.getString("eemail") + "</td>");
                out.println("<td>" + rs.getDouble("esal") + "</td>");
                out.println("<td>" + rs.getString("edesg") + "</td>");
                out.println("<td>" + rs.getString("econtact") + "</td>");
                out.println("<td>" + rs.getString("eadd") + "</td>");
                out.println("<td>" + rs.getInt("eage") + "</td>");

                out.println("<td><a href='EditEmployee?eid=" + eid + "'>Edit</a></td>");
                out.println("<td><a href='DeleteEmployee?eid=" + eid + "'>Delete</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.print("<button><a style='text-decoration:none; color:black;' href='./index.html'>Back</a></button>");
            out.println("</body></html>");           
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
