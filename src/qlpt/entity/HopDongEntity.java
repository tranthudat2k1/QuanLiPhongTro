package qlpt.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "HOPDONG")
public class HopDongEntity{
	@Id
	private int MAHD;
	
	private double TIENCOC;
	private double TIENDIEN;
	private double TIENNUOC;
	
	@ManyToOne
	@JoinColumn(name = "MAKT")
	private KhachThueEntity khachThue;
	
	@OneToMany(mappedBy = "hopDong", fetch = FetchType.EAGER)
	private Collection<CTHopDongEntity> dsCTHopDong;
	
	public double getTIENCOC() {
		return TIENCOC;
	}
	public void setTIENCOC(double tIENCOC) {
		TIENCOC = tIENCOC;
	}
	public double getTIENDIEN() {
		return TIENDIEN;
	}
	public void setTIENDIEN(double tIENDIEN) {
		TIENDIEN = tIENDIEN;
	}
	public double getTIENNUOC() {
		return TIENNUOC;
	}
	public void setTIENNUOC(double tIENNUOC) {
		TIENNUOC = tIENNUOC;
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
	public KhachThueEntity getKhachThue() {
		return khachThue;
	}
	public void setKhachThue(KhachThueEntity khachThue) {
		this.khachThue = khachThue;
	}
	public Collection<CTHopDongEntity> getDsCTHopDong() {
		return dsCTHopDong;
	}
	public void setDsCTHopDong(Collection<CTHopDongEntity> dsCTHopDong) {
		this.dsCTHopDong = dsCTHopDong;
	}
	
}
