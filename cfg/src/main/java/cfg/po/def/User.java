package cfg.po.def;

import cfg.infrastructure.PersistentObject.def.PO;

/**
 * @author dizisa
 *
 */
public interface User extends PO {

	/**
	 * @param id 
	 */
	public void setId(int id);

	/**
	 * @return name
	 */
	public String getName();

	/**
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @return surname
	 */
	public String getSurname();

	/**
	 * @param surname
	 */
	public void setSurname(String surname);

	/**
	 * @return age
	 */
	public Integer getAge();

	/**
	 * @param age
	 */
	public void setAge(Integer age);

}
