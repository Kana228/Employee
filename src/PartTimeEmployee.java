public class PartTimeEmployee extends Employee implements EmployeeCategory {
    public PartTimeEmployee(int employeeId, String firstName, String lastName, String department) {
        super(employeeId, firstName, lastName, department);
    }

    @Override
    public String getCategory() {
        return "Part-Time";
    }
}
