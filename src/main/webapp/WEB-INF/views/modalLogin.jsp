<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--Modal: Login / Register Form-->
<div class="modal fade" id="modalLRForm" tabindex="-1" role="dialog"
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
							class="fas fa-user mr-1"></i> Login</a></li>
				</ul>

				<!-- Tab panels -->
				<div class="tab-content">
					<!--Panel 7-->
					<div class="tab-pane fade in show active" id="panel7"
						role="tabpanel">

						<!--Body-->
						<div class="modal-body mb-1">
							<form action="" method="post" id="login_form">
								<div class="mb-3">
									<label for="member_id">ID </label> <input type="text"
										class="form-control" name="member_id"
										placeholder="">
									<div class="invalid-feedback">X</div>
								</div>

								<div class="mb-3">
									<label for="password">비밀번호 </label> <input type="password"
										class="form-control" name="password"
										placeholder="">
									<div class="invalid-feedback">X</div>
								</div>

								<div class="text-center mt-2">
									<button class="btn btn-info" type="button" id="member_login">
										Log in <i class="fas fa-sign-in ml-1"></i>
									</button>
									<a href="<%=request.getContextPath()%>/naver_login"
										type="button" class="btn btn-info">Naver</a>
									<a href="<%=request.getContextPath()%>/google_login"
										type="button" class="btn btn-info">Google</a>
									<a href="https://kauth.kakao.com/oauth/authorize?client_id=041a20c843f92a91b5fa33c3d3fc5d85&redirect_uri=http://localhost:8080/webapp/loginResult&response_type=code"
										type="button" class="btn btn-info">KaKaO</a>
								</div>
							</form>
						</div>
						<!--Footer
						
							<div class="options text-center text-md-right mt-1">
								<p>
									Not a member? <a href="#" class="blue-text">Sign Up</a>
								</p>
								<p>
									Forgot <a href="#" class="blue-text">Password?</a>
								</p>
							</div>
							
						-->
						
						<div class="modal-footer">
						<button type="button"
								class="btn btn-outline-info waves-effect ml-auto"
								data-dismiss="modal">Close</button>
						</div>

					</div>
					<!--/.Panel 7-->
				</div>

			</div>
		</div>
		<!--/.Content-->
	</div>
</div>