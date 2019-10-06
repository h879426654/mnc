(function($) {
 var kendo = window.kendo, ui = kendo.ui, Widget = ui.Widget;
 var defaultOptions = {

 };

 var BootstrapFileInput = Widget.extend({
  init : function(element, options) {
   var that = this;
   Widget.fn.init.call(that, element, options);
   var options = $.extend({}, defaultOptions, options);
   var fileinput = $(element).fileinput();
   that.fileinput = fileinput;
   $(element).on('fileuploaded', function(event, data, previewId, index) {
    that.onFileUploaded(event, data, previewId, index);
   });
  },
  reset : function() {
   $(this.element).fileinput('reset');
  },
  clear : function() {
   $(this.element).fileinput('_clearPreview');
  },
  refresh : function(params) {
   this.log('refresh');
   this.log(params);
   $(this.element).fileinput('refresh', params);
   this.log($(this.element).data('kendoBootstrapFileInput'));
   ;
  },
  string : function(params) {
   return JSON.stringify(params);
  },
  log : function(params) {
   try {
   // console.log(this.string(params));
   } catch (e) {
   }
  },
  options : {
   name : "BootstrapFileInput",
   ownerId : "",
   ownerClass : "",
   view : '0',// 是否为查看模式(不允许上传)
   uploadUrl : "",
   initUrl : "",
   deleteUrl : "",
   bindUrlId : "",
   multiple : "multiple"
  },
  generateGuid : function(that, success) {
   var $this = $(that);
   if ($this.val() == '') {
    $.getJSON("generateGuid.do", function(json) {
     if (json.success) {
      $this.val(json.item);
      if (success) {
       success.call();
      }
     }
    });
   } else {
    if (success) {
     success.call();
    }
   }
  },
  onFileUploaded : function(event, data, previewId, index) {
   this.log('onFileUploaded');
   var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
   this.log('response');
   this.log(response);
   this.onBindUrl(response);
   this.sync();
  },
  onBindUrl : function(data) {
   this.log('onBindUrl');
   if (data.initialPreview && data.initialPreview.length > 0) {
    var url = data.initialPreview[0];
    this.log(url);
    this.log(this.options.bindUrlId);
    if (this.options.bindUrlId != '') {
     var bindUrlEle = $("#" + this.options.bindUrlId);
     $(bindUrlEle).val(url);
    } else {
     this.log('no bindUrlId no bind work.');
    }
   }
  },
  sync : function() {
   this.clear();
   var ownerId = this.value(this.options.ownerId);
   var $this = this;
   if (ownerId == '') {
    var that = $("#" + ownerId);
    $this.generateGuid(that, function() {
     $this.load();
    });
   } else {
    $this.load();
   }
  },
  value : function(id) {
   var elements = $("#" + id);
   if (elements.length == 1) {
    return elements[0].value;
   }
   return id;
  },
  uploadExtraData : function() {
   var data = {};
   data.ownerId = this.value(this.options.ownerId);
   if (data.ownerId == '') {
    data.ownerId = 'dev';
   }
   data.ownerClass = this.options.ownerClass;
   data.view = this.options.view;
   data.multiple = this.options.multiple;
   return data;
  },
  load : function() {
   var that = this;
   this.log('options');
   this.log(this.options);
   // uploadExtraData
   var uploadExtraData = this.uploadExtraData();
   if (uploadExtraData.ownerId == '') {
    this.log('no ownerId no work.');
    return;
   }
   that.refresh({
    "uploadExtraData" : uploadExtraData
   });
   // initialPreview
   var initUrl = this.options.initUrl;
   $.post(initUrl, uploadExtraData, function(data) {
    var data = $.parseJSON(data);
    that.log(data);
    if (data.initialPreview) {
     that.refresh({
      "initialPreview" : data.initialPreview,
      "initialPreviewConfig" : data.initialPreviewConfig
     });
    }
   });
  },
  urls : function() {
   var that = this;
   // uploadExtraData
   var uploadExtraData = this.uploadExtraData();
   if (uploadExtraData.ownerId == '') {
    this.log('no ownerId no work.');
    return [];
   }
   // initialPreview
   var initUrl = this.options.initUrl;
   var data = $.ajax({
    url : initUrl,
    data : uploadExtraData,
    async : false,
    dataType:'json'
   }).responseJSON;
   if (data.initialPreview){
    return data.initialPreview;
   }else{
    return [];
   }
  }
 });
 ui.plugin(BootstrapFileInput);
})(jQuery);
