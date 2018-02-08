<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的购物车</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/style.css" />
<script src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/js/com.js"></script>
<script src="${pageContext.request.contextPath}/res/common/js/xiu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user.js"></script>
<script type="text/javascript">

$(function(){
	allCheckBuyCart("skuIds",true);
});

//包装全选/只选择几个的
function allCheckBuyCart(tagName,status){
	allcheckBox(tagName,status);
	//获取选中的skuId
	//根据skuid（多个skuid以逗号分隔）获取用户选中的商品构成一个新的购物车
	//结算时，从购物车中删除已经购买的商品项目
	//商品数量/商品金额/运费/商品总额
	balanceChecked(tagName);
}

function checkHeaderBuyCart(childTagName,headTagName){
	checkHead(childTagName,headTagName);
	balanceChecked(childTagName);
}

//获取用户选中需要结算的商品价格，运费，等信息
function balanceChecked(tagName){
	 var array = $("input[name='"+tagName+"']:checked"); 
	 
 	 if(array.length<=0){
 		 $("#productAmount").html(0);
		 $("#productPrice").html(0.00);
		 $("#fee").html(0.00);
		 $("#totalPrice").html(0.00);
	  	 
  		 return;
	 }
	 
     url = '${pageContext.request.contextPath}/front/shopping/showBalance.shtml';

       //拼接id
 	 var skuIds = "";
 	 for(var i=0,len=array.length;i<len;i++){
 		  skuIds = skuIds+array[i].value+",";
 	   }
 	   skuIds = skuIds.slice(0,skuIds.length-1);
	
       ajax(skuIds,url,"POST",function(data){
			var buyCart = eval(data);
			var buyItems = buyCart.buyItems;
			//productAmount/productPrice/fee/totalPrice
			 var account = 0;
			 var productPrice = 0.00;
			 var fee = 0.00;
			 var totalPrice = 0.00;
		  	
		  	 for(var i=0;i<buyItems.length;i++){
		  		
		  		account += buyItems[i].account;
		  		productPrice += buyItems[i].sku.skuPrice*buyItems[i].account;
		  		
		  		fee += buyItems[i].sku.deliveFee;
		  	 }
		  	 
		  	 if(productPrice>=69.00){
		  		fee = 0.00;
		  	 }
		  	 totalPrice = productPrice + fee;
		  	 
		  	 //设置值
		  	 $("#productAmount").html(account);
			 $("#productPrice").html(productPrice);
			 $("#fee").html(fee);
			 $("#totalPrice").html(totalPrice);
		  	 
       });
}


//结算
function trueBuy(){
	//获取选中的菜单项
    var array = $("input[name='skuIds']:checked"); 
	
    if(array.length<=0){
         alert("没有商品被选中结算！！！");	  	 
 		 return;
	 }
	
    //拼接id
 	var skuIds = "";
 	for(var i=0,len=array.length;i<len;i++){
 	    skuIds = skuIds+array[i].value+",";
 	}
 	skuIds = skuIds.slice(0,skuIds.length-1);
 	
 	//跳转到后台的controller层
 	window.location.href = "${pageContext.request.contextPath}/front/buyer/productOrderUI.shtml?skuIds="+skuIds;
}

//减少购物车数量
function subProductAmount(skuId){
	//获取当前商品的数量
	var num = parseInt($("#num"+skuId).val());
	//如果为1 则提示是否删除，走删除该商品项
	if(num==1){
		if(confirm("确定要删除该商品项吗？")){
			window.location.href = "${pageContext.request.contextPath}/front/shopping/deleteBuyItem.shtml?skuId="+skuId;
		}
	}
	//大于1  则商品项减一
	var url = "${pageContext.request.contextPath}/front/shopping/subBuyItem.shtml";
	ajax(skuId,url,"POST",function(data){
		$("#num"+skuId).val(num-1);
	 });
	
}
    
