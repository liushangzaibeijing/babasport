<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-list</title>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 库存管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='${pageContext.request.contextPath}/back/addSkuUI.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input name="header" type="checkbox" onclick="allcheckBox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品颜色</th>
			<th>商品尺码</th>
			<th>市场价格</th>
			<th>销售价格</th>
			<th>库       存</th>
			<th>购买限制</th>
			<th>运       费</th>
			<th>是否赠品</th>
			<th>操       作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	    <c:forEach items="${pagination.list}" var="sku">
	       <tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<td><input type="checkbox" name="ids" value="${sku.id}"  onclick="checkHead('ids','header')"/></td>
				<td>${sku.product.no}</td>
				<td align="center">${sku.color.name}</td>
				<td align="center">${sku.size}</td>
				<td align="center"><input type="text" id="m52" value="${sku.marketPrice}"  size="10" disabled="disabled"/></td>
				<td align="center"><input type="text" id="p52" value="${sku.skuPrice}"  size="10" disabled="disabled"/></td>
				<td align="center"><input type="text" id="i52" value="${sku.stockInventory}"  size="10" disabled="disabled"/></td>
				<td align="center"><input type="text" id="l52" value="${sku.skuUpperLimit}"  size="10" disabled="disabled"/></td>
				<td align="center"><input type="text" id="f52" value="${sku.deliveFee}"  size="10" disabled="disabled"/></td>
				<td align="center">${sku.skuType==0?'是':'否'}</td>
				<td align="center"><a href="${pageContext.request.contextPath}/back/updateSkuUI.do?skuId=${sku.id}" class="pn-opt">修改</a> | <a href="${pageContext.request.contextPath}/back/deleteSku.do?skuId=${sku.id}&productId=${sku.productId}" class="pn-opt">删除</a></td>
			</tr>
	    </c:forEach>
	</tbody>
</table>
</form>
</div>
<div class="page pb15">

   <span class="r inb_a page_b">
	 	<c:forEach items="${pagination.pageView }" var="page">
            ${page }
        </c:forEach>
   </span>
</div>
</body>
</html>