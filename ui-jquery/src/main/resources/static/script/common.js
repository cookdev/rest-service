/**
 * Created by Hahn on 2016-02-03.
 */
var util = {
    contextRoot: "http://localhost:8081",
    apiVersion: {
        v1: "/v1",
        v2: "/v2"
    },
    urlRoot: {
        user: '/users'
    },
    makeUrl: function(version, root){
        return this.contextRoot + this.apiVersion[version] + this.urlRoot[root]
    },
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
    },
    isString: function(value) {
        return typeof value === 'string';
    },
    isNumber: function(value) {
        return typeof value === 'number';
    },
    isObject: function(value) {
        return value !== null && typeof value === 'object';
    },
    toJson: function(data){
        return this.isString(data) ? JSON.parse(data) : data;

    },
    fromJson: function(data){
        if (typeof data === 'undefined') return undefined;
        return JSON.stringify(data);

    },
    jsonParser: function (data) {
        if(this.isString(data)){
            return this.toJson(data);

        }else if(this.isObject(data)){
            return this.fromJson(data);

        }else{
            return null;
        }
    },
    queryStringToJson: function(queryString){
        //decodeURIComponent(data.emailAddress)
        return (queryString || document.location.search)
            .replace(/(^\?)/,'')
            .split("&")
            .map(function(n){
                n = n.split("=");
                this[n[0]] = decodeURIComponent(n[1]);
                return this;
            }.bind({})
            )[0];
    }
};