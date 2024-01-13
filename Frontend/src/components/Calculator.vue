<template>
    <div id="screen">
        <div id="output">{{ expression1 }} <br> {{ expression2 }}</div>
    </div>
    <div id="buttons">
        <button id="%" @click="unaryOp('per')">%</button>
        <button id="clearRecent" @click="clearRecent">CE</button>
        <button id="clearAll" @click="clearAll">C</button>
        <button id="erase" @click="erase">&#x232B;</button>
        <button id="^ -1" @click="unaryOp('** -1')"><sup>1</sup>&frasl;<sub>&#119909;</sub></button>
        <button id="^ 2" @click="unaryOp('** 2')">&#119909;<sup>2</sup></button>
        <button id="^ 0.5" @click="unaryOp('** 0.5')">&radic;&#119909;</button>
        <button id="/" @click="binaryOp('d')">&div;</button>
        <button id="7" @click="addNumber(7)">7</button>
        <button id="8" @click="addNumber(8)">8</button>
        <button id="9" @click="addNumber(9)">9</button>
        <button id="*" @click="binaryOp('*')">&times;</button>
        <button id="4" @click="addNumber(4)">4</button>
        <button id="5" @click="addNumber(5)">5</button>
        <button id="6" @click="addNumber(6)">6</button>
        <button id="-" @click="binaryOp('-')">-</button>
        <button id="1" @click="addNumber(1)">1</button>
        <button id="2" @click="addNumber(2)">2</button>
        <button id="3" @click="addNumber(3)">3</button>
        <button id="+" @click="binaryOp('+')">+</button>
        <button id="* -1" @click="unaryOp('* -1')"><sup>+</sup>&frasl;<sub>-</sub></button>
        <button id="0" @click="addNumber(0)">0</button>
        <button id="." @click="addNumber('.')">.</button>
        <button id="=" class="active" @click="equal">=</button>
    </div>
</template>

<script>

const host = "http://localhost:8080/";

export default {
    data() {
        return {
            override: true,
            expression1: "",
            expression2: "0"
        };
    },
    methods: {
        clearAll() {
            this.expression1 = ""
            this.expression2 = "0"
            this.override = true
        },
        clearRecent() {
            this.expression2 = "0";
            this.override = true
        },
        erase() {
            const len = this.expression2.length
            if(len == 1)
                this.expression2 = "0"
            else {
                this.expression2 = this.expression2.substring(0, len - 1)
            }
            this.override = false
        },
        addNumber(num) {
            const len = this.expression1.length
            const lastChar = this.expression1.charAt(len - 1)
            if(this.override || this.expression2 == "0")
                this.expression2 = num.toString()
            else {
                let len = this.expression2.length
                if(len < 12) 
                    this.expression2 += num
                
            }
            this.override = false
        },
        equal() {
            const len = this.expression1.length
            const lastChar = this.expression1.charAt(len - 1)
            if(lastChar == '=' || !isNaN(lastChar) || len == 0){
                this.expression1 = this.expression2 + " =" 
            }else if(isNaN(lastChar)) {
                this.expression1 += " " + this.expression2 + " =" 
                let op = ' '
                let index
                for (index = 0; index < this.expression1.length; index++) {
                    if(this.expression1.charAt(index) == ' '){
                        op = this.expression1.charAt(index + 1)
                        break
                    }
                }
                const a = this.expression1.substring(0, index)
                const b = this.expression1.substring(index + 3, this.expression1.length - 2)
                if(op == '/')
                    op = 'd'
                let urlAPI = host + a + '/' + op + '/' + b
                fetch(urlAPI)
                .then(res => res.json())
                .then(data => this.expression2 = data)
                .catch(error => this.expression2 = "Error")
                this.override = true
            }
        },
        unaryOp(op) {
            const len = this.expression1.length
            const lastChar = this.expression1.charAt(len - 1)
            
            if(!isNaN(lastChar) && op != 'per')
                if(op == '** -1')
                    this.expression1 = "1/("+ this.expression2+")"
                else if(op == '** 0.5')
                    this.expression1 = "sqrt(" + this.expression2 + ")"
                else if(op == '** 2')
                    this.expression1 = "sqr(" + this.expression2 + ")"
                else
                    this.expression1 = this.expression2 + " " + op
            let a = 0
            let b = 0
            if(op == 'per'){
                if(len == 0)
                    b = Number(this.expression2)
                else{
                    let index
                    for (index = 0; index < this.expression1.length; index++) {
                        if(this.expression1.charAt(index) == ' '){
                            break
                        }
                    }
                    a = Number(this.expression1.substring(0, index))
                    b = Number(this.expression2)
                }
            }else if(op == '* -1'){
                a = Number(this.expression2)
                b = op.substring(2)
                op = op.charAt(0)
            }else {
                a = Number(this.expression2)
                b = op.substring(3)
                op = op.substring(0, 3)
            }
            let urlAPI = host + a + '/' + op + '/' + b
            fetch(urlAPI)
            .then(res => res.json())
            .then(data => this.expression2 = data)
            .catch(error => this.expression2 = "Error")
            this.override = true
        },
        binaryOp(op) {
            const len = this.expression1.length
            const lastChar = this.expression1.charAt(len - 1)
            if(op == 'd')
                op = '/'
            this.expression1 = this.expression2 + " " + op
            this.override = true
        }
    }
};
</script>

<style>
* {
    padding: 0;
    margin: 0;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    line-height: 1.7;
}

#screen {
    display: block;
    height: 25%;
}

#output {
    display: block;
    height: 100%;
    padding: 10px;
    background-color: aqua;
    text-align: right;
    letter-spacing: 2px;
    font-size: 35px;
    overflow: auto;
}

#buttons {
    display: grid;
    grid-template-columns: auto auto auto auto;
    grid-template-rows: auto auto auto auto;
    height: 75%;
}

button {
    font-size: large;
    border: 1px solid #000000;
}

.active {
    background-color: #9394a5;
}   

button:hover {
    opacity: 0.7;
}
</style>