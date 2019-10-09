<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="创建人">
              <a-input placeholder="请输入创建人" v-model="queryParam.createUser"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="发布人ACCID">
              <a-input placeholder="请输入发布人ACCID" v-model="queryParam.customerAccid"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="发布人ID">
              <a-input placeholder="请输入发布人ID" v-model="queryParam.customerId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="是否删除（1是 0否）">
              <a-input placeholder="请输入是否删除（1是 0否）" v-model="queryParam.flagDel"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="点赞数">
              <a-input placeholder="请输入点赞数" v-model="queryParam.goodCount"></a-input>
            </a-form-item>
          </a-col>
        </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <cuLooks-modal ref="modalForm" @ok="modalFormOk"></cuLooks-modal>
  </a-card>
</template>

<script>
  import CuLooksModal from './modules/CuLooksModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "CuLooksList",
    mixins:[JeecgListMixin],
    components: {
      CuLooksModal
    },
    data () {
      return {
        description: '看看管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '创建人',
            align:"center",
            dataIndex: 'createUser'
           },
		   {
            title: '发布人ACCID',
            align:"center",
            dataIndex: 'customerAccid'
           },
		   {
            title: '发布人ID',
            align:"center",
            dataIndex: 'customerId'
           },
		   {
            title: '是否删除（1是 0否）',
            align:"center",
            dataIndex: 'flagDel'
           },
		   {
            title: '点赞数',
            align:"center",
            dataIndex: 'goodCount'
           },
		   {
            title: '看看内容',
            align:"center",
            dataIndex: 'looksContext'
           },
		   {
            title: '朋友圈精准定位xy轴',
            align:"center",
            dataIndex: 'looksCoordinate'
           },
		   {
            title: '看看ID',
            align:"center",
            dataIndex: 'looksId'
           },
		   {
            title: '图片地址',
            align:"center",
            dataIndex: 'looksImgs'
           },
		   {
            title: '所处位置',
            align:"center",
            dataIndex: 'looksLocation'
           },
		   {
            title: '视频地址',
            align:"center",
            dataIndex: 'looksVideo'
           },
		   {
            title: '修改时间',
            align:"center",
            dataIndex: 'modifyTime'
           },
		   {
            title: '修改人',
            align:"center",
            dataIndex: 'modifyUser'
           },
		   {
            title: '版本号',
            align:"center",
            dataIndex: 'versionNum'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/cuLooks/cuLooks/list",
          delete: "/cuLooks/cuLooks/delete",
          deleteBatch: "/cuLooks/cuLooks/deleteBatch",
          exportXlsUrl: "cuLooks/cuLooks/exportXls",
          importExcelUrl: "cuLooks/cuLooks/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 3px
  }
  .ant-card-body .table-operator{
    margin-bottom: 18px;
  }
  .ant-table-tbody .ant-table-row td{
    padding-top:15px;
    padding-bottom:15px;
  }
  .anty-row-operator button{margin: 0 5px}
  .ant-btn-danger{background-color: #ffffff}

  .ant-modal-cust-warp{height: 100%}
  .ant-modal-cust-warp .ant-modal-body{height:calc(100% - 110px) !important;overflow-y: auto}
  .ant-modal-cust-warp .ant-modal-content{height:90% !important;overflow-y: hidden}
</style>