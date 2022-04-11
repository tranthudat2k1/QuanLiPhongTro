<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<link
	href="<c:url value='/resources/assets/dist/css/bootstrap.min.css' />"
	rel="stylesheet">
<div class="app">
	<%@include file="/WEB-INF/views/includes/navbar.jsp"%>

	<main class="container">
		<div class="row justify-content-md-center">
			<div class="pseudo"></div>
			<div class="content__header">
				<i class='bx bx-menu'></i>
			</div>
			<hr>
			<div class="row">
				<div class="box" style="height: auto;">
					<form:form action="service/create.htm" modelAttribute="service"
						method="post">
						<div class="box__title">
							<div class="d-md-flex" style="justify-content: space-between;">
								<h2>${title}</h2>
								<div>
									<button onclick="location.href='service/index.htm'"
										class="btn btn-warning me-md-2" type="button"
										style="border-color: #fffafa;">
										<i class='bx bx-arrow-back'
											style="font-size: 16px; font-weight: bold;"></i> Quay về
									</button>
									<button name="${btnStatus}" class="btn btn-primary"
										style="border-color: #fffafa;" type="submit">
										<i class='bx bx-save'
											style="font-size: 16px; font-weight: bold;"></i> Lưu
									</button>
								</div>
							</div>
						</div>
						<br>
						<div class="box__main">
							<div class="row" style="margin-bottom: 0px;">
								<form:input path="MADV" class="form-control" type="hidden" />
								<div class="col mb-3">
									<label class="form-label">Tên dịch vụ* </label>
									<form:input path="LOAIDV" type="text" cssClass="form-control"
										placeholder="Nhập tên dịch vụ"></form:input>
								</div>
								<div class="col mb-3">
									<label class="form-label">Đơn giá* </label>
									<div class="input-group" >
										<form:input path="DONGIA" class="form-control"
											aria-label="Dollar amount (with dot and two decimal places)" style="text-align: right;"/>
										<span class="input-group-text">VNĐ</span>
									</div>
								</div>
							</div>
							<div class="row" style="margin-bottom: 10px;">
								<div class="col mb-8">
									<label class="form-label">Ghi chú </label>
									<form:textarea path="MOTA" class="form-control"></form:textarea>
								</div>
							</div>
							<div class="row" style="margin-bottom: 0px;">
								<div class="col mb-8">
									<p style="color: red;">(*)Thông tin bắt buộc</p>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</main>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
