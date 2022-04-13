package qlpt.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CT_HOPDONG")
public class CTHopDongEntity{
	@Id
	@GeneratedValue
	private int MACTHD;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NGAYKY;
	
	@ManyToOne
	@JoinColumn(name = "MAPHONG")
	private PhongEntity phong;
	
	@ManyToOne
	@JoinColumn(name = "MAHD")
	private HopDongEntity hopDong;
	
	@ManyToOne
	@JoinColumn(name = "MAHOADON")
	private HoaDonEntity hoaDon;
	
	@OneToMany(mappedBy = "ctHopDong", fetch = FetchType.EAGER)
	Collection<CTDichVuEntity> dsCTDichVu;
	
	@OneToMany(mappedBy = "ctHopDong", fetch = FetchType.EAGER)
	private Collection<CTKhachThueEntity> dsCTKhachThue;

	public int getMACTHD() {
		return MACTHD;
	}

	public void setMACTHD(int mACTHD) {
		MACTHD = mACTHD;
	}

	public Date getNGAYKY() {
		return NGAYKY;
	}

	public void setNGAYKY(Date nGAYKY) {
		NGAYKY = nGAYKY;
	}

	public PhongEntity getPhong() {
		return phong;
	}

	public void setPhong(PhongEntity phong) {
		this.phong = phong;
	}

	public HopDongEntity getHopDong() {
		return hopDong;
	}

	public void setHopDong(HopDongEntity hopDong) {
		this.hopDong = hopDong;
	}

	public HoaDonEntity getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDonEntity hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Collection<CTDichVuEntity> getDsCTDichVu() {
		return dsCTDichVu;
	}

	public void setDsCTDichVu(Collection<CTDichVuEntity> dsCTDichVu) {
		this.dsCTDichVu = dsCTDichVu;
	}

	public Collection<CTKhachThueEntity> getDsCTKhachThue() {
		return dsCTKhachThue;
	}

	public void setDsCTKhachThue(Collection<CTKhachThueEntity> dsCTKhachThue) {
		this.dsCTKhachThue = dsCTKhachThue;
	}

	public CTHopDongEntity() {
		super();
	}
	
}
