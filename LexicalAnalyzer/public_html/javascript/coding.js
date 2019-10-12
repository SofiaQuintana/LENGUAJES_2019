    var columns = 1;
    var rows = 1;
    var index = 0;
    var indexWords = 0;
    var indexError = 0;
    var tokenIndex = 0;
    var errorDetected = new Array();
    var errorPosition = new Array();
    var tokens = new Array();
    var approvedWord = new Array();
    var output = '';
        
    function readFile(files) {
        var file = files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
        var textArea = document.getElementById("textArea");
        textArea.textContent = e.target.result;
        };
        reader.readAsText(file);
    }
    
    function analyze() {
        var text = document.getElementById("textArea").value;
        resetElements();
        analyzeText(text);
    }
   
    function analyzeText(text) {
        var char = '';
        var code = 0;
        var state = 0;
        
        do{

            char = text.charAt(index); 
            code = char.charCodeAt(0);
 
            if(letterValidation(code)) {
                state = 1;
            } else if(numberValidation(code)) {
                state = 2;
            } else if(puntuactionMarksValidation(code)) {
                switch(code) {
                    case 46:
                        state = 3;
                        break;
                    case 44:
                        state = 4;
                        break;
                    case 59:
                        state = 5;
                        break;
                    case 58:
                        state = 6;
                        break;
                }
            } else if(arithmeticSignsValidation(code)) {
                switch(code) {
                    case 43:
                        state = 7;
                        break;
                    case 45:
                        state = 8;
                        break;
                    case 42:
                        state = 9;
                        break;
                    case 47:
                        state = 10;
                        break;
                    case 37:
                        state = 11;
                        break;
                }
            } else if(groupingSignsValidation(code)) {
                switch(code) {
                    case 40:
                        state = 12;
                        break;
                    case 41:
                        state = 13;
                        break;
                    case 91:
                        state = 14;
                        break;
                    case 93:
                        state = 15;
                        break;
                    case 123:
                        state = 16;
                        break;
                    case 125:
                        state = 17;
                        break;
                }
            } 
            rules(state, text);
            index++;
        } while(text.charAt(index) !== "");        
    }
         
    function addColumn(letter) {
        var asciiCode = letter;
        if(asciiCode === '\n') {
            columns = 0;
        } else {
            columns++;
        }
    }
    
    function addRow(letter) {
        var asciiCode = letter;
        if(asciiCode === '\n') {
            rows++;
        }
    }
    
    function letterValidation(code) {
        return ((code >= 65 && code <= 90) ||(code >= 97 && code <= 122));
    }
    
    function numberValidation(code) {
        return (code >= 48 && code <= 57);
    }
    
    function puntuactionMarksValidation(code) {       
        return (code === 46 || code === 44 || code === 59 || code === 58);
    }
    
    function arithmeticSignsValidation(code) {
        return (code === 43 || code === 45 || code ===42 || code === 47 || code === 57);
    }
    
    function groupingSignsValidation(code) {
        return (code === 40 || code === 41 || code === 91 || code === 93 || code === 123 || code === 125);
    }

    function rules(state, text) {
        var inputText = text;
        var words = inputText.split(" ");
        var word = words[indexWords];
        var newCode = 0;
  
        switch(state) {
            case 1:
                for(i = 0; i < word.length; i++) {
                    newCode = word.charCodeAt(i);
                    basicStuff(newCode);
                    if(letterValidation(newCode) || numberValidation(newCode)) {
                        concatWord(word.charAt(i));
                    } else {
                        if(i === (word.length - 1)) {
                        error(words[indexWords]);
                        }
                    } 
                    index++;
                }
                addApprovedWord("Identificador", words[indexWords]);
                break;
            case 2:
                for(i = 0; i < word.length; i++) {
                    newCode = word.charCodeAt(i);
                    basicStuff(newCode);
                    if(numberValidation(newCode)) {
                        concatWord(word.charAt(i));
                    } else {
                        if(i === (word.length - 1)) {
                        error(words[indexWords]);
                        }
                    }
                    index++;
                }
                addApprovedWord("Numero", words[indexWords]);
                break;
            case 3:                
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Punto", words[indexWords]);            
                break;
            case 4:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Coma", words[indexWords]);                
                break;
            case 5:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Punto y coma", words[indexWords]);
                break;
            case 6:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Dos puntos", words[indexWords]);
                break;
            case 7:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Suma", words[indexWords]);
                break;
            case 8:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Resta", words[indexWords]);
                break;
            case 9:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Multiplicacion", words[indexWords]);
                break;
            case 10:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Division", words[indexWords]);
                break;
            case 11:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Modulo", words[indexWords]);
                break;
            case 12:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Parentesis derecho", words[indexWords]);
                break;
            case 13:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Parentesis izquierdo", words[indexWords]);
                break;
            case 14:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Corchete derecho", words[indexWords]);
                break;
            case 15:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Corchete izquierdo", words[indexWords]);
                break;
            case 16:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Llave derecha", words[indexWords]);
                break;
            case 17:
                verifyLexemaOneToOne(words[indexWords], newCode);
                addApprovedWord("Llave izquierda", words[indexWords]);
                break;
            }
            state = 0;
            columns++;
    }
    
    function basicStuff(code) {
        addColumn(code);
        addRow(code);
    }
    
    function concatWord(char) {
        var word = "";
        word += char;
    }
    
    function error(word) {
        errorDetected.push(word);
        errorPosition[indexError] = new Array(columns, rows);
        indexError++;
    }
    
    function addApprovedWord(token, word) {
        var temp = indexError;
        temp--;
        if(temp === -1 || errorDetected[temp] === null) {
            tokens[tokenIndex] = token;
            approvedWord[tokenIndex] = word;
            tokenIndex++;
            writeOutput(word, token);
        }          
        indexWords++;
    }
    
    function verifyLexemaOneToOne(word, newCode) {
        for(i = 1; i < word.length; i++) {
            newCode = word.charCodeAt(i);
            basicStuff(newCode);
            index++;
            index++;
        }
         if(word.charAt(1) !== null) {
                error(word);
            }
    }
    
    function createTable() {
        var table = '';
        for(var i = 0; i < approvedWord.length; i++) {
            table += '<tr>';
            
                table += '<td>' + approvedWord[i] + '</td>'; 
                table += '<td>' + tokens[i] + '</td>';
            
            table += '</tr>';
        }
        document.write('<table border = 1>' + table + '</table>');
    }
    
    function writeOutput(word, token) {
        output += 'Word: ' + word + ' | Token: ' + token + ' | Fila: ' + rows + ' | Columna: ' + columns + '\n';
        document.getElementById("output").innerHTML = output;
    }
    
    function resetElements() {
        columns = 1;
        rows = 1;
        index = 0;
        indexWords = 0;
        indexError = 0;
        tokenIndex = 0;
       output = '';
    }
    
    
    
    function analisisLexico(){
        
        
        
    }
    
    
