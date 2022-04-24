package qlpt.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOAIPHONG")
public class LoaiPhongEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer MANT;
	
	private String TENLOAI;
	private double DIENTICH;
	private Integer SLNGUOITD;
	private double DONGIA;
	private String MOTA;
	
	@OneToMany(mappedBy = "loaiPhong",fetch = FetchType.EAGER)
	Collection<PhongEntity> dsPhong;

	public LoaiPhongEntity(Integer mANT, String tENLOAI, double dIENTICH, Integer sLNGUOITD, double dONGIA, String mOTA,
			Collection<PhongEntity> dsPhong) {
		super();
		MANT = mANT;
		TENLOAI = tENLOAI;
		DIENTICH = dIENTICH;
		SLNGUOITD = sLNGUOITD;
		DONGIA = dONGIA;
		MOTA = mOTA;
		this.dsPhong = dsPhong;
	}

	public LoaiPhongEntity() {
		super();
	}

	public Integer getMANT() {
		return MANT;
	}

	public void setMANT(Integer mANT) {
		MANT = mANT;
	}

	public String getTENLOAI() {
		return TENLOAI;
	}

	public void setTENLOAI(String tENLOAI) {
		TENLOAI = tENLOAI;
	}

	public double getDIENTICH() {
		return DIENTICH;
	}

	public void setDIENTICH(double dIENTICH) {
		DIENTICH = dIENTICH;
	}

	public Integer getSLNGUOITD() {
		return SLNGUOITD;
	}

	public void setSLNGUOITD(Integer sLNGUOITD) {
		SLNGUOITD = sLNGUOITD;
	}

	public double getDONGIA() {
		return DONGIA;
	}

	public void setDONGIA(double dONGIA) {
		DONGIA = dONGIA;
	}

	public String getMOTA() {
		return MOTA;
	}

	public void setMOTA(String mOTA) {
		MOTA = mOTA;
	}

	public Collection<PhongEntity> getDsPhong() {
		return dsPhong;
	}

	public void setDsPhong(Collection<PhongEntity> dsPhong) {
		this.dsPhong = dsPhong;
	}
	
	
}
