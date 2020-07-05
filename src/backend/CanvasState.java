package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.List;

// TODO hacer que la figuras sea iterable / funcion figures vuela

public class CanvasState {

    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

}
