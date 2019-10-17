<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--Modal: Login / Register Form-->
<div class="modal fade" id="reviewSearchModal" tabindex="-1" role="dialog"
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
							<form action="<%= request.getContextPath() %>/review/search" method="post" id="searchForm">
								<div class="mb-3">
										<select name="category_Num">
											<option value="1" selected="selected">전체</option>
											<option value="2">상품</option>
											<option value="3">피트니스</option>
											<option value="4">장소</option>
											<option value="5">다이어트</option>
											<option value="6">웨이트 트레이닝</option>
											<option value="7">레시피</option>
										</select>
										<select name="search_Type">
											<option value="1" selected="selected">전체</option>
											<option value="2">제목</option>
											<option value="3">내용</option>
											<option value="4">제목+내용</option>
											<option value="5">글쓴이</option>
										</select>
								</div>

								<div class="mb-3">
									<input type="text"
										class="form-control" name="keyword"
										placeholder="검색어를 입력해주세요.">
								</div>

								<div class="text-center mt-2">
									<button class="btn btn-info" type="submit" id="review_search">
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