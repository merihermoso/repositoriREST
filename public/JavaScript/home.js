var username = localStorage.getItem("username")
if (username == null){
    var url = "http://localhost:8080/login.html";
    window.open(url, "_self");
}