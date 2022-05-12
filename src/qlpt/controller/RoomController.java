package qlpt.controller;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qlpt.entity.CTKhachThueEntity;
import qlpt.entity.ChuTroEntity;
import qlpt.entity.DichVuEntity;
import qlpt.entity.HopDongEntity;
import qlpt.entity.KhachThueEntity;
import qlpt.entity.LoaiPhongEntity;
import qlpt.entity.NhaTroEntity;
import qlpt.entity.PhongEntity;
import qlpt.entity.TrangThaiEntity;

@Transactional
@Controller
@RequestMapping("room/")
public class RoomController {

	@Autowired
	SessionFactory factory;

	private String mact;

	@RequestMapping("index")
	public String index(ModelMap model, @ModelAttribute("room") PhongEntity room, HttpSession ss) {
		mact = ss.getAttribute("mact").toString();
		System.out.println("index " + mact);
		List<PhongEntity> rooms = this.getRooms();
		model.addAttribute("rooms", rooms);
		model.addAttribute("formHide", null);
//		if(page + 1 <= this.getRooms().size() / 6 && page >= 0)
//		{
//			model.addAttribute("page",page);
//		}else
//		{
//			model.addAttribute("page",-1);
//		}
//		if(page == 0)
//		{
//			System.out.println(this.getRooms().size() / 6 + 1);
//			model.addAttribute("page",this.getRooms().size() / 6 + 1);
//		}
		return "room/index";
	}

