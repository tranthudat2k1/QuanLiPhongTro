package qlpt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "HOPDONG")
@IdClass(HopDongEntity.class)
public class HopDongEntity implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "MAKHACH")
	private KhachThueEntity KHACHTHUE;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NGAYKY;
	
	private double TIENCOC;
	private double TIENDIEN;
	private double TIENNUOC;
	
	public KhachThueEntity getKHACHTHUE() {
		return KHACHTHUE;
	}
	public void setKHACHTHUE(KhachThueEntity kHACHTHUE) {
		KHACHTHUE = kHACHTHUE;
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
	
}
