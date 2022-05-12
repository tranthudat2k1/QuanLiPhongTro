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
			<h2 class="room_name">Xem những người đang ở tại phòng</h2>
			<hr />

				<c:if test="${message != null }">
					<div class="alert alert-primary" role="alert">${message}</div>
				</c:if>
				<table class="table table-striped  table-bordered">
					<thead>
						<tr style="text-align: center">
							<th scope="col">Họ</th>
							<th scope="col">Tên</th>
							<th scope="col">Năm sinh</th>
							<th scope="col">Giới tính</th>
							<th scope="col">CCCD</th>
							<th scope="col">Địa chỉ</th>
							<th scope="col">Email</th>
							<th scope="col">Phone</th>
							<th scope="col">Nghề nghiệp</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${khach != null }">
							<c:forEach var="r" items="${khach}">
							<tr>
								<td style="width: 8%">${r.HO}</td>
								<td style="width: 8%">${r.TEN }</td>
								<td style="width: 10%">${r.NAMSINH}</td>
								<td style="width: 10%">${r.GIOITINH }</td>
								<td style="width: 15%">${r.CCCD }</td>
								<td style="width: 20%">${r.DIACHI }</td>
								<td style="width: 10%">${r.EMAIL }</td>
								<td style="width: 15%">${r.SDT }</td>
								<td style="width: 14%">${r.NGHENGHIEP }</td>
							</tr>
						</c:forEach>
						</c:when>
						<c:otherwise>
							<h3>Chưa có ai ở phòng này</h3>
						</c:otherwise>
					</c:choose>
						

					</tbody>
				</table>

		</div>
	</main>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
