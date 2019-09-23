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
  urlSend : 'send.do',
  urlDelete : 'del.do',
  urlView : 'view.do',
  urlEnable : 'enable.do',
  urlDisable : 'disable.do',
  onViewToGet : false,
  uiSearchForm : 'search_form',
  uiGrid : 'grid',
  uiDetailDialog : 'detail_dialog',
  uiDetailForm : 'detail_form',
  uiViewDialog : 'view_dialog',
  uiViewForm : 'view_form',
  uiIdField : 'id',
  ckfinderBasePath : '/ckfinder/',
  ckeditorBasePath : '/ckeditor/',
  contextPath : '/',
  kindeditorUploadJson : 'kindEditorFileUpload.do',
  onReflashReloadWindow : false,
  onEditDialogCloseReloadWindow : false,
  onSaveDialogCloseReloadWindow : false,
  onSaveDialogCloseWindow : false,
  onEditToGet : false,
  uiTreeselectTree : 'treeselect_tree',
  uiTreeselectForm : 'treeselect_form',
  uiTreeselectDialog : 'treeselect_dialog',
  urlTreeselectGet : 'getUserRoles.do',
  urlTreeselectGetOneParamName : 'user',
  urlTreeselectGetCheckIds : 'item',
  urlTreeselectSave : 'saveUsersForRoles.do',
  urlTreeselectSaveOneParamName : 'users',
  urlTreeselectSaveManyParamName : 'roles',
  uiTreeselect1Tree : 'treeselect1_tree',
  uiTreeselect1Form : 'treeselect1_form',
  uiTreeselect1Dialog : 'treeselect1_dialog',
  urlTreeselect1Get : 'getUserRoles.do',
  urlTreeselect1GetOneParamName : 'user',
  urlTreeselect1GetCheckIds : 'item',
  urlTreeselect1Save : 'saveUsersForRoles.do',
  urlTreeselect1SaveOneParamName : 'users',
  urlTreeselect1SaveManyParamName : 'roles'
 };

 var uiSearchForm, uiGrid, uiDetailDialog, uiDetailForm, uiViewForm;

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
  try {
   uiViewDialog = $("#" + settings.uiViewDialog);
   uiViewForm = $("#" + settings.uiViewForm);
   onCreateViewDialog();
  } catch (e) {
  }
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

 // 返回发送短信单条记录的url
 function getUrlSend() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlSend;
  } else {
   return settings.urlSend;
  }
 }

 // 返回启用单条记录的url
 function getUrlEnable() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlEnable;
  } else {
   return settings.urlEnable;
  }
 }

 // 返回禁用单条记录的url
 function getUrlDisable() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlDisable;
  } else {
   return settings.urlDisable;
  }
 }

 // 返回查看的url
 function getUrlView() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlView;
  } else {
   return settings.urlView;
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

 function on(what, row) {
  if ('add' == what) {
   onAdd();
   return;
  }
  if ('edit' == what) {
   onEdit();
   return;
  }
  if ('view' == what) {
   onView();
  }
  if ('delete' == what) {
   onDelete();
   return;
  }
  if ('send' == what) {
   onSend();
   return;
  }
  if ('enable' == what) {
   onEnable();
   return;
  }
  if ('disable' == what) {
   onDisable();
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
  if ('treeselect1' == what) {
   treeselect1();
   return;
  }
  if (settings.on) {
   settings.on(what, row);
  }
 }

 // 返回查询条件
 function getSearchParams() {
  return $(uiSearchForm).serializeObject();
 }

 // 刷新列表
 function onReflash() {
  // $(uiGrid).datagrid('load', getSearchParams());
  // $(uiGrid).datagrid('clearSelections');
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
  $(uiGrid).datagrid('clearSelections');
  $(uiGrid).datagrid('load', getSearchParams());
 }

 // 处理添加
 function onAdd() {
  $(uiDetailForm).form('clear');
  onEditDialogOpen();
  on('didAdd');
 }

 // 处理编辑
 function onEdit() {
  var rows = $(uiGrid).datagrid('getSelections');
  if (rows.length > 1) {
   warning('修改只能选择一条记录!');
   return;
  }
  var row = $(uiGrid).datagrid('getSelected');
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
 function edit(rowId) {
  var idField = settings.uiIdField;
  var idValue = rowId;
  var getParams = {};
  getParams[idField] = idValue;
  $.post(settings.urlGet, getParams, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    $(uiDetailForm).form('load', data.item);
    on('didEdit', data.item);
   } else {
    // alert(data.message);
   }
  });
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
  on('didEdit', row);
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
  } else {
   onReflash();
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
  var rows = $(uiGrid).datagrid('getSelections');
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

 // 选定记录，发送短信.
 function onSend() {
  var rows = $(uiGrid).datagrid('getSelections');
  if (rows.length == 0) {
   warning('请选择记录!');
   return;
  }
  var array = new Array();
  for (var i = 0; i < rows.length; i++) {
   var row = rows[i];
   array.push(row[settings.uiIdField]);
  }
  $.messager.confirm('确认', '确认操作?', function(confirmed) {
   if (!confirmed) {
    return;
   }
   $.ajax({
    type : "POST",
    dataType : "json",
    url : getUrlSend(),
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

 // 选定记录，启用.
 function onEnable() {
  var rows = $(uiGrid).datagrid('getSelections');
  if (rows.length == 0) {
   warning('请选择记录!');
   return;
  }
  var array = new Array();
  for (var i = 0; i < rows.length; i++) {
   var row = rows[i];
   array.push(row[settings.uiIdField]);
  }
  $.messager.confirm('确认', '确认启用?', function(confirmed) {
   if (!confirmed) {
    return;
   }
   $.ajax({
    type : "POST",
    dataType : "json",
    url : getUrlEnable(),
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

 // 选定记录，禁用.
 function onDisable() {
  var rows = $(uiGrid).datagrid('getSelections');
  if (rows.length == 0) {
   warning('请选择记录!');
   return;
  }
  var array = new Array();
  for (var i = 0; i < rows.length; i++) {
   var row = rows[i];
   array.push(row[settings.uiIdField]);
  }
  $.messager.confirm('确认', '确认禁用?', function(confirmed) {
   if (!confirmed) {
    return;
   }
   $.ajax({
    type : "POST",
    dataType : "json",
    url : getUrlDisable(),
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
     // onEditDialogClose();
     if (settings.onSaveDialogCloseReloadWindow) {
      // 直接刷新页面,释放资源
      window.location.reload();
     } else {
      if (settings.onSaveDialogCloseWindow) {
       onEditDialogClose()
      } else {
       toast(data.message);
      }
      // onReflash();
     }

    } else {
     alert(data.message);
    }
   }
  });
 }

 // 处理表单加载完成
 function onLoadSuccess(data) {
  // console.log('onLoadSuccess:');
  initEditors(data);
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

 function initEditors(data) {
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
  // AppImageWidget
  $(".AppImageWidget").each(function() {
   setAppImageWidgetValue(this);
  });
  // MultipleImageWidget
  $(".MultipleImageWidget").each(function() {
   $(this).MultipleImageWidget();
  });
  // TemplateWidget
  $(".TemplateWidget").each(function() {
   setTemplateWidgetValue(this, data);
  });
  // EntOwnerChangeDetailWidget
  $(".EntOwnerChangeDetailWidget").each(function() {
   setEntOwnerChangeDetailWidgetValue(this);
  });

  // EntOwnerChangeDetailWidgetView
  $(".EntOwnerChangeDetailWidgetView").each(function() {
   setEntOwnerChangeDetailWidgetValue(this);
  });

  // FitzDetailListWidget
  $(".FitzDetailListWidget").each(function() {
   setFitzDetailListWidgetValue(this);
  });

  // FitzDetailListWidgetView
  $(".FitzDetailListWidgetView").each(function() {
   setFitzDetailListWidgetValueView(this);
  });
 }

 // kindeditor image-upload-button{
 function getImageUploadButton(that) {
  var $this = $(that);
  var editor = $this.data('ImageUploadButton');
  if (typeof editor == 'undefined') {
   editor = createImageUploadButton($this.attr('id'));
   $this.data('ImageUploadButton', editor);
  } else {
   editor.remove();
   editor = createImageUploadButton($this.attr('id'));
   $this.data('ImageUploadButton', editor);
  }
  return editor;
 }

 function setImageUploadButtonValue(that) {
  var editor = getImageUploadButton(that);
  var url = $(that).prop('value');
  var img = $("#" + $(that).attr('data-img'));
  if (url != '') {
   img.attr('src', url);
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
     var url = data.url;
     var img = $("#" + field.attr('data-img'));
     img.attr('src', url);
     field.val(url);
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
     // bindThumbUrlId
     var bindThumbUrlId = field.attr('data-bindThumbUrlId');
     if (bindThumbUrlId != '') {
      $("#" + bindThumbUrlId).val(data.thumbUrl);
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
 // AppImageWidget{
 function getAppImageWidget(that) {
  var $this = $(that);
  var editor = $this.data('AppImageWidget');
  if (typeof editor == 'undefined') {
   editor = $this.AppImageWidget();
  }
  return editor;
 }

 function setAppImageWidgetValue(that) {
  var $editor = getAppImageWidget(that);
  var dataBindId = $(that).attr('data-bind-id');
  var videoIdField = $("#" + dataBindId);
  generateGuid(videoIdField, function() {
   $(that).AppImageWidget('load');
  });
 }

 function getAppImageWidgetValue(that) {

 }
 // AppImageWidget}
 // TemplateWidget{
 function getTemplateWidget(that) {
  var $this = $(that);
  var editor = $this.data('TemplateWidget');
  if (typeof editor == 'undefined') {
   editor = $this.TemplateWidget();
  }
  return editor;
 }

 function setTemplateWidgetValue(that, data) {
  var $editor = getTemplateWidget(that);
  // var dataBindId = $(that).attr('data-bind-id');
  // var videoIdField = $("#" + dataBindId);
  // generateGuid(videoIdField, function() {
  $(that).TemplateWidget('load', data);
  // });
 }

 function getTemplateWidgetValue(that) {

 }
 // TemplateWidget}
 // EntOwnerChangeDetailWidget{
 function getEntOwnerChangeDetailWidget(that) {
  var $this = $(that);
  var editor = $this.data('EntOwnerChangeDetailWidget');
  if (typeof editor == 'undefined') {
   editor = $this.EntOwnerChangeDetailWidget();
  }
  return editor;
 }

 // EntOwnerChangeDetailWidgetView{
 function getEntOwnerChangeDetailWidgetView(that) {
  var $this = $(that);
  var editor = $this.data('EntOwnerChangeDetailWidgetView');
  if (typeof editor == 'undefined') {
   editor = $this.EntOwnerChangeDetailWidgetView();
  }
  return editor;
 }

 function setEntOwnerChangeDetailWidgetValue(that) {
  var $editor = getEntOwnerChangeDetailWidget(that);
  var dataBindId = $(that).attr('data-bind-id');
  var idField = $("#" + dataBindId);
  generateGuid(idField, function() {
   $(that).EntOwnerChangeDetailWidget('load');
  });
 }

 function setEntOwnerChangeDetailWidgetValueView(that) {
  var $editor = getEntOwnerChangeDetailWidgetView(that);
  var dataBindId = $(that).attr('data-bind-id');
  var idField = $("#" + dataBindId);
  generateGuid(idField, function() {
   $(that).EntOwnerChangeDetailWidgetView('load');
  });
 }

 function getEntOwnerChangeDetailWidgetValue(that) {

 }

 function getEntOwnerChangeDetailWidgetValueView(that) {

 }

 // EntOwnerChangeDetailWidget}
 // FitzDetailListWidget{
 function getFitzDetailListWidget(that) {
  var $this = $(that);
  var editor = $this.data('FitzDetailListWidget');
  if (typeof editor == 'undefined') {
   editor = $this.FitzDetailListWidget();
  }
  return editor;
 }
 function setFitzDetailListWidgetValue(that) {
  var $editor = getFitzDetailListWidget(that);
  var dataBindId = $(that).attr('data-bind-id');
  var idField = $("#" + dataBindId);
  generateGuid(idField, function() {
   $(that).FitzDetailListWidget('load');
  });
 }
 function getFitzDetailListWidgetValue(that) {

 }

 // FitzDetailListWidgetView{
 function getFitzDetailListWidgetView(that) {
  var $this = $(that);
  var editor = $this.data('FitzDetailListWidgetView');
  if (typeof editor == 'undefined') {
   editor = $this.FitzDetailListWidgetView();
  }
  return editor;
 }
 function setFitzDetailListWidgetValueView(that) {
  var $editor = getFitzDetailListWidgetView(that);
  var dataBindId = $(that).attr('data-bind-id');
  var idField = $("#" + dataBindId);
  generateGuid(idField, function() {
   $(that).FitzDetailListWidgetView('load');
  });
 }
 function getFitzDetailListWidgetValueView(that) {

 }

 // 创建查看对话框
 function onCreateViewDialog() {
  // 初始化弹出框
  $(uiViewDialog).dialog({
   buttons : [ {
    text : '关闭',
    iconCls : 'icon-cancel',
    handler : function() {
     onViewDialogClose();
    }
   } ]
  });
 }

 // 关闭查看对话框
 function onViewDialogClose() {
  $(uiViewDialog).dialog('close');
 }

 // 打开查看对话框
 function onViewDialogOpen() {
  $(uiViewDialog).dialog('open');
  onAfterViewDialogOpen();
 }
 // 打开查看对话框以后的回调
 function onAfterViewDialogOpen() {

 }

 // 处理查看
 function onView() {
  var rows = $(uiGrid).datagrid('getSelections');
  if (rows.length > 1) {
   warning('查看只能选择一条记录!');
   return;
  }
  var row = $(uiGrid).datagrid('getSelected');
  if (row == null) {
   warning('请选择要查看的记录!');
   return;
  }
  if (settings.onViewToGet) {
   var idField = settings.uiIdField;
   var idValue = row[idField];
   var getParams = {};
   getParams[idField] = idValue;
   $.post(settings.urlGet, getParams, function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     onViewFormLoad(data.item);
    } else {
     alert(data.message);
    }
   });
  } else {
   onViewFormLoad(row);
  }
 }

 function onViewFormLoad(row) {
  $(uiViewForm).form('load', row);
  // TemplateWidget
  $(".TemplateWidget").each(function() {
   setTemplateWidgetValue(this, row);
  });
  onViewDialogOpen();
  on('didView', row);
 }
 // view}

 // FitzDetailListWidget}
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
  return $(uiGrid).datagrid('getSelections');
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
  $("#" + settings.uiTreeselectTree).tree('expandAll');
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
 // treeselect1{
 function treeselect1ClearCheck(treeselect1_tree) {
  var nodes = $(treeselect1_tree).tree('getChecked');
  for (var i = 0; i < nodes.length; i++) {
   var node = $(treeselect1_tree).tree('find', nodes[i]['id']);
   if (node && node.target) {
    $(treeselect1_tree).tree('uncheck', node.target);
   }
  }
 }
 function treeselect1SetCheckIds(treeselect1_tree, checkIds) {
  treeselect1ClearCheck(treeselect1_tree);
  var ids = checkIds.split(',');
  for (var i = 0; i < ids.length; i++) {
   var node = $(treeselect1_tree).tree('find', ids[i]);
   if (node && node.target) {
    $(treeselect1_tree).tree('check', node.target);
   }
  }
 }
 function treeselect1GetCheckIds(treeselect1_tree) {
  var checkIds = [];
  var nodes = $(treeselect1_tree).tree('getChecked');
  for (var i = 0; i < nodes.length; i++) {
   checkIds.push(nodes[i].id);
  }
  return checkIds.join(',');
 }
 function treeselect1() {
  var rows = getSelections();
  if (rows.length != 1) {
   warning('选择一条记录!');
   return;
  }
  $("#" + settings.uiTreeselect1Tree).tree('expandAll');
  var row = rows[0];
  $("#" + settings.uiTreeselect1Form).form('load', row);
  var params = {};
  params[settings.urlTreeselect1GetOneParamName] = row['id'];
  $.post(settings.urlTreeselect1Get, params, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    $("#" + settings.uiTreeselect1Dialog).dialog({
     buttons : [ {
      text : '保存',
      iconCls : 'icon-save',
      handler : function() {
       treeselect1Save(row);
      }
     }, {
      text : '关闭',
      iconCls : 'icon-cancel',
      handler : function() {
       $("#" + settings.uiTreeselect1Dialog).dialog('close');
      }
     } ]
    });
    $("#" + settings.uiTreeselect1Dialog).dialog('open');
    treeselect1SetCheckIds("#" + settings.uiTreeselect1Tree, data[settings.urlTreeselect1GetCheckIds]);
   } else {
    alert(data.message);
   }
  });
 }
 function treeselect1Save(row) {
  var checkIds = treeselect1GetCheckIds("#" + settings.uiTreeselect1Tree);
  var params = {};
  params[settings.urlTreeselect1SaveOneParamName] = row['id'];
  params[settings.urlTreeselect1SaveManyParamName] = checkIds;
  $.post(settings.urlTreeselect1Save, params, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    toast('保存成功');
   } else {
    alert(data.message);
   }
  });
 }
 // treeselect1}
 // 查看
 function view(rowId, uiDetailForm) {
  var idField = settings.uiIdField;
  var idValue = rowId;
  var getParams = {};
  getParams[idField] = idValue;
  $.post(settings.urlGet, getParams, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    $(uiDetailForm).form('load', data.item);
    on('didView', data.item);
   }
  });
 }

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
  treeselect : treeselect,
  treeselect1SetCheckIds : treeselect1SetCheckIds,
  treeselect1GetCheckIds : treeselect1GetCheckIds,
  treeselect1 : treeselect1,
  edit : edit,
  view : view,
  generateGuid : generateGuid
 }
})();