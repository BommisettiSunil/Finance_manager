<%@ include file="components/header.jsp" %>

<div class="main-content">

<div class="container mt-5">

<div class="card col-md-4 offset-md-4">

<div class="card-header bg-dark text-white">
Admin Login
</div>

<div class="card-body">

<form action="LoginServlet" method="post">

<div class="mb-3">
<label>Username</label>
<input type="text" name="username" class="form-control">
</div>

<div class="mb-3">
<label>Password</label>
<input type="password" name="password" class="form-control">
</div>

<button class="btn btn-primary w-100">Login</button>

</form>

<br>

<a href="signup.jsp">New Admin? Sign Up</a>

</div>
</div>

</div>

</div>

<%@ include file="components/footer.jsp" %>
