package qlpt.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;

import qlpt.entity.CTDichVuEntity;
import qlpt.entity.DichVuEntity;
import qlpt.entity.NhaTroEntity;
import qlpt.entity.QuyDinhEntity;

@Transactional
@Controller
@RequestMapping("service/")
public class ServiceController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<DichVuEntity> services = getServices();
		model.addAttribute("services", services);
		model.addAttribute("cbNhaTro", getDSNhaTro());
		model.addAttribute("nhatro", new NhaTroEntity());
		return "service/index1";
	}

	@RequestMapping(value = "index", method = RequestMethod.POST)
	public String index1(ModelMap model, @ModelAttribute("nhatro") NhaTroEntity nhaTro) {
		List<DichVuEntity> services = getServices();
		model.addAttribute("cbNhaTro", getDSNhaTro());
		model.addAttribute("services", getServices());
		model.addAttribute("MANT", nhaTro.getMANT());
		return "service/index1";
	}

	public List<NhaTroEntity> getDSNhaTro() {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaTroEntity";
		Query query = session.createQuery(hql);
		List<NhaTroEntity> nhaTros = query.list();
		return nhaTros;
	}

	public NhaTroEntity getNhaTroTheoMa(String MANT) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaTroEntity where MANT=:MANT";
		Query query = session.createQuery(hql);
		query.setParameter("MANT", MANT);
		NhaTroEntity nhaTro = (NhaTroEntity) query.list().get(0);
		return nhaTro;
	}

	/* Get Service */
	public List<DichVuEntity> getServices() {
		Session session = factory.getCurrentSession();
		String hql = "FROM DichVuEntity";
		Query query = session.createQuery(hql);
		List<DichVuEntity> services = query.list();
		return services;
	}

	public DichVuEntity getService(Integer maDV) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DichVuEntity where MADV = :MADV";
		Query query = session.createQuery(hql);
		query.setParameter("MADV", maDV);
		DichVuEntity dv = (DichVuEntity) query.list().get(0);
		return dv;
	}

	public QuyDinhEntity getQuyDinh(Integer MADV, String MANT) {
		Session session = factory.getCurrentSession();
		String hql = "FROM QuyDinhEntity where MADV = :MADV AND MANT= :MANT";
		Query query = session.createQuery(hql);
		query.setParameter("MADV", MADV);
		query.setParameter("MANT", MANT);
		QuyDinhEntity dv = (QuyDinhEntity) query.list().get(0);
		return dv;
	}

	public List<QuyDinhEntity> getDsQuyDinh() {
		Session session = factory.getCurrentSession();
		String hql = "FROM QuyDinhEntity";
		Query query = session.createQuery(hql);
		List<QuyDinhEntity> services = query.list();
		return services;
	}

	/* Begin Add Service */
	@RequestMapping("create")
	public String create(ModelMap model, @ModelAttribute("service") DichVuEntity service) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("cbNhaTro", getDSNhaTro());
		model.addAttribute("cbDichVu", getServices());
		model.addAttribute("abc", getDsQuyDinh());
		model.addAttribute("title", "Thêm dịch vụ");
		return "service/create1";
	}

	@RequestMapping(value = "create", params = "btnAdd", method = RequestMethod.POST)
	public String addService(@ModelAttribute("service") DichVuEntity service, ModelMap model,
			HttpServletRequest resquest) {
		String maDVStr = resquest.getParameter("MADV1");
		String maNT = resquest.getParameter("MANT");
		if(resquest.getParameter("dongia").equals("")) {
			model.addAttribute("lbTBDonGiaNull", "Đơn giá không được bỏ trống!");
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("cbNhaTro", getDSNhaTro());
			model.addAttribute("cbDichVu", getServices());
			model.addAttribute("abc", getDsQuyDinh());
			model.addAttribute("title", "Thêm dịch vụ");
			return "service/create1";
		}
		Double donGia = Double.parseDouble(resquest.getParameter("dongia"));
		String mota= resquest.getParameter("mota");
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {

			Integer maDVInt;
			if (!maDVStr.equals("isSelected")) {
				System.out.println("Có dịch vụ");
				maDVInt = Integer.parseInt(maDVStr);
				DichVuEntity sv = getService(maDVInt);
				boolean kt = ktDV_THUOC_NT(sv.getMADV(), maNT);
				if (kt) {
					model.addAttribute("lbTBTenDVTrung", "Dịch vụ đã có trong nhà trọ "+ getNhaTroTheoMa(maNT).getTENNT() +"!");
					model.addAttribute("btnStatus", "btnAdd");
					model.addAttribute("cbNhaTro", getDSNhaTro());
					model.addAttribute("cbDichVu", getServices());
					model.addAttribute("abc", getDsQuyDinh());
					model.addAttribute("title", "Thêm dịch vụ");
					return "service/create1";
				} else {
				QuyDinhEntity qd= new QuyDinhEntity(getNhaTroTheoMa(maNT), sv, donGia, mota, date);
				session.save(qd);
				}
			} else {
				if(service.getTENDV().equals("")) {
					model.addAttribute("lbTBTenDVTrung", "Tên dịch vụ không được bỏ trống!");
					model.addAttribute("btnStatus", "btnAdd");
					model.addAttribute("cbNhaTro", getDSNhaTro());
					model.addAttribute("cbDichVu", getServices());
					model.addAttribute("abc", getDsQuyDinh());
					model.addAttribute("title", "Thêm dịch vụ");
					return "service/create1";
				}
				boolean kt = ktTenDV(service.getTENDV());
				if (kt) {
					model.addAttribute("lbTBTenDVTrung", "Tên dịch vụ đã tồn tại!");
					model.addAttribute("btnStatus", "btnAdd");
					model.addAttribute("cbNhaTro", getDSNhaTro());
					model.addAttribute("cbDichVu", getServices());
					model.addAttribute("abc", getDsQuyDinh());
					model.addAttribute("title", "Thêm dịch vụ");
					return "service/create1";
				} else {QuyDinhEntity qd = new QuyDinhEntity(getNhaTroTheoMa(maNT), service, donGia,mota, date);
					service.setTENDV(service.getTENDV().toUpperCase());
					session.save(service);
					session.save(qd);
				}
			}
			t.commit();
			model.addAttribute("message", "Thêm thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm thất bại!");
		} finally {
			session.close();
		}
		model.addAttribute("services", getServices());
		model.addAttribute("cbNhaTro", getDSNhaTro());
		model.addAttribute("nhatro", new NhaTroEntity());
		return "service/index1";
	}

	/* Update Service */
	@RequestMapping(value = "create/{MADV}", params = "linkEdit")
	public String editService(ModelMap model, @ModelAttribute("service") DichVuEntity service,
			@PathVariable("MADV") Integer MADV, HttpServletRequest request) {
		model.addAttribute("cbNhaTro", getDSNhaTro());
		model.addAttribute("service", getService(MADV));
		model.addAttribute("btnStatus", "btnUpdate");
		model.addAttribute("title", "Sửa dịch vụ ");
		model.addAttribute("type", 1);
		model.addAttribute("dongia", request.getParameter("dongia"));
		model.addAttribute("mota", request.getParameter("mota"));
		model.addAttribute("MANT", request.getParameter("MANT"));
		return "service/create1";
	}

	@RequestMapping(value = "create", params = "btnUpdate")
	public String upDateService(ModelMap model, @ModelAttribute("service") DichVuEntity service,
			HttpServletRequest request) {
		String maNT = request.getParameter("MANT");
		String mota=request.getParameter("mota");
		if(request.getParameter("dongia").equals("")) {
			model.addAttribute("lbTBDonGiaNull", "Đơn giá không được bỏ trống!");
			model.addAttribute("cbNhaTro", getDSNhaTro());
			model.addAttribute("service", getService(service.getMADV()));
			model.addAttribute("btnStatus", "btnUpdate");
			model.addAttribute("title", "Sửa dịch vụ ");
			model.addAttribute("type", 1);
			model.addAttribute("dongia", request.getParameter("dongia"));
			model.addAttribute("mota", request.getParameter("mota"));
			model.addAttribute("MANT", request.getParameter("MANT"));
			return "service/create1";
		}
		Double donGia = Double.parseDouble(request.getParameter("dongia"));
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		QuyDinhEntity qd = new QuyDinhEntity(getNhaTroTheoMa(maNT), service, donGia,mota, date);
		Integer t2 = this.updateQuyDinh(qd);
		if (t2 != 0) {
			model.addAttribute("message", "Sửa thành công!");
		} else {
			model.addAttribute("message", "Sửa thất bại!");
		}
		model.addAttribute("services", getServices());
		model.addAttribute("cbNhaTro", getDSNhaTro());
		model.addAttribute("nhatro", new NhaTroEntity());
		return "service/index1";
	}

	public int updateDichVu(DichVuEntity dv) {
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

	public int updateQuyDinh(QuyDinhEntity qd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(qd);
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
	public String deleteService(ModelMap model, @PathVariable("MADV") Integer MADV, HttpServletRequest request) {
		String MANT = request.getParameter("MANT");
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(getQuyDinh(MADV, MANT));
			t.commit();// bắt đầu lưu
			model.addAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại!");
		} finally {
			session.close();
		}
		model.addAttribute("services", getServices());
		model.addAttribute("cbNhaTro", getDSNhaTro());
		model.addAttribute("nhatro", new NhaTroEntity());
		return "service/index1";
	}

	/* Search Service */
	public List<DichVuEntity> searchServices(String TENDV) {
		Session session = factory.openSession();
		String hql = "FROM DichVuEntity where TENDV LIKE :TENDV";
		Query query = session.createQuery(hql);
		query.setParameter("TENDV", "%" + TENDV + "%");
		List<DichVuEntity> list = query.list();
		return list;
	}

	@RequestMapping(value = "index", params = "btnSearch")
	public String searchServices(ModelMap model, HttpServletRequest request,
			@ModelAttribute("nhatro") NhaTroEntity nhaTro) {
		System.out.println("NHa tro search: " + nhaTro.getMANT());
		model.addAttribute("services", searchServices(request.getParameter("searchInput")));
		model.addAttribute("cbNhaTro", getDSNhaTro());
		model.addAttribute("MANT", nhaTro.getMANT());
		return "service/index1";
	}

	public boolean ktTenDV(String tenDV) {
		List<DichVuEntity> dsDichVu = getServices();
		for (DichVuEntity d : dsDichVu) {
			if (d.getTENDV().toLowerCase().equals(tenDV.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	public boolean ktDV_THUOC_NT(int maDV, String maNT) {
		List<QuyDinhEntity> dsQuyDinh = getDsQuyDinh();
		for (QuyDinhEntity d : dsQuyDinh) {
			if(d.getDichVu().getMADV()== maDV && d.getNhaTro().getMANT().equals(maNT)) {
				return true;
			}
		}
		return false;
	}

}