//添加商品数量
function addProductAmount(skuId,buyLimit,stockInventory){
	//获取当前商品的数量
	var num = parseInt($("#num"+skuId).val())+1;
	//获得商品的库存，购买限制
	var url = "${pageContext.request.contextPath}/front/shopping/addBuyItem.shtml";
	ajax(skuId,url,"POST",function(data){
		$("#num"+skuId).val(num);
		
		if(num > buyLimit){
			$("#num"+skuId).val(buyLimit);
		}
		
		if(num > stockInventory){
			$("#num"+skuId).val(buyLimit);
		}
		
	 });
}

//清空购物车
function clearCart(){
	window.location.href="${pageContext.request.contextPath}/front/shopping/clearCart.shtml";
}

//删除商品
function delProduct(skuId){
	if(confirm("确定要删除该商品项吗？")){
		window.location.href = "${pageContext.request.contextPath}/front/shopping/deleteBuyItem.shtml?skuId="+skuId;
	}
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
<c:if test="${fn:length(buyCart.buyItems) > 0}">
<ul class="ul step st3_1">
<li title="1.我的购物车" class="here">1.我的购物车</li>
<li title="2.填写核对订单信息">2.填写核对订单信息</li>
<li title="3.成功提交订单">3.成功提交订单</li>
</ul>
<div class="w ofc case">
		<div class="confirm">
			<div class="tl"></div><div class="tr"></div>
			<div class="ofc pb40">
	
				<div class="page">
					<b class="l f14 blue pt48">
						我挑选的商品：
					</b>
				</div>
				<table cellspacing="0" class="tab tab4" summary="">
				<thead>
				<tr>
				<th ><input type="checkbox" name="header"  checked="checked"  onclick="allCheckBuyCart('skuIds',this.checked)"/></th>
				<th class="wp">商品</th>
				<th>单价（元）</th>
				<th>数量</th>
				<th>操作</th>
				</tr>     
				</thead>
				<tbody>
				    <c:forEach items="${buyCart.buyItems}" var="buyItem" >
						<tr>
						   <td><input type="checkbox" value="${buyItem.sku.id}" name="skuIds" onclick="checkHeaderBuyCart('skuIds','header')"/></td>
							<td class="nwp pic">
								<ul class="uls">
									<li>
										<a class="pic" title="${buyItem.sku.product.name}" href="javascript:void(0)"><img alt="${buyItem.sku.product.name}" src="${baseUrl}${buyItem.sku.skuImg}"></a>
										<dl>
											<dt><a title="${buyItem.sku.product.name}" href="javascript:void(0)"> ${buyItem.sku.product.name}--${buyItem.sku.color.name}--${buyItem.sku.size}</a></dt>
											<dd><span class="red"><c:if test="${buyItem.sku.skuType==1}">普通</c:if><c:if test="${buyItem.sku.skuType==0}">赠品</c:if></span>
												<p class="box_d bg_gray2 gray"><a title="${buyItem.sku.typeName}" href="#">${buyItem.sku.typeName}</a><br></p>
											</dd>
										</dl>
									</li>
								</ul>
							</td>
							<td>${buyItem.sku.skuPrice}</td>
							<td>
							    <a onclick="subProductAmount('${buyItem.sku.id}')" class="inb arr" title="减" href="javascript:void(0);">-</a>
							        <input type="text" id="num${buyItem.sku.id}" readonly="readonly" value="${buyItem.account}" name="" size="1" class="txts">
							    <a onclick="addProductAmount('${buyItem.sku.id}','${buyItem.sku.skuUpperLimit}','${buyItem.sku.stockInventory}')" class="inb arr" title="加" href="javascript:void(0);">+</a>
							 </td>
							<td class="blue"><a onclick="delProduct('${buyItem.sku.id}')" title="删除" href="javascript:void(0);">删除</a></td>
						</tr>
				    </c:forEach>
				</tbody>
				</table>
				<div class="page">
					<span class="l">
						<input type="button" onclick="window.open('${pageContext.request.contextPath}/html/product/${buyCart.productId}.html?isLogin=${isLogin}&username=${username}')" class="hand btn100x26c" title="继续购物" value="继续购物">
						<input type="button" onclick="clearCart()" class="hand btn100x26c" title="清空购物车" value="清空购物车">
					</span>
					<span class="r box_gray">
						<dl class="total">
							<dt>购物车金额小计：<cite>(共<var id="productAmount">${buyCart.productAmount}</var>个商品)</cite></dt>
							<dd><em class="l">商品金额：</em>￥<var id="productPrice">${buyCart.productPrice}</var>元</dd>
							<dd><em class="l">运费：</em>￥<var id="fee">${buyCart.deliveFee}</var>元</dd>
							<dd class="orange"><em class="l">应付总额：</em>￥<var id="totalPrice">${buyCart.totalPrice}</var>元</dd>
							<dd class="alg_c"><input type="button" onclick="trueBuy();" class="hand btn136x36a" value="结算" id="settleAccountId"></dd>
						</dl>
					</span>
				</div>
			</div>
		</div>
   </div>
</c:if>
  <c:if test="${fn:length(buyCart.buyItems) <= 0}">
	<div class="w ofc case" style="display: block;">
		<div class="confirm">
			<div class="tl"></div><div class="tr"></div>
			<div class="ofc pb40" style="text-align: center;height: 200px;margin-top: 80px">
	       		 <a href="${pageContext.request.contextPath}" style="color: red;font-size: 30px;">去首页</a>挑选喜欢的商品
			</div>
		</div>
	</div>
</c:if>
<div class="mode">
	<div class="tl"></div><div class="tr"></div>
	<ul class="uls">
		<li class="first">
			<span class="guide"></span>
			<dl>
			<dt title="购物指南">购物指南</dt>
			<dd><a href="#" title="购物流程">购物流程</a></dd>
			<dd><a href="#" title="购物流程">购物流程</a></dd>
			<dd><a href="#" target="_blank" title="联系客服">联系客服</a></dd>
			<dd><a href="#" target="_blank" title="联系客服">联系客服</a></dd>
			</dl>
		</li>
		<li>
			<span class="way"></span>
			<dl>
			<dt title="支付方式">支付方式</dt>
			<dd><a href="#" title="货到付款">货到付款</a></dd>
			<dd><a href="#" title="在线支付">在线支付</a></dd>
			<dd><a href="#" title="分期付款">分期付款</a></dd>
			<dd><a href="#" title="分期付款">分期付款</a></dd>
			</dl>
		</li>
		<li>
			<span class="help"></span>
			<dl>
			<dt title="配送方式">配送方式</dt>
			<dd><a href="#" title="上门自提">上门自提</a></dd>
			<dd><a href="#" title="上门自提">上门自提</a></dd>
			<dd><a href="#" title="上门自提">上门自提</a></dd>
			<dd><a href="#" title="上门自提">上门自提</a></dd>
			</dl>
		</li>
		<li>
			<span class="service"></span>
			<dl>
			<dt title="售后服务">售后服务</dt>
			<dd><a href="#" target="_blank" title="售后策略">售后策略</a></dd>
			<dd><a href="#" target="_blank" title="售后策略">售后策略</a></dd>
			<dd><a href="#" target="_blank" title="售后策略">售后策略</a></dd>
			<dd><a href="#" target="_blank" title="售后策略">售后策略</a></dd>
			</dl>
		</li>
		<li>
			<span class="problem"></span>
			<dl>
			<dt title="特色服务">特色服务</dt>
			<dd><a href="#" target="_blank" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="#" target="_blank" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="#" target="_blank" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="#" target="_blank" title="夺宝岛">夺宝岛</a></dd>
			</dl>
		</li>
	</ul>
</div>
</body>
</html>