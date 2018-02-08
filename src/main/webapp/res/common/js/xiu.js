  //全选/全不选操作
      function allcheckBox(tagName,status){
    	  // 全选
    	  $("input[type=checkbox][name='"+tagName+"']").attr("checked",status);
    	  
      }
      
     // 根据用户的勾选来设置表头是否为全选状态
     function checkHead(childTagName,headTagName){
    	 // 获取选中的记录数
    	 var checkedCount = $("input[type=checkbox][name='"+childTagName+"']:checked").size();
    	 // 获取总记录数
    	 var totalCount = $("input[type=checkbox][name='"+childTagName+"']").size();
    	 // 获取头部checkbox
    	 var header =  $("input[type=checkbox][name='"+headTagName+"']");
    	 // 获取未选中的记录数
   	     var uncheckedCount = $("input[type=checkbox][name='"+childTagName+"']:not(:checked)").size();
    	 
    	 // 第一种方式
    	 // 比较如果相等，则表头选中
    	 /*
			 * if(totalCount===checkedCount){
			 * $("input[type=checkbox][name='header']").attr("checked",true);
			 * }else{//否则表头不选中
			 * $("input[type=checkbox][name='header']").attr("checked",false); }
			 */
    	 
    	 // 三目运算符
    	 // totalCount===checkedCount?header.attr("checked",true):header.attr("checked",false);
    	 
    	 // 第二种方式
    	 // 如果记录数为0，则表头为选中
    	 // 否则，表头为不选中
    	  uncheckedCount==0?header.attr("checked",true):header.attr("checked",false);
    	 
     }
     
     
   // 异步操作
     // 删除单个品牌
     function ajax(param,url,type,okCallBack){
   	  
   	  $.ajax({
   		    async:true,
   		    type:type,
   			url:url,
   			contentType:'application/json;charset=utf-8',
   			data:param,
   			success:okCallBack
   		});
     }         