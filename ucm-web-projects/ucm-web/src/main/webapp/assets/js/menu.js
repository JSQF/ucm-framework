var Menu = function() {

    var handleMenu = function(){
        $("a", ".page-sidebar-menu").on('click', function(){
            var menuId = $(this).attr("id");
            if (menuId == null || menuId == ""){
                return;
            }
            document.cookie="menuId=" +menuId + ";path=/;";
        })
    }

    var locateMenu = function(){

        var menuId = null;
        if (document.cookie.length > 0) {
            var start = document.cookie.indexOf("menuId=");
            if (start != -1) {
                start = start + "menuId".length + 1;
                var end = document.cookie.indexOf(";", start);
                if (end == -1) {
                    end = document.cookie.length;
                }
                menuId =  unescape(document.cookie.substring(start, end));
            }
        }

        if (menuId == null || menuId == '') {
            return;
        }

        $("a", ".page-sidebar-menu").each(function(){

            var anchor = $(this);
            var li = anchor.parent("li");
            li.removeClass("active open")

            anchor.removeClass("active")



            if (anchor.attr("id") == null || anchor.attr("id") == '') {
                return;
            }

            if (anchor.attr("id") == menuId) {
                li.addClass("active open");
                anchor.addClass("active");
                if (li.parent('ul').hasClass('sub-menu')) {
                    var obj = li.parent('ul').parent().children().first();
                    obj.click();
                }
            }
        });

    }

    return {
        init: function() {
            handleMenu();
            locateMenu();
        }

    };

}();