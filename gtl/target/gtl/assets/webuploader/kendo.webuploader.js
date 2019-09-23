(function($) {
 var kendo = window.kendo, ui = kendo.ui, Widget = ui.Widget;
 var WebUploader = window.WebUploader;
 var Webuploader = Widget.extend({
  /**
   * 日志功能,按options.debug控制开关.
   */
  log : function() {
   if (this.options.debug && arguments && arguments.length > 0) {
    for (var i = 0; i < arguments.length; i++) {
     console.log(this.options.name + ':' + JSON.stringify(arguments[i]));
    }
   }
  },
  /**
   * Widget初始化函数.
   */
  init : function(element, options) {
   // 初始化选项.
   var that = this;
   Widget.fn.init.call(that, element, options);
   that.options = $.extend({}, that.options, options);
   this.log('init.options', that.options);
   that.doInit(that);
   that.didInit(that);
  },
  /**
   * Widget选项.
   */
  options : {
   // Widget名称.
   name : "Webuploader",
   // 调试开关.
   debug : false,
   // 选完文件后，是否自动上传。
   auto : true,
   // swf文件路径
   swf : '/assets/webuploader/Uploader.swf',
   // 选择文件的按钮。可选。
   // 内部根据当前运行是创建，可能是input元素，也可能是flash.
   pick : '',
   // 只允许选择图片文件。
   accept : {
    title : 'Images',
    extensions : 'gif,jpg,jpeg,bmp,png',
    mimeTypes : 'image/*'
   },
   // fileNumLimit {int} [可选] [默认值：undefined]
   // fileNumLimit : 1,
   // fileSizeLimit {int} [可选] [默认值：undefined]
   // fileSizeLimit : 1024*100,
   // fileSingleSizeLimit {int} [可选] [默认值：undefined]
   // 验证单个文件大小是否超出限制, 超出则不允许加入队列。
   // fileSingleSizeLimit : 1024*100,
   // 服务端处理参数.
   uploadJson : 'kindEditorFileUpload.do',
   getJson : 'getKindEditorFileUpload.do',
   fieldName : 'imgFile',
   maxWidth : 640,
   maxHeight : 960,
   thumbWidth : 150,
   thumbHeight : 150,
   imgId : '',
   thumbImgId : '',
   uploadId : '',
   bindFileId : '',
   bindPathId : '',
   bindThumbPathId : '',
   bindUrlId : '',
   bindThumbUrlId : ''
  },
  /**
   * 执行初始化.
   */
  doInit : function(that) {
   // 额外参数.
   that.extraParams = {};
   var fileId = '';
   if (that.options.bindFileId != '') {
    fileId = $("#" + that.options.bindFileId).val();
   }
   that.extraParams = {
    maxWidth : that.options.maxWidth,
    maxHeight : that.options.maxHeight,
    thumbWidth : that.options.thumbWidth,
    thumbHeight : that.options.thumbHeight,
    fileId : fileId
   };
   var uploaderParams = {
    // 选完文件后，是否自动上传。
    auto : that.options.auto,
    // swf文件路径
    swf : that.options.swf,
    pick : that.options.pick,
    // 文件接收服务端。
    server : that.options.uploadJson,
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick : that.options.pick,
    // 只允许选择图片文件。
    accept : that.options.accept,
    fileNumLimit : that.options.fileNumLimit,
    fileSizeLimit : that.options.fileSizeLimit,
    fileSingleSizeLimit : that.options.fileSingleSizeLimit,
    // 文件上传请求的参数表，每次发送都会发送此对象中的参数
    formData : that.extraParams,
    // fileVal {Object} [可选] [默认值：'file'] 设置文件上传域的name。
    fileVal : that.options.fieldName
   };
   this.log('uploaderParams', uploaderParams);
   // 创建webuploader.
   that.uploader = WebUploader.create(uploaderParams);
   // 监听事件.
   that.uploader.on('fileQueued', function(file) {
    that.fileQueued(file);
   });
   that.uploader.on('uploadProgress', function(file) {
    that.uploadProgress(file);
   });
   that.uploader.on('uploadSuccess', function(file) {
    that.uploadSuccess(file);
   });
   that.uploader.on('uploadError', function(file) {
    that.uploadError(file);
   });
   that.uploader.on('uploadComplete', function(file) {
    that.uploadComplete(file);
   });
   that.uploader.on('uploadAccept', function(obj, result) {
    return that.uploadAccept(obj, result);
   });
  },
  /**
   * 初始化以后执行.
   */
  didInit : function(that) {
   // 同步.
   that.sync();
  },
  fileQueued : function(file) {
  },
  uploadProgress : function(file, percentage) {
  },
  uploadSuccess : function(file) {
   this.log('uploadSuccess', file.name);
  },
  uploadError : function(file) {
   this.log('uploadError', file.name);
  },
  uploadComplete : function(file) {
   this.log('uploadComplete', file.name);
  },
  /**
   * 处理上传返回的json.
   */
  uploadAccept : function(obj, result) {
   if (result && result.success) {
    this.render(result);
    return true;
   }
   return false;
  },
  /**
   * 返回或者设置fileId.
   */
  fileId : function(val) {
   if (val === undefined) {
    if (this.options.bindFileId != '') {
     var fileId = $("#" + this.options.bindFileId).val();
     this.options.fileId = fileId;
    }
    return this.options.fileId;
   } else {
    this.options.fieldId = val;
    this.extraParams.fileId = val;
   }
  },
  /**
   * 执行同步.
   */
  sync : function() {
   this.log("sync");
   var that = this;
   $(that.element).find('div').attr('style','');
   var options = that.options;
   this.log("options.bindFileId=",options.bindFileId);
   // bindFileId
   if (options.bindFileId != '') {
    var fileId = $("#" + options.bindFileId).val();
    that.log("fileId",fileId);
    if (fileId == undefined || fileId == '') {
     that.log("fileId == undefined || fileId == ''");
     return;
    }
    $.post(options.getJson, {
     fileId : fileId
    }, function(data) {
     var data = $.parseJSON(data);
     that.log(data);
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
  // 渲染界面
  renderEmpty : function() {
   var that = this;
   var options = that.options;
   // bindFileId
   if (options.bindFileId != '') {
    // $("#" + options.bindFileId).val('');
   }
   // bindPathId
   if (options.bindPathId != '') {
    $("#" + options.bindPathId).val('');
   }
   // bindThumbPathId
   if (options.bindThumbPathId != '') {
    $("#" + options.bindThumbPathId).val('');
   }
   // bindUrlId
   if (options.bindUrlId != '') {
    $("#" + options.bindUrlId).val('');
   }
   // bindThumbUrlId
   if (options.bindThumbUrlId != '') {
    $("#" + options.bindThumbUrlId).val('');
   }
   // bind imgId src.
   if (options.imgId != '') {
    $("#" + options.imgId).hide();
   }
   // bind thumbImgId src.
   if (options.thumbImgId != '') {
    $("#" + options.thumbImgId).hide();
   }
  },
  // 渲染界面
  render : function(data) {
   var that = this;
   var options = that.options;
   // 更新fileId.
   if (data.fileId) {
    this.fileId(data.fileId);
   }
   // bindFileId
   if (data.fileId && options.bindFileId != '') {
    var fileId = $("#" + options.bindFileId).val();
    if (fileId == '') {
     $("#" + options.bindFileId).val(data.fileId);
    }
   }
   // bindPathId
   if (data.path && options.bindPathId != '') {
    $("#" + options.bindPathId).val(data.path);
   }
   // bindThumbPathId
   if (data.thumbPath && options.bindThumbPathId != '') {
    $("#" + options.bindThumbPathId).val(data.thumbPath);
   }
   // bindUrlId
   if (data.url && options.bindUrlId != '') {
    $("#" + options.bindUrlId).val(data.url);
   }
   // bindThumbUrlId
   if (data.thumbUrl && options.bindThumbUrlId != '') {
    $("#" + options.bindThumbUrlId).val(data.thumbUrl);
   }
   // bind imgId src.
   if (data.url && options.imgId != '') {
    $("#" + options.imgId).prop('src', data.url + '?' + new Date().getTime());
    $("#" + options.imgId).show();
   }
   // bind thumbImgId src.
   if (data.thumbUrl && options.thumbImgId != '') {
    $("#" + options.thumbImgId).prop('src', data.thumbUrl + '?' + new Date().getTime());
    $("#" + options.thumbImgId).show();
   }
  }
 });
 ui.plugin(Webuploader);
})(jQuery);