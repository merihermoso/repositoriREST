var username = localStorage.getItem("username")
if (username == null){
    var url = "login.html";
    window.open(url, "_self");
}