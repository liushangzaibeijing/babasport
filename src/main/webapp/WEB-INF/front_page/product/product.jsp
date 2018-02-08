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

<title>新巴巴运动网-电子商城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/style.css" />
<script src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/js/com.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/common/js/xiu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user.js"></script>
<script type="text/javascript">

//关闭筛选条件
function closeQuery(queryFlag){
	
	 var url  ='${pageContext.request.contextPath}/front/productList.shtml?flag=true'
	    
     if(queryFlag=="brand"||${brandId==null}){
		url+='&brandId=&brandName=';
	 }else{
		 url+='&brandId=${brandId}&brandName=${brandName}';
	 }
	 if(queryFlag=='type'||${typeId==null}){
		url+='&typeId=&typeName=';
	 }else{
		url+='&typeId=${typeId}&typeName=${typeName}';
     }
	 if(queryFlag=='price'||${minPrice==null}){
		url+='&minPrice=&maxPrice=';
	 }else{
		url+='&minPrice=${minPrice }&maxPrice=${maxPrice}';
	 }
	 if(queryFlag=='feature'||${featureId==null}){
		url+='&featureId=&featureName=';
	 }else{
		url+='&featureId=${featureId}&featureName=${featureName}';
	 }
	 if(queryFlag=='filPeople'||${filPeople==null}){
			url+='&filPeople=';
	 }else{
			url+='&filPeople=${filPeople}';
	 }
	 
	 console.log(url);
	 window.location.href=url;
}
  //用于存储
  var selectFlag = null;

  //降序排列
  function orderByFn(orderByField){
	  $("#"+orderByField).css("color","#f00");
	  //先判断是否重复按下相同的，如果是相同的话不进行查询操作
	  if(selectFlag==orderByField){
		  return ;
	  }
	  var url = '${pageContext.request.contextPath}/front/productList.shtml'
			  +'?brandId=${brandId}&brandName=${brandName}'
              +'&typeId=${typeId}&typeName=${typeName }'
              +'&minPrice=${minPrice }&maxPrice=${maxPrice}'
              +'&featureId=${featureId}&featureName=${featureName}'
              +'&filPeople=${filPeople}';
              
      //按照销量降序
      if(orderByField=="sales"){
    	  if(selectFlag==null||selectFlag!=orderByField){
    		  selectFlage = "sales";
    	  }
    	  url +='&isSalesDesc=true';
      }
      //按照上架时间降序
	  if(orderByField=="createTime"){
		  if(selectFlag==null||selectFlag!=orderByField){
    		  selectFlage = "createTime";
    	  }
    	  url +='&iscreateTimeDesc=true';
      }
      alert(url);
      window.location.href=url;
  }

  //单击搜索商品的搜索按钮
  function getSearchProduct(){
		var keyword = $('#gover_search_key').val();
		
		window.location.href="${pageContext.request.contextPath}/front/getproduct.shtml?keyword="+keyword;
	}
  
  var searchSuggest = null;
  $(function(){
	  searchSuggest = new oSearchSuggest(sendKeyWordToBack);
	  getBuyCart();
  });
  
  
  //装载购物车数据
  function getBuyCart(){
  	var url = "${pageContext.request.contextPath}/front/shopping/showBuyItemAjax.shtml";
  	
  	ajax(null,url,"POST",function(result){
  		var buyCart = eval(result);
  		var buyItems =  buyCart.buyItems;
  		//判断buycart的buyItem长度是否为0
  		//如果不为0显示购物项
  		if(buyItems.length==0){
  			//不显示购物项 nobuyItem/buyItems/toBuy/title
  			$("#nobuyItem ").css("display","block");
  			$("#buyItems").css("display","none");
  			$("#title").css("display","none");
  			$("#toBuy").css("display","none");
  			$("#count").html("0");
  		}else{
  			loadBuyCart(buyCart,buyItems);
  			//不显示购物项
   			$("#nobuyItem").css("display","none");
   			$("#buyItems").css("display","block");
   			$("#title").css("display","block");
   			$("#toBuy").css("display","block");
  		}
  	
  		
  	});
  	
  }

  //删除后重新装载数据库
  function deleteBuyItem(skuId){
      var url = "${pageContext.request.contextPath}/front/shopping/deleteBuyItemAjax.shtml?skuId="+skuId;
  	
  	ajax(null,url,"POST",function(result){
  		var buyCart = eval(result);
  		var buyItems =  buyCart.buyItems;
  		//判断buycart的buyItem长度是否为0
  		//如果不为0显示购物项
  		if(buyItems.length==0){
  			//不显示购物项 nobuyItem/buyItems/toBuy/title
  			$("#nobuyItem ").css("display","block");
  			$("#buyItems").css("display","none");
  			$("#title").css("display","none");
  			$("#toBuy").css("display","none");
  			$("#count").html("0");
  		}else{
  			loadBuyCart(buyCart,buyItems);
  			//不显示购物项
   			$("#nobuyItem").css("display","none");
   			$("#buyItems").css("display","block");
   			$("#title").css("display","block");
   			$("#toBuy").css("display","block");
  		}
  	
  	});
  }
  

  
  function loadBuyCart(buyCart,buyItems){
	  $("#buyItems").html("");
  	
	var account = 0;
	var productPrice = 0.00;
	var fee = 0.00;
	var totalPrice = 0.00;
  	
  	for(var i=0;i<buyItems.length;i++){
  		
  		account += buyItems[i].account;
  		productPrice += buyItems[i].sku.skuPrice*buyItems[i].account;
  		
  		fee += buyItems[i].sku.deliveFee;
  		//创建li元素
  		var liEle = $("<li />");
  		var imgEle = $("<img />").attr("src","${baseUrl}"+buyItems[i].sku.skuImg).attr("alt",""+buyItems[i].sku.product.name);
  		var aEle =$("<a />").attr("href","${pageContext.request.contextPath}/html/product/"+buyItems[i].sku.productId+".html?isLogin=${isLogin}&username=${username}")
  		        .attr("title",buyItems[i].sku.product.name);
  		
  		aEle.append(imgEle);
  		
  		var a2Ele =$("<a />").attr("href","${pageContext.request.contextPath}/html/product/"+buyItems[i].sku.productId+".html?isLogin=${isLogin}&username=${username}")
            .attr("title",""+buyItems[i].sku.product.name).html(buyItems[i].sku.product.name+"--"+buyItems[i].sku.color.name+"--"+buyItems[i].sku.size);
          
  		var p1Ele = $("<p />").attr("class","dt")
  		p1Ele.append(a2Ele);
  		
  		var p2Ele = $("<p />").attr("class","dd").html(
  				"<b><var>"+buyItems[i].sku.skuPrice+"</var><span>x"+buyItems[i].account+"</span></b>"
  			    +"<a href='javascript:void(0)' onclick='deleteBuyItem("+buyItems[i].sku.id+")'"
  			    +" title='删除' class='del'>删除</a>" 
  		);
  		
  		liEle.append(aEle).append(p1Ele).append(p2Ele);
  		
  		//将其添加到ul中
  		$("#buyItems").append(liEle);
  		
  	}
	if(productPrice>=69.00){
	  		fee = 0.00;
	  	 }
	 totalPrice = productPrice + fee;
  	
	$("#count").html(account);
	$("#toBuy").html("<p>共<b>"+account+"</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;共计<b class='f20'>"+totalPrice+"</b></p>"
					+"<a href='${pageContext.request.contextPath}/front/shopping/showBuyItem.shtml' title='去购物车结算' class='inb btn120x30c'>去购物车结算</a>");
		 
		 
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
<div class="w loc">
	<div class="h-title">
		<div class="h-logo"><a href="http://localhost:8080">
		 <img src="${pageContext.request.contextPath}/res/img/pic/logo-1.png" /></a></div>
	    <div class="h-search">
	      	<input type="text" id="gover_search_key" placeholder="请输入关键词直接搜索"  />
	        <div class="h-se-btn"><a href="javascript:void(0);" onclick="getSearchProduct();" >搜索</a></div>
	    </div>
	    <div class="search_suggest" id="gov_search_suggest">
			<ul>
			</ul>
		</div>
	</div>
	
	<dl id="cart" class="cart r">
		<dt><a href="${pageContext.request.contextPath}/front/shopping/showBuyItem.shtml" title="结算">结算</a>购物车:<b id="count"></b>件</dt>
		 <dd class="hidden">
		      <!-- 购物车没有购物项的时候显示 -->
			  <p class="alg_c " id="nobuyItem">购物车中还没有商品，赶紧选购吧！</p>
			  <!-- 购物车的购物项显示 -->
				<h3 title="最新加入的商品" id="title">最新加入的商品</h3>
				<ul class="uls" id="buyItems">
				 </ul>  
				<div id="toBuy">
				</div>
		</dd>
	</dl>
</div>

<div class="w ofc">
	<div class="l wl">
		<h2 class="h2 h2_l"><em title="销量排行榜">销量排行榜</em><cite></cite></h2>
		<div class="box bg_white">
			<ul class="uls x_50x50">
			   <c:forEach items="${salesProducts}" var="product">
			     <li>
			        <c:if test="${product.num<=3}">
					  <var class="sfont">${product.num}</var>
			   	    </c:if>
					<a href="${pageContext.request.contextPath}/html/product/${product.id}.html?isLogin=${isLogin}&username=${username}" title="${product.keywords}" target="_blank" class="pic"><img src="${baseUrl}${product.img.url}" alt="${product.name}" /></a>
					<dl>
						<!-- dt 8个文字+... -->
						<dt><a href="${pageContext.request.contextPath}/html/product/${product.id}.html?isLogin=${isLogin}&username=${username}"  title="${product.keywords}" target="_blank">${product.name}</a></dt>
						<dd class="orange">￥${product.minPrice}~ ￥${product.maxPrice}</dd>
					</dl>
				 </li>
			   </c:forEach>
				
			</ul>
		</div>

		<h2 class="h2 h2_l mt"><em title="我的浏览记录">我的浏览记录</em><cite></cite></h2>
		<div class="box bg_white">
			<ul class="uls x_50x50">
				<li>
					<a href="#" title="富一代" target="_blank" class="pic"><img src="${pageContext.request.contextPath}/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
					<dl>
						<!-- dt 8个文字+... -->
						<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="富一代" target="_blank" class="pic"><img src="${pageContext.request.contextPath}/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
					<dl>
						<!-- dt 8个文字+... -->
						<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="富一代" target="_blank" class="pic"><img src="${pageContext.request.contextPath}/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
					<dl>
						<!-- dt 8个文字+... -->
						<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="富一代" target="_blank" class="pic"><img src="${pageContext.request.contextPath}/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
					<dl>
						<!-- dt 8个文字+... -->
						<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="富一代" target="_blank" class="pic"><img src="${pageContext.request.contextPath}/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
					<dl>
						<!-- dt 8个文字+... -->
						<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="富一代" target="_blank" class="pic"><img src="${pageContext.request.contextPath}/res/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
					<dl>
						<!-- dt 8个文字+... -->
						<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
			</ul>
		</div>
		
		<h2 class="h2 h2_l mt"><em title="商家精选">商家精选</em><cite></cite></h2>
		<img src="${pageContext.request.contextPath}/res/img/pic/ad200x75.jpg" alt="" />
	</div>
	<div class="r wr">
		<h2 class="h2 h2_r rel"><samp></samp><em title="热卖推荐">&nbsp;&nbsp;&nbsp;热卖推荐</em></h2>
		<div class="box bg_white">
			<ul class="uls i_150x150 x4_150x150">
			   <c:forEach items="${hotProducts}" var="product">
				   <li>
						<a href="${pageContext.request.contextPath}/html/product/${product.id}.html?isLogin=${isLogin}&username=${username}" title="${product.keywords}" target="_blank" class="pic"><img src="${baseUrl}${product.imgUrl}" alt="${product.name}" /></a>
						<dl>
							<dt><a href="${pageContext.request.contextPath}/html/product/${product.id}.html?isLogin=${isLogin}&username=${username}"  title="${product.keywords}" target="_blank">${product.name}</a></dt>
							<dd class="h40">${product.keywords}</dd>
							<dd class="orange">￥${product.minPrice} ~ ￥${product.maxPrice}</dd>
							<dd><a href="${pageContext.request.contextPath}/html/product/${product.id}.html?isLogin=${isLogin}&username=${username}" title="立即抢购" class="inb btn70x21 mr">立即抢购</a></dd>
						</dl>
					</li>
			   </c:forEach>
			</ul>
		</div>

		<h2 class="h2 h2_filter mt"><em title="商品筛选">商品筛选</em><cite><a href="javascript:void(0);" onclick="window.location.href='${pageContext.request.contextPath}/front/productList.shtml'" id="filterRest" title="重置筛选条件">重置筛选条件</a></cite></h2>
			<ul class="uls filter">
				<li><label>已选条件：</label>
				<p class="sel">
				   <c:if test="${!empty brandId }">
						<a href="javascript:void(0);" >
							<em>品牌：</em>${brandName}
							<cite title="关闭此筛选条件" onclick="closeQuery('brand')">X</cite>
						</a>
				   </c:if>
				   <c:if test="${!showPrice}">
						<a href="javascript:void(0);">
							<em>价格：</em>${minPrice}-${maxPrice}
							<cite title="关闭此筛选条件" onclick="closeQuery('price')">X</cite>
						</a>
				   </c:if>
				    <c:if test="${!empty typeId}">
						<a href="javascript:void(0);">
							<em>类型：</em>${typeName}
							<cite title="关闭此筛选条件" onclick="closeQuery('type')" >X</cite>
						</a>
				   </c:if>
				    <c:if test="${!empty featureId}">
						<a href="javascript:void(0);">
							<em>材质：</em>${featureName}
							<cite title="关闭此筛选条件" onclick="closeQuery('feature')" >X</cite>
						</a>
				   </c:if>
				    <c:if test="${!showFilePeople}">
						<a href="javascript:void(0);">
							<em>适用人群：</em>${filPeople}
							<cite title="关闭此筛选条件" onclick="closeQuery('filPeople')" >X</cite>
						</a>
				   </c:if>
				</p>
				</li>
				
				<c:if test="${empty brandId}">
					<li><b>品牌：</b>
					   <p>
						 <a href="javascript:void(0);" title="不限" class="here">不限</a>
						
						 <c:forEach items="${brands}" var="brand">
						   	<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brand.id}&brandName=${brand.name}&typeId=${typeId}&typeName=${typeName }&minPrice=${minPrice }&maxPrice=${maxPrice}&featureId=${featureId}&featureName=${featureName}&filPeople=${filPeople}" 
						   	                                                                    title="${brand.name}">${brand.name}</a>
						</c:forEach>
					   </p>
					</li>
				</c:if>
				<c:if test="${showPrice}">
				  <li><b>价格：</b><p>
					<a href="javascript:void(0);" title="不限" class="here">不限</a>
					<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName}&minPrice=0&maxPrice=79&featureId=${featureId}&featureName=${featureName}&filPeople=${filPeople}"
						   	                                                                   title="1-99">0-79</a>
					<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName }&minPrice=80&maxPrice=199&featureId=${featureId}&featureName=${featureName}&filPeople=${filPeople}" 
						   	                                                                   title="100-199">80-199</a>
					
					<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName }&minPrice=200&maxPrice=299&featureId=${featureId}&featureName=${featureName}&filPeople=${filPeople}"
						   	                                                                   title="200-499">200-299</a>
					
					<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName }&minPrice=300&maxPrice=499&featureId=${featureId}&featureName=${featureName}&filPeople=${filPeople}"
						   	                                                                   title="200-499">300-499</a>
					<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName}&minPrice=500&maxPrice=599&featureId=${featureId}&featureName=${featureName}&filPeople=${filPeople}" 
						   	                                                                    +"title="200-499">500-599</a>
					
					<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName }&minPrice=600&maxPrice=600000&featureId=${featureId}&featureName=${featureName}&filPeople=${filPeople}"
						   	                                                                    title="200-499">600以上</a>
				</p></li>
				</c:if>
				
					<c:if test="${empty typeId}">
					<li><b>类型：</b>
					   <p>
						 <a href="javascript:void(0);" title="不限" class="here">不限</a>
						
						 <c:forEach items="${types}" var="type">
						   	<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${type.id}&typeName=${type.name}&minPrice=${minPrice }&maxPrice=${maxPrice}&featureId=${featureId}&featureName=${featureName}&filPeople=${filPeople}"
						   	                                                                    title="${type.id}">${type.name}</a>
						</c:forEach>
					   </p>
					</li>
				</c:if>
				<c:if test="${empty featureId}">
					<li><b>材质：</b>
					   <p>
						 <a href="javascript:void(0);" title="不限" class="here">不限</a>
						
						 <c:forEach items="${features}" var="feature">
						   	<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName}&minPrice=${minPrice }&maxPrice=${maxPrice}&featureId=${feature.id}&featureName=${feature.name}&filPeople=${filPeople}"
						   	                                                                    title="${feature.id}">${feature.name}</a>
						</c:forEach>
					   </p>
					</li>
				</c:if>
				
                
                <c:if test="${empty filPeople }">
					<li><b>适用人群：</b><p>
						<a href="javascript:void(0);" title="不限" class="here">不限</a>
				     	<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName}&minPrice=${minPrice }&maxPrice=${maxPrice}&featureId=${feature.id}&featureName=${feature.name}&filPeople=男士 "
					   	                                                                    title="男士">男士</a>
						<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName}&minPrice=${minPrice }&maxPrice=${maxPrice}&featureId=${feature.id}&featureName=${feature.name}&filPeople=女士" 
					   	                                                                    title="女士">女士</a>
						<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName}&minPrice=${minPrice }&maxPrice=${maxPrice}&featureId=${feature.id}&featureName=${feature.name}&filPeople=儿童"
					   	                                                                    title="儿童">儿童</a>
						<a href="${pageContext.request.contextPath}/front/productList.shtml?brandId=${brandId}&brandName=${brandName}&typeId=${typeId}&typeName=${typeName}&minPrice=${minPrice }&maxPrice=${maxPrice}&featureId=${feature.id}&featureName=${feature.name}&filPeople=中性"
					   	                                                                    title="中性">中性</a>
					</p></li>
				</c:if>
			</ul>
			<div class="sort_type">
				<a href="javascript:void(0);" id="sales" onclick="orderByFn('sales');"  title="销量" class="sales">销量</a>
				<a href="javascript:void(0);" id="createTime" onclick="orderByFn('createTime');" title="上架时间" class="time">上架时间</a>
			</div>
			<div class="mt ofc">
				<ul class="uls i_150x150 x4_150x150b" id="showProducts">
				   <c:forEach items="${pagination.list}" var="product" >
				     <li>
						<a href="${pageContext.request.contextPath}/html/product/${product.id}.html?isLogin=${isLogin}&username=${username}" title="${product.keywords}" target="_blank" class="pic"><img src="${baseUrl}${product.img.url}" alt="${product.name}" /></a>
						<dl>
							<!-- dt 10个文字+... -->
							<dt><a href="${pageContext.request.contextPath}/html/product/${product.id}.html?isLogin=${isLogin}&username=${username}" title="${product.keywords}" target="_blank">${product.name}</a></dt>
							<!-- dt 25个文字+... -->
							<dd class="h40">${product.keywords}</dd>
							<dd class="orange">￥${product.minPrice} ~ ￥${product.maxPrice}</dd>
							<dd>北京有货</dd>
							<dd><a href="#" title="加入购物车" class="inb btn70x21 mr">加入购物车</a></dd>
						</dl>
						<img src="${pageContext.request.contextPath}/res/img/pic/hot.gif" alt="热门" class="type" />
					</li>
				   </c:forEach>
				</ul>
			<div class="page pb15">
			   <span class="r inb_a page_b">
					<c:forEach items="${pagination.pageView }" var="page">
			            ${page }
			        </c:forEach>
				</span>
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