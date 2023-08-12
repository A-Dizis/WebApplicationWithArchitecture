package cfg.infrastructure.PersistentObject.def;

import java.io.Serializable;

/**
 * Persistent object.
 * 
 * @author dizisa
 */
public interface PO extends Serializable{

	/**
	 * 
	 */
	String id = null;
	
	/**
	 * @return id
	 */
	public String getId();

	/**
	 * @param id  
	 */
	public void setId(String id);
	
}
