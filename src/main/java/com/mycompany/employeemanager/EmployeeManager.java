package com.mycompany.employeemanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class EmployeeManager {

// This cocde represents employee information
    static class Employee {
        String empNumber, lastName, firstName, birthday, address, phoneNumber;
        String sss, philhealth, tin, pagibig, status, position, supervisor;
        double basicSalary, riceSubsidy, phoneAllowance, clothingAllowance;
        double grossSemiMonthly, hourlyRate;
        double sssDeduction, pagibigDeduction, philhealthDeduction, tax, hmoDeduction, netSalary;
		
	//This code reads the data and separates it

        public Employee(String[] data) {
            empNumber = data[0];
            lastName = data[1];
            firstName = data[2];
            birthday = data[3];
            address = data[4];
            phoneNumber = data[5];
            sss = data[6];
            philhealth = data[7];
            tin = data[8];
            pagibig = data[9];
            status = data[10];
            position = data[11];
            supervisor = data[12];
            basicSalary = Double.parseDouble(data[13]);
            riceSubsidy = Double.parseDouble(data[14]);
            phoneAllowance = Double.parseDouble(data[15]);
            clothingAllowance = Double.parseDouble(data[16]);
            computeSalary();
        }
//This computes the deminis and deductions
        void computeSalary() {
            grossSemiMonthly = basicSalary / 2 + riceSubsidy + phoneAllowance + clothingAllowance;
            hourlyRate = basicSalary / 25 / 8;
            sssDeduction = basicSalary * 0.045;
            pagibigDeduction = -100;
            philhealthDeduction = basicSalary * 0.03;
            tax = basicSalary * 0.1;
            hmoDeduction = -500;
            netSalary = grossSemiMonthly - (sssDeduction + pagibigDeduction + philhealthDeduction + tax + hmoDeduction);
        }
//This displays the text and data for each field grouped by a specific title
        void display() {
            System.out.println("==========================================");
            System.out.println("Employee #: " + empNumber);
            System.out.println("Name: " + lastName + ", " + firstName);
            System.out.println("Birthday: " + birthday);
            System.out.println("Address: " + address);
            System.out.println("Phone: " + phoneNumber);
            System.out.println("SSS #: " + sss);
            System.out.println("Philhealth #: " + philhealth);
            System.out.println("TIN #: " + tin);
            System.out.println("Pag-ibig #: " + pagibig);
            System.out.println("Status: " + status);
            System.out.println("Position: " + position);
            System.out.println("Supervisor: " + supervisor);
            System.out.printf("Basic Salary: %.2f\n", basicSalary);
            System.out.printf("Rice Subsidy: %.2f\n", riceSubsidy);
            System.out.printf("Phone Allowance: %.2f\n", phoneAllowance);
            System.out.printf("Clothing Allowance: %.2f\n", clothingAllowance);
            System.out.printf("Gross Semi-monthly Rate: %.2f\n", grossSemiMonthly);
            System.out.printf("Hourly Rate: %.2f\n", hourlyRate);
            System.out.printf("SSS Deduction: %.2f\n", sssDeduction);
            System.out.printf("Pag-ibig Deduction: %.2f\n", pagibigDeduction);
            System.out.printf("Philhealth Deduction: %.2f\n", philhealthDeduction);
            System.out.printf("Tax: %.2f\n", tax);
            System.out.printf("HMO Deduction: %.2f\n", hmoDeduction);
            System.out.printf("Net Salary: %.2f\n", netSalary);
            System.out.println("==========================================");
        }
    }

    static Map<String, Employee> employeeMap = new HashMap<>();

    public static void main(String[] args) {
        loadEmployees();
//This displays the initial function of asking for instructions
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEmployee Manager");
            System.out.println("[1] Search Employee");
            System.out.println("[2] Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter Employee #: ");
                    String empNo = scanner.nextLine();
                    Employee emp = employeeMap.get(empNo);
                    if (emp != null) {
                        emp.display();
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case "2":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
/* This code was made by Gavril and his groupmates who helped in
Researching for this code as well as encoding the data into the text file
Comments are made individually
*/
    private static void loadEmployees() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                EmployeeManager.class.getClassLoader().getResourceAsStream("employees.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employee emp = new Employee(data);
                employeeMap.put(emp.empNumber, emp);
            }
            System.out.println("Employee data loaded successfully.");
        } catch (IOException | NullPointerException e) {
            System.out.println("Error loading employee data: " + e.getMessage());
        }
    }
}
