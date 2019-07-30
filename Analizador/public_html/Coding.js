/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var inputText = "";
var index = 0;
var flag = 0;
var isTrue = null;

const charIndex = 0;
const list = document.getElementById("list");
const text  = document.getElementById("inputBox");

function lexicalAnalyzer() {
    receiveText();
    analyzeText(inputText);
    document.getElementById("inputBox").value = "";
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
       } else if (text === " ") {
           flag = 4;
       }
        rules(flag, text);    
}

function receiveText() {
    inputText = document.getElementById("inputBox").value;
}

function rules(flag, text) {
    var inputText = "";
    var charCode = 0;
    
    inputText = text;
    switch(flag) {
        case 1:
            for (i = 0; i < inputText.length; i++) {
                charCode = inputText.charCodeAt(i);
                if(letterValidation(charCode) || numberValidation(charCode)) {
                    isTrue = true;
                } else {
                    isTrue = false;
                    break;
                }
            } 
            verifyValidation(isTrue, "Identificador");
            break;
        case 2:
            for (i = 0; i < inputText.length; i++) {
                charCode = inputText.charCodeAt(i);
                if(numberValidation(charCode)) {
                    isTrue = true;
                } else {
                    isTrue = false;
                    break;
                }
            }
            verifyValidation(isTrue, "Numero");
            break;
        case 3:
            for (i = 0; i < inputText.length; i++) {
                charCode = inputText.charCodeAt(i);
                if(signsValidation(charCode)) {
                    isTrue = true;
                } else {
                    isTrue = false;
                    break;
                }
            }
            verifyValidation(isTrue, "Simbolo");
            break;
        case 4:
            alert("Elemento invalido");
        break;
        default:
            isTrue = false;
            verifyValidation(isTrue, "Error");    
    }
}

function addTable(token) {
    list.innerHTML = list.innerHTML + "<tr>" + "<td>" + text.value + "</td>" + "<td>" + token + "</td>" + "</tr>";
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

function verifyValidation(flag, text) {
    if(flag === true) {
        addTable(text);
    } else {
        addTable("Error");
    }
}