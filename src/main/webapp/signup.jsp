<%@ include file="components/header.jsp" %>

<div class="main-content">

<div class="container mt-5">

<div class="card col-md-4 offset-md-4">

<div class="card-header bg-primary text-white">
Admin Registration
</div>

<div class="card-body">

<form action="SignupServlet" method="post">

<div class="mb-3">
<label>Full Name</label>
<input type="text" name="name" class="form-control">
</div>

<div class="mb-3">
<label>Username</label>
<input type="text" name="username" class="form-control">
</div>

<div class="mb-3">
<label>Password</label>
<input type="password" name="password" class="form-control">
</div>

<button class="btn btn-success w-100">Register</button>

</form>

</div>
</div>

</div>

</div>

<%@ include file="components/footer.jsp" %>
