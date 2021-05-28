package com.scg.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.scg.student.exception.InvalidInputException;
import com.scg.student.vo.Student;

public class StudentDAOImpl implements StudentDAO {

	private Connection connnection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String query;
	private List<Student> studentList = new ArrayList<>();

	@Override
	public void connect() throws Exception {

		final String url = "jdbc:mysql://localhost:3306/scg";
		final String uname = "root";
		final String pass = "root";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connnection = DriverManager.getConnection(url, uname, pass);
		connnection.setAutoCommit(false);

	}

	@Override
	public boolean existStudent(int id) throws Exception {

		query = String.format("select id from student where id=%d", id);
		preparedStatement = connnection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			preparedStatement.close();
			return true;
		}
		return false;

	}

	@Override
	public void insert(Student s) throws Exception {

		int id = s.getId();
		String name = s.getName();
		int age = s.getAge();
		if (existStudent(id)) {
			throw new InvalidInputException("Id already exist");
		}
		query = "insert into student values(?,?,?)";
		preparedStatement = connnection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.setInt(3, age);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	@Override
	public void delete(int id) throws Exception {

		if (!existStudent(id)) {
			preparedStatement.close();
			throw new InvalidInputException("Id does not exist");
		}

		query = String.format("delete from student where id=%d", id);
		preparedStatement = connnection.prepareStatement(query);
		preparedStatement.executeUpdate();
		preparedStatement.close();

	}

	@Override
	public List<Student> readByPage(int pageSize, int fromIndex) throws Exception {

		query = String.format("select * from student rows limit %d offset %d", pageSize, fromIndex);
		preparedStatement = connnection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		studentList.clear();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");
			Student s = new Student(id, name, age);
			studentList.add(s);
		}
		preparedStatement.close();
		return studentList;
	}

	@Override
	public void update(Student student) throws Exception {

		if (!existStudent(student.getId())) {
			preparedStatement.close();
			throw new InvalidInputException("Id does not exist");
		}

		int id = student.getId();
		String name = student.getName();
		int age = student.getAge();
		query = String.format("update student set name='%s',age=%d where id=%d", name, age, id);
		preparedStatement = connnection.prepareStatement(query);
		preparedStatement.executeUpdate();
		preparedStatement.close();

	}

	@Override
	public List<Student> searchbyName(String pattern) throws Exception {

		query = "select * from student where name like ?";
		preparedStatement = connnection.prepareStatement(query);
		preparedStatement.setString(1, "%" + pattern + "%");
		resultSet = preparedStatement.executeQuery();
		studentList.clear();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");
			Student s = new Student(id, name, age);
			studentList.add(s);
		}
		preparedStatement.close();
		return studentList;

	}

	@Override
	public Student readById(int idToSearch) throws Exception {

		query = String.format("select * from student where id=%d", idToSearch);
		preparedStatement = connnection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			preparedStatement.close();
			throw new InvalidInputException("Id not found");
		}
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		int age = resultSet.getInt("age");
		Student s = new Student(id, name, age);
		preparedStatement.close();
		return s;

	}

	@Override
	public void commit() throws Exception {
		// TODO Auto-generated method stub
		connnection.commit();

	}

	@Override
	public void rollback() throws Exception {
		// TODO Auto-generated method stub
		connnection.rollback();

	}

	@Override
	public List<Student> readAll() throws Exception {
		query = "select * from student";
		preparedStatement = connnection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		studentList.clear();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");
			Student s = new Student(id, name, age);
			studentList.add(s);
		}
		preparedStatement.close();
		return studentList;
	}

	@Override
	public void closeConnection() throws Exception {
		// TODO Auto-generated method stub
		connnection.close();

	}

}
