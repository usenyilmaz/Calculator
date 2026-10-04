package View;

import javax.swing.*;
import Controller.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        final int FrameWidth = 600;
        final int FrameLength = 800;

        frame.getContentPane().setPreferredSize(new Dimension(FrameWidth, FrameLength));
        frame.pack();

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Set<JButton> buttonSet = new HashSet<>();

        // Buttons for operators and numbers
        JButton CE = new JButton("CE");
        JButton C = new JButton("C");
        JButton DEL = new JButton("DEL");

        JButton additionSign = new JButton("+");
        JButton minus = new JButton("-");
        JButton divisionMark = new JButton("/");
        JButton multiplicationSign = new JButton("*");

        JButton comma = new JButton(",");
        JButton equals = new JButton("=");

        JButton B1 = new JButton("1");
        JButton B2 = new JButton("2");
        JButton B3 = new JButton("3");
        JButton B4 = new JButton("4");
        JButton B5 = new JButton("5");
        JButton B6 = new JButton("6");
        JButton B7 = new JButton("7");
        JButton B8 = new JButton("8");
        JButton B9 = new JButton("9");
        JButton B0 = new JButton("0");

        buttonSet.add(B1);
        buttonSet.add(B2);
        buttonSet.add(B3);
        buttonSet.add(B4);
        buttonSet.add(B5);
        buttonSet.add(B6);
        buttonSet.add(B7);
        buttonSet.add(B8);
        buttonSet.add(B9);
        buttonSet.add(B0);
        buttonSet.add(CE);
        buttonSet.add(C);
        buttonSet.add(DEL);
        buttonSet.add(comma);
        buttonSet.add(equals);
        buttonSet.add(additionSign);
        buttonSet.add(multiplicationSign);
        buttonSet.add(divisionMark);
        buttonSet.add(minus);

        // Bounds for the calculator screen and button area on the UI
        final int screenBorder = 200;

        // Button width and length
        final int buttonLength = (FrameLength - screenBorder) / 5;
        final int buttonWidth = FrameWidth / 4;

        // first row
        CE.setBounds(0 * buttonWidth, screenBorder + 0 * buttonLength, buttonWidth, buttonLength);
        C.setBounds(1 * buttonWidth, screenBorder + 0 * buttonLength, buttonWidth, buttonLength);
        DEL.setBounds(2 * buttonWidth, screenBorder + 0 * buttonLength, buttonWidth, buttonLength);

        // second row
        B1.setBounds(0 * buttonWidth, screenBorder + 1 * buttonLength, buttonWidth, buttonLength); // x axis, y axis,
                                                                                                   // width, height
        B2.setBounds(1 * buttonWidth, screenBorder + 1 * buttonLength, buttonWidth, buttonLength);
        B3.setBounds(2 * buttonWidth, screenBorder + 1 * buttonLength, buttonWidth, buttonLength);
        divisionMark.setBounds(3 * buttonWidth, screenBorder + 1 * buttonLength, buttonWidth, buttonLength);

        // third row
        B4.setBounds(0 * buttonWidth, screenBorder + 2 * buttonLength, buttonWidth, buttonLength);
        B5.setBounds(1 * buttonWidth, screenBorder + 2 * buttonLength, buttonWidth, buttonLength);
        B6.setBounds(2 * buttonWidth, screenBorder + 2 * buttonLength, buttonWidth, buttonLength);
        multiplicationSign.setBounds(3 * buttonWidth, screenBorder + 2 * buttonLength, buttonWidth, buttonLength);

        // fourth row
        B7.setBounds(0 * buttonWidth, screenBorder + 3 * buttonLength, buttonWidth, buttonLength);
        B8.setBounds(1 * buttonWidth, screenBorder + 3 * buttonLength, buttonWidth, buttonLength);
        B9.setBounds(2 * buttonWidth, screenBorder + 3 * buttonLength, buttonWidth, buttonLength);
        minus.setBounds(3 * buttonWidth, screenBorder + 3 * buttonLength, buttonWidth, buttonLength);

        // fifth row,
        comma.setBounds(0 * buttonWidth, screenBorder + 4 * buttonLength, buttonWidth, buttonLength);
        B0.setBounds(1 * buttonWidth, screenBorder + 4 * buttonLength, buttonWidth, buttonLength);
        equals.setBounds(2 * buttonWidth, screenBorder + 4 * buttonLength, buttonWidth, buttonLength);

        /*
         * arrangement of numbers on the screen:
         * CE C DEL
         * 1 2 3 /
         * 4 5 6 x
         * 7 8 9 -
         * , 0 =
         */

        for (JButton b : buttonSet) {
            // Source - https://stackoverflow.com/a/20462394
            // Posted by Colin, modified by community. See post 'Timeline' for change
            // history
            // Retrieved 2026-09-13, License - CC BY-SA 3.0
            b.setFont(new Font("Arial", Font.PLAIN, 50));
            frame.add(b);
        }
        // ---------------------------end of buttons---------------------------

        // TextFields and event listeners
        final int textFieldFont = 125;
        final int errorLogsBorder = 150;

        TextField textField = new TextField();
        textField.setBounds(0, 0, FrameWidth, errorLogsBorder);
        textField.setVisible(true);
        textField.setFont(new Font("Arial", Font.PLAIN, textFieldFont));
        frame.add(textField);

        final int errorLogsFont = 25;

        TextField errorLogs = new TextField();
        errorLogs.setBounds(0, errorLogsBorder, FrameWidth, screenBorder - errorLogsBorder);
        errorLogs.setVisible(true);
        errorLogs.setFont(new Font("Arial", Font.PLAIN, errorLogsFont));
        frame.add(errorLogs);

        // ButtonListener

        ButtonListener buttonListener = new ButtonListener(textField, buttonSet, errorLogs);

        // setVisible() must remain at the very end
        frame.setVisible(true);

    }
}
