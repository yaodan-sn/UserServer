package com.pay.common.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;


public abstract class BaseMysqlEntity<ID extends Serializable> extends AbstractEntity<ID> {


	private static final long serialVersionUID = 6086217779965466797L;

    protected ID id;

	//创建时间
	protected Date createTime;

	 //版本号
	
	public Long optimistic = 0L;

	//最后修改时间
	
	public Date lastUpdateTime;

	public Long getOptimistic() {
		return optimistic;
	}

	public void setOptimistic(Long optimistic) {
		this.optimistic = optimistic;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void addOptimistic(){
		this.optimistic += 1;
	}

    
    public ID getId() {
        return id;
    }

    
    public void setId(ID id) {
        this.id = id;
    }

    
	public Date getCreateTime() {
	  return createTime;
	}

	public void setCreateTime(Date createTime) {
	  this.createTime = createTime;
	}



	/**
	 * 设置基础属性
	 * @Description  设置基础属性
	 * @see 需要参考的类或方法
	 */
	public void setBasicProperties(){
		if(id == null || optimistic == null){
			this.optimistic = 1L;
		}else{
			this.optimistic += 1;
		}

		this.lastUpdateTime = new Date();

		if(createTime == null){
			this.createTime = new Date();
		}

	}
	
	public String toString() {

		StringBuilder sb = null;
        try {
            Class<?> c = this.getClass();
            Field[] fields = c.getDeclaredFields();

            sb = new StringBuilder();
            sb.append(this.getClass().getSimpleName());
            sb.append(" [");

            int i = 1;
            for(Field fd : fields){
                fd.setAccessible(true);
                sb.append(fd.getName());
                sb.append("=");
                sb.append(fd.get(this));
                if(i != fields.length){
                    sb.append(", ");
                }
                i++;
            }
            sb.append("]");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return sb.toString();
	}
}
