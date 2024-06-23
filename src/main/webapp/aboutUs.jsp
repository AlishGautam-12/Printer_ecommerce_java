<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>About Us</title>
    <%@include file="Components/common.jsp"%>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        form {
          
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        textarea {
            height: 100px;
        }
        button[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<%@include file="Components/nav.jsp"%>
    
    
    
    <form action="contactInfoServlet" method="post">
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        
        <label for="message">Message:</label>
        <textarea id="message" name="message" required></textarea><br>
        
        <button type="submit">Save</button>
    </form>
</body>
</html>
