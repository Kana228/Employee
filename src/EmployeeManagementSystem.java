import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementSystem {
    private List<Employee> employees;

    public EmployeeManagementSystem() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void editEmployee(int employeeId, Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId() == employeeId) {
                employees.set(i, updatedEmployee);
                break;
            }
        }
    }

    public void deleteEmployee(int employeeId) {
        List<Employee> employeesToRemove = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                employeesToRemove.add(employee);
            }
        }

        employees.removeAll(employeesToRemove);
    }



    public List<Employee> getAllEmployees() {
        return employees;
    }
}
