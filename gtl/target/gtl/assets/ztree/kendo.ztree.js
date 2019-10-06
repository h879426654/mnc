(function($) {
 var kendo = window.kendo, ui = kendo.ui, Widget = ui.Widget;
 var ZTree = Widget.extend({
  init : function(element, options) {
   var that = this;
   var setting = {};
   if (options.checkEnable) {
    setting.check = {
     enable : true
    };
   }
   Widget.fn.init.call(that, element, options);
   //console.log('options:' + JSON.stringify(options));
   $.post(options.url, {}, function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     that.ztree = $.fn.zTree.init($(element), setting, data.item);
     if (options.expandAll) {
      that.ztree.expandAll(true);
     }
    } else {
     alert(data.message);
    }
   });
  },
  options : {
   name : "ZTree",
   checkEnable : false,
   expandAll : false,
   url : ""
  },
  ztree : function() {
   return this.ztree;
  },
  setCheckIds : function(ids) {
   this.ztree.checkAllNodes(false);
   var ids = ids.split(',');
   if (ids.length == 0) {
    return;
   }
   var nodes = this.ztree.transformToArray(this.ztree.getNodes());
   for (var i = 0; i < nodes.length; i++) {
    var id=nodes[i].id;
    if($.inArray(id,ids)!=-1){
     this.ztree.checkNode(nodes[i], true, false);
    }
   }
  },
  getCheckIds : function() {
   var nodes = this.ztree.getCheckedNodes(true);
   var ids = [];
   for (var i = 0; i < nodes.length; i++) {
    ids.push(nodes[i].id);
   }
   return ids.join(',');
  }
 });
 ui.plugin(ZTree);
})(jQuery);