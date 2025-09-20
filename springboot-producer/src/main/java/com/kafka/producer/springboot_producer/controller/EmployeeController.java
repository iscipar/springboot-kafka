package com.kafka.producer.springboot_producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.bo.springboot_bo.avro.Employee;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    KafkaTemplate<String, Employee> kafkaTemplateEmployee;

    @PostMapping("/dispatch")
    public String employeeDetail(@RequestBody Employee employee) {
        kafkaTemplateEmployee.send("springboot-topic-employee", employee);
        return "Employee " + employee.getName() + " dispatched successfully!";
    }
}