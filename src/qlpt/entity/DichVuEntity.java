package qlpt.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "DICHVU")
public class DichVuEntity{
	@Id
	@GeneratedValue
	@Column(name = "MADV")
	private int MADV;
	
	@Column(name = "LOAIDV")
	private String LOAIDV;
	
	@Column(name = "MOTA")
	private String MOTA;
	
	@Column(name = "DONGIA")
	private double DONGIA;
	
	@OneToMany(mappedBy = "dichVu", fetch = FetchType.EAGER)
	private Collection<CTDichVuEntity> dsCTDichVu;

	public Collection<CTDichVuEntity> getDsCTDichVu() {
		return dsCTDichVu;
	}

	public void setDsCTDichVu(Collection<CTDichVuEntity> dsCTDichVu) {
		this.dsCTDichVu = dsCTDichVu;
	}

	public int getMADV() {
		return MADV;
	}

	public void setMADV(int mADV) {
		MADV = mADV;
	}

	public String getLOAIDV() {
		return LOAIDV;
	}

	public void setLOAIDV(String lOAIDV) {
		LOAIDV = lOAIDV;
	}

	public String getMOTA() {
		return MOTA;
	}

	public void setMOTA(String mOTA) {
		MOTA = mOTA;
	}

	public double getDONGIA() {
		return DONGIA;
	}

	public void setDONGIA(double dONGIA) {
		DONGIA = dONGIA;
	}

	public DichVuEntity() {
		super();
	}
	
	
}
