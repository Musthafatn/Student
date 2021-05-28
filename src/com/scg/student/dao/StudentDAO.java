package com.scg.student.dao;

import java.util.List;

import com.scg.student.vo.Student;

public interface StudentDAO {

	void connect() throws Exception;

	public boolean existStudent(int id) throws Exception;

	void insert(Student s) throws Exception;

	void delete(int id) throws Exception;

	List<Student> readByPage(int fromIndex, int toIndex) throws Exception;

	void update(Student student) throws Exception;

	List<Student> searchbyName(String name) throws Exception;

	Student readById(int id) throws Exception;

	void commit() throws Exception;

	void rollback() throws Exception;

	List<Student> readAll() throws Exception;

	void closeConnection() throws Exception;

}
