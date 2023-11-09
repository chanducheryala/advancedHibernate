package com.practice.advancedHibernate.dao;

import com.practice.advancedHibernate.entity.Instructor;
import com.practice.advancedHibernate.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int id);

    void deleteById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);
}
