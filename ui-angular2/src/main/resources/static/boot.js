(function(app) {
    document.addEventListener('DOMContentLoaded', function() {
        //ng.platform.browser.bootstrap(app.AppComponent);
        ng.platform.browser.bootstrap(app.AppComponent, [ng.http.HTTP_PROVIDERS]);
    });
    ng.core.enableProdMode();
})(window.app || (window.app = {}));