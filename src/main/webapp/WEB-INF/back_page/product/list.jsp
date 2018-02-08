<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
<script type="text/javascript">
//删除单个商品
function deleteProduct(ids,name,isShow,brandId){
	  if(!confirm("您确定要删除吗？  可能包含对应的商品信息，删除后不可恢复请慎重！！！")){
 		  return false;
 	  }
 	  
	  url = '${pageContext.request.contextPath }/back/deleteProduct.do';
    
 	  var json = {ids:ids,name:name,isShow:isShow,brandId:brandId};
       json = JSON.stringify(json);
 	   ajax(json,url,"POST",function(data){
   			  alert("删除成功");
   			  window.location.reload();}
 	   );
}


 //删除多个商品  
 function deleteProductMuti(tagName,name,isShow,brandId){
	  var ids = checkSelected(tagName);
	   	  
	  deleteProduct(ids,name,isShow,brandId);
 }
 
 function checkSelected(tagName){
	 //获取选中的checkbox
     var array = $("input[name='"+tagName+"']:checked");  
	 
	  //如果没有选中则提示用户    	  
 	  if(array.length<=0){
 		  alert("请至少选中一个");
 		  return;
 	  }
 	  
	  //拼接id
	  var ids = "";
	  for(var i=0,len=array.length;i<len;i++){
 	     ids = ids+array[i].value+",";
     }
	  ids = ids.slice(0,ids.length-1);
	  alert("ids:"+ids);
	  return ids;
 }
	 //上架
	 function putOnSale(name,isShow,brandId){
		 var ids = checkSelected("ids");
		 
		 var url = "${pageContext.request.contextPath }/back/putOnsale.do"; 
		 var json = {ids:ids,name:name,isShow:isShow,brandId:brandId};
		  json = JSON.stringify(json);
		 ajax(json,url,"POST",function(data){
   			  alert("上架成功");
   			  window.location.reload();}
		 );
	 }
 
 
	  //下架
	  function putOffSale(name,isShow,brandId){
		  var ids = checkSelected("ids");
			 
		  var url = "${pageContext.request.contextPath }/back/putOffsale.do"; 
		  var json = {ids:ids,name:name,isShow:isShow,brandId:brandId};
		  json = JSON.stringify(json); 
		  ajax(json,url,"POST",function(data){
	   			  alert("下架成功");
	   			  window.location.reload();
		  });
		  
	  }

//每次查询条件改变，都默认页号为1
function changePageNo(){
	$("input[name='pageNo']").val(1);
}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='${pageContext.request.contextPath}/back//addUI.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="${pageContext.request.contextPath}/back//productList.do" method="post" style="padding-top:5px;">
<input type="hidden" value="" name="pageNo"/>
名称: <input type="text" onkeyup="changePageNo()" value="${name}" name="name"/>
	<!-- 查询品牌下的所有商品  ajax -->
	<select onchange="changePageNo()" name="brandId">
		<option value="">请选择品牌</option>
		<!-- 查询所有的品牌 后台查询 -->
		<c:forEach items="${brands}" var="brand">
		  <option value="${brand.id }"  <c:if test='${brand.id==brandId}'>selected="selected"</c:if>>${brand.name}</option>
		</c:forEach>
	</select>
	<select onchange="changePageNo()" name="isShow">
		<option value="1" <c:if test='${isShow==1}'>selected="selected"</c:if>>上架</option>
		<option value="0" <c:if test='${isShow==0}'>selected="selected"</c:if>>下架</option>
	</select>
	<input type="submit" class="query" value="查询"/>
</form>
<form method="post" id="tableForm">
<input type="hidden" value="" name="pageNo"/>
<input type="hidden" value="" name="queryName"/>
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input name="header" type="checkbox" onclick="allcheckBox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>图片</th>
			<th width="5%">新品</th>
			<th width="5%">热卖</th>
			<th width="5%">推荐</th>
			<th width="5%">上下架</th>
			<th width="15%">操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	    <!-- 分页查询的品牌对象的展示 -->
	    <c:forEach items="${pagination.list}" var="product">
	       <tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" value="${product.id}" name="ids" onclick="checkHead('ids','header')"/></td>
			<td>${product.no}</td>
			<td align="center">${product.name}</td>
			<td align="center"><img width="50" height="50" src="${baseUrl}${product.img.url}"/></td>
			<td align="center">${product.isNew==0?'否':'是'}</td>
			<td align="center">${product.isHot==0?'否':'是'}</td>
			<td align="center">${product.isCommend==0?'否':'是'}</td>
			<td align="center">${product.isShow==1?'上架':'下架'}</td>
			<td align="center">
			<a href="#" class="pn-opt">查看</a> | <a href="#" class="pn-opt">修改</a> | <a href="#" onclick="deleteProduct('${product.id}',<c:if test="${empty product.name}">null</c:if>
																			                                                               <c:if test="${not empty product.name}">'${product.name}'</c:if>,
																					                                                       <c:if test="${empty product.isShow}">null</c:if>
																					                                                       <c:if test="${not empty product.isShow}">${product.isShow}</c:if>,
																					                                                       <c:if test="${empty product.brandId}">null</c:if>
																					                                                       <c:if test="${not empty product.brandId}">${product.brandId}</c:if>)" class="pn-opt">删除</a> | 
																					                                                       <a href="${pageContext.request.contextPath}/back/skuList.do?productId=${product.id}" class="pn-opt">库存</a>
			</td>
		   </tr>
	    </c:forEach>
	
		
	</tbody>
</table>
<div class="page pb15">

   <span class="r inb_a page_b">
	 	<c:forEach items="${pagination.pageView }" var="page">
            ${page }
        </c:forEach>
   </span>
</div>

<div style="margin-top:15px;">
    <input class="del-button" type="button" value="删除" onclick="deleteProductMuti('ids',<c:if test="${empty name}">null</c:if>
																			                                      <c:if test="${not empty name}">'${name}'</c:if>,
																					                              <c:if test="${empty isShow}">null</c:if>
																					                              <c:if test="${not empty isShow}">${isShow}</c:if>,
																					                              <c:if test="${empty brandId}">null</c:if>
																					                              <c:if test="${not empty brandId}">${brandId}</c:if>)"/>
    <input class="add" type="button" value="上架" onclick="putOnSale(<c:if test="${empty name}">null</c:if>
																<c:if test="${not empty name}">'${name}'</c:if>,
																<c:if test="${empty isShow}">null</c:if>
																<c:if test="${not empty isShow}">${isShow}</c:if>,
																<c:if test="${empty brandId}">null</c:if>
																<c:if test="${not empty brandId}">${brandId}</c:if>);"/>
     <input class="del-button" type="button" value="下架" onclick="putOffSale(<c:if test="${empty name}">null</c:if>
																	   <c:if test="${not empty name}">'${name}'</c:if>,
																	   <c:if test="${empty isShow}">null</c:if>
																	   <c:if test="${not empty isShow}">${isShow}</c:if>,
																	   <c:if test="${empty brandId}">null</c:if>
																	   <c:if test="${not empty brandId}">${brandId}</c:if>)"/>
</div>
</form>
</div>
</body>
</html>