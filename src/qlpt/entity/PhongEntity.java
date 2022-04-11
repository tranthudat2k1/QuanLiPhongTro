package qlpt.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PHONG")
public class PhongEntity implements Serializable{
	@Id
	@GeneratedValue
	private int MAPHONG;
	private int SLNGUOITOIDA;
	private double DONGIA;
	private String MOTA;
	
	@ManyToOne
	@JoinColumn(name = "MATT")
	TrangThaiEntity TRANGTHAI;
	
	@ManyToOne
	@JoinColumn(name = "MAKHU")
	private KhuEntity KHU;
	
	@OneToMany(mappedBy = "PHONG", fetch = FetchType.EAGER)
	private List<CTDichVuEntity> dsCTDichVu;

	@OneToMany(mappedBy = "PHONG", fetch = FetchType.EAGER)
	private List<HopDongEntity> dsHopDong;
	
	public int getMAPHONG() {
		return MAPHONG;
	}

	public void setMAPHONG(int mAPHONG) {
		MAPHONG = mAPHONG;
	}

	public int getSLNGUOITOIDA() {
		return SLNGUOITOIDA;
	}

	public void setSLNGUOITOIDA(int sLNGUOITOIDA) {
		SLNGUOITOIDA = sLNGUOITOIDA;
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

	public TrangThaiEntity getTRANGTHAI() {
		return TRANGTHAI;
	}

	public void setTRANGTHAI(TrangThaiEntity tRANGTHAI) {
		TRANGTHAI = tRANGTHAI;
	}

	public KhuEntity getKHU() {
		return KHU;
	}

	public void setKHU(KhuEntity kHU) {
		KHU = kHU;
	}

	public List<CTDichVuEntity> getDsDichVu() {
		return dsCTDichVu;
	}

	public void setDsDichVu(List<CTDichVuEntity> dsDichVu) {
		this.dsCTDichVu = dsDichVu;
	}

	public List<HopDongEntity> getDsHopDong() {
		return dsHopDong;
	}

	public void setDsHopDong(List<HopDongEntity> dsHopDong) {
		this.dsHopDong = dsHopDong;
	}

	public PhongEntity() {
		super();
	}
	
}
