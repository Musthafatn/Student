package com.scg.student.service;

import java.util.ArrayList;
import java.util.List;

import com.scg.student.dao.StudentDAO;
import com.scg.student.dao.StudentDAOImpl;
import com.scg.student.exception.InvalidInputException;
import com.scg.student.validator.StudentValidator;
import com.scg.student.vo.Student;

public class StudentServiceImpl implements StudentService {

	private static StudentDAO studentDAO = new StudentDAOImpl();

	@Override
	public void connect() throws Exception {
		studentDAO.connect();
	}

	@Override
	public void insert(Student student) {
		// TODO Auto-generated method stub
		try {
			StudentValidator.validate(student);
			studentDAO.insert(student);
			System.out.println("Inserted successfuly\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\n");
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			StudentValidator.validateId(id);
			studentDAO.delete(id);
			System.out.println("Deleted successfully\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\n");
		}

	}

	@Override
	public void readByPage(int pageNumber) {
		// TODO Auto-generated method stub

		try {

			StudentValidator.validatePageNumber(pageNumber);

			final int pageSize = 4;

			int fromIndex = pageSize * (pageNumber - 1);

			List<Student> subList = studentDAO.readByPage(pageSize, fromIndex);

			if (subList.isEmpty()) {
				throw new InvalidInputException("No data found");
			}

			for (Student student : subList) {
				System.out.println(student.getId() + " " + student.getName() + " " + student.getAge());
			}
			System.out.println();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage() + "\n");
		}

	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub

		try {
			StudentValidator.validate(student);
			studentDAO.update(student);
			System.out.println("Updated Successfully\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\n");
		}

	}

	@Override
	public void searchByName(String name) {
		// TODO Auto-generated method stub

		try {
			StudentValidator.validateName(name);
			List<Student> studentList = studentDAO.searchbyName(name);
			if (studentList.isEmpty()) {
				System.out.println("No data found\n");
				return;
			}

			for (Student student : studentList) {
				System.out.println(student.getId() + " " + student.getName() + " " + student.getAge());
			}
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\n");
		}
	}

	@Override
	public void readById(int id) {
		// TODO Auto-generated method stub

		try {
			StudentValidator.validateId(id);
			Student student = studentDAO.readById(id);
			System.out.println(student.getId() + " " + student.getName() + " " + student.getAge() + "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\n");
		}

	}

	@Override
	public void commit() throws Exception {
		// TODO Auto-generated method stub
		studentDAO.commit();
		System.out.println("Committed successfully");

	}

	@Override
	public void rollback() throws Exception {
		// TODO Auto-generated method stub
		studentDAO.rollback();
		System.out.println("Rollbacked successfully");

	}

	@Override
	public void readAll() throws Exception {
		List<Student> studentList = new ArrayList<>();
		studentList = studentDAO.readAll();
		for (Student student : studentList) {
			System.out.println(student.getId() + " " + student.getName() + " " + student.getAge());
		}
		System.out.println();
	}

	@Override
	public void closeConnection() throws Exception {
		// TODO Auto-generated method stub
		studentDAO.closeConnection();
		System.out.println("Connection closed");

	}

}
