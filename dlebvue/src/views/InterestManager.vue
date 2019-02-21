<template>
  <div>
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="创建时间:">
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.startTime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择日期"></el-date-picker>
        </el-col>
        <el-col :span="1" style="width: 25px">至</el-col>
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.endTime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择日期"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="品种:">
        <el-select  v-model="searchForm.interestManagerKind" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsInterestManagerKind"
            :key="item.kind_name"
            :label="item.kind_name"
            :value="item.kind_name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户名称:" >
        <el-select  v-model="searchForm.interestManagerCustomerName" placeholder="请选择"  clearable filterable >
          <el-option
            v-for="item in optionsInterestManagerCustomer"
            :key="item.customer_name"
            :label="item.customer_name"
            :value="item.customer_name">
          </el-option>
        </el-select>
      </el-form-item>
<!--
        <el-form-item label="状态:">
          <el-select class="el-select" v-model="searchForm.interestManagerInd" placeholder="请选择" clearable filterable>
            <el-option
              v-for="item in optionsStatus"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
-->
      <el-form-item label="关键字:">
        <el-tooltip effect="light" content="票号" placement="bottom-start">
          <el-input placeholder="关键字" v-model="searchForm.keyword" clearable style="width:200px"></el-input>
        </el-tooltip>
      </el-form-item>
      <el-form-item label="分配金额">
        <el-tooltip effect="light" content="输入待分配使用金额总数" placement="bottom-start">
          <el-input-number placeholder="分配金额总数" v-model="searchForm.useMoney" :min="0"></el-input-number>
        </el-tooltip>
      </el-form-item>
      <el-form-item label="待扣除贴息金额">
        <el-tooltip effect="light" content="待扣除贴息金额" placement="bottom-start">
          <el-input placeholder="待扣除贴息金额" v-model="searchForm.interestMoneyOut" :disabled="true" ></el-input>
        </el-tooltip>
      </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="getData('searchForm')"
                   :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-sold-out" @click="expData">导出</el-button>


      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-circle-plus-outline" @click="dialogFormImpVisible1 = true">导入</el-button>
<!--
<el-button type="primary" icon="el-icon-edit" @click="editData">修改</el-button>
-->
        <el-button type="primary" icon="el-icon-delete" @click="deleteData">删除</el-button>
        <el-button type="primary" icon="el-icon-edit-outline" @click="editAgain" >信息补充</el-button>
        <el-button type="primary" icon="el-icon-rank" @click="useMoneyAvg" >分配金额</el-button>
        <el-button type="primary" icon="el-icon-share" @click="copyAdd" >拆分</el-button>
        <el-button type="primary" icon="el-icon-tickets" @click="interestCompute" >贴息计算</el-button>
        <el-button type="primary" icon="el-icon-document" @click="interestOut">扣除贴息</el-button>
        <el-button type="primary" icon="el-icon-check" @click="setFinish">结算</el-button>




      </div>
    </el-form>


        <!--导入窗体-->
        <el-dialog title="请选择Excel文件导入" :visible.sync="dialogFormImpVisible1">
          <el-upload ref="impFile" action="" :file-list="uploadFile" :show-file-list="true" :limit="2"
                     :on-change="impFile" :auto-upload="false" style="text-align: center">

            <img v-if="uploadFile[0]" src="/static/img/excel.jpg" class="avatar" >
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <a href = "http://doc.dllg56.com/dllgplatform/template/财务贴息导入模板.xls">导入模板下载</a>
            <el-button @click="dialogFormImpVisible1 = false">取 消</el-button>
            <el-button type="primary" @click="selectUpload()">确 定</el-button>
          </div>
        </el-dialog>


    <!--列表-->
    <el-table class="borderNone" ref="myTable" stripe border
              :data="tableList"
              style="width: 1180px!important;font-size:12px!important;"  highlight-current-row
              @selection-change="handleSelectionChange"
              :summary-method="getSummariesCheck"
              show-summary>
      <el-table-column type="selection" align="center" width="50px"></el-table-column>
      <el-table-column prop="interestManagerTicketNo" sortable align="center" width="260px" label="汇票编号"></el-table-column>
      <el-table-column prop="interestManagerCustomerName" sortable align="center" width="110px" label="前手背书人" show-overflow-tooltip></el-table-column>
      <el-table-column prop="interestManagerMoneyAll" sortable align="center" width="105px" label="票面金额" :formatter="moneyFormat"></el-table-column>
      <el-table-column prop="interestManagerMoneyUse" sortable align="center" width="105px" label="使用金额" :formatter="moneyFormat"></el-table-column>
      <el-table-column prop="interestManagerMoneySurplus" sortable align="center" width="105px" label="剩余金额" :formatter="moneyFormat"></el-table-column>
      <el-table-column prop="interestManagerMoney" sortable align="center" width="105px" label="贴息金额" :formatter="moneyFormat"></el-table-column>
      <el-table-column prop="interestManagerDate" sortable align="center" width="100px" label="合同月份"></el-table-column>
      <el-table-column prop="interestManagerKind" sortable align="center" width="80px" label="品种"></el-table-column>
      <el-table-column prop="interestManagerInterest" sortable align="center" width="85px" label="利率"></el-table-column>
      <el-table-column prop="interestManagerFreeDay" sortable align="center" width="100px" label="免息天数"></el-table-column>
      <el-table-column prop="interestManagerFreeDayY" sortable align="center" width="110px" label="免息天数Y"></el-table-column>
      <el-table-column prop="interestManagerMemo" sortable align="center" width="100px" label="备注" show-overflow-tooltip></el-table-column>
      <el-table-column prop="interestManagerDateFinish" sortable align="center" width="100px" label="结算月份" show-overflow-tooltip></el-table-column>
      <el-table-column prop="interestManagerDateStart" sortable align="center" width="100px" label="开票日期" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="interestManagerDateEnd" sortable align="center" width="110px" label="汇票到期日" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="interestManagerDateGet" sortable align="center" width="100px" label="收到日期" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="interestManagerTicketUser" sortable align="center" width="100px" label="出票人" show-overflow-tooltip></el-table-column>
      <el-table-column prop="interestManagerTicketBank" sortable align="center" width="100px" label="付款行" show-overflow-tooltip></el-table-column>
      <el-table-column prop="interestManagerContract" sortable align="center" width="120px" label="合同签订日期" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="interestManagerInd" sortable align="center" width="70px" label="状态" :formatter="statusFormat"></el-table-column>
      <el-table-column prop="interestManagerInd2" sortable align="center" width="70px" label="计算"></el-table-column>
      <el-table-column prop="interestManagerTicketNoFact" sortable align="center" width="280px" label="实际票号" ></el-table-column>

      <el-table-column prop="createTime" align="center" width="180px" label="创建时间"></el-table-column>
      <el-table-column prop="createUserId" align="center" width="120px" label="创建人" show-overflow-tooltip></el-table-column>
      <el-table-column prop="updateTime" align="center" width="180px" label="修改时间"></el-table-column>
      <el-table-column prop="updateUserId" align="center" width="120px" label="修改人" show-overflow-tooltip></el-table-column>
    </el-table>


    <!--待扣除贴息总金额-->
    <el-dialog
      :title="'选择待扣除贴息总金额'"
      :visible.syne="dialogInterestVisible"
      width="75%"
      center :show-close="false">
      <el-form ref="dialogFormInterest" :rules="dialogFormInterestRules" :model="dialogFormInterest"
               label-width="120px" labelPosition="right">


        <!--查询条件-->
        <el-form :inline="true" :model="searchFormK" class="admin-search-form" align="left">
          <el-form-item label="创建时间:">
            <el-col :span="8" style="width: 150px">
              <el-date-picker style="width: 140px" type="date" v-model="searchFormK.startTime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="选择日期"></el-date-picker>
            </el-col>
            <el-col :span="1" style="width: 25px">至</el-col>
            <el-col :span="8" style="width: 150px">
              <el-date-picker style="width: 140px" type="date" v-model="searchFormK.endTime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="选择日期"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="品种:">
            <el-select  v-model="searchFormK.interestManagerKind" placeholder="请选择" clearable filterable>
              <el-option
                v-for="item in optionsInterestManagerKind"
                :key="item.kind_name"
                :label="item.kind_name"
                :value="item.kind_name">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户名称:" >
            <el-select  v-model="searchFormK.interestManagerCustomerName" placeholder="请选择"  clearable filterable >
              <el-option
                v-for="item in optionsInterestManagerCustomer"
                :key="item.customer_name"
                :label="item.customer_name"
                :value="item.customer_name">
              </el-option>
            </el-select>
          </el-form-item>
