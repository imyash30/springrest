package com.spring.boot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class BaseEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="created_by", length = 15)	//	tokenId - max = 11
	private String createdby;

	@Column(name="modified_by", length = 15)	//	tokenId
	private String modifiedby;

	@Column(name="created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdon;

	@Column(name="modified_on")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
	private Date modifiedon;

	@Column(name="is_active", length = 3)	//	max = 1
	private String isActive;

	@Version
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedby() {
		return createdby;
	}
	
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}
	
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Date getCreatedon() {
		return createdon;
	}
	
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Date getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@PrePersist
	public void beforeCreate(){
		Date now = new Date();
		setCreatedon(now);
		setModifiedon(now);
		setIsActive("Y");
	}

	@PreUpdate
	public void beforeUpdate(){
		setModifiedon(new Date());
	}

}


/* For using the above code in the entity you need to  follow the below snipet
 * 
 * 
 * 
 * @Entity
   @Table(name="mt_demo")
   @AttributeOverride(name="id",column=@Column(name="demoid"))//if you want to override the base entity column then use this
    public class MTDemo extends BaseEntity{
    '
    '
    ----your remaining column---getter setters
    }

 * 
 * */


