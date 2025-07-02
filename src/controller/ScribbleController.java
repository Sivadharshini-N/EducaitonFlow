package controller;

import model.ScribbleModel;
import view.ScribbleView;
import java.time.LocalDate;

public class ScribbleController {
    private final ScribbleModel model = new ScribbleModel();
    private final ScribbleView view = new ScribbleView();

    public void writeScribble() {
        String date = LocalDate.now().toString();  // current date
        String entry = view.askScribble();
        model.saveEntry(date, entry);
        view.showMessage("Scribble saved successfully!");
    }

    public void viewScribble() {
        String date = view.askDate();
        String entry = model.getEntryByDate(date);
        view.showMessage(entry);
    }
}
