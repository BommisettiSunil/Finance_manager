<!DOCTYPE html>

<html>
<head>

<title>Finance Manager</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

<style>

body{
margin:0;
font-family:Arial;
padding-top:70px;
}

/* NAVBAR */

.navbar{
background:#0b0b0b;
height:60px;
z-index:1100;
position:fixed;
top:0;
width:100%;
}

/* SIDEBAR */

.sidebar{

position:fixed;
top:60px;
left:0;

width:70px;
height:100%;

background:#0b0b0b;

transition:0.3s;

overflow:hidden;

z-index:1000;

}

/* SIDEBAR EXPAND */

.sidebar:hover{
width:240px;
}

/* SIDEBAR LINKS */

.sidebar a{

display:flex;

align-items:center;

padding:15px;

color:white;

text-decoration:none;

font-size:16px;

}

/* ICON */

.sidebar i{

font-size:22px;

min-width:40px;

text-align:center;

}

/* TEXT HIDDEN */

.sidebar span{

opacity:0;

transition:0.3s;

white-space:nowrap;

}

/* TEXT SHOW */

.sidebar:hover span{

opacity:1;

}

/* HOVER EFFECT */

.sidebar a:hover{

background:#1f1f1f;

}

/* MAIN CONTENT */

.main-content{

margin-left:70px;

margin-top:60px;

padding:20px;

transition:0.3s;

}

/* SHIFT CONTENT WHEN SIDEBAR EXPANDS */

.sidebar:hover ~ .main-content{

margin-left:240px;

}

/* DARK MODE */

.dark-mode{

background:#121212;
color:white;

}

</style>

</head>

<body>