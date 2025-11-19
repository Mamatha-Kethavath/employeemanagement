package employeemanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeRegistering extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();       
        String eid = request.getParameter("eid");
        String ename = request.getParameter("ename");
        String esal = request.getParameter("esal");
        String edesg = request.getParameter("edesg");
        String econtact = request.getParameter("econtact");
        String eadd = request.getParameter("eadd");
        String eage = request.getParameter("eage");
        String eemail = request.getParameter("eemail");
        String epswrd = request.getParameter("epswrd");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps", "root", "root");            
            String query = "INSERT INTO employee (eid, ename, esal, edesg, econtact, eadd, eage, eemail, epswrd) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(eid));
            ps.setString(2, ename);
            ps.setDouble(3, Double.parseDouble(esal));
            ps.setString(4, edesg);
            ps.setString(5, econtact);
            ps.setString(6, eadd);
            ps.setInt(7, Integer.parseInt(eage));
            ps.setString(8, eemail);
            ps.setString(9, epswrd);
            
            int i = ps.executeUpdate();
            if(i > 0){
                out.println("<html><body bgcolor='lightblue'><h2>Employee Registered Successfully!</h2></body></html>");
            } else {
                out.println("<html><body bgcolor='lightblue'><h2>Registration Failed. Try Again!</h2></body></html>");
            }         
            con.close();
            
        } catch(Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            e.printStackTrace(out);
        }
    }
}
