package com.actsone.extjsdemo.sdo;

// Generated Sep 11, 2012 3:28:38 PM by Hibernate Tools 3.4.0.CR1

/**
 * Score generated by hbm2java
 */
public class ScoreSDO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3362753538757512936L;
	private Integer id;
	private Integer courseId;
	private Integer subjectId;
	private Integer studentId;
	private String subject;
	private Double score;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
