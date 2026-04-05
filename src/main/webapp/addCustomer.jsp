<%@ include file="components/header.jsp" %>
<%@ include file="components/navbar.jsp" %>

<div class="main-content">

<div class="container mt-4">

<h3 class="mb-4">Add Customer</h3>

<%
String msg = request.getParameter("msg");

if("success".equals(msg)){
%>

<div class="alert alert-success">
Customer Added Successfully
</div>

<%
}
%>

<form action="AddCustomerServlet" method="post">

<div class="mb-3">
<input type="text" 
name="name" 
class="form-control" 
placeholder="Customer Name" 
required>
</div>

<div class="mb-3">
<input type="text" 
name="phone" 
class="form-control" 
placeholder="Phone Number" 
required>
</div>

<div class="mb-3">
<input type="text" 
name="address" 
class="form-control" 
placeholder="Address">
</div>

<div class="mb-3">
<input type="email" 
name="email" 
class="form-control" 
placeholder="Email Address">
</div>

<button class="btn btn-success">
Add Customer
</button>

</form>

</div>

</div>

<%@ include file="components/footer.jsp" %>
