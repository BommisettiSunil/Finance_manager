<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="components/header.jsp" %>
<%@ include file="components/navbar.jsp" %>

<div class="main-content">

<div class="card">

<div class="card-header bg-warning">
Give Loan
</div>

<div class="card-body">
<%
String msg = request.getParameter("msg");

if("success".equals(msg)){
%>

<div class="alert alert-success alert-dismissible fade show" role="alert">
    Loan Added Successfully ✅
    
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
</div>

<%
}
%>

<form action="AddLoanServlet" method="post">

<div class="mb-3">
<label>Customer ID</label>
<input type="text" name="customer_id" class="form-control">
</div>

<div class="mb-3">
<label>Loan Amount</label>
<input type="text" name="loan_amount" class="form-control">
</div>

<div class="mb-3">
<label>Interest Rate</label>
<input type="text" name="interest_rate" class="form-control">
</div>

<div class="mb-3">
<label>Interest Type</label>
<select name="interest_type" class="form-control">
<option value="NORMAL">Normal EMI</option>
<option value="MONTHLY_FLAT">Monthly Flat Interest</option>
</select>
</div>

<div class="mb-3">
<label>Duration (Months)</label>
<input type="text" name="duration" class="form-control">
</div>

<!-- ADD THIS FIELD HERE -->

<div class="mb-3">
<label>Loan Start Date</label>
<input type="date" name="start_date" class="form-control">
</div>

<button class="btn btn-primary">Give Loan</button>

</form>

</div>
</div>

</div>

<%@ include file="components/footer.jsp" %>