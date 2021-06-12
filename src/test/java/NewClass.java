/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ELVIS
 * 
 */
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.text.JTextComponent;
public class NewClass extends JPanel{
     private MainUwc uwcschool = new MainUwc();
    private MainStudenPanel studentsPanel = new MainStudentsPanel();

    public NewClass () {
        studentsPanel.setFocusable(false);
        studentsPanel.setBorder(BorderFactory.createTitledBorder("Current Human"));

        JPanel btnPanel = new JPanel();
        btnPanel.add(new JButton(new AddStudentsAction("Add")));
        btnPanel.add(new JButton(new NextAction("Next")));
        btnPanel.add(new JButton(new PreviousAction("Previous")));

        setLayout(new BorderLayout());
        add(studentsPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.PAGE_END);
    }

    private class AddStudentsAction extends AbstractAction {
        MainStudentsPanel innerStudentsPanel = new MainStudents();

        public AddStudentsAction(String name) {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            innerStudentsPanel.clear();
            Component parentComponent = MainClass.this;
            MainStudentsPanel message = innerStudentsPanel;
            String title = "Create a Human";
            int optionType = JOptionPane.OK_CANCEL_OPTION;
            int messageType = JOptionPane.PLAIN_MESSAGE;
            int selection = JOptionPane.showConfirmDialog(parentComponent,
                    message, title, optionType, messageType);

            if (selection == JOptionPane.OK_OPTION) {
                uwcschool.addHuman(innerStudentsPanel.createstudents());
                studentsPanel.setHuman(uwcschool.getCurrentStudents());
            }
        }
    }

    private class NextAction extends AbstractAction {

        public NextAction(String name) {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MainStudents nextStudents = uwcschool.next();
            if (nextStudents != null) {
                studentsPanel.setHuman(nextStudents);
            }
        }
    }

    private class PreviousAction extends AbstractAction {

        public PreviousAction(String name) {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MainStudents previousHuman = uwcstudents.previous();
            if (previousStudents != null) {
                studentsPanel.setStudents(previousHuman);
            }
        }
    }

    private static void createAndShowGui() {
        MainClass mainPanel = new MainClass();

        JFrame frame = new JFrame("SimpleCityGui");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}

@SuppressWarnings("serial")
class SimpleStudentsPanel extends JPanel {
    private static final int COLS = 10;
    private static final Insets INSETS = new Insets(5, 5, 5, 5);
    private JTextField firstNameField = new JTextField(COLS);
    private JTextField lastNameField = new JTextField(COLS);
    private JTextComponent[] textComponents = { firstNameField, lastNameField };
    private MainStudents students;

    public MainStudentsPanel() {
        setLayout(new GridBagLayout());
        add(new JLabel("First Name:"), createGbc(0, 0));
        add(firstNameField, createGbc(1, 0));
        add(new JLabel("Last Name:"), createGbc(0, 1));
        add(lastNameField, createGbc(1, 1));
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        for (JTextComponent jTextComponent : textComponents) {
            jTextComponent.setFocusable(focusable);
        }
    }

    public void setStudents(MainStudents students) {
        this.students = students;
        firstNameField.setText(students.getFirstName());
        lastNameField.setText(students.getLastName());
    }

    public MainStudents getStudents() {
        return students;
    }

    public void clear() {
        this.students = null;
        for (JTextComponent jTextComponent : textComponents) {
            jTextComponent.setText("");
        }
    }

    public MainStudents createStudents() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        students = new MainStudents(firstName, lastName);
        return students;
    }

    private static GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = INSETS;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }
}

class MainUwc {
    private List<MainStudents> studentsList = new ArrayList<>();
    private int studentsListIndex = -1; // start with a nonsense value

    public void addStudents(MainStudents h) {
        studentsList.add(h);
        if (studentsListIndex == -1) {
            studentsListIndex = 0;
        }
    }

    public MainUwc getStudents(int index) {
        return studentsList.get(index);
    }

    public MainStudents getCurrentStudents() {
        if (studentsListIndex == -1) {
            return null;
        }
        return studentsList.get(studentsListIndex);

    }

    public MainStudents next() {
        if (studentsListIndex == -1) {
            return null;
        }

        studentsListIndex++;
        studentsListIndex %= studentsList.size(); // set back to 0 if == size
        return studentsList.get(studentsListIndex);
    }

    public MainStudents previous() {
        if (studentsListIndex == -1) {
            return null;
        }

        studentsListIndex--;
        studentsListIndex += studentsList.size();
        studentsListIndex %= studentsList.size();
        return studentsList.get(studentsListIndex);
    }
}

class MainStudents {
    private String firstName;
    private String lastName;

    public MainStudents(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result
                + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MainStudents other = (MainStudents) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SimpleHuman [firstName=" + firstName + ", lastName=" + lastName
                + "]";
    }

}
   

    
}
