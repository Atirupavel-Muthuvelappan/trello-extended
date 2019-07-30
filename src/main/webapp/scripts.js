var password,email,resp;

(function(){ var resp; var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      resp = this.responseText;
      if(resp.includes("yes"))
      window.location.href="view.html";
    }
 };

xhttp.open("GET", "/sessioncheck",true);
 xhttp.send(null);
})();
function validateForm(){
if(validatePassword()&& validateEmail()){
async();
}}
   function validatePassword(){
     password = document.getElementById("fpassword").value;
    if (password) {
      if(password.length>7){
        var re = /(?=.*\d)(?=.*[* | & | # | @ | $ | %])/;
       if(re.test(password) )
        {
        return true;}
        else window.alert("The password should contain atleast one special character and a number");
      }
      else{
      window.alert("The password should be atleast minimum of 8 characters");return false;}}
      else{  window.alert("Password must be filled out");
return false; }}

function validateEmail()
{
	 email = document.getElementById("femail").value;
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
  {
    return true;
  }
 else
 {	 alert("You have entered an invalid email address!");
    return false;
}}
function async() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      resp = this.responseText;
     if(resp.includes("wrong")){
     document.getElementById("disp").innerText="The password entered is wrong";}
  else if(resp.includes("signup")){
      document.getElementById("disp").innerText="Please Signup";}
     else if(resp.includes("correct")){
           location.href="view.html";
            } }
};
xhttp.open("GET", "/login?fpassword="+password+"&femail="+email,true);
 xhttp.send(null);
}
