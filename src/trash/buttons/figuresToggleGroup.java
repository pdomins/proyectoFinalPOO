package trash.buttons;

import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class figuresToggleGroup {

    ToggleButton[] toolsArr = {new selectionButton(), new rectangleButton(),new squareButton(),
            new lineButton(), new circleButton(),new ellipseButton()};

    public void createToggleGroup(){
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
    }

    public ToggleButton isOn(){
        ToggleButton selected = null;
        for (ToggleButton tool:toolsArr){
            if (tool.isSelected()){
                selected=tool;
                break;
            }
        }
        return selected;
    }

    public ToggleButton[] getToolsArr() {
        return toolsArr;
    }
}
