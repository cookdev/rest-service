(function(app) {
    app.User = User;
    function User(userJson) {
        this.id = userJson.id;
        this.loginName = userJson.loginName;
        this.emailAddress = userJson.emailAddress;
        this.firstName = userJson.firstName;
        this.lastName = userJson.lastName;
    }
})(window.app || (window.app = {}));