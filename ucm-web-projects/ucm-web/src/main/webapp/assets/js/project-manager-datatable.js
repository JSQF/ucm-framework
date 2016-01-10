var ProjectManagerDataTable = {
    projectManagerDataTable : null,

    //---------------------------
    // 更新项目
    //---------------------------
    showUpdateProjectBtn: null,
    updateProjectModal: null,
    closeUpdateProjectModalBtn: null,
    checkUpdateProjectBtn : null,



    reload : function(){
        this.projectManagerDataTable.fnDraw();
    },

    handlerDataTable: function(){
        this.projectManagerDataTable = $("#project_manager").dataTable({
            "processing": false,
            "serverSide": true,
            "sort": false,
            "paging": true,
            "searching": false,
            "autoWidth": true,
            "ajax": {
                url: "/project/list-project.json",
                data: function (data) {
                    data.projectCode = $("#searchProjectCode").val();
                    data.projectType = $("#searchProjectType").val();
                },
                type: "POST"
            },
            "columns": [{
                "mData": "code",
                "title": "项目代码",
                "width": '10%'
            }, {
                "mData": "name",
                "title": "项目名称",
                "width": '20%'
            }, {
                "mData": "type",
                "title": "项目类型",
                "width": '20%'
            }, {
                "title": "操作",
                "render": function (data, type, row) {
                    var updateBtn = '<a href="javascript:;" class="btn purple btn-xs showUpdateProjectBtn" data_id="' + row.id + '">修改</a>';
                    var viewVersionBtn = '<a href="javascript:;" class="btn purple btn-xs showProjectVersionConig" data_id="' + row.id + '">查看配置版本</a>';
                    return updateBtn + "&nbsp;" + viewVersionBtn;
                }
            }],
            "lengthMenu": [ 1, 25, 50, 75, 100 ],
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_条",
                "sZeroRecords": "没有找到符合条件的数据",
                "sProcessing": "<img src='/assets/wait.gif' />",
                "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                "sInfoEmpty": "木有记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                "sSearch": "搜索：",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                }
            }
        });

        this.projectManagerDataTable.on('draw.dt', function(){
            if(ProjectManagerDataTable.showUpdateProjectBtn != null) {
                ProjectManagerDataTable.showUpdateProjectBtn.off('click');
            }

            ProjectManagerDataTable.showUpdateProjectBtn = $('.showUpdateProjectBtn');
            ProjectManagerDataTable.showUpdateProjectBtn.on('click', function(){

            })
        })


        $(".updateProject").on("click", function(){
            $.ajax({
                "url":"/project/show-update.json",
                "type":"POST",
                "dateType":"json",
                "data":{
                    "projectId": $(this).attr("data_id")
                },
                success: function(result){
                    if (result.code == 200) {

                    }else {

                    }
                },
                error: function(){

                }
            })
        });
    },

    init: function(){
        this.handlerDataTable();
    }
}