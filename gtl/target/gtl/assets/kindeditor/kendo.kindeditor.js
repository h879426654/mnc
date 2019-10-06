//KindEditor
(function($) {
 var kendo = window.kendo, ui = kendo.ui, Widget = ui.Widget;
 var kindEditor = window.KindEditor;
 var defaultOptions = {
  uploadJson : 'kindEditorFileUpload.do',
  wellFormatMode : false,
  filterMode : false,
  items : [ 'quickformat', '|',  'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull',
   '|', 'bold', 'italic', 'underline', 'strikethrough', '|', 'image', '|', 'link', 'unlink' ]
 };

 var KindEditor = Widget.extend({
  init : function(element, options) {
   var that = this;
   options = options || {};
   var thatOptions = $.extend({}, defaultOptions, options);
   Widget.fn.init.call(that, element, options);
   // console.log('options:' + JSON.stringify(thatOptions));
   this.editor = kindEditor.create(element, thatOptions);
  },
  options : {
   name : "KindEditor",
   uploadJson : 'kindEditorFileUpload.do',
   wellFormatMode : false,
   filterMode : false,
   items : [ 'quickformat', '|',  'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter',
    'justifyright', 'justifyfull', '|', 'bold', 'italic', 'underline', 'strikethrough', '|', 'image', '|', 'link', 'unlink' ]
  },
  value : function(html) {
   if (html === undefined) {
    this.editor.sync();
    return this.element.text();
   } else {
    this.element.text(html);
    this.editor.html(html);
   }
  },
  sync : function() {
   this.editor.sync();
  }
 });
 ui.plugin(KindEditor);
})(jQuery);

