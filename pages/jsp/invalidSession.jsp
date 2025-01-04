<html>
    <head>
        <title>
            invalid <%
                String user = (String)request.getAttribute("user");
                out.print(user);
            %> session
        </title>
    </head>
    <body>
        <script>
            let user = document.title.split(" ")[1]
            alert(`Invalid ${user} session, please login`);
            window.location.replace(`/bog/login/${user}`);
        </script>
    </body>
</html>