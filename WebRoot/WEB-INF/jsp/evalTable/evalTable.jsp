<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
	<%@ include file="../header.jspf" %>
	<title>评教指标</title>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/datatables/dataTables.bootstrap.css"></link>
  </head>
  
  <body>
    	<div class="container-fluid">
    		<%@ include file="../head.jsp" %>
    		<div class="row" style="margin-top: 70px">
    			<div class="col-sm-2">
					<%@ include file="../left.jsp" %>
    			</div>
    			<div class="col-sm-10">
    					<!-- 生成试卷 -->
    					<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title">生成新指标</h3>
						  </div>
						  <div class="panel-body">
						   	 <center><a href="${pageContext.request.contextPath}/admin/evalTable/add" class="btn btn-info btn-lg">生成新指标</a></center>
						  </div>
						</div><!-- 生成试卷 -->
						
						
						<!-- 试卷列表 -->
    					<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title">指标列表</h3>
						  </div>
						  <div class="panel-body">
						    	<table class="table table-hover table-striped table-bordered">
						    		<thead>
						    		<tr>
						    			<td>评教指标标题</td>
						    			<td>生成时间</td>
						    			<td>操作</td>
						    		</tr>
						    		</thead>
						    		<tbody>
						    		<c:forEach items="${evalTableList }" var="e">
						    			<tr>
						    				<td><a target="_blank" href="${pageContext.request.contextPath}/admin/evalTable/show/${e.id}">${e.title }</a></td>
						    				<td><fm:formatDate value="${e.createDate}" pattern="yyyy-MM-dd"/></td>
						    				<td><a id="${e.id}" class="btn btn-sm btn-warning delete">删除</a></td>
						    				
						    			</tr>
						    		</c:forEach>
						    		</tbody>
						    	</table>
						  </div>
						</div><!-- 试卷列表 -->
    			</div>
    		</div>
    		<script type="text/javascript">
    			$(function(){
    				$(".delete").click(function(){
    					var mes;
         				$.post("../../problem/delete/"+$(this).attr("name"),{"id":$(this).attr("name")},function(date){
         					mes= date.mes;
         				});
         				if(mes == 'success'){
         					alert("删除成功");
         					$(this).parent().parent("tr").fadeOut(2000);
         				}else{
         					alert("删除失败！有  用户的申请 使用了改试卷，不可删除");
         				}
         				
        			});
    			});
    		</script>
    	</div>
    	<%@ include file="../buttom.jsp" %>
    	<script
		src="${pageContext.request.contextPath}/js/datatables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/datatables/dataTables.bootstrap.js"></script>
  </body>
</html>