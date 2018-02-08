//县区下拉框改变时触发
function checkPCTBlur(){
	//省市县三级  校验如果选中的是select的第一个option 则判断用户没有选中省市县其中的一个
	 if(checkPCT("provinces")){
		   showCheckInfo("请选择省份",true,"pctTip");
		   return false;
	 }
	 if(checkPCT("citys")){
		   showCheckInfo("请选择城市",true,"pctTip");
		   return false;
	  }
	
	 if(checkPCT("towns")){
		   showCheckInfo("请选择县区",true,"pctTip");
		   return false;
	  }
	 
	 showCheckInfo("",false,"pctTip");
	 
}


//检验用户名
function checkUserName(){
	var userNameRegex = /^[\u4e00-\u9fa5a-zA-Z0-9\-]{4,20}$/;  //匹配用户名的正则表达式
	//用户名校验
	var username = $("#userName").val();
	if(username==null||$.trim(username)==""){
		showCheckInfo("用户名不能为空",true,"usernameTip");
		return false;
	}
	var result = userNameRegex.test(username);
	if(!userNameRegex.test(username)){
		showCheckInfo("用户名为4-20位字母、数字或中文组成，字母区分大小写",true,"usernameTip");
		return false;
	}
  	  showCheckInfo("",false,"usernameTip");
}
//校验邮箱
function checkEmail(){
	var mailRegex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //邮箱正则表达式
	//邮箱校验
	var email = $("#email").val();
	if(email==null||$.trim(email)==""){
		showCheckInfo("邮箱不能为空",true,"emailTip");
		return false;
	}
	var result = mailRegex.test(email);
	if(!mailRegex.test(email)){
		showCheckInfo("请输入有效的邮箱名",true,"emailTip");
		return false;
	}
	  showCheckInfo("",false,"emailTip");
	
}

//真实姓名校验
function checkRealName(){
	//真实姓名校验
	var realName = $("#realName").val();
	if(realName==null||$.trim(realName)==""){
		showCheckInfo("真实姓名不能为空",true,"realNameTip");
		return false;
	}
	  showCheckInfo("",false,"realNameTip");
	
}

//校验地址
function  checkAddress(){
	var address = $("#address").val();
	if(address==null||$.trim(address)==""){
		showCheckInfo("详细地址不能为空",true,"addressTip");
		return false;
	}
		showCheckInfo("",false,"addressTip");
	
}

//用户信息保存，保存时校验错误
//使用js的正则表达式来校验用户名
function check(){
    //该正则表达式匹配 4-20个含有中文，英文字母（区分大小写）	
	var userNameRegex = /^[\u4e00-\u9fa5a-zA-Z0-9\-]{4,20}$/;  //匹配用户名的正则表达式
	var mailRegex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; //邮箱正则表达式
    
	//用户名校验
	var username = $("#userName").val();
	if(username==null||$.trim(username)==""){
		showCheckInfo("用户名不能为空",true,"usernameTip");
		return false;
	}
	var result = userNameRegex.test(username);
	if(!userNameRegex.test(username)){
		showCheckInfo("用户名为4-20位字母、数字或中文组成，字母区分大小写",true,"usernameTip");
		return false;
	}
	
	showCheckInfo("",false,"usernameTip");
	
	//邮箱校验
	var email = $("#email").val();
	if(email==null||$.trim(email)==""){
		showCheckInfo("邮箱不能为空",true,"emailTip");
		return false;
	}
	var result = mailRegex.test(email);
	if(!mailRegex.test(email)){
		showCheckInfo("请输入有效的邮箱名",true,"emailTip");
		return false;
	}
	
	showCheckInfo("",false,"emailTip");
	
	//真实姓名校验
	var realName = $("#realName").val();
	if(realName==null||$.trim(realName)==""){
		showCheckInfo("真实姓名不能为空",true,"realNameTip");
		return false;
	}
	
	showCheckInfo("",false,"realNameTip");
	//省市县校验
	//省市县三级  校验如果选中的是select的第一个option 则判断用户没有选中省市县其中的一个
	 if(checkPCT("provinces")){
		   showCheckInfo("请选择省份",true,"pctTip");
		   return false;
	 }
	 if(checkPCT("citys")){
		   showCheckInfo("请选择城市",true,"pctTip");
		   return false;
	  }
	
	 if(checkPCT("towns")){
		   showCheckInfo("请选择县区",true,"pctTip");
		   return false;
	  }
	 
	 showCheckInfo("",false,"pctTip");
	//校验
	//真实地址判断文本框是否为null
	var address = $("#address").val();
	if(address==null||$.trim(address)==""){
		showCheckInfo("详细地址不能为空",true,"addressTip");
		return false;
	}
	
	showCheckInfo("",false,"addressTip");
}
/**
 * 保存用户时 进行表单校验时的信息友好化显示
 * message 校验失败后的错误提示信息
 * isError 校验成功或者失败的标识
 * tag 需要将提示信息放置在哪里
 */
function showCheckInfo(message,isError,tagName){
	$("#"+tagName).html(""); //先清空其中的元素
	if(isError){
		var span = $("<span />").attr("class","tip errorTip").html(message);
		$("#"+tagName).append(span); //添加错误校验信息
	}else{
		var span = $("<span />").attr("class","tip okTip").html("&nbsp;");
		$("#"+tagName).append(span); //添加成功校验信息
	}
}

//检查(P（province）C（city））T（town）)
function checkPCT(addrTag){
    //获取select 的第一个option 是否为选中状态	
	return $('#'+addrTag).children('option').first().attr("selected");
}

