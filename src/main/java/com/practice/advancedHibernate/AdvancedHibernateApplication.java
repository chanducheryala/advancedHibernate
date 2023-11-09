package com.practice.advancedHibernate;

import com.practice.advancedHibernate.dao.AppDAO;
import com.practice.advancedHibernate.entity.Instructor;
import com.practice.advancedHibernate.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedHibernateApplication.class, args);
	}


	// it will executed after all Spring Bean are loaded
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDao) {
		return runner -> {
//			createInstructor(appDao);
//			findInstructor(appDao);
//			deleteInstructorById(appDao);

			findInstructorDetailById(appDao);
		};
	}

	private void findInstructorDetailById(AppDAO appDao) {
		int id = 1;
		InstructorDetail instructorDetail = appDao.findInstructorDetailById(id);
		Instructor instructor = instructorDetail.getInstructor();
		System.out.println("Instructor for given id is : " + instructor.toString());
		System.out.println("InstructorDetail for given id is : " + instructorDetail);
	}

	private void deleteInstructorById(AppDAO appDao) {
		int id = 2;
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
