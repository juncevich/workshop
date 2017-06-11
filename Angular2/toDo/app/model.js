"use strict";
var Model = (function () {
    function Model() {
        this.user = "Alex";
        this.items = [new TodoItem("Buy Flowers", false),
            new TodoItem("GetShoes", false),
            new TodoItem("Collect Tickets", true),
            new TodoItem("Call Joe", false)];
    }
    return Model;
}());
exports.Model = Model;
var TodoItem = (function () {
    function TodoItem(action, done) {
        this.action = action;
        this.done = done;
    }
    return TodoItem;
}());
exports.TodoItem = TodoItem;
