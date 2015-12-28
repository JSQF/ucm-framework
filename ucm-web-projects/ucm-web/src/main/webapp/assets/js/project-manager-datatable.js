var ProjectManagerDataTable = {
    projectManagerDataTable : null,

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
                    var updateBtn = '<a href="javascript:;" class="updateProject" data_id="' + row.id + '">修改</a>';
                    var viewVersionBtn = '<a href="javascript:;" class="showProjectVersionConig" data_id="' + row.id + '">查看配置版本</a>';
                    return updateBtn + "&nbsp;" + viewVersionBtn;
                }
            }]
        });

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