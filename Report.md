# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for? 

        Comma-Separated Values.  It is a file format used to store tabular data, where each line represents a data record, and each value within the record is separated by a comma.

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

        Declaring List<IEmployee> instead of ArrayList<HourlyEmployee> promotes flexibility, abstraction, and code reusability.  By using the IEmployee interface, the code can work with any class that implements this interface, such as HourlyEmployee and SalaryEmployee.  This follows the principle of programming to an interface, not an implementation, allowing easy substitution of different employee types without changing the code.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?

        This is called a has-a relationship.  It means one class contains or uses another class as part of its attributes, representing composition or aggregation.

4. Can you provide an example of a has-a relationship in your code (if one exists)?

        the PayStub class has a has-a relationship with the IEmployee attributes.  It stores employeeName and employeeId as part of its attributes, representing the employee associated with the pay stub.

5. Can you provide an example of an is-a relationship in your code (if one exists)?

         the HourlyEmployee and SalaryEmployee classes have an is-a relationship with the IEmployee interface because they both implement IEmployee, meaning each is a type of employee.

6. What is the difference between an interface and an abstract class?

        Abstract classes can have both abstract and non-abstract methods, while interfaces can only have abstract methods.

7. What is the advantage of using an interface over an abstract class?

        The big advantage of an interface is that a class can implement multiple interfaces, which isn’t possible with abstract classes.  This makes interfaces super flexible when you want a class to follow different “rules” or behaviors.  Plus, interfaces are great for defining a clear contract without worrying about shared code.

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 

        No, this code is not valid because in Java, we can’t use primitive types like int with generic classes like List.  Generics work with objects, not primitive data types.
        To fix it:
        `List<Integer> numbers = new ArrayList<Integer>();`

9. Which class/method is described as the "driver" for your application? 

        `PayrollGenerator` class, the main() method inside this class is the entry point that controls the flow of the program, handling file input/output and coordinating the payroll processing.

10. How do you create a temporary folder for JUnit Testing? 

        We can create a temporary folder in JUnit by using the @TempDir annotation. This annotation tells JUnit to create a temporary directory before the test runs, which is automatically deleted after the test finishes. It’s useful for testing file operations without affecting actual project files.
## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

        To address salary inequality through the payroll system, it's essential to incorporate additional data points such as gender, race/ethnicity, job title, department, and years of experience into employee records. This enriched dataset enables the generation of detailed reports that compare compensation across various demographic groups within similar roles, facilitating the identification of pay disparities. Integrating regular pay equity analyses into the payroll process allows for proactive adjustments, ensuring fair compensation practices are maintained. It's also crucial to consider pretax benefits like health insurance and retirement contributions, as variations in these benefits can obscure underlying pay inequalities. By leveraging payroll analytics, organizations can align compensation strategies with diversity, equity, and inclusion initiatives, promoting a more equitable workplace.

        Research indicates that the gender pay gap in STEM fields persists, with men earning significantly more than women. For instance, men in STEM fields have annual salaries nearly $15,000 higher than women, with men earning $85,000 compared to women's $60,828. Additionally, Latina and Black women in STEM earn around $33,000 less, averaging about $52,000 per year. By conducting thorough pay equity analyses, organizations can identify and rectify wage disparities, fostering a fairer and more inclusive working environment
    
* https://www.ukg.com/blog/payroll/what-payrolls-role-pay-equity?utm_source=chatgpt.com
* https://www.aauw.org/resources/research/the-stem-gap/?utm_source=chatgpt.com
* https://www.bamboohr.com/blog/conduct-a-pay-equity-analysis?utm_source=chatgpt.com