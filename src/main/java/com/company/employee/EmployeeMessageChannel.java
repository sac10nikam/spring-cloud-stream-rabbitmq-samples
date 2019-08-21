package com.company.employee;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EmployeeMessageChannel {
	@Input
	SubscribableChannel messages();
}
