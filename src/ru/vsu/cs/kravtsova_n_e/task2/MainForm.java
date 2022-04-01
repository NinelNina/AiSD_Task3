package ru.vsu.cs.kravtsova_n_e.task2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainForm extends JFrame{
    private  JFileChooser fileChooser;
    private JPanel mainPanel;
    private JTextPane textPaneInput;
    private JButton loadButton;
    private JButton sortDescendingButton;
    private JButton sortAscendingButton;
    private JButton saveButton;
    private JTextPane textPaneOutput;

    public MainForm(){
        this.setTitle("Task2 - Bubble Sort для связного списка");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                        List<String> lines;
                        try (Scanner scanner = new Scanner(new File(fileChooser.getSelectedFile().getPath()), "UTF-8")) {
                            lines = new ArrayList<>();
                            while (scanner.hasNext()) {
                                lines.add(scanner.nextLine());
                            }
                        }
                        textPaneInput.setText(lines.get(0));
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(mainPanel, exception, "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        sortAscendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
                fillLinkedList(list, textPaneInput.getText());
                try {
                    list.sort((a, b) -> a - b);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                setToOutput(list);
            }
        });

        sortDescendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
                fillLinkedList(list, textPaneInput.getText());
                try {
                    list.sort((a, b) -> b - a);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                setToOutput(list);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                        String file = fileChooser.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        try (PrintWriter out = new PrintWriter(file)) {
                            out.println(textPaneOutput.getText());
                        }
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void fillLinkedList(SimpleLinkedList<Integer> list, String data){
        Scanner scanner = new Scanner(data);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        while (scanner.hasNext()) {
            list.addLast(scanner.nextInt());
        }
    }

    private <T> void setToOutput(SimpleLinkedList<T> list){
        StringBuilder output = new StringBuilder();
        int i = 0;
        for (T value : list){
            output.append(i > 0 ? ", " : "").append(value);
            i++;
        }
        textPaneOutput.setText(output.toString());
    }
}
