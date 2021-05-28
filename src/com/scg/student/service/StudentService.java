package com.scg.student.service;

import com.scg.student.vo.Student;

public interface StudentService {

	void connect() throws Exception;

	void insert(Student student) throws Exception;

	void delete(int id) throws Exception;

	void readByPage(int pageNumber) throws Exception;

	void update(Student student) throws Exception;

	void searchByName(String name) throws Exception;

	void readById(int id) throws Exception;

	void commit() throws Exception;

	void rollback() throws Exception;

	void readAll() throws Exception;

	void closeConnection() throws Exception;

}
