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

import qlpt.entity.ChuTroEntity;
import qlpt.entity.KhuEntity;



@Transactional
@Controller
@RequestMapping("area/")
public class AreaController {
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index(ModelMap model,@ModelAttribute("area") KhuEntity area )
	{
		
		List<KhuEntity> areas = this.getAreas();
		model.addAttribute("areas",areas);
		model.addAttribute("btnStatus","btnAdd");
		return "area/index";
	}
	
	// GET AREA
	public List<KhuEntity> getAreas() {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuEntity";
		Query query = session.createQuery(hql);
		List<KhuEntity> list = query.list();

		return list;
	}
	
	
	public KhuEntity getArea(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuEntity where MAKHU = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		KhuEntity list = (KhuEntity) query.list().get(0);
		System.out.println("in o getArea "+list.toString());

		return list;
	}
	
	@ModelAttribute("ChuTroSelect")
	public List<ChuTroEntity> selectChuTro()
	{
		Session session = factory.getCurrentSession();
		String hql = "FROM ChuTroEntity";
		Query query = session.createQuery(hql);
		List<ChuTroEntity> list = query.list();
		return list;
	}
	// FINISH GET AREA
	
	// ADD AREA 
	
	public Integer insertArea(KhuEntity khu) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(khu);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@RequestMapping(value="index",params = "btnAdd")
	public String addArea(ModelMap model,@ModelAttribute("area") KhuEntity area)
	{
		
		model.addAttribute("areas",this.getAreas());
		area.setMAKHU("MK213");
		Integer temp = this.insertArea(area);
		if(temp != 0)
				model.addAttribute("message", "Thêm thành công");
		else
			model.addAttribute("message", "Thêm thất bại!");
		model.addAttribute("rooms",this.getAreas());
		return "area/index";
	}
	// FINISH ADD AREA 
//	// DELETE AREA
	public Integer deleteArea(KhuEntity area) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		System.out.println(area.toString());
		try {
			session.delete(area);
			t.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	public Integer deleteArea(String id) {
		Session session1 = factory.openSession();
		Transaction t = session1.beginTransaction();
		KhuEntity area = this.getArea(id);
		System.out.println(area.toString());
		try {
			session1.delete(area);
			t.commit();
		} catch (Exception e) {
			System.out.println("Lỗi "+e.toString());
			t.rollback();
			return 0;
		} finally {
			session1.close();
		}
		return 1;
	}
	//	
	@RequestMapping(value="index/{id}.htm",params = "linkDelete")
	public String deleteArea(ModelMap model,@ModelAttribute("area") KhuEntity area,@PathVariable("id") String id)
	{
		/*
		 * Integer temp = this.deleteArea(getArea(id)); System.out.println(temp); if
		 * (temp != 0) { model.addAttribute("message", "Delete thành công"); } else {
		 * model.addAttribute("message", "Delete thất bại!"); }
		 * 
		 * model.addAttribute("areas", this.getAreas());
		 */
		Session session1 = factory.openSession();
		Transaction t = session1.beginTransaction();
		try {
			KhuEntity khu = (KhuEntity) session1.load(KhuEntity.class, id);
			session1.delete(khu);
			t.commit();
			model.addAttribute("message", "Delete thành công");
		} catch (Exception e) {
			System.out.println("Lỗi "+e.toString());
			t.rollback();
			 model.addAttribute("message", "Delete thất bại!");
		} finally {
			session1.close();
		}
		return "area/index";
	}
//	// FINISH DELETE AREA
}
