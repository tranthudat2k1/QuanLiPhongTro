package qlpt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "QUYDINH")
public class QuyDinhEntity implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name="MANT")
	private NhaTroEntity nhaTro;
	
	@Id
	@ManyToOne
	@JoinColumn(name="MADV")
	private DichVuEntity dichVu;
	
	private double DONGIA;
	private String MOTA;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NGAYQD;

	public NhaTroEntity getNhaTro() {
		return nhaTro;
	}

	public void setNhaTro(NhaTroEntity nhaTro) {
		this.nhaTro = nhaTro;
	}

	public DichVuEntity getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVuEntity dichVu) {
		this.dichVu = dichVu;
	}

	public double getDONGIA() {
		return DONGIA;
	}

	public void setDONGIA(double dONGIA) {
		DONGIA = dONGIA;
	}

	public Date getNGAYQD() {
		return NGAYQD;
	}

	public void setNGAYQD(Date nGAYQD) {
		NGAYQD = nGAYQD;
	}

	

	public QuyDinhEntity(NhaTroEntity nhaTro, DichVuEntity dichVu, double dONGIA, String mOTA, Date nGAYQD) {
		super();
		this.nhaTro = nhaTro;
		this.dichVu = dichVu;
		DONGIA = dONGIA;
		MOTA = mOTA;
		NGAYQD = nGAYQD;
	}

	public String getMOTA() {
		return MOTA;
	}

	public void setMOTA(String mOTA) {
		MOTA = mOTA;
	}

	public QuyDinhEntity() {
		super();
	}
	
	
}
