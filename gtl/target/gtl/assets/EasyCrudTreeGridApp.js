//将表单序列化成json对象
$.fn.serializeObject = function() {
 var o = {};
 var a = this.serializeArray();
 $.each(a, function() {
  if (o[this.name]) {
   if (!o[this.name].push) {
    o[this.name] = [ o[this.name] ];
   }
   o[this.name].push(this.value || '');
  } else {
   o[this.name] = this.value || '';
  }
 });
 return o;
};

// 简单Crud应用程序.基于jQueryEasyUI。
// #search_form:显示查询条件
// #detail_form:添加/编辑表单.
// #grid:结果列表
var EasyCrudApp = EasyCrudApp || {};

EasyCrudApp = (function() {
 var settings = {
  urlBase : '',
  urlGet : 'get.do',
  urlSave : 'save.do',
  urlDelete : 'del.do',
  uiSearchForm : 'search_form',
  uiGrid : 'grid',
  uiDetailDialog : 'detail_dialog',
  uiDetailForm : 'detail_form',
  uiIdField : 'id',
  ckfinderBasePath : '/ckfinder/',
  ckeditorBasePath : '/ckeditor/',
  contextPath : '/',
  kindeditorUploadJson : 'kindEditorFileUpload.do',
  onReflashReloadWindow : false,
  onEditDialogCloseReloadWindow : false,
  onEditToGet : false,
  uiTreeselectTree : 'treeselect_tree',
  uiTreeselectForm : 'treeselect_form',
  uiTreeselectDialog : 'treeselect_dialog',
  urlTreeselectGet : '',
  urlTreeselectGetOneParamName : '',
  urlTreeselectGetCheckIds : 'item',
  urlTreeselectSave : '',
  urlTreeselectSaveOneParamName : '',
  urlTreeselectSaveManyParamName : ''
 };

 var uiSearchForm, uiGrid, uiDetailDialog, uiDetailForm;

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

 // 初始化.
 function initializeApp(options) {
  $.extend(settings, options);
  uiSearchForm = $("#" + settings.uiSearchForm);
  uiGrid = $("#" + settings.uiGrid);
  uiDetailDialog = $("#" + settings.uiDetailDialog);
  uiDetailForm = $("#" + settings.uiDetailForm);
  $.fn.form.defaults.onLoadSuccess = onLoadSuccess;
  onCreateEditDialog();
  // initEditors();
 }

 // 返回保存表单提交的url
 function getUrlSave() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlSave;
  } else {
   return settings.urlSave;
  }
 }

 // 返回删除单条记录的url
 function getUrlDelete() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlDelete;
  } else {
   return settings.urlDelete;
  }
 }

 // 创建编辑对话框
 function onCreateEditDialog() {
  // 初始化弹出框
  $(uiDetailDialog).dialog({
   buttons : [ {
    text : '保存',
    iconCls : 'icon-save',
    handler : function() {
     onSave();
    }
   }, {
    text : '关闭',
    iconCls : 'icon-cancel',
    handler : function() {
     onEditDialogClose();
    }
   } ]
  });
 }

 function on(what) {
  if ('add' == what) {
   onAdd();
   return;
  }
  if ('edit' == what) {
   onEdit();
   return;
  }
  if ('delete' == what) {
   onDelete();
   return;
  }
  if ('save' == what) {
   onSave();
   return;
  }
  if ('search' == what) {
   onSearch();
   return;
  }
  if ('clearSearch' == what) {
   onClearSearch();
   return;
  }
  if ('reflash' == what) {
   onReflash();
   return;
  }
  if ('treeselect' == what) {
   treeselect();
   return;
  }
  if (settings.on) {
   settings.on(what);
  }
 }

 // 返回查询条件
 function getSearchParams() {
  return $(uiSearchForm).serializeObject();
 }

 // 刷新列表
 function onReflash() {
  // $(uiGrid).treegrid('load', getSearchParams());
  // $(uiGrid).treegrid('clearSelections');
  if (settings.onReflashReloadWindow) {
   // 直接刷新页面,释放资源
   window.location.reload();
  } else {
   onSearch();
  }
 }

 // 清空查询条件
 function onClearSearch() {
  $(uiSearchForm).form('clear');
  onSearch();
 }

 // 执行查询
 function onSearch() {
  $(uiGrid).treegrid('clearSelections');
  $(uiGrid).treegrid('load', getSearchParams());
 }

 // 处理添加
 function onAdd() {
  $(uiDetailForm).form('clear');
  onEditDialogOpen();
  on('didAdd');
 }

 // 处理编辑
 function onEdit() {
  var rows = $(uiGrid).treegrid('getSelections');
  if (rows.length > 1) {
   warning('修改只能选择一条记录!');
   return;
  }
  var row = $(uiGrid).treegrid('getSelected');
  if (row == null) {
   warning('请选择要修改的记录!');
   return;
  }
  if (settings.onEditToGet) {
   var idField = settings.uiIdField;
   var idValue = row[idField];
   var getParams = {};
   getParams[idField] = idValue;
   $.post(settings.urlGet, getParams, function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     onEditFormLoad(data.item);
    } else {
     alert(data.message);
    }
   });
  } else {
   onEditFormLoad(row);
  }
 }
 // 处理编辑
 function toEdit(rowId) {
  var idField = settings.uiIdField;
  var idValue = rowId;
  var getParams = {};
  getParams[idField] = idValue;
  $.post(settings.urlGet, getParams, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    onEditFormLoad(data.item);
   } else {
    alert(data.message);
   }
  });
 }
 function onEditFormLoad(row) {
  $(uiDetailForm).form('load', row);
  onEditDialogOpen();
  on('didEdit');
 }
 function toEditFormLoadRow(formId, row) {
  $(formId).form('load', row);
 }
 function toEditFormLoadUrl(formId, url, getParams) {
  $.post(url, getParams, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    toEditFormLoadRow(formId, data.item);
   } else {
    alert(data.message);
   }
  });
 }
 // 关闭编辑对话框
 function onEditDialogClose() {
  $(uiDetailDialog).dialog('close');
  if (settings.onEditDialogCloseReloadWindow) {
   // 直接刷新页面,释放资源
   window.location.reload();
  }
 }

 // 打开编辑对话框
 function onEditDialogOpen() {
  $(uiDetailDialog).dialog('open');
  onAfterEditDialogOpen();
 }
 // 打开编辑对话框以后的回调
 function onAfterEditDialogOpen() {
  initEditors();
 }
 // 删除选中的记录.
 function onDelete() {
  var rows = $(uiGrid).treegrid('getSelections');
  if (rows.length == 0) {
   warning('请选择要删除的记录!');
   return;
  }
  var array = new Array();
  for (var i = 0; i < rows.length; i++) {
   var row = rows[i];
   array.push(row[settings.uiIdField]);
  }
  $.messager.confirm('确认', '确认删除?', function(confirmed) {
   if (!confirmed) {
    return;
   }
   $.ajax({
    type : "POST",
    dataType : "json",
    url : getUrlDelete(),
    data : {
     id : array.join(',')
    },
    success : function(data) {
     toast(data.message);
     if (data.success) {
      onReflash();
     }
    }
   });
  })

 }

 // 处理保存
 function onSave() {
  $(uiDetailForm).form('submit', {
   url : getUrlSave(),
   onSubmit : function() {
    try {
     onWillSave();
    } catch (e) {
     alert(e);
    }
    if (!$(this).form('validate')) {
     alert('请验证您的输入!');
     return false;
    }
    return true;
   },
   success : function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     onEditDialogClose();
     toast(data.message);
     onReflash();
    } else {
     alert(data.message);
    }
   }
  });
 }

 // 处理表单加载完成
 function onLoadSuccess(data) {
  // console.log('onLoadSuccess:');
  initEditors();
  if (settings.onLoadSuccess) {
   settings.onLoadSuccess(data);
  }
 }
 // 表单提交之前处理.
 function onWillSave() {
  $(".easyui-ckeditor").each(function() {
   // getCKEditorValue(this);
  });
  // kindeditor
  $(".easyui-kindeditor").each(function() {
   getKindEditorValue(this);
  });
 }

 function initEditors() {
  // imageUpload
  $(".image-uploader").each(function() {
   // setImageUploaderValue(this);
  });
  // kindeditor
  $(".easyui-kindeditor").each(function() {
   setKindEditorValue(this);
  });
  // image-upload-button
  $(".image-upload-button").each(function() {
   setImageUploadButtonValue(this);
  });
  // fancybox
  // $('.fancybox').fancybox();
  // jKit
  try {
   $('body').jKit();
  } catch (e) {
  }
  // VideoImageWidget
  $(".VideoImageWidget").each(function() {
   setVideoImageWidgetValue(this);
  });
 }
 function destroyEditors() {
  // imageUpload
  $(".image-uploader").each(function() {
   destroyImageUploader(this);
  });
 }

 function getCKEditor(that) {
  /*
   * var $this = $(that); var editor = $this.data('ckeditor'); if (typeof editor ==
   * 'undefined') { editor = CKEDITOR.replace(that);
   * CKFinder.setupCKEditor(editor, settings.ckfinderBasePath);
   * $this.data('ckeditor', editor); } return editor;
   */
 }

 function setCKEditorValue(that) {
  /*
   * var editor = getCKEditor(that); editor.setData($(that).prop('value'));
   */
 }

 function getCKEditorValue(that) {
  /*
   * var editor = getCKEditor(that); var data = editor.getData();
   * $(that).prop('value', data); return data;
   */
 }

 // 通过ckfinder上传、选择图片
 function getImageEditor(that) {
  var $this = $(that);
  var editor = $this.data('ckfinder');
  var dataImg = $this.data('img');
  var $thisImg = $('#' + dataImg);
  if (typeof editor == 'undefined') {
   editor = new CKFinder();
   // editor.basePath = '../'; // The path for the installation of CKFinder
   // (default = "/ckfinder/").
   editor.basePath = settings.ckfinderBasePath;
   editor.resourceType = 'Images';
   editor.selectActionFunction = function(fileUrl) {
    var url = fileUrl;
    if (url.substring(0, settings.contextPath.length) != settings.contextPath) {
     url = settings.contextPath + fileUrl;
    }
    $this.val(url);
    $thisImg.prop('src', url);
   }
   // click image to upload select.
   $thisImg.on('click', function() {
    editor.popup();
   });
   $this.data('ckfinder', editor);
  }
  return editor;
 }

 function setImageEditorValue(that) {
  var editor = getImageEditor(that);
  var $this = $(that);
  var dataImg = $this.data('img');
  var $thisImg = $('#' + dataImg);
  if ($this.val() != '') {
   $thisImg.prop('src', $this.val());
  }
 }

 // 通过plupload上传图片{
 function getImageUploader(that) {
  var $this = $(that);
  var uploader = $this.data('uploader');
  var dataImg = $this.data('img');
  var $thisImg = $('#' + dataImg);
  if (typeof uploader == 'undefined') {
   $thisImg.attr('bindTo', $this.attr('id'));
   uploader = createImageUploader($thisImg.attr('id'));
   $this.data('uploader', uploader);
  }
  return uploader;
 }
 function setImageUploaderValue(that) {
  console.log('setImageUploaderValue:' + that);
  var uploader = getImageUploader(that);
  var $this = $(that);
  var dataImg = $this.data('img');
  var $thisImg = $('#' + dataImg);
  if ($this.val() != '') {
   var showUrl = $thisImg.attr('showUrl');
   $thisImg.prop('src', showUrl + $this.val());
  } else {
   var blankUrl = $thisImg.attr('blankUrl');
   $thisImg.prop('src', blankUrl);
  }
 }
 function destroyImageUploader(that) {

 }
 function createImageUploader(uploadPickerId) {
  console.log('createImageUploader:' + uploadPickerId);
  var uploadPicker = $("#" + uploadPickerId);
  var uploadUrl = uploadPicker.attr('uploadUrl');
  var showUrl = uploadPicker.attr('showUrl');
  var bindTo = $("#" + uploadPicker.attr('bindTo'));
  var multipart_params = {};
  var flash_swf_url = uploadPicker.attr('flash_swf_url');
  if (flash_swf_url == '') {
   flash_swf_url = '../assets/plupload/js/Moxie.swf';
  }
  console.log('uploadUrl:' + uploadUrl);
  var uploader = new plupload.Uploader({
   browse_button : uploadPickerId, // you can pass in id...
   url : uploadUrl,
   multipart_params : multipart_params,
   chunk_size : '10mb',
   unique_names : true,
   filters : {
    max_file_size : '10mb',
    mime_types : [ {
     title : "图片",
     extensions : "jpg,png"
    } ]
   },
   flash_swf_url : flash_swf_url,
   init : {
    UploadProgress : function(up, file) {
     // console.log('UploadProgress id:'+file.id+' percent:'+file.percent+'%');
    },
    FilesAdded : function(up, files) {
     try {
      plupload.each(files, function(file) {
       console.log('FilesAdded name:' + file.name + ' size:' + file.size);
       up.start();
      });
     } catch (e) {
      alert(e);
     }
    },
    FileUploaded : function(up, file, info) {
     var json = $.parseJSON(info.response);
     if (json.success) {
      up.uploadPicker.attr('src', showUrl + json.path);
      bindTo.val(json.path);
     }
     console.log(info.response);
    },
    Error : function(up, err) {
     if (err.code == -600) {
      alert('文件尺寸不允许超过10MB!');
      return;
     }
     if (err.code == -601) {
      alert('请上传图片文件!');
      return;
     }
     alert(err + '(' + err.code + ')');
    }
   }
  });
  uploader.init();
  uploader.uploadPicker = uploadPicker;
  /*
   * var path= bindTo.val(); if(path!=''){
   * uploadPicker.attr('src',showUrl+path); }
   */
  return uploader;
 }
 // 通过plupload上传图片}
 // kindeditor{
 function getKindEditor(that) {
  var $this = $(that);
  var editor = $this.data('kindeditor');
  if (typeof editor == 'undefined') {
   editor = KindEditor.create(that, {
    uploadJson : settings.kindeditorUploadJson,
    items : [ 'source', '|', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter',
     'justifyright', 'justifyfull', '|', 'fontname', 'fontsize', '|', 'bold', 'italic', 'underline', 'strikethrough', '|', 'image', 'link',
     'unlink' ]
   });
   $this.data('kindeditor', editor);
  }
  return editor;
 }

 function setKindEditorValue(that) {
  var editor = getKindEditor(that);
  editor.html($(that).prop('value'));
 }

 function getKindEditorValue(that) {
  var $this = $(that);
  var editor = getKindEditor(that);
  editor.sync();
  return $this.val();
 }
 // kindeditor}
 // kindeditor image-upload-button{
 function getImageUploadButton(that) {
  var $this = $(that);
  var editor = $this.data('ImageUploadButton');
  if (typeof editor == 'undefined') {
   editor = createImageUploadButton($this.attr('id'));
   $this.data('ImageUploadButton', editor);
  }
  return editor;
 }

 function setImageUploadButtonValue(that) {
  var editor = getImageUploadButton(that);
  var path = $(that).prop('value');
  var img = $("#" + $(that).attr('data-img'));
  var showUrl = $(that).attr('data-showUrl');
  if (path != '') {
   img.attr('src', showUrl + path);
  } else {
   img.attr('src', $(that).attr('data-blankUrl'));
  }
 }

 function getImageUploadButtonValue(that) {
  var $this = $(that);
  var editor = getImageUploadButton(that);
  return $this.val();
 }

 function createImageUploadUrlParams(field) {
  var $this = $(field);
  var params = new Array();
  params.push('?maxWidth=');
  params.push($this.attr('data-maxWidth'));
  params.push('&maxHeight=');
  params.push($this.attr('data-maxHeight'));
  params.push('&thumbWidth=');
  params.push($this.attr('data-thumbWidth'));
  params.push('&thumbHeight=');
  params.push($this.attr('data-thumbHeight'));
  return params.join('');
 }

 function createImageUploadButton(fieldId) {
  var field = $('#' + fieldId);
  var button = $('#' + field.attr('data-button'));
  var urlParams = createImageUploadUrlParams(field);
  var uploadbutton = KindEditor.uploadbutton({
   button : button,
   fieldName : 'imgFile',
   width : 64,
   url : field.attr('data-uploadUrl') + urlParams,
   afterUpload : function(data) {
    if (data.error === 0) {
     var path = data.path;
     var img = $("#" + field.attr('data-img'));
     var showUrl = field.attr('data-showUrl');
     img.attr('src', showUrl + path);
     field.val(path);
     // bindFileId
     var bindFileId = field.attr('data-bindFileId');
     if (bindFileId != '') {
      $("#" + bindFileId).val(data.fileId);
     }
     // bindThumbPathId
     var bindThumbPathId = field.attr('data-bindThumbPathId');
     if (bindThumbPathId != '') {
      $("#" + bindThumbPathId).val(data.thumbPath);
     }
     // bindPathId
     var bindPathId = field.attr('data-bindPathId');
     if (bindPathId != '') {
      $("#" + bindPathId).val(data.path);
     }

    } else {
     alert(data.message);
    }
   },
   afterError : function(str) {
    alert('自定义错误信息: ' + str);
   }
  });
  uploadbutton.fileBox.change(function(e) {
   uploadbutton.submit();
  });
  return uploadbutton;
 }

 // kindeditor image-upload-button}
 // VideoImageWidget{
 function getVideoImageWidget(that) {
  var $this = $(that);
  var editor = $this.data('VideoImageWidget');
  if (typeof editor == 'undefined') {
   editor = $this.VideoImageWidget();

  }
  return editor;
 }
 function setVideoImageWidgetValue(that) {
  var $editor = getVideoImageWidget(that);
  var dataBindId = $(that).attr('data-bind-id');
  var videoIdField = $("#" + dataBindId);
  generateGuid(videoIdField, function() {
   $(that).VideoImageWidget('load');
  });
 }

 function getVideoImageWidgetValue(that) {

 }
 // VideoImageWidget}
 // generateGuid
 function generateGuid(that, success) {
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
 }
 // 全局默认配置
 function defaultSettings(options) {
  $.extend(settings, options);
 }
 function getSelections() {
  return $(uiGrid).treegrid('getSelections');
 }
 // treeselect{
 function treeselectClearCheck(treeselect_tree) {
  var nodes = $(treeselect_tree).tree('getChecked');
  for (var i = 0; i < nodes.length; i++) {
   var node = $(treeselect_tree).tree('find', nodes[i]['id']);
   if (node && node.target) {
    $(treeselect_tree).tree('uncheck', node.target);
   }
  }
 }
 function treeselectSetCheckIds(treeselect_tree, checkIds) {
  treeselectClearCheck(treeselect_tree);
  var ids = checkIds.split(',');
  for (var i = 0; i < ids.length; i++) {
   var node = $(treeselect_tree).tree('find', ids[i]);
   if (node && node.target) {
    $(treeselect_tree).tree('check', node.target);
   }
  }
 }
 function treeselectGetCheckIds(treeselect_tree) {
  var checkIds = [];
  var nodes = $(treeselect_tree).tree('getChecked');
  for (var i = 0; i < nodes.length; i++) {
   checkIds.push(nodes[i].id);
  }
  return checkIds.join(',');
 }
 function treeselect() {
  var rows = getSelections();
  if (rows.length != 1) {
   warning('选择一条记录!');
   return;
  }
  var row = rows[0];
  $("#" + settings.uiTreeselectForm).form('load', row);
  var params = {};
  params[settings.urlTreeselectGetOneParamName] = row['id'];
  $.post(settings.urlTreeselectGet, params, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    $("#" + settings.uiTreeselectDialog).dialog({
     buttons : [ {
      text : '保存',
      iconCls : 'icon-save',
      handler : function() {
       treeselectSave(row);
      }
     }, {
      text : '关闭',
      iconCls : 'icon-cancel',
      handler : function() {
       $("#" + settings.uiTreeselectDialog).dialog('close');
      }
     } ]
    });
    $("#" + settings.uiTreeselectDialog).dialog('open');
    treeselectSetCheckIds("#" + settings.uiTreeselectTree, data[settings.urlTreeselectGetCheckIds]);
   } else {
    alert(data.message);
   }
  });
 }
 function treeselectSave(row) {
  var checkIds = treeselectGetCheckIds("#" + settings.uiTreeselectTree);
  var params = {};
  params[settings.urlTreeselectSaveOneParamName] = row['id'];
  params[settings.urlTreeselectSaveManyParamName] = checkIds;
  $.post(settings.urlTreeselectSave, params, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    toast('保存成功');
   } else {
    alert(data.message);
   }
  });
 }
 // treeselect}
 return {
  init : initializeApp,
  initEditors : initEditors,
  on : on,
  defaultSettings : defaultSettings,
  getSelections : getSelections,
  warning : warning,
  alert : alert,
  toast : toast,
  onReflash : onReflash,
  onSearch : onSearch,
  toEdit : toEdit,
  toEditFormLoadUrl : toEditFormLoadUrl,
  treeselectSetCheckIds : treeselectSetCheckIds,
  treeselectGetCheckIds : treeselectGetCheckIds,
  treeselect : treeselect
 }
})();
