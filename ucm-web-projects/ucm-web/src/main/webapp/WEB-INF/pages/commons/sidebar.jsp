<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu " data-keep-expanded="true" data-auto-scroll="true" data-slide-speed="200">
            <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
            <li class="sidebar-toggler-wrapper">
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler">
                </div>
                <!-- END SIDEBAR TOGGLER BUTTON -->
            </li>
            <li class="start active open">
                <a id="-1" href="<%=request.getContextPath()%>/index.htm" class="active">
                    <i class="icon-home"></i>
                    <span class="title">Dashboard</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-user"></i>
                    <span class="title">用户 & 角色</span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a id ="1" href="<%=request.getContextPath()%>/user/manager.htm">
                            <i class="icon-users"></i>
                            用户管理</a>
                    </li>
                    <li>
                        <a id = "2" href="<%=request.getContextPath()%>/project/show-role.htm">
                            <i class="icon-users"></i>
                            添加角色</a>
                    </li>
                    <li>
                        <a id = "3" href="<%=request.getContextPath()%>/project/show-role.htm">
                            <i class="icon-users"></i>
                            管理角色</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-basket"></i>
                    <span class="title">项目管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a id="4" href="<%=request.getContextPath()%>/project/show-add.htm">
                            <i class="icon-home"></i>
                            添加项目</a>
                    </li>
                    <li>
                        <a id="5" href="<%=request.getContextPath()%>/project/show-manager.htm">
                            <i class="icon-basket"></i>
                            管理项目</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-basket"></i>
                    <span class="title">环境管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a id="6" href="<%=request.getContextPath()%>/env/show-add.htm">
                            <i class="icon-home"></i>
                            添加环境</a>
                    </li>
                    <li>
                        <a id="7" href="<%=request.getContextPath()%>/env/show-manager.htm">
                            <i class="icon-basket"></i>
                            管理环境</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
<!-- END SIDEBAR -->
