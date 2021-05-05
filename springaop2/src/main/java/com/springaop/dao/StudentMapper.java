package com.springaop.dao;

import com.springaop.entity.Student;

public interface StudentMapper {

    Student updateByStudent(Student stu);

    void addStudent(Student stu);

    void deleteStudent(String id);

    Student selectById(String id);
}
