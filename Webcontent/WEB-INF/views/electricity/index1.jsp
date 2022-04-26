<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/resources/css/service.css"%>
<!-- included thư viện calendar -->
<!--  jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />

<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<!-- end -->
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
						<div class="box__title">
							<div class="d-md-flex" style="justify-content: space-between;">
								<h2 style="font-weight: bold;">Chỉ số điện</h2>
								<div>
									<button onclick="location.href='electricity/save.htm'"
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
							<div class="d-md-flex" style="justify-content:space-between;">
								<div>
									<!-- Form code begins -->
									<form method="post">
										<div class="form-group" style="display: flex; justify-content: space-between;">
											<!-- Date input -->
											<label class="control-label" for="date">Tháng: </label> <input
												class="form-control" id="date" name="date"
												placeholder="MM/YYYY" type="text" style="height: 30px"/>
										</div>
									</form>
									<!-- Form code ends -->
								</div>
								<div>
									Kỳ: <select class="form-select"
										aria-label="Default select example" style="border: 1px solid #ced4da; border-radius: 0.25rem; height: 30px;">
										<option selected>Tất cả</option>
										<option>Kì 15</option>
										<option>Kì 30</option>
									</select>
								</div>
								<div>
									Nhà: <select class="form-select" style="border: 1px solid #ced4da; height: 30px;">
										<option selected>Tất cả</option>
										<c:forEach items="${dsNhaTro}" var="k">
											<option value="${k.getMANT()}">${k.getTENNT()}</option>
										</c:forEach>
									</select>
								</div>
								<div>
									Trạng thái: <select class="form-select" style="border: 1px solid #ced4da; height: 30px;">
										<option selected>Tất cả</option>
										<c:forEach items="${dsTrangThai}" var="tt">
											<option value="${tt.getMATT()}">${tt.getTENTT()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<br>
							<div>${message}</div>
							<table id="customers" class="rounded_table">
								<thead>
									<tr>
										<th scope="col">Nhà</th>
										<th scope="col">Phòng</th>
										<th scope="col">Khách thuê</th>
										<th scope="col" style="width: 15%;">Chỉ số điện cũ</th>
										<th scope="col" style="width: 15%;">Chỉ số điện mới</th>
										<th scope="col">Sử dụng</th>
									</tr>
								</thead>
								<tbody id="table_services">
									<c:forEach items="${dsCTDichVu}" var="dv">
									<tr>
										<td>${dv.GET().getKHU().getTENKHU()}</td>
										<td>${dv.getPHONG().getMAPHONG()}</td>
										<c:choose>
											<c:when test="${!empty dv.getPHONG().getDsHopDong()}">
												<td>dv.getPHONG().getDsHopDong().get(0).getKHACHTHUE().getTEN()</td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>
										<td><input type="text" value="${dv.getCHISOCU()}"
											style="width: 100%; text-align: right;"></td>
										<td><input type="text" value="${dv.getCHISOMOI()}"
											style="width: 100%; text-align: right;"></td>
										<td>${dv.getCHISOMOI()- dv.getCHISOCU()}</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<br>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
