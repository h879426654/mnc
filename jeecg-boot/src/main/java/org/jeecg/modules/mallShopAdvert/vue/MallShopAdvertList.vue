<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="商家ID">
              <a-input placeholder="请输入商家ID" v-model="queryParam.advertId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="用户ID">
              <a-input placeholder="请输入用户ID" v-model="queryParam.customerId"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="商家名称">
              <a-input placeholder="请输入商家名称" v-model="queryParam.advertName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="商家介绍">
              <a-input placeholder="请输入商家介绍" v-model="queryParam.advertContext"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="商家封面图片">
              <a-input placeholder="请输入商家封面图片" v-model="queryParam.advertImage"></a-input>
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
    <mallShopAdvert-modal ref="modalForm" @ok="modalFormOk"></mallShopAdvert-modal>
  </a-card>
</template>

<script>
  import MallShopAdvertModal from './modules/MallShopAdvertModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "MallShopAdvertList",
    mixins:[JeecgListMixin],
    components: {
      MallShopAdvertModal
    },
    data () {
      return {
        description: '商家表管理页面',
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
            title: '商家ID',
            align:"center",
            dataIndex: 'advertId'
           },
		   {
            title: '用户ID',
            align:"center",
            dataIndex: 'customerId'
           },
		   {
            title: '商家名称',
            align:"center",
            dataIndex: 'advertName'
           },
		   {
            title: '商家介绍',
            align:"center",
            dataIndex: 'advertContext'
           },
		   {
            title: '商家封面图片',
            align:"center",
            dataIndex: 'advertImage'
           },
		   {
            title: '店铺营业执照',
            align:"center",
            dataIndex: 'shopLicence'
           },
		   {
            title: '视频地址',
            align:"center",
            dataIndex: 'shopVideo'
           },
		   {
            title: '联系方式',
            align:"center",
            dataIndex: 'advertPhone'
           },
		   {
            title: '地址',
            align:"center",
            dataIndex: 'advertAddress'
           },
		   {
            title: '经度',
            align:"center",
            dataIndex: 'advertLongitude'
           },
		   {
            title: '纬度',
            align:"center",
            dataIndex: 'advertLatitude'
           },
		   {
            title: '审核状态(1.待审核 2.审核通过， 3.审核拒绝)',
            align:"center",
            dataIndex: 'applyStatus'
           },
		   {
            title: '审核原因',
            align:"center",
            dataIndex: 'applyContext'
           },
		   {
            title: '是否删除（1是 0否）',
            align:"center",
            dataIndex: 'flagDel'
           },
		   {
            title: '销量',
            align:"center",
            dataIndex: 'advertSale'
           },
		   {
            title: '市',
            align:"center",
            dataIndex: 'city'
           },
		   {
            title: '区',
            align:"center",
            dataIndex: 'region'
           },
		   {
            title: '商家类型ID',
            align:"center",
            dataIndex: 'classifyId'
           },
		   {
            title: '负责人',
            align:"center",
            dataIndex: 'person'
           },
		   {
            title: '0:非热门，1热门',
            align:"center",
            dataIndex: 'hot'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/mallShopAdvert/mallShopAdvert/list",
          delete: "/mallShopAdvert/mallShopAdvert/delete",
          deleteBatch: "/mallShopAdvert/mallShopAdvert/deleteBatch",
          exportXlsUrl: "mallShopAdvert/mallShopAdvert/exportXls",
          importExcelUrl: "mallShopAdvert/mallShopAdvert/importExcel",
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