/**
 * Created by Hahn on 2016-02-03.
 */

var UserView = (function(formId){

    var userList = [];

    $(formId).submit(function(e){
        e.preventDefault();
        var data = util.queryStringToJson($(this).serialize());

        var method = $(this).find('button').attr('data-method');
        if("POST" === method){
            UserService.register(data);

        }else if("PUT" === method){
            UserService.modifyUserById(data.id, data);

        }else if("DELETE" === method){
            UserService.removeUserById(data.id);

        }else {
            UserService.getUserById(data.id);

        }
    });

    $(formId).find('input#isMethodOverride').change(function(){
        UserService.setIsMethodOverride($(this).prop("checked"));
    })

    var setUserList = function(result){
        userList = result;
    }

    // public
    return {
        displayResult: function(result){
            $(".example.result").removeClass("hidden");
            $(".result-display").text(result);
        },

        setUserListCombo: function(result){
            setUserList(result);

            $(formId).find('select#inputId').append('<option>Select User</option>');
            $.each(result, function(idx){
                $(formId).find('select#inputId').append('<option value="'+this.userId+'">'+this.loginName+'</option>');
            });

            $(formId).find('select#inputId').on('change', function(e){
                var that = this;
                var selectedUser = {};
                $.each(userList, function(idx){
                    if(this.userId === $(that).val()){
                        selectedUser = this;
                        return;
                    }
                });

                $(formId).find('input#inputLoginName').val(selectedUser.loginName);
                $(formId).find('input#inputEmail').val(selectedUser.emailAddress);
                $(formId).find('input#inputFName').val(selectedUser.firstName);
                $(formId).find('input#inputLName').val(selectedUser.lastName);
            });
        }
    }
})("#formUser");