var Menu = function() {

    var handleMenu = function(){
        $("a", ".page-sidebar-menu").on('click', function(){
            var href = $(this).attr("href");
            if (href.startsWith("javascript")){
                return;
            }
            $.cookie("menuhref", href);
        })
    }

    var locateMenu = function(){

        var href  = $.cookie('menuhref');
        if (href == null || href == '') {
            return;
        }

        $("a", ".page-sidebar-menu").each(function(){

            var anchor = $(this);
            var li = anchor.parent("li");
            li.removeClass("active open")

            anchor.removeClass("active")

            if (anchor.attr("href") == href) {
                li.addClass("active open");
                anchor.addClass("active");
                li.click();
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