public class FullTimeEmployee extends Employee implements EmployeeCategory {
    public FullTimeEmployee(int employeeId, String firstName, String lastName, String department) {
        super(employeeId, firstName, lastName, department);
    }

    @Override
    public String getCategory() {
        return "Full-Time";
    }
}
