import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeGUI {
    private EmployeeManagementSystem managementSystem;
    private JFrame frame;
    private JList<String> employeeList;
    private DefaultListModel<String> listModel;

    public EmployeeGUI() {
        managementSystem = new EmployeeManagementSystem();
        listModel = new DefaultListModel<>();
        employeeList = new JList<>(listModel);

        frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(new JScrollPane(employeeList), BorderLayout.CENTER);

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int employeeId = listModel.getSize() + 1;
                String firstName = JOptionPane.showInputDialog("Enter First Name:");
                String lastName = JOptionPane.showInputDialog("Enter Last Name:");
                String department = JOptionPane.showInputDialog("Enter Department:");

                String[] categories = {"Part-Time", "Full-Time"};
                String selectedCategory = (String) JOptionPane.showInputDialog(null, "Select Employee Category:",
                        "Employee Category", JOptionPane.QUESTION_MESSAGE, null, categories, categories[0]);

                Employee newEmployee;
                if ("Part-Time".equals(selectedCategory)) {
                    newEmployee = new PartTimeEmployee(employeeId, firstName, lastName, department);
                } else {
                    newEmployee = new FullTimeEmployee(employeeId, firstName, lastName, department);
                }

                managementSystem.addEmployee(newEmployee);
                listModel.addElement(newEmployeeToString(newEmployee));
            }
        });

        JButton editButton = new JButton("Edit Employee");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = employeeList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Employee selectedEmployee = managementSystem.getAllEmployees().get(selectedIndex);

                    String firstName = JOptionPane.showInputDialog("Enter New First Name:");
                    String lastName = JOptionPane.showInputDialog("Enter New Last Name:");
                    String department = JOptionPane.showInputDialog("Enter New Department:");

                    String[] categories = {"Part-Time", "Full-Time"};
                    String selectedCategory = (String) JOptionPane.showInputDialog(null, "Select Employee Category:",
                            "Employee Category", JOptionPane.QUESTION_MESSAGE, null, categories, selectedEmployee instanceof PartTimeEmployee
                                    ? "Part-Time" : "Full-Time");

                    Employee updatedEmployee;
                    if ("Part-Time".equals(selectedCategory)) {
                        updatedEmployee = new PartTimeEmployee(selectedEmployee.getEmployeeId(), firstName, lastName, department);
                    } else {
                        updatedEmployee = new FullTimeEmployee(selectedEmployee.getEmployeeId(), firstName, lastName, department);
                    }

                    managementSystem.editEmployee(selectedIndex + 1, updatedEmployee);
                    listModel.set(selectedIndex, newEmployeeToString(updatedEmployee));
                }
            }
        });


        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = employeeList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    managementSystem.deleteEmployee(selectedIndex + 1);
                    listModel.remove(selectedIndex);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private String newEmployeeToString(Employee employee) {
        return "ID: " + employee.getEmployeeId() + " | Name: " + employee.getFirstName() + " " + employee.getLastName() + " | Department: "
                + employee.getDepartment() + " | Category: " + ((EmployeeCategory)employee).getCategory();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeGUI());
    }
}