<!--
        <el-form-item label="状态:">
          <el-select class="el-select" v-model="searchFormK.interestManagerInd" placeholder="请选择" clearable filterable>
            <el-option
              v-for="item in optionsStatus"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
-->
          <el-form-item label="关键字:">
            <el-tooltip effect="light" content="票号" placement="bottom-start">
              <el-input placeholder="关键字" v-model="searchFormK.keyword" style="width:200px"></el-input>
            </el-tooltip>
          </el-form-item>
          <div class="admin-search-btns">
            <el-button type="primary" icon="el-icon-search" @click="getDataK('searchFormK')"
                       :loading="this.isShowLoadingIcon">查询
            </el-button>
          </div>
        </el-form>

        <!--列表-->
        <el-table class="borderNone" ref="myTable" stripe border
                  :data="tableListK"
                  style="width: 1180px!important;font-size:12px!important;"  highlight-current-row
                  @selection-change="handleSelectionChangeInterest"
                  :summary-method="getSummariesInterestCheck"
                  show-summary>
          <el-table-column type="selection" align="center" width="50px"></el-table-column>
          <el-table-column prop="interestManagerTicketNoFact" sortable align="center" width="280px" label="实际票号" ></el-table-column>
          <el-table-column prop="interestManagerCustomerName" sortable align="center" width="110px" label="前手背书人" show-overflow-tooltip></el-table-column>
          <el-table-column prop="interestManagerMoneyAll" sortable align="center" width="105px" label="票面金额" :formatter="moneyFormat"></el-table-column>
          <el-table-column prop="interestManagerMoneyUse" sortable align="center" width="105px" label="使用金额" :formatter="moneyFormat"></el-table-column>
          <el-table-column prop="interestManagerMoney" sortable align="center" width="105px" label="贴息金额" :formatter="moneyFormat"></el-table-column>
          <el-table-column prop="interestManagerDate" sortable align="center" width="100px" label="合同月份"></el-table-column>
          <el-table-column prop="interestManagerKind" sortable align="center" width="100px" label="品种"></el-table-column>
        </el-table>


        <!--分页-->
        <el-pagination
          :total="pageSettingsK.totalK"
          :current-page="pageSettingsK.pageNum"
          :page-size="pageSettingsK.pageSize"
          :page-sizes="pageSettingsK.pageSizesK"
          @size-change="sizeChangeK"
          @current-change="currentChangeK"
          :layout="pageSettingsK.layoutK">
        </el-pagination>


      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmDialogFormInterest()">确定</el-button>
        <el-button @click="closeDialogFormInterest('dialogFormInterest')">取消</el-button>
      </div>
    </el-dialog>



    <!--信息补充-->
    <el-dialog
      :title="'信息补充'"
      :visible.syne="dialogEditAgainVisible"
      width="40%"
      center :show-close="false">
      <el-form ref="dialogFormEditAgain" :rules="dialogFormEditAgainRules" :model="dialogFormEditAgain"
               label-width="120px" labelPosition="right">
<!--
        <el-row>
          <el-col :span="8">
-->
            <el-form-item label="合同月份:" prop="interestManagerDate" >
              <el-select  v-model="dialogFormEditAgain.interestManagerDate" placeholder="请选择" clearable filterable>
                <el-option
                  v-for="item in optionsInterestManagerMonth"
                  :key="item.interest_date"
                  :label="item.interest_date"
                  :value="item.interest_date">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="品种:" prop="interestManagerKind" >
              <el-select  v-model="dialogFormEditAgain.interestManagerKind" placeholder="请选择" clearable filterable>
                <el-option
                  v-for="item in optionsInterestManagerKind"
                  :key="item.kind_name"
                  :label="item.kind_name"
                  :value="item.kind_name">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="合同签订日期:" prop="interestManagerContract" >
              <el-date-picker style="width: 150px" type="date" v-model="dialogFormEditAgain.interestManagerContract"
                              value-format="yyyy-MM-dd"
                              placeholder="合同签订日期"></el-date-picker>
            </el-form-item>
            <el-form-item label="备注:" prop="interestManagerMemo" style="margin-top: 16px">
              <el-input type="text" v-model="dialogFormEditAgain.interestManagerMemo"></el-input>
            </el-form-item>
<!--
          </el-col>
-->

