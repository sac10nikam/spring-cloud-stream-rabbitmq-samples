package com.company.employee;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@EnableBinding(EmployeeSource.class)
@Slf4j
@RequestMapping(path = "/api/employee")
public class EmployeePublisher {

	@Autowired
	private EmployeeSource employeeSource;

	@PostMapping(path = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public <T> ResponseEntity<T> register(@Valid @RequestBody Employee employee) {
		employeeSource.addEmployeeOutput().send(MessageBuilder.withPayload(employee).build());
		log.debug(employee.toString());
		return new ResponseEntity<>(CREATED);
	}
}
