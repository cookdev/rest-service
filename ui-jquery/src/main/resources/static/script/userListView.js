/**
 * Created by Hahn on 2016-02-03.
 */
var UserListView = (function(formId){

    var pager = undefined;

    $(formId).find('select#selectVersion').change(function(e){
        //debugger;
        $('#user-table > tbody').empty();
        UserService.getUserListPagination(0, util.itemsPerPage);
    });

    // public
    return {
        displayResult: function(result){
            $(".example.result").removeClass("hidden");
            $(".result-display").text(result);
        },

        setUserListPagination: function(result){
            $.each(result.content, function(i,item){
                $('#user-table > tbody:last').append('<tr><th>'+item.id+'</th><th>'+item.loginName+'</th><th>'+item.emailAddress+'</th><th>'+item.firstName+'</th><th>'+item.lastName+'</th></tr>');
            });

            if(pager === undefined){
                pager = new Pager('user-table', result.total);
            }
            pager.changeNav(result.pageable.page + 1);
        }
    }
})("#formUserList");

$(document).ready(function(){
    UserService.getUserListPagination(0, util.itemsPerPage);
});

function Pager(tableName, totalCnt){
    this.currentPage = 1;
    this.pages = Math.ceil(totalCnt / util.itemsPerPage);

    this.showPage = function (pageNumber) {
        var from = (pageNumber - 1) * util.itemsPerPage;

        $('#user-table > tbody').empty();
        UserService.getUserListPagination(from, util.itemsPerPage);
    }

    this.prev = function () {
        if (this.currentPage > 1) {
            this.showPage(this.currentPage - 1);
        }
    }

    this.next = function () {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }

    this.showPageNav = function () {
        var $pageNav = $("#pageNavPosition");
        var that = this;

        $pageNav.append('<nav><ul class="pagination"></ul></nav>')
            .find(".pagination")
                .append('<li><span id="pageNavPrev">&laquo;</span></li>');
        $("#pageNavPrev").on('click', function(e){
            that.prev();
        });

        for (var page = 1; page <= this.pages; page++) {
            $pageNav.find(".pagination").append('<li class="pgNum"><span id="pg' + page + '">' + page + '</span></li>');

            $("#pg"+page).on('click', function(e){
                that.showPage($(this).text());
            });
        }

        $pageNav.find(".pagination").append('<li><span id="pageNavNext">&raquo;</span></li>');

        $("#pageNavNext").on('click', function(e){
            that.next();
        });
    }

    this.changeNav = function (pageNumber) {
        var $oldPageAnchor = $('.active');
        $oldPageAnchor.removeClass('active');

        this.currentPage = pageNumber;
        var $newPageAnchor = $('#pg' + this.currentPage);
        $newPageAnchor.parent('li').addClass('active');
    }

    this.showPageNav();
}