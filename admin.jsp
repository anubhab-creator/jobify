<%@ page import="java.sql.*,java.util.*" %>
<%@ page import="jakarta.servlet.http.*,jakarta.servlet.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body { font-family: Arial; background: #f5f5f5; padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #333; color: white; }
        a.btn { padding: 4px 8px; background: red; color: white; text-decoration: none; border-radius: 5px; }
    </style>
</head>
<body>
<h1>Welcome Admin</h1>

<%-- Users Table --%>
<h2>All Users</h2>
<jsp:include page="AdminServlet" >
    <jsp:param name="action" value="viewUsers"/>
</jsp:include>

<%-- Jobs Table --%>
<h2>All Jobs</h2>
<jsp:include page="AdminServlet" >
    <jsp:param name="action" value="viewJobs"/>
</jsp:include>

<%-- Applications Table --%>
<h2>All Applications</h2>
<jsp:include page="AdminServlet" >
    <jsp:param name="action" value="viewApplications"/>
</jsp:include>

</body>
</html>
