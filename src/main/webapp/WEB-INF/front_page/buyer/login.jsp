<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录_新巴巴运动网</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/style.css" />
<script src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/js/com.js"></script>
<script type="text/javascript">
   //登录操作，进行校验
   function check(){
	   
	   var username = $("#username").val();
	   var password = $("#password").val();
	   var captcha = $("#captcha").val();
	   var error = $("#errorName");
	   error.css("display","none");
	   //用户名不能为null
	   if(username==null||username==""){
		   error.css("display","block").append("请填写用户名!");
		   return false;
	   }
	   //用户名必须为指定的格式  (以后考虑)
	   //密码不能为null
	   if(password==null||password==""){
		   error.css("display","block").append("请填写密码!");
		   return false;
	   }
	   //密码必须为指定的格式
	   //验证不能为null
	   if(captcha==null||captcha==""){
		   error.css("display","block").text("请填写验证码！");
		   return false;
	   }
	   return true;
   }
  

</script>
</head>
<body>
<div class="bar"><div class="bar_w">
	<p class="l">
		<span class="l">
			收藏本网站！北京<a href="#" title="更换">[更换]</a>
		</span>
	</p>
	<ul class="r uls">
	<li class="dev">您好,欢迎来到新巴巴运动网！</li>
	<li class="dev"><a href="#" title="在线客服">在线客服</a></li>
	<li class="dev after"><a href="#" title="English">English</a></li>
	</ul>
</div></div>
<div class="w loc">
	<div class="h-title" id="logo">
		<div class="h-logo l"><img src="${pageContext.request.contextPath}/res/img/pic/logo-1.png"/></div>
		<div class="l" style="margin: 13px 10px;font-size: 20px;font-family: '微软雅黑';letter-spacing: 2px">欢迎登录</div>
	</div>
</div>
<div class="sign">
	<div class="l ad420x205"><a href="#" title="title"><img src="${pageContext.request.contextPath}/res/img/pic/ppp0.jpg" width="400" height="400"/></a></div>
	<div class="r">
		<h2 title="登录新巴巴运动网">登录新巴巴运动网</h2>
		<form id="jvForm" action="${pageContext.request.contextPath}/front/user/index.shtml" method="post"   onsubmit="return check()">
			<input type="hidden" name="returnUrl" value="${returnUrl}"/>
			<ul class="uls form">
			<!-- 后台的校验错误放置到该标签下 -->
			<c:if test="${not empty error }">
			   <li class="errorTip" style="display:block; clear:both;">${error}</li>
			</c:if>
			<!-- error为null,则为前台校验 -->
			<c:if test="${empty error }">
    			<li id="errorName" class="errorTip" style="display:none; clear:both;">${error}</li>
			</c:if>
			<li><label for="username">用户名：</label>
				<span class="bg_text">
					<input type="text" id="username" name="username" maxLength="100" value="${buyer.username}" />
				</span>
			</li>
			<li><label for="password">密　码：</label>
				<span class="bg_text">
					<input type="password" id="password" name="password" maxLength="32" value="${buyer.password}"/>
				</span>
			</li>
			<li><label for="captcha">验证码：</label>
				<span class="bg_text small">
					<input class="logininput" type="text" id="captcha" name="captcha" maxLength="7"/>
				</span>
				<img src="${pageContext.request.contextPath}/captcha.shtml" onclick="this.src='${pageContext.request.contextPath}/captcha.shtml?d='+new Date()" class="code" alt="换一张" /><a href="javascript:void(0);" onclick="$('.code').attr('src','${pageContext.request.contextPath}/captcha.shtml?d='+new Date())" title="换一张">换一张</a></li>
			<li><label for="">&nbsp;</label><input type="submit" value="登 录" class="hand btn66x23"/><a href="#" title="忘记密码？">忘记密码？</a></li>
			</ul>
		</form>
	</div>
</div>
</body>
</html>