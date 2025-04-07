package in.sp.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/JobServlet")
public class jobs extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // ✅ Retrieve user session details
        HttpSession session = req.getSession();
        Integer employer_id = (Integer) session.getAttribute("session_userid");  // Use session ID

        if (employer_id == null) {
            req.setAttribute("errorMessage", "Session expired or not logged in. Please log in again.");
            RequestDispatcher rd = req.getRequestDispatcher("/1.jsp");
            rd.forward(req, resp);
            return;
        }

        // ✅ Retrieve job seeker details from form (no employer_id)
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        int salary = Integer.parseInt(req.getParameter("salary"));  // Convert to int
        String location = req.getParameter("location");
        int experience = Integer.parseInt(req.getParameter("experience"));
        String job_type = req.getParameter("job_type");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "Anwesha2014$");

            String query = "INSERT INTO jobs (employer_id, title, description, category, salary, location, experience, job_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, employer_id);
            pst.setString(2, title);
            pst.setString(3, description);
            pst.setString(4, category);
            pst.setInt(5, salary);
            pst.setString(6, location);
            pst.setInt(7, experience);
            pst.setString(8, job_type);

            int count = pst.executeUpdate();

            if (count > 0) {
                req.setAttribute("message", "Job posted successfully!");
                RequestDispatcher rd = req.getRequestDispatcher("/jobs.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("errorMessage", "Failed to post job.");
                RequestDispatcher rd = req.getRequestDispatcher("/jobs.jsp");
                rd.include(req, resp);
            }

            pst.close();
            con.close();

        } catch (Exception e) {
            req.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/jobs.jsp");
            rd.include(req, resp);
        }
    }
}
