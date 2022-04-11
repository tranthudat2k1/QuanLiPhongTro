package qlpt.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "HOADON")
public class HoaDonEntity {
	@Id
	private String MAHD;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NGAYLAP;
	
	private double THANHTIEN;
	private float THUE;
	private double TONGPHUTHU;
	
	@OneToMany(mappedBy = "HOADON", fetch = FetchType.EAGER)
	private Collection<CTDichVuEntity> dsCTDichVu;
	
	
	public String getMAHD() {
		return MAHD;
	}
	public void setMAHD(String mAHD) {
		MAHD = mAHD;
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
	public Collection<CTDichVuEntity> getDsCTDichVu() {
		return dsCTDichVu;
	}
	public void setDsCTDichVu(Collection<CTDichVuEntity> dsCTDichVu) {
		this.dsCTDichVu = dsCTDichVu;
	}
	public HoaDonEntity() {
		super();
	}
	
	
}
