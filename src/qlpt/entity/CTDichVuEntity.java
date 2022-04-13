package qlpt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "CT_DICHVU")
@IdClass(CTDichVuEntity.class)
public class CTDichVuEntity implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "MACTHD")
	private CTHopDongEntity ctHopDong;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MADV")
	private DichVuEntity dichVu;

	@Column(name = "THANG" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date THANG;

	@Column(name = "CHISOCU" )
	private int CHISOCU;
	
	@Column(name = "CHISOMOI" )
	private int CHISOMOI;


	public Date getTHANG() {
		return THANG;
	}

	public void setTHANG(Date tHANG) {
		THANG = tHANG;
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


	public CTDichVuEntity() {
		super();
	}


	public CTHopDongEntity getCtHopDong() {
		return ctHopDong;
	}

	public void setCtHopDong(CTHopDongEntity ctHopDong) {
		this.ctHopDong = ctHopDong;
	}

	public DichVuEntity getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVuEntity dichVu) {
		this.dichVu = dichVu;
	}

}
