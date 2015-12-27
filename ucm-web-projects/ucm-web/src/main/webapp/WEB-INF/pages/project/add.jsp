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
            <!-- /.modal -->
            <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <!-- BEGIN PAGE HEADER-->
            <h3 class="page-title">
                添加项目 <small>报告 & 统计</small>
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
                                            <input type="text" class="form-control" placeholder="项目代码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>项目名称</label>
                                        <div class="input-group">
											<span class="input-group-addon">
											<i class="fa fa-file-text"></i>
											</span>
                                            <input type="text" class="form-control" placeholder="项目名称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>项目类型</label>
                                        <select class="form-control">
                                            <c:forEach items="${projectTypeMap}" var="entry">
                                                <option value="${entry.key}">${entry.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>项目描述</label>
                                        <textarea class="form-control" rows="3" placeholder="请填写项目描述" required="required"></textarea>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="submit" class="btn blue">添加</button>
                                    <button type="reset" class="btn default">重置</button>
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
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function() {

        //QuickSidebar.init(); // init quick sidebar
        //Demo.init(); // init demo features
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>