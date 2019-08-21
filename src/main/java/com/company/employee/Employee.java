package com.company.employee;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Employee implements Serializable {
	private static final long serialVersionUID = -2625801882058803683L;
	private String empName;
	private String empID;
}
