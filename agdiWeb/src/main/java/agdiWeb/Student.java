package agdiWeb;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {

	private Long studentId;	
	private String firstName;
	private String surName;
	
	public Student() {
	}

	public Student(Long studentId, String firstName, String surName) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.surName = surName;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}
	
}
