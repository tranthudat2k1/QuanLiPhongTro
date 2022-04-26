package qlpt.controller;

import java.util.List;
import java.util.Random;

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

import qlpt.entity.CTDichVuEntity;
import qlpt.entity.DichVuEntity;
import qlpt.entity.HopDongEntity;
import qlpt.entity.KhachThueEntity;
import qlpt.entity.NhaTroEntity;
import qlpt.entity.PhongEntity;
import qlpt.entity.TrangThaiEntity;

@Transactional
@Controller
@RequestMapping("electricity/")
public class ElectricityController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("index")
	public String index(ModelMap model) {
		model.addAttribute("dsCTDichVu", getCTDichVu());
		model.addAttribute("dsTrangThai", getDsTrangThai());
		model.addAttribute("dsNhaTro",getDSNhaTro());
		return "electricity/index1";
	}

	public List<TrangThaiEntity> getDsTrangThai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM TrangThaiEntity";
		Query query = session.createQuery(hql);
		List<TrangThaiEntity> trangThai = query.list();
		return trangThai;
	}

	public List<CTDichVuEntity> getCTDichVu() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDichVuEntity";
		Query query = session.createQuery(hql);
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
	public List<NhaTroEntity> getDSNhaTro(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaTroEntity";
		Query query = session.createQuery(hql);
		List<NhaTroEntity> dsNhaTro = query.list();
		return dsNhaTro;
	}
	
	@RequestMapping(value = "save")
	public String addService(@ModelAttribute("dsCTDichVu") List<CTDichVuEntity> dsCTDichVu, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(dsCTDichVu);
			t.commit();
			model.addAttribute("message", "Thêm thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm thất bại!");
		} finally {
			session.close();
		}
		model.addAttribute("dsCTDichVu", getCTDichVu());
		return "electricity/index1";
	}

//	Tạo mã hóa đơn ngẫu nhiên
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
	private static final String alphaUpperCase = alpha.toUpperCase();
	private static final String digits = "0123456789";
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static Random generator = new Random();

	public String randomAlphaNumeric(int numberOfCharactor) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfCharactor; i++) {
			int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
			char ch = ALPHA_NUMERIC.charAt(number);
			sb.append(ch);
		}
		return sb.toString();
	}

	public static int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}
}
