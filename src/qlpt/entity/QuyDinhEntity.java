package qlpt.entity;

import java.io.Serializable;
import java.util.Date;

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
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date NGAYQD;
}
