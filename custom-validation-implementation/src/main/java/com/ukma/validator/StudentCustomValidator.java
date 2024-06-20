package com.ukma.validator;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
// import com.google.common.collect.Lists;
import com.ukma.entity.Student;
import com.ukma.utils.StringValidationUtils;
import com.ukma.validator.CustomValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StudentCustomValidator implements CustomValidator {

    @Override
    public List<String> validate(Object object) {
        List<String> errors = new ArrayList<>();
        if (object == null) {
            errors.add("student is null!");
            return errors;
        }
        Student student = (Student) object;


        if (!StringValidationUtils.isEmail(student.getEmail())) {
            errors.add("email format is not correct!");
        }
        if (StringValidationUtils.isBlank(student.getName())) {
            errors.add("student's name is blank!");
        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                int nameMaxSize = objectMapper.readTree(new File("src/main/resources/validation-config.json"))
                        .get("nameMaxSize")
                        .asInt();
                if (student.getName().length() > nameMaxSize) {
                    errors.add("name length is bigger than max value(" + nameMaxSize + ")!");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (!StringValidationUtils.isUkrainianPhone(student.getPhone())) {
            errors.add("phone format is not correct!");
        }
        if (!StringUtils.isAllUpperCase(student.getFaculty())) {
            errors.add("faculty name's characters should all be upper case!");
        }

        return errors;
    }
}
