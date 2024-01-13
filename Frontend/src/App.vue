<template>
  <Calculator/>
</template>

<script>
import Calculator from "./components/Calculator.vue";

export default {
  name: 'App',
  components: {
    Calculator
  }
}
</script>

<style>
  #app {
    position: absolute;
    display: flex;
    flex-direction: column;
    width: 450px;
    height: 650px;
    background-color: #000000;
    padding: 5px;
    border-radius: 1%;
    top: 10%;
    left: 38%;
  }
</style>