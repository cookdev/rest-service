(function(app) {
    app.AppComponent = ng.core
        .Component({
            selector: 'my-app',
            template: '<user-form></user-form>',
            directives: [app.UserFormComponent]
        })
        .Class({
            constructor: function() {}
        });
})(window.app || (window.app = {}));