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
	TrangThaiEntity trangThai;
	
	@ManyToOne
	@JoinColumn(name = "MAKHU")
	private KhuEntity khu;
	
	@OneToMany(mappedBy = "phong", fetch = FetchType.EAGER)
	Collection<CTHopDongEntity> dsCTHopDong;
	

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
	public PhongEntity() {
		super();
	}

	public TrangThaiEntity getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiEntity trangThai) {
		this.trangThai = trangThai;
	}

	public KhuEntity getKhu() {
		return khu;
	}

	public void setKhu(KhuEntity khu) {
		this.khu = khu;
	}

	public Collection<CTHopDongEntity> getDsCTHopDong() {
		return dsCTHopDong;
	}

	public void setDsCTHopDong(Collection<CTHopDongEntity> dsCTHopDong) {
		this.dsCTHopDong = dsCTHopDong;
	}

	@Override
	public String toString() {
		return "PhongEntity [MAPHONG=" + MAPHONG + ", SLNGUOITOIDA=" + SLNGUOITOIDA + ", DONGIA=" + DONGIA + ", MOTA="
				+ MOTA + ", TRANGTHAI=" + TRANGTHAI + ", KHU=" + KHU + "]";
	}
	
}
