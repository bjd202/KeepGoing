<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-migrate-3.0.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/popper.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.stellar.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.countdown.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.magnific-popup.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/aos.js"></script>

<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.slidereveal.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#right_sidebar").slideReveal({
			  trigger: $("#trigger"),
			  push: false,
			  overlay: true,
			  position: "right"
			});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#member_login").on("click", function() {
			var params=$("#login_form").serialize();
			console.log(params);
			
			$.ajax({
	            url: "<%=request.getContextPath()%>/login",
	            type: "POST",
	            data: params,
	            success: function(data){
	                console.log(data);
	                alert(data);
	                location.reload();
	            },
	            error: function(){
	                alert("err");
	            }
	        });
		})
	})
</script>