<%@ page import="com.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% 
if (request.getParameter("itemCode") != null)
{
//session.setAttribute("itemCode", request.getParameter("itemCode"));
//session.setAttribute("itemName", request.getParameter("itemName"));
//session.setAttribute("itemPrice", request.getParameter("itemPrice"));
//session.setAttribute("itemDesc", request.getParameter("itemDesc"));

	Item itemobj = new Item();
	itemobj.connect();
	String insert = itemobj.insertString(request.getParameter("itemCode"),
										 request.getParameter("itemName"),
										 request.getParameter("itemPrice"),
										 request.getParameter("itemDesc"));
	 session.setAttribute("StatusMsg", insert);
}
    

if(request.getParameter("itemId") != null)
{
	Item itemobj = new Item();
	String delmsg = itemobj.deleteItem(request.getParameter("itemId"));
	session.setAttribute("statusMsg", delmsg);
}
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row">
			 <div class="col">
			 
			 <h1>Item Management</h1>
			 
			 <form action="item.jsp" method="post">
		Item Code: <input name="itemCode" type="text" class="form-control">
		<br> 
		Item Name: <input name="itemName" type="text" class="form-control">
		<br> 
		Item Price: <input name="itemPrice" type="text" class="form-control">
		<br> 
		Item Description: <input name="itemDesc" type="text" class="form-control">
		<br> 
		<input name="btnSubmit" type="submit" value="Save" class="btn btn-primary">
		
		</form>
			 
			<div class="alert alert-success">
			 <% out.print(session.getAttribute("StatusMsg")); %>
			</div> 
	
	<br>
	
	<% 
		Item itemobj = new Item();
		out.print(itemobj.readItems());
	%>
			 </div>
		</div>
	</div>
	
	<br>
</body>
</html>