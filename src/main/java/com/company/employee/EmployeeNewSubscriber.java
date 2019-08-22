package com.company.employee;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@Service
@Transactional
@EnableBinding(Sink.class)
@Slf4j
public class EmployeeNewSubscriber {

	@StreamListener(target = EmployeeConstants.MESSAGES_INPUT)
	public void handleEmployeeMessage(Employee employee){
		log.debug("New Employee Subscriber Message Received is: " + employee.toString());
	}
}
