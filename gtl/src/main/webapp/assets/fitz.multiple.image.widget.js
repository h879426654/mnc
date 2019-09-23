/**
 * MultipleImageWidget
 * 
 */
(function($) {
 // 统一样式alert
 function alert(message) {
  $.messager.alert('提示', message);
 }
 // 统一样式warning
 function warning(message) {
  $.messager.alert('警告', message, 'warning');
 }
 // 统一样式toast
 function toast(message) {
  $.messager.show({
   title : '提示',
   msg : message,
   showType : 'show',
   style : {
    right : '',
    bottom : ''
   }
  });
 }
 // log
 function _log(options) {
  options = options || {};
  // console.log(JSON.stringify(options));
 }
 // data-bind
 function _dataBindProps(target, bindProps) {
  var props = {};
  for (var i = 0; i < bindProps.length; i++) {
   var bindProp = bindProps[i];
   var bindTarget = $(target).attr('data-bind-' + bindProp);
   props[bindProp] = $("#" + bindTarget).val();
  }
  return props;
 }
 // data-option
 function _dataOptionProps(target, optionProps, opts) {
  var props = {};
  for (var i = 0; i < optionProps.length; i++) {
   var optionProp = optionProps[i];
   var optionValue = $(target).attr('data-option-' + optionProp);
   props[optionProp] = $.templates(optionValue).render(opts);
  }
  return props;
 }
 // options
 function _options(target) {
  var state = $(target).data('MultipleImageWidget');
  var targetParams = {
   "targetId" : $(target).attr('id')
  };
  var opts = $.extend($.fn.MultipleImageWidget.defaults, targetParams);
  // bindProps
  var bindProps = _dataBindProps(target, opts.dataBindProps);
  // _dataOptionProps
  var optionProps = _dataOptionProps(target, opts.dataOptionProps, opts);
  var opts = $.extend(opts, optionProps, bindProps);
  if (state) {
   opts = $.extend(opts, $(target).data('MultipleImageWidget').options);
  }
  return opts;
 }
 function _allowUpload(target, options) {
  var allowUpload = $(target).attr('data-option-allowUpload');
  return 'true' == allowUpload;
 }
 // 初始化.
 function _init(target) {
  var options = _options(target);
  var allowUpload = options.allowUpload;
  var _tmpl = 'true' == allowUpload ? options.widgetTmpl : options.listTmpl;
  var tmpl = $.templates(_tmpl);
  var html = tmpl.render(options);
  _log(options);
  $(target).html(html);
  // UEditor
  _createUEditor(target, options);
  // listImage
  // _listImage(target);
  return $(target);
 }

 function _clearImage(target, options) {
  var targetId = $(target).attr('id');
  _log('clearImage');
  _log("targetId=" + targetId);
  if (_allowUpload(target, options)) {
   $("#" + targetId + "WidgetPreviewContainer").html('');
  } else {
   $("#" + targetId + "ListPreviewContainer").html('');
  }
 }
 function _deleteImage(target, options) {
  var imageUrl = options.imageUrl;
  var id = options.id;
  $.messager.confirm('确认', '确认删除图片?', function(confirmed) {
   if (!confirmed) {
    return;
   }
   $.ajax({
    type : "get",
    dataType : "json",
    url : imageUrl,
    success : function(data) {
     if (data.success) {
      $("#uploadImageItemContainer" + id).remove();
     } else {
      toast('图片删除失败!');
     }
    }
   });
  });
 }
 function _hideImage(target, options) {
  _log("_hideImage");
  var targetId = $(target).attr('id');
  $("#" + targetId + "WidgetPreviewContainer").toggle();
 }
 function _uploadImage(target, options) {
  options = options || {};
  _log("_uploadImage");
  var myImage = _getUEditor(target, options).getDialog("insertimage");
  myImage.open();
  try {
   $("#edui_fixedlayer").css({
    "z-index" : 10005
   });
  } catch (e) {
  }
 }
 function _appendImage(target, options, image) {
  _log("_appendImage");
  _log(image);
  var itemImpl = $(target).attr("data-option-itemImpl");
  var tmpl = $.templates("#" + itemImpl);
  var html = tmpl.render(image);
  var targetId = $(target).attr('id');
  _log("targetId=" + targetId);
  if (_allowUpload(target, options)) {
   $("#" + targetId + "WidgetPreviewContainer").append(html);
  } else {
   $("#" + targetId + "ListPreviewContainer").append(html);
  }
  try {
   $('body').jKit();
  } catch (e) {
  }
 }
 function _listImage(target) {
  var options = _options(target);
  _clearImage(target, options);
  var mainId = $("#" + $(target).attr("data-bind-mainId")).val();
  if (mainId == '') {
   return;
  }
  var serverParams = {
   'mainId' : mainId
  };
  var listImageUrl = $.templates(options.listImageUrl).render(serverParams);
  _log("_listImage");
  _log("listImageUrl:" + listImageUrl);
  var ue = _getUEditor(target, options);
  ue.ready(function() {
   ue.execCommand('serverparam', function(editor) {
    _log('serverParams');
    _log(serverParams);
    return serverParams;
   });
  });
  $.ajax({
   type : "get",
   dataType : "json",
   url : listImageUrl,
   success : function(data) {
    if (data.success) {
     for (var i = 0; i < data.list.length; i++) {
      _appendImage(target, options, data.list[i]);
     }
    } else {
     toast('读取图片列表失败!');
    }
   }
  });
 }
 function _createUEditor(target, options) {
  var targetId = options.targetId;
  var uploadImageUEditor = UE.getEditor(targetId + 'UEditor', {
   initialFrameHeight : 400,
   initialFrameWidth : 660,
   toolbars : [ [ 'insertimage' ] ]
  });
  uploadImageUEditor.ready(function() {
   uploadImageUEditor.hide();
   uploadImageUEditor.addListener('beforeInsertImage', function(t, image) {
    _appendImage(target, options, image);
   });
  });
  $(target).data('UEditor', uploadImageUEditor);
  return uploadImageUEditor;
 }
 function _getUEditor(target, options) {
  var state = $(target).data('UEditor');
  if (state) {
   return state;
  } else {
   return _createUEditor(target, options);
  }
 }
 // 插件定义.
 $.fn.MultipleImageWidget = function(options, param) {
  if (typeof options == 'string') {
   if (typeof param != 'undefined') {
    return $.fn.MultipleImageWidget.methods[options](this, param);
   } else {
    return $.fn.MultipleImageWidget.methods[options](this);
   }
  }
  options = options || {};
  return this.each(function() {
   var state = $.data(this, 'MultipleImageWidget');
   if (state) {
    $.extend(state.options, options);
   } else {
    $.data(this, 'MultipleImageWidget', {
     options : _options(this, options)
    });
    _init(this);
   }
  });
 };
 // 方法.
 $.fn.MultipleImageWidget.methods = {
  options : function(jq) {
   return $.data(jq[0], 'MultipleImageWidget').options;
  },
  deleteImage : function(jq, param) {
   return jq.each(function() {
    _deleteImage($(this), param);
   });
  },
  hideImage : function(jq) {
   return jq.each(function() {
    _hideImage($(this));
   });
  },
  uploadImage : function(jq) {
   return jq.each(function() {
    _uploadImage($(this));
   });
  },
  listImage : function(jq) {
   return jq.each(function() {
    _listImage($(this));
   });
  }
 };
 // 默认设置.
 $.fn.MultipleImageWidget.defaults = {
  dataBindProps : [ 'mainId' ],
  dataOptionProps : [ 'listImageUrl', 'itemImpl', 'allowUpload' ],
  widgetTmpl : " <script type=\"text/plain\" id=\"{{:targetId}}UEditor\" ></script><a class=\"btn btn-primary\" onclick=\"$('#{{:targetId}}').MultipleImageWidget('uploadImage');\">浏览图片</a><a class=\"btn btn-primary\" onclick=\"$('#{{:targetId}}').MultipleImageWidget('hideImage');\">显示/隐藏图片</a><div id=\"{{:targetId}}WidgetPreviewContainer\"></div>",
  listTmpl : " <script type=\"text/plain\" id=\"{{:targetId}}UEditor\" ></script><div id=\"{{:targetId}}ListPreviewContainer\"></div>"
 };
})(jQuery);
