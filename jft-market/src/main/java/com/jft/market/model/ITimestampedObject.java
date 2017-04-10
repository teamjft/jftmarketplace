package com.jft.market.model;

import java.io.Serializable;
import java.util.Date;

public interface ITimestampedObject extends Serializable {
	public Date getCreatedOn();

	public void setCreatedOn(Date createdOn);

	public Date getLastModified();

	public void setLastModified(Date lastModified);

	public void setDeleted(Boolean isDeleted);

	public Boolean getDeleted();

	public void setEnabled(Boolean isEnabled);

	public Boolean getEnabled();
}
