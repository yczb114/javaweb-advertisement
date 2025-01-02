// 获取模态窗口元素
var modal = document.getElementById("adModal");

// 获取关闭按钮元素
var span = document.getElementsByClassName("close")[0];

// 点击关闭按钮或外部区域关闭模态
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// 页面加载时打开模态
window.onload = function() {
    modal.style.display = "block";
}