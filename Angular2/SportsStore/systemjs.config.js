/**
 * Created by alex on 20.03.17.
 */
(function (global) {
    var paths = {
        "@angular/*": "node_modules/@angular/*"
    }

    var packages = {"app": {}};

    var angularModules = ["common", "compiler", "core", "platform-browser", "platform-browser-dunamic", "forms", "http", "router"];

    angularModules.forEach(function (pkg) {
        packages["@angular/" + pkg] = {
            main: "/bundles/" + pkg + ".umd.min.js"
        };
    });

    System.config({paths: paths, packages: packages});
})(this);