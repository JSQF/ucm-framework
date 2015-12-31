<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<%@include file="../commons/head.jsp"%>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content">
<%@include file="../commons/page-header.jsp"%>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <%@include file="../commons/sidebar.jsp"%>
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">

            <!-- /.modal -->
            <div class="modal fade" id="addEnvironmentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close closeAdd" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">添加环境</h4>
                        </div>
                        <div class="modal-body">
                            <form role="form">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label>环境代码</label>
                                        <div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-file-text"></i>
											</span>
                                            <input id="addEnvironmentCode" type="text" class="form-control" maxlength="32" placeholder="环境代码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>环境名称</label>
                                        <div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-file-text"></i>
											</span>
                                            <input id="addEnvironmentName" type="text" class="form-control" maxlength="32" placeholder="环境名称">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="button" class="btn blue checkAddEnvironmentBtn">添加</button>
                                    <button type="reset" class="btn default resetAddEnvironmentForm">重置</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <div class="modal fade" id="addEnvironmentIpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close closeAdd" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">添加IP</h4>
                        </div>
                        <div class="modal-body">
                            <form role="form">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label>IP</label>
                                        <div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-file-text"></i>
											</span>
                                            <input id="addEnvironmentIp" type="text" class="form-control" maxlength="32" placeholder="IP">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="button" class="btn blue checkAddEnvironmentIpBtn">添加</button>
                                    <button type="reset" class="btn default resetAddEnvironmentIpForm">重置</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <div class="modal fade" id="confirmAddEnvironment" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="confirmTitle" class="modal-title">确认</h4>
                        </div>
                        <div id="confirmAddProjectMessage" class="modal-body">
                            确定添加吗?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn default addEnvironmentBtn" data-dismiss="modal">确定</button>
                            <button type="button" class="btn red" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <div class="modal fade" id="confirmAddEnvironmentIp" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">确认</h4>
                        </div>
                        <div class="modal-body">
                            确定添加吗?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn default addEnvironmentIpBtn" data-dismiss="modal">确定</button>
                            <button type="button" class="btn red" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <%@include file="../commons/message-modal.jsp"%>
            <!-- /.modal -->
            <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <!-- BEGIN PAGE HEADER-->
            <h3 class="page-title">
                 环境管理 <small>管理ucm环境</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="fa fa-home"></i>
                        <a href="<%=request.getContextPath()%>/env/show-manager.htm">环境管理</a>
                    </li>
                </ul>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-edit"></i>项目管理
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-toolbar">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="btn-group">
                                            <button id="addEnvironmentModalBtn" class="btn green">
                                                添加环境<i class="fa fa-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-hover table-bordered" id="environment_manager">
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </table>
                        </div>

                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
            </div>

            <!-- END PAGE CONTENT-->
        </div>
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="../commons/footer.jsp"%>
<%@include file="../commons/javascripts.jsp"%>
<%@include file="../commons/metronic.jsp"%>
<script src="<%=request.getContextPath()%>/assets/js/environment-manager-datatable.js" type="text/javascript"></script>
<script>
    $(function() {
        EnvironmentDataTable.initDataTable();

        $("#addEnvironmentModalBtn").on('click', function(){
            $(".resetAddEnvironmentForm").click();
            $("#addEnvironmentModal").modal();

        });

        $(".checkAddEnvironmentBtn").on('click', function(){
            var name = $("#addEnvironmentName").val();
            var code = $("#addEnvironmentCode").val();

            if (code == null || code == '') {
                $("#messageTitle").html("错误");
                $("#messageContent").html("请填写环境代码")
                $("#message").modal();
            }
            if (name == null || name == '') {
                $("#messageTitle").html("错误");
                $("#messageContent").html("请填写环境名称")
                $("#message").modal();
            }
            $("#confirmAddEnvironment").modal();
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
                        $("#messageTitle").html("成功");
                        $("#messageContent").html("保存成功")
                        $("#message").modal();
                        $(".closeAdd").click();
                        EnvironmentDataTable.reload();
                    }else{
                        $("#messageTitle").html("错误");
                        $("#messageContent").html(result.message);
                        $("#message").modal();
                    }
                },
                error: function(){
                    $("#messageTitle").html("错误");
                    $("#messageContent").html("保存失败")
                    $("#message").modal();
                }
            })
        });


    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>