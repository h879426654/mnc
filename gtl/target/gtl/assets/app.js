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

// 将表单序列化成filter对象
$.fn.serializeFilter = function() {
 var o = {};
 var filterArray = [];
 // { field: "code", operator: "eq", value: "admin" },
 var a = this.serializeArray();
 $.each(a, function() {
  var filter = {
   field : "",
   operator : "eq",
   value : ""
  };
  var value = {};
  if (o[this.name]) {
   if (!o[this.name].push) {
    value = [ o[this.name] ];
    o[this.name] = value;
   }
   value = this.value || '';
   o[this.name].push(value);
  } else {
   value = this.value || '';
   o[this.name] = value;
  }
  filter.field = this.name;
  filter.value = o[this.name];
  filterArray.push(filter);
 });
 return filterArray;
};

// 简单Crud应用程序.基于kendo ui
var CrudApp = CrudApp || {};

CrudApp = (function() {
 var settings = {
  urlBase : '',
  urlGet : 'get.do',
  urlSave : 'save.do',
  urlSubmit : 'submit.do',
  urlSubmitNext : 'submitNext.do',// 提交下一级审核
  urlApprove : 'approve.do',// 审核通过
  urlReject : 'reject.do', // 审核不通过
  urlState : 'state.do',// 禁用启用(或者其他状态的修改)
  buttonSubmit : false,
  buttonSubmitDisabled : false,
  urlView : 'view.do',
  onViewToGet : false,
  urlDelete : 'del.do',
  uiSearchForm : '#searchForm',
  uiGrid : '#grid',
  uiGridType : 'SwGrid',
  uiAddDialog : '#addDialog',
  uiAddForm : '#addForm',
  uiEditDialog : '#editDialog',
  uiEditForm : '#editForm',
  uiViewDialog : '#viewDialog',
  uiViewForm : '#viewForm',
  uiApproveDialog : '#approveDialog',
  uiApproveForm : '#approveForm',
  uiIdField : 'id',
  uiStateField : 'state',
  ckfinderBasePath : '/ckfinder/',
  ckeditorBasePath : '/ckeditor/',
  contextPath : '/',
  kindeditorUploadJson : 'kindEditorFileUpload.do',
  onReflashReloadWindow : false,
  onEditDialogCloseReloadWindow : false,
  onDeleteDialogCloseReloadWindow : false,
  onSaveDialogCloseReloadWindow : false,
  onSaveDialogCloseWindow : true,
  onViewDialogCloseReloadWindow : false,
  onEditToGet : false,
  onAddToGenId : false,// 添加的时候,生成ID
  uiTreeselectTree : 'treeselect_tree',
  uiTreeselectForm : 'treeselect_form',
  uiTreeselectDialog : 'treeselect_dialog',
  urlTreeselectGet : 'getUserRoles.do',
  urlTreeselectGetOneParamName : 'user',
  urlTreeselectGetCheckIds : 'item',
  urlTreeselectSave : 'saveUsersForRoles.do',
  urlTreeselectSaveOneParamName : 'users',
  urlTreeselectSaveManyParamName : 'roles'
 };

 // log
 function log(message) {
  try {
   // console.log(JSON.stringify(message));
  } catch (e) {
  }
 }
 // 统一样式确认
 function confirm(message, callback) {
  bootbox.confirm(message, function(result) {
   if (result) {
    callback();
   }
  });
 }
 // 统一样式alert
 function alert(message) {
  bootbox.alert(message);
 }
 // 统一样式warning
 function warning(message) {
  toastr.warning(message);
 }
 // 统一样式toast
 function toast(message) {
  toastr.warning(message);
 }
 function success(message) {
  toastr.success(message);
 }
 function error(message) {
  toastr.error(message);
 }
 // 初始化.
 function init(options) {
  $.extend(settings, options);
  // onCreateEditDialog();
  // initEditors();
  bootbox.setLocale('zh_CN');
  kendo.culture("zh-CN");
  // initKendo
  initKendo();
  //ajaxSetup;
  ajaxSetup();
 }
 function initKendo() {
  if ($(settings.uiAddForm).length > 0) {
   initKendoValidator(settings.uiAddForm);
   kendo.bind($(settings.uiAddForm), {});
  }
  if ($(settings.uiEditForm).length > 0) {
   initKendoValidator(settings.uiEditForm);
   kendo.bind($(settings.uiEditForm), {});
  }
  if ($(settings.uiViewForm).length > 0) {
   kendo.bind($(settings.uiViewForm), {});
  }
  if ($(settings.uiApproveForm).length > 0) {
   kendo.bind($(settings.uiApproveForm), {});
  }
  if ($(settings.uiSearchForm).length > 0) {
   kendo.bind($(settings.uiSearchForm), {});
  }
 }

 function initKendoValidator(formId) {
  if (!$(formId).data('kendoValidator')) {
   $(formId).kendoValidator();
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

 // 返回查看的url
 function getUrlView() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlView;
  } else {
   return settings.urlView;
  }
 }
 // 返回提交表单提交的url
 function getUrlSubmit() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlSubmit;
  } else {
   return settings.urlSubmit;
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
  if ('save' == what) {
   onSave();
   return;
  }
  if ('submit' == what) {
   onSubmit();
  }
  if ('approve' == what) {
   onApprove();
   return;
  }
  if ('approvePass' == what) {
   onApprovePass();
   return;
  }
  if ('approveReject' == what) {
   onApproveReject();
   return;
  }
  if ('approveSubmitNext' == what) {
   onApproveSubmitNext();
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
   settings.on(what, row);
  }
  if (window[what]) {
   window[what].call();
  }
 }

 // 返回查询条件
 function getSearchParams() {
  return $(settings.uiSearchForm).serializeObject();
 }

 function getSearchFilters() {
  return $(settings.uiSearchForm).serializeFilter();
 }

 // 刷新列表
 function onReflash() {
  // $(uiGrid).datagrid('load', getSearchParams());
  // $(uiGrid).datagrid('clearSelections');
  onSearch();
 }

 // 清空查询条件
 function onClearSearch() {
  $(settings.uiSearchForm).form('clear');
  onSearch();
 }

 function useSwGrid() {
  return 'SwGrid' == settings.uiGridType;
 }
 // 执行查询
 function onSearch() {
  // $(uiGrid).datagrid('clearSelections');
  // $(uiGrid).datagrid('load', getSearchParams());
  var searchParams = getSearchParams();
  if (useSwGrid()) {
   getGrid().load(searchParams);
   return;
  }
  var searchFilters = getSearchFilters();
  getGrid().dataSource.filter(searchFilters);
  getGrid().dataSource.read();
 }

 function getGrid() {
  var swGrid = $(settings.uiGrid).data("kendoSwGrid");
  if (swGrid) {
   return swGrid;
  }
  var grid = $(settings.uiGrid).data("kendoGrid");
  if (grid) {
   return grid;
  }
  var treeList = $(settings.uiGrid).data("kendoTreeList");
  if (treeList) {
   return treeList;
  }
  var treeView = $(settings.uiGrid).data("kendoTreeView");
  if (treeView) {
   return treeView;
  }
 }

 // 处理添加
 function onAdd() {
  $(settings.uiAddForm).form('clear');
  // 添加的时候,生成主键.
  if (settings.onAddToGenId) {
   $.getJSON("generateGuid.do", function(json) {
    if (json.success) {
     var guid = json.item;
     log(guid);
     var idField = settings.uiIdField;
     var idValue = guid;
     var json = {};
     json[idField] = idValue;
     log(json);
     $(settings.uiAddForm).form('load', json);
     doAdd();
    }
   });
  } else {
   doAdd();
  }

 }
 function doAdd() {
  $(settings.uiAddDialog).modal({
   backdrop : 'static',
   keyboard : false
  });
  on('didAdd');
  didAdd();
 }
 function didAdd() {
  $(settings.uiAddForm).find("[data-role='kinduploadbutton']").each(function() {
   var button = $(this).data('kendoKindUploadButton');
   button.sync();
  });
  $(settings.uiAddForm).find("[data-role='webuploader']").each(function() {
   var button = $(this).data('kendoWebuploader');
   button.sync();
  });
 }

 // 处理编辑
 function onEdit() {
  var rows = getSelections();
  if (rows.length > 1) {
   warning('修改只能选择一条记录!');
   return;
  }
  if (rows.length == 0) {
   warning('请选择要修改的记录!');
   return;
  }
  var row = rows[0];
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
    // 是否允许编辑回调
    if (settings.didFormLoad) {
     settings.didFormLoad(data.item);
    }
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

 function onEditFormLoadExtend(row) {
  $(settings.uiEditForm).find("[data-role='kinduploadbutton']").each(function() {
   var button = $(this).data('kendoKindUploadButton');
   button.sync();
  });
  $(settings.uiEditForm).find("[data-role='webuploader']").each(function() {
   var button = $(this).data('kendoWebuploader');
   button.sync();
  });
 }
 function onEditFormLoad(row) {
  log('onEditorLoad');
  log(row);
  $(settings.uiEditForm).form('load', row);
  onEditFormLoadExtend(row);
  onEditDialogOpen();
  if (settings.didFormLoad) {
   log('settings.didFormLoad begin.');
   settings.didFormLoad(row);
   log('settings.didFormLoad end.');
  }
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
  $(settings.uiEditDialog).modal('hide');
  if (settings.onEditDialogCloseReloadWindow) {
   // 直接刷新页面,释放资源
   window.location.reload();
  } else {
   onReflash();
  }
 }

 // 打开编辑对话框
 function onEditDialogOpen() {
  $(settings.uiEditDialog).modal({
   backdrop : 'static',
   keyboard : false
  });
  onAfterEditDialogOpen();
 }
 // 打开编辑对话框以后的回调
 function onAfterEditDialogOpen() {
  initEditors();
 }
 // 处理保存
 function onAddFormSave() {
  $(settings.uiAddForm).form('submit', {
   url : getUrlSave(),
   onSubmit : function() {
    try {
     onWillAddFormSave();
    } catch (e) {
     alert(e);
    }
    if (!$(this).data("kendoValidator").validate()) {
     warning('请验证您的输入!');
     return false;
    }
    return true;
   },
   success : function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     if (settings.onSaveDialogCloseReloadWindow) {
      // 直接刷新页面,释放资源
      window.location.reload();
     } else {
      if (settings.onSaveDialogCloseWindow) {
       onAddDialogClose();
       success(data.message);
      } else {
       success(data.message);
       // onReflash();
       // 保存以后刷新表单数据.
       try {
        if (data.item) {
         $(settings.uiAddForm).form('load', data.item);
         log('保存以后刷新表单数据成功');
        }
       } catch (e) {
        log('保存以后刷新表单数据失败' + e);
       }
       try {
        // onDidAddFormSave();
       } catch (e) {
        alert(e);
       }
      }
     }
    } else {
     alert(data.message);
    }
   }
  });
 }

 // 关闭编辑对话框
 function onAddDialogClose() {
  $(settings.uiAddDialog).modal('hide');
  onReflash();
 }

 // 打开编辑对话框
 function onAddDialogOpen() {
  $(settings.uiAddDialog).modal({
   backdrop : 'static',
   keyboard : false
  });
  onAfterAddDialogOpen();
 }
 // 打开编辑对话框以后的回调
 function onAfterAddDialogOpen() {
 }

 // 删除选中的记录.
 function onDelete() {
  var rows = getSelections();
  if (rows.length == 0) {
   warning('请选择要删除的记录!');
   return;
  }
  var array = new Array();
  for (var i = 0; i < rows.length; i++) {
   array.push(rows[i][settings.uiIdField]);
  }
  confirm('确认删除?', function() {
   $.ajax({
    type : "POST",
    dataType : "json",
    url : getUrlDelete(),
    data : {
     id : array.join(',')
    },
    success : function(data) {
     success(data.message);
     if (data.success) {
      if (settings.onDeleteDialogCloseReloadWindow) {
       // 直接刷新页面,释放资源
       window.location.reload();
      } else {
       onReflash();
      }
     }
    }
   });
  });
 }

 // 处理保存
 function onSave() {
  $(settings.uiEditForm).form('submit', {
   url : getUrlSave(),
   onSubmit : function() {
    try {
     onWillSave();
    } catch (e) {
     alert(e);
    }
    if (!$(this).data("kendoValidator").validate()) {
     warning('请验证您的输入!');
     return false;
    }
    return true;
   },
   success : function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     if (settings.onSaveDialogCloseReloadWindow) {
      // 直接刷新页面,释放资源
      window.location.reload();
     } else {
      if (settings.onSaveDialogCloseWindow) {
       success(data.message);
       onEditDialogClose()
      } else {
       success(data.message);
       // onReflash();
       // 保存以后刷新表单数据.
       try {
        if (data.item) {
         $(settings.uiEditForm).form('load', data.item);
         log('保存以后刷新表单数据成功');
        }
       } catch (e) {
        log('保存以后刷新表单数据失败' + e);
       }
       try {
        onDidSave();
       } catch (e) {
        alert(e);
       }
      }
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
 function onWillAddFormSave() {
  // kindeditor
  $(".kindeditor").each(function() {
   $(this).data('kendoKindEditor').sync();
  });
 }

 // 表单提交之前处理.
 function onWillSave() {
  // kindeditor
  $(".kindeditor").each(function() {
   $(this).data('kendoKindEditor').sync();
  });
 }
 // 表单提交之后处理。
 function onDidSave() {
  // on('didSave');
  $(settings.editDialog).model('hide');
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
   if (data) {
    $(this).MultipleImageWidget('listImage');
   }
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

 function getGuid(success) {
  $.getJSON("generateGuid.do", function(json) {
   if (json.success) {
    if (success) {
     success.call(json.item);
    }
   }
  });
 }
 // 全局默认配置
 function defaultSettings(options) {
  $.extend(settings, options);
 }
 function getSelections() {
  var grid = getGrid();
  var selects = grid.select();
  if (useSwGrid()) {
   return selects;
  }
  var rows = [];
  for (var i = 0; i < selects.length; i++) {
   var data = grid.dataItem(selects[i]);
   rows.push(data);
  }
  return rows;
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
 // view{

 // 关闭查看对话框
 function onViewDialogClose() {
  $(settings.uiViewDialog).modal('hide');
  if (settings.onViewDialogCloseReloadWindow) {
   // 直接刷新页面,释放资源
   window.location.reload();
  }
 }

 // 打开查看对话框
 function onViewDialogOpen() {
  $(settings.uiViewDialog).modal({
   backdrop : 'static',
   keyboard : false
  });
  onAfterViewDialogOpen();
 }
 // 打开查看对话框以后的回调
 function onAfterViewDialogOpen() {

 }
 // 处理查看
 function onView() {
  var rows = getSelections();
  if (rows.length > 1) {
   warning('查看只能选择一条记录!');
   return;
  }
  if (rows.length == 0) {
   warning('请选择要查看的记录!');
   return;
  }
  var row = rows[0];
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

 function onViewFormLoadExtend(row) {
  $(settings.uiViewForm).find("[data-role='kinduploadbutton']").each(function() {
   var button = $(this).data('kendoKindUploadButton');
   button.sync();
  });
  $(settings.uiViewForm).find("[data-role='webuploader']").each(function() {
   var button = $(this).data('kendoWebuploader');
   button.sync();
  });
 }
 function onViewFormLoad(row) {
  $(settings.uiViewForm).form('load', row);
  onViewFormLoadExtend(row)
  onViewDialogOpen();
  on('didView', row);
  if (settings.didViewFormLoad) {
   settings.didViewFormLoad(row);
  }
 }
 // view}

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

 // 处理审核{
 function getUrlApprove() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlApprove;
  } else {
   return settings.urlApprove;
  }
 }
 function getUrlReject() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlReject;
  } else {
   return settings.urlReject;
  }
 }
 function getUrlSubmitNext() {
  if (settings.urlBase != '') {
   return settings.urlBase + '/' + settings.urlSubmitNext;
  } else {
   return settings.urlSubmitNext;
  }
 }
 function onApprovePass() {
  confirm('确认提交?', function() {
   onApproveToUrl(getUrlApprove());
  });
 }
 function onApproveReject() {
  confirm('确认提交?', function() {
   onApproveToUrl(getUrlReject());
  });
 }
 function onApproveSubmitNext() {
  confirm('确认提交?', function() {
   onApproveToUrl(getUrlSubmitNext());
  });
 }
 function onApproveToUrl(url) {
  $(settings.uiApproveForm).form('submit', {
   url : url,
   onSubmit : function() {
    try {
     onWillSave();
    } catch (e) {
     alert(e);
    }
    // if (!$(this).data("kendoValidator").validate()) {
    // warning('请验证您的输入!');
    // return false;
    // }
    return true;
   },
   success : function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     if (settings.onSaveDialogCloseReloadWindow) {
      // 直接刷新页面,释放资源
      window.location.reload();
     } else {
      if (settings.onSaveDialogCloseWindow) {
       success(data.message);
       onApproveDialogClose()
      } else {
       success(data.message);
       // onReflash();
       // 保存以后刷新表单数据.
       try {
        if (data.item) {
         $(settings.uiApproveForm).form('load', data.item);
         log('保存以后刷新表单数据成功');
        }
       } catch (e) {
        log('保存以后刷新表单数据失败' + e);
       }
       try {
        onDidSave();
       } catch (e) {
        alert(e);
       }
      }
     }
    } else {
     alert(data.message);
    }
   }
  });
 }

 function onApprove() {
  var rows = getSelections();
  if (rows.length > 1) {
   warning('审核只能选择一条记录!');
   return;
  }
  if (rows.length == 0) {
   warning('请选择要审核的记录!');
   return;
  }
  var row = rows[0];
  if (settings.onEditToGet) {
   var idField = settings.uiIdField;
   var idValue = row[idField];
   var getParams = {};
   getParams[idField] = idValue;
   $.post(settings.urlGet, getParams, function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     onApproveFormLoad(data.item);
    } else {
     alert(data.message);
    }
   });
  } else {
   onApproveFormLoad(row);
  }
 }

 function onApproveFormLoad(row) {
  log(row);
  $(settings.uiApproveForm).form('load', row);
  // onApproveFormLoadExtend(row);
  onApproveDialogOpen();
  if (settings.didApproveFormLoad) {
   settings.didApproveFormLoad(row);
  }
  on('didApprove', row);
 }

 // 关闭审核对话框
 function onApproveDialogClose() {
  $(settings.uiApproveDialog).modal('hide');
  if (settings.onEditDialogCloseReloadWindow) {
   // 直接刷新页面,释放资源
   window.location.reload();
  } else {
   onReflash();
  }
 }

 // 打开审核对话框
 function onApproveDialogOpen() {
  $(settings.uiApproveDialog).modal({
   backdrop : 'static',
   keyboard : false
  });
  onAfterApproveDialogOpen();
 }
 // 打开审核对话框以后的回调
 function onAfterApproveDialogOpen() {
  initEditors();
 }
 // 处理审核}

 // {处理状态修改:禁用/启用.
 // state:0 禁用 1 :启用
 // stateName:作为确认的提示
 function onState(state, stateName) {
  var rows = getSelections();
  if (rows.length == 0) {
   warning('请选择要操作的记录!');
   return;
  }
  // 获取ids.
  var ids = new Array();
  for (var i = 0; i < rows.length; i++) {
   var row = rows[i];
   var idField = settings.uiIdField;
   var idValue = row[idField];
   if (state == row[settings.uiStateField]) {
    warning('无效的操作!');
    return;
   }
   ids.push(idValue);
  }
  // 确认操作.
  confirm(stateName, function() {
   var params = {};
   params["ids"] = ids.join(',');
   params["state"] = state;
   doState(params);
  });

 }

 // 执行具体的状态修改操作.
 // params
 // +ids:主键
 // +state:新的状态值.
 function doState(params) {
  $.post(settings.urlState, params, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    onReflash();
   } else {
    alert(data.message);
   }
  });
 }
 // 处理状态修改:禁用/启用}

 function delay(fun) {
  setTimeout(fun, 1000);
 }
 // 解决省市区级联更新需要延时的问题.
 function delayProvinceIdCityIdAreaId(item, option) {
  // FIX:省市区级联加载可能造成清空.
  var provinceIdName = option.provinceId ? option.provinceId : 'provinceId';
  var cityIdName = option.cityId ? option.cityId : 'cityId';
  var areaIdName = option.areaId ? option.areaId : 'areaId';
  log(option);
  log('provinceIdName=' + provinceIdName + ' cityIdName=' + cityIdName + ' areaIdName=' + areaIdName);
  var provinceId = item[provinceIdName];
  var cityId = item[cityIdName];
  var areaId = item[areaIdName];
  log('provinceId=' + provinceId + ' cityId=' + cityId + ' areaId=' + areaId);
  if (provinceId && provinceId != '') {
   log('load province for #provinceId_edit');
   $("#provinceId_edit").val(provinceId);
   $("#provinceId_edit").data('kendoDropDownList').value(provinceId);
  }
  delay(function() {
   if (cityId && cityId != '') {
    log('load city for #cityId_edit');
    $("#cityId_edit").val(cityId);
    $("#cityId_edit").data('kendoDropDownList').value(cityId);
   }
   delay(function() {
    if (areaId && areaId != '') {
     log('load area for #areaId_edit');
     $("#areaId_edit").val(areaId);
     $("#areaId_edit").data('kendoDropDownList').value(areaId);
    }
   });
  });
 }

 function ajaxSetup() {
  $.ajaxSetup({
   type : 'POST',
   error : function(XMLHttpRequest, textStatus, errorThrown) {
    toastr.error('服务器维护中，请稍后再试');
   },
   complete : function(XMLHttpRequest, textStatus) {
    //处理onSessionTimeout{
    var isLoginPage=XMLHttpRequest.responseText.indexOf("loginPage")!=-1;
    if(isLoginPage){
     if(window.onSessionTimeout){
      window.onSessionTimeout();
     }
    }
    //处理onSessionTimeout}
   }
  });
 }

 return {
  ajaxSetup : ajaxSetup,
  init : init,
  initEditors : initEditors,
  on : on,
  defaultSettings : defaultSettings,
  getSelections : getSelections,
  alert : alert,
  toast : toast,
  warning : warning,
  error : error,
  success : success,
  confirm : confirm,
  onReflash : onReflash,
  onSearch : onSearch,
  toEdit : toEdit,
  toEditFormLoadUrl : toEditFormLoadUrl,
  treeselectSetCheckIds : treeselectSetCheckIds,
  treeselectGetCheckIds : treeselectGetCheckIds,
  treeselect : treeselect,
  edit : edit,
  onEditDialogClose : onEditDialogClose,
  onSave : onSave,
  onAddFormSave : onAddFormSave,
  onState : onState,
  view : view,
  generateGuid : generateGuid,
  initKendoValidator : initKendoValidator,
  delay : delay,
  delayProvinceIdCityIdAreaId : delayProvinceIdCityIdAreaId
 }
})();

