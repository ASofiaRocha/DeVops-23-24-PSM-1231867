package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testGetJobYears() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@baggins.com");
        assertEquals(1, employee.getJobYears());
    }

    @Test
    void testSetJobYears() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@baggins.com");
        employee.setJobYears(2);
        assertEquals(2, employee.getJobYears());
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@baggins.com");

        assertEquals("Frodo", employee.getFirstName());
        assertEquals("Baggins", employee.getLastName());
        assertEquals(1, employee.getJobYears());
    }


    @Test
    void testSetJobYearsWithNegativeValue() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 20, "frodo@baggins.com");
        assertThrows(IllegalArgumentException.class, () -> {
            employee.setJobYears(-2);
        });
    }

    @Test
    void testSetEmail() {
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 20, "frodo@baggins.com");

        employee.setEmail("frodo@baggins.com");
        assertEquals("frodo@baggins.com", employee.getEmail());
    }

}