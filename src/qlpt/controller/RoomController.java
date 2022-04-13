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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import qlpt.entity.KhuEntity;
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
		model.addAttribute("btnStatus","btnAdd");
		
		return "room/index";
	}
	
	public List<PhongEntity> getRooms() {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhongEntity";
		Query query = session.createQuery(hql);
		List<PhongEntity> list = query.list();
		return list;
	}
	
	@ModelAttribute("KhuSelect")
	public List<KhuEntity> getKhus()
	{
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuEntity";
		Query query = session.createQuery(hql);
		List<KhuEntity> list = query.list();

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
	public String addRoom(@ModelAttribute("room") PhongEntity room,ModelMap model)
	{
		System.out.println(room.toString());
		Integer temp = this.insertRoom(room);
		if(temp != 0)
				model.addAttribute("message", "Thêm thành công");
		else
			model.addAttribute("message", "Thêm thất bại!");
		model.addAttribute("rooms",this.getRooms());
		room.setSLNGUOITOIDA(0);
		room.setDONGIA(0);
		room.setMOTA("");
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
	public Integer updateRoom(PhongEntity room) {
		System.out.println(room.toString());
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(room);
			t.commit();
		} catch (Exception e) {
			System.out.println("LỖi : "+e.toString());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	// lưu thay đổi
	@RequestMapping(value = "index", params = "btnEdit")
	public String edit_Room(ModelMap model,
			@ModelAttribute("room") PhongEntity room) {

		Integer temp = this.updateRoom(room);
		
		System.out.println(temp);
		if (temp != 0) {
			model.addAttribute("message", "Update thành công");
		} else {
			model.addAttribute("message", "Update thất bại!");
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

		return "room/index";
	}
	//Finish Edit room
	
	// DELETE ROOM
	public Integer deleteRoom(PhongEntity room) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		System.out.println(room.toString());
		try {
			session.delete(room);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@RequestMapping(value = "/index/{id}.htm", params = "linkDelete")
	public String deleteRoom( ModelMap model, @ModelAttribute("room") PhongEntity room,
			@PathVariable("id") Integer id) {
		 
		Integer temp = this.deleteRoom(this.getRoom(id));
		System.out.println(temp);
		if (temp != 0) {
			model.addAttribute("message", "Delete thành công");
		} else {
			model.addAttribute("message", "Delete thất bại!");
		}
		
		model.addAttribute("rooms", this.getRooms());
		
		return "room/index";
	}
	// FINISH DELETE ROOM
}
