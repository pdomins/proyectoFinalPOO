package frontend;

import frontend.Drawable.Drawable;

import java.util.*;


public class CanvasState{

    private final List<Drawable> list = new LinkedList<>();

    public void addFigure(Drawable figure) {
        list.add(figure);
    }

    public Iterable<Drawable> figures() {
        return new ArrayList<>(list);
    }


    public void removeFigure(Drawable figure){
        list.remove(figure);

    }
    public void setToTop(Drawable figure){
        list.remove(figure);
        list.add(figure);
    }
    public void setToBottom(Drawable figure){
        list.remove(figure);
        list.add(0,figure);
    }
}
