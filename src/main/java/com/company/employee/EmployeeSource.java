package com.company.employee;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

public interface EmployeeSource extends Source {
	@Output
	MessageChannel addEmployeeOutput();
}
