<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>成功提交订单</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/style.css" />
<script src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/js/com.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user.js"></script>
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
		<c:if test="${isLogin}">
		   <li class="dev"><a href="${pageContext.request.contextPath}/front/buyer/profile.shtml"  title="欢迎">${username}</a></li>
	       <li class="dev"><a href="javascript:void(0)" onclick="logout()" title="退出">[退出]</a></li>
		</c:if>
		<c:if test="${!isLogin}">
		  <li class="dev"><a href="javascript:void(0)" onclick="login()"  title="登陆">[登陆]</a></li>
		  <li class="dev"><a href="javascript:void(0)" onclick="register()" title="免费注册">[免费注册]</a></li>
		</c:if>
		<li class="dev"><a href="javascript:void(0)" onclick="myOrder()" title="我的订单">我的订单</a></li>
		<li class="dev"><a href="#" title="在线客服">在线客服</a></li>
		<li class="dev after"><a href="#" title="English">English</a></li>
	</ul>
</div></div>

<ul class="ul step st3_3">
<li title="1.我的购物车">1.我的购物车</li>
<li title="2.填写核对订单信息">2.填写核对订单信息</li>
<li title="3.成功提交订单" class="here">3.成功提交订单</li>
</ul>

<div class="w ofc case">

	<div class="confirm">
		<div class="tl"></div><div class="tr"></div>
		<div class="ofc">
			
			<p class="okMsg">您的订单已成功提交，完成支付后，我们将立即发货！</p>

			<table cellspacing="0" class="tab tab5" summary="">
			<tbody>
			<tr>
				<th>您的订单号</th>
				<td><var class="blue"><b>${order.oid}</b></var></td>
				<th>应付现金</th>
				<td><var class="red"><b>￥${order.totalPrice}</b></var>&nbsp;元</td>
				<th>支付方式</th>
				<td>${order.paymentWayName}</td>
			</tr>
			<tr>
				<th>配送方式</th>
				<td>快递</td>
				<th>预计到货时间</th>
				<td><fmt:formatDate value="${order.arrivalDate}" pattern="yyyy年MM月dd日" /> </td>
				<th>付款方式</th>
				<td>${order.paymentCashName}</td>
			</tr>
			<tr>
				<th>送货时间</th>
				<td>${order.deliveryName}</td>
				<th>支付状态</th>
				<td>${order.isPaiyName}</td>
				<th>订单状态</th>
				<td>${order.stateName}</td>
			</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>