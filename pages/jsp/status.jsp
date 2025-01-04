<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
        body {
            margin: 0;
            font-family: "Poppins", sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
            flex-direction: column;
        }

        h1 {
            text-align: center;
            color: white;
            background-color: #0058A0;
            padding: 20px 0;
            position: fixed;
            width: 100%;
            top: 0;
            margin:0px;
        }

        #footer {
            text-align: center;
            background-color: #0058A0;
            width: 100%;
            color: white;
            padding:2px 0px;
            position: absolute;
            bottom: 0;
        }

        .container {
            text-align: center;
            background-color: whitesmoke;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0px 0px 6px 1px rgba(0, 0, 0, 0.2);
            width: 300px;
        }

        .status-message {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .status-message.error {
            color: red;
        }

        .status-message.success {
            color: green;
        }

        button {
            width: 100%;
            border: none;
            cursor: pointer;
            color: lightgrey;
            background-color: black;
            padding: 10px;
            border-radius: 5px;
            text-transform: capitalize;
        }

        #logout {
            font-size: small;
            text-align: right;
            margin: 0 30px;
            cursor: pointer;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Bank of Gachibowli</h1>

    <div class="container">
        <% 
            model.Status st = (model.Status) request.getAttribute("status"); 
            if (st != null) {
                String message = st.getMessage();
                boolean isError = st.getIsError();
        %>
            <div class="status-message <%= isError ? "error" : "success" %>">
                <%= message %>
            </div>
        <% } %>

        <button onclick="goBack()">Go Back</button>
    </div>

    <div id="footer"><a href="/bog/contact" style="color:white">@Team8</a></div>

    <script>
        function goBack() {
            history.back();
        }
    </script>
</body>
</html>