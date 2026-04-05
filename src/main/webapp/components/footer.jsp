<script>

function loadData(type){

document.getElementById("resultSection").innerHTML = "Loading...";

let url = "";

if(type === "totalLoan"){
    url = "TotalLoanServlet";
}
else if(type === "interest"){
    url = "InterestServlet";
}
else if(type === "due"){
    url = "AllDueServlet";
}
else if(type === "today"){
    url = "TodayCollectionServlet";
}

fetch(url)
.then(res => res.text())
.then(data => {
    document.getElementById("resultSection").innerHTML = data;
});

}

function toggleDarkMode(){
document.body.classList.toggle("dark-mode");
}

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>