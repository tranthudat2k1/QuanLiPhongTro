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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer MACTDV;
	
	@ManyToOne
	@JoinColumn(name = "MADV")
	private DichVuEntity dichVu;

	@ManyToOne
	@JoinColumn(name = "MAHD")
	private DichVuEntity hopDong;
	
	@ManyToOne
	@JoinColumn(name = "MATG")
	private ThoiGianEntity thoiGian;
	
	@Column(name = "CSCU" )
	private int CHISOCU;
	
	@Column(name = "CSMOI" )
	private int CHISOMOI;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NGAYTHONGKE;

	public CTDichVuEntity(Integer mACTDV, DichVuEntity dichVu, DichVuEntity hopDong, ThoiGianEntity thoiGian,
			int cHISOCU, int cHISOMOI, Date nGAYTHONGKE) {
		super();
		MACTDV = mACTDV;
		this.dichVu = dichVu;
		this.hopDong = hopDong;
		this.thoiGian = thoiGian;
		CHISOCU = cHISOCU;
		CHISOMOI = cHISOMOI;
		NGAYTHONGKE = nGAYTHONGKE;
	}

	public CTDichVuEntity() {
		super();
	}

	public Integer getMACTDV() {
		return MACTDV;
	}

	public void setMACTDV(Integer mACTDV) {
		MACTDV = mACTDV;
	}

	public DichVuEntity getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVuEntity dichVu) {
		this.dichVu = dichVu;
	}

	public DichVuEntity getHopDong() {
		return hopDong;
	}

	public void setHopDong(DichVuEntity hopDong) {
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

	public Date getNGAYTHONGKE() {
		return NGAYTHONGKE;
	}

	public void setNGAYTHONGKE(Date nGAYTHONGKE) {
		NGAYTHONGKE = nGAYTHONGKE;
	}
	
	
}
