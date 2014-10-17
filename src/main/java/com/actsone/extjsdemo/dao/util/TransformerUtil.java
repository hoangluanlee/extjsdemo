package com.actsone.extjsdemo.dao.util;

import java.util.ArrayList;
import java.util.List;

import com.actsone.extjsdemo.model.Course;
import com.actsone.extjsdemo.model.Score;
import com.actsone.extjsdemo.model.Student;
import com.actsone.extjsdemo.model.Subject;
import com.actsone.extjsdemo.sdo.CourseSDO;
import com.actsone.extjsdemo.sdo.ScoreSDO;
import com.actsone.extjsdemo.sdo.StudentSDO;
import com.actsone.extjsdemo.sdo.SubjectSDO;

public final class TransformerUtil {
	public static StudentSDO transferToSDO(Student student) {
		StudentSDO sdo = new StudentSDO();
		sdo.setBirthday(student.getBirthday());
		sdo.setId(student.getId());
		sdo.setName(student.getName());
		sdo.setPhoneNumber(student.getPhoneNumber());
		sdo.setSurName(student.getSurName());

		List<ScoreSDO> scores = new ArrayList<ScoreSDO>();
		for (Score score : student.getScores()) {
			scores.add(transformToSDO(score));
		}
		sdo.setScores(scores);
		return sdo;
	}

	public static Student transformToDTO(StudentSDO student) {
		Student dto = new Student();
		dto.setBirthday(student.getBirthday());
		dto.setId(student.getId());
		dto.setName(student.getName());
		dto.setPhoneNumber(student.getPhoneNumber());
		dto.setSurName(student.getSurName());

		// Set<ScoreSDO> scores = new
		// TODO: implement later
		// for(Score score: student.getScores())
		// {
		// scores.add(transferToSDO(score));
		// }
		// dto.setScores(scores);
		return dto;
	}

	public static ScoreSDO transformToSDO(Score score) {
		ScoreSDO scoreSDO = new ScoreSDO();
		scoreSDO.setCourseId(score.getCourse().getId());
		scoreSDO.setSubjectId(score.getSubject().getId());
		scoreSDO.setStudentId(score.getStudent().getId());
		scoreSDO.setScore(score.getScore());
		scoreSDO.setSubject(score.getSubject().getName());
		scoreSDO.setId(score.getId());
		return scoreSDO;
	}

	public static Score transformToDTO(ScoreSDO score) {
		Score dto = new Score();
		Course course = new Course();
		course.setId(score.getCourseId());
		dto.setCourse(course);

		Subject subject = new Subject();
		subject.setId(score.getSubjectId());
		dto.setSubject(subject);

		Student student = new Student();
		student.setId(score.getStudentId());
		dto.setStudent(student);

		dto.setScore(score.getScore());

		dto.setId(score.getId());
		return dto;
	}

	public static CourseSDO transformToSDO(Course course) {
		CourseSDO sdo = new CourseSDO(course.getName(), course.getDesciption());
		sdo.setId(course.getId());
		return sdo;

	}

	public static Course transformToDTO(CourseSDO course) {
		Course dto = new Course(course.getName(), course.getDescription());
		dto.setId(course.getId());
		return dto;

	}

	public static SubjectSDO transformToSDO(Subject subject) {
		SubjectSDO sdo = new SubjectSDO(subject.getName(),
				subject.getDescription(), subject.getNumberOfClass());
		sdo.setId(subject.getId());
		return sdo;
	}

	public static Subject transformToDTO(SubjectSDO subject) {
		Subject dto = new Subject(subject.getName(), subject.getDescription(),
				subject.getNumberOfClass());
		dto.setId(subject.getId());
		return dto;
	}
}
