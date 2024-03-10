package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testGetJobYears() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        assertEquals(1, employee.getJobYears());
    }

    @Test
    void testSetJobYears() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        employee.setJobYears(2);
        assertEquals(2, employee.getJobYears());
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);

        assertEquals("Frodo", employee.getFirstName());
        assertEquals("Baggins", employee.getLastName());
        assertEquals(1, employee.getJobYears());
    }


    @Test
    void testSetJobYearsWithNegativeValue() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 20);
        assertThrows(IllegalArgumentException.class, () -> {
            employee.setJobYears(-2);
        });
    }

}