	@RequestMapping(value = "index", params = "linkAdd")
	public String redirectLinkAdd(ModelMap model, @ModelAttribute("room") PhongEntity room) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("formHide", 1);
		return "room/index";
	}

	public List<String> getMANT() {
		System.out.println("getMANT " + mact);
		Session session = factory.getCurrentSession();
		String hql = "SELECT MANT FROM NhaTroEntity WHERE MACT = :mact";
		Query query = session.createQuery(hql);
		query.setParameter("mact", mact);
		List<String> list = query.list();
		return list;
	}

	public List<PhongEntity> getRooms() {
//		String result = String.join(",",this.getMANT());
//		System.out.println(result);
		List<String> listnt = this.getMANT();
		if (!listnt.isEmpty()) {
			Session session = factory.getCurrentSession();
			String hql = "FROM PhongEntity WHERE MANT in (:listMANT)";
			Query query = session.createQuery(hql);
			query.setParameterList("listMANT", this.getMANT());
			List<PhongEntity> list = query.list();
			return list;
		} else {
			return Collections.emptyList();
		}

	}

	public List<PhongEntity> getRooms(int page, int amount) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhongEntity p ORDER BY p.MAPHONG DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult(page * amount);
		query.setMaxResults(amount);
		List<PhongEntity> list = query.list();
		return list;
	}

	@ModelAttribute("KhuSelect")
	public List<NhaTroEntity> getNhaTros() {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaTroEntity WHERE MACT = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", mact);
		List<NhaTroEntity> list = query.list();

		return list;
	}

	@ModelAttribute("TrangThaiSelect")
	public List<TrangThaiEntity> getTrangThai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM TrangThaiEntity";
		Query query = session.createQuery(hql);
		List<TrangThaiEntity> list = query.list();
		return list;
	}

	@ModelAttribute("LoaiPhongSelect")
	public List<TrangThaiEntity> getLoaiPhongg() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiPhongEntity";
		Query query = session.createQuery(hql);
		List<TrangThaiEntity> list = query.list();
		return list;
	}

	// ADD room
	public Integer insertRoom(PhongEntity phong) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(phong);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "index", params = "btnAdd")
	public String addRoom(@ModelAttribute("room") PhongEntity room, ModelMap model, BindingResult errors) {
		if (room.getMOTARIENG().trim().length() == 0) {
			System.out.println("Điền thiếu !!!");
			errors.rejectValue("MOTARIENG", "PhongEntity", "Mô tả cho từng phòng không được bỏ trống !");
			model.addAttribute("rooms", this.getRooms());
			return "room/index";
		}
		Integer temp = this.insertRoom(room);
		if (temp != 0)
			model.addAttribute("message", "Thêm thành công");
		else
			model.addAttribute("message", "Thêm thất bại!");
		model.addAttribute("rooms", this.getRooms());

		return "room/index";
	}

	// Finish add room
	// Edit room
	public PhongEntity getRoom(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhongEntity where MAPHONG = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		PhongEntity list = (PhongEntity) query.list().get(0);

		return list;
	}

	@RequestMapping(value = "index", params = "btnEdit")
	public String edit_Room(ModelMap model, @ModelAttribute("room") PhongEntity room) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			System.out.println(room.getMAPHONG());
			String hql = "update PhongEntity set MOTARIENG = :motarieng, MATT = :matt, MANT = :mant, MALOAI = :maloai where MAPHONG = "
					+ String.valueOf(room.getMAPHONG());
			Query query = session.createQuery(hql);
			query.setParameter("motarieng", room.getMOTARIENG());
			query.setParameter("matt", room.getTrangThai().getMATT());
			query.setParameter("mant", room.getNhatro().getMANT());
			query.setParameter("maloai", room.getLoaiPhong().getMALOAI());
			int a = query.executeUpdate();
			model.addAttribute("message", "Update thành công");
			t.commit();

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
			model.addAttribute("message", "Update thất bại!");
		} finally {
			session.close();
		}

		model.addAttribute("rooms", this.getRooms());

		return "room/index";
	}

	// Thả dữ liệu vô form
	@RequestMapping(value = "index/{id}", params = "linkEdit")
	public String editRoommmm(@ModelAttribute("room") PhongEntity room, ModelMap model, @PathVariable("id") int id) {
		model.addAttribute("btnStatus", "btnEdit");
//		System.out.println("check1");
		model.addAttribute("room", this.getRoom(id));
		// System.out.println(this.getRoom(id).getMAPHONG());

//     	model.addAttribute("rooms", this.getRooms());
//		System.out.println("check2");
		model.addAttribute("formHide", 1);
		return "room/index";
	}
	// Finish Edit room
	// DELETE ROOM

	@RequestMapping(value = "/index/{id}.htm", params = "linkDelete")
	public String delete(@PathVariable("id") int id, ModelMap model, @ModelAttribute("room") PhongEntity room) {
		System.out.println("check");
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
//        String hql = "FROM PhongEntity where MAPHONG = :id";	
		String hql = "DELETE FROM PhongEntity where MAPHONG = :id";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
//		PhongEntity room1 = (PhongEntity) query.list().get(0);
//		session.delete(room1);
			query.executeUpdate();
			t.commit();
			String success = "Xóa thành công phòng " + id + " !";
			model.addAttribute("message", success);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			e.printStackTrace();
			t.rollback();
			String failure = "Xóa thất bại phòng " + id + " !";
			model.addAttribute("message", failure);

		} finally {
			session.close();
		}
		model.addAttribute("rooms", this.getRooms());
		return "room/index";

	}

	// FINISH DELETE ROOM
	// TYPE ROOM
	@RequestMapping("typeroom/index")
	public String typeRoom(ModelMap model) {
		model.addAttribute("loaiPhong", this.getTypeRooms());
		model.addAttribute("formHide", null);

		return "room/typeroom";
	}

	// get type room
	public List<LoaiPhongEntity> getTypeRooms() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiPhongEntity";
		Query query = session.createQuery(hql);
		List<LoaiPhongEntity> list = query.list();
		return list;
	}
	// END TYPE ROOM
	// ADD type room

	public Integer insertArea(LoaiPhongEntity loai) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(loai);
			t.commit();
		} catch (Exception e) {
			System.out.println("Loi Loai phong " + e.getMessage());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "typeroom/index", params = "linkAdd")
	public String redirectLinkAdd(ModelMap model, @ModelAttribute("loaiPhong") LoaiPhongEntity loai) {
		model.addAttribute("formHide", 1);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("areas", this.getTypeRooms());
		return "room/typeroom";
	}

	@RequestMapping(value = "typeroom/index", params = "btnAdd")
	public String addTypeRoom(ModelMap model, @ModelAttribute("loaiPhong") LoaiPhongEntity loai) {
		Integer temp = this.insertArea(loai);
		if (temp != 0)
			model.addAttribute("message", "Thêm thành công");
		else
			model.addAttribute("message", "Thêm thất bại!");
		model.addAttribute("loaiPhong", this.getTypeRooms());
		return "room/typeroom";
	}

	// end add type room
	public Integer deleteRoom(Integer maLoai) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "DELETE FROM PhongEntity where MALOAI = :maLoai";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("maLoai", maLoai);
			query.executeUpdate();
			t.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
	}

	@RequestMapping(value = "/typeroom/index/{id}.htm", params = "linkDelete")
	public String deleteTypeRoom(@PathVariable("id") int id, ModelMap model,
			@ModelAttribute("loaiPhong") LoaiPhongEntity loaiPhong) {
		System.out.println("check");
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "DELETE FROM LoaiPhongEntity where MALOAI = :id";
		try {
			this.deleteRoom(id);
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			query.executeUpdate();
			t.commit();
			String success = "Xóa thành công !";
			model.addAttribute("message", success);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			e.printStackTrace();
			t.rollback();
			String failure = "Xóa thất bại !";
			model.addAttribute("message", failure);

		} finally {
			session.close();
		}
		model.addAttribute("loaiPhong", this.getTypeRooms());
		return "room/typeroom";
	}
	// delete room

	// edit type room
	public LoaiPhongEntity getOneType(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiPhongEntity where MALOAI = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		LoaiPhongEntity list = (LoaiPhongEntity) query.list().get(0);

		return list;
	}

	@RequestMapping(value = "typeroom/index", params = "btnEdit")
	public String edit_TypeRoom(ModelMap model, @ModelAttribute("loaiPhong") LoaiPhongEntity loaiPhong) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			System.out.println(loaiPhong.getMALOAI());
			String hql = "update LoaiPhongEntity set TENLOAI = :tenloai , DIENTICH = :dientich , SLNGUOITD = :sltd , DONGIA = :dongia , MOTA =:mota WHERE MALOAI ="
					+ String.valueOf(loaiPhong.getMALOAI());

			System.out.println(hql);
			Query query = session.createQuery(hql);
			query.setParameter("tenloai", loaiPhong.getTENLOAI());
			query.setParameter("dientich", loaiPhong.getDIENTICH());
			query.setParameter("sltd", loaiPhong.getSLNGUOITD());
			query.setParameter("dongia", loaiPhong.getDONGIA());
			query.setParameter("mota", loaiPhong.getMOTA());

			int a = query.executeUpdate();
			model.addAttribute("message", "Update thành công");
			t.commit();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi edit type " + e.getMessage());
			t.rollback();
			e.printStackTrace();
			model.addAttribute("message", "Update thất bại!");
		} finally {
			session.close();
		}

		model.addAttribute("loaiPhong", this.getTypeRooms());

		return "room/typeroom";
	}

	// Thả dữ liệu vô form
	@RequestMapping(value = "typeroom/index/{id}", params = "linkEdit")
	public String typeRoomEdit(ModelMap model, @PathVariable("id") Integer id,
			@ModelAttribute("loaiPhong") LoaiPhongEntity loaiPhong) {
		System.out.println("Check");
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("loaiPhong", this.getOneType(id));
		model.addAttribute("formHide", 1);
		return "room/typeroom";
	}
	// end edit type room

