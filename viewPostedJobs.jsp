<%@ page import="java.sql.*" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("session_email") == null || !"employer".equals(session.getAttribute("session_role"))) {
        response.sendRedirect("login.jsp");
        return;
    }

    String email = (String) session.getAttribute("session_email");
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobify", "root", "Anwesha2014$");

    pst = con.prepareStatement("SELECT * FROM jobs WHERE employer_id = (SELECT user_id FROM users WHERE email = ?)");
    pst.setString(1, email);
    rs = pst.executeQuery();
%>
<html>
<head>
    <title>View Posted Jobs</title>
</head>
<body>
    <center>
        <h2>📋 Jobs You've Posted</h2>
        <table border="1" cellpadding="10">
            <tr>
                <th>Job Title</th>
                <th>Category</th>
                <th>Location</th>
                <th>Salary</th>
                <th>Experience</th>
                <th>Posted Date</th>
            </tr>
            <%
                while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("title") %></td>
                <td><%= rs.getString("category") %></td>
                <td><%= rs.getString("location") %></td>
                <td><%= rs.getString("salary") %></td>
                <td><%= rs.getInt("experience") %> years</td>
                <td><%= rs.getTimestamp("posted_date") %></td>
            </tr>
            <% } %>
        </table>
        <br><a href="employer.jsp">🔙 Back to Dashboard</a>
    </center>
</body>
</html>
