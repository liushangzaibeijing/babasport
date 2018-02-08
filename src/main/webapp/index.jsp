<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

   <!-- 默认跳转到前台的首页  使用该方式 url不进行改变 针对具体的url进行缓存也就无法实现 所以这里使用重定向 url会发生改变 -->
   <!-- 使用重定向时，地址栏的url会改变 -->
   <%response.sendRedirect("front/productList.shtml"); %>

<!-- 
<script type="text/javascript">
window.onload= function(){
  alert("aaa");
  window.location.href="http://localhost:8080/babasport/front/productList.shtml";
}
</script>
-->