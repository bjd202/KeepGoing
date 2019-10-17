<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--Modal: free / Register Form-->
<div class="modal fade" id="freeSearchModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
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
							class="fas fa-user mr-1"></i> 검색</a></li>
				</ul>

				<!-- Tab panels -->
				<div class="tab-content">
					<!--Panel 7-->
					<div class="tab-pane fade in show active" id="panel7"
						role="tabpanel">

						<!--Body-->
						<div class="modal-body mb-1">
							<form action="<%= request.getContextPath() %>/free/search" method="post" id="searchForm">
								<div class="mb-3">
										<select name="category_Num">
											<option value="1" selected="selected">전체</option>
											<option value="2">우리동네 운동부</option>
											<option value="3">건강한 식생활</option>
											<option value="4">나만의 운동법</option>
											<option value="5">초보자를 위한 운동추천</option>
											<option value="6">컴플랙스 극복</option>
											
										</select>
										<select name="search_Type">
											<option value="1" selected="selected">전체</option>
											<option value="2">제목</option>
											
											<option value="5">글쓴이</option>
										</select>
								</div>

								<div class="mb-3">
									<input type="text"
										class="form-control" name="keyword"
										placeholder="검색어를 입력해주세요.">
								</div>

								<div class="text-center mt-2">
									<button class="btn btn-info" type="submit" id="free_search">
										검색 <i class="fas fa-sign-in ml-1"></i>
									</button>
								</div>
							</form>
						</div>
					</div>
					<!--/.Panel 7-->
				</div>
			</div>
		</div>
		<!--/.Content-->
	</div>
</div>