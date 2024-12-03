package jaxb.test;

import jaxb.model.Department;
import jaxb.model.Employee;
import jaxb.model.Organization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestExample {

    private static final String XML_FILE = "org-info.xml";

    public static void main(String[] args) {
        Employee emp1 = new Employee("E01", "Tom", null);
        Employee emp2 = new Employee("E02", "Mary", "E01");
        Employee emp3 = new Employee("E03", "John", null);
        Employee emp4 = new Employee("E09", "BOBYK", null);
        Employee emp5 = new Employee("E00", "BOBY", "E05");
        Employee emp6 = new Employee("E05", "BOB", null);

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        List<Employee> employees2 = new ArrayList<>();
        employees2.add(emp4);
        employees2.add(emp5);
        employees2.add(emp6);
        Department dept = new Department("D01", "ACCOUNTING", "NEW YORK");
        Department dept2 = new Department("D02", "ACCOUNTING2", "BOSTON");
        dept.setEmployees(employees);
        dept2.setEmployees(employees2);
        List<Department> departments = new ArrayList<>();
        departments.add(dept);
        departments.add(dept2);

        Organization org = new Organization(departments, "111", "TOP", "MINSK");

        try {
            // Создание JAXB контекста и маршаллера
            JAXBContext context = JAXBContext.newInstance(Organization.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Запись в XML файл
            marshaller.marshal(org, new File(XML_FILE));
            System.out.println("Запись в файл: " + new File(XML_FILE).getAbsolutePath());

            // Чтение из XML файла
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Organization orgFromFile = (Organization) unmarshaller.unmarshal(new FileReader(XML_FILE));
            System.out.println("Организация N " + orgFromFile.getOrgNo() + "\tС названием\t" + orgFromFile.getOrgName() + "\tпо месту пребывания\t" + orgFromFile.getOrglocation());
            for (Department department : orgFromFile.getDepartments()) {
                System.out.println("Департамент: " + department.getDeptName());
                for (Employee emp : department.getEmployees()) {
                    System.out.println("Сотрудник: " + emp.getEmpName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}