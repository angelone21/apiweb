/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("tab_productos").style.display = "none";


document.getElementById("view_clientes").addEventListener("click",function (){
    document.getElementById("tab_productos").style.display = "none";
    document.getElementById("tab_clientes").style.display = "block";
},false);

document.getElementById("view_productos").addEventListener("click",function (){
    document.getElementById("tab_clientes").style.display = "none";
    document.getElementById("tab_productos").style.display = "block";
},false);