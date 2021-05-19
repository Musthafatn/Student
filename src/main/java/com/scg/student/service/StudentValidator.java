package main.java.com.scg.student.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.com.scg.student.dao.StudentDAOImplement;
import main.java.com.scg.student.vo.Student;

public class StudentValidator {
	
	StudentDAOImplement dao=new StudentDAOImplement();
	
	public static void validateId(int id) throws Exception {
		
		if(id<1) {
			throw new InvalidInput("Invalid Id");
		}
		
	}
	
	public static void validateName(String name) throws Exception {
		
		String format="^[a-zA-Z\\s]*$";
		Pattern pattern = Pattern.compile(new String (format));
	    Matcher matcher = pattern.matcher(name);
	    if(!matcher.matches()){
	    	throw new InvalidInput("Invalid name");
	    }
		
	}

	public static void validateAge(int age) throws Exception {
		
		if(age<5||age>100) {
			throw new InvalidInput("Invalid Age");
		}
	
	}
	
	public static void validate(Student s) throws Exception {
		
		//validate id
		StudentValidator.validateId(s.getId());
		
		//validate name
		StudentValidator.validateName(s.getName());
				
		//validate age
		StudentValidator.validateAge(s.getAge());		
		
	}

	public static void validatePageNumber(int pageNumber) throws Exception {
		
		if(pageNumber<1) {
			throw new InvalidInput("Invalid page number");
		}
		
	}

}
