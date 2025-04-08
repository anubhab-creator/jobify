package in.sp.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "Anwesha2014$");

            if ("viewUsers".equals(action)) {
                PreparedStatement pst = con.prepareStatement("SELECT * FROM users");
                ResultSet rs = pst.executeQuery();

                out.println("<table><tr><th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Action</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("user_id") + "</td><td>" + rs.getString("name") + "</td><td>" +
                            rs.getString("email") + "</td><td>" + rs.getString("role") + "</td>" +
                            "<td><a class='btn' href='AdminServlet?action=deleteUser&id=" + rs.getInt("user_id") + "'>Delete</a></td></tr>");
                }
                out.println("</table>");
                rs.close();
                pst.close();
            }

            else if ("viewJobs".equals(action)) {
                PreparedStatement pst = con.prepareStatement("SELECT * FROM jobs");
                ResultSet rs = pst.executeQuery();

                out.println("<table><tr><th>ID</th><th>Title</th><th>Category</th><th>Salary</th><th>Location</th><th>Action</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("job_id") + "</td><td>" + rs.getString("title") + "</td><td>" +
                            rs.getString("category") + "</td><td>" + rs.getDouble("salary") + "</td><td>" +
                            rs.getString("location") + "</td>" +
                            "<td><a class='btn' href='AdminServlet?action=deleteJob&id=" + rs.getInt("job_id") + "'>Delete</a></td></tr>");
                }
                out.println("</table>");
                rs.close();
                pst.close();
            }

            else if ("viewApplications".equals(action)) {
                PreparedStatement pst = con.prepareStatement("SELECT * FROM applications");
                ResultSet rs = pst.executeQuery();

                out.println("<table><tr><th>ID</th><th>Job ID</th><th>Seeker ID</th><th>Status</th><th>Date</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("application_id") + "</td><td>" + rs.getInt("job_id") + "</td><td>" +
                            rs.getInt("job_seeker_id") + "</td><td>" + rs.getString("status") + "</td><td>" +
                            rs.getTimestamp("applied_date") + "</td></tr>");
                }
                out.println("</table>");
                rs.close();
                pst.close();
            }

            else if ("deleteUser".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                PreparedStatement pst = con.prepareStatement("DELETE FROM users WHERE user_id = ?");
                pst.setInt(1, id);
                int count = pst.executeUpdate();
                resp.sendRedirect("admin.jsp");
            }

            else if ("deleteJob".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                PreparedStatement pst = con.prepareStatement("DELETE FROM jobs WHERE job_id = ?");
                pst.setInt(1, id);
                int count = pst.executeUpdate();
                resp.sendRedirect("admin.jsp");
            }

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
