<%@ include file="components/header.jsp" %>
<%@ include file="components/navbar.jsp" %>

<div class="main-content">

<div class="container mt-4">

<h3 class="mb-4">Collect EMI Payment</h3>

<%
String msg = request.getParameter("msg");

if("success".equals(msg)){
%>

<div class="alert alert-success">
Payment Recorded Successfully
</div>

<%
}

String loanId = request.getParameter("loan_id");
%>

<form action="AddPaymentServlet" method="post">

<div class="mb-3">

<label>Loan ID</label>

<input type="text"
name="loan_id"
class="form-control"
value="<%= loanId!=null?loanId:"" %>"
required>

</div>

<div class="mb-3">

<label>Amount</label>

<input type="text"
name="amount"
class="form-control"
placeholder="Enter EMI Amount"
required>

</div>

<div class="mb-3">

<label>Payment Date</label>

<input type="date"
name="payment_date"
class="form-control"
required>

</div>

<button class="btn btn-success">
Record Payment
</button>

</form>

</div>

</div>

<%@ include file="components/footer.jsp" %>
