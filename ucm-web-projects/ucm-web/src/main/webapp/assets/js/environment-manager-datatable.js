var EnvironmentDataTable = {
    environmentDataTable: null,

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
            "columns": [{
                "mData": "code",
                "title": "环境代码",
                "width": '20%'
            }, {
                "mData": "name",
                "title": "环境名称",
                "width": '50%'
            }, {
                "title": "操作",
                "render": function (data, type, row) {
                    var addEnvironmentIpBtn = '<a href="javascript:;" class="addEnvironmentIp" data_id="' + row.id + '">添加IP</a>';
                    return addEnvironmentIpBtn;
                }
            }]
        })
    }
}