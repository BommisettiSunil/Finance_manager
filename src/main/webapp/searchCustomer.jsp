<%@ include file="components/header.jsp" %>
<%@ include file="components/navbar.jsp" %>

<div class="main-content">

<div class="card">

<div class="card-header bg-info text-white">
Search Customer
</div>

<div class="card-body">

<form action="SearchCustomerServlet" method="get">

<div class="mb-3">
<label>Customer Name</label>
<input type="text" name="name" class="form-control">
</div>

<button class="btn btn-primary">Search</button>

</form>

</div>
</div>

</div>

<%@ include file="components/footer.jsp" %>
