package JNWR.application.utilities;

import javax.swing.*;

public class CustomRoundTextField extends JTextField {
    private CustomRoundTextFieldUI textUI;

    public CustomRoundTextField() {
        textUI = new CustomRoundTextFieldUI(this);
        setUI(textUI);
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