//格式时间
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
//yyyy-MM-dd hh:mm:ss.S ==> 2006-07-02 08:09:04.423 
Date.prototype.format = function(fmt) {
var o = {
    "M+" : this.getMonth()+1,                 //月份
    "d+" : this.getDate(),                    //日
    "h+" : this.getHours(),                   //小时
    "m+" : this.getMinutes(),                 //分
    "s+" : this.getSeconds(),                 //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S"  : this.getMilliseconds()             //毫秒
};
if(/(y+)/i.test(fmt)) {
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
}
for(var k in o) {
    if(new RegExp("("+ k +")").test(fmt)){
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    }
}
return fmt;
}

//kendo格式化时间
function formatDate(value, record, column, grid, dataNo, columnNo) {
	if(null != value && "" != value) {
		var dateString = new Date(value).format('yyyy-MM-dd hh:mm:ss');
		$('#'+column.id+'Name').html(dateString);
		return dateString;
	}
	return "";
}

//kendo格式化图片
function formatImg(value, record, column, grid, dataNo, columnNo) {
 var content = '';
 content += '<img width="100" src="';
  content += value;
  content += '\">';
 return content;
}

//kendo格式化状态
function formatSatus(value, record, column, grid, dataNo, columnNo) {
	if(1==value){
		return "是";
	}else{
		return "否";
	}
}
function formatCustomerSataus(value, record, column, grid, dataNo, columnNo) {
	if(1==value){
		return "正常";
	}else{
		return "冻结";
	}
}
function formatApplyStatus(value, record, column, grid, dataNo, columnNo) {
    if(1==value){
        return "待审核";
    } else if(2 == value) {
        return "审核通过";
    } else if(3 == value) {
        return "审核拒绝";
    } else {
        return "";
    }
}