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
					<div class="box__title">
						<div class="d-md-flex" style="justify-content: space-between;">
							<h2>Danh sách dịch vụ</h2>
							<div>
								<button onclick="location.href='service/create.htm'"
									class="btn btn-primary me-md-2 bg-success" type="button"
									style="border-color: #fffafa;">
									<i class='bx bx-plus'
										style="font-size: 16px; font-weight: bold;"></i> Thêm dịch vụ
								</button>
								<!-- <button class="btn  btn-primary bg-danger" type="button"
									style="border-color: #fffafa;">
									<i class='bx bx-x' style="font-size: 16px; font-weight: bold;"></i>
									Xóa
								</button> -->
							</div>
						</div>
					</div>
					<br>
					<div class="box__main" style="height: auto;">
						<div class="d-md-flex"
							style="flex-wrap: nowrap; justify-content: space-between;">
							<div>
								Xem <select class="form-select form-select-sm"
									aria-label="Default select example"
									style="width: 75px; display: inline-block;">
									<option selected>20</option>
									<option value="1">One</option>
									<option value="2">Two</option>
									<option value="3">Three</option>
								</select> mục
							</div>
							<%-- <div>
								<form:form>
									<div>
										<input name="searchInput" id="searchInput"
											class="form-control" placeholder="Nhập tên dịch vụ cần tìm"
											aria-lable="Search">
									</div>
									<div>
										<button name="btnSearch" id="searchService"
											class="btn btn-outline-success" type="submit">Search</button>
									</div>
								</form:form>
							</div> --%>
							<form class="d-inline-flex">
								<input name="searchInput" id="searchInput" class="form-control" style="margin-right: 16px;"
									aria-lable="Search" placeholder="Nhập tên dịch vụ cần tìm">
								<button name="btnSearch" id="searchProduct"
									class="btn btn-outline-success" type="submit" >Search</button>
							</form>
						</div>
						<br>
						<div>${message}</div>
						<table id="customers" class="rounded_table">
							<thead>
								<tr>
									<th scope="col">Tên dịch vụ</th>
									<th scope="col">Đơn giá (VNĐ)</th>
									<th scope="col">Mô tả</th>
									<th scope="col">Chỉnh sửa</th>
									<th scope="col">Xóa</th>
								</tr>
							</thead>
							<tbody id="table_services">
								<c:forEach items="${services}" var="dv">
									<tr>
										<td>${dv.LOAIDV }</td>
										<td><fmt:formatNumber value="${dv.DONGIA}"
												currencySymbol=""></fmt:formatNumber></td>
										<td>${dv.MOTA}</td>
										<td><a class="btn-primary"
											href="service/create/${dv.MADV}.htm?linkEdit"><i
												class='bx bxs-edit'></i> </a></td>
										<td><a class="btn-danger"
											href="service/index/${dv.MADV}.htm?linkDelete"> <i
												class='bx bx-x'></i>
										</a></td>
									</tr>
								</c:forEach>

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
