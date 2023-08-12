package agdi.po.impl;

import javax.xml.bind.annotation.XmlRootElement;

import agdi.po.def.User;

/**
 * User PO
 */
@XmlRootElement(name = "User")
public class UserImpl implements User {

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	String id;
	String name;
	String surname;
	Integer age;

	/**
	 * Constructor
	 * 
	 */
	public UserImpl() {

	}

	/**
	 * Constructor
	 */
	public UserImpl(String id) {
		this.id = id;
	}

	/**
	 * @param id 
	 * @param name
	 * @param surname
	 * @param age
	 * @return
	 */
	public UserImpl(String id, String name, String surname, Integer age) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	/**
	 * @return useID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param id 
	 */
	public void setId(int id) {
		setId(String.format("%010d", id));
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
	
	@Override
	public String toString() {
		StringBuilder literal = new StringBuilder();
		literal.append("ID: ")		.append(getId())		.append("\n");
		literal.append("Name: ")	.append(getName())		.append("\n");
		literal.append("Surname: ")	.append(getSurname())	.append("\n");
		literal.append("Age: ")		.append(getAge())		.append("\n");

		return literal.toString();
	}
}
