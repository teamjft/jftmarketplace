package com.jft.market.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class TimestampedFieldObject implements ITimestampedObject {
	private static final long serialVersionUID = -971635703385604526L;

	@Column(name = "created_on", updatable = false)
	private Date createdOn;
	@Column(name = "last_modified")
	private Date lastModified;
	@Column(name = "is_deleted")
	private Boolean deleted;

	public TimestampedFieldObject(ITimestampedObject timestampedObject) {
		super();
		this.createdOn = timestampedObject.getCreatedOn();
		this.lastModified = timestampedObject.getLastModified();
		this.deleted = timestampedObject.getDeleted();
	}

	@PrePersist
	public void prePersist() {
		Date newEntryDate = new Date();
		setCreatedOn(newEntryDate);
		setLastModified(newEntryDate);
		setDeleted(Boolean.FALSE);
	}

	@PreUpdate
	public void preUpdate() {
		setLastModified(new Date());
	}
}
