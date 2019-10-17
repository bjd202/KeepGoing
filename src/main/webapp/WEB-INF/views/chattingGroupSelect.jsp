<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>관심사 선택</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>
</head>
<body>
	<div class="modal-dialog cascading-modal" role="document">
		<!--Content-->
		<div class="modal-content">

			<!--Modal cascading tabs-->
			<div class="modal-c-tabs">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs md-tabs tabs-2 light-blue darken-3"
					role="tablist">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#panel7" role="tab"><i
							class="fas fa-user mr-1"></i> 그룹채팅</a></li>
				</ul>

				<!-- Tab panels -->
				<div class="tab-content">
					<!--Panel 6-->
					<div class="tab-pane fade in show active" id="panel7"
						role="tabpanel">
						<!--Body-->
						<div class="modal-body">
							<div class="md-form form-sm mb-6">
								<i class="fas fa-envelope prefix"></i>
							</div>

							<div class="text-center mt-2">
							<p>원하는 그룹을 선택하세요.</p>
								<select style="visibility: visible;" id="selectedGroup" name="selectedGroup" onclick="selectGroup();"
								class="btn btn-outline-info waves-effect ml-auto">
									<option value="1" selected="selected">관심사 1</option>
									<option value="2">관심사 2</option>
									<option value="3">관심사 3</option>
									<option value="4">관심사 4</option>
									<option value="5">관심사 5</option>
									<option value="6">관심사 6</option>
								</select>
								<a href="<%= request.getContextPath() %>/chattingGroupModal_1" 
									onclick="window.open(this.href, '관심사 채팅방', 'width = 456, height = 805'); window.close(); return false;" 
									id="enterGroupModal" style="visibility: visible;"
									class="btn btn-outline-info waves-effect ml-auto" >입장하기</a>
							</div>

						</div>
						<!--Footer-->
						<div class="modal-footer">
							<div class="options text-center text-md-right mt-1"
								style="width: 90%;">
								<!-- 푸터의 내용 -->
							</div>
						</div>
					</div>
					<!--/.Panel 6-->
				</div>
			</div>
		</div>
		<!--/.Content-->
	</div>

<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
var selected = null;
	function selectGroup() {
		selected = $("#selectedGroup").val();
		$("#enterGroupModal").attr("href", "<%= request.getContextPath() %>/chattingGroupModal_" + selected);
	}
</script>
</body>
</html>

