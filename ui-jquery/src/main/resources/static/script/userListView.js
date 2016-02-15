/**
 * Created by Hahn on 2016-02-03.
 */
var itemsPerPage = 3;

var UserListView = (function(formId){

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

            pager = new Pager('user-table');
            pager.init(result.total);
            pager.showPageNav('pager', 'pageNavPosition');
            pager.changeNav(result.pageable.page + 1);
        }
    }
})("#formUserList");

$(document).ready(function(){
    UserService.getUserListPagination(0, itemsPerPage);
});

function Pager(tableName){
    this.currentPage = 1;
    this.pages = 0;

    this.init = function (totalCnt) {
        this.pages = Math.ceil(totalCnt / itemsPerPage);
    }

    this.showPage = function (pageNumber) {
        var from = (pageNumber - 1) * itemsPerPage;

        $('#user-table > tbody').empty();
        UserService.getUserListPagination(from, itemsPerPage);
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

    this.showPageNav = function (pagerName, positionId) {
        var element = document.getElementById(positionId);

        var pagerHtml = '<nav><ul class="pagination">'
        pagerHtml += '<li><span onclick="' + pagerName + '.prev();">&laquo;</span></li>';
        for (var page = 1; page <= this.pages; page++) {
            pagerHtml += '<li class="pgNum" id="pg' + page + '"><span onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span></li>';
        }
        pagerHtml += '<li><span onclick="' + pagerName + '.next();">&raquo;</span></li>';
        pagerHtml += '</ul></nav>'
        element.innerHTML = pagerHtml;
    }

    this.changeNav = function (pageNumber) {
        var oldPageAnchor = document.getElementsByClassName('pgNum');
        oldPageAnchor.className = '';

        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg' + this.currentPage);
        newPageAnchor.className = 'active';
    }
}