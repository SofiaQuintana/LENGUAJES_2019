/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var inputText = "";
var index = 0;
var flag = 0;

const charIndex = 0;
const list = document.getElementById("list");
const text  = document.getElementById("inputBox");

function lexicalAnalyzer() {
    receiveText();
    analyzeText(inputText);
}

function analyzeText(text) {
   var char = '';
   var code = 0;
   
       char = text.charAt(charIndex);
       code = char.charCodeAt(charIndex);
       
       if(letterValidation(code)) {
           flag = 1;
       } else if (numberValidation(code)) {
           flag = 2;
       } else if (signsValidation(code)) {
           flag = 3;
       }
       
        rules(flag, text);
    
    //addTable();
}

function receiveText() {
    inputText = document.getElementById("inputBox").value;
    alert(inputText);
}

function rules(flag, text) {
    var inputText = "";
    var charCode = 0;
    var isTrue = null;
    
    inputText = text;
    switch(flag) {
        case 1:
            for (i = 0; i < inputText.length; i++) {
                charCode = inputText.charCodeAt(i);
                if(letterValidation(charCode) || numberValidation(charCode)) {
                    concatWord(inputText.charAt(i));
                    isTrue = true;
                } else {
                    isTrue = false;
//                    addTable("Error");
                }
            } 
            verifyValidation(isTrue, "Identificador");
            break;
        case 2:
            break;
        case 3:
            break;
    }
}

function addTable(token) {
    list.innerHTML = list.innerHTML + "<tr>" + "<td>" + text.value + "</td>" + "<td>" + token + "</td>" + "</tr>";
    console.log(list);
}

function letterValidation(code) {
    return ((code >= 65 && code <= 90) || (code >= 97 && code <= 122));
}
    
function numberValidation(code) {
    return (code >= 48 && code <= 57);
}
    
function signsValidation(code) {
    return (code >= 35 && code <= 37);
}

function concatWord(char) {
    var inputText = "";
    inputText += char;
}

function verifyValidation(flag, text) {
    if(flag === true) {
        addTable(text);
    } else {
        addTable("Error");
    }
}