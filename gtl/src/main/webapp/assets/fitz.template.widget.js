/**
 * TemplateWidget
 * 
 */
(function($) {
 // 初始化.
 function init(target) {
  return $(target);
 }
 function render(target, params) {
  // console.log('render:params:' + JSON.stringify(params));
  var opts = $.extend($(target).data('TemplateWidget').options, params);
  var html = $("#" + opts.tmplId).render(opts);
  // console.log('html:' + html);
  $(target).html(html);
 }
 // 插件定义.
 $.fn.TemplateWidget = function(options, param) {
  if (typeof options == 'string') {
   return $.fn.TemplateWidget.methods[options](this, param);
  }
  options = options || {};
  return this.each(function() {
   var state = $.data(this, 'TemplateWidget');
   if (state) {
    $.extend(state.options, options);
   } else {
    init(this);
    $.data(this, 'TemplateWidget', {
     options : $.extend({}, $.fn.TemplateWidget.defaults, $.fn.TemplateWidget.parseOptions(this), options)
    });
   }
  });
 };
 // 方法.
 $.fn.TemplateWidget.methods = {
  options : function(jq) {
   return $.data(jq[0], 'TemplateWidget').options;
  },
  load : function(jq, param) {
   return jq.each(function() {
    render($(this), param);
   });
  }
 };
 // 解析方法
 $.fn.TemplateWidget.parseOptions = function(target) {
  var t = $(target);
  return $.extend({}, {
   tmplId : t.attr('tmplId')
  });
 };
 // 默认设置.
 $.fn.TemplateWidget.defaults = {};
})(jQuery);
