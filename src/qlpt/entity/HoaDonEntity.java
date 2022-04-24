package qlpt.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "HOADON")
public class HoaDonEntity {
	@Id
	private String MAHOADON;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NGAYLAP;
	
	private double THANHTIEN;
	private float THUE;
	private double TONGPHUTHU;
	private Boolean TRANGTHAI;
	
	@ManyToOne
	@JoinColumn(name="MAHOPDONG")
	private HopDongEntity hopDong;

	public HoaDonEntity(String mAHOADON, Date nGAYLAP, double tHANHTIEN, float tHUE, double tONGPHUTHU,
			Boolean tRANGTHAI, HopDongEntity hopDong) {
		super();
		MAHOADON = mAHOADON;
		NGAYLAP = nGAYLAP;
		THANHTIEN = tHANHTIEN;
		THUE = tHUE;
		TONGPHUTHU = tONGPHUTHU;
		TRANGTHAI = tRANGTHAI;
		this.hopDong = hopDong;
	}

	public HoaDonEntity() {
		super();
	}

	public String getMAHOADON() {
		return MAHOADON;
	}

	public void setMAHOADON(String mAHOADON) {
		MAHOADON = mAHOADON;
	}

	public Date getNGAYLAP() {
		return NGAYLAP;
	}

	public void setNGAYLAP(Date nGAYLAP) {
		NGAYLAP = nGAYLAP;
	}

	public double getTHANHTIEN() {
		return THANHTIEN;
	}

	public void setTHANHTIEN(double tHANHTIEN) {
		THANHTIEN = tHANHTIEN;
	}

	public float getTHUE() {
		return THUE;
	}

	public void setTHUE(float tHUE) {
		THUE = tHUE;
	}

	public double getTONGPHUTHU() {
		return TONGPHUTHU;
	}

	public void setTONGPHUTHU(double tONGPHUTHU) {
		TONGPHUTHU = tONGPHUTHU;
	}

	public Boolean getTRANGTHAI() {
		return TRANGTHAI;
	}

	public void setTRANGTHAI(Boolean tRANGTHAI) {
		TRANGTHAI = tRANGTHAI;
	}

	public HopDongEntity getHopDong() {
		return hopDong;
	}

	public void setHopDong(HopDongEntity hopDong) {
		this.hopDong = hopDong;
	}
	
	
	
	
}
