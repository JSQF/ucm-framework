
var EnvironmentDataTable = {
    environmentDataTable: null,

    //----------------------------------
    // 添加环境
    //----------------------------------
    addEnvironmentModal: null,
    closeAddEnvironmentModalBtn: null,
    checkAddEnvironmentBtn: null,
    resetAddEnvironmentBtn: null,
    confirmAddEnvironmentModal: null,
    closeConfirmAddEnvironmentBtn: null,
    addEnvironmentBtn: null,
    cancelConfirmAddEnvironmentBtn: null,

    //---------------------------------
    // 添加环境IP
    //---------------------------------
    addEnvironmentIpModal: null,
    closeAddEnvironmentIpModalBtn: null,
    checkAddEnvironmentIpBtn: null,
    resetAddEnvironmentIpFormBtn: null,
    confirmAddEnvironmentIpModal: null,
    closeConfirmAddEnvironmentIpModalBtn: null,
    addEnvironmentIpBtn: null,
    cancelAddEnvironmentIpBtn: null,

    //----------------------------------
    // 删除环境IP
    //----------------------------------
    confirmDeleteEnvironmentIpModal: null,
    closeDeleteEnvironmentIpModalBtn: null,
    confirmDeleteEnvironmentIpBtn: null,
    cancelDeleteEnvironmentIpBtn: null,
    deleteEnvironmentIpMessage : null,

    //----------------------------------
    // 修改环境状态
    //----------------------------------
    confirmChangeEnvironmentStatusModal: null,
    changeEnvironmentStatusBtn: null,
    confirmChangeEnvironmentStatusBtn: null,
    cancelChangeEnvironmentStatusBtn: null,
    changeEnvironmentStatusMessage : null,

    reload: function () {
        this.environmentDataTable.fnDraw();
    },

    init: function(){
        this.initModal();
        this.initEnvironmentDataTable();
        this.initEnvironmentIpDataTable();
    },

    initModal: function(){
        this.addEnvironmentModal = $("#addEnvironmentModal");
        this.closeAddEnvironmentModalBtn = $(".closeAddEnvironmentModalBtn");
        this.checkAddEnvironmentBtn = $(".checkAddEnvironmentBtn");
        this.resetAddEnvironmentBtn = $(".resetAddEnvironmentBtn");
        this.confirmAddEnvironmentModal = $("#confirmAddEnvironmentModal");
        this.closeConfirmAddEnvironmentBtn = $(".closeConfirmAddEnvironmentBtn");
        this.addEnvironmentBtn = $(".addEnvironmentBtn");
        this.cancelConfirmAddEnvironmentBtn = $(".cancelConfirmAddEnvironmentBtn");

        this.addEnvironmentIpModal = $("#addEnvironmentIpModal");
        this.closeAddEnvironmentIpModalBtn = $(".closeAddEnvironmentIpModalBtn");
        this.checkAddEnvironmentIpBtn = $(".checkAddEnvironmentIpBtn");
        this.resetAddEnvironmentIpFormBtn = $(".resetAddEnvironmentIpFormBtn");
        this.confirmAddEnvironmentIpModal = $("#confirmAddEnvironmentIpModal");
        this.closeConfirmAddEnvironmentIpModalBtn = $(".closeConfirmAddEnvironmentIpModalBtn");
        this.addEnvironmentIpBtn = $(".addEnvironmentIpBtn");
        this.cancelAddEnvironmentIpBtn = $(".cancelAddEnvironmentIpBtn");

        this.confirmDeleteEnvironmentIpModal = $("#confirmDeleteEnvironmentIpModal");
        this.closeDeleteEnvironmentIpModalBtn = $(".closeDeleteEnvironmentIpModalBtn");
        this.confirmDeleteEnvironmentIpBtn = $(".confirmDeleteEnvironmentIpBtn");
        this.cancelDeleteEnvironmentIpBtn = $(".cancelDeleteEnvironmentIpBtn");
        this.deleteEnvironmentIpMessage = $(".deleteEnvironmentIpMessage");

        this.confirmChangeEnvironmentStatusModal = $("#confirmChangeEnvironmentStatusModal");
        this.changeEnvironmentStatusBtn = $(".changeEnvironmentStatusBtn");
        this.confirmChangeEnvironmentStatusBtn = $(".confirmChangeEnvironmentStatusBtn");
        this.cancelChangeEnvironmentStatusBtn = $(".cancelChangeEnvironmentStatusBtn");
        this.changeEnvironmentStatusMessage = $(".changeEnvironmentStatusMessage");
    },

    initEnvironmentDataTable: function () {
        this.environmentDataTable = $("#environment_manager").dataTable({
            "processing": false,
            "serverSide": true,
            "sort": false,
            "paging": false,
            "searching": false,
            "autoWidth": true,
            "ajax": {
                url: "/env/list-environment.json",
                data: function (data) {
                },
                type: "POST"
            },
            drawCallback: function () {
                var downEnvironmentBtn = $(".downEnvironmentBtn");
                var upEnvironmentBtn = $(".upEnvironmentBtn");
                EnvironmentDataTable.changeEnvironmentStatusBtn.off('click');
                EnvironmentDataTable.changeEnvironmentStatusBtn = $(".changeEnvironmentStatusBtn");
                EnvironmentDataTable.changeEnvironmentStatusBtn.on('click', function () {
                    var changeStatusBtn = $(this);
                    var changeEnvironmentStatusInput = $("#changeEnvironmentStatus");
                    changeEnvironmentStatusInput.val(changeStatusBtn.attr('data_id'));
                    if (changeStatusBtn.text().indexOf('停用') == -1) {
                        EnvironmentDataTable.changeEnvironmentStatusMessage.html("确定启用吗?");
                        changeEnvironmentStatusInput.attr("change_status", '1')
                    } else {
                        EnvironmentDataTable.changeEnvironmentStatusMessage.html("确定停用吗?");
                        changeEnvironmentStatusInput.attr("change_status", '0')
                    }
                    console.log(EnvironmentDataTable.confirmChangeEnvironmentStatusModal);
                    EnvironmentDataTable.confirmChangeEnvironmentStatusModal.modal();
                });
                downEnvironmentBtn.on("click", function () {
                    var order = $(this).attr('data_order');
                    var id = $(this).attr("data_id");
                    $.ajax({
                        url: "/env/down-environment.json",
                        type: 'POST',
                        dataType: "json",
                        data: {
                            environmentId : id,
                            order : order
                        },
                        success: function (result) {
                            if (result.code == 200) {
                                EnvironmentDataTable.reload();
                            } else {
                                MessageBox.setMessage("错误", result.message);
                                MessageBox.show();
                            }
                        },
                        error : function(){
                            MessageBox.showSystemError();

                        }
                    });
                });

                upEnvironmentBtn.on("click", function () {
                    var order = $(this).attr('data_order');
                    var id = $(this).attr("data_id");

                    $.ajax({
                        url: "/env/up-environment.json",
                        type: 'POST',
                        dataType: "json",
                        data: {
                            environmentId : id,
                            order : order
                        },
                        success: function (result) {
                            if (result.code == 200) {
                                EnvironmentDataTable.reload();
                            } else {
                                MessageBox.setMessage("错误", result.message);
                                MessageBox.show();
                            }
                        },
                        error : function(){
                            MessageBox.showSystemError();
                        }
                    });
                })
            },
            "columns": [{
                "mData": "code",
                "title": "环境代码",
                "width": '10%'
            }, {
                "mData": "name",
                "title": "环境名称",
                "width": '20%'
            }, {
                "mData": "statusDesc",
                "title": "状态",
                "width": '10%'
            }, {
                "title": "操作",
                width: "30%",
                "render": function (data, type, row) {
                    var showAddEnvironmentIpBtn = '<a href="javascript:;" class="btn default btn-xs purple showAddEnvironmentIpBtn"  data_id="' + row.id + '">管理IP</a>';
                    var changeEnvironmentStatusBtn = null;
                    if (row.statusDesc == '启用') {
                        changeEnvironmentStatusBtn = '<a href="javascript:;" class="btn red btn-xs changeEnvironmentStatusBtn"  data_id="' + row.id + '"><i class="fa fa-remove"></i>停用</a>';
                    } else {
                        changeEnvironmentStatusBtn = '<a href="javascript:;" class="btn default btn-xs purple changeEnvironmentStatusBtn"  data_id="' + row.id + '"><i class="fa fa-check"></i>启用</a>';
                    }
                    return showAddEnvironmentIpBtn + "&nbsp;" + changeEnvironmentStatusBtn;
                }
            }, {
                "title": "移动",
                "render": function (data, type, row) {
                    var upBtn = '<a href="javascript:;" class="btn default btn-xs red upEnvironmentBtn" data_id="' + row.id + '" data_order="' + row.order + '"><i class="fa fa-arrow-circle-up"></i>上移</a>';
                    var downBtn = '<a href="javascript:;" class="btn default btn-xs blue downEnvironmentBtn" data_id="' + row.id + '" data_order="' + row.order + '"><i class="fa fa-arrow-circle-down"></i>下移</a>';

                    var buttons = "";

                    if (row.order == 1 && row.total == row.order) {
                        return buttons;
                    }
                    if (row.order == 1) {
                        buttons = buttons + "&nbsp;" + downBtn;
                        return buttons;
                    }
                    if (row.total != row.order) {
                        buttons = buttons + "&nbsp;" + downBtn + "&nbsp;" + upBtn;
                    } else {
                        buttons = buttons + "&nbsp;" + upBtn;
                    }
                    return buttons;
                }
            }]
        });

        $("#addEnvironmentModalBtn").on('click', function(){
            $(".resetAddEnvironmentForm").click();
            $("#addEnvironmentModal").modal();

        });

        $(".checkAddEnvironmentBtn").on('click', function(){
            var name = $("#addEnvironmentName").val();
            var code = $("#addEnvironmentCode").val();

            if (code == null || code == '') {
                MessageBox.setMessage("错误", "请填写环境代码");
                MessageBox.show();
            }
            if (name == null || name == '') {
                MessageBox.setMessage("错误", "请填写环境名称");
                MessageBox.show();
            }
            EnvironmentDataTable.confirmAddEnvironmentModal.modal();
        });

        $(".addEnvironmentBtn").on('click', function(){
            $.ajax({
                url : "/env/add-environment.json",
                type: "POST",
                dataType:'json',
                data: {
                    name : $("#addEnvironmentName").val(),
                    code : $("#addEnvironmentCode").val()
                },
                success: function(result) {
                    if(result.code == 200) {
                        MessageBox.setMessage("成功", "保存成功");
                        MessageBox.show();
                        EnvironmentDataTable.closeAddEnvironmentModalBtn.click();
                        EnvironmentDataTable.reload();
                    }else{
                        MessageBox.setMessage("错误", result.message);
                        MessageBox.show();
                    }
                },
                error: function(){
                    MessageBox.setMessage("错误", "请联系管理员");
                    MessageBox.show();
                }
            })
        });

        EnvironmentDataTable.confirmChangeEnvironmentStatusBtn.on('click', function () {
            var changeEnvironmentStatusInput = $("#changeEnvironmentStatus");
            $.ajax({
                url: '/env/change-environment-status.json',
                type: 'POST',
                dataType: 'json',
                data: {
                    environmentId: changeEnvironmentStatusInput.val(),
                    status: changeEnvironmentStatusInput.attr("change_status")
                },
                beforeSend: function () {
                    $(".closeConfirmChangeEnvironmentStatusModalBtn").click();
                },
                success: function (result) {
                    if (result.code == 200) {
                        MessageBox.setMessage("成功", "修改成功");
                        MessageBox.show();
                        EnvironmentDataTable.reload();
                    } else {
                        MessageBox.setMessage("错误", result.message);
                        MessageBox.show();
                    }
                },
                error : function(){
                    MessageBox.showSystemError();
                }
            });
        });


    },

    initEnvironmentIpDataTable: function(){
        this.environmentDataTable.on('click', "tbody td .showAddEnvironmentIpBtn", function () {
            var addEnvironmentIpBtn= $(".addEnvironmentIpBtn");
            EnvironmentDataTable.confirmDeleteEnvironmentIpBtn.off('click');
            EnvironmentDataTable.checkAddEnvironmentIpBtn.off('click');
            addEnvironmentIpBtn.off('click');

            var trs = $("#environment_manager").children('tbody').children('tr');
            var nTr = $(this).parents('tr')[0];
            if (EnvironmentDataTable.environmentDataTable.fnIsOpen(nTr)) {
                EnvironmentDataTable.environmentDataTable.fnClose(nTr);
            } else {
                trs.each(function () {
                    var tr = $(this);
                    if (EnvironmentDataTable.environmentDataTable.fnIsOpen(tr)) {
                        EnvironmentDataTable.environmentDataTable.fnClose(tr);
                    }
                });
                var environmentId = $(this).attr("data_id");
                var html = '<div class="table-toolbar"><div class="row"><div class="col-md-12"><div class="btn-group"><button id="showAddEnvironmentIpModalBtn" data_environment_id="' + environmentId + '" class="btn green">添加IP<i class="fa fa-plus"></i></button></div></div></div></div><table class="table table-striped table-bordered table-hover" id="environmentIp_table" width="100%"><tr><th></th><th></th></tr></table>';

                EnvironmentDataTable.environmentDataTable.fnOpen(nTr, html, "info_row");



                var environmentIpDataTable = null;

                if (environmentIpDataTable != null) {
                    environmentIpDataTable.unbind();
                }

                environmentIpDataTable = $("#environmentIp_table").dataTable({
                    "processing": false,
                    "serverSide": true,
                    "sort": false,
                    "paging": false,
                    "searching": false,
                    "autoWidth": true,
                    "ajax": {
                        url: "/env/list-environment-ip.json",
                        data: function (data) {
                            data.environmentId = environmentId;
                        },
                        type: "POST"
                    },
                    "columns": [{
                        "mData": "ip",
                        "title": "IP",
                        "width": '10%',
                    }, {
                        "title": "操作",
                        "width": '20%',
                        "render": function (data, type, row) {
                            return '<a data_environment_ip="' + row.ip + '" data_environment_ip_id ="' + row.id + '" class="btn red btn-xs deleteEnvironmentIpBtn">删除</a>';
                        }
                    }]
                });
                environmentIpDataTable.on('draw.dt', function () {
                    var deleteEnvironmentIpBtn = $(".deleteEnvironmentIpBtn");
                    deleteEnvironmentIpBtn.off('click');
                    deleteEnvironmentIpBtn.on('click', function () {
                        var deleteEnvironmentIpBtn = $(this);
                        var environmentIpId = deleteEnvironmentIpBtn.attr("data_environment_ip_id");
                        var environmentIp = deleteEnvironmentIpBtn.attr("data_environment_ip");
                        $("#deleteEnvironmentIp").val(environmentIpId);
                        EnvironmentDataTable.deleteEnvironmentIpMessage.html("确定要删除" + environmentIp + "吗?");
                        EnvironmentDataTable.confirmDeleteEnvironmentIpModal.modal();
                    });
                    var showAddEnvironmentIpModalBtn = $("#showAddEnvironmentIpModalBtn");
                    showAddEnvironmentIpModalBtn.off('click');
                    showAddEnvironmentIpModalBtn.on('click', function () {
                        EnvironmentDataTable.addEnvironmentIpModal.modal();
                    });
                });
                EnvironmentDataTable.confirmDeleteEnvironmentIpBtn.on('click', function () {
                    $.ajax({
                        url: '/env/delete-environment-ip.json',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            environmentIpId: $("#deleteEnvironmentIp").val()
                        },
                        beforeSend: function () {
                            EnvironmentDataTable.closeDeleteEnvironmentIpModalBtn.click();
                        },
                        success: function (result) {
                            if (result.code == 200) {
                                MessageBox.setMessage("成功", "删除成功");
                                MessageBox.show();
                                environmentIpDataTable.fnDraw();
                            } else {
                                MessageBox.setMessage("错误", result.message);
                                MessageBox.show();
                            }
                        },
                        error: function () {
                            MessageBox.showSystemError();
                        }
                    });
                });

                EnvironmentDataTable.checkAddEnvironmentIpBtn.on('click', function () {
                    var ip = $("#addEnvironmentIp").val();
                    if (ip == null || ip == '') {
                        MessageBox.setMessage("错误", "请填写IP");
                        MessageBox.show();
                        return;
                    }

                    if (ip.endsWith(".")) {
                        MessageBox.setMessage("错误", "IP格式错误");
                        MessageBox.show();
                        return;
                    }

                    var array = ip.split(".");

                    for (var i = 0; i < array.length; i++) {
                        if (isNaN(array[i])) {
                            MessageBox.setMessage("错误", "IP格式错误");
                            MessageBox.show();
                            return;
                        }

                        if (array[i] > 255 || array[i] < 1) {
                            MessageBox.setMessage("错误", "IP格式错误");
                            MessageBox.show();
                            return;
                        }
                    }
                    EnvironmentDataTable.confirmAddEnvironmentIpModal.modal();
                })

                addEnvironmentIpBtn.on('click', function () {
                    $.ajax({
                        url: '/env/add-environment-ip.json',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            environmentId: $("#showAddEnvironmentIpModalBtn").attr('data_environment_id'),
                            ip: $("#addEnvironmentIp").val()
                        },
                        beforeSend: function () {

                        },
                        complete: function () {

                        },
                        success: function (result) {
                            if (result.code == 200) {
                                MessageBox.setMessage("成功", "添加成功");
                                MessageBox.show();
                                EnvironmentDataTable.closeAddEnvironmentIpModalBtn.click();
                                EnvironmentDataTable.closeConfirmAddEnvironmentIpModalBtn.click();
                                $("#addEnvironmentIp").val("");
                                environmentIpDataTable.fnDraw();
                            } else {
                                MessageBox.setMessage("错误", result.message);
                                MessageBox.show();
                            }

                        },
                        error: function () {
                            MessageBox.showSystemError();
                        }
                    });
                });
            }
        });


    }

}