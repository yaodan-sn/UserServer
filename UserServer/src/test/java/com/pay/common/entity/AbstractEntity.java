package com.pay.common.entity;

import java.io.Serializable;

public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = -1544766588814690602L;

	public abstract ID getId();

	public abstract void setId(final ID id);

	public abstract String toString();

	public boolean isNew() {
		return null == getId();
	}

}
