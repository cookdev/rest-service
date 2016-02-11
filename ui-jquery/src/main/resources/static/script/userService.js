/**
 * Created by Hahn on 2016-02-03.
 */

var UserService = (function() {

    // private 영역 시작
    var user = {
    };

    var isMethodOverride = false;

    var setUser = function(userData){
        user.id = userData.id;
        user.loginName = userData.loginName;
        user.emailAddress = userData.emailAddress;
        user.firstName = userData.firstName;
        user.lastName = userData.lastName;
    };

    var getUser = function(){
        return user;
    };

    var registerNewUser = function(registerUser, callback){
        if(!util.isObject(registerUser)){
            return undefined;
        }
        setUser(registerUser);

        $.ajax(util.urlRoot.user, {
            headers: util.headers,
            method: 'POST',
            data: util.jsonParser(user),
            complete: callback
        });
    };

    var getAllUserAccount = function(callback){
        $.ajax(util.urlRoot.user + "/accounts", {
            headers: util.headers,
            method: 'GET',
            complete: callback
        });
    };

    var getUserList = function(callback){
        $.ajax(util.urlRoot.user, {
            headers: util.headers,
            method: 'GET',
            data: {
                offset: 1,
                limit: 1
            },
            complete: callback
        });
    };

    var getUserById = function(userId, callback){
        if(userId === undefined || userId === ""){
            throw "UserId is null";
        }
        $.ajax(util.urlRoot.user + "/" + userId, {
            headers: util.headers,
            method: 'GET',
            complete: callback
        });
    };

    var modifyUserById = function(userId, registeredUser, callback){
        if(!util.isObject(registeredUser)){
            return undefined;
        }
        setUser(registeredUser);

        $.ajax(util.urlRoot.user + "/" + userId, {
            headers: util.headers,
            method: 'PUT',
            data: util.jsonParser(registeredUser),
            complete: callback
        });
    };

    var removeUserById = function(userId, callback){
        if(userId === undefined || userId === ""){
            throw "UserId is null";
        }

        $.ajax(util.urlRoot.user + "/" + userId, {
            headers: util.headers,
            method: 'DELETE',
            complete: callback
        });
    };

    $.ajaxPrefilter(function( options ) {
        if ( isMethodOverride ) {
            //options.url = options.url + "?_method=" + options.method;
            options.headers["x-http-method-override"] = options.method;
            options.method = 'POST';
            options.type = 'POST';
        }
    });

    // public
    return {

        setIsMethodOverride: function(isOverride){
            isMethodOverride = isOverride;
        },

        register: function(registerUser, callback) {
            callback = callback || function(jqXHR, textStatus){
                jqXHR.responseJSON = jqXHR.responseJSON || textStatus
                if("error" === textStatus){
                    UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                }else{
                    UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                }

            };
            registerNewUser(registerUser, callback);
        },

        getUserById: function(userId, callback){
            callback = callback || function(jqXHR, textStatus){
                    jqXHR.responseJSON = jqXHR.responseJSON || textStatus
                    if("error" === textStatus){
                        UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                    }else{
                        UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                    }

                };
            getUserById(userId, callback);
        },

        getUserListForIdList: function(callback){
            callback = callback || function(jqXHR, textStatus){
                jqXHR.responseJSON = jqXHR.responseJSON || textStatus
                if("error" === textStatus){
                    UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                }else{
                    UserView.setUserListCombo(jqXHR.responseJSON);
                }

            };
            getAllUserAccount(callback);
        },

        modifyUserById: function(userId, registeredUser, callback){
            callback = callback || function(jqXHR, textStatus){
                jqXHR.responseJSON = jqXHR.responseJSON || textStatus
                if("error" === textStatus){
                    UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                }else{
                    UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                }

            };
            modifyUserById(userId, registeredUser, callback);
        },

        removeUserById: function(userId, callback){
            callback = callback || function(jqXHR, textStatus){
                jqXHR.responseJSON = jqXHR.responseJSON || textStatus
                if("error" === textStatus){
                    UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                }else{
                    UserView.displayResult(util.fromJson(jqXHR.responseJSON));
                }

            };
            removeUserById(userId, callback);
        }
    }

})();