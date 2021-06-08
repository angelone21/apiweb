/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("contenedor_productos").style.display = "none";

document.getElementById("view_clientes").addEventListener("click",function (e){
    e.target.classList.add("active");
    document.getElementById("view_productos").classList.remove("active");
    document.getElementById("contenedor_productos").style.display = "none";
    document.getElementById("contenedor_clientes").style.display = "block";
},false);

document.getElementById("view_productos").addEventListener("click",function (e){
    e.target.classList.add("active");
    document.getElementById("view_clientes").classList.remove("active");
    document.getElementById("contenedor_clientes").style.display = "none";
    document.getElementById("contenedor_productos").style.display = "block";
},false);