package qlpt.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "HOPDONG")
public class HopDongEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int MAHD;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NGAYKY;
	
	private double TIENCOC;
	private Boolean DAHUY;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date THOIHAN;
	
	@ManyToOne
	@JoinColumn(name = "MAKT")
	private KhachThueEntity khachThue;
	
	@ManyToOne
	@JoinColumn(name = "MAPHONG")
	private PhongEntity phong;
	
	@OneToMany(mappedBy = "hopDong",fetch = FetchType.EAGER)
	private Collection<HoaDonEntity> dsHoaDon;
	
	@OneToMany(mappedBy = "hopDong",fetch = FetchType.EAGER)
	private Collection<CTDichVuEntity> dsCTDichVu;
	
	@OneToMany(mappedBy = "hopDong",fetch = FetchType.EAGER)
	private Collection<CTKhachThueEntity> dsCTKT;

	public HopDongEntity(int mAHD, Date nGAYKY, double tIENCOC, Boolean dAHUY, Date tHOIHAN, KhachThueEntity khachThue,
			PhongEntity phong, Collection<HoaDonEntity> dsHoaDon, Collection<CTDichVuEntity> dsCTDichVu,
			Collection<CTKhachThueEntity> dsCTKT) {
		super();
		MAHD = mAHD;
		NGAYKY = nGAYKY;
		TIENCOC = tIENCOC;
		DAHUY = dAHUY;
		THOIHAN = tHOIHAN;
		this.khachThue = khachThue;
		this.phong = phong;
		this.dsHoaDon = dsHoaDon;
		this.dsCTDichVu = dsCTDichVu;
		this.dsCTKT = dsCTKT;
	}

	public HopDongEntity() {
		super();
	}

	public int getMAHD() {
		return MAHD;
	}

	public void setMAHD(int mAHD) {
		MAHD = mAHD;
	}

	public Date getNGAYKY() {
		return NGAYKY;
	}

	public void setNGAYKY(Date nGAYKY) {
		NGAYKY = nGAYKY;
	}

	public double getTIENCOC() {
		return TIENCOC;
	}

	public void setTIENCOC(double tIENCOC) {
		TIENCOC = tIENCOC;
	}

	public Boolean getDAHUY() {
		return DAHUY;
	}

	public void setDAHUY(Boolean dAHUY) {
		DAHUY = dAHUY;
	}

	public Date getTHOIHAN() {
		return THOIHAN;
	}

	public void setTHOIHAN(Date tHOIHAN) {
		THOIHAN = tHOIHAN;
	}

	public KhachThueEntity getKhachThue() {
		return khachThue;
	}

	public void setKhachThue(KhachThueEntity khachThue) {
		this.khachThue = khachThue;
	}

	public PhongEntity getPhong() {
		return phong;
	}

	public void setPhong(PhongEntity phong) {
		this.phong = phong;
	}

	public Collection<HoaDonEntity> getDsHoaDon() {
		return dsHoaDon;
	}

	public void setDsHoaDon(Collection<HoaDonEntity> dsHoaDon) {
		this.dsHoaDon = dsHoaDon;
	}

	public Collection<CTDichVuEntity> getDsCTDichVu() {
		return dsCTDichVu;
	}

	public void setDsCTDichVu(Collection<CTDichVuEntity> dsCTDichVu) {
		this.dsCTDichVu = dsCTDichVu;
	}

	public Collection<CTKhachThueEntity> getDsCTKT() {
		return dsCTKT;
	}

	public void setDsCTKT(Collection<CTKhachThueEntity> dsCTKT) {
		this.dsCTKT = dsCTKT;
	}
	
	
	
}
