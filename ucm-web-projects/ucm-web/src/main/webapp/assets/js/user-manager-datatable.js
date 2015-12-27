var UserManagerDataTable = {

    userManagerDataTable :  null,

    reload : function(){
        this.userManagerDataTable.fnDraw();
    },

    handleListUserDataTable : function () {
        this.userManagerDataTable = $("#user_manager").dataTable({
            "processing": false,
            "serverSide": true,
            "sort": false,
            "paging": true,
            "searching": false,
            "autoWidth": true,
            "ajax": {
                url: "/user/list-user.json",
                data: function (data) {
                    data.username = $("#searchUsername").val();
                },
                type: "POST"
            },
            "columns": [{
                "mData": "id",
                "title": "ID",
                "width": '10%'
            }, {
                "mData": "username",
                "title": "用户名",
                "width": '50%'
            }, {
                "mData": "createTime",
                "title": "创建时间"

            }, {
                "title": "操作",
                "render": function (data, type, row) {
                    return '<a href="javascript:;" class="addproject" data_id="' + row.id + '">关联项目</a>';
                }
            }]
        });

        this.userManagerDataTable.on('click', ' tbody td .addproject', function(){
            //alert("关联项目");
        })

    },

    init: function () {
        this.handleListUserDataTable();
    }

};