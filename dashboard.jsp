<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            background-color: #f4f4f4;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        h1, h2 {
            color: #333;
        }
        form {
            display: inline;
        }
    </style>
</head>
<body>
    <h1>Welcome, <%= session.getAttribute("username") %>!</h1>

    <form method="post" action="LogoutServlet">
        <button type="submit">Logout</button>
    </form>

    <h2>Available Courses</h2>
    <table border="1">
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.courseId}</td>
                <td>${course.courseName}</td>
                <td>${course.instructor}</td>
                <td><a href="EnrollServlet?courseId=${course.courseId}">Enroll</a></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Your Enrolled Courses</h2>
    <ul>
        <c:forEach items="${enrolledCourses}" var="course">
            <li>${course.courseName} (${course.courseId})</li>
        </c:forEach>
    </ul>
</body>
</html>
