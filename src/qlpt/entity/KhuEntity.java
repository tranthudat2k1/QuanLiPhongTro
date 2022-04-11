package qlpt.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KHU")
public class KhuEntity {
	@Id
	private String MAKHU;
	private String TENKHU;
	private String TINH_TP;
	private String QUAN_HUYEN;
	private String PHUONG_XA;
	private String DIACHI;
	
	@ManyToOne
	@JoinColumn(name = "MACT")
	private ChuTroEntity CHUTRO;
	
	@OneToMany(mappedBy = "KHU", fetch = FetchType.EAGER)
	private Collection<PhongEntity> dsPhong;

	public String getMAKHU() {
		return MAKHU;
	}

	public void setMAKHU(String mAKHU) {
		MAKHU = mAKHU;
	}

	public String getTENKHU() {
		return TENKHU;
	}

	public void setTENKHU(String tENKHU) {
		TENKHU = tENKHU;
	}

	public String getTINH_TP() {
		return TINH_TP;
	}

	public void setTINH_TP(String tINH_TP) {
		TINH_TP = tINH_TP;
	}

	public String getQUAN_HUYEN() {
		return QUAN_HUYEN;
	}

	public void setQUAN_HUYEN(String qUAN_HUYEN) {
		QUAN_HUYEN = qUAN_HUYEN;
	}

	public String getPHUONG_XA() {
		return PHUONG_XA;
	}

	public void setPHUONG_XA(String pHUONG_XA) {
		PHUONG_XA = pHUONG_XA;
	}

	public String getDIACHI() {
		return DIACHI;
	}

	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}

	public ChuTroEntity getCHUTRO() {
		return CHUTRO;
	}

	public void setCHUTRO(ChuTroEntity cHUTRO) {
		CHUTRO = cHUTRO;
	}

	public Collection<PhongEntity> getDsPhong() {
		return dsPhong;
	}

	public void setDsPhong(Collection<PhongEntity> dsPhong) {
		this.dsPhong = dsPhong;
	}

	public KhuEntity() {
		super();
	}
	
}
