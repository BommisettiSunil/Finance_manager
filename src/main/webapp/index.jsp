<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="components/header.jsp" %>
<%@ include file="components/navbar.jsp" %>

<div class="main-content">
<div class="container mt-4">

<h2 class="mb-4">Dashboard</h2>

<%
Object loanObj = request.getAttribute("totalLoan");
double loan = loanObj == null ? 0 : ((Number)loanObj).doubleValue();

Object todayObj = request.getAttribute("todayCollection");
double today = todayObj == null ? 0 : ((Number)todayObj).doubleValue();

Object intObj = request.getAttribute("interestCollected");
double interest = intObj == null ? 0 : ((Number)intObj).doubleValue();
%>

<!-- 🔵 TOP ROW (NON-CLICKABLE) -->
<div class="row">

    <!-- Total Loan -->
    <div class="col-md-6">
        <div class="card text-white bg-primary mb-3 shadow">
            <div class="card-body">
                <h5>Total Loan Given</h5>
                <h3>₹ <%= String.format("%.2f", loan) %></h3>
            </div>
        </div>
    </div>

    <!-- Interest -->
    <div class="col-md-6">
        <div class="card text-dark bg-warning mb-3 shadow">
            <div class="card-body">
                <h5>Total Interest Collected</h5>
                <h3>₹ <%= String.format("%.2f", interest) %></h3>
            </div>
        </div>
    </div>

</div>

<!-- 🔴 BOTTOM ROW (CLICKABLE) -->
<div class="row">

    <!-- Overdue -->
    <div class="col-md-6">
        <div class="card text-white bg-danger mb-3 shadow"
             onclick="loadData('due')" style="cursor:pointer;">
            <div class="card-body">
                <h5>Overdue Amount</h5>
                <h6 class="text-light opacity-75">Click to view</h6>
            </div>
        </div>
    </div>

    <!-- Today Collection -->
    <div class="col-md-6">
        <div class="card text-white bg-success mb-3 shadow"
             onclick="loadData('today')" style="cursor:pointer;">
            <div class="card-body">
                <h5>Today's Collection</h5>
                <h6 class="text-light opacity-75">Click to view</h6>
                <h3>₹ <%= String.format("%.2f", today) %></h3>
            </div>
        </div>
    </div>

</div>

<!-- RESULT SECTION -->
<div id="resultSection" class="mt-4"></div>

</div>
</div>

<%@ include file="components/footer.jsp" %>