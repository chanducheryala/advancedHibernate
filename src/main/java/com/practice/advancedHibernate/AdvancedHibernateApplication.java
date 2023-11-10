package com.practice.advancedHibernate;

import com.practice.advancedHibernate.dao.AppDAO;
import com.practice.advancedHibernate.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedHibernateApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdvancedHibernateApplication.class, args);
	}


	// it will executed after all Spring Bean are loaded
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDao) {
		return runner -> {
//			createCourseAndStudents(appDao);
//			findCourseAndStudents(appDao);

			findStudentAndCourses(appDao);
		};
	}

	private void findStudentAndCourses(AppDAO appDao) {
		int id = 1;
		Student student = appDao.findStudentAndCoursesByStudentId(id);
		System.out.println("The student : " + student);
		System.out.println("The associated courses : " + student.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDao) {
		int id = 10;
		Course theCourse = appDao.findCourseAndStudentsByCourseId(id);
		System.out.println("The Course : " + theCourse);
		System.out.println("The associated students : " + theCourse.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDao) {
		// create a course

		Course theCourse = new Course("The Mighty Eagle");

		// create students
		Student student1 = new Student("ch", "chintu", "abc@gmail.com");
		Student student2 = new Student("ch", "chintu2", "abc@gmail.com");
		Student student3 = new Student("ch", "chintu3", "abc@gmail.com");

		// add students to course
		theCourse.addStudent(student1);
		theCourse.addStudent(student2);
		theCourse.addStudent(student3);

		// save course and associated students

		appDao.save(theCourse);
	}

	private void retrieveCoursesAndReviews(AppDAO appDao) {

		int id = 10;
		// get the course and reviews
		Course course = appDao.findCourseAndReviewByCourseId(id);

		// print the course
		System.out.println("The course : " + course);

		// print the reviews
		System.out.println("The reviews : " + course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDao) {
		Course tempCourse = new Course("The little Bird");

		tempCourse.addReview(new Review("Great Course 1"));
		tempCourse.addReview(new Review("Great Course 2"));
		tempCourse.addReview(new Review("Great Course 3"));

		appDao.save(tempCourse);
	}

	private void updateInstructor(AppDAO appDao) {
		int id = 1;
		Instructor instructor = appDao.findInstructorById(id);
		instructor.setLastName("Chintu");
		appDao.update(instructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDao) {
		int id = 1;
		Instructor instructor = appDao.findInstructorByIdJoinFetch(id);
		System.out.println("The instructor : " + instructor);
		System.out.println("The associated Courses : " + instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDao) {
		int id = 1;

		Instructor theInstructor = appDao.findInstructorById(id);
		System.out.println("Instructor : " + theInstructor);

		List<Course> courses = appDao.findCoursesByInstructorId(id);
		theInstructor.setCourses(courses);
		System.out.println("The associated courses : " + theInstructor.getCourses());
		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDao) {
		Instructor tempInstructor = new Instructor("ch", "bhanu", "abc@gmail.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://fb.com", "Cricket");
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Course tempCourse1 = new Course("Happy days");
		Course tempCourse2 = new Course("The Life");


		// add courses to the instructor
		tempInstructor.addCourse(tempCourse1);
		tempInstructor.addCourse(tempCourse2);

		// save the instructor
		System.out.println("Saving the instructor " + tempInstructor);
		System.out.println("The Courses : " + tempInstructor.getCourses());
		appDao.save(tempInstructor);

		System.out.println("Done");
	}

	private void deleteInstructorDetailById(AppDAO appDao) {
		int id = 2;
		appDao.deleteInstructorDetailById(id);
		System.out.println("InstructorDetail of given id is deleted");
	}

	private void findInstructorDetailById(AppDAO appDao) {
		int id = 1;
		InstructorDetail instructorDetail = appDao.findInstructorDetailById(id);
		Instructor instructor = instructorDetail.getInstructor();
		System.out.println("Instructor for given id is : " + instructor.toString());
		System.out.println("InstructorDetail for given id is : " + instructorDetail);
	}

	private void deleteInstructorById(AppDAO appDao) {
		int id = 1;
		appDao.deleteById(id);
	}

	private void findInstructor(AppDAO appDao) {
		int id = 1;
		Instructor tempInstructor = appDao.findInstructorById(id);

		// printing out the Instructor Detail
		System.out.println("Instructor Detail of the Id is : " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create the Instructor
		Instructor tempInstructor = new Instructor("ch", "chandu", "abc@gmail.com");

		// create the InstructorDetail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com", "Playing online games");

		//associate the objects to make relationship
		 tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the Instructor
		System.out.println(tempInstructor.toString());
		appDAO.save(tempInstructor);

		// Note it will also save the Instructor Detail object by simply saving the Instructor object
	}
}

//			createInstructor(appDao);
//			findInstructor(appDao);
//			deleteInstructorById(appDao);
//			findInstructorDetailById(appDao);
//			deleteInstructorDetailById(appDao);
//			createInstructorWithCourses(appDao);
//			findInstructorWithCourses(appDao);
//			findInstructorWithCoursesJoinFetch(appDao);
//			updateInstructor(appDao);
//			deleteInstructorById(appDao);
//			createCourseAndReviews(appDao);
//			retrieveCoursesAndReviews(appDao);
