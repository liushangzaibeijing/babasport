<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-add</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/fckeditor/fckeditor.js"></script>

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
$(function () {
    var tObj;
    //拿到tabs下的三个a标签，遍历a标签
    $("#tabs a").each(function () {
    	//如果当前tbody的class属性为here  here表示为当前要显示的
        if ($(this).attr("class").indexOf("here") == 0) {
            tObj = $(this)
        }
    	//给三个tab子标签分别单击事件 单击则显示
        $(this).click(function () {
            var c = $(this).attr("class");
            if (c.indexOf("here") == 0) {
                return;
            }
            //获取当前单击的ref 和先前显示的对象。
            var ref = $(this).attr("ref");
            
            var ref_t = tObj.attr("ref");
            
            tObj.attr("class", "nor");
            $(this).attr("class", "here");
            $(ref_t).hide();  //将以前显示的隐藏
            $(ref).show();    //将当前单击的显示
            
            tObj = $(this);
            //fck文本编辑器
            if (ref == '#tab_2') {
                var fck = new FCKeditor("productdesc");
                fck.BasePath = "${pageContext.request.contextPath}/res/fckeditor/";
                fck.Height = 400;
                fck.Config["ImageUploadURL"] = "${pageContext.request.contextPath}/upload/uploadFckProduct.do";
                fck.ReplaceTextarea();
            }
         });
       });
     });
      //图片上传
      function uploadPic(){
    	 var options = {
    	    url:"${pageContext.request.contextPath}/upload/uploadImgProduct.do",                  //默认是form的action， 如果申明，则会覆盖
    	    type:"POST",             //form的method（get or post）
    	    dataType:"json",         //返回的数据类型
    	    success: function(data){
    			//回调 二个路径  
    			//url
    			//path
    			alert("json");
    			$("#product_url").attr("src",data.url);
    			$("#product_path").val(data.path);
    		 },//提交后的回调函数 
    	    timeout: 3000,           //限制请求时间，单位毫秒
    	    //target: '#allImgUrl',    //把服务器返回的内容放入id为output的元素中      
    			 
    	 }
    	 alert("上传....");
    	 $("#jvForm").ajaxSubmit(options);
     }

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="window.history.back(-1);" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<h2 class="h2_ch"><span id="tabs">
<a href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
<a href="javascript:void(0);" ref="#tab_2" title="商品描述" class="nor">商品描述</a>
<a href="javascript:void(3);" ref="#tab_3" title="商品参数" class="nor">包装清单</a>
</span></h2>
<div class="body-box" style="float:right">
	<form id="jvForm" action="${pageContext.request.contextPath}/back/addProduct.do" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						商品类型:</td><td width="80%" class="pn-fcontent">
								<select name="typeId">
									<option value="">请选择</option>
									<c:forEach items="${types}" var="type">
									  <option value="${type.id}">${type.name}</option>
									</c:forEach>
								</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						商品名称:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="name" maxlength="100" size="100"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						商品品牌:</td><td width="80%" class="pn-fcontent">
						<select name="brandId">
							<option value="">请选择品牌</option>
							<c:forEach items="${brands}" var="brand">
								<option value="${brand.id}">${brand.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						商品毛重:</td><td width="80%" class="pn-fcontent">
						<input type="text" value="0.6" class="required" name="weight" maxlength="10"/>KG
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					    <!-- 获取商品材质  bbs_feature -->
						材质:</td><td width="80%" class="pn-fcontent">
						    <c:forEach items="${features}" var="feature">
								<input type="checkbox" value="${feature.id}" name="feature"/>${feature.name}
							</c:forEach>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					    <!-- 获取商品颜色  bbs_color -->
						<span class="pn-frequired">*</span>
						颜色:</td><td width="80%" class="pn-fcontent">
						    <c:forEach items="${colors}" var="color">
								<input type="checkbox" value="${color.id}" name="color"/>${color.name}
							</c:forEach>
					    </td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					  
						<span class="pn-frequired">*</span>
						<!-- 获取产品尺码  字段固定没有数据表-->
						尺码:</td><td width="80%" class="pn-fcontent">
						<input type="checkbox" name="size" value="S"/>S
						<input type="checkbox" name="size" value="M"/>M
						<input type="checkbox" name="size" value="L"/>L
						<input type="checkbox" name="size" value="XL"/>XL
						<input type="checkbox" name="size" value="XXL"/>XXL
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						状态:</td><td width="80%" class="pn-fcontent">
						<input type="checkbox" name="isNew" value="1"/>新品
						<input type="checkbox" name="isCommend" value="1"/>推荐
						<input type="checkbox" name="isHot" value="1"/>热卖
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">
						注:该尺寸图片必须为90x150。
					</td>
				</tr>
				<tr>
				    <!-- 商品路径  -->
					<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
						<img width="100" height="100" id="product_url" />
						<input type="hidden" name="url" id="product_path"/>
						<input type="file" onchange="uploadPic()" name="imgs"/>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_2" style="display: none">
				<tr>
					<td >
						<textarea rows="10" cols="10" id="productdesc" name="description"></textarea>
					</td>
				</tr>
			</tbody>
			<!-- 产品列表是什么？ -->
			<tbody id="tab_3" style="display: none">
				<tr>
					<td >
						<textarea rows="15" cols="136" id="productList" name="packageList"></textarea>
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