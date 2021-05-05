package com.springaop.service;

import com.springaop.entity.Student;

public interface IStudentService {

    void addStudent(Student stu);

    void deleteStudent(String id);

    Student modifyStudent(Student stu);


}
