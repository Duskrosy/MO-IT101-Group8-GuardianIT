package com.mycompany.employeemanager;

import java.io.*;
import java.util.*;

public class EmployeeManager {

    static class Employee {
        String empNumber, lastName, firstName, birthday, address, phoneNumber;
        String sss, philhealth, tin, pagibig, status, position, supervisor;
        double basicSalary, riceSubsidy, phoneAllowance, clothingAllowance;
        double grossSemiMonthly, hourlyRate;
        double sssDeduction, pagibigDeduction, philhealthDeduction, tax, hmoDeduction, netSalary;

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

        void computeSalary() {
            grossSemiMonthly = basicSalary / 2 + riceSubsidy + phoneAllowance + clothingAllowance;
            hourlyRate = basicSalary / 21 / 8;
            sssDeduction = basicSalary * 0.045;
            pagibigDeduction = 100;
            philhealthDeduction = basicSalary * 0.03;
            tax = basicSalary * 0.1;
            hmoDeduction = 500;
            netSalary = grossSemiMonthly - (sssDeduction + pagibigDeduction + philhealthDeduction + tax + hmoDeduction);
        }

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

        String toFileString() {
            return String.join(",", empNumber, lastName, firstName, birthday, address, phoneNumber,
                    sss, philhealth, tin, pagibig, status, position, supervisor,
                    String.valueOf(basicSalary), String.valueOf(riceSubsidy),
                    String.valueOf(phoneAllowance), String.valueOf(clothingAllowance));
        }
    }

    static Map<String, Employee> employeeMap = new LinkedHashMap<>();
    static final String FILE_PATH = "src/main/resources/employees.txt";

    public static void main(String[] args) {
        loadEmployees();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEmployee Manager");
            System.out.println("[1] Search Employee");
            System.out.println("[2] Add Employee");
            System.out.println("[3] Edit Employee");
            System.out.println("[4] Delete Employee");
            System.out.println("[5] Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    searchEmployee(scanner);
                    break;
                case "2":
                    addEmployee(scanner);
                    break;
                case "3":
                    editEmployee(scanner);
                    break;
                case "4":
                    deleteEmployee(scanner);
                    break;
                case "5":
                    saveEmployees();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void searchEmployee(Scanner scanner) {
        System.out.print("Enter Employee #: ");
        String empNo = scanner.nextLine();
        Employee emp = employeeMap.get(empNo);
        if (emp != null) {
            emp.display();
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void addEmployee(Scanner scanner) {
        try {
            System.out.println("Enter new employee details:");
            String[] data = new String[17];
            System.out.print("Employee #: "); data[0] = scanner.nextLine();
            System.out.print("Last Name: "); data[1] = scanner.nextLine();
            System.out.print("First Name: "); data[2] = scanner.nextLine();
            System.out.print("Birthday: "); data[3] = scanner.nextLine();
            System.out.print("Address: "); data[4] = scanner.nextLine();
            System.out.print("Phone Number: "); data[5] = scanner.nextLine();
            System.out.print("SSS #: "); data[6] = scanner.nextLine();
            System.out.print("Philhealth #: "); data[7] = scanner.nextLine();
            System.out.print("TIN #: "); data[8] = scanner.nextLine();
            System.out.print("Pag-ibig #: "); data[9] = scanner.nextLine();
            System.out.print("Status: "); data[10] = scanner.nextLine();
            System.out.print("Position: "); data[11] = scanner.nextLine();
            System.out.print("Immediate Supervisor: "); data[12] = scanner.nextLine();
            System.out.print("Basic Salary: "); data[13] = scanner.nextLine();
            System.out.print("Rice Subsidy: "); data[14] = scanner.nextLine();
            System.out.print("Phone Allowance: "); data[15] = scanner.nextLine();
            System.out.print("Clothing Allowance: "); data[16] = scanner.nextLine();

            Employee newEmp = new Employee(data);
            employeeMap.put(newEmp.empNumber, newEmp);
            saveEmployees();
            System.out.println("Employee added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    private static void editEmployee(Scanner scanner) {
        System.out.print("Enter Employee # to edit: ");
        String empNo = scanner.nextLine();
        Employee emp = employeeMap.get(empNo);
        if (emp != null) {
            System.out.println("Editing employee: " + empNo);
            System.out.println("Leave blank to keep current value.");

            System.out.print("Last Name [" + emp.lastName + "]: ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) emp.lastName = input;

            System.out.print("First Name [" + emp.firstName + "]: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) emp.firstName = input;

            // Repeat for other fields...
            System.out.print("Basic Salary [" + emp.basicSalary + "]: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) emp.basicSalary = Double.parseDouble(input);

            emp.computeSalary();
            saveEmployees();
            System.out.println("Employee updated.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee # to delete: ");
        String empNo = scanner.nextLine();
        if (employeeMap.containsKey(empNo)) {
            employeeMap.remove(empNo);
            saveEmployees();
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void loadEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employee emp = new Employee(data);
                employeeMap.put(emp.empNumber, emp);
            }
            System.out.println("Employee data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading employee data: " + e.getMessage());
        }
    }

    private static void saveEmployees() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Employee emp : employeeMap.values()) {
                bw.write(emp.toFileString());
                bw.newLine();
            }
            System.out.println("Employee data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }
}
