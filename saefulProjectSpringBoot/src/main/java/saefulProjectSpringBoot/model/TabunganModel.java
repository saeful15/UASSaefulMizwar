package saefulProjectSpringBoot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="tabungan_tbl")
@EntityListeners(AuditingEntityListener.class)

public class TabunganModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nik;
	
	@NotBlank
	private String nama;

	private int kredit;
	private int debit;
	private int saldo;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createtime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String judul) {
		this.nik = judul;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String pengarang) {
		this.nama = pengarang;
	}
	public int getkredit() {
		return kredit;
	}

	public void setkredit(int pengarang) {
		this.kredit = pengarang;
	}
	public int getDebit() {
		return debit;
	}

	public void setDebit(int pengarang) {
		this.debit = pengarang;
	}
	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int pengarang) {
		this.saldo = pengarang;
	}
	

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createdate) {
		this.createtime = createdate;
	}
	
	
}
