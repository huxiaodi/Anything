// 默认每页条数
const pageSize = 10
const pageSizeK = 10

export const pageMixin = {
  data() {
    return {
      tableMaxHeight: '',
      pageSettings: {
        total: 0,
        pageNum: 1,
        pageSize: pageSize,
        pageSizes: [10, 20, 50, 100],
        layout: 'total, sizes, prev, pager, next, jumper'
      },
      pageSettingsK: {
        totalK: 0,
        pageNumK: 1,
        pageSizeK: pageSize,
        pageSizesK: [10, 20, 50, 100],
        layoutK: 'total, sizes, prev, pager, next, jumper'
      },
      searchForm: {
        pageNum: 1,
        pageSize: pageSize,
        startTime: '',
        endTime: ''
      },
      searchFormK: {
        pageNumK: 1,
        pageSizeK: pageSizeK,
        startTimeK: '',
        endTimeK: ''
      },
      qs: require('qs'),
      isShowLoadingIcon: false
    }
  },
  methods: {
    // 设置列表时间查询条件默认为最近15天
    setDate() {
      const start = new Date();
      const end = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 15);
      start.setHours(0);
      start.setMinutes(0);
      start.setSeconds(0);
      start.setMilliseconds(0);
      end.setHours(0);
      end.setMinutes(0);
      end.setSeconds(0);
      end.setMilliseconds(0);
      this.searchForm.startTime = start
      this.searchForm.endTime = end
      this.searchFormK.startTimeK = start
      this.searchFormK.endTimeK = end
    },
    sizeChange(val) {
      this.pageChange(null, val)
    },
    currentChange(val) {
      this.pageChange(val, null)
    },
    pageChange(pageNum, pageSize) {
      if (pageNum) {
        this.searchForm.pageNum = pageNum
      }
      if (pageSize) {
        this.searchForm.pageSize = pageSize
      }
      this.getData()
    },
    updateTableData(result) {
      this.tableList = result.list
      this.pageSettings.total = result.total
    },

    sizeChangeK(valK) {
      this.pageChangeK(null, valK)
    },
    currentChangeK(valK) {
      this.pageChangeK(valK, null)
    },
    pageChangeK(pageNumK, pageSizeK) {
      if (pageNumK) {
        this.searchFormK.pageNum = pageNumK
      }
      if (pageSizeK) {
        this.searchFormK.pageSize = pageSizeK
      }
      this.getDataK()
    },
    updateTableDataK(resultK) {
      this.tableListK = resultK.list
      this.pageSettingsK.totalK = resultK.total
    },
    changeTableMaxHeight() {
      if (this.$refs.myTable) {
        let tmp = 400
        let temp = this.$root.$data.documentClientHeight - this.$refs.myTable.$el.offsetTop - 36 - 45 - 8
        tmp = temp > tmp ? temp : tmp
        this.tableMaxHeight = tmp
      }
    }
  },
  watch: {
    '$root.$data.documentClientHeight'() {
      this.changeTableMaxHeight()
    }
  },
  mounted() {
    setTimeout(() => {
      this.changeTableMaxHeight()
    }, 0)
  }
}
