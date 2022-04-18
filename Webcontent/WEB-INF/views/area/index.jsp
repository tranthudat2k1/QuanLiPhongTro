<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<div class="app">
	<%@include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div class="pseudo"></div>	
	<main class="container">
		
		<div class="room">
                <h2 class="room_name">Danh sách khu</h2>
                <hr/>
                <div class="mb-4 d-flex flex-row-reverse">
                    <button type="button" class="btn btn-primary"> <i class='bx bx-plus-medical'></i> Thêm Phòng</button>
                </div>
                <table class="table table-striped  table-bordered">
                    <thead>
                      <tr style="text-align: center">
                        <th scope="col">Mã khu</th>
                        <th scope="col">Tên khu</th>
                        <th scope="col">Tỉnh/TP</th>
                        <th scope="col">Quận/Huyện</th>
                        <th scope="col">Phường/Xã</th>
                        <th scope="col">Chủ Trọ</th>
                        <th scope="col">Địa chỉ</th>
                        <th scope="col">Xóa</th>
                        <th scope="col">Sửa</th>
                      </tr>
                    </thead>
                    <tbody>
              
                     <c:forEach var="k" items="${areas}">
						<tr>
							<td style="width: 7%">${k.MAKHU}</td>
							<td style="width: 15%">${k.TENKHU}</td>
							<td style="width: 15%">${k.TINH_TP }</td>
							<td style="width: 10%">${k.QUAN_HUYEN }</td>
							<td style="width: 10%">${k.PHUONG_XA }</td>
							<td style="width: 10%">${k.chuTro.TEN }</td>
							<td style="width: 23%">${k.DIACHI }</td>
							 <td style="text-align: center;"><a href="area/index/${k.MAKHU}.htm?linkDelete"><i class='bx bx-folder-minus' style="font-size: 18px;"></i></a></td>
                        <td style="text-align: center;"><a href="area/index/${k.MAKHU}.htm?linkEdit"><i class='bx bx-edit' style="font-size: 18px;"></i></a></td>
						</tr>
					</c:forEach>
                
    					<tr>
    						
    					</tr>
                    </tbody>
                  </table>
                
					<span>${message}</span>

                  <form:form modelAttribute="area" method="POST" action="area/index.htm">
                    <div class="form-row">
                      <div class="form-group col-4">
                        <label for="tenKhu" class="room__label">Tên khu</label>
                        <form:input type="text" class="form-control" id="tenKhu" placeholder="Nhập tên khu" path="TENKHU"/>
                      </div>
                      <div class="form-group col-4">
                        <label for="Tinh" class="room__label">Tỉnh/TP 
                        </label>
                        <form:input type="text" class="form-control" id="Tinh" placeholder="Nhập Tỉnh/TP" path="TINH_TP"/>
                      </div>
                    </div>
                <div class="form-row">
                <div class="form-group col-4">
                        <label for="quanhuyen" class="room__label">Quận/Huyện</label>
                        <form:input type="text" class="form-control" id="quanhuyen" placeholder="Nhập Quận/Huyện" path="QUAN_HUYEN"/>
                      </div>
                      <div class="form-group col-4">
                        <label for="phuongxa" class="room__label">Phường/Xã
                        </label>
                        <form:input type="text" class="form-control" id="phuongxa" placeholder="Nhập Phường / Xã" path="PHUONG_XA"/>
                      </div>
                </div>
                   <div class="form-group col-8">
                          <label for="ChuTro" class="room__label">Chủ Trọ</label>
                          <form:select id="ChuTro" class="form-control" path="chuTro.MACT" items="${ChuTroSelect}" itemLabel="TEN" itemValue="MACT">
				      		</form:select>
                        </div>
                    <div class="form-row">
                      <div class="form-group col-8">
                        <label for="diachi" class="room__label">Địa chỉ</label>
                        <form:textarea class="form-control" id="diachi" rows="3" path="DIACHI"></form:textarea>
                      </div>
                    </div>
                    <button type="submit" name="${btnStatus}" class="btn btn-success mt-3"><i class='bx bx-save' style="font-size: 18px;position: relative;top: 2px;"></i> Lưu</button>
                  </form:form>
          
                 
            </div>
	</main>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
