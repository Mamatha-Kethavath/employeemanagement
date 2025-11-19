package employeemanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateEmployee extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int eid = Integer.parseInt(req.getParameter("eid"));
        String ename = req.getParameter("ename");
        int eage = Integer.parseInt(req.getParameter("eage"));
        String edesg = req.getParameter("edesg");
        double esal = Double.parseDouble(req.getParameter("esal"));
        String econtact = req.getParameter("econtact");
        String eadd = req.getParameter("eadd");
        String eemail = req.getParameter("eemail");
        String epswrd = req.getParameter("epswrd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps", "root", "root");
            PreparedStatement ps = con.prepareStatement("UPDATE employee SET ename=?, eage=?, edesg=?, esal=?, econtact=?, eadd=?, eemail=?, epswrd=? WHERE eid=?");

            ps.setString(1, ename);
            ps.setInt(2, eage);
            ps.setString(3, edesg);
            ps.setDouble(4, esal);
            ps.setString(5, econtact);
            ps.setString(6, eadd);
            ps.setString(7, eemail);
            ps.setString(8, epswrd);
            ps.setInt(9, eid);
            int updated = ps.executeUpdate();

            if (updated > 0) {
                res.sendRedirect("AdminViewEmployees");
            } else {
                out.println("<html><body bgcolor='lightblue'><h3>Update failed!</h3></body></html>");
            }
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
