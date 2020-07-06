package backend;

import backend.model.DrawableFigure;
import backend.model.Figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// TODO hacer que la figuras sea iterable / funcion figures vuela
//TODO no tiene sentido que haya una array list conviene una Linked List para las profundidades

public class CanvasState{ //implements Iterable<CanvasState>

    private final List<DrawableFigure> list = new LinkedList<>();

    public void addFigure(DrawableFigure figure) {
        list.add(figure);
    }

    public Iterable<DrawableFigure> figures() {
        return new ArrayList<>(list);
    }

    public void removeFigure(DrawableFigure figure){
        list.remove(figure);

    }
    public void setToTop(DrawableFigure figure){
        list.remove(figure);
        list.add(figure);
    }
    public void setToBottom(DrawableFigure figure){
        list.remove(figure);
        list.add(0,figure);
    }
}
