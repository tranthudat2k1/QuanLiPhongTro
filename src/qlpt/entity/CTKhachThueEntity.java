package qlpt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_KHACHTHUE")
@IdClass(CTKhachThueEntity.class)
public class CTKhachThueEntity implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "MAKT")
	private KhachThueEntity khachThue;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MAHD")
	private CTHopDongEntity hopDong;

	private Boolean TRANGTHAI;

	public CTKhachThueEntity(KhachThueEntity khachThue, CTHopDongEntity hopDong, Boolean tRANGTHAI) {
		super();
		this.khachThue = khachThue;
		this.hopDong = hopDong;
		TRANGTHAI = tRANGTHAI;
	}

	public CTKhachThueEntity() {
		super();
	}

	public KhachThueEntity getKhachThue() {
		return khachThue;
	}

	public void setKhachThue(KhachThueEntity khachThue) {
		this.khachThue = khachThue;
	}

	public CTHopDongEntity getHopDong() {
		return hopDong;
	}

	public void setHopDong(CTHopDongEntity hopDong) {
		this.hopDong = hopDong;
	}

	public Boolean getTRANGTHAI() {
		return TRANGTHAI;
	}

	public void setTRANGTHAI(Boolean tRANGTHAI) {
		TRANGTHAI = tRANGTHAI;
	}
	
	
}
