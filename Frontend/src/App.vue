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
  body {
    width: 100%;
    height: 100%;
    position: absolute;
    background-color: #2f2f2f;
  }

  #app {
    position: absolute;
    display: flex;
    flex-direction: column;
    width: 450px;
    height: 650px;
    background-color: #e0e0e0;
    padding: 5px;
    border-radius: 3%;
    top: 10%;
    left: 38%;
  }
</style>