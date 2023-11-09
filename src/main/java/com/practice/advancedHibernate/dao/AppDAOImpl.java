package com.practice.advancedHibernate.dao;

import com.practice.advancedHibernate.entity.Course;
import com.practice.advancedHibernate.entity.Instructor;
import com.practice.advancedHibernate.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // defining the field for entity manager
    private EntityManager entityManager;

    // inject the entity manager using Construction Injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        // it will persist or apply the same operation on the InstructionDetail
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        // It will also fetch the Instructor Detail because the Default behaviour(One to One) of the fetch type is eager
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Instructor theInstructor = entityManager.find(Instructor.class, id);
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> courseTypedQuery = entityManager.createQuery("From Course where theInstructor.id = :data", Course.class);
        courseTypedQuery.setParameter("data", id);
        List<Course> theCourses = courseTypedQuery.getResultList();
        return theCourses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id = :data", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }
}
