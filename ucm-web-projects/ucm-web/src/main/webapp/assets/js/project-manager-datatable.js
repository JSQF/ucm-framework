var ProjectManagerDataTable = {
    projectManagerDataTable : null,

    //---------------------------
    // 更新项目
    //---------------------------
    showUpdateProjectBtn: null,
    updateProjectFormModal: null,
    checkUpdateProjectFormBtn: null,




    reload : function(){
        this.projectManagerDataTable.fnDraw();
    },

    initUpdateModal: function(){
        ProjectManagerDataTable.showUpdateProjectBtn = $(".showUpdateProjeexcexcedfsctBtn");
        ProjectManagerDataTable.updateProjectFormModal = $("#updateProjectFormModal");
        ProjectManagerDataTable.checkUpdateProjectFormBtn = $(".checkUpdateProjectFormBtn");
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
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_条",
                "sZeroRecords": "没有找到符合条件的数据",
                "loadingRecords": "<img src='/assets/wait.gif' />",
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
            if (ProjectManagerDataTable.showUpdateProjectBtn != null) {
                //console.log('off');
                ProjectManagerDataTable.showUpdateProjectBtn.off("click");
            }

            ProjectManagerDataTable.showUpdateProjectBtn = $(".showUpdateProjectBtn");

            ProjectManagerDataTable.showUpdateProjectBtn.on('click', function(){
                var btn = $(this);
                var projectId = btn.attr('data_id');
                $.ajax({
                    url:'/project/get-project-by-id.json',
                    type:'POST',
                    dataType:'json',
                    data: {
                        projectId: projectId
                    },
                    success: function(result) {
                        if (result.code == 200) {
                            $("#updateProjectId").val(result.data.id);
                            $("#updateProjectCode").val(result.data.code);
                            $("#updateProjectName").val(result.data.name);
                            $("#updateProjectType").val(result.data.type);
                            var radios = $("input[type=radio][name=updateProjectStatus]");
                            radios.parent('span').removeClass('checked');
                            var radio = $("input[type=radio][name=updateProjectStatus][value='" + result.data.active + "']");
                            radio.parent('span').addClass("checked");
                            $("#updateProjectDescription").val(result.data.description);
                            ProjectManagerDataTable.updateProjectFormModal.modal();
                        }else{
                            MessageBox.setMessage("失败", result.message);
                            MessageBox.show();
                        }
                    },
                    error: function(){
                        MessageBox.showSystemError();
                    }
                });

            });
        });

        ProjectManagerDataTable.checkUpdateProjectFormBtn.on('click', function(){
            var updateProjectName = $("#updateProjectName").val();

            if (updateProjectName == null || updateProjectName == '') {
                MessageBox.setMessage("错误", "项目名称不能为空");
                MessageBox.show();
                return ;
            }
            var updateProjectDescription = $("#updateProjectDescription").val();
            if (updateProjectDescription == null || updateProjectDescription == '') {
                MessageBox.setMessage("错误", "项目描述不能为空");
                MessageBox.show();
                return;
            }
            $("#confirmUpdateProjectModal").modal();
        });
        $(".updateProjectBtn").on('click', function(){
            $(".closeConfirmUpdateProjectModalBtn").click();
            var status = $(".updateProjectStatus").parent('.checked').children().val();
            $.ajax({
                url: '/project/update-project.json',
                type:'POST',
                dataType:'json',
                data:{
                    projectId: $("#updateProjectId").val(),
                    name: $("#updateProjectName").val(),
                    type: $("#updateProjectType").val(),
                    description: $("#updateProjectDescription").val(),
                    status: status
                },
                success: function(result) {
                    if (result.code == 200) {
                        ProjectManagerDataTable.projectManagerDataTable.fnDraw();
                        $(".closeUpdateProjectModalBtn").click();
                        MessageBox.showSuccess("更新成功");
                    }else {
                        MessageBox.showFailed(result.message);
                    }
                },
                error: function () {
                    MessageBox.showSystemError();
                }
            });
        })
    },

    init: function(){
        this.initUpdateModal();
        this.handlerDataTable();
    }
}