<!--
<el-col :span="16">
        <el-form :inline="true" :model="searchFormAddInfo" class="admin-search-form" align="left">
          <el-form-item label="关键字:">
            <el-tooltip effect="light" content="合同月份" placement="bottom-start">
              <el-input placeholder="关键字" v-model="searchFormAddInfo.keyword"></el-input>
            </el-tooltip>
          </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="getDataAddInfo('searchFormAddInfo')"
                   :loading="this.isShowLoadingIcon">查询
        </el-button>


          <el-table class="borderNone" ref="myTable" stripe border
                    :data="tableListAddInfo"
                    style="width: 1180px!important;font-size:12px!important;"  highlight-current-row
                    @selection-change="handleSelectionChangeAddInfo">
            <el-table-column type="selection" align="center" width="50px"></el-table-column>
            <el-table-column prop="freeDayKind" sortable align="center" width="90px" label="品种"></el-table-column>
            <el-table-column prop="freeDayDate" sortable align="center" width="110px" label="合同月份"></el-table-column>
            <el-table-column prop="freeDay" sortable align="center" width="105px" label="免息天数"></el-table-column>
            <el-table-column prop="freeDayContract" sortable align="center" width="130px" label="合同签订日期" :formatter="dateFormat"></el-table-column>
            <el-table-column prop="freeDayY" sortable align="center" width="115px" label="免息天数Y"></el-table-column>
            <el-table-column prop="freeDayCustomer" sortable align="center" width="230px" label="客户名称"></el-table-column>
          </el-table>

        </el-form>

        </el-col>

