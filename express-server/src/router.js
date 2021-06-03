"use strict";
exports.__esModule = true;
var Router = (function () {
    function Router() {
    }
    Router.prototype.start = function (app, port) {
        if (port === void 0) { port = 8080; }
    };
    Router.prototype.setupRoutes = function (app) {
        app.get("/", function (req, res) {
            res.send("hello world!");
        });
    };
    return Router;
}());
exports["default"] = new Router();
