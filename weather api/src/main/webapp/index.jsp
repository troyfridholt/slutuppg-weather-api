<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Väder dags!</title>
</head>
<body>
	<div class="wrapper">
 		<header>
            <div><img src="img/weather100.jpeg" alt="Well, regn!"></div>
            <div>
                <h1>Weather Search</h1>
            </div>
        </header>
		 <nav>
            <a href="index.jsp">Startsida</a>
            <div class="dropdown">
                <button class="dropbtn">Hjälpmedel</button>
                <div class="dropdown-content">
                    <a href="https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes">Country Codes</a>
                    <a href="https://openweathermap.org/">Open weather map</a>
                </div>
            </div>
        </nav>
		

	<main>
 		<form action="WServlet" method="get">  
    		City:  <input type="text" name="city"/><br/>  
    		Country code:  <input type="text" name="country"/><br/>  
    		<input type="submit" value="Search Weather"/>
    	</form>  

	<div>	
<%

	Cookie[] cookies = request.getCookies();
	if( cookies != null ) {
    String ckcity = cookies[0].getValue().toString();
    String ckcountry = cookies[1].getValue().toString();
    out.print("<h1>" + "Earlier search: " + "</h1>");
    
    out.print("<a href=\"http://localhost:8080/weatherApp/WServlet?city="+ ckcity + "&country="+ ckcountry + "\">" + "<h2>" + ckcity + "</h2>"  + "</a>");}
 else{
    out.println("<h2>No cookie of earlier search found</h2>");
 }
%>
	 </div>
 	</main>

 		<footer>
            <h3>Kreativ med design.</h3>
        </footer>



	</div>
</body>
</html>