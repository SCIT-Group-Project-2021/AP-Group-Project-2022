package JNWR.application.utilities;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class CustomRoundTextField extends JFormattedTextField {
    private CustomRoundTextFieldUI textUI;

    public CustomRoundTextField() {
        textUI = new CustomRoundTextFieldUI(this);
        setUI(textUI);
    }

    public CustomRoundTextField(MaskFormatter fmt) {
        textUI = new CustomRoundTextFieldUI(this);
        setUI(textUI);
        DefaultFormatterFactory factory = new DefaultFormatterFactory(fmt);
        this.setFormatterFactory(factory);
    }

    public void addItemSuggestion(String text) {
        textUI.getItems().add(text);
    }

    public void removeItemSuggestion(String text) {
        textUI.getItems().remove(text);
    }

    public void clearItemSuggestion() {
        textUI.getItems().clear();
    }

    public void setRound(int round) {
        textUI.setRound(round);
    }

    public int getRound() {
        return textUI.getRound();
    }

}
