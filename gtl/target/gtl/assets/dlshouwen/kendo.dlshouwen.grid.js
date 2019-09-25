(function($) {
 var kendo = window.kendo, ui = kendo.ui, Widget = ui.Widget;
 var defaultOption = {
  lang : 'zh-cn',
  autoload: true,
  ajaxLoad : true,
  loadURL : 'swgrid.do',
  check : false,
  checkWidth : 40,
  gridContainer : 'gridContainer',
  toolbarContainer : 'gridToolBarContainer',
  tools : '',
  pageSize : 10,
  pageSizeLimit : [ 10, 20, 50 ]
 };

 var SwGrid = Widget.extend({
  init : function(element, options) {
   var that = this;
   Widget.fn.init.call(that, element, options);
   var options = $.extend({},defaultOption,options);
   if(options.loadurl){
    options.loadURL=options.loadurl;
   }
   var grid = $.fn.dlshouwen.grid.init(options);
   that.grid = grid;
   if(options.autoLoad){
   that.load({});
   }
  },
  options : {
   name : "SwGrid",
   lang : 'zh-cn',
   autoload : true,
   ajaxLoad : true,
   loadurl : 'swgrid.do',
   check : false,
   checkWidth : 40,
   columns : {},
   gridContainer : 'gridContainer',
   toolbarContainer : 'gridToolBarContainer',
   tools : '',
   pageSize : 10,
   pageSizeLimit : [ 10, 20, 50 ]
  },
  load : function(parameters) {
   if (parameters) {
    this.grid.parameters = parameters;
   }
   this.grid.pager.nowPage = 1;
   this.grid.pager.startRecord = 0;
   this.grid.load();
  },
  select : function() {
   return this.grid.getCheckedRecords();
  }
 });
 ui.plugin(SwGrid);
})(jQuery);