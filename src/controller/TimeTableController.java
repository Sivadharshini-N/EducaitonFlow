package controller;

import model.TimeTableModel;
import view.TimeTableView;

public class TimeTableController {
    private final TimeTableModel model = new TimeTableModel();
    private final TimeTableView view = new TimeTableView();

    private final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private final String[] slots = {"9-10", "10-11", "11-12", "12-1", "2-3", "3-4"};

    public void addFullTimetable() {
        int startHour = view.askStartHour();
        int endHour = view.askEndHour();

        // Generate time slots like 7-8, 8-9, ...
        String[] slots = generateTimeSlots(startHour, endHour);

        for (String day : days) {
            view.showMessage("\nEnter subjects for " + day + ":");
            for (String slot : slots) {
                String subject = view.askSubjectForSlot(slot);
                model.saveOrUpdate(day, slot, subject);
            }
        }
        view.showMessage("\n✅ Full timetable added successfully!");
    }

    private String[] generateTimeSlots(int start, int end) {
        String[] slots = new String[end - start];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = (start + i) + "-" + (start + i + 1);
        }
        return slots;
    }

    public void updateSlot() {
        String day = view.askDay();
        String slot = view.askSlot();
        String subject = view.askSubject();
        model.saveOrUpdate(day, slot, subject);
        view.showMessage("✅ Slot updated successfully!");
    }

    public void displayTimetable() {
        String[][] table = model.getTimetable(days, slots);
        view.displayTable(days, slots, table);
    }

    public void timeTableMenu() {
        while (true) {
            view.showMessage("\n--- Time Table Manager ---");
            view.showMessage("1. Add Full Weekly Time Table\n2. Update a Specific Slot\n3. Display Time Table\n4. Back to Main Menu");
            int choice = view.askOption();

            switch (choice) {
                case 1 -> addFullTimetable();
                case 2 -> updateSlot();
                case 3 -> displayTimetable();
                case 4 -> {
                    return; // Back to Main
                }
                default -> view.showMessage("Invalid choice. Please try again.");
            }
        }
    }
}
