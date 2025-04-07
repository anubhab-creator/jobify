<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registration Form</title>
</head>
<body>

    <h2>Registration Form</h2>

    <form action="regForm" method="post">
        Name: <input type="text" name="name1" required /> <br/> <br/>
        Email: <input type="email" name="email1" required /> <br/> <br/>
        Password: <input type="password" name="pass1" required /> <br/> <br/>
        Role:
        <select name="role1" required>
            <option value="">Select Role</option>
            <option value="job_seeker">job_seeker</option>
            <option value="employer">employer</option>
            <option value="admin">admin</option>
        </select>
        <br/> <br/>
        <input type="submit" value="Register" />
    </form>
</body>
</html>
