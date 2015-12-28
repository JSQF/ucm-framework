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
            <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <div class="modal fade" id="message" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="messageTitle" class="modal-title">Modal title</h4>
                        </div>
                        <div id="messageContent" class="modal-body">
                            Widget settings form goes here
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>

            <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <div class="modal fade" id="wait" tabindex="-1" role="dialog" aria-labelledby="waitModalLabel" aria-hidden="true">
                <img src="<%=request.getContextPath()%>/assets/wait.gif"/>
            </div>

            <div class="modal fade" id="confirmAddProject" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="confirmTitle" class="modal-title">确认</h4>
                        </div>
                        <div id="confirmAddProjectMessage" class="modal-body">

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn default addProjectBtn" data-dismiss="modal">确定</button>
                            <button type="button" class="btn red" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <!-- BEGIN PAGE HEADER-->
            <h3 class="page-title">
                添加项目 <small>添加ucm项目</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="fa fa-home"></i>
                        <a href="javascript:;">项目管理</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <i class="fa fa-home"></i>
                        <a href="<%=request.getContextPath()%>/project/show-add.htm">添加项目</a>
                    </li>
                </ul>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row">
                <div class="col-md-12 ">
                    <!-- BEGIN SAMPLE FORM PORTLET-->
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i> 添加项目
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form role="form">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label>项目代码</label>
                                        <div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-file-text"></i>
											</span>
                                            <input id="addProjectCode" type="text" class="form-control" maxlength="32" placeholder="项目代码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>项目名称</label>
                                        <div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-file-text"></i>
											</span>
                                            <input id="addProjectName" type="text" class="form-control" maxlength="32" placeholder="项目名称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>项目类型</label>
                                        <select id="addProjectType" class="form-control">
                                            <c:forEach items="${projectTypeMap}" var="entry">
                                                <option value="${entry.key}">${entry.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>项目描述</label>
                                        <textarea id="addProjectDescription" class="form-control" maxlength="64" rows="3" placeholder="请填写项目描述" required="required"></textarea>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="button" class="btn blue checkAddProjectBtn">添加</button>
                                    <button type="reset" class="btn default resetAddProjectForm">重置</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- END SAMPLE FORM PORTLET-->
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
<script>
    jQuery(document).ready(function() {

        $(".checkAddProjectBtn").on("click", function(){
            var code = $("#addProjectCode").val();
            var name = $("#addProjectName").val();
            var description = $("#addProjectDescription").val();

            if (code == null || code == '') {
                $("#messageTitle").html("错误");
                $("#messageContent").html("请填写项目代码")
                $("#message").modal();
                return;
            }

            if (name == null || name == '') {
                $("#messageTitle").html("错误");
                $("#messageContent").html("请填写项目名称")
                $("#message").modal();
                return;
            }


            if (description == null || description == '') {
                $("#messageTitle").html("错误");
                $("#messageContent").html("请填写项目描述")
                $("#message").modal();
                return;
            }

            $("#confirmAddProjectMessage").html("确认添加吗");
            $("#confirmAddProject").modal();

        });

        $(".addProjectBtn").on("click", function(){
            var code = $("#addProjectCode").val();
            var name = $("#addProjectName").val();
            var type = $("#addProjectType").val();
            var description = $("#addProjectDescription").val();

            $.ajax({
                "url":"<%=request.getContextPath()%>/project/add-project.json",
                "type":"POST",
                "dataType":"json",
                "beforeSend": function(){
                    $("#wait").toggle();
                },
                "complete":function(){
                    $("#wait").toggle();
                },
                data:{
                    "projectCode": code,
                    "projectName": name,
                    "projectType": type,
                    "projectDesc": description
                },
                success: function(result) {
                    if (result.code == 200) {
                        $(".resetAddProjectForm").click();
                        $("#messageTitle").html("成功");
                        $("#messageContent").html("添加成功")
                        $("#message").modal();
                    }else {
                        $("#messageTitle").html("失败");
                        $("#messageContent").html(result.message);
                        $("#message").modal();
                    }
                },
                "error": function(){
                    $("#messageTitle").html("失败");
                    $("#messageContent").html("请联系管理");
                    $("#message").modal();
                }

            });
        })
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>