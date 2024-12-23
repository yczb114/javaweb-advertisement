const key="1919810";
var button = document.getElementById("login");
button.addEventListener("click", function () {
    var username=document.getElementById("username");
    var password=document.getElementById("password");
    var usernameValue=username.value;
    var passwordValue=password.value;
    usernameValue=key+usernameValue;
    passwordValue=key+passwordValue;
    username.value=usernameValue;
    password.value=passwordValue;
});