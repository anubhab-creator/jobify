package in.sp.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginForm")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String myemail = req.getParameter("email");
        String mypass = req.getParameter("password");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/jobify";
            String dbUser = "root";
            String dbPass = "Anwesha2014$";

            con = DriverManager.getConnection(dbURL, dbUser, dbPass);

            ps = con.prepareStatement("SELECT name, email, role FROM users WHERE email=? AND password=?");
            ps.setString(1, myemail);
            ps.setString(2, mypass);

            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String role = rs.getString("role");

                HttpSession session = req.getSession();
                session.setAttribute("session_name", name);
                session.setAttribute("session_email", email);
                session.setAttribute("session_role", role);

                // ✅ Role-based redirection
                 if ("job_seeker".equals(role)) {
                    RequestDispatcher rd = req.getRequestDispatcher("/user.jsp");
                    rd.forward(req, resp);
                } else if ("employer".equals(role)) {
                    RequestDispatcher rd = req.getRequestDispatcher("/employer.jsp");
                    rd.forward(req, resp);
                } else if ("admin".equals(role)) {
                    RequestDispatcher rd = req.getRequestDispatcher("/admin.jsp");
                    rd.forward(req, resp);
                }


            } else {
                req.setAttribute("errorMessage", "Invalid Email or Password!");
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(req, resp);
            }

        } catch (Exception e) {
            req.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req, resp);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
