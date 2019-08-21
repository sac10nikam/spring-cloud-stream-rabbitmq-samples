package com.company.employee;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@EnableBinding(EmployeeMessageChannel.class)
@Slf4j
public class EmployeeSubscriber {

	@StreamListener(EmployeeConstants.MESSAGES)
	public void handleEmployeeMessage(Employee employee){
		log.debug("Subscriber Received Employee Message is: " + employee.toString());
	}
}
