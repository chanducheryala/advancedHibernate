package com.practice.advancedHibernate.dao;

import com.practice.advancedHibernate.entity.Course;
import com.practice.advancedHibernate.entity.Instructor;
import com.practice.advancedHibernate.entity.InstructorDetail;
import com.practice.advancedHibernate.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    void save(Course course);

    Instructor findInstructorById(int id);

    void deleteById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor theInstructor);

    void deleteInstructorById(int id);

    Course findCourseAndReviewByCourseId(int id);

    Course findCourseAndStudentsByCourseId(int id);

    Student findStudentAndCoursesByStudentId(int id);
}
