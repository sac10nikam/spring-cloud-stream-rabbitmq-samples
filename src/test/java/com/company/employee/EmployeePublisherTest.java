package com.company.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
public class EmployeePublisherTest<T> extends AbstractTest {

	@Autowired
	private EmployeeSource processor;

	@Autowired
	private MessageCollector messageCollector;

	@Test
	@SuppressWarnings("unchecked")
	public void testEmployeeMessage_withValidData() {
		Message<T> message = buildEmployeeMessage();
		sendMessage(message);
		Message<Employee> received = pollMessage();
		assertTrue(received.toString().contains(((Employee) message.getPayload()).getId()));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testEmployeeMessage_withValidId() {
		Message<T> message = buildEmployeeMessage();
		sendMessage(message);
		Message<Employee> received = pollMessage();
		assertTrue(received.toString().contains(((Employee) message.getPayload()).getId()));
	}
	
	@Test
	public void testEmployeeRegistration_withValidData() throws Exception {
		String uri = "/api/employee/register";

		String inputJson = super.mapToJson(buildEmployee());
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@SuppressWarnings("unchecked")
	private Message<T> buildEmployeeMessage() {
		return (Message<T>) MessageBuilder.withPayload(buildEmployee()).build();
	}
	
	private Employee buildEmployee() {
		return Employee.builder().id("1").name("Sachin").build();
	}
	private void sendMessage(Message<T> message) {
		processor.addEmployeeOutput().send(message);
	}
	
	@SuppressWarnings("unchecked")
	private Message<Employee> pollMessage() {
		return (Message<Employee>) messageCollector.forChannel(processor.addEmployeeOutput()).poll();
	}
}