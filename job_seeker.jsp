<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job Seeker Profile</title>
</head>
<body>

<h2>Complete Your Job Seeker Profile</h2>

<%-- Display message from servlet (if any) --%>
<% String message = (String) request.getAttribute("message");
   String error = (String) request.getAttribute("errorMessage");
   if (message != null) { %>
   <p style="color:green;"><%= message %></p>
<% } else if (error != null) { %>
   <p style="color:red;"><%= error %></p>
<% } %>

<form action="JobSeekerProfileServlet" method="post">
    <label for="full_name">Full Name:</label><br>
    <input type="text" id="full_name" name="full_name" required><br><br>

    <label for="phone">Phone:</label><br>
    <input type="text" id="phone" name="phone" required><br><br>

    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address" required><br><br>

    <label for="education">Education:</label><br>
    <textarea id="education" name="education" rows="3" cols="50" required></textarea><br><br>

    <label for="skills">Skills (comma-separated):</label><br>
    <textarea id="skills" name="skills" rows="2" cols="50" required></textarea><br><br>

    <label for="experience">Years of Experience:</label><br>
    <input type="number" id="experience" name="experience" min="0" required><br><br>

    <label for="resume">Resume File Name:</label><br>
    <input type="text" id="resume" name="resume" placeholder="example_resume.pdf" required><br><br>

    <input type="submit" value="Save Profile">
</form>

</body>
</html>