// KindUploadButton
(function($) {
 var kendo = window.kendo, ui = kendo.ui, Widget = ui.Widget;
 var KindEditor = window.KindEditor;
 var defaultOptions = {
  uploadJson : 'kindEditorFileUpload.do',
  getJson : 'getKindEditorFileUpload.do',
  fieldName : 'imgFile',
  maxWidth : 640,
  maxHeight : 960,
  thumbWidth : 150,
  thumbHeight : 150,
  buttonId : '',
  imgId : '',
  thumbImgId : '',
  uploadId : '',
  bindFileId : '',
  bindPathId : '',
  bindThumbPathId : '',
  bindUrlId : '',
  bindThumbUrlId : ''
 };
 var KindUploadButton = Widget.extend({
  init : function(element, options) {
   var that = this;
   options = options || {};
   // options.
   var thatOptions = $.extend({}, defaultOptions, options);
   Widget.fn.init.call(that, element, options);
   that.thatOptions = thatOptions;
   // console.log('options:' + JSON.stringify(thatOptions));
   KindEditor.ready(function(K) {
    var button = element;
    if (thatOptions.buttonId != '') {
     button = $('#' + thatOptions.buttonId);
    }
    var fileId = '';
    if (thatOptions.bindFileId != '') {
     fileId = $("#" + that.thatOptions.bindFileId).val();
    }
    var extraParams = {
     maxWidth : thatOptions.maxWidth,
     maxHeight : thatOptions.maxHeight,
     thumbWidth : thatOptions.thumbWidth,
     thumbHeight : thatOptions.thumbHeight,
     fileId : fileId
    };
    // console.log('fileId:' + fileId)
    // uploadbutton.
    var uploadbutton = K.uploadbutton({
     button : button,
     fieldName : thatOptions.fieldName,
     url : thatOptions.uploadJson,
     extraParams : extraParams,
     afterUpload : function(data) {
      // console.log('afterUpload:'+JSON.stringify(data));
      if (data.error == 0) {
       that.render(data);
      } else {
       alert('文件上传失败!');
      }
     },
     afterError : function(str) {
      alert('自定义错误信息: ' + str);
     }
    });

    uploadbutton.fileBox.change(function(e) {
     uploadbutton.submit();
    });

    that.uploadbutton = uploadbutton;
    // 如果指定了触发上传事件的组件.
    if (thatOptions.uploadId != "") {
     $(uploadbutton.div[0]).hide();
     $("#" + thatOptions.uploadId).click(function() {
      that.upload();
     });
    } else {
     $(uploadbutton.div[0]).hide();
    }
    // 同步
    that.sync();
   });
  },
  options : {
   name : "KindUploadButton",
   uploadJson : 'kindEditorFileUpload.do',
   getJson : 'getKindEditorFileUpload.do',
   fieldName : 'imgFile',
   maxWidth : 640,
   maxHeight : 960,
   thumbWidth : 150,
   thumbHeight : 150,
   buttonId : '',
   imgId : '',
   thumbImgId : '',
   uploadId : '',
   bindFileId : '',
   bindPathId : '',
   bindThumbPathId : '',
   bindUrlId : '',
   bindThumbUrlId : ''
  },
  fileId : function(val) {
   if (val === undefined) {
    if (this.thatOptions.bindFileId != '') {
     var fileId = $("#" + this.thatOptions.bindFileId).val();
     this.thatOptions.fileId = fileId;
    }
    return this.thatOptions.fileId;
   } else {
    this.thatOptions.fieldId = val;
    $(this.uploadbutton.form[0]).find('input[name="fileId"]').val(val);
    if (this.thatOptions.bindFileId != '') {
     $("#" + this.thatOptions.bindFileId).val(val);
    }
   }
  },
  debug : function() {
   var that = this;
   var thatOptions = that.thatOptions;
  },
  sync : function() {
   var that = this;
   var thatOptions = that.thatOptions;
   // console.log('sync:thatOptions:' + JSON.stringify(thatOptions));
   // bindFileId
   if (thatOptions.bindFileId != '') {
    var fileId = $("#" + thatOptions.bindFileId).val();
    // console.log('bindFileId=' + thatOptions.bindFileId);
    // console.log('fileId=' + fileId);
    if (fileId == undefined || fileId == '') {
     return;
    }
    $.post(thatOptions.getJson, {
     fileId : fileId
    }, function(data) {
     var data = $.parseJSON(data);
     if (data.success) {
      that.render(data);
     } else {
      // CrudApp.alert(data.message);
      that.fileId(fileId);
      that.renderEmpty();
     }
    });
   }
  },
  // 显示选择文件对话框.
  upload : function() {
   this.uploadbutton.fileBox[0].click();
  },
  // 渲染界面
  renderEmpty : function() {
   var that = this;
   var thatOptions = that.thatOptions;
   // bindFileId
   if (thatOptions.bindFileId != '') {
    // $("#" + thatOptions.bindFileId).val('');
   }
   // bindPathId
   if (thatOptions.bindPathId != '') {
    $("#" + thatOptions.bindPathId).val('');
   }
   // bindThumbPathId
   if (thatOptions.bindThumbPathId != '') {
    $("#" + thatOptions.bindThumbPathId).val('');
   }
   // bindUrlId
   if (thatOptions.bindUrlId != '') {
    $("#" + thatOptions.bindUrlId).val('');
   }
   // bindThumbUrlId
   if (thatOptions.bindThumbUrlId != '') {
    $("#" + thatOptions.bindThumbUrlId).val('');
   }
   // bind imgId src.
   if (thatOptions.imgId != '') {
    $("#" + thatOptions.imgId).hide();
   }
   // bind thumbImgId src.
   if (thatOptions.thumbImgId != '') {
    $("#" + thatOptions.thumbImgId).hide();
   }
  },
  // 渲染界面
  render : function(data) {
   var that = this;
   var thatOptions = that.thatOptions;
   // console.log('render:thatOptions:' + JSON.stringify(thatOptions));
   // 更新fileId.
   if (data.fileId) {
    this.fileId(data.fileId);
   }
   // console.log('render:' + JSON.stringify(data));
   // bindFileId
   if (data.fileId && thatOptions.bindFileId != '') {
    var fileId = $("#" + thatOptions.bindFileId).val();
    if (fileId == '') {
     // console.log('setBindFileId to ' + fileId);
     $("#" + thatOptions.bindFileId).val(data.fileId);
    }
   }
   // bindPathId
   if (data.path && thatOptions.bindPathId != '') {
    $("#" + thatOptions.bindPathId).val(data.path);
   }
   // bindThumbPathId
   if (data.thumbPath && thatOptions.bindThumbPathId != '') {
    $("#" + thatOptions.bindThumbPathId).val(data.thumbPath);
   }
   // bindUrlId
   if (data.url && thatOptions.bindUrlId != '') {
    $("#" + thatOptions.bindUrlId).val(data.url);
   }
   // bindThumbUrlId
   if (data.thumbUrl && thatOptions.bindThumbUrlId != '') {
    $("#" + thatOptions.bindThumbUrlId).val(data.thumbUrl);
   }
   // bind imgId src.
   if (data.url && thatOptions.imgId != '') {
    $("#" + thatOptions.imgId).prop('src', data.url + '?' + new Date().getTime());
    $("#" + thatOptions.imgId).show();
   }
   // bind thumbImgId src.
   if (data.thumbUrl && thatOptions.thumbImgId != '') {
    $("#" + thatOptions.thumbImgId).prop('src', data.thumbUrl + '?' + new Date().getTime());
    $("#" + thatOptions.thumbImgId).show();
   }
  }
 });
 ui.plugin(KindUploadButton);
})(jQuery);