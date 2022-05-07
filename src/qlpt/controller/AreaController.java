package qlpt.controller;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
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
import qlpt.entity.NhaTroEntity;
import qlpt.entity.PhongEntity;



@Transactional
@Controller
@RequestMapping("area/")
public class AreaController{
	
	@Autowired
	SessionFactory factory;
	
	public String mact;
	
	@RequestMapping("index")
	public String index(ModelMap model,@ModelAttribute("area") NhaTroEntity area,HttpSession ss )
	{
		mact = ss.getAttribute("mact").toString();
		
		List<NhaTroEntity> areas = this.getAreas(mact);
		model.addAttribute("areas",areas);

		model.addAttribute("formHide",null);
		return "area/index";
	}
	@RequestMapping(value="index",params = "linkAdd")
	public String redirectLinkAdd(ModelMap model,@ModelAttribute("area") NhaTroEntity area )
	{
		model.addAttribute("btnStatus","btnAdd");
		model.addAttribute("formHide",1);
		return "area/index";
	}
	// GET AREA
	public List<NhaTroEntity> getAreas() {
		Session session = factory.getCurrentSession();
	

		String hql = "FROM NhaTroEntity WHERE MACT = :mact";
		Query query = session.createQuery(hql);
		query.setParameter("mact", mact);
		List<NhaTroEntity> list = query.list();
		return list;
	}
	public List<NhaTroEntity> getAreas(String mact) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaTroEntity WHERE MACT = :mact";
		Query query = session.createQuery(hql);
		query.setParameter("mact", mact);
		List<NhaTroEntity> list = query.list();
		return list;
	}
	public ChuTroEntity getCT(String id) {
		System.out.println("checker chutroentity"+id);
		Session session = factory.getCurrentSession();
		String hql = "FROM ChuTroEntity WHERE MACT = :MACT";
		Query query = session.createQuery(hql);
		query.setParameter("MACT",id);
		ChuTroEntity nt = (ChuTroEntity) query.list().get(0);
		System.out.println(nt.toString());
		return nt;
	}
	
	public NhaTroEntity getArea(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaTroEntity where MANT = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhaTroEntity list = (NhaTroEntity) query.list().get(0);
		System.out.println("in o getArea "+list.toString());

		return list;
	}
	
	// FINISH GET AREA
	
	// ADD AREA 
	
	public Integer insertArea(NhaTroEntity area) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		System.out.println(area.toString());
		try {
			session.save(area);
			t.commit();
		} catch (Exception e) {
			System.out.println("Loi area "+e.getMessage());
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@RequestMapping(value="index",params = "btnAdd")
	public String addArea(ModelMap model,@ModelAttribute("area") NhaTroEntity area)
	{
		System.out.println("check add area "+this.getCT(mact).toString());
		model.addAttribute("areas",this.getAreas());
		String uuid = UUID.randomUUID().toString();
//		area.setMANT("MK"+getAreas().size()+100);
		area.setMANT(uuid);
		area.setChutro(this.getCT(mact));
		Integer temp = this.insertArea(area);
		if(temp != 0)
				model.addAttribute("message", "Thêm thành công");
		else
			model.addAttribute("message", "Thêm thất bại!");
		model.addAttribute("areas",this.getAreas());
		return "area/index";
	}
	// FINISH ADD AREA 
	
	// EDIT NHATRO
	@RequestMapping(value = "index", params = "btnEdit")
	public String edit_NhaTro(ModelMap model,
			@ModelAttribute("area") NhaTroEntity area) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
//			System.out.println(area.getMANT());
//			String hql = "update NhaTroEntity set TENNT = :tennt, TINH_TP = :tinhtp, QUAN_HUYEN = :quanhuyen, PHUONG_XA = :phuongxa,DIACHI = :diachi,MACT = :mact where MANT = "
//					+ String.valueOf(area.getMANT());
//			Query query = session.createQuery(hql);
//			query.setParameter("tennt",area.getTENNT());
//			query.setParameter("tinhtp",area.getTINH_TP());
//			query.setParameter("quanhuyen",area.getQUAN_HUYEN());
//			query.setParameter("phuongxa", area.getPHUONG_XA());
//			query.setParameter("diachi", area.getDIACHI());
//			query.setParameter("mact", area.getChutro().getMACT());
//			int a = query.executeUpdate();
			session.update(area);
			model.addAttribute("message", "Update thành công");
			t.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
			System.out.println("Loi sua nha tro " + e.getMessage());
			model.addAttribute("message", "Update thất bại!");
		}
		finally
		{
			session.close();
		}
		 model.addAttribute("areas", this.getAreas());

		return "area/index";
	}
	@RequestMapping(value="index/{id}",params = "linkEdit")
	public String editNhaTro(@ModelAttribute("area") NhaTroEntity area,ModelMap model,@PathVariable("id") String id)
	{
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("area", this.getArea(id));
		model.addAttribute("areas", this.getAreas());
		model.addAttribute("formHide",1);
		return "area/index";
	}
	// FINISH EDIT NHATRO
//	// DELETE AREA
	@RequestMapping(value="index/{id}.htm",params = "linkDelete")
	public String deleteArea(ModelMap model,@ModelAttribute("area") NhaTroEntity area,@PathVariable("id") String id)
	{
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		  String hql = "FROM NhaTroEntity where MANT = :id";	
		try {
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			NhaTroEntity nt = (NhaTroEntity) query.list().get(0);
			session.delete(nt);
			t.commit();
			String success = "Xóa thành công Nhà trọ "+id+" !";
			model.addAttribute("message", success);
			model.addAttribute("areas",this.getAreas());
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			t.rollback();
			String failure = "Xóa thất bại nhà trọ "+id+" !";
			model.addAttribute("message", failure);
		} finally {
			session.close();
		}
		model.addAttribute("areas", this.getAreas());
		return "area/index";
	}
//	// FINISH DELETE AREA
}