</el-row>
-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmDialogFormEditAgain('dialogFormEditAgain')">确定</el-button>
        <el-button @click="closeDialogFormEditAgain('dialogFormEditAgain')">取消</el-button>
      </div>
    </el-dialog>


    <!--结算-->
    <el-dialog
      :title="'结算'"
      :visible.syne="dialogSetFinishVisible"
      width="40%"
      center :show-close="false">
      <el-form ref="dialogFormSetFinish" :rules="dialogFormSetFinishRules" :model="dialogFormSetFinish"
               label-width="120px" labelPosition="right">
        <el-form-item label="结算月份:" prop="interestManagerDateFinish" style="margin-top: 16px">
          <el-input type="text" v-model="dialogFormSetFinish.interestManagerDateFinish"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmDialogFormSetFinish('dialogFormSetFinish')">确定</el-button>
        <el-button @click="closeDialogFormSetFinish('dialogFormSetFinish')">取消</el-button>
      </div>
    </el-dialog>


    <!--贴息计算管理新增修改-->
    <el-dialog
      :title="titleType=='add'?'新增贴息计算管理':'修改贴息计算管理'"
      :visible.syne="dialogVisible"
      width="90%"
      center :show-close="false">
      <el-form ref="dialogForm" :rules="dialogFormRules" :model="dialogForm"
               label-width="120px" labelPosition="right">

        <el-row>
          <el-col :span="8">
            <el-form-item label="汇票编号:" prop="interestManagerTicketNo" >
              <el-input type="text" v-model="dialogForm.interestManagerTicketNo" @change="addNewNo()" :disabled="titleType == 'edit'" clearable style="width:300px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="实际票号:" prop="interestManagerTicketNoFact" >
              <el-input type="text" v-model="dialogForm.interestManagerTicketNoFact" clearable :disabled="true" style="width:300px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="单据状态:" prop="interestManagerInd" >
              <el-input type="text" v-model="dialogForm.interestManagerInd" :disabled="true" style="width:90px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="9">
            <el-form-item label="前手背书人:" prop="interestManagerCustomerName" >
              <el-select  v-model="dialogForm.interestManagerCustomerName" placeholder="请选择" clearable filterable style="width:300px" :disabled="true">
                <el-option
                  v-for="item in optionsInterestManagerCustomer"
                  :key="item.customer_name"
                  :label="item.customer_name"
                  :value="item.customer_name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="合同签订日期:" prop="interestManagerContract" >
              <el-date-picker style="width: 200px" type="date" v-model="dialogForm.interestManagerContract"
                              value-format="yyyy-MM-dd"
                              placeholder="合同签订日期"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="票面金额:" prop="interestManagerMoneyAll" >
              <el-input type="text" v-model="dialogForm.interestManagerMoneyAll" :disabled="titleType == 'edit'" clearable  style="width:200px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="使用金额:" prop="interestManagerMoneyUse" >
              <el-input-number v-model="dialogForm.interestManagerMoneyUse" :min="0" clearable style="width:200px"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="贴息金额:" prop="interestManagerMoney" >
              <el-input type="text" v-model="dialogForm.interestManagerMoney" :disabled="true" clearable  style="width:200px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="开票日期:" prop="interestManagerDateStart" >
              <el-date-picker style="width: 200px" type="date" v-model="dialogForm.interestManagerDateStart"
                              value-format="yyyy-MM-dd"
                              placeholder="开票日期" ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="汇票到期日:" prop="interestManagerDateEnd" >
              <el-date-picker style="width: 200px" type="date" v-model="dialogForm.interestManagerDateEnd"
                              value-format="yyyy-MM-dd"
                              placeholder="汇票到期日"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收到日期:" prop="interestManagerDateGet" >
              <el-date-picker style="width: 200px" type="date" v-model="dialogForm.interestManagerDateGet"
                              value-format="yyyy-MM-dd"
                              placeholder="收到日期"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="出票人:" prop="interestManagerTicketUser" >
              <el-input type="text" v-model="dialogForm.interestManagerTicketUser" clearable style="width:300px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="付款行:" prop="interestManagerTicketBank" >
              <el-input type="text" v-model="dialogForm.interestManagerTicketBank" clearable style="width:300px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="备注:" prop="interestManagerMemo" >
              <el-input type="text" v-model="dialogForm.interestManagerMemo" clearable style="width:200px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="合同月份:" prop="interestManagerDate" >
              <el-input type="text" v-model="dialogForm.interestManagerDate" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="利率:" prop="interestManagerInterest" >
              <el-input type="text" v-model="dialogForm.interestManagerInterest" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="品种:" prop="interestManagerKind" >
              <el-select  v-model="dialogForm.interestManagerKind" placeholder="请选择" clearable filterable>
                <el-option
                  v-for="item in optionsInterestManagerKind"
                  :key="item.kind_name"
                  :label="item.kind_name"
                  :value="item.kind_name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>




      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmDialogForm('dialogForm')">确定</el-button>
        <el-button @click="closeDialogForm('dialogForm')">取消</el-button>
      </div>
    </el-dialog>
    <!--分页-->
    <el-pagination
      :total="pageSettings.total"
      :current-page="pageSettings.pageNum"
      :page-size="pageSettings.pageSize"
      :page-sizes="pageSettings.pageSizes"
      @size-change="sizeChange"
      @current-change="currentChange"
      :layout="pageSettings.layout">
    </el-pagination>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'
  import _ from 'lodash'
  import moment from 'moment'

  export default {
    // 贴息计算
    name: 'InterestManagerList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        dialogFormImpVisible1: false,
        uploadFile: [],

        searchForm: {
          keyword: ''
        },
        searchFormK: {
          keyword: ''
        },
        searchFormAddInfo: {
          keyword: ''
        },
        optionsStatus: [{
          value: 'F',
          label: '完成'
        }, {
          value: '0',
          label: '锁定'
        }, {
          value: 'A',
          label: '新增'
        }, {
          value: 'E',
          label: '已修改'
        }, {
          value: '1',
          label: '已分配'
        }, {
          value: '9',
          label: '已计算'
        }, {
          value: 'K',
          label: '已扣除'
        }, {
          value: 'C',
          label: '已拆分'
        }],
        tableList: [],
        tableListK: [],
        tableListAddInfo: [],
        pageSettings: {
                pageSizes: [10, 20, 50, 100, 500],
              },
        titleType: 'add',
        dialogVisible: false,
        dialogEditAgainVisible: false,
        dialogSetFinishVisible: false,
        dialogInterestVisible: false,
        dataExist: false,
        dialogForm: {},
        dialogFormRules: {
          interestManagerTicketNo: [
            {required: true, message: '请输入票号', trigger: 'blur' }
          ]
        },

        dialogFormExpDataList: {},
        dialogFormInterestRules: {},
        dialogFormMoneyAvg: {},
        dialogFormMoneyOut: {},
        dialogFormMoneyOutMerge: {},
        dialogFormInterestCompute: {},
        dialogFormEditAgain: {},
        dialogFormSetFinish: {},
        dialogFormInterest: {},
        dialogFormEditAgainRules: {
          interestManagerDate: [
            {required: true, message: '请输入合同月份', trigger: 'blur' }
          ]
        },
        dialogFormSetFinishRules: {
          interestManagerDateFinish: [
            {required: true, message: '请输入结算月份', trigger: 'blur' }
          ]
        },
        optionsInterestManagerKind: [],
        optionsInterestManagerCustomer: [],
        optionsInterestManagerMonth: [],
        row: [],
        rowInterest: [],
        rowAddInfo: [],
        moneyAllSum: 0,
        moneyUseSum: 0,
        moneySum: 0
      }
    },
    methods: {

      //获取数据 扣除贴息列表
      getDataK() {
        //扣除贴息列表
        this.isShowLoadingIcon = true;
        api.post('interestManager/listK', this.searchFormK).then(response => {
          if (response.data.code === 1) {
            let listK = response.data.data.list
            for (var i = 0; i < listK.length; i++) {
              if (listK[i].updateUserId === '') {
                listK[i].updateTime = ''
              }
            }
            this.updateTableDataK(response.data.data)
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },

      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('interestManager/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list
            for (var i = 0; i < list.length; i++) {
              if (list[i].updateUserId === '') {
                list[i].updateTime = ''
              }
            }
            this.updateTableData(response.data.data)
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })

        this.searchForm.useMoney = 0;         //待分配使用金额
        this.searchForm.interestMoneyOut = 0; //待扣除贴息金额

        //this.getDataK();
      },




      // 获取当前行数据
      handleSelectionChange(val) {
        this.row = val;
      },
      // 获取当前行数据 贴息扣除
      handleSelectionChangeInterest(val) {
        this.rowInterest = val;
      },
      // 获取当前行数据 信息补充
      handleSelectionChangeAddInfo(val) {
        this.rowAddInfo = val;
        //this.dialogFormEditAgain.interestManagerDate = this.rowAddInfo[0].freeDayDate;
        //this.dialogFormEditAgain.interestManagerKind = this.rowAddInfo[0].freeDayKind;
        //this.dialogFormEditAgain.interestManagerContract = this.rowAddInfo[0].freeDayContract;
        //console.info(this.dialogFormEditAgain);
      },

      //检查主键
      checkKey(keyValue) {
        api.get('interestManager/checkKey/' + keyValue).then(response => {
          if (response.data.code === 1) {
            this.dataExist = response.data.data;
          }else {
            Message.MessageError(response.data.msg);
          }
        })
      },
      // 创建
      addData() {
        this.titleType = 'add';
        this.dialogVisible = true;
        this.dialogForm = {
          interestManagerTicketNo: '',
          interestManagerTicketNoFact: '',
          interestManager_customerName: '',
          interestManagerMoneyAll: '',
          interestManagerMoneyUse: '',
          interestManagerMoneySurplus: '',
          interestManagerMoney: '',
          interestManagerDateStart: '',
          interestManagerDateEnd: '',
          interestManagerDateGet: '',
          interestManagerTicketUser: '',
          interestManagerTicketBank: '',
          interestManagerDate: '',
          interestManagerKind: '',
          interestManagerMemo: '',
          interestManagerInd: ''
        };
      },
      // 修改
      editData() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要修改的单据!')
          return
        }
        if (this.row.length > 1) {
          Message.MessageInfo('一次只能修改一条单据!')
          return
        }
        //判断单据状态
        var b = this.isFinish();
        if (b) {
          return
        }
        this.dialogVisible = true;
        this.dialogForm = _.cloneDeep(this.row[0]);
        this.titleType = 'edit';
      },

      //========================分配使用金额（与扣除贴息套路一致、可以合并）========================
      useMoneyAvg() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要分配的单据!')
          return
        }

        //判断单据状态
        var b = this.isFinish();
        if (b) {
          return
        }

        this.moneyOutMerge('分配金额');

      },



      //========================待扣除贴息金额（与分配使用金额套路一致、可以合并）========================
      interestOut() {
        //判断单据状态
        var b = this.isFinish();
        if (b) {
          return
        }

        //没用待分配贴息金额
        if (this.searchForm.interestMoneyOut == 0) {
          this.getDataK();
          this.dialogInterestVisible = true;
          return;
        }
        //已选择完 待分配贴息金额
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要扣除的单据!')
          return
        }

        this.moneyOutMerge('贴息扣除');

      },

      //======================================================合并 使用金额分配、贴息金额扣除======================================================
      moneyOutMerge(type) {
        this.dialogFormMoneyOutMerge.idArray                      = []; //
        this.dialogFormMoneyOutMerge.idOldArray                   = []; //待扣除贴息单据号
        this.dialogFormMoneyOutMerge.interestManagerMoneyAllArray = [];
        this.dialogFormMoneyOutMerge.interestManagerIndArray      = [];
        this.dialogFormMoneyOutMerge.interestManagerMoneySurplusArray = []; //剩余金额

        var chooseMoneyAll = 0 ;
        var useMoneyAll = 0;
        var tips1 = '';
        var tips2 = '';
        var ind = '';

        if (type == '贴息扣除') {
          useMoneyAll = this.searchForm.interestMoneyOut; //待扣除贴息金额
          tips1 = '选择待扣除单据的总票面金额 < 待扣除贴息总金额!';
          tips2 = '确定扣除贴息金额【'+useMoneyAll+'】吗？';
          this.dialogFormMoneyOutMerge.interestManagerInd2 = '0';
          for (var j = 0; j < this.rowInterest.length; j++) {
            this.dialogFormMoneyOutMerge.idOldArray.push(this.rowInterest[j].interestManagerId);
          }
          if (useMoneyAll <= 0) {
            Message.MessageInfo('待扣除贴息金额错误！');
            return;
          }
        }
        if (type == '分配金额') {
          useMoneyAll = this.searchForm.useMoney; //待分配使用金额
          tips1 = '选择待分配单据的总票面金额 < 待分配总金额!';
          tips2 = '确定分配使用金额【'+useMoneyAll+'】吗？';
          this.dialogFormMoneyOutMerge.interestManagerInd2 = '1';
          if (useMoneyAll <= 0) {
            Message.MessageInfo('待分配金额错误！');
            return;
          }
        }

        this.dialogFormMoneyOutMerge.useMoneyAll = useMoneyAll; //待操作总金额

        for (var i = 0; i < this.row.length; i++) {
          var a = this.row[i].interestManagerMoneyUse;
          if (a == null) {
            a = 0;
          }
          //判断选择数据是否正确，条件 使用金额 = 0
          if ( a != 0 ) {
            Message.MessageError('选择数据错误，存在使用金额 不等于 0 的记录');
            return;
          }

          chooseMoneyAll = chooseMoneyAll + this.row[i].interestManagerMoneyAll;

          this.dialogFormMoneyOutMerge.interestManagerId = this.row[i].interestManagerId;
          this.dialogFormMoneyOutMerge.interestManagerMoneyAll = this.row[i].interestManagerMoneyAll;
          this.dialogFormMoneyOutMerge.interestManagerMoneySurplus = this.row[i].interestManagerMoneySurplus;

          this.dialogFormMoneyOutMerge.idArray.push(this.dialogFormMoneyOutMerge.interestManagerId);
          this.dialogFormMoneyOutMerge.interestManagerMoneyAllArray.push(this.dialogFormMoneyOutMerge.interestManagerMoneyAll);
          this.dialogFormMoneyOutMerge.interestManagerMoneySurplusArray.push(this.dialogFormMoneyOutMerge.interestManagerMoneySurplus);

        }

        if (useMoneyAll > chooseMoneyAll) {
          Message.MessageInfo(tips1)
          return
        }

        this.$confirm(tips2, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          confirmButtonClass: 'el-button--danger'
        }).then(() => {
          api.post('interestManager/moneyAvg', this.dialogFormMoneyOutMerge).then(response => {
            if (response.data.code === 1) {
              Message.MessageSuccess(response.data.data);
              this.getData()
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        }).catch(() => {
        });

        this.searchForm.interestMoneyOut == 0;


      },
      //======================================================合并 使用金额分配、贴息金额扣除======================================================



      //信息补充
      editAgain() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要修改的单据!')
          return
        }

        //判断单据状态
        var b = this.isFinish();
        if (b) {
          return
        }

        this.dialogFormEditAgain = {};
        this.dialogEditAgainVisible = true;
      },

      //结算
      setFinish() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要结算的单据!')
          return
        }

        //判断单据状态
        var b = this.isFinish();
        if (b) {
          return
        }

        this.dialogFormSetFinish = {};
        this.dialogSetFinishVisible = true;
      },

      //拆分
      copyAdd() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要拆分的单据!')
          return
        }
        if (this.row.length > 1) {
          Message.MessageInfo('一次只能拆分一条单据!')
          return
        }

        //判断单据状态
        var b = this.isFinish();
        if (b) {
          return
        }

        this.dialogForm = _.cloneDeep(this.row[0]);

        this.titleType = 'copyAdd';

          this.$confirm('确定拆分该数据吗？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            confirmButtonClass: 'el-button--danger'
          }).then(() => {
            api.post('interestManager/copyAdd', this.dialogForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.data);
                this.getData()
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          }).catch(() => {
          });

      },

      // 删除
      deleteData() {
        if (this.row == null || this.row.length <= 0) {
          Message.MessageInfo('请选择要删除的单据!')
        } else {
          //判断单据状态
          var b = this.isFinish();
          if (b) {
            return
          }

          let ids = []
          for (var i = 0; i < this.row.length; i++) {
            ids.push(this.row[i].interestManagerId)
          }
          this.$confirm('确定删除该数据吗？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            confirmButtonClass: 'el-button--danger'
          }).then(() => {
            api.post('interestManager/deletes', ids).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg)
                this.getData()
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          }).catch(() => {
          });
        }
      },

      //信息补充列表
      getDataAddInfo() {
        api.post('/freeDayDict/list', this.searchFormAddInfo).then(response => {
          if (response.data.code === 1) {
            this.tableListAddInfo = response.data.data.list;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
      },

      // 获取查询条件中的品种、客户
      getQueryInfo() {
        //日期条件
        var d = new Date();
        //this.searchForm.startTime = d;
        //this.searchForm.endTime   = d;

        //信息补充列表
        this.getDataAddInfo();

        // 品种下拉
        api.get('/interestManager/getKindDictSelect').then(response => {
          if (response.data.code === 1) {
            this.optionsInterestManagerKind = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
        // 客户下拉
        api.get('/interestManager/getCustomerDictSelect').then(response => {
          if (response.data.code === 1) {
            this.optionsInterestManagerCustomer = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
        //合同月份下拉
        api.get('/interestManager/getInterestDictSelect').then(response => {
          if (response.data.code === 1) {
            this.optionsInterestManagerMonth = response.data.data;
          } else {
            Message.MessageError(response.data.msg);
          }
        })
      },

      // 确定
      confirmDialogForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('interestManager/save', this.dialogForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.data);
                if (response.data.data == "使用金额 大于 汇票金额！") {
                  return;
                }
                this.dialogVisible = false;
                this.getData()
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        });
      },

      //待扣除贴息金额 确定
      confirmDialogFormInterest() {
        var interestMoneyOutAll = 0;
        for (var i = 0; i < this.rowInterest.length; i++) {
          interestMoneyOutAll = interestMoneyOutAll + this.rowInterest[i].interestManagerMoney;
        }
        this.searchForm.interestMoneyOut = interestMoneyOutAll.toFixed(2);
        this.dialogInterestVisible = false;
       },

      //信息补充 确定
      confirmDialogFormEditAgain() {
        this.dialogFormEditAgain.idArray = [];

        for (var i = 0; i < this.row.length; i++) {
          this.dialogFormEditAgain.interestManagerId = "";
          this.dialogFormEditAgain.interestManagerId = this.row[i].interestManagerId;
          this.dialogFormEditAgain.idArray.push(this.dialogFormEditAgain.interestManagerId);           //id
        }

        api.post('interestManager/editAgain', this.dialogFormEditAgain).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.data)
            this.getData();
          } else {
            Message.MessageError(response.data.msg)
          }
        } )

        this.dialogEditAgainVisible = false;

      },

      //结算 确定
      confirmDialogFormSetFinish() {
        this.dialogFormSetFinish.idArray = [];

        for (var i = 0; i < this.row.length; i++) {
          this.dialogFormSetFinish.interestManagerId = "";
          this.dialogFormSetFinish.interestManagerId = this.row[i].interestManagerId;
          this.dialogFormSetFinish.idArray.push(this.dialogFormSetFinish.interestManagerId);           //id
        }

        api.post('interestManager/setFinish', this.dialogFormSetFinish).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.data)
            this.getData();
          } else {
            Message.MessageError(response.data.msg)
          }
        } )

        this.dialogSetFinishVisible = false;

      },

      //贴息计算
      interestCompute() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要计算的单据!')
          return
        }

        //判断单据状态
        var b = this.isFinish();
        if (b) {
          return
        }

        //判断是否允许计算
        for (var i = 0; i < this.row.length; i++) {
          if (this.row[i].interestManagerInd2 == '0') {
            Message.MessageInfo('选择单据存在 不允许计算 状态，扣除贴息款！')
            return
          }
        }


        this.$confirm('确定计算贴息数据？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          confirmButtonClass: 'el-button--danger'
        }).then(() => {
          this.dialogFormInterestCompute.idArray = [];
          this.dialogFormInterestCompute.interestManagerDateStartArray    = [];
          this.dialogFormInterestCompute.interestManagerDateEndArray      = [];
          this.dialogFormInterestCompute.interestManagerInterestArray     = [];
          this.dialogFormInterestCompute.interestManagerMoneyUseArray     = [];
          this.dialogFormInterestCompute.interestManagerKindArray         = [];
          this.dialogFormInterestCompute.interestManagerCustomerNameArray = [];
          this.dialogFormInterestCompute.interestManagerDateArray         = [];
          this.dialogFormInterestCompute.interestManagerContractArray     = [];
          this.dialogFormInterestCompute.interestManagerIndArray          = [];

          for (var i = 0; i < this.row.length; i++) {
            this.dialogFormInterestCompute.interestManagerId = this.row[i].interestManagerId;
            this.dialogFormInterestCompute.interestManagerDateStart = this.row[i].interestManagerDateStart;
            this.dialogFormInterestCompute.interestManagerDateEnd = this.row[i].interestManagerDateEnd;
            this.dialogFormInterestCompute.interestManagerInterest = this.row[i].interestManagerInterest;
            this.dialogFormInterestCompute.interestManagerMoneyUse = this.row[i].interestManagerMoneyUse;

            this.dialogFormInterestCompute.interestManagerKind = this.row[i].interestManagerKind;
            this.dialogFormInterestCompute.interestManagerCustomerName = this.row[i].interestManagerCustomerName;
            this.dialogFormInterestCompute.interestManagerDate = this.row[i].interestManagerDate;
            this.dialogFormInterestCompute.interestManagerContract = this.row[i].interestManagerContract;
            this.dialogFormInterestCompute.interestManagerInd = this.row[i].interestManagerInd;

            this.dialogFormInterestCompute.idArray.push(this.dialogFormInterestCompute.interestManagerId);
            this.dialogFormInterestCompute.interestManagerDateStartArray.push(this.dialogFormInterestCompute.interestManagerDateStart);
            this.dialogFormInterestCompute.interestManagerDateEndArray.push(this.dialogFormInterestCompute.interestManagerDateEnd);
            this.dialogFormInterestCompute.interestManagerInterestArray.push(this.dialogFormInterestCompute.interestManagerInterest);
            this.dialogFormInterestCompute.interestManagerMoneyUseArray.push(this.dialogFormInterestCompute.interestManagerMoneyUse);
            this.dialogFormInterestCompute.interestManagerKindArray.push(this.dialogFormInterestCompute.interestManagerKind);
            this.dialogFormInterestCompute.interestManagerCustomerNameArray.push(this.dialogFormInterestCompute.interestManagerCustomerName);
            this.dialogFormInterestCompute.interestManagerDateArray.push(this.dialogFormInterestCompute.interestManagerDate);
            this.dialogFormInterestCompute.interestManagerContractArray.push(this.dialogFormInterestCompute.interestManagerContract);

            this.dialogFormInterestCompute.interestManagerIndArray.push(this.dialogFormInterestCompute.interestManagerInd);

          }


            api.post('interestManager/interestCompute', this.dialogFormInterestCompute).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.data);
                this.getData();
              } else {
                Message.MessageError(response.data.msg)
              }
            } )



        }).catch(() => {
        });

      },

      // 取消
      closeDialogForm(formName) {
        this.dialogVisible = false;
        this.$refs[formName].resetFields();
      },

      // 待扣除贴息金额 取消
      closeDialogFormInterest(formName) {
        this.dialogInterestVisible = false;
        this.$refs[formName].resetFields();
      },

      // 信息补充 取消
      closeDialogFormEditAgain(formName) {
        this.dialogEditAgainVisible = false;
        this.$refs[formName].resetFields();
      },

      // 信息补充 取消
      closeDialogFormSetFinish(formName) {
        this.dialogSetFinishVisible = false;
        this.$refs[formName].resetFields();
      },

      //判断是否为 完成 状态
      isFinish() {
        for (var i = 0; i < this.row.length; i++) {
          if (this.row[i].interestManagerInd == 'F') {
            Message.MessageInfo('选择单据存在 完成 状态!')
            return true
          }
        }
        return false
      },


      //时间格式化
      dateFormat:function(row, column) {
        var date = row[column.property];
        if (date == undefined) {
        return "";
        }
        return moment(date).format("YYYY-MM-DD");
       },


      //money格式化
      moneyFormat:function(row, column){
        //var reg=/\d{1,3}(?=(\d{3})+$)/g;
        var reg=/(\d{1,3})(?=(\d{3})+$)/g;

        var data = row[column.property];
        if (column.property == 'interestManagerMoneyAll' || column.property == 'interestManagerMoneyUse' || column.property == 'interestManagerMoney' || column.property == 'interestManagerMoneySurplus') {
          if (data == null) {
            return ''
          }
          if (data.toString().indexOf(".") > 0) {
            var data1 = data.toString().substr(data.toString().indexOf("."));
            data = data.toString().substr(0, data.toString().indexOf("."));
          } else {
            var data1 = '';
            data = data;
          }
          return (data + '').replace(reg, '$&,') + data1;
        }
       },

       //状态格式化
       statusFormat:function(val) {
        var rlt = '';
        switch (val.interestManagerInd){
          case 'F':
            rlt = '完成';
            break;
          case '0':
            rlt = '锁定';
            break;
          case 'A':
            rlt = '新增';
            break;
          case 'E':
            rlt = '已修改';
            break;
          case '1':
            rlt = '已分配';
            break;
          case '9':
            rlt = '已计算';
            break;
          case 'K':
            rlt = '已扣除'; //票面金额 = 使用金额，状态 = 0（锁定），票面金额 > 使用金额，状态 = 1（已分配）
            break;
          case 'C':
            rlt = '已拆分'; //拆分后新记录 状态 = 1（已分配）
            break;
          default:
          rlt = '未知';
        }
        return rlt;
       },

      //实际票号 新增时
       addNewNo() {
        this.dialogForm.interestManagerTicketNoFact = this.dialogForm.interestManagerTicketNo + "-01";
       },

       //列表合计 选择项
       getSummariesCheck(param) {
         var reg=/\d{1,3}(?=(\d{3})+$)/g;

         const {columns, data} = param
         const sums = []

           var rows = this.row

         var moneyAllSum = 0
         var moneyUseSum = 0
         var moneySurplusSum = 0
         var moneySum    = 0

         var moneyAllIndex = 0
         var moneyUseIndex = 0
         var moneySurplusIndex = 0
         var moneyIndex    = 0

         if (columns.length < 0) {
           return
         }

         sums[0] = `合计`

         if (rows.length == 0) {
           sums[1] = 0
         }

         for (var i = 0; i < rows.length; i++) {
           columns.forEach( (column, index) => {
             if (index === 1) {
               sums[index] = rows.length;
             }
             if (column.label == '票面金额') {
               moneyAllSum = moneyAllSum + rows[i].interestManagerMoneyAll
               moneyAllIndex = index
             }
             if (column.label == '使用金额') {
               moneyUseSum = moneyUseSum + rows[i].interestManagerMoneyUse
               moneyUseIndex = index
             }
             if (column.label == '剩余金额') {
               moneySurplusSum = moneySurplusSum + rows[i].interestManagerMoneySurplus
               moneySurplusIndex = index
             }
             if (column.label == '贴息金额') {
               moneySum = moneySum + rows[i].interestManagerMoney
               moneyIndex = index
             }

           } )

         }

        // 票面 金额合计
          moneyAllSum = moneyAllSum.toFixed(2);
          if (moneyAllSum.toString().indexOf(".") > 0) {
            var data1 = moneyAllSum.toString().substr(moneyAllSum.toString().indexOf("."));
            moneyAllSum = moneyAllSum.toString().substr(0, moneyAllSum.toString().indexOf("."));
          } else {
            var data1 = '';
            moneyAllSum = moneyAllSum;
          }
         moneyAllSum = ( moneyAllSum + '' ).replace(reg, '$&,') + data1;
         sums[moneyAllIndex] = moneyAllSum;

        // 使用 金额合计
          moneyUseSum = moneyUseSum.toFixed(2);
          if (moneyUseSum.toString().indexOf(".") > 0) {
            var data1 = moneyUseSum.toString().substr(moneyUseSum.toString().indexOf("."));
            moneyUseSum = moneyUseSum.toString().substr(0, moneyUseSum.toString().indexOf("."));
          } else {
            var data1 = '';
            moneyUseSum = moneyUseSum;
          }
         moneyUseSum = ( moneyUseSum + '' ).replace(reg, '$&,') + data1;
         sums[moneyUseIndex] = moneyUseSum;

        // 贴息 金额合计
          moneySum = moneySum.toFixed(2);
          if (moneySum.toString().indexOf(".") > 0) {
            var data1 = moneySum.toString().substr(moneySum.toString().indexOf("."));
            moneySum = moneySum.toString().substr(0, moneySum.toString().indexOf("."));
          } else {
            var data1 = '';
            moneySum = moneySum;
          }
         moneySum = ( moneySum + '' ).replace(reg, '$&,') + data1;
         sums[moneyIndex] = moneySum;

        // 剩余 金额合计
          moneySurplusSum = moneySurplusSum.toFixed(2);
          if (moneySurplusSum.toString().indexOf(".") > 0) {
            var data1 = moneySurplusSum.toString().substr(moneySurplusSum.toString().indexOf("."));
            moneySurplusSum = moneySurplusSum.toString().substr(0, moneySurplusSum.toString().indexOf("."));
          } else {
            var data1 = '';
            moneySurplusSum = moneySurplusSum;
          }
         moneySurplusSum = ( moneySurplusSum + '' ).replace(reg, '$&,') + data1;
         sums[moneySurplusIndex] = moneySurplusSum;



         if (sums.length == 2 ) {
           sums[0] = `合计`
         }

         return sums
       },

       //列表合计 选择项 选择扣除贴息金额
       getSummariesInterestCheck(param) {
         var reg=/\d{1,3}(?=(\d{3})+$)/g;

         const {columns, data} = param
         const sums = []

           var rows = this.rowInterest

         var moneyAllSum = 0
         var moneyUseSum = 0
         var moneySum    = 0

         var moneyAllIndex = 0
         var moneyUseIndex = 0
         var moneyIndex    = 0

         if (columns.length < 0) {
           return
         }

         sums[0] = `合计`

         if (rows.length == 0) {
           sums[1] = 0
         }

         for (var i = 0; i < rows.length; i++) {
           columns.forEach( (column, index) => {
             if (index === 1) {
               sums[index] = rows.length;
             }
             if (column.label == '票面金额') {
               moneyAllSum = moneyAllSum + rows[i].interestManagerMoneyAll
               moneyAllIndex = index
             }
             if (column.label == '使用金额') {
               moneyUseSum = moneyUseSum + rows[i].interestManagerMoneyUse
               moneyUseIndex = index
             }
             if (column.label == '贴息金额') {
               moneySum = moneySum + rows[i].interestManagerMoney
               moneyIndex = index
             }

           } )

         }

          moneyAllSum = moneyAllSum.toFixed(2);
          if (moneyAllSum.toString().indexOf(".") > 0) {
            var data1 = moneyAllSum.toString().substr(moneyAllSum.toString().indexOf("."));
            moneyAllSum = moneyAllSum.toString().substr(0, moneyAllSum.toString().indexOf("."));
          } else {
            var data1 = '';
            moneyAllSum = moneyAllSum;
          }
         moneyAllSum = ( moneyAllSum + '' ).replace(reg, '$&,') + data1;
         sums[moneyAllIndex] = moneyAllSum;

          moneyUseSum = moneyUseSum.toFixed(2);
          if (moneyUseSum.toString().indexOf(".") > 0) {
            var data1 = moneyUseSum.toString().substr(moneyUseSum.toString().indexOf("."));
            moneyUseSum = moneyUseSum.toString().substr(0, moneyUseSum.toString().indexOf("."));
          } else {
            var data1 = '';
            moneyUseSum = moneyUseSum;
          }
         moneyUseSum = ( moneyUseSum + '' ).replace(reg, '$&,') + data1;
         sums[moneyUseIndex] = moneyUseSum;

          moneySum = moneySum.toFixed(2);
          if (moneySum.toString().indexOf(".") > 0) {
            var data1 = moneySum.toString().substr(moneySum.toString().indexOf("."));
            moneySum = moneySum.toString().substr(0, moneySum.toString().indexOf("."));
          } else {
            var data1 = '';
            moneySum = moneySum;
          }
         moneySum = ( moneySum + '' ).replace(reg, '$&,') + data1;
         sums[moneyIndex] = moneySum;

         if (sums.length == 1 ) {
           sums[0] = `合计`
         }

         return sums
       },

