package com.company.employee;
import static com.company.employee.EmployeeConstants.ADD_EMPLOYEE_OUTPUT_CHANNEL;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

public interface EmployeeSource extends Source {
	@Output(ADD_EMPLOYEE_OUTPUT_CHANNEL)
	MessageChannel addEmployeeOutput();
}
