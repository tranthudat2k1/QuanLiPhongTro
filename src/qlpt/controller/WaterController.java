package qlpt.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import qlpt.entity.CTDichVuEntity;
import qlpt.entity.DichVuEntity;
import qlpt.entity.HopDongEntity;
import qlpt.entity.KhachThueEntity;
import qlpt.entity.NhaTroEntity;
import qlpt.entity.PhongEntity;
import qlpt.entity.QuyDinhEntity;
import qlpt.entity.ThoiGianEntity;
import qlpt.entity.TrangThaiEntity;

@Transactional
@Controller
@RequestMapping("water/")
public class WaterController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model, @ModelAttribute("CTDichVu") CTDichVuEntity ctDIchVu,
			HttpServletRequest request) {
		LocalDate now = LocalDate.now();
		int THANG = now.getMonthValue();
		int NAM = now.getYear();
		ThoiGianEntity t = getThoiGianTheoThangNam(THANG, NAM);
		if (t.getMATG() == 0) {
			this.themThoiGian(THANG, NAM);
		}
		List<CTDichVuEntity> dsCTDV = getDSCTDV_ChuaTonTai(THANG, NAM);
		this.themDSCTDV(dsCTDV);
		model.addAttribute("date", THANG + "/" + NAM);
		model.addAttribute("dsCTDichVu", getCTDVTheoTG(THANG, NAM));
		model.addAttribute("dsTrangThai", getDsTrangThai());
		model.addAttribute("dsNhaTro", getDSNhaTro());
		return "water/index";
	}

	@RequestMapping(value = "index", params = "btnXem")
	public String index1(ModelMap model, HttpServletRequest request,
			@ModelAttribute("CTDichVu") CTDichVuEntity ctDIchVu) {
		List<CTDichVuEntity> dsCTDichVu = new ArrayList<CTDichVuEntity>();
		String dateStr = request.getParameter("date");// 05/2022
		String maNT = request.getParameter("nhaTro");
		String maTTStr = request.getParameter("trangThai");
		int maTTInt = -1;
		int THANG = Integer.parseInt(dateStr.substring(0, dateStr.indexOf("/")));
		int NAM = Integer.parseInt(dateStr.substring(dateStr.indexOf("/") + 1));
		ThoiGianEntity t = getThoiGianTheoThangNam(THANG, NAM);
		/* So sánh thời gian */
		Date d= new Date("01/05/2022");
		System.out.println("So sanh ngay: "+d.compareTo(new Date("01/03/2022")));
		
		if (t.getMATG() == 0) {
			this.themThoiGian(THANG, NAM);
		}
		List<CTDichVuEntity> dsCTDV = getDSCTDV_ChuaTonTai(THANG, NAM);
		this.themDSCTDV(dsCTDV);

		if (maNT.equals("null") && maTTStr.equals("null")) {
			dsCTDichVu = getCTDVTheoTG(THANG, NAM);

		} else if (!maNT.equals("null") && maTTStr.equals("null")) {
			dsCTDichVu = getCTDVTheoTG_MANT(THANG, NAM, maNT);
		} else if (maNT.equals("null") && !maTTStr.equals("null")) {
			maTTInt = Integer.parseInt(maTTStr);
			dsCTDichVu = getCTDVTheoTG_MATT(THANG, NAM, maTTInt);
		} else {
			maTTInt = Integer.parseInt(maTTStr);
			dsCTDichVu = getCTDVTheoTG_MANT_MATT(THANG, NAM, maNT, maTTInt);
		}
		model.addAttribute("dsCTDichVu", dsCTDichVu);
		model.addAttribute("date", dateStr);
		model.addAttribute("nhaTro", maNT);
		model.addAttribute("trangThai", maTTInt);
		model.addAttribute("dsTrangThai", getDsTrangThai());
		model.addAttribute("dsNhaTro", getDSNhaTro());
		return "water/index";
	}

	public List<TrangThaiEntity> getDsTrangThai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM TrangThaiEntity";
		Query query = session.createQuery(hql);
		List<TrangThaiEntity> trangThai = query.list();
		return trangThai;
	}

	public List<HopDongEntity> getDsHopDong() {
		Session session = factory.getCurrentSession();
		String hql = "FROM HopDongEntity where DAHUY = 0";
		Query query = session.createQuery(hql);
		List<HopDongEntity> ds = query.list();
		return ds;
	}

	public List<CTDichVuEntity> getCTDichVu() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDichVuEntity WHERE dichVu.TENDV= :TENDV";
		Query query = session.createQuery(hql);
		query.setParameter("TENDV", "NƯỚC");
		List<CTDichVuEntity> dsDichVu = query.list();
		return dsDichVu;
	}

	public List<CTDichVuEntity> getCTDVTheoTG(int THANG, int NAM) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDichVuEntity WHERE dichVu.TENDV= :TENDV and thoiGian.THANG= :THANG and thoiGian.NAM= :NAM "
				+ "and hopDong.DAHUY = 0";
		Query query = session.createQuery(hql);
		query.setParameter("TENDV", "NƯỚC");
		query.setParameter("THANG", THANG);
		query.setParameter("NAM", NAM);
		List<CTDichVuEntity> dsDichVu = query.list();
		return dsDichVu;
	}

	public List<CTDichVuEntity> getCTDVTheoTG_MANT(int THANG, int NAM, String MANT) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDichVuEntity WHERE dichVu.TENDV= :TENDV and thoiGian.THANG= :THANG and thoiGian.NAM= :NAM "
				+ "and hopDong.DAHUY = 0 and hopDong.phong.nhatro.MANT= :MANT";
		Query query = session.createQuery(hql);
		query.setParameter("TENDV", "NƯỚC");
		query.setParameter("THANG", THANG);
		query.setParameter("NAM", NAM);
		query.setParameter("MANT", MANT);
		List<CTDichVuEntity> dsDichVu = query.list();
		return dsDichVu;
	}

	public List<CTDichVuEntity> getCTDVTheoTG_MATT(int THANG, int NAM, int MATT) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDichVuEntity WHERE dichVu.TENDV= :TENDV and thoiGian.THANG= :THANG and thoiGian.NAM= :NAM "
				+ "and hopDong.DAHUY = 0 and hopDong.phong.trangThai.MATT= :MATT";
		Query query = session.createQuery(hql);
		query.setParameter("TENDV", "NƯỚC");
		query.setParameter("THANG", THANG);
		query.setParameter("NAM", NAM);
		query.setParameter("MATT", MATT);
		List<CTDichVuEntity> dsDichVu = query.list();
		return dsDichVu;
	}

	public List<CTDichVuEntity> getCTDVTheoTG_MANT_MATT(int THANG, int NAM, String MANT, int MATT) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDichVuEntity WHERE dichVu.TENDV= :TENDV and thoiGian.THANG= :THANG and thoiGian.NAM= :NAM "
				+ "and hopDong.DAHUY = 0 and hopDong.phong.nhatro.MANT= :MANT "
				+ "and hopDong.phong.trangThai.MATT= :MATT";
		Query query = session.createQuery(hql);
		query.setParameter("TENDV", "NƯỚC");
		query.setParameter("THANG", THANG);
		query.setParameter("NAM", NAM);
		query.setParameter("MANT", MANT);
		query.setParameter("MATT", MATT);
		List<CTDichVuEntity> dsDichVu = query.list();
		return dsDichVu;
	}

	public List<PhongEntity> getDsPhong() {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhongEntity";
		Query query = session.createQuery(hql);
		List<PhongEntity> dsPhong = query.list();
		return dsPhong;
	}

	public List<KhachThueEntity> getDsKhachThue() {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhachThueEntity";
		Query query = session.createQuery(hql);
		List<KhachThueEntity> dsKhachThue = query.list();
		return dsKhachThue;
	}

	public List<NhaTroEntity> getDSNhaTro() {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaTroEntity";
		Query query = session.createQuery(hql);
		List<NhaTroEntity> dsNhaTro = query.list();
		return dsNhaTro;
	}

	@RequestMapping(value = "save")
	public String addListService(ModelMap model, HttpServletRequest request) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			/* session.save(dsCTDichVu); */
			t.commit();
			model.addAttribute("message", "Thêm thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm thất bại!");
		} finally {
			session.close();
		}
		/* model.addAttribute("dsCTDichVu", getCTDichVu()); */
		return "water/index";
	}

	// ==================================================
	@RequestMapping(value = "saveCTDV")
	public String addService(ModelMap model, HttpServletRequest request) {
		int maHd = Integer.parseInt(request.getParameter("hopDong1"));
		int maDv = Integer.parseInt(request.getParameter("dichVu1"));
		int maTg = Integer.parseInt(request.getParameter("thoiGian1"));
		int csc = Integer.parseInt(request.getParameter("CHISOCU1"));
		int csm = Integer.parseInt(request.getParameter("CHISOMOI1"));
		ThoiGianEntity t = getThoiGianTheoMa(maTg);
		int THANG = t.getTHANG();
		int NAM = t.getNAM();
		if (csc > csm) {
			model.addAttribute("lbThongBaoThemDV", "Chỉ số cũ không được lớn hơn chỉ số mới");
		} else {
			CTDichVuEntity ctdv = getCTDVTheoMa(maHd, maDv, maTg);

			Integer i = this.updateCTDV(ctdv, csc, csm);
			if (i != 0) {
				model.addAttribute("lbThongBaoThemDV", "1");
			} else {
				model.addAttribute("lbThongBaoThemDV", "Lưu thất bại!");
			}
			/* Set chỉ số cũ cho tháng sau */
			ThoiGianEntity t1 = getThoiGianTheoThangNam(THANG + 1, NAM);
			ThoiGianEntity tg1 = new ThoiGianEntity();
			CTDichVuEntity ctdv1 = new CTDichVuEntity();
			if (t1.getMATG() == 0) {
				this.themThoiGian(THANG + 1, NAM);
				tg1 = getThoiGianTheoThangNam(THANG + 1, NAM);
				ctdv1 = getCTDVTheoMa(maHd, maDv, tg1.getMATG());
			} else {
				ctdv1 = getCTDVTheoMa(maHd, maDv, t1.getMATG());
			}

			if (ctdv1 != null && ctdv1.getCHISOCU() == 0) {
				Integer i1 = this.updateCTDVTheoCSC(ctdv1, csm);
			} else if(ctdv1==null) {
				CTDichVuEntity ctdv2 = new CTDichVuEntity(getService(maDv), getHopDongTheoMa(maHd),
						getThoiGianTheoThangNam(THANG + 1, NAM), csm, 0);
				this.themCTDV(ctdv2);
			}
		}
		List<CTDichVuEntity> dsCTDichVu = new ArrayList<CTDichVuEntity>();

		String maNT = request.getParameter("nhaTro");
		String maTTStr = request.getParameter("trangThai");
		int maTTInt = -1;
		if (maNT == null && maTTStr == null) {
			dsCTDichVu = getCTDVTheoTG(THANG, NAM);

		} else if (maNT != null && maTTStr == null) {
			dsCTDichVu = getCTDVTheoTG_MANT(THANG, NAM, maNT);
		} else if (maNT == null && maTTStr != null) {
			maTTInt = Integer.parseInt(maTTStr);
			dsCTDichVu = getCTDVTheoTG_MATT(THANG, NAM, maTTInt);
		} else {
			maTTInt = Integer.parseInt(maTTStr);
			dsCTDichVu = getCTDVTheoTG_MANT_MATT(THANG, NAM, maNT, maTTInt);
		}

		model.addAttribute("dsCTDichVu", dsCTDichVu);
		model.addAttribute("date", THANG + "/" + NAM);
		model.addAttribute("nhaTro", maNT);
		model.addAttribute("trangThai", maTTInt);
		model.addAttribute("dsTrangThai", getDsTrangThai());
		model.addAttribute("dsNhaTro", getDSNhaTro());
		return "water/index";
	}

	// ==================================
	public DichVuEntity getService(int maDV) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DichVuEntity where MADV = :MADV";
		Query query = session.createQuery(hql);
		query.setParameter("MADV", maDV);
		DichVuEntity dv = new DichVuEntity();
		if (query.list().size() == 0) {
			return null;
		} else {
			dv = (DichVuEntity) query.list().get(0);
		}
		return dv;
	}

	public int getMaDVNuoc() {
		Session session = factory.getCurrentSession();
		String hql = "FROM DichVuEntity where TENDV = :TENDV";
		Query query = session.createQuery(hql);
		query.setParameter("TENDV", "NƯỚC");
		DichVuEntity dv = new DichVuEntity();
		if (query.list().size() == 0) {
			return -1;
		} else {
			dv = (DichVuEntity) query.list().get(0);
		}
		return dv.getMADV();
	}

	public ThoiGianEntity getThoiGianTheoMa(int MATG) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ThoiGianEntity where MATG = :MATG";
		Query query = session.createQuery(hql);
		query.setParameter("MATG", MATG);
		ThoiGianEntity dv = (ThoiGianEntity) query.list().get(0);
		return dv;
	}

	public List<ThoiGianEntity> getDsThoiGian() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ThoiGianEntity";
		Query query = session.createQuery(hql);
		List<ThoiGianEntity> dv = query.list();
		return dv;
	}

	public HopDongEntity getHopDongTheoMa(int MAHOPDONG) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HopDongEntity where MAHOPDONG = :MAHOPDONG";
		Query query = session.createQuery(hql);
		query.setParameter("MAHOPDONG", MAHOPDONG);
		HopDongEntity dv = (HopDongEntity) query.list().get(0);
		return dv;
	}

	public CTDichVuEntity getCTDVTheoMa(Integer MAHOPDONG, Integer MADV, Integer MATG) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDichVuEntity where MAHOPDONG = :MAHOPDONG and MADV= :MADV and MATG= :MATG";
		Query query = session.createQuery(hql);
		query.setParameter("MAHOPDONG", MAHOPDONG);
		query.setParameter("MADV", MADV);
		query.setParameter("MATG", MATG);
		CTDichVuEntity dv = new CTDichVuEntity();
		if (query.list().size() == 0) {
			return null;
		} else {
			dv = (CTDichVuEntity) query.list().get(0);
		}
		return dv;
	}

	public ThoiGianEntity getThoiGianTheoThangNam(int THANG, int NAM) {
		List<ThoiGianEntity> dsTG = getDsThoiGian();
		ThoiGianEntity tg = new ThoiGianEntity();
		for (ThoiGianEntity t : dsTG) {
			if (t.getTHANG() == THANG && t.getNAM() == NAM) {
				tg = t;
			}
		}
		return tg;
	}

	public List<CTDichVuEntity> getDSCTDV_ChuaTonTai(int THANG, int NAM) {
		List<HopDongEntity> dsHD = getDsHopDong();
		List<CTDichVuEntity> dsCTDV = getCTDichVu();
		List<CTDichVuEntity> ds = new ArrayList<CTDichVuEntity>();
		int maDVNuoc = getMaDVNuoc();
		ThoiGianEntity tg = getThoiGianTheoThangNam(THANG, NAM);
		/*Lấy những hợp đồng có tồn tại dịch vụ nước trong quy định*/
		for(int i=0;i<dsHD.size();i++) {
			boolean kt1=false;
			List<QuyDinhEntity> dsQDTmp=(List<QuyDinhEntity>) dsHD.get(i).getPhong().getNhatro().getDsQuyDinh();
			for(QuyDinhEntity q:dsQDTmp) {
				if(q.getDichVu().getMADV()==maDVNuoc) {
					kt1=true;
					break;
				}
			}
			if(!kt1) {
				dsHD.remove(i);
				i--;
			}
		}
		boolean kt;
		for (HopDongEntity h : dsHD) {
			kt = true;
			for (CTDichVuEntity c : dsCTDV) {
				if (h.getMAHOPDONG() == c.getHopDong().getMAHOPDONG() && c.getThoiGian().getTHANG() == THANG
						&& c.getThoiGian().getNAM() == NAM) {
					kt = false;
					break;
				}
			}
			if (kt) {
				ds.add(new CTDichVuEntity(getService(getMaDVNuoc()), h, tg, 0, 0));
			}
		}
		return ds;
	}

	public Integer themThoiGian(int THANG, int NAM) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		ThoiGianEntity tg = new ThoiGianEntity(THANG, NAM);
		try {
			session.save(tg);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer updateCTDV(CTDichVuEntity ctdv, int csc, int csm) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Query query = session.createQuery("update CTDichVuEntity " + "set CHISOCU= " + csc + ", CHISOMOI= " + csm
					+ "  where hopDong.MAHOPDONG= " + ctdv.getHopDong().getMAHOPDONG() + " and thoiGian.MATG= "
					+ ctdv.getThoiGian().getMATG() + " and dichVu.MADV= " + ctdv.getDichVu().getMADV());
			int update = query.executeUpdate();
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer updateCTDVTheoCSC(CTDichVuEntity ctdv, int csc) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Query query = session.createQuery("update CTDichVuEntity " + "set CHISOCU= " + csc
					+ "  where hopDong.MAHOPDONG= " + ctdv.getHopDong().getMAHOPDONG() + " and thoiGian.MATG= "
					+ ctdv.getThoiGian().getMATG() + " and dichVu.MADV= " + ctdv.getDichVu().getMADV());
			int update = query.executeUpdate();
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer themDSCTDV(List<CTDichVuEntity> dsCt) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			for (CTDichVuEntity c : dsCt) {
				session.save(c);
			}
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer themCTDV(CTDichVuEntity ctdv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(ctdv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
}
