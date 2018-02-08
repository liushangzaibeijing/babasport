<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
 <script type="text/javascript">
    
      function deleteBrand(ids){
    	  if(!confirm("您确定要删除吗？ 可能包含对应的商品信息，删除后不可恢复请慎重！！！")){
     		  return false;
     	  }
      
    	  url = '${pageContext.request.contextPath }/back/deleteBrandAjax.do';
          
    	  var name = '${name}';
       	  var isDisplay = ${isDisplay};
       	  if(name==''){
       		  name = null;
       	  }
       	  
       	  var json = {ids:ids,name:name,isDisplay:isDisplay};
       	  json = JSON.stringify(json);
          ajax(json,url,"POST",function(data){
   			  alert("删除成功");
   			  window.location.reload();
	      });
      }
      
     
   	  
       function	deleteBrandMuti(tagName){
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
   	   	  
   	   	   deleteBrand(ids);
       }
      
      
      //同步操作
      //删除单个品牌
      function deleteOne(ids){
    	  if(!confirm("您确定要删除吗？")){
    		  return false;
    	  }
    	  var name = '${name}';
    	  var isDisplay = ${isDisplay};
    	  if(name==''){
         	  window.location.href="${pageContext.request.contextPath }/back/deleteBrand.do?ids="+ids+"&isDisplay="+isDisplay;
    	  }else
    		  window.location.href="${pageContext.request.contextPath }/back/deleteBrand.do?ids="+ids+"&name="+name+"&isDisplay="+isDisplay; 
    	  
      }
      
      //删除多个文件
      function deleteMuti(){
    	  //获取所有选中的
    	  var array = $("input[name='ids']:checked");
    	  //如果没有选中则提示用户    	  
    	  if(array.length<=0){
    		  alert("请至少选中一个");
    		  return;
    	  }
    	  
    	  if(!confirm("您确定要删除吗？")){
    		  return false;
    	  }
    	   
    	  //拼接id
    	  var ids = "";
    	  for(var i=0,len=array.length;i<len;i++){
    		  ids = ids+array[i].value+",";
    	  }
    	  ids = ids.slice(0,ids.length-1);
    	  
    	  var name = '${name}';
    	  var isDisplay = ${isDisplay};
    	  if(name==''){
         	  window.location.href="${pageContext.request.contextPath }/back/deleteBrand.do?ids="+ids+"&isDisplay="+isDisplay;
    	  }else
    		  window.location.href="${pageContext.request.contextPath }/back/deleteBrand.do?ids="+ids+"&name="+name+"&isDisplay="+isDisplay; 
    	  
      }
      
      //更新品牌
      function updateBrand(id){
    	  var name = '${name}';
    	  var isDisplay = ${isDisplay};
    	  if(name==''){
         	  window.location.href="${pageContext.request.contextPath }/back/brandEditUI.do?id="+id+"&isDisplay="+isDisplay;
    	  }else
    		  window.location.href="${pageContext.request.contextPath }/back/brandEditUI.do?id="+id+"&name="+name+"&isDisplay="+isDisplay; 
    	   
      }
      
 </script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 品牌管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='${pageContext.request.contextPath}/back/brandAdd.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="${pageContext.request.contextPath}/back/brandList.do" method="post" style="padding-top:5px;">
品牌名称: <input type="text" value="${name}" name="name" id="name"/>
	<select name="isDisplay" id="isDisplay" >
		<option value="1" <c:if test="${isDisplay==0}">selected="selected"</c:if>>是</option>
		<option value="0" <c:if test="${isDisplay==0}">selected="selected"</c:if>>不是</option>
	</select>
	<input type="submit" class="query" value="查询"/>
</form>
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" name="header" onclick="allcheckBox('ids',this.checked);"/></th>
			<th>品牌ID</th>
			<th>品牌名称</th>
			<th>品牌图片</th>
			<th>品牌描述</th>
			<th>排序</th>
			<th>是否可用</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	  <c:forEach items="${pagination.list}" var="brand">
	    <tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
			<td><input type="checkbox" value="${brand.id}" name="ids" onclick="checkHead('ids','header')"/></td>
			<td align="center">${brand.id}</td>
			<td align="center">${brand.name}</td>
			<td align="center"><img width="40" height="40" src="${baseUrl}${brand.imgUrl}"/></td>
			<td align="center">${brand.description}</td>
			<td align="center">${brand.sort}</td>
			<td align="center">${brand.isDisplay}</td>
			<td align="center">
			<a class="pn-opt" href="#" onclick="updateBrand(${brand.id})">修改</a> | <a class="pn-opt" href="javascript:void(0);"  onclick="deleteBrand('${brand.id}');">删除</a>
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
<div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="deleteBrandMuti('ids')"/></div>
</div>
</body>
</html>