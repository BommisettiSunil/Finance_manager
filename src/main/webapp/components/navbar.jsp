<nav class="navbar navbar-dark">

<div class="container-fluid">

<span class="navbar-brand">

<i class="bi bi-list"></i> Finance Manager

</span>

<div class="d-flex gap-2">

<button onclick="toggleDarkMode()" class="btn btn-dark">

<i class="bi bi-moon"></i>

</button>

<a class="btn btn-danger"
href="LogoutServlet"
onclick="return confirm('Are you sure you want to logout?')">

<i class="bi bi-power"></i>

</a>

</div>

</div>

</nav>

<!-- SIDEBAR -->

<div class="sidebar">

<a href="DashboardServlet">
<i class="bi bi-speedometer2"></i>
<span>Dashboard</span>
</a>

<a href="addCustomer.jsp">
<i class="bi bi-person-plus"></i>
<span>Add Customer</span>
</a>

<a href="addLoan.jsp">
<i class="bi bi-cash-stack"></i>
<span>Give Loan</span>
</a>

<a href="addPayment.jsp">
<i class="bi bi-credit-card"></i>
<span>Collect EMI</span>
</a>

<a href="searchCustomer.jsp">
<i class="bi bi-search"></i>
<span>Search Customer</span>
</a>

<a href="ViewCustomersServlet">
<i class="bi bi-people"></i>
<span>View Customers</span>
</a>


<a href="ViewPaymentsServlet">
<i class="bi bi-receipt"></i>
<span>Payment History</span>
</a>

</div>
