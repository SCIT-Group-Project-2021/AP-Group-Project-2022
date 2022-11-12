package JNWR.application.utilities;

import javax.swing.*;
import java.awt.*;

public class CustomRoundComboBox<E> extends JComboBox<E> {
    public CustomRoundComboBox() {
        setUI(new CustomRoundComboBoxUI());
    }

    public void sendFont(Font f){
        this.setFont(f);
    }
}
