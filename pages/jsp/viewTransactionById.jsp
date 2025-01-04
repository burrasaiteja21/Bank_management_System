<%@ page import="model.Transaction" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transaction Details</title>
    <style>
     @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
    body {
        font-family: "Poppins", sans-serif;
        margin:0px;
    }
    h1{
        margin:0px;
    }
    h1 {
        text-align: center;
        color: white;
        background-color: #0058A0;
        padding: 20px 0;
    }
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #0058A0;
            color: white;
        }
         #logout {
        font-size: small;
        text-align: right;
        margin: 0px 30px;
        cursor: pointer;
        text-decoration: underline;
    }
    #footer {
        position: fixed;
        bottom: 0;
        text-align: center;
        background-color: #0058A0;
        width: 100%;
        color: white;
        padding: 3px;
    }
    </style>
</head>
<body>
    <h1>Bank of Gachibowli <div id="logout"><a href="/bog/logout" style="color: white;">logout</a></div></h1>
 
    <h2 style="text-align: center;">Transaction details</h2>
    <table>
        <tr>
            <th>Transaction ID</th>
            <th>From Account</th>
            <th>To Account</th>
            <th>Amount</th>
            <th>Action</th>
            <th>time</th>
        </tr>
        <tr>
            <td>${transaction.getTransactionId()}</td>
            <td class="from">${transaction.getFromAccount()}</td>
            <td class="to">${transaction.getToAccount()}</td>
            <td>${transaction.getAmount()}</td>
            <td class="action"></td>
            <td class="time">${transaction.getDateTime()}</td>
        </tr>
    </table>
    <div id="footer"><a href="/bog/contact" style="color:white">@Team8</a></div>
    <script>
        let timeElements = document.getElementsByClassName("time")
        for(let i=0;i<timeElements.length;i++){
        	let value = timeElements[i].innerText;
        	timeElements[i].innerText = new Date(Number(value));
        }
        let accountElements = document.getElementsByClassName("account")
        for(let i=0;i<accountElements.length;i++){
        	let value = accountElements[i].innerText;
        	if(value == "null"){
                accountElements[i].innerText="";
            }
        }
        
        let fromAccountElements = document.getElementsByClassName("from")
        let toAccountElements = document.getElementsByClassName("to")
        let actionElements = document.getElementsByClassName("action")
        for(let i=0;fromAccountElements.length;i++){
            if(fromAccountElements[i].innerText == ""){
                actionElements[i].innerText = "deposit"
            }
            else if(toAccountElements[i].innerText == ""){
                actionElements[i].innerText = "withdraw"
            }
            else{
                actionElements[i].innerText = "transfer"
            }
        }
        </script>
</body>
</html>