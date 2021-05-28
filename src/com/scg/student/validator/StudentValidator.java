package com.scg.student.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scg.student.dao.StudentDAO;
import com.scg.student.dao.StudentDAOImpl;
import com.scg.student.exception.InvalidInputException;
import com.scg.student.vo.Student;

public class StudentValidator {
	
	public static void validateId(int id) throws Exception {
		
		if(id<1) {
			throw new InvalidInputException("Invalid Id");
		}
		
	}
	
	public static void validateName(String name) throws Exception {
		
		String format="^[a-zA-Z\\s]*$";
		Pattern pattern = Pattern.compile(new String (format));
	    Matcher matcher = pattern.matcher(name);
	    if(!matcher.matches()){
	    	throw new InvalidInputException("Invalid name");
	    }
		
	}

	public static void validateAge(int age) throws Exception {
		
		if(age<5||age>100) {
			throw new InvalidInputException("Invalid Age");
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
			throw new InvalidInputException("Invalid page number");
		}
		
	}

}
