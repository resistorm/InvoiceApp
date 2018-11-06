/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.views;

import app.main.ApplicationManager;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




/**
 *
 * @author daan-
 */
public class DatePicker extends JFrame {

    private LocalDate currentDate;
    private MonthHeader monthHeader;
    private MonthBody monthBody;
    private MonthFooter monthFooter;






    public DatePicker() {
        super("Datum Kiezer");
        currentDate = ApplicationManager.getInstance().getProjectManager().getInvoiceDetails().getLocalInvoiceDate();
        monthHeader = new MonthHeader();
        monthBody = new MonthBody();
        monthFooter = new MonthFooter();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(monthHeader, BorderLayout.NORTH);
        getContentPane().add(monthBody, BorderLayout.CENTER);
        getContentPane().add(monthFooter, BorderLayout.SOUTH);
        validate();
        pack();
    }






    public void loadAndShowDatePicker() {
        SwingUtilities.invokeLater(() -> {
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }






    public void nextMonth() {
        currentDate = currentDate.plusMonths(1L);
        monthHeader.updateCurrentMonth();
        monthBody.updateCurrentDays();
    }






    public void prevMonth() {
        currentDate = currentDate.minusMonths(1L);
        monthHeader.updateCurrentMonth();
        monthBody.updateCurrentDays();
    }






    public void selectDay(int day) {
        currentDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), day);
    }






    public void commit() {
        SwingUtilities.invokeLater(() -> {
            ApplicationManager.getInstance().getProjectManager().updateInvoiceDate(currentDate);
            this.setVisible(false);
            this.dispose();
        });
    }






    public void cancel() {
        SwingUtilities.invokeLater(() -> {
            this.setVisible(false);
            this.dispose();
        });
    }




    public class MonthHeader extends JPanel {

        private JButton nextMonth;
        private JButton prevMonth;
        private JLabel currentMonth;






        public MonthHeader() {
            super(new FlowLayout(FlowLayout.CENTER));
            nextMonth = new JButton(" -> ");
            prevMonth = new JButton(" <- ");
            currentMonth = new JLabel(currentDate.getMonth().toString() + ", " + currentDate.getYear());
            add(prevMonth);
            add(currentMonth);
            add(nextMonth);
            nextMonth.addActionListener((ActionEvent ae) -> {
                nextMonth();
            });
            prevMonth.addActionListener((ActionEvent ae) -> {
                prevMonth();
            });
        }






        public void updateCurrentMonth() {
            SwingUtilities.invokeLater(() -> {
                currentMonth.setText(currentDate.getMonth().toString() + ", " + currentDate.getYear());
            });
        }

    }




    public class MonthBody extends JPanel {

        private JLabel[] dayHeaders = new JLabel[]{new JLabel("Ma"), new JLabel("Di"), new JLabel("Wo"), new JLabel("Do"), new JLabel("Vr"), new JLabel("Za"), new JLabel("Zo")};
        private DayButton[][] days = new DayButton[6][7];






        public MonthBody() {
            super(new GridLayout(7, 7, 5, 5));
            for (int i = 0; i < days.length; i++) {
                days[i] = new DayButton[7];
                for (int d = 0; d < 7; d++) {
                    DayButton dayButton = new DayButton();
                    days[i][d] = dayButton;
                }
            }
            updateCurrentDays();
            for (int i = 0; i < 7; i++) {
                add(dayHeaders[i]);
            }
            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < 7; i++) {
                    add(days[j][i]);
                }
            }

        }






        public void updateCurrentDays() {
            LocalDate pointerDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
            int inset = pointerDate.getDayOfWeek().getValue() - 1;
            pointerDate = pointerDate.minusDays((long) inset);
            for (int week = 0; week < days.length; week++) {
                for (int day = 0; day < 7; day++) {
                    DayButton dayButton = days[week][day];
                    dayButton.setButtonValue(pointerDate.getDayOfMonth());
                    if (pointerDate.getMonthValue() != currentDate.getMonthValue()) {
                        dayButton.setEnabled(false);
                    } else {
                        dayButton.setEnabled(true);
                    }

                    pointerDate = pointerDate.plusDays(1L);
                }
            }

        }

    }




    public class MonthFooter extends JPanel {

        private JButton submitButton;
        private JButton cancelButton;






        public MonthFooter() {
            super(new FlowLayout(FlowLayout.RIGHT));
            submitButton = new JButton("Selecteren");
            cancelButton = new JButton("Annuleren");
            add(submitButton);
            add(cancelButton);
            submitButton.addActionListener((ActionEvent ae) -> {
                commit();
            });
            cancelButton.addActionListener((ActionEvent ae) -> {
                cancel();
            });

        }

    }




    public class DayButton extends JButton {

        private int day;






        public DayButton() {
            super("");
            day = 0;
            addActionListener((ActionEvent ae) -> {
                selectDay(day);
            });
        }






        public void setButtonValue(int day) {
            this.day = day;
            setText(String.valueOf(day));
        }

    }


}

