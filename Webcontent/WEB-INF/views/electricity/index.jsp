<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<link
	href="<c:url value='/resources/assets/dist/css/bootstrap.min.css' />"
	rel="stylesheet">
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

	<main class="container">
		<div class="row justify-content-md-center">
			<div class="pseudo"></div>
			<div class="content__header">
				<i class='bx bx-menu'></i>
			</div>
			<hr>
			<div class="row">
				<div class="box" style="height: auto;">
					<div class="box__title">
						<div class="d-md-flex" style="justify-content: space-between;">
							<h2>Chỉ số điện</h2>
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
						<div class="row"
							style="margin-bottom: 0px; justify-content: space-around;">
							<div class="col mb-3">
								<!-- Form code begins -->
								<form method="post">
									<div class="form-group">
										<!-- Date input -->
										<label class="control-label" for="date">Tháng</label> <input
											class="form-control" id="date" name="date"
											placeholder="MM/DD/YYY" type="text" />
									</div>
								</form>
								<!-- Form code ends -->
							</div>
							<div class="col mb-3">
								Kỳ: <select class="form-select"
									aria-label="Default select example">
									<option selected>Tất cả</option>
									<option>Kì 15</option>
									<option>Kì 30</option>
								</select>
							</div>
							<div class="col mb-3">
								Nhà: <select class="form-select">
									<option selected>Tất cả</option>
									<c:forEach items="${dsNhaTro}" var="k">
										<option value="${k.getMANT()}">${k.getTENNT()}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col mb-3">
								Trạng thái: <select class="form-select">
									<option selected>Tất cả</option>
									<c:forEach items="${dsTrangThai}" var="tt">
										<option value="${tt.getMATT()}">${tt.getTENTT()}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<br>
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
								<%-- <c:forEach items="${dsCTDichVu}" var="dv">
									<tr>
										<td>${dv.getPHONG().getKHU().getTENKHU()}</td>
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
								</c:forEach> --%>
							</tbody>
						</table>
						<br>
					</div>
				</div>
			</div>
		</div>
	</main>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
