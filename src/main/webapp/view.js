 var resp;var check=false;
 (function(){ var resp; var xhttp = new XMLHttpRequest();
   xhttp.onreadystatechange = function() {
     if (this.readyState == 4 && this.status == 200) {
       resp = this.responseText;
       if(resp.includes("no"))
       window.location.replace("login.html");
     }
  };
 xhttp.open("GET", "/sessioncheck",true);
  xhttp.send(null);
 })();
 function recept() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
 var receive=this.response;
  resp=JSON.parse(receive);
if(resp=="nothing"){
resp="";
document.getElementById("demo").innerText="Sorry, no cards available";}
 iterate();
 }
  };
  xhttp.open("GET", "/view",true);
 xhttp.send(null);
}

function iterate() {
  var length=resp.length;
  var key=new Array(length);
 var value=new Array(length);
 var date=new Array(length);
var body = document.getElementsByTagName("body")[0];
 var cdiv = document.createElement("div");
 cdiv.setAttribute("id","cdiv");
 cdiv.style.width="550px";
 cdiv.style.margin="auto";
  var tbl = document.createElement("table");
  tbl.setAttribute("id","tbl");
  var tblBody = document.createElement("tbody");
  tblBody.setAttribute("id","tblBody");
   for (var j = 0; j < length; j++) {
  key[j]=resp[j].key;
  value[j]=resp[j].value;
  date[j]=resp[j].date;
  var row = document.createElement("tr");
  for (var i = 0; i < 1; i++) {
  var cell = document.createElement("td");
      var cellText = document.createElement("INPUT");
  cellText.setAttribute("type", "text");
  cellText.setAttribute("value", value[j]);
 cellText.setAttribute("readOnly",true);
cell.appendChild(cellText);
      row.appendChild(cell);
     var cellText = document.createElement("INPUT");
 cellText.setAttribute("type", "text");
  cellText.setAttribute("value", date[i]+"days elapsed");
 cellText.setAttribute("readOnly",true);
      cell.appendChild(cellText);
      row.appendChild(cell);
    }
tblBody.appendChild(row);
  }
 tbl.appendChild(tblBody);
 cdiv.appendChild(tbl);
 var btn= document.getElementById("bt");
cdiv.appendChild(btn);
var buton= document.getElementById("save");

cdiv.appendChild(buton);
  body.appendChild(cdiv);
}
function checkNumber(){
var number=document.getElementById("tbl").rows.length;
if(number==0)
add();
else{ var detail=document.getElementById("tbl").rows[(document.getElementById("tbl").rows.length)-1].cells[0].childNodes[0].value;
       var  people=document.getElementById("tbl").rows[(document.getElementById("tbl").rows.length)-1].cells[0].childNodes[1].value;
       if(detail && people){add();}
       else
       alert("please fill in and save the details");

}}
  function add(){var verify=document.getElementById("demo").innerText;
if(verify=="Do not forget to save"){alert("Save the details first");}
 else{var rows= document.createElement("tr");
  var tds= document.createElement("td");
 var tnode = document.createElement("INPUT");
  tnode.setAttribute("type", "text");
  tnode.setAttribute("value", "");
tnode.setAttribute("placeholder","Enter your ideas");
tds.appendChild(tnode);
      rows.appendChild(tds);
    var tnode = document.createElement("INPUT");
tnode.setAttribute("type", "text");
  tnode.setAttribute("value", "");
  tnode.setAttribute("placeholder","Share");
tds.appendChild(tnode);
      rows.appendChild(tds);
      document.getElementById("tbl").appendChild(rows);
     document.getElementById("demo").innerText="Do not forget to save";}
}
 function save(){
var detail=document.getElementById("tbl").rows[(document.getElementById("tbl").rows.length)-1].cells[0].childNodes[0].value;
var  people=document.getElementById("tbl").rows[(document.getElementById("tbl").rows.length)-1].cells[0].childNodes[1].value;
 if(detail && people){
var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 201) {
     document.getElementById("demo").innerText="Details successfully saved";

if(document.getElementsByTagName("INPUT").length>3){
document.getElementsByTagName("INPUT")[(document.getElementsByTagName("INPUT").length)-3].setAttribute("readOnly",true);
document.getElementsByTagName("INPUT")[(document.getElementsByTagName("INPUT").length)-4].setAttribute("readOnly",true);
}}
 };
xhttp.open("POST","/addDetails",true);
xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xhttp.send("details="+detail+"&people="+people);}
else
alert("Fill in the details");
}