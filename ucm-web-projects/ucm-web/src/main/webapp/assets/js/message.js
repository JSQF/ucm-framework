var MessageBox = {
    messageModal: null,

    init: function(){
        this.messageModal = $("#message");
    },

    setMessage: function(title, message) {
        $("#messageTitle").html(title);
        $("#messageContent").html(message);
    },

    show: function(){
        this.messageModal.modal();
    },

    close: function(){

    },

    showSuccess: function(message){
        this.setMessage("成功", message);
        this.show();
    },

    showFailed: function(message) {
        this.setMessage("错误", message);
        this.show();
    },

    showSystemError: function(){
        this.setMessage("错误", "系统错误请联系管理员");
        this.show();
    }
};