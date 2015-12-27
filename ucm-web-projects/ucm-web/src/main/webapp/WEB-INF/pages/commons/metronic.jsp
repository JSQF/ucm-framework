<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script>
    $(document).ready(function(){
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Menu.init();
    });
</script>

