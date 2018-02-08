//用户相关的js

//登陆
function login(){
	window.location.href = "../front/user/login.shtml?returnUrl="+window.location.href;
}
//登出
function logout(){
	window.location.href = "../front/user/logout.shtml?returnUrl="+window.location.href;
}

//注册
function register(){
	alert("功能暂未开放....");
}