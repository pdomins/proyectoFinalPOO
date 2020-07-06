package frontend;

import backend.model.DrawableFigure;
import backend.model.Figure;
import frontend.Drawable.Drawable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// TODO hacer que la figuras sea iterable / funcion figures vuela
//TODO no tiene sentido que haya una array list conviene una Linked List para las profundidades

public class CanvasState{ //implements Iterable<CanvasState>

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
    public boolean hasFigures(){
        return !list.isEmpty();
    }
}
