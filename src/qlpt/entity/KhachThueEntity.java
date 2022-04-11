package qlpt.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KHACHTHUE")
public class KhachThueEntity {
	@Id
	private String MAKHACH;
	private String HO;
	private String TEN;
	private int NAMSINH;
	private String GIOITINH;
	private String CCCD;
	private String DIACHI;
	private String EMAIL;
	private String SDT;
	private String NGHENGHIEP;
	
	@OneToMany(mappedBy = "KHACHTHUE", fetch = FetchType.EAGER)
	private Collection<HopDongEntity> dsHopDong;

	public String getMAKHACH() {
		return MAKHACH;
	}

	public void setMAKHACH(String mAKHACH) {
		MAKHACH = mAKHACH;
	}

	public String getHO() {
		return HO;
	}

	public void setHO(String hO) {
		HO = hO;
	}

	public String getTEN() {
		return TEN;
	}

	public void setTEN(String tEN) {
		TEN = tEN;
	}

	public int getNAMSINH() {
		return NAMSINH;
	}

	public void setNAMSINH(int nAMSINH) {
		NAMSINH = nAMSINH;
	}

	public String getGIOITINH() {
		return GIOITINH;
	}

	public void setGIOITINH(String gIOITINH) {
		GIOITINH = gIOITINH;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public String getDIACHI() {
		return DIACHI;
	}

	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getNGHENGHIEP() {
		return NGHENGHIEP;
	}

	public void setNGHENGHIEP(String nGHENGHIEP) {
		NGHENGHIEP = nGHENGHIEP;
	}

	public Collection<HopDongEntity> getDsHopDong() {
		return dsHopDong;
	}

	public void setDsHopDong(Collection<HopDongEntity> dsHopDong) {
		this.dsHopDong = dsHopDong;
	}

	public KhachThueEntity() {
		super();
	}
	
	
}
