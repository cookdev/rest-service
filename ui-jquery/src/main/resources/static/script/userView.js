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
    });

    // public
    return {
        displayResult: function(result){
            $(".example.result").removeClass("hidden");
            $(".result-display").text(result);
        },

        setUserListCombo: function(result){

            $(formId).find('select#inputId').append('<option>Select User</option>');
            $.each(result, function(idx){
                $(formId).find('select#inputId').append('<option value="'+this.userId+'">'+this.loginName+'</option>');
            });

            $(formId).find('select#inputId').on('change', function(e){
                var that = this;

                UserService.getUserById($(this).val(), function(jqXHR, textStatus){
                    var selectedUser = jqXHR.responseJSON || textStatus
                    if("error" === textStatus){

                    }else{
                        $(formId).find('input#inputLoginName').val(selectedUser.loginName);
                        $(formId).find('input#inputEmail').val(selectedUser.emailAddress);
                        $(formId).find('input#inputFName').val(selectedUser.firstName);
                        $(formId).find('input#inputLName').val(selectedUser.lastName);
                    }
                });
            });
        }
    }
})("#formUser");