<template>
  <div v-if="loading" id="loading" class="loadingMask">
    <div class="loadingMaskContent">
      <v-progress-circular indeterminate class="green--text"></v-progress-circular>
    </div>
  </div>
</template>

<script>
  export default {
    props:['loading']
  }
</script>

<style type="text/css">
  .loadingMask {
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
    background: rgba(255, 255, 255, 0.5);
    z-index: 1000;
    cursor: pointer;
  }

  .loadingMaskContent {
    position: absolute;
    left: 50%;
    top: 50%
  }
</style>