//KHACH THUE
	@ModelAttribute("gender")
	public String[] getGender() {
		String[] genders = { "Nam", "Nữ", "Khác" };
		return genders;
	}

	@RequestMapping("addCustomer/{id}.htm")
	public String showForm(@ModelAttribute("customer") KhachThueEntity customer, ModelMap model,
			@PathVariable("id") String id) {
		model.addAttribute("id", id);
		return "room/addCus";
	}

	public Integer insertCustomer(KhachThueEntity customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(customer);
			t.commit();
		} catch (Exception e) {
			System.out.println("Loi area " + e.getMessage());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	private KhachThueEntity kt;
	private Integer idPhong;
	private PhongEntity phongtemp;

	@RequestMapping(value = "addCustomer/{id}.htm", method = RequestMethod.POST)
	public void addCustomer(ModelMap model, @ModelAttribute("customer") KhachThueEntity customer,
			HttpServletResponse res, HttpServletRequest req, @PathVariable("id") int id) {
		System.out.println("check add Cus");
		String uuid = UUID.randomUUID().toString();
		customer.setMAKT(uuid);
		this.kt = customer;
		this.idPhong = id;
		try {
			res.sendRedirect(req.getContextPath() + "/room/hopdong.htm");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}

	private KhachThueEntity checkNguoiDaiDien(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HopDongEntity hd WHERE hd.phong.MAPHONG = :idPhong";
		Query query = session.createQuery(hql);
		query.setParameter("idPhong", id);
//		int soLuong =(int) query.uniqueResult();
//		Iterator count = query.iterate();
//		System.out.println("No. of rows : " + count.next());
//		if ( (Integer) count.next()== 0)
//			return 0;
//		Long soLuong = (Long) query.uniqueResult();
		List<HopDongEntity> ktdt = query.list();

		if (ktdt.size() == 0)
			return null;
		return ktdt.get(0).getKhachThueDaiDien();
	}

	@RequestMapping("hopdong")
	public String hopdong(@ModelAttribute("hopdong") HopDongEntity hopdong) {

		return "room/hopdong";
	}

	public Integer insertHopDong(HopDongEntity hd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(hd);
			t.commit();
		} catch (Exception e) {
			System.out.println("Loi hd " + e.getMessage());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer insertCTKT(CTKhachThueEntity ctkt) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(ctkt);
			t.commit();
		} catch (Exception e) {
			System.out.println("Loi ctkt " + e.getMessage());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "themHopDong", method = RequestMethod.POST)
	public String addHopDong(ModelMap model, @ModelAttribute("hopdong") HopDongEntity hopdong) {
		CTKhachThueEntity ctkt = new CTKhachThueEntity();
		ctkt.setHopDong(hopdong);
		ctkt.setKhachThue(this.kt);
		ctkt.setTRANGTHAI(true);

		KhachThueEntity a = this.checkNguoiDaiDien(this.idPhong);
		if (a == null)
			hopdong.setKhachThueDaiDien(this.kt);
		else
			hopdong.setKhachThueDaiDien(a);
		hopdong.setPhong(this.getRoom(this.idPhong));
		hopdong.setDAHUY(false);

		Integer temp1 = this.insertCustomer(this.kt);
		Integer temp = this.insertHopDong(hopdong);
		Integer temp2 = this.insertCTKT(ctkt);
		if (temp != 0 && temp1 != 0 && temp2 != 0)
			model.addAttribute("message", "Thêm khách vào phòng thành công");
		else
			model.addAttribute("message", "Thêm khách thất bại!");
		model.addAttribute("rooms", this.getRooms());
		return "room/index";
	}

	@RequestMapping("desc-hop-dong/{id}")
	public String xemHopDong(@PathVariable("id") int id, ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "Select MAHOPDONG FROM HopDongEntity hd WHERE hd.phong.MAPHONG = :id AND hd.DAHUY=False";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Integer> listHD = query.list();
		if (listHD.size() == 0) {
			model.addAttribute("khach", null);
			return "room/descPhong";
		}
		String hql1 = "Select khachThue.MAKT FROM CTKhachThueEntity ct WHERE ct.hopDong.MAHOPDONG IN (:mahd) AND ct.TRANGTHAI = True";
		Session s1 = factory.getCurrentSession();
		Query query1 = s1.createQuery(hql1);
		query1.setParameterList("mahd", listHD);
		List<String> listKT = query1.list();
		if (listKT.size() == 0) {
			model.addAttribute("khach", null);
			return "room/descPhong";
		}

		String hql3 = "FROM KhachThueEntity kt WHERE kt.MAKT IN (:listmakt)";
		Session s2 = factory.getCurrentSession();
		Query query2 = s2.createQuery(hql3);
		query2.setParameterList("listmakt", listKT);
		List<KhachThueEntity> list = query2.list();
		model.addAttribute("khach", list);
		return "room/descPhong";
	}
//END KHACHTHUE
}