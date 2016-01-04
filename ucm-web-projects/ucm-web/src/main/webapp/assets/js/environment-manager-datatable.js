var EnvironmentDataTable = {
    environmentDataTable: null,

    reload: function () {
        this.environmentDataTable.fnDraw();
    },

    initDataTable: function () {
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
                $(".changeEnvironmentStatus").on('click', function () {
                    var changeStatusBtn = $(this);
                    var changeEnvironmentStatusInput = $("#changeEnvironmentStatus");
                    changeEnvironmentStatusInput.val(changeStatusBtn.attr('data_id'));
                    if (changeStatusBtn.text().indexOf('停用') == -1) {
                        $(".changeEnvironmentStatusMessage").html("确定启用吗?");
                        changeEnvironmentStatusInput.attr("change_status", '1')
                    } else {
                        $(".changeEnvironmentStatusMessage").html("确定停用吗?");
                        changeEnvironmentStatusInput.attr("change_status", '0')
                    }
                    $("#confirmChangeEnvironmentStatusModal").modal();
                });

                $(".downEnvironmentBtn").on("click", function () {
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
                                $("#messageTitle").html("失败");
                                $("#messageContent").html(result.message);
                                $("#message").modal();
                            }
                        },
                        error : function(){
                            $("#messageTitle").html("失败");
                            $("#messageContent").html("请联系管理员");
                            $("#message").modal();

                        }
                    });
                });

                $(".upEnvironmentBtn").on("click", function () {
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
                                $("#messageTitle").html("失败");
                                $("#messageContent").html(result.message);
                                $("#message").modal();
                            }
                        },
                        error : function(){
                            $("#messageTitle").html("失败");
                            $("#messageContent").html("请联系管理员");
                            $("#message").modal();

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
                        changeEnvironmentStatusBtn = '<a href="javascript:;" class="btn red btn-xs changeEnvironmentStatus"  data_id="' + row.id + '"><i class="fa fa-remove"></i>停用</a>';
                    } else {
                        changeEnvironmentStatusBtn = '<a href="javascript:;" class="btn default btn-xs purple changeEnvironmentStatus"  data_id="' + row.id + '"><i class="fa fa-check"></i>启用</a>';
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


        this.environmentDataTable.on('click', "tbody td .showAddEnvironmentIpBtn", function () {
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
                })
                var environmentId = $(this).attr("data_id");
                var html = '<div class="table-toolbar"><div class="row"><div class="col-md-12"><div class="btn-group"><button id="showAddEnvironmentIpModalBtn" data_environment_id="' + environmentId + '" class="btn green">添加IP<i class="fa fa-plus"></i></button></div></div></div></div><table class="table table-striped table-bordered table-hover" id="environmentIp_table" width="100%"><tr><th></th><th></th></tr></table>';

                EnvironmentDataTable.environmentDataTable.fnOpen(nTr, html, "info_row");


                var environmentIpDataTable = $("#environmentIp_table").dataTable({
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
                    }],
                });


                environmentIpDataTable.on('draw.dt', function () {
                    $(".deleteEnvironmentIpBtn").on('click', function () {
                        var deleteEnvironmentIpBtn = $(this);
                        var environmentIpId = deleteEnvironmentIpBtn.attr("data_environment_ip_id");
                        var environmentIp = deleteEnvironmentIpBtn.attr("data_environment_ip");
                        $("#deleteEnvironmentIp").val(environmentIpId);
                        $(".deleteEnvironmentIpMessage").html("确定要删除" + environmentIp + "吗?");
                        $("#confirmDeleteEnvironmentIpModal").modal();
                    })


                })

                $(".confirmDeleteEnvironmentIpBtn").on('click', function () {
                    $.ajax({
                        url: '/env/delete-environment-ip.json',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            environmentIpId: $("#deleteEnvironmentIp").val()
                        },
                        beforeSend: function () {
                            $(".closeDeleteEnvironmentIpModal").click();
                        },
                        success: function (result) {
                            if (result.code == 200) {
                                $("#messageTitle").html("成功");
                                $("#messageContent").html("删除成功");
                                $("#message").modal();
                                environmentIpDataTable.fnDraw();
                            } else {
                                $("#messageTitle").html("错误");
                                $("#messageContent").html(result.message);
                                $("#message").modal();
                            }
                        },
                        error: function () {
                            $("#messageTitle").html("异常");
                            $("#messageContent").html("请联系管理员");
                            $("#message").modal();
                        }
                    });
                })

                $("#showAddEnvironmentIpModalBtn").on('click', function () {
                    $("#addEnvironmentIpModal").modal();
                })

                $(".checkAddEnvironmentIpBtn").on('click', function () {
                    var ip = $("#addEnvironmentIp").val();
                    if (ip == null || ip == '') {
                        $("#messageTitle").html("错误");
                        $("#messageContent").html("请填写IP");
                        $("#message").modal();
                        return;
                    }

                    if (ip.endsWith(".")) {
                        $("#messageTitle").html("错误");
                        $("#messageContent").html("IP格式错误");
                        $("#message").modal();
                        return;
                    }

                    var array = ip.split(".");

                    for (var i = 0; i < array.length; i++) {
                        if (isNaN(array[i])) {
                            $("#messageTitle").html("错误");
                            $("#messageContent").html("IP格式错误");
                            $("#message").modal();
                            return;
                        }

                        if (array[i] > 255 || array[i] < 1) {
                            $("#messageTitle").html("错误");
                            $("#messageContent").html("IP格式错误");
                            $("#message").modal();
                            return;
                        }
                    }
                    $("#confirmAddEnvironmentIp").modal();

                })

                $(".addEnvironmentIpBtn").on('click', function () {
                    $.ajax({
                        url: '/env/add-environment-ip.json',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            environmentId: $("#showAddEnvironmentIpModalBtn").attr('data_environment_id'),
                            ip: $("#addEnvironmentIp").val()
                        },
                        beforeSend: function () {
                            $(".wait").modal();
                        },
                        complete: function () {
                            $(".wait").toggle();
                        },
                        success: function (result) {
                            if (result.code == 200) {
                                $("#messageTitle").html("成功");
                                $("#messageContent").html("添加成功");
                                $("#message").modal();
                                $(".closeConfirmAddEnvironment").click();
                                $(".closeAddEnvironmentIpModal").click();
                                $(".closeAddEnvironmentIpBtn").click();
                                $("#addEnvironmentIp").val("");
                                environmentIpDataTable.fnDraw();
                            } else {
                                $("#messageTitle").html("错误");
                                $("#messageContent").html(result.message)
                                $("#message").modal();
                            }

                        },
                        error: function () {
                            $("#messageTitle").html("错误");
                            $("#messageContent").html("系统错误请联系管理员");
                            $("#message").modal();
                        }
                    });
                });


            }
        });

        $(".changeEnvironmentStatusBtn").on('click', function () {
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
                    $(".closeDeleteEnvironmentIpModal").click();
                },
                success: function (result) {
                    if (result.code == 200) {
                        $("#messageTitle").html("成功");
                        $("#messageContent").html("修改成功");
                        $("#message").modal();
                        EnvironmentDataTable.reload();
                    } else {
                        $("#messageTitle").html("错误");
                        $("#messageContent").html(result.message)
                        $("#message").modal();
                    }
                }
            });
        })
    }

}