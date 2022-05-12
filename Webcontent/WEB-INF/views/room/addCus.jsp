<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<link href="<c:url value='/resources/assets/bootstrap.min.css' />"
	rel="stylesheet" />
<div class="app">
	<%@include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div class="pseudo"></div>
	<main class="container">
		<div class="form_cus">
		<h2 class="room_name">Thêm khách vào phòng</h2>
			<hr />
			<form:form method="POST" action="room/addCustomer/${id}.htm"
				modelAttribute='customer'>
				<form:input path="MAKT" style="display:none" />
				<div class="form-row col-8">
					<div class="form-group col-6">
						<label for="ho" class="room__label">Họ</label>
						<form:input id="ho" class="form-control" path="HO" />
					</div>
					<div class="form-group col-6">
						<label for="ho" class="room__label">Tên</label>
						<form:input id="ten" class="form-control" path="TEN" />
					</div>
				</div>
				<div class="form-row  col-8">
					<div class="form-group col-6">
						<label for="namsinh" class="room__label">Năm sinh</label>
						<form:input id="namsinh" class="form-control" path="NAMSINH" />
					</div>
					<div class="form-group col-6">
						<label for="gioitinh" class="room__label">Giới tính</label>
						<form:select id="gioitinh" class="form-control" path="GIOITINH"
							items="${gender}" />
					</div>
				</div>

				<div class="form-row col-8">
					<div class="form-group col-6">
						<label for="cccd" class="room__label">CMND/CCCD</label>
						<form:input id="cccd" class="form-control" path="CCCD" />
					</div>
					<div class="form-group col-6">
						<label for="email" class="room__label">SĐT</label>
						<form:input id="email" class="form-control" path="EMAIL" />

					</div>
				</div>

				<div class="form-row col-8">
					<div class="form-group col-6">
						<label for="std" class="room__label">SĐT</label>
						<form:input id="sdt" class="form-control" path="SDT" />
					</div>
					<div class="form-group col-6">
						<label for="nghenghiep" class="room__label">Nghề nghiệp</label>
						<form:input id="nghenghiep" class="form-control" path="NGHENGHIEP" />
					</div>

				</div>
				<div class="col-8">
					<label for="diachi" class="room__label">Địa chỉ</label>
					<form:textarea id="diachi" class="form-control" path="DIACHI" />
				</div>


				<button type="submit" class="btn btn-success mt-3">
					Tiếp <i class='bx bxs-chevrons-right'
						style="font-size: 18px; position: relative; top: 2px;"></i>
				</button>
			</form:form>

		</div>
	</main>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
