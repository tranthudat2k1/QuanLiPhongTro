<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<link
	href="<c:url value='/resources/assets/bootstrap.min.css' />"
	rel="stylesheet" />
<div class="app">
	<%@include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div class="pseudo"></div>	
	<main class="container">
	<%-- 
	<div class="row ">
			<div class="col-12">
				<p class="room_list--text">Danh sách phòng</p>
			</div>
			<div class="col-12">
				<form class="d-flex">
					<div class="form-group cus_select">
						<select class="form-control" id="exampleFormControlSelect1">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<div class="form-group cus_select">
						<select class="form-control" id="exampleFormControlSelect1">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<div class="form-group cus_input">
						<input type="text" class="form-control"
							id="exampleFormControlInput1" placeholder="Phòng">
					</div>
					<button type="submit" class="btn btn-primary mb-2 cus_btn--primary">Tìm
						Kiếm</button>
				</form>
			</div>
			<div class="col-12">
				<div class="row">
					<div class="col-4 room_status--text">Còn trống 4| Đã cho thuê 0 | Chưa thu phí
						0</div>
					<div class="col-8"></div>
				</div>
			</div>
		</div>
	--%>
	
		
		<div class="room">
                <h2 class="room_name">Danh sách phòng</h2>
                <hr/>
                <div class="mb-4 d-flex flex-row-reverse">
                    <button type="button" class="btn btn-primary"> <i class='bx bx-plus-medical'></i> Thêm Phòng</button>
                	<button type="button" class="btn btn-success"> 
                
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
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="r" items="${rooms}">
						<tr>
							<td style="width: 7%">${r.MAPHONG }</td>
							<td style="width: 8%">${r.SLNGUOITOIDA}</td>
							<td style="width: 15%">${r.DONGIA }</td>
							<td style="width: 10%">${r.khu.TENKHU }</td>
							<td style="width: 10%">${r.trangThai.TENTT }</td>
							<td style="width: 30%">${r.MOTA }</td>
							 <td style="text-align: center;"><a href="room/index/${r.MAPHONG}.htm?linkDelete"><i class='bx bx-folder-minus' style="font-size: 18px;"></i></a></td>
                        <td style="text-align: center;"><a href="room/index/${r.MAPHONG}.htm?linkEdit"><i class='bx bx-edit' style="font-size: 18px;"></i></a></td>
						</tr>
					</c:forEach>
    
                    </tbody>
                  </table>
                
					<span>${message}</span>
                  <form:form modelAttribute="room" method="POST" action="room/index.htm">
                    <div class="form-row">
                      <div class="form-group col-4">
                        <label for="soNguoiTD" class="room__label">Số người 1 phòng</label>
                        <form:input type="number" class="form-control" id="soNguoiTD" placeholder="Nhập số người tối đa" path="SLNGUOITOIDA"/>
                      </div>
                      <div class="form-group col-4">
                        <label for="DonGia" class="room__label">Đơn giá / Tháng
                        </label>
                        <form:input type="number" class="form-control" id="DonGia" placeholder="Nhập đơn giá" path="DONGIA"/>
                      </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-4">
                            <label for="Khu" class="room__label">Khu</label>
                            <form:select id="Khu" class="form-control" path="khu.MAKHU" items="${KhuSelect}" itemLabel="TENKHU" itemValue="MAKHU">
				      		</form:select>
                        </div>
                        <div class="form-group col-4">
                          <label for="TrangThai" class="room__label">Trạng thái</label>
                          <form:select id="TrangThai" class="form-control" path="trangThai.MATT" items="${TrangThaiSelect}" itemLabel="TENTT" itemValue="MATT">
				      		</form:select>
                        </div>
                    </div>
                    <div class="form-row">
                      <div class="form-group col-8">
                        <label for="MoTa" class="room__label">Mô Tả</label>
                        <form:textarea class="form-control" id="MoTa" rows="3" path="MOTA"></form:textarea>
                      </div>
                    </div>
                    <button type="submit" name="${btnStatus}" class="btn btn-success mt-3"><i class='bx bx-save' style="font-size: 18px;position: relative;top: 2px;"></i> Lưu</button>
                  </form:form>

            </div>
	</main>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
