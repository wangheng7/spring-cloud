package com.springaop.service;

import com.springaop.dao.StudentMapper;
import com.springaop.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentMapper studentMapper;

    public void addStudent(Student stu) {
        studentMapper.addStudent(stu);
    }

    public void deleteStudent(String id) {
        studentMapper.deleteStudent(id);
    }

    public Student modifyStudent(Student stu) {
        Student student = studentMapper.updateByStudent(stu);
        return student;
    }

}
