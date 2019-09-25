/**
 * AppImageWidget
 * 
 */
(function($) {
 // 初始化.
 function init(target) {
  return $(target);
 }
 function render(target, params) {
  // console.log('render:params:' + JSON.stringify(params));
  var opts = $.extend($(target).data('AppImageWidget').options, params);
  // ownerId.
  var dataOwnerId = $(target).attr('data-owner-id');
  var ownerId = $("#" + dataOwnerId).val();
  if (ownerId) {
   opts['ownerId'] = ownerId;
  }
  // ownerClass.
  var ownerClass = $(target).attr('data-owner-class');
  if (ownerClass) {
   opts['ownerClass'] = ownerClass;
  }
  // dataSrc.
  var dataSrc = $(target).attr('data-src');
  if (dataSrc) {
   opts['dataSrc'] = dataSrc;
  }
  // dataWidth.
  var dataWidth = $(target).attr('data-width');
  if (dataWidth) {
   opts['dataWidth'] = dataWidth;
  }
  // dataHeight.
  var dataHeight = $(target).attr('data-height');
  if (dataHeight) {
   opts['dataHeight'] = dataHeight;
  }
  console.log('render:options:' + JSON.stringify(opts));
  var tmpl = $.templates(opts.tmpl);
  var html = tmpl.render(opts);
  console.log('html:' + html);
  $(target).html(html);
 }

 // 插件定义.
 $.fn.AppImageWidget = function(options, param) {
  if (typeof options == 'string') {
   return $.fn.AppImageWidget.methods[options](this, param);
  }
  options = options || {};
  return this.each(function() {
   var state = $.data(this, 'AppImageWidget');
   if (state) {
    $.extend(state.options, options);
   } else {
    init(this);
    $.data(this, 'AppImageWidget', {
     options : $.extend({}, $.fn.AppImageWidget.defaults, $.fn.AppImageWidget.parseOptions(this), options)
    });
   }
  });
 };
 // 方法.
 $.fn.AppImageWidget.methods = {
  options : function(jq) {
   return $.data(jq[0], 'AppImageWidget').options;
  },
  load : function(jq, param) {
   return jq.each(function() {
    render($(this), param);
   });
  }
 };
 // 解析方法
 $.fn.AppImageWidget.parseOptions = function(target) {
  var t = $(target);
  return $.extend({}, {
   disabled : (t.attr('disabled') ? true : undefined)
  });
 };
 // 默认设置.
 $.fn.AppImageWidget.defaults = {
  dataSrc : 'widget.do',
  disabled : false,
  dataWidth : '100%',
  dataHeight : '360px',
  tmpl : '<iframe src="{{:dataSrc}}?ownerId={{:ownerId}}&ownerClass={{:ownerClass}}" style="width: {{:dataWidth}}; height: {{:dataHeight}}" frameborder=0></iframe>'
 };
})(jQuery);
