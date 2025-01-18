import javax.swing.*;
import java.util.Objects;

public class DropdownManager extends CalculatorWindow{
    public static void setVisibility(String selectedOption, JComboBox<String> dropdown2, JComboBox<String> dropdown3, JComboBox<String> dropdown4, JComboBox<String> dropdown5, JComboBox<String> dropdown6, JComboBox<String> dropdown7){
        if (Objects.equals(selectedOption, "Stocks")) {
            dropdown2.setVisible(false);
            dropdown3.setVisible(true);
        } else if (Objects.equals(selectedOption, "Bonds")) {
            dropdown2.setVisible(false);
            dropdown4.setVisible(true);
        } else if (Objects.equals(selectedOption, "Real Estate")) {
            dropdown2.setVisible(false);
            dropdown5.setVisible(true);
        } else if (Objects.equals(selectedOption, "Cryptocurrency")) {
            dropdown2.setVisible(false);
            dropdown6.setVisible(true);
        } else if (Objects.equals(selectedOption, "Commodities")) {
            dropdown2.setVisible(false);
            dropdown7.setVisible(true);
        }
    }
}