/*
       // 列表合计
       getSummaries(param) {
         var reg=/\d{1,3}(?=(\d{3})+$)/g;
         var all = 0;
         const {columns, data} = param
         const sums = []
         columns.forEach((column, index) => {
             if (index === 0) {
               sums[index] = `合计`
               return
             }
             if (index === 1) {
               sums[index] = `${this.tableList.length}`
               return
             }
             const values = data.map(item => item[column.property])
             if (values.every(value => typeof value === 'number') || column.label == '贴息金额' || column.label == '使用金额' ) {
               sums[index] = values.reduce((prev, curr) => {
                 if (typeof curr === 'number') {
                   if (curr == null) {curr = 0}
                   all = prev + curr
                   return all;
                   //return ( all + '' ).replace(reg, '$&,');
                   //return prev + curr
                 } else {
                   return ( prev + '').replace(reg, '$&,');
                   //return prev
                 }
               }, 0)
             }


         })
         return sums
       },
*/

       //导出EXCEL
       expData() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要导出的单据!')
          return
        }

        this.dialogFormExpDataList.idArray = [];
        this.dialogFormExpDataList.interestManagerDateGetArray      = []; //收到日期
        this.dialogFormExpDataList.interestManagerTicketNoArray     = []; //汇票编号
        this.dialogFormExpDataList.interestManagerCustomerNameArray = []; //前手背书人
        this.dialogFormExpDataList.interestManagerMoneyAllArray     = []; //金额
        this.dialogFormExpDataList.interestManagerDateStartArray    = []; //开票日期
        this.dialogFormExpDataList.interestManagerDateEndArray      = []; //汇票到期日
        this.dialogFormExpDataList.interestManagerTicketUserArray   = []; //出票人
        this.dialogFormExpDataList.interestManagerTicketBankArray   = []; //付款行
        this.dialogFormExpDataList.interestManagerFreeDayArray      = []; //贴息减免天数
        this.dialogFormExpDataList.interestManagerInterestArray     = []; //贴息率
        this.dialogFormExpDataList.interestManagerMoneyArray        = []; //贴息金额
        this.dialogFormExpDataList.interestManagerMemoArray         = []; //备注

        for (var i = 0; i < this.row.length; i++) {
          this.dialogFormExpDataList.idArray.push(this.row[i].interestManagerId);
          this.dialogFormExpDataList.interestManagerDateGetArray.push(this.row[i].interestManagerDateGet);
          this.dialogFormExpDataList.interestManagerTicketNoArray.push(this.row[i].interestManagerTicketNo);
          this.dialogFormExpDataList.interestManagerCustomerNameArray.push(this.row[i].interestManagerCustomerName);
          this.dialogFormExpDataList.interestManagerMoneyAllArray.push(this.row[i].interestManagerMoneyAll);
          this.dialogFormExpDataList.interestManagerDateStartArray.push(this.row[i].interestManagerDateStart);
          this.dialogFormExpDataList.interestManagerDateEndArray.push(this.row[i].interestManagerDateEnd);
          this.dialogFormExpDataList.interestManagerTicketUserArray.push(this.row[i].interestManagerTicketUser);
          this.dialogFormExpDataList.interestManagerTicketBankArray.push(this.row[i].interestManagerTicketBank);
          this.dialogFormExpDataList.interestManagerFreeDayArray.push(this.row[i].interestManagerFreeDay);
          this.dialogFormExpDataList.interestManagerInterestArray.push(this.row[i].interestManagerInterest);
          this.dialogFormExpDataList.interestManagerMoneyArray.push(this.row[i].interestManagerMoney);
          this.dialogFormExpDataList.interestManagerMemoArray.push(this.row[i].interestManagerMemo);
        }




        api.put('interestManager/exportForEXCEL', this.dialogFormExpDataList, {responseType: "blob"}).then(response => {
              var blob = new Blob([response.data], {
                  type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
              });
              var fileName = "财务贴息信息列表.xls";
              var a = document.createElement('a');
              document.body.appendChild(a);
              a.download = fileName;
              a.href = URL.createObjectURL(blob);
              a.click();
        })

