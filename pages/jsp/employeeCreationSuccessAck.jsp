<!DOCTYPE html>
<html>
<head>

    <title>Employee Registration Acknowledgement</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
        h1{
		text-transform: capitalize;
		text-align:center;
		color:white;
        font-weight: bolder;
		background-color: #0058A0;
		padding:20px 0px;
		position:fixed;
		width:100%;
		top:0;
        margin:0;
	}
        body{
            font-family: "Poppins", sans-serif;
            
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            background-color: white;
            width: 400px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 20px;
            text-align: center;
        }
        .card h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }
        .card table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        .card table th, .card table td {
            text-align: left;
            padding: 10px;
        }
        .card table th {
            background-color: #f4f4f9;
            color: #555;
        }
        .card table tr:nth-child(odd) {
            background-color: #f9f9f9;
        }
        .card table tr:nth-child(even) {
            background-color: #fff;
        }
        .card button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .card button:hover {
            background-color: #0056b3;
        }
        #footer{
		position:fixed;
		bottom:0px;
		text-align:center;
		background-color: #0058A0;
		width:100%;
		color:white;
		padding:3px;
	}
    </style>
</head>
<body>
    <h1>bank of gachibowli</h1>
    <div class="card">
        <h3>Employee Registration Acknowledgement</h3>
        <!-- Employee Details -->
        <table>
            <tr>
                <th>Field</th>
                <th>Details</th>
            </tr>
            <tr>
                <td>Employee ID</td>
                <td>${employee.id}</td>
            </tr>
            <tr>
                <td>Name</td>
                <td>${employee.name}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${employee.email}</td>
            </tr>
            <tr>
                <td>Contact No</td>
                <td>${employee.contactNumber}</td>
            </tr>
        </table>
        <!-- Login Button -->
        <form action="/bog/login/employee" method="get">
            <button type="submit">Go to Login</button>
        </form>
    </div>
    <div id='footer'><a href="/bog/contact" style="color:white">@Team8</a></div>
</body>
</html>