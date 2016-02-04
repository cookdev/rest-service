(function(app) {
    app.UserFormComponent = ng.core
        .Component({
            selector: 'user-form',
            templateUrl: 'user-form.component.html',
            providers: [ng.http.HTTP_PROVIDERS]
        })
        .Class({
            constructor: function (http) {
                this.http = http;
                // Setting Default model
                this.model = new app.User({
                    loginName: 'anyframecloud',
                    emailAddress: 'anyframecloud@api.com',
                    firstName: 'cloud',
                    lastName: 'anyframe'
                });
                this.submitted = false;
            },
            onSubmit: function () {
                var that = this;
                this.http.post('http://localhost:8081/users',
                    JSON.stringify({
                        "loginName": this.model.loginName,
                        "emailAddress": this.model.emailAddress,
                        "firstName": this.model.firstName,
                        "lastName": this.model.lastName
                    }),{
                    headers: {
                        'Accept': 'service/json',
                        'Content-Type': 'service/json'
                    }
                }).subscribe(function(response){
                    that.viewChange(response.json());
                });
            },
            viewChange: function(data){
                var that = this;
                this.http.get('http://localhost:8081/users/'+data.loginName,{
                    headers: {
                        'Accept': 'service/json',
                        'Content-Type': ' service/json'
                    }
                }).subscribe(function(response){
                    that.model = new app.User(response.json());
                    that.submitted = true;
                });
            }

        });
    app.UserFormComponent.parameters = [new ng.core.Inject(ng.http.Http)]
})(window.app || (window.app = {}));