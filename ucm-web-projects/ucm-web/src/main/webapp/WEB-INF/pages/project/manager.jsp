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
            <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            Widget settings form goes here
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn blue">Save changes</button>
                            <button type="button" class="btn default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <div class="modal fade" id="updateProjectFormModal" tabindex="-1" role="dialog" aria-labelledby="updateProjectFormModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close closeUpdateProjectModalBtn" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">更新项目</h4>
                        </div>
                        <div class="modal-body">
                            <div class="portlet-body form">
                                <form role="form">
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>项目代码</label>
                                            <div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-file-text"></i>
											</span>
                                                <input id="updateProjectCode" type="text" class="form-control" maxlength="32" placeholder="项目代码" readonly="readonly">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>项目名称</label>
                                            <div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-file-text"></i>
											</span>
                                                <input id="updateProjectName" type="text" class="form-control" maxlength="32" placeholder="项目名称">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>项目类型</label>
                                            <select id="updateProjectType" class="form-control">
                                                <c:forEach items="${projectTypeMap}" var="entry">
                                                    <option value="${entry.key}">${entry.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>是否启用</label>
                                            <div class="radio-list">
                                                <label class="radio-inline">
                                                    <input type="radio" name="updateProjectStatus" value="true"> 启用 </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="updateProjectStatus" value="false"> 停用 </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>项目描述</label>
                                            <textarea id="updateProjectDescription" class="form-control" maxlength="64" rows="3" placeholder="请填写项目描述" required="required"></textarea>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn red checkUpdateProjectFormBtn">更新</button>
                            <button type="button" class="btn default" data-dismiss="modal">取消</button>
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
                管理项目 <small>管理ucm项目</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="fa fa-home"></i>
                        <a href="javascript:;">项目管理</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                    <li>
                        <i class="icon-basket"></i>
                        <a href="<%=request.getContextPath()%>/project/show-manager.htm">管理项目</a>
                    </li>
                </ul>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row">
                <div class="col-md-12 ">
                    <div class="portlet light bordered">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>查询
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="horizontal-form">
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">项目名称</label>
                                                <select id="searchProjectCode" class="form-control">
                                                    <option value="">请选择</option>
                                                    <c:forEach items="${projectCodeNameMap}" var="entry">
                                                        <option value="${entry.key}">${entry.value} - ${entry.key}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">项目类型</label>
                                                <select id="searchProjectType" class="form-control">
                                                    <option value="">请选择</option>
                                                    <c:forEach items="${projectTypeMap}" var="entry">
                                                        <option value="${entry.key}">${entry.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="button" class="btn default queryProjectBtn">查询</button>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
            </div>
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
                            <table class="table table-striped table-hover table-bordered" id="project_manager">
                                <tr>
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
<script src="<%=request.getContextPath()%>/assets/js/project-manager-datatable.js" type="text/javascript"></script>
<script>
    $(function() {
        MessageBox.init();
        ProjectManagerDataTable.init();

        $(".queryProjectBtn").on("click", function(){
            ProjectManagerDataTable.reload();
        });
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>