<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<div class="app">
	<%@include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div class="main">
		<div class="pseudo"></div>
		<div class="content">
			<div class="content__header">
				<i class='bx bx-menu'></i>
			</div>
			<hr />
			<c:choose>
				<c:when test="${ sessionScope.mact != null}">
					<p>Đã đăng nhập ${mact }</p>
				</c:when>
				<c:otherwise>
					<p>Chưa đăng nhập</p>
				</c:otherwise>
			</c:choose>
			
			
			<div class="row">
				<div class="c-6">
					<div class="box">
						<h2 class="box__title">Trạng thái phòng</h2>
						<div class="box__main">
							<div id="piechart"></div>
						</div>
					</div>
				</div>
				<div class="c-6">
					<div class="box">
						<h2 class="box__title">Danh sách phòng trống</h2>
						<div class="box__main">
							<div class="rounded_table">
								<table id="customers">
									<tr>
										<th>Nhà</th>
										<th>Phòng</th>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 1</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 2</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 3</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 4</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 5</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 1</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 2</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 3</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 4</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 5</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 1</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 2</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 3</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 4</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 5</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 1</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 2</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 3</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 4</td>
									</tr>
									<tr>
										<td>Tầng 1</td>
										<td>Phòng 5</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="c-6">
					<div class="box">
						<h2 class="box__title">Danh sách khách nợ tiền nhà</h2>
						<div class="box__main">
							<div class="rounded_table">
								<table id="customers">
									<tr>
										<th>Nhà</th>
										<th>Phòng</th>
										<th>Khách</th>
										<th>Tháng</th>
										<th>Số tiền</th>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2</td>
										<td>Nguyễn Doãn Thông</td>
										<td>2</td>
										<td>1.200.000</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="c-6">
					<div class="box">
						<h2 class="box__title">Danh sách công việc chưa hoàn thành</h2>
						<div class="box__main">
							<div class="rounded_table">
								<table id="customers">
									<tr>
										<th>Ngày</th>
										<th>Nhà</th>
										<th>Phòng</th>
										<th>Nội Dung</th>
									</tr>
									<tr>
										<td>12-03-2022</td>
										<td>1</td>
										<td>2</td>
										<td>Sửa trần nhà</td>
									</tr>
									<tr>
										<td>12-03-2022</td>
										<td>1</td>
										<td>2</td>
										<td>Sửa trần nhà</td>
									</tr>
									<tr>
										<td>12-03-2022</td>
										<td>1</td>
										<td>2</td>
										<td>Sửa trần nhà</td>
									</tr>
									<tr>
										<td>12-03-2022</td>
										<td>1</td>
										<td>2</td>
										<td>Sửa trần nhà</td>
									</tr>
									<tr>
										<td>12-03-2022</td>
										<td>1</td>
										<td>2</td>
										<td>Sửa trần nhà</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
