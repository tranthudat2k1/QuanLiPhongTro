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
import org.springframework.web.bind.annotation.RequestMapping;

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
	public Integer updateRoom(PhongEntity room) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(room);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@RequestMapping(value = "index", params = "btnEdit")
	public String edit_Product(ModelMap model,
			@ModelAttribute("room") PhongEntity room) {

		Integer temp = this.updateRoom(room);
		if (temp != 0) {
			model.addAttribute("message", "Update thành công");
		} else {
			model.addAttribute("message", "Update thất bại!");
		}

		 model.addAttribute("products", this.getRooms());

		return "Lesson6/products";
	}
	//End Edit room
}
