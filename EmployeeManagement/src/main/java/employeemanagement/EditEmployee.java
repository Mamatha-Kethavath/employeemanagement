package employeemanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EditEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String idParam = req.getParameter("eid");
        if (idParam == null || idParam.isEmpty()) {
            out.println("<h3 style='color:red'>Employee ID missing!</h3>");
            return;
        }
        int eid = Integer.parseInt(idParam);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps", "root", "root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE eid=?");
            ps.setInt(1, eid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<html>");
                out.println("<body bgcolor='lightblue'>");
                out.println("<h2 style='text-align:center;'>Edit Employee</h2>");
                out.println("<form action='UpdateEmployee' method='post'>");
                out.println("<table border='1' cellpadding='10' cellspacing='0' "
                        + "align='center' style='background:white; border-radius:5px;'>");
                out.println("<input type='hidden' name='eid' value='" + eid + "'>");
                out.println("<tr><td>Name</td>"
                        + "<td><input type='text' name='ename' value='" + rs.getString("ename") + "'></td></tr>");
                out.println("<tr><td>Age</td>"
                        + "<td><input type='number' name='eage' value='" + rs.getInt("eage") + "'></td></tr>");
                out.println("<tr><td>Designation</td>"
                        + "<td><input type='text' name='edesg' value='" + rs.getString("edesg") + "'></td></tr>");
                out.println("<tr><td>Salary</td>"
                        + "<td><input type='number' name='esal' value='" + rs.getDouble("esal") + "'></td></tr>");
                out.println("<tr><td>Contact</td>"
                        + "<td><input type='text' name='econtact' value='" + rs.getString("econtact") + "'></td></tr>");
                out.println("<tr><td>Address</td>"
                        + "<td><input type='text' name='eadd' value='" + rs.getString("eadd") + "'></td></tr>");
                out.println("<tr><td>Email</td>"
                        + "<td><input type='email' name='eemail' value='" + rs.getString("eemail") + "'></td></tr>");
                out.println("<tr><td>Password</td>"
                        + "<td><input type='text' name='epswrd' value='" + rs.getString("epswrd") + "'></td></tr>");
                out.println("<tr><td colspan='2' style='text-align:center;'>"
                        + "<input type='submit' value='Update'></td></tr>");
                out.println("</table>");
                out.println("</form>");
                out.println("</body></html>");

            } else {
                out.println("<html><body bgcolor='lightblue'><h3>No employee found!</h3></body></html>");
            }
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
