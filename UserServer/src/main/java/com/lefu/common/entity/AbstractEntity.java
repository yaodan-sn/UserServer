package com.lefu.common.entity;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;

public abstract class AbstractEntity<ID extends Serializable>  implements Persistable<ID> {

	private static final long serialVersionUID = -1544766588814690602L;

	public abstract ID getId();
	
    public abstract void setId(final ID id);
    
    public abstract String toString();
    
    @Override
    public boolean isNew() {
        return null == getId();
    }



}
