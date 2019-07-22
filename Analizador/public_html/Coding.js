/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var index = 0;

const list = document.getElementById("list");
const text  = document.getElementById("inputBox");

function analyzeText() {
   // var char = '';
    
    addTable();
}

function addTable(token) {
    list.innerHTML = list.innerHTML + "<tr>" + "<td>" + text.value + "</td>" + "<td>" + token + "</td>" + "</tr>";
    console.log(list);
}