/*
				//生成打印格式
				$http.put(_api("/financial/outStanding/exportMainForEXCEL"), $scope.printMainList, {responseType: "blob"}).then(function (response) {
                    var blob = new Blob([response.data], {
                        type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
                    });
                    var fileName = "挂帐单主信息列表.xls";
                    var a = document.createElement('a');
                    document.body.appendChild(a);
                    a.download = fileName;
                    a.href = URL.createObjectURL(blob);
                    a.click();
				});
*/

       },

      //=====================================================
      //选择上传文件
      selectUpload() {
        if (this.uploadFile.length !== 1) {  Message.MessageError('请选择文件') }
        //构建上传
        var formData = new FormData();
        formData.append('impFile', this.uploadFile[0].raw);
        api.post('interestManager/imp', formData).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.data)
            this.dialogFormImpVisible1 = false;
            this.getData();
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },

      //选择文件,准备上传
      impFile(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传文件大小不能超过 2MB!');
        }

        var filename = file.name;
        var index = filename.lastIndexOf(".");
        var ext = filename.substr(index+1);

        if (ext != "xls") {
          this.$message.error('上传文件后缀必须为 .xls 文件!');
        }
        this.uploadFile.pop();
        this.uploadFile.push(file);
      },
      showDetail(row) {
        this.$router.push(`/InvoiceManageDetail/${row.djh}`)
      },

       //Y值是否计算格式化
       indYFormat:function(val) {
        if( val.freeDayYInd == "1" ){
          return "计算";
        }else {
          return "否";
        }
       },




     },

    mounted() {
      this.getQueryInfo();
      this.getData();
    }
  }
</script>

<style scoped>

</style>
