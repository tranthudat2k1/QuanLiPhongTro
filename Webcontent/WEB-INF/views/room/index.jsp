<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<link href="<c:url value='/resources/assets/bootstrap.min.css' />"
	rel="stylesheet" />
<div class="app">
	<%@include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div class="pseudo"></div>
	<main class="container">

		<div class="room">
		<c:choose>
				<c:when test="${ sessionScope.mact != null}">
					<p>Đã đăng nhập ${mact }</p>
				</c:when>
				<c:otherwise>
					<p>Chưa đăng nhập</p>
				</c:otherwise>
			</c:choose>
			<c:if test="${formHide != null }">
					<h2 class="room_name">Thêm phòng</h2>
			<hr />
				<form:form modelAttribute="room" method="POST"
					action="room/index.htm">
					<form:input path="MAPHONG" style="display:none" />
					<div class="form-row col-8">
						<div class="col-6">
							<label for="TrangThai" class="room__label">Trạng thái</label>
							<form:select id="TrangThai" class="form-control"
								path="trangThai.MATT" items="${TrangThaiSelect}"
								itemLabel="TENTT" itemValue="MATT">
							</form:select>
						</div>

						<div class="col-6">
							<label for="NhaTro" class="room__label">Nhà trọ</label>
							<form:select id="NhaTro" class="form-control" path="nhatro.MANT"
								items="${KhuSelect}" itemLabel="TENNT" itemValue="MANT">
							</form:select>
						</div>
					</div>
					<div class="col-8">
						<label for="loaiPhong" class="room__label">Loại Phòng</label>
						<form:select id="loaiPhong" class="form-control"
							path="loaiPhong.MALOAI" items="${LoaiPhongSelect}"
							itemLabel="TENLOAI" itemValue="MALOAI">
						</form:select>
					</div>
						<div class="col-8">
							<label for="MoTa" class="room__label">Mô Tả Riêng</label>
							<form:textarea class="form-control" id="MoTa" rows="3"
								path="MOTARIENG" value="Phòng ngonnnnn"></form:textarea>
							<form:errors path="MOTARIENG" />
						</div>
					<button type="submit" name="${btnStatus}"
						class="btn btn-success mt-3">
						<i class='bx bx-save'
							style="font-size: 18px; position: relative; top: 2px;"></i> Lưu
					</button>
				</form:form>
			</c:if>

			<c:if test="${formHide == null }">
				<h2 class="room_name">Danh sách phòng</h2>
				<hr />
				<c:if test="${message != null }">
					<div class="alert alert-primary" role="alert">${message}</div>
				</c:if>
				<div class="mb-4 d-flex flex-row-reverse">
					<div class="ml-2">
						<a href="room/index.htm?linkAdd">
							<button type="button" class="btn btn-primary">
								<i class='bx bx-plus-medical'></i> Thêm Phòng
							</button>
						</a>
					</div>
					<a href="room/typeroom/index.htm">
						<button type="button" class="btn btn-success">Xem Loại
							Phòng</button>
					</a>
				</div>

				<table class="table table-striped  table-bordered">
					<thead>
						<tr style="text-align: center">
							<th scope="col">Mã phòng</th>
							<th scope="col">Số người tối đa</th>
							<th scope="col">Đơn giá</th>
							<th scope="col">Khu</th>
							<th scope="col">Trạng thái</th>
							<th scope="col">Mô tả</th>
							<th scope="col">Xóa</th>
							<th scope="col">Sửa</th>
							<th scope="col">Thêm khách</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="r" items="${rooms}">
							<tr>
								<td style="width: 7%">
								<a href="room/desc-hop-dong/${r.MAPHONG}.htm">
								${r.MAPHONG}
								</a>
								</td>
								<td style="width: 8%">${r.loaiPhong.SLNGUOITD}</td>
								<td style="width: 15%">${r.loaiPhong.DONGIA }</td>
								<td style="width: 10%">${r.nhatro.TENNT }</td>
								<td style="width: 10%">${r.trangThai.TENTT }</td>
								<td style="width: 30%">${r.MOTARIENG }</td>
								<td style="text-align: center;"><a
									href="room/index/${r.MAPHONG}.htm?linkDelete"><i
										class='bx bx-folder-minus' style="font-size: 18px;"></i></a></td>
								<td style="text-align: center;"><a
									href="room/index/${r.MAPHONG}.htm?linkEdit"><i
										class='bx bx-edit' style="font-size: 18px;"></i></a></td>
								<td style="font-size: 24px;text-align:center">
								<c:choose>
									<c:when test="${r.trangThai.MATT != 3 }">
										<a href="room/addCustomer/${r.MAPHONG}.htm">
									<i class='bx bx-user-plus' style="font-size: 22px"></i>
									</a>
									</c:when>
									<c:otherwise>
									<i class='bx bx-user-plus' style="font-size: 22px;color:gray"></i>
									</c:otherwise>
								</c:choose>
									
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-end">
		
						<li class="page-item"><a class="page-link"
							href="room/index.htm?page=${ page - 1 }"> < Prev</a></li>
						<li class="page-item"><a class="page-link"
							href="room/index.htm?page=${page + 1 }">Next > </a></li>
	
					</ul>
				</nav>
			</c:if>

		</div>
	</main>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
