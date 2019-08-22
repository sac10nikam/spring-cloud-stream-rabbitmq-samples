package com.company.employee;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeePublisherTest<T> {

	@Autowired
	private EmployeeSource processor;

	@Autowired
	private MessageCollector messageCollector;

	@Test
	@SuppressWarnings("unchecked")
	public void testEmployeeRegistration() {
		Message<T> message = buildEmployeeMessage();
		sendMessage(message);
		Message<Employee> received = pollMessage();
		assertTrue(received.toString().contains(((Employee) message.getPayload()).getId()));
	}

	@SuppressWarnings("unchecked")
	private Message<T> buildEmployeeMessage() {
		Employee employee = Employee.builder().id("1").name("Sachin").build();
		return (Message<T>) MessageBuilder.withPayload(employee).build();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testEmployeeRegistration_validId() {
		Message<T> message = buildEmployeeMessage();
		sendMessage(message);
		Message<Employee> received = pollMessage();
		assertTrue(received.toString().contains(((Employee) message.getPayload()).getId()));
	}
	
	private void sendMessage(Message<T> message) {
		processor.addEmployeeOutput().send(message);
	}
	
	@SuppressWarnings("unchecked")
	private Message<Employee> pollMessage() {
		return (Message<Employee>) messageCollector.forChannel(processor.addEmployeeOutput()).poll();
	}
}