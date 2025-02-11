# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)
```mermaid
---
title: Class Diagram
---
classDiagram
class IEmployee {
+String getName()
+String getID()
+double getPayRate()
+String getEmployeeType()
+double getYTDEarnings()
+double getYTDTaxesPaid()
+double getPretaxDeductions()
+IPayStub runPayroll(double hoursWorked)
+String toCSV()
}

    class IPayStub {
        +double getPay()
        +double getTaxesPaid()
        +String toCSV()
    }

    class ITimeCard {
        +String getEmployeeID()
        +double getHoursWorked()
    }

    class HourlyEmployee {
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double pretaxDeductions
        +runPayroll(double hoursWorked)
        +toCSV()
    }

    class SalaryEmployee {
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double pretaxDeductions
        +runPayroll(double hoursWorked)
        +toCSV()
    }

    class PayStub {
        -String employeeName
        -double netPay
        -double taxes
        -double ytdEarnings
        -double ytdTaxesPaid
        +toCSV()
    }

    class TimeCard {
        -String employeeID
        -double hoursWorked
    }

    IEmployee <|-- HourlyEmployee
    IEmployee <|-- SalaryEmployee
    IPayStub <|-- PayStub
    ITimeCard <|-- TimeCard
    HourlyEmployee --> PayStub
    SalaryEmployee --> PayStub
```




## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
3. continue to add your brainstorm here (you don't need to super formal - this is a brainstorm) - yes, you can change the bullets above to something that fits your design.
   Test that the HourlyEmployee class properly returns name using getName().

    * Test that the `HourlyEmployee` class properly returns `name` using `getName()`
    * Test that the `HourlyEmployee` class properly returns `id` using `getID()`
    * Test that the `SalaryEmployee` class correctly calculates payroll using `runPayroll()` with valid hours.
    * Test that the `HourlyEmployee` calculates overtime pay correctly when hours exceed 40.
    * Test that the `runPayroll()` method correctly skips payroll for negative hours
    * Test that the `PayStub` class returns the correct net pay, taxes, YTD earnings, and YTD taxes paid.
    * Test that the `Builder` class accurately converts CSV data into `IEmployee` objects.
    * Test that the `Builder` class accurately converts CSV data into `ITimeCard` objects
    * Test that `FileUtil.readFileToList()` correctly reads file content and skips the header line.
    * Test that `FileUtil.writeFile()` successfully writes data to a file and handles backups appropriately.
    * Test that `PayrollGenerator` correctly matches employees to time cards and generates pay stubs.
    * Test edge cases like zero hours worked, maximum integer limits, and invalid CSV formatting.


## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.





## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 
