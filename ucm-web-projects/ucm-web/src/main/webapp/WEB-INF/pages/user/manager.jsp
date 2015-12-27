<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<%@include file="../commons/head.jsp" %>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content">
<%@include file="../commons/page-header.jsp" %>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <%@include file="../commons/sidebar.jsp" %>
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">
            <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
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
                用户管理
                <small>管理用户</small>
            </h3>
            <div class="page-bar">
                <ul class="page-breadcrumb">
                    <li>
                        <i class="fa fa-user"></i>
                        <a href="javascript:;">用户 & 角色</a>
                        <i class="fa fa-angle-right"></i>
                    </li>
                </ul>
                <ul class="page-breadcrumb">
                    <li>
                        <i class="fa fa-users"></i>
                        <a href="<%=request.getContextPath()%>/user/manager.htm">管理用户</a>
                    </li>
                </ul>
            </div>
            <!-- END PAGE HEADER-->
            <div class="row">
                <div class="portlet light bordered">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-equalizer font-blue-hoki"></i>
                            <span class="caption-subject font-blue-hoki bold uppercase">查询</span>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <!-- BEGIN FORM-->
                        <form action="#" class="horizontal-form">
                            <div class="form-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">用户名</label>
                                            <input type="text" id="searchUsername" class="form-control" placeholder="请输入需要查询的用户名">
                                        </div>
                                    </div>
                                    <!--/span-->
                                    <!--
                                        <div class="col-md-6">
                                        <div class="form-group has-error">
                                            <label class="control-label">Last Name</label>
                                            <input type="text" id="lastName" class="form-control" placeholder="Lim">
															<span class="help-block">
															This field has error. </span>
                                        </div>
                                    </div>
                                    -->
                                    <!--/span-->
                                </div>
                                <!--/row-->
                            </div>
                            <div class="form-actions center-block">
                                <button type="button" class="btn blue" onclick="queryUser()">查询</button>
                                <button type="reset" class="btn default">重置</button>
                            </div>
                        </form>
                        <!-- END FORM-->
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-edit"></i>用户管理
                            </div>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-striped table-hover table-bordered" id="user_manager">
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
            <!-- END PAGE CONTENT -->
        </div>
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="../commons/footer.jsp" %>
<%@include file="../commons/javascripts.jsp" %>
<%@include file="../commons/metronic.jsp" %>
<script src="<%=request.getContextPath()%>/assets/js/user-manager-datatable.js" type="text/javascript"></script>
<script>
    $(function () {
        UserManagerDataTable.handleListUserDataTable();
    });

    function queryUser(){
        UserManagerDataTable.reload();
    }
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
