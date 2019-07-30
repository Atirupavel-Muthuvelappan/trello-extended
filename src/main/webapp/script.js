var name,password,email,resp;
function validateForm(){
var vname=validateName();
if(vname){
var vpassword=validatePassword();
if(vpassword)
{
var vemail=validateEmail();
if(vemail)
{
ajax();
}}}}
function validateName() {
   name = document.getElementById("fname").value;
   if (name) {
    return true;
  }
alert("Name must be filled out!");
      return false;}
  function validatePassword(){
   password = document.getElementById("fpassword").value;
  if (password == "") {
    window.alert("Password must be filled out");
    return false;}
    if(password.length<8){
    window.alert("The password should be atleast minimum of 8 characters");
    return false;
    }
    var reg =new RegExp("[0-9]");
     var regc = /(! || @ || # || $ || & || * )/i;
  if(reg.test(password) && regc.test(password))
{
return true;}
else {window.alert("The password should contain atleast one special character and a number");
return false;}}
function validateEmail()
{
	 email =document.getElementById("femail").value;
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
  {
    return true;
  }
 else
 {	 alert("You have entered an invalid email address!");
    return false;
}}

function ajax() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      resp = this.responseText;
     if(resp.includes("exist")){
     document.getElementById("disp").innerText="This email already exist.Try another email";}
 else if(resp.includes("new")){
           window.location.replace("view.html") ; }}
};
xhttp.open("POST","/signup",true);
xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xhttp.send("fname="+name+"&fpassword="+password+"&femail="+email);}

