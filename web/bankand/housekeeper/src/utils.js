export default {
    getUrlKey: function (name) {
        return decodeURIComponent(
            (new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
    },
    isNotEmpty: function (v) {
        return !this.isEmpty(v);
    },
    isEmpty: function (v) {
        return v == undefined || v == null || v == "" || v == "undefined" || v == "null";
    },
}
