import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//TextEditor class starts here
class TextEditor extends JFrame implements ActionListener {
    JTextArea ta = new JTextArea();
    int i, len1, len, pos1;
    String str = "", s3 = "", s2 = "", s4 = "", s32 = "", s6 = "", s7 = "", s8 = "", s9 = "";
    String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };
    CheckboxMenuItem chkb = new CheckboxMenuItem("Word Wrap");

    public TextEditor() {
        MenuBar mb = new MenuBar();
        setLayout(new BorderLayout());
        add("Center", ta);
        setMenuBar(mb);
        Menu m1 = new Menu("File");
        Menu m2 = new Menu("Edit");
        Menu m3 = new Menu("Tools");
        Menu m4 = new Menu("Help");
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);

        MenuItem mi1[] = {
                new MenuItem("New"), new MenuItem("Open"), new MenuItem("Save"), new MenuItem("Save As"),
                new MenuItem("Page Setup"), new MenuItem("Print"), new MenuItem("Exit")
        };
        MenuItem mi2[] = { new MenuItem("Delete"), new MenuItem("Cut"),
                new MenuItem("Copy"), new MenuItem("Paste"),
                new MenuItem("Find Next"), new MenuItem("Replace"),
                new MenuItem("Go To"), new MenuItem("Select All"),
                new MenuItem("Time Stamp") };
        MenuItem mi3[] = { new MenuItem("Choose Font"), new MenuItem("Compile"),
                new MenuItem("Run") };
        MenuItem mi4[] = { new MenuItem("Help Topics"),
                new MenuItem("About TextEditor") };
        MenuItem mi5[] = { new MenuItem("Serif"), new MenuItem("Sans Serif"), new MenuItem("Monospaced"),
                new MenuItem("Dialog"), new MenuItem("DialogInput") };
        for (int i = 0; i < mi1.length; i++) {
            m1.add(mi1[i]);
            mi1[i].addActionListener(this);
        }
        for (int i = 0; i < mi2.length; i++) {
            m2.add(mi2[i]);
            mi2[i].addActionListener(this);
        }
        m3.add(chkb);
        chkb.addActionListener(this);
        for (int i = 0; i < mi3.length; i++) {
            m3.add(mi3[i]);
            mi3[i].addActionListener(this);
        }
        for (int i = 0; i < mi4.length; i++) {
            m4.add(mi4[i]);
            mi4[i].addActionListener(this);
        }

        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        this.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                if (JOptionPane.showConfirmDialog(ta,
//                        "Are you sure you want to close this window?", "Close Window?",
//                        JOptionPane.YES_NO_OPTION,
//                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
//                    System.out.println("hi");
//                    System.exit(0);
//                }
//            }
//        });

        MyWindowsAdapter mw = new MyWindowsAdapter(this);
        addWindowListener(mw);
        setSize(500, 500);
        setTitle("untitled notepad");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String arg = (String) ae.getActionCommand();
        if (arg.equals("New")) {
            dispose();
            TextEditor t11 = new TextEditor();
            t11.setSize(500, 500);
            t11.setVisible(true);
        }
        try {
            if (arg.equals("Open")) {
                FileDialog fd1 = new FileDialog(this, "Select File", FileDialog.LOAD);
                fd1.setVisible(true);
                String s4 = "";
                s2 = fd1.getFile();
                s3 = fd1.getDirectory();
                s32 = s3 + s2;
                File f = new File(s32);
                FileInputStream fii = new FileInputStream(f);
                len = (int) f.length();
                for (int j = 0; j < len; j++) {
                    char s5 = (char) fii.read();
                    s4 = s4 + s5;
                }
                ta.setText(s4);
                fii.close();
            }
        } catch (IOException e) {
        }
        try {
            if (arg.equals("Save As")) {
                FileDialog dialog1 = new FileDialog(this, "Save As", FileDialog.SAVE);
                dialog1.setVisible(true);
                s7 = dialog1.getDirectory();
                s8 = dialog1.getFile();
                s9 = s7 + s8 + ".txt";
                s6 = ta.getText();
                len1 = s6.length();
                byte buf[] = s6.getBytes();
                File f1 = new File(s9);
                FileOutputStream fobj1 = new FileOutputStream(f1);
                for (int k = 0; k < len1; k++) {
                    fobj1.write(buf[k]);
                }
                fobj1.close();
            }
            this.setTitle(s8 + " TextEditor File");
        } catch (IOException e) {
        }
        try {
            if (arg.equals("Save As")) {
                FileDialog dialog1 = new FileDialog(this, "Save As", FileDialog.SAVE);
                dialog1.setVisible(true);
                s7 = dialog1.getDirectory();
                s8 = dialog1.getFile();
                s9 = s7 + s8 + ".txt";
                s6 = ta.getText();
                len1 = s6.length();
                byte buf[] = s6.getBytes();
                File f1 = new File(s9);
                FileOutputStream fobj1 = new FileOutputStream(f1);
                for (int k = 0; k < len1; k++) {
                    fobj1.write(buf[k]);
                }
                fobj1.close();
            }
            this.setTitle(s8 + " TextEditor File");
        } catch (IOException e) {
        }
        if (arg.equals("Exit")) {
            System.exit(0);
        }
        if (arg.equals("Cut")) {
            str = ta.getSelectedText();
            i = ta.getText().indexOf(str);
            ta.replaceRange(" ", i, i + str.length());
        }
        if (arg.equals("Copy")) {
            str = ta.getSelectedText();
        }
        if (arg.equals("Paste")) {
            pos1 = ta.getCaretPosition();
            ta.insert(str, pos1);
        }
        if (arg.equals("Delete")) {
            String msg = ta.getSelectedText();
            i = ta.getText().indexOf(msg);
            ta.replaceRange(" ", i, i + msg.length());
            msg = "";
        }
        if (arg.equals("Select All")) {
            String strText = ta.getText();
            int strLen = strText.length();
            ta.select(0, strLen);
        }
        if (arg.equals("Find Next")) {
            JFrame find = new JFrame();
            JTextArea inField = new JTextArea();
            JButton next = new JButton("Next");
            JTextArea outFIeld = new JTextArea();
            JButton replace = new JButton("Replace");

            find.setLayout(new FlowLayout());

            find.setSize(300, 150);
            inField.setSize(250, 50);
            ta.setCaretPosition(0);

            next.addActionListener((ActionEvent a) -> {
                String target = inField.getText();
                if (target.length() == 0) {
                    return;
                }
                String text = ta.getText();
                for (int i = ta.getCaretPosition(); i < text.length() - target.length() + 1; i++) {
                    if (text.startsWith(target, i)) {
                        ta.setCaretPosition(i);
                        ta.setSelectionStart(i);
                        ta.setSelectionEnd(i + target.length());
                        break;
                    }
                }
            });

            find.add(inField);
            find.add(next);

            find.setVisible(true);
        }
        if (arg.equals("Time Stamp")) {
            GregorianCalendar gcalendar = new GregorianCalendar();
            String h = String.valueOf(gcalendar.get(Calendar.HOUR));
            String m = String.valueOf(gcalendar.get(Calendar.MINUTE));
            String s = String.valueOf(gcalendar.get(Calendar.SECOND));
            String date = String.valueOf(gcalendar.get(Calendar.DATE));
            String mon = months[gcalendar.get(Calendar.MONTH)];
            String year = String.valueOf(gcalendar.get(Calendar.YEAR));
            String hms = "Time" + " - " + h + ":" + m + ":" + s + " Date" + " - " + date + " " + mon + " " + year + " ";
            int loc = ta.getCaretPosition();
            ta.insert(hms, loc);
        }
        if (arg.equals("About TextEditor")) {
            AboutDialog d1 = new AboutDialog(this, "About TextEditor");

            d1.setVisible(true);
            setSize(500, 500);
        }
        if (arg.equals("Print")) {
            System.out.println(ta.getText());
        }
        if (arg.equals("Choose Font")) {

        }
    }



    public static void main(String args[]) {
        TextEditor to = new TextEditor();
    }

}

class MyWindowsAdapter extends WindowAdapter {
    TextEditor tt;

    public MyWindowsAdapter(TextEditor ttt) {
        tt = ttt;
    }

    public void windowClosing(WindowEvent we) {
        if (JOptionPane.showConfirmDialog(tt,
                "Would you like to save before exiting?", "Close Window?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            FileDialog dialog1 = new FileDialog(tt, "Save As", FileDialog.SAVE);
            dialog1.setVisible(true);
            String s7 = dialog1.getDirectory();
            String s8 = dialog1.getFile();
            String s9 = s7 + s8 + ".txt";
            String s6 = tt.ta.getText();
            int len1 = s6.length();
            byte buf[] = s6.getBytes();
            File f1 = new File(s9);
            FileOutputStream fobj1 = null;
            try {
                fobj1 = new FileOutputStream(f1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            for (int k = 0; k < len1; k++) {
                try {
                    fobj1.write(buf[k]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                fobj1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.exit(0);
    }
}

class AboutDialog extends Dialog implements ActionListener {
    AboutDialog(Frame parent, String title) {
        super(parent, title, false);
        this.setResizable(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(500, 300);
    }

    public void actionPerformed(ActionEvent ae) {
        dispose();
    }
}
