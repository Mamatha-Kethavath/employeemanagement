package employeemanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String idParam = req.getParameter("eid");

        if (idParam == null) {
            out.println("<html><body bgcolor='lightblue'><h3>ID missing</h3></body></html>");
            return;
        }
        int eid = Integer.parseInt(idParam);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps", "root", "root");
            PreparedStatement ps = con.prepareStatement("DELETE FROM employee WHERE eid=?");
            ps.setInt(1, eid);
            int deleted = ps.executeUpdate();

            
            if (deleted > 0) {
                res.sendRedirect("AdminViewEmployees");
            } else {
                out.println("<html><body bgcolor='lightblue'><h3>Delete failed!</h3></body></html>");
            }
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
