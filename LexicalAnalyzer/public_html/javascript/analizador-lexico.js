
var tokenOperador = false;
var tokenAgrupacion = false;
var tokenSigno = false;
var tokenNumeroEntero = false;
var tokenNumeroFlotante = false;
var tokenIdentificador = false;
var tokenError = false;

var contadorOperador = 0;
var indiceActual;
var lexemaActual = "";
var lexemas = new Array();
var tokens = new Array();
var palabrasReservadas = ['variable', 'entero', 'decimal', 'booleano', 'cadena', 'si', 'sino', 'mientras', 'hacer'];
var boolean = ['VERDADERO', 'FALSO'];


/*
Metodo encargado de la lectura del archivo de entrada.
 */
function readFile(files) {
        var file = files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
        var textArea = document.getElementById("textArea");
        textArea.textContent = e.target.result;
        };
        reader.readAsText(file);
    }


function analyze(){
    var texto = document.getElementById("textArea").value;    
    analisisLexico(texto);
}


function analisisLexico(texto){
    lexemaActual = "";
    indiceActual = 0;
    
    while(indiceActual <= texto.length){      
        
        if(tokenIdentificador === true){
            identificador(texto.charCodeAt(indiceActual), texto.charAt(indiceActual));
        }
        else if(tokenNumeroEntero === true){
            numeroEntero(texto.charCodeAt(indiceActual), texto.charAt(indiceActual));
        }
        else if(tokenNumeroFlotante === true){
            numeroFlotante(texto.charCodeAt(indiceActual), texto.charAt(indiceActual));
        }
        else if(tokenOperador === true){
            operador(texto.charCodeAt(indiceActual), texto.charAt(indiceActual));
        }
        else if(tokenAgrupacion === true){
            agrupacion(texto.charCodeAt(indiceActual), texto.charAt(indiceActual));
        }
        else if(tokenSigno === true){
            signo(texto.charCodeAt(indiceActual), texto.charAt(indiceActual));
        }
        else if(tokenError === true){
            error(texto.charCodeAt(indiceActual), texto.charAt(indiceActual));
        }
        else{
            lexemaActual = "";
            switch(texto.charCodeAt(indiceActual)){    
                //Identificador
                case 65: case 66: case 67: case 68: case 69: case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79: case 80:  
                case 81: case 82:  case 83: case 84: case 85: case 86: case 87: case 88: case 89: case 90: 
                case 97: case 98: case 99: case 100: case 101: case 102: case 103: case 104: case 105: case 106: case 107: case 108: case 109: case 110: case 111: 
                case 112: case 113:  case 114: case 115: case 116: case 117: case 118: case 119: case 120: case 121: case 122: case 111: case 112: case 113:                                                                    
                    tokenIdentificador = true;
                break;
        
                //Numero Entero
                case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57:
                    tokenNumeroEntero = true;
                break;
            
                //Operador
                case 37: case 42: case 43: case 45: case 47: case 60: case 61: case 62:
                    tokenOperador = true;
                break; 
                
                //Agrupacion
                case 40: case 41: case 123: case 125:
                    tokenAgrupacion = true;     
                break;
            
                //Signo
                case 34: case 59: 
                    tokenSigno = true;
                break;
            
                //Espacio en blanco entre lexemas(32) y saltos de linea(10)
                case 10: case 32:
                    indiceActual++;
                break;
                
                case 01: case 02: case 03: case 04: case 05: case 06: case 07: case 08: case 09: case 10: case 11: case 12: case 13: case 14:
                break;
            
                //Error 
                default:
                    tokenError = true;
                break;
            }
        }   
    }
    writeOutput();
}


function identificador(caracterAscii, caracter){
    if((caracterAscii > 64 && caracterAscii < 91) || (caracterAscii > 96 && caracterAscii < 122) || (caracterAscii > 47 && caracterAscii < 58)){
        lexemaActual = lexemaActual + caracter;
        indiceActual++;
    }
    else{
        if(palabrasReservadas.includes(lexemaActual)){
            tokens.push("Palabra Reservada");
        }
        else if(boolean.includes(lexemaActual)){
            tokens.push("Boolean")
        }
        else{
            tokens.push("Identificador");
        }
        lexemas.push(lexemaActual);
        tokenIdentificador = false;
    }
}

function numeroEntero(caracterAscii, caracter){
    if(caracterAscii > 47 && caracterAscii < 58){
        lexemaActual = lexemaActual + caracter;
        indiceActual++;
    }
    else if(caracterAscii === 46){
        tokenNumeroEntero = false;
        tokenNumeroFlotante = true;
        lexemaActual = lexemaActual + caracter;
        indiceActual++;
    }
    else{
        lexemas.push(lexemaActual);
        tokens.push("Numero Entero");
        tokenNumeroEntero = false;
    }
}

function numeroFlotante(caracterAscii, caracter){
   if(caracterAscii > 47 && caracterAscii < 58){
        lexemaActual = lexemaActual + caracter;
        indiceActual++;
    }
    else{
        lexemas.push(lexemaActual);
        tokens.push("Numero Flotante");
        tokenNumeroFlotante  = false;
    }
}

function operador(caracterAscii, caracter){
    if(caracterAscii > 59 && caracterAscii < 63){
        contadorOperador++;
        if(contadorOperador === 2){
            lexemaActual = lexemaActual + caracter;
            lexemas.push(lexemaActual);
            tokens.push("Operador");
            tokenOperador  = false;
            indiceActual++;
            contadorOperador = 0;
        }
        else{
            lexemaActual = lexemaActual + caracter;
            indiceActual++;
        }
    }
    else{
        lexemaActual = lexemaActual + caracter;
        lexemas.push(lexemaActual);
        tokens.push("Operador");
        tokenOperador  = false;
        indiceActual++;
    } 
}

function agrupacion(caracterAscii, caracter){
    lexemaActual = lexemaActual + caracter;
    lexemas.push(lexemaActual);
    tokens.push("Agrupacion");
    tokenAgrupacion  = false;
    indiceActual++;
}

function signo(caracterAscii, caracter){
    lexemaActual = lexemaActual + caracter;
    lexemas.push(lexemaActual);
    tokens.push("Signo");
    tokenSigno  = false;
    indiceActual++;
}

function error(caracterAscii, caracter){
    lexemaActual = lexemaActual + caracter;
    lexemas.push(lexemaActual);
    tokens.push("Error");
    tokenError  = false;
    indiceActual++;
}









/*
Metodo encargado de imprimir en el textArea outPut el resumen del analisis lexico
*/
function writeOutput() {
    var salida = "";
    document.getElementById("output").innerHTML =  " ";
    for(var i = 0; i < lexemas.length -1; i++){
        salida = salida + "Lexema: " + lexemas[i] + "  Token: " + tokens[i] + ' \n';
    }   
    document.getElementById("output").innerHTML =  salida;
}





