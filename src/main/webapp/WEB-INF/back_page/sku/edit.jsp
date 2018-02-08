<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-edit</title>
<style type="">
.h2_ch a:hover, .h2_ch a.here {
    color: red;
    font-weight: bold;
    background-position: 0px -32px;
}
.h2_ch a {
    float: left;
    height: 32px;
    margin-right: -1px;
    padding: 0px 16px;
    line-height: 32px;
    font-size: 14px;
    font-weight: normal;
    border: 1px solid #C5C5C5;
    background: url('${pageContext.request.contextPath}/res/itcast/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
}
a {
    color: #06C;
    text-decoration: none;
}
</style>
<script type="text/javascript">
      //图片上传
      function uploadPic(){
    	 var options = {
    	    url:"${pageContext.request.contextPath}/upload/uploadImgSku.do",                  //默认是form的action， 如果申明，则会覆盖
    	    type:"POST",             //form的method（get or post）
    	    dataType:"json",         //返回的数据类型
    	    success: function(data){
    			//回调 二个路径  
    			//url
    			//path
    			$("#sku_url").attr("src",data.url);
    			$("#sku_path").val(data.path);
    		 },//提交后的回调函数 
    		error:function(){alert("回调失败函数");},
    			 
    	 }
    	 //alert("上传....");
    	 $("#jvForm").ajaxSubmit(options);
     }

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: sku管理 - 修改</div>
	<form class="ropt">
		<!-- <input type="submit" onclick="window.location.href='${pageContext.request.contextPath}/back/skuList.do?productId=${sku.productId }';" value="返回列表" class="return-button"/> -->
	    <a href="${pageContext.request.contextPath}/back/skuList.do?productId=${sku.productId }" class="pn-opt">返回列表</a>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="${pageContext.request.contextPath}/back/updateSku.do" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">所属商品:</td>
					<input type="hidden" name="id" value="${sku.id}" />
					<td width="80%" class="pn-fcontent">
						<select name="productId">
							<option value="">请选择商品</option>
							<c:forEach items="${products}" var="product">
								 <option value="${product.id}"  <c:if test="${sku.productId==product.id}">selected </c:if> >${product.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
				    <!-- 获取商品颜色  bbs_color -->
					<td width="20%" class="pn-flabel pn-flabel-h">sku的颜色:</td>
					<td width="80%" class="pn-fcontent">
						<select name="colorId">
							<option value="">请选择颜色</option>
							<c:forEach items="${colors}" var="color">
								<option value="${color.id}" <c:if test="${sku.colorId==color.id}">selected</c:if> >${color.name}</option>
							</c:forEach>
					    </select>
				   </td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					  
						<span class="pn-frequired">*</span>
						<!-- 获取产品尺码  字段固定没有数据表-->
						尺码:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="size"  value="S" <c:if test="${sku.size=='S'}">checked</c:if> />S
						<input type="radio" name="size" value="M" <c:if test="${sku.size=='M'}">checked</c:if>/>M
						<input type="radio" name="size" value="L" <c:if test="${sku.size=='L'}">checked</c:if> />L
						<input type="radio" name="size" value="XL" <c:if test="${sku.size=='XL'}">checked</c:if>  />XL
						<input type="radio" name="size" value="XXL" <c:if test="${sku.size=='XXL'}">checked</c:if>   />XXL
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						运费:</td><td width="80%" class="pn-fcontent">
						<input type="number" step="0.01" value="${sku.deliveFee}"  class="required" name="deliveFee" maxlength="10"/>元
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						售价:</td><td width="80%" class="pn-fcontent">
						<input type="number" step="0.01" value="${sku.skuPrice}" class="required" name="skuPrice" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						库存:</td><td width="80%" class="pn-fcontent">
						<input type="number" value="${sku.stockInventory}" class="required" name="stockInventory" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						购买限制:</td><td width="80%" class="pn-fcontent">
						<input type="number" value="${sku.skuUpperLimit}" class="required" name="skuUpperLimit" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						仓库位置:</td><td width="80%" class="pn-fcontent">
						<input type="number"  value="${sku.location}" class="required" name="location" maxlength="10"/>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						前台显示排序:</td><td width="80%" class="pn-fcontent">
						<input type="number"  value="${sku.skuSort}" class="required" name="skuSort" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						sku名称:</td><td width="80%" class="pn-fcontent">
						<input type="text" value="${sku.skuName}" class="required" name="skuName" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						市场价:</td><td width="80%" class="pn-fcontent">
						<input type="number" step="0.01" value="${sku.marketPrice}" class="required" name="marketPrice" maxlength="10"/>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						sku图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">
						SKU图片  精确到颜色及尺码对应的图片。
					</td>
				</tr>
				<tr>
				    <!-- sku图片路径  -->
					<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
						<img width="100" height="100" id="sku_url" src="${baseUrl}${sku.skuImg}"/>
						<input type="hidden" name="skuImg" id="sku_path"/>
						<input type="file" onchange="uploadPic()" name="imgs"/>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						类型:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="skuType" value="1" <c:if test="${sku.colorId==color.id}">checked</c:if>  />赠品
						<input type="radio" name="skuType" value="0" <c:if test="${sku.colorId==color.id}">checked</c:if>/>普通
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						状态:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="lastStatus" value="1" <c:if test="${sku.lastStatus==1}">checked</c:if> />最新
						<input type="radio" name="lastStatus" value="0" <c:if test="${sku.lastStatus==0}">checked</c:if> />历史
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						销量:</td><td width="80%" class="pn-fcontent">
						<input type="number" value="${sku.sales}" class="required" name="sales" maxlength="10"/>
					</td>
				</tr>
			</tbody>
		    <tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>