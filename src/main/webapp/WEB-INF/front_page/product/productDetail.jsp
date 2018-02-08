<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<title>新巴巴运动网-商品详情页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/style.css" />
<script src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/com.js"></script>
<style type="text/css">
.changToRed {
	border: 2px solid #e4393c;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}
.changToWhite {
	border: 1px solid #ccc;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}
.not-allow {
	cursor: not-allowed;
	color: #999;
	border: 1px dashed #ccc;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}
</style>
</head>
<script type="text/javascript">
  var searchSuggest = null;
  //页面加载完毕后 调用该函数
  $(function(){
	 //显示用户登陆信息
	 showUserLoginStatusIndfo(getSearch());
	 //默认选中第一个颜色
	 $("#colors a:first").trigger("click");  //颜色的第一个a标签触发单击事件
	 //设置搜索框
	
     searchSuggest = new oSearchSuggest(sendKeyWordToBack);
	 
  });
//获取传递到改静态页面的参数信息
function getSearch(){
	var url = location.search;  //获取请求的请求参数 即 ？isLogin=aaa&username=bbb

	//获取的请求参数（在参数未知的情况下推荐使用这种方式）
	var params = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1)　 //去掉?号
			strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {　
			params[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			
		}
	}
	//console.log(params["isLogin"]);
	//console.log(params["username"]);
	return params;
}  

//显示用户的登录状态信息 
function showUserLoginStatusIndfo(params){
	var isLogin = params["isLogin"];
	var username = params["username"];
	
	//用户登录
	if(isLogin=="true"){
		//设置用户信息
		$("#username").html(username);
		//隐藏未现实的登录页面
		$("#nologin").css("display","none");
		//显示用户登录的页面
		$("#logined").css("display","inline");
	}else if(isLogin=="false"){//未登录
		//隐藏用户登录页面
		$("#logined").css("display","none");
		//显示用户未登录的页面
		$("#nologin").css("display","inline");
	}
	
	
}
//使用ajax获取搜索数据
function sendKeyWordToBack(keyword) {
	$.ajax(
		{ 
		 url:'${pageContext.request.contextPath}/front/productSearch.shtml',
		 type:"POST",
		 contentType:"application/json",
		 data:keyword,
		 dataType:"json",
		 success:function(data){
			 var aData = [];
			 for(i in data){
				aData.push('<span>'+data[i]+'</span>');
		     }
			//将返回的数据传递给实现搜索输入框的输入提示js类 
			searchSuggest.dataDisplay(aData);
		 },
		 error:function(error){
			 console.log("ajax 失败");
		 }
		}
	);
}
 
 
//单击搜索商品的搜索按钮
function getSearchProduct(){
		var keyword = $('#gover_search_key').val();
		
		window.location.href="${pageContext.request.contextPath}/front/getproduct.shtml?keyword="+keyword;
	}

//登陆
function login(){
	window.location.href = "${pageContext.request.contextPath}/front/user/login.shtml?returnUrl="+window.location.href;
}
//登出
function logout(){
	window.location.href = "${pageContext.request.contextPath}/front/user/logout.shtml?returnUrl="+window.location.href;
}
 
//加入购物车
function addCart(){
	window.location.href= "${pageContext.request.contextPath}/front/user/shopping/shoppCartUI.shtml";
	alert("添加购物车成功!");
}
//立即购买
function buy(){
	window.location.href='cart.jsp';
}
 //商品颜色
 var colorId = null;
 //购买限制
 var buyLimit;
 
 //选中颜色
 function colorToRed(target,id){
     colorId =	id;
	 //清除所有的边框颜色
	 $("#colors a").each(function(){
		 $(this).attr("class","changToWhite");
	 });
	 //设置当前点击的a标签边框变红
	 $(target).attr("class","changToRed");
	 
	 //设置尺码(可选 不可选) 根据颜色id 查询对应的sku,判断sku 分别为 ‘S,M,L.XXL’ 判断是否存在 设置不同的class
	 
	 //先清除尺寸的所有边框颜色
	 $("#sizes a").each(function(){
		 $(this).attr("class","not-allow");
	 });
	 var flag = 0;
	 <#list skus as sku>
	   if('${sku.colorId}'==id){  //该判断条件 会根据后台具有多少类型的尺寸，则进入几次
           if(flag==0){
             //默认选中可用的第一个尺寸 并设置对应该sku的其他信息如市场价 库存，购买限制  显示的图片（扩大镜）
		     $("#" + '${sku.size}').attr("class","changToRed");
             flag = 1;
             //巴巴价   
             $("#price").html("￥" + '${sku.skuPrice}');
             //市场价
             $("#mprice").html("￥" + '${sku.marketPrice}');
             //运费
             $("#fee").html('${sku.deliveFee}');
             //库存
             $("#stock").html('${sku.stockInventory}');
             //显示图片 skuImg/showImg
             $("#skuImg").attr("src","${baseUrl}${sku.skuImg}");
             $("#showImg").attr("href","${baseUrl}${sku.skuImg}");
             //重新加载扩大镜
             $('.cloud-zoom, .cloud-zoom-gallery').CloudZoom();
             //skuId 
             //skuId = '${sku.id}';
             
             //限购
             buyLimit = '${sku.skuUpperLimit}';
           }else{
		      //要给所有可点击的size设置为白色边框
        	   $("#" + '${sku.size}').attr("class","changToWhite");
           }
	   }  
	</#list>
 }
    
    //选中尺寸
    function sizeToRed(target,id){
      //清除所有样式(除去选中的)
     //判断class为not-allow(当前用户如果点击的是not-allow 则直接返回)
     var clazz = $(target).attr("class");
     if(clazz=="not-allow"){
    	 return ;
     }
     //将（除不可点之外的所有尺寸 清楚选中样式）
 	 $("#sizes a").each(function(){
 		 if($(this).attr("class")!="not-allow"){
    		 $(this).attr("class","changToWhite");
 		 }
	 });
	 //设置当前点击的a标签边框变红
	 $(target).attr("class","changToRed");
	 
      //选中指定的样式 设置相关信息
	<#list skus as sku>
	     if (colorId=="${sku.colorId}" && id=="${sku.size}"){
	    	 $("#price").html("￥" + '${sku.skuPrice}');
             //市场价
             $("#mprice").html("￥" + '${sku.marketPrice}');
             //运费
             $("#fee").html('${sku.deliveFee}');
             //库存
             $("#stock").html('${sku.stockInventory}');
             //显示图片 skuImg/showImg
             $("#skuImg").attr("src","${baseUrl}${sku.skuImg}");
             $("#showImg").attr("href","${baseUrl}${sku.skuImg}");
             //重新加载扩大镜
             $('.cloud-zoom, .cloud-zoom-gallery').CloudZoom();
             //skuId
             //skuId = '${sku.id}';
             
             //限购
             buyLimit = '${sku.skuUpperLimit}';
	     }
    </#list>
      
    }
    
    //购买数量减一
    function subNum(){
    	var num = $("#num").val();
    	if(num==1){
    		return;
    	}
    	$("#num").val(--num);
    }
    
    //购买数量加一
    function addNum(){
       var num = $("#num").val();
       if(num==buyLimit){
    	 alert("此商品只能购买"+buyLimit+"件");
   		 return;
       }
   	   $("#num").val(++num);
    }
    
    //输入文本框设置
    function checkProductNum(){
    	var num = $("#num").val();
    	//输入内容不能为null或者""
    	if(num==null||$.trim(num)==""){
    		alert("购买件数不能为空");
    		return ;
    	}
    	//输入内容必须为正整数（负数和小数均不可以）
    	if(!checkPositiveNumer(num)){
    		alert("购买件数必须为数字，且必须是正整数");
    		return;
    	}
    	//输入的数字必能超过购买限制
    	if(num>buyLimit){
    		alert("购买件数不能超过购买限制数量");
    		return;
    		
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
		<li class="dev">
			您好,欢迎来到新巴巴运动网！
		</li>
	<span id="logined" style="display:none;" > <!-- 已经登陆 -->
	  <li class="dev"><a href="${pageContext.request.contextPath}/front/buyer/profile.shtml"  id="username" title="欢迎"></a></li>
      <li class="dev"><a href="javascript:void(0)" onclick="logout()" title="退出">[退出]</a></li>
	</span>
	<span id="nologin" style="display:none;" >
	  <li class="dev"><a href="javascript:void(0)" onclick="login()"  title="登陆">[登陆]</a></li>
	  <li class="dev"><a href="javascript:void(0)" onclick="register()" title="免费注册">[免费注册]</a></li>
	</span>
	<li class="dev"><a href="javascript:void(0)" onclick="myOrder()" title="我的订单">我的订单</a></li>
	<li class="dev"><a href="#" title="在线客服">在线客服</a></li>
	<li class="dev after"><a href="#" title="English">English</a></li>
	</ul>
</div></div>
<div class="w loc">
	<div class="h-title">
		<div class="h-logo"><a href="#">
		 <img src="${pageContext.request.contextPath}/res/img/pic/logo-1.png" /></a></div>
	    <div class="h-search">
	      	<input type="text" id="gover_search_key" placeholder="请输入关键词直接搜索"  />
	        <div class="h-se-btn"><a href="javascript:void(0);" onclick="getSearchProduct();" >搜索</a></div>
	    </div>
	    <div class="search_suggest" id="gov_search_suggest">
			<ul >
			</ul>
		</div>
	</div>
	<dl id="cart" class="cart r">
		<dt><a href="#" title="结算">结算</a>购物车:<b id="">123</b>件</dt>
		<dd class="hidden">
			<p class="alg_c hidden">购物车中还没有商品，赶紧选购吧！</p>
			<h3 title="最新加入的商品">最新加入的商品</h3>
			<ul class="uls">
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
			</ul>
			<div>
				<p>共<b>5</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;共计<b class="f20">¥640.00</b></p>
				<a href="#" title="去购物车结算" class="inb btn120x30c">去购物车结算</a>
			</div>
		</dd>
	</dl>
</div>

<div class="w ofc mt">
	<div class="l">
		<div class="showPro">  
			<div class="big"><a id="showImg" class="cloud-zoom" href="${baseUrl}${product.img.url }" rel="adjustX:10,adjustY:-1"><img id="skuImg" alt="${product.name}" src="${baseUrl}${product.img.url }"></a></div>
		</div>
	</div>
	<div class="r" style="width: 640px">
		<ul class="uls form">  
			<li><h2>${product.name}</h2></li>   
			<li><label>巴  巴 价：</label><span class="word"><b class="f14 red mr" id="price" >￥128.00</b>(市场价:<del id="mprice">￥150.00</del>)</span></li>
			<li><label>商品评价：</label><span class="word"><span class="val_no val3d4" title="4分">4分</span><var class="blue">(已有888人评价)</var></span></li>
			<li><label>运　　费：</label><span class="word" id="fee">10元</span></li>
			<li><label>库　　存：</label><span class="word" id="stock">100</span><span class="word" >件</span></li>
			<li><label>选择颜色：</label>
				<div id="colors" class="pre spec">
				    <#list colorList as color>
					    <a id="${color.id}" onclick="colorToRed(this,${color.id})" href="javascript:void(0)" title="西瓜红" class="changToWhite"><img width="25" height="25" data-img="1" src="${baseUrl}${color.imgUrl}" alt="西瓜红 "><i>${color.name}</i></a>
				    </#list>
				</div>
			</li>
			<li id="sizes"><label>尺　　码：</label>
						<a href="javascript:void(0)" onclick="sizeToRed(this,'S')" class="not-allow"  id="S">S</a>
						<a href="javascript:void(0)" onclick="sizeToRed(this,'M')" class="not-allow"  id="M">M</a>
						<a href="javascript:void(0)" onclick="sizeToRed(this,'L')" class="not-allow"  id="L">L</a>
						<a href="javascript:void(0)" onclick="sizeToRed(this,'XL')" class="not-allow"  id="XL">XL</a>
						<a href="javascript:void(0)" onclick="sizeToRed(this,'XXL')" class="not-allow"  id="XXL">XXL</a>
			</li>
			<li><label>我 要 买：</label>
				<a id="sub" onclick="subNum();" class="inb arr" style="border: 1px solid #919191;width: 10px;height: 10px;line-height: 10px;text-align: center;" title="减" href="javascript:void(0);" >-</a>
				<input id="num" type="text" value="1" name="" size="1" onblur="checkProductNum();">
				<a id="add" onclick="addNum();" class="inb arr" style="border: 1px solid #919191;width: 10px;height: 10px;line-height: 10px;text-align: center;" title="加" href="javascript:void(0);">+</a></li>
			
		    <li class="submit"><input type="button" value="" class="hand btn138x40" onclick="buy();"/><input type="button" value="" class="hand btn138x40b" onclick="addCart()"/></li>
		</ul>
	</div>
</div>
<div class="w ofc mt">
<div class="l wl">
		<h2 class="h2 h2_l"><em title="销量排行榜">销量排行榜</em><cite></cite></h2>
		<div class="box bg_white">
			<ul class="uls x_50x50">
			   <#list salesProducts as product >
			     <li>
			        <#if (product_index<=3) >
					  <var class="sfont">${product.num}</var>
			   	    </#if>
					<a href="${pageContext.request.contextPath}/html/product/${product.id}.html" title="${product.keywords}" target="_blank" class="pic"><img src="${baseUrl}${product.img.url}" alt="${product.name}" /></a>
					<dl>
						<!-- dt 8个文字+... -->
						<dt><a href="#" title="${product.keywords}" target="_blank">${product.name}</a></dt>
						<dd class="orange">￥${product.minPrice}~ ￥${product.maxPrice}</dd>
					</dl>
				 </li>
			  </#list>
				
			</ul>
		</div>
	<h2 class="h2 h2_l mt"><em title="我的浏览记录">我的浏览记录</em><cite></cite></h2>
	<div class="box bg_white">
		<ul class="uls x_50x50">
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
		</ul>
	</div>
	
	<h2 class="h2 h2_l mt"><em title="商家精选">商家精选</em><cite></cite></h2>
	<img src="/res/img/pic/ad200x75.jpg" alt="" />
</div>
<div class="r wr">
		<h2 class="h2 h2_ch mt"><em>
			<a href="javascript:void(0);" title="商品介绍" rel="#detailTab1" class="here">商品介绍</a>
			<a href="javascript:void(0);" title="规格参数" rel="#detailTab2">规格参数</a>
			<a href="javascript:void(0);" title="包装清单" rel="#detailTab3">包装清单</a></em><cite></cite></h2>
		<div class="box bg_white ofc">
			<div id="detailTab1" class="detail">
				${product.description}
			</div>
			
			<div id="detailTab2" style="display:none">
					<div class="mc">
						<div class="item-detail item-detail-copyright">
							<div class="serve-agree-bd">
								<dl>
									<dt>
										<i class="goods"></i> <strong>厂家服务</strong>
									</dt>
									<dd>
										本产品全国联保，享受三包服务，质保期为：三十天质保<br>
									</dd>

									<dt>
										<i class="goods"></i> <strong>巴巴运动承诺</strong>
									</dt>
									<dd>
										巴巴运动平台卖家销售并发货的商品，由平台卖家提供发票和相应的售后服务。请您放心购买！<br>
										注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！
									</dd>

									<dt>
										<i class="goods"></i><strong> 正品行货 </strong>
									</dt>
									<dd>巴巴运动商城向您保证所售商品均为正品行货，巴巴运动自营商品开具机打发票或电子发票。</dd>
									<dt>
										<i class="unprofor"></i><strong>全国联保</strong>
									</dt>
									<dd>
										凭质保证书及巴巴运动商城发票，可享受全国联保服务（奢侈品、钟表除外；奢侈品、钟表由巴巴运动联系保修，享受法定三包售后服务），与您亲临商场选购的商品享受相同的质量保证。巴巴运动商城还为您提供具有竞争力的商品价格和<a
											href="//help.jd.com/help/question-892.html" target="_blank">运费政策</a>，请您放心购买！
										<br>
										<br>注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！
									</dd>
									<dt>
										<i class="no-worries"></i><strong>无忧退换货</strong>
									</dt>
									<dd class="no-worries-text">
										客户购买巴巴运动自营商品7日内（含7日，自客户收到商品之日起计算），在保证商品完好的前提下，可无理由退货。（部分商品除外，详情请见各商品细则）
									</dd>
								</dl>
							</div>
							<div id="state">
								<strong>权利声明：</strong><br>巴巴运动上的所有商品信息、客户评价、商品咨询、网友讨论等内容，是巴巴运动重要的经营资源，未经许可，禁止非法转载使用。
								<p>
									<b>注：</b>本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。
								</p>
								<br> <strong>价格说明：</strong><br>
								<p>
									<b>巴巴运动价：</b>巴巴运动价为商品的销售价，是您最终决定是否购买商品的依据。
								</p>
								<p>
									<b>划线价：</b>商品展示的划横线价格为参考价，该价格可能是品牌专柜标价、商品吊牌价或由品牌供应商提供的正品零售价（如厂商指导价、建议零售价等）或该商品在巴巴运动平台上曾经展示过的销售价；由于地区、时间的差异性和市场行情波动，品牌专柜标价、商品吊牌价等可能会与您购物时展示的不一致，该价格仅供您参考。
								</p>
								<p>
									<b>折扣：</b>如无特殊说明，折扣指销售商在原价、或划线价（如品牌专柜标价、商品吊牌价、厂商指导价、厂商建议零售价）等某一价格基础上计算出的优惠比例或优惠金额；如有疑问，您可在购买前联系销售商进行咨询。
								</p>
								<p>
									<b>异常问题：</b>商品促销信息以商品详情页“促销”栏中的信息为准；商品的具体售价以订单结算页价格为准；如您发现活动商品售价或促销信息有异常，建议购买前先联系销售商咨询。
								</p>

							</div>
						</div>
					</div>

				</div>

			<div id="detailTab3" class="detail" style="display:none">

	<pre class="f14">
		${product.packageList}
	</pre>

			</div>
		</div>

	</div>
</div>

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