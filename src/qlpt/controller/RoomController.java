package qlpt.controller;

import java.util.List;
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

import qlpt.entity.DichVuEntity;
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
	@RequestMapping("index")
	public String index(ModelMap model,@ModelAttribute("room") PhongEntity room)
	{
		List<PhongEntity> rooms = this.getRooms();
		model.addAttribute("rooms",rooms);
		model.addAttribute("formHide",null);
		return "room/index";
	}
	@RequestMapping(value="index",params="linkAdd")
	public String redirectLinkAdd(ModelMap model,@ModelAttribute("room") PhongEntity room)
	{
		model.addAttribute("btnStatus","btnAdd");
		model.addAttribute("formHide",1);
		return "room/index";
	}
	public List<PhongEntity> getRooms() {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhongEntity";
		Query query = session.createQuery(hql);
		List<PhongEntity> list = query.list();
		return list;
	}
	public List<PhongEntity> getRooms(int page) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhongEntity p ORDER BY p.MAPHONG DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult(page);
		query.setMaxResults(page+5);
		List<PhongEntity> list = query.list();
		return list;
	}
	@ModelAttribute("KhuSelect")
	public List<NhaTroEntity> getNhaTros()
	{
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaTroEntity";
		Query query = session.createQuery(hql);
		List<NhaTroEntity> list = query.list();

		return list;
	}
	
	
	@ModelAttribute("TrangThaiSelect")
	public List<TrangThaiEntity> getTrangThai()
	{
		Session session = factory.getCurrentSession();
		String hql = "FROM TrangThaiEntity";
		Query query = session.createQuery(hql);
		List<TrangThaiEntity> list = query.list();
		return list;
	}
	
	@ModelAttribute("LoaiPhongSelect")
	public List<TrangThaiEntity> getLoaiPhongg()
	{
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
	public String addRoom(@ModelAttribute("room") PhongEntity room,ModelMap model,BindingResult errors)
	{
		if(room.getMOTARIENG().trim().length() == 0)
		{
			System.out.println("Điền thiếu !!!");
			errors.rejectValue("MOTARIENG","PhongEntity","Mô tả cho từng phòng không được bỏ trống !");
			model.addAttribute("rooms",this.getRooms());
			return "room/index";
		}
		Integer temp = this.insertRoom(room);
		if(temp != 0)
				model.addAttribute("message", "Thêm thành công");
		else
			model.addAttribute("message", "Thêm thất bại!");
		model.addAttribute("rooms",this.getRooms());

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
	public String edit_Room(ModelMap model,
			@ModelAttribute("room") PhongEntity room) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			System.out.println(room.getMAPHONG());
			String hql = "update PhongEntity set MOTARIENG = :motarieng, MATT = :matt, MANT = :mant, MALOAI = :maloai where MAPHONG = "
					+ String.valueOf(room.getMAPHONG());
			Query query = session.createQuery(hql);
			query.setParameter("motarieng", room.getMOTARIENG());
			query.setParameter("matt",room.getTrangThai().getMATT());
			query.setParameter("mant",room.getNhatro().getMANT());
			query.setParameter("maloai", room.getLoaiPhong().getMALOAI());
			int a = query.executeUpdate();
			model.addAttribute("message", "Update thành công");
			t.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
			model.addAttribute("message", "Update thất bại!");
		}
		finally
		{
			session.close();
		}


		 model.addAttribute("rooms", this.getRooms());

		return "room/index";
	}

	
	// Thả dữ liệu vô form
	@RequestMapping(value = "index/{id}", params = "linkEdit")
	public String editRoom(ModelMap model,
			 @PathVariable("id") Integer id) {
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("room", this.getRoom(id));
		model.addAttribute("rooms", this.getRooms());
		model.addAttribute("formHide",1);
		return "room/index";
	}
	//Finish Edit room
	// DELETE ROOM
	
	@RequestMapping(value = "/index/{id}.htm", params = "linkDelete")
	public String delete(@PathVariable("id") int id, ModelMap model,@ModelAttribute("room") PhongEntity room) {	
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
		String success = "Xóa thành công phòng "+id+" !";
		model.addAttribute("message", success);
		
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			e.printStackTrace();
			t.rollback();
			String failure = "Xóa thất bại phòng "+id+" !";
			model.addAttribute("message", failure);

		}finally {
			session.close();
		}
		model.addAttribute("rooms", this.getRooms());
		return "room/index";
		

	}
	// FINISH DELETE ROOM
	// TYPE ROOM
	@RequestMapping("typeroom/index")
	public String typeRoom(ModelMap model)
	{
		model.addAttribute("loaiPhong",this.getTypeRooms());
		model.addAttribute("formHide",null);

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
			System.out.println("Loi Loai phong "+e.getMessage());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	@RequestMapping(value="typeroom/index",params = "linkAdd")
	public String redirectLinkAdd(ModelMap model,@ModelAttribute("loaiPhong") LoaiPhongEntity loai)
	{
		model.addAttribute("formHide",1);
		model.addAttribute("btnStatus","btnAdd");
		model.addAttribute("areas",this.getTypeRooms());
		return "room/typeroom";
	}
	@RequestMapping(value="typeroom/index",params = "btnAdd")
	public String addTypeRoom(ModelMap model,@ModelAttribute("loaiPhong") LoaiPhongEntity loai)
	{
		Integer temp = this.insertArea(loai);
		if(temp != 0)
				model.addAttribute("message", "Thêm thành công");
		else
			model.addAttribute("message", "Thêm thất bại!");
		model.addAttribute("loaiPhong",this.getTypeRooms());
		return "room/typeroom";
	}
	// end add type room
	public Integer deleteRoom(Integer maLoai)
	{
		Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        String hql = "DELETE FROM PhongEntity where MALOAI = :maLoai";	
		try {
		Query query = session.createQuery(hql);
		query.setParameter("maLoai", maLoai);
		query.executeUpdate();
		t.commit();	
		return 1;
		}
		catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
	}
	@RequestMapping(value = "/typeroom/index/{id}.htm", params = "linkDelete")
	public String deleteTypeRoom(@PathVariable("id") int id, ModelMap model,@ModelAttribute("loaiPhong") LoaiPhongEntity loaiPhong) {	
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
		
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			e.printStackTrace();
			t.rollback();
			String failure = "Xóa thất bại !";
			model.addAttribute("message", failure);

		}finally {
			session.close();
		}
		model.addAttribute("loaiPhong", this.getTypeRooms());
		return "room/typeroom";		
	}
	// delete room
	
	//edit type room
	public LoaiPhongEntity getOneType(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiPhongEntity where MALOAI = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		LoaiPhongEntity list = (LoaiPhongEntity) query.list().get(0);

		return list;
	}
	
	@RequestMapping(value = "typeroom/index", params = "btnEdit")
	public String edit_TypeRoom(ModelMap model,
			@ModelAttribute("loaiPhong") LoaiPhongEntity loaiPhong) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			System.out.println(loaiPhong.getMALOAI());
			String hql = "update LoaiPhongEntity set TENLOAI = :tenloai , DIENTICH = :dientich , SLNGUOITD = :sltd , DONGIA = :dongia , MOTA =:mota WHERE MALOAI ="+
			String.valueOf(loaiPhong.getMALOAI());
			
			System.out.println(hql);
			Query query = session.createQuery(hql);
			query.setParameter("tenloai", loaiPhong.getTENLOAI());
			query.setParameter("dientich",loaiPhong.getDIENTICH());
			query.setParameter("sltd",loaiPhong.getSLNGUOITD());
			query.setParameter("dongia", loaiPhong.getDONGIA());
			query.setParameter("mota", loaiPhong.getMOTA());

			
			int a = query.executeUpdate();
			model.addAttribute("message", "Update thành công");
			t.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi edit type "+e.getMessage());
			t.rollback();
			e.printStackTrace();
			model.addAttribute("message", "Update thất bại!");
		}
		finally
		{
			session.close();
		}

		 model.addAttribute("loaiPhong", this.getTypeRooms());

		return "room/typeroom";
	}

	
	// Thả dữ liệu vô form
	@RequestMapping(value = "typeroom/index/{id}", params = "linkEdit")
	public String typeRoomEdit(ModelMap model,
			 @PathVariable("id") Integer id,@ModelAttribute("loaiPhong") LoaiPhongEntity loaiPhong) {
		System.out.println("Check");
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("loaiPhong", this.getOneType(id));
		model.addAttribute("formHide",1);
		return "room/typeroom";
	}
	// end edit type room
	
}
