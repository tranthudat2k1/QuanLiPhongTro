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
	@JoinColumn(name = "MACTHD")
	private CTHopDongEntity ctHopDong;

	public KhachThueEntity getKhachThue() {
		return khachThue;
	}

	public void setKhachThue(KhachThueEntity khachThue) {
		this.khachThue = khachThue;
	}

	public CTHopDongEntity getCtHopDong() {
		return ctHopDong;
	}

	public void setCtHopDong(CTHopDongEntity ctHopDong) {
		this.ctHopDong = ctHopDong;
	}

	public CTKhachThueEntity() {
		super();
	}
	
	
}
