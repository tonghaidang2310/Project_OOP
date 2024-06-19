package demo;

public class SharedData {
    private static SharedData instance = new SharedData();
    private String selectedButton;

    private SharedData() {}

    public static SharedData getInstance() {
        return instance;
    }

    public String getSelectedButton() {
        return selectedButton;
    }

    public void setSelectedButton(String selectedButton) {
        this.selectedButton = selectedButton;
    }
}
