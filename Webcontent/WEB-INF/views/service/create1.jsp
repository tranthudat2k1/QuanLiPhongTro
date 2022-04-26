<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/resources/css/service.css"%>
<div class="app">
	<%@include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div class="main">
		<div class="pseudo"></div>
		<div class="content">
			<div class="content__header">
				<i class='bx bx-menu'></i>
			</div>
			<hr />
			<div class="row">
				<div class="c-12">
					<div class="box" style="width: 95%; padding: 16px; padding-top: 0;">
						<form:form action="service/create.htm" modelAttribute="service"
							method="post">
							<div class="box__title">
								<div class="d-md-flex" style="justify-content: space-between;">
									<h2 style="font-weight: bold;">Thêm dịch vụ</h2>
									<div>
										<button onclick="location.href='service/index.htm'"
											class="btn btn-warning me-md-2" type="button"
											style="border-color: #fffafa; color: #fff">
											<i class='bx bx-arrow-back'
												style="font-size: 16px; font-weight: bold; "></i> Quay về
										</button>

										<button onclick="location.href='service/create.htm'"
											class="btn btn-primary me-md-2 bg-success" type="button"
											style="border-color: #fffafa;">
											<i class='bx bx-plus'
												style="font-size: 16px; font-weight: bold;"></i> Lưu
										</button>
									</div>
								</div>
							</div>
							<br>
							<div class="box__main" style="height: auto;">
								<div class="row" style="margin-bottom: 0px;">
									<form:input path="MADV" class="form-control" type="hidden" />
									<div class="col mb-3">
										<label class="form-label">Tên dịch vụ* </label>
										<form:input path="TENDV" type="text" cssClass="form-control"
											placeholder="Nhập tên dịch vụ"></form:input>
									</div>
									<div class="col mb-3">
										<label class="form-label">Đơn vị tính </label>
										<form:input path="DONVITINH" type="text"
											cssClass="form-control" placeholder="Nhập đơn vị tính"></form:input>
									</div>
									<%-- <div class="col mb-3">
									<label class="form-label">Đơn giá* </label>
									<div class="input-group" >
										<form:input path="DONGIA" class="form-control"
											aria-label="Dollar amount (with dot and two decimal places)" style="text-align: right;"/>
										<span class="input-group-text">VNĐ</span>
									</div>
								</div> --%>
								</div>
								<div class="row" style="margin-bottom: 10px;">
									<div class="col mb-8">
										<label class="form-label">Ghi chú </label>
										<form:textarea path="MOTA" class="form-control" rows="3"
											style="resize: unset;"></form:textarea>
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
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
