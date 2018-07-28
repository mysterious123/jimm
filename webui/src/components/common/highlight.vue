<script>
  export default {
    name:'highlight',
    props:['content','keyword'],
    render: function (createElement) {
      var vm = this;
      return createElement(
        "span",
        {
          domProps: {
            innerHTML: vm.escapeHtml(vm.content).replace(vm.keyword,'<span style="background: #ffff00;color:#000">' + vm.keyword + '</span>')
          }
        }
      )
    },
    methods: {
      escapeHtml(content){
        return $("<div>").text(content).html();
      }
    }
  }
</script>
