package qlpt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import qlpt.entity.DichVuEntity;

@Transactional
@Controller
@RequestMapping("service/")
public class ServiceController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "index")
	public String index(ModelMap model) {
		List<DichVuEntity> services = getServices();
		model.addAttribute("services", services);
		return "service/index";
	}

	/* Get Service */
	public List<DichVuEntity> getServices() {
		Session session = factory.getCurrentSession();
		String hql = "FROM DichVuEntity";
		Query query = session.createQuery(hql);
		List<DichVuEntity> services = query.list();
		return services;
	}

	public DichVuEntity getService(int MADV) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DichVuEntity where MADV = :MADV";
		Query query = session.createQuery(hql);
		query.setParameter("MADV", MADV);
		DichVuEntity dv = (DichVuEntity) query.list().get(0);
		return dv;
	}

	/* Begin Add Service */
	@RequestMapping("create")
	public String create(ModelMap model, @ModelAttribute("service") DichVuEntity service) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("title", "Thêm dịch vụ");
		return "service/create";
	}

	@RequestMapping(value = "create", params = "btnAdd")
	public String addService(@ModelAttribute("service") DichVuEntity service, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(service);
			t.commit();
			model.addAttribute("message", "Thêm thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm thất bại!");
		} finally {
			session.close();
		}
		model.addAttribute("services", getServices());
		return "service/index";
	}

	/* Update Service */
	@RequestMapping(value = "create/{MADV}", params = "linkEdit")
	public String editService(ModelMap model, @ModelAttribute("service") DichVuEntity service,
			@PathVariable("MADV") Integer MADV) {
		model.addAttribute("service", getService(MADV));
		model.addAttribute("btnStatus", "btnUpdate");
		model.addAttribute("title", "Sửa dịch vụ");
		return "service/create";
	}

	@RequestMapping(value = "create", params = "btnUpdate")
	public String upDateService(ModelMap model, @ModelAttribute("service") DichVuEntity service) {
		Integer t = this.update(service);
		if (t != 0) {
			model.addAttribute("message", "Sửa thành công!");
		} else {
			model.addAttribute("message", "Sửa thất bại!");
		}
		model.addAttribute("services", getServices());
		return "service/index";
	}

	public int update(DichVuEntity dv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(dv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}


	/* Delete Service */
	@RequestMapping(value = "index/{MADV}", params = "linkDelete")
	public String deleteService(ModelMap model,
			@PathVariable("MADV") Integer MADV) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(getService(MADV));
			t.commit();// bắt đầu lưu
			model.addAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại!");
		} finally {
			session.close();
		}
		model.addAttribute("services", getServices());
		return "service/index";
	}

	/* Search Service */
	public List<DichVuEntity> searchServices(String LOAIDV) {
		Session session = factory.openSession();
		String hql = "FROM DichVuEntity where LOAIDV LIKE :LOAIDV";
		Query query = session.createQuery(hql);
		query.setParameter("LOAIDV", "%" + LOAIDV + "%");
		List<DichVuEntity> list = query.list();
		return list;
	}

	@RequestMapping(value = "index", params = "btnSearch")
	public String searchServices(ModelMap model, HttpServletRequest request) {
		System.out.print("Vao ham search");
		List<DichVuEntity> services = searchServices(request.getParameter("searchInput"));
		model.addAttribute("services", services);
		return "service/index";
	}
}
