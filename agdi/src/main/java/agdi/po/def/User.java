package agdi.po.def;

import javax.xml.bind.annotation.XmlRootElement;

import agdi.infrastructure.PersistentObject.def.PO;

/**
 * User PO
 */
@XmlRootElement
public class User 
implements PO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String name;
	private String surname;
	private Integer age;

	/**
	 * 
	 */
	public User() {
	}

	/**
	 * @param userId
	 */
	public User(String userId) {
		this.userId = userId;
	}

	/**
	 * @param userId
	 * @param name
	 * @param surname
	 * @param age
	 */
	public User(String userId, String name, String surname, Integer age) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	/**
	 * @return useID
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = String.format("%010d", userId);
	}

	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return surname
	 */
	public String getSurname() {
		return this.surname;
	}

	/**
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return age
	 */
	public Integer getAge() {
		return this.age;
	}

	/**
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

}
