package qlpt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CT_DICHVU")
@IdClass(CTDichVuEntity.class)
public class CTDichVuEntity implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "MADV")
	private DichVuEntity dichVu;

	@Id
	@ManyToOne
	@JoinColumn(name = "MAHOPDONG")
	private HopDongEntity hopDong;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MATG")
	private ThoiGianEntity thoiGian;
	
	
	public CTDichVuEntity() {
		super();
	}
	public CTDichVuEntity(DichVuEntity dichVu, HopDongEntity hopDong, ThoiGianEntity thoiGian, int cHISOCU,
			int cHISOMOI) {
		super();
		this.dichVu = dichVu;
		this.hopDong = hopDong;
		this.thoiGian = thoiGian;
		CHISOCU = cHISOCU;
		CHISOMOI = cHISOMOI;
	}
	private int CHISOCU;
	private int CHISOMOI;
	public DichVuEntity getDichVu() {
		return dichVu;
	}
	public void setDichVu(DichVuEntity dichVu) {
		this.dichVu = dichVu;
	}
	public HopDongEntity getHopDong() {
		return hopDong;
	}
	public void setHopDong(HopDongEntity hopDong) {
		this.hopDong = hopDong;
	}
	public ThoiGianEntity getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(ThoiGianEntity thoiGian) {
		this.thoiGian = thoiGian;
	}
	public int getCHISOCU() {
		return CHISOCU;
	}
	public void setCHISOCU(int cHISOCU) {
		CHISOCU = cHISOCU;
	}
	public int getCHISOMOI() {
		return CHISOMOI;
	}
	public void setCHISOMOI(int cHISOMOI) {
		CHISOMOI = cHISOMOI;
	}

}
