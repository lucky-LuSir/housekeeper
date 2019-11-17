

var G_menuList = [];


export function judgeMenu(pageItemid){
    var mFlag = false;
    //1获取localhost中的treeMenuList
    var menuTreeList=JSON.parse(localStorage.getItem("judgeMenuTreeList"));
    for(var i=0;i<menuTreeList.length;i++){
        var one = menuTreeList[i];
        var oneItemid = one.item;
        if(pageItemid== oneItemid){
            mFlag = true;
           break;
        }
    }
    return mFlag;
}

function treeToList(node){
    if (!node) {
        return;
    }

    G_menuList.push(node);
    if (node.children && node.children.length > 0) {
        var i = 0;
        for (i = 0; i < node.children.length; i++) {
            treeToList(node.children[i]);
        }
    }
}