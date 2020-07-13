package org.example.notepad;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Notepad extends JFrame {
    private Font font = new Font("黑体", Font.ITALIC, 16);
    private JTextArea area = new JTextArea(20, 100);
    //菜单
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("文件（F）");
    private JMenu editMenu = new JMenu("编辑（E）");
    private JMenu layoutMenu = new JMenu("格式（L）");
    private JMenu viewMenu = new JMenu("查看（V）");
    private JMenu helpMenu = new JMenu("帮助（H）");
    //菜单项
    private JMenuItem newItem = new JMenuItem("新建（N）");
    private JMenuItem openItem = new JMenuItem("打开（O）");
    private JMenuItem saveItem = new JMenuItem("保存（S）");
    private JMenuItem exitItem = new JMenuItem("退出（X）");

    public Notepad() {
        this.setOther();
        this.addListener();
        this.addElements();
    }

    public Notepad(String title) {
        super(title);
        this.setOther();
        this.addListener();
        this.addElements();
    }

    private void setOther() {
        setSelf();
        area.setFont(font);

        this.setResizable(false);
        this.setVisible(true);
    }

    private void addListener() {
        saveItem.addActionListener(e -> {
            //1.弹出文件选择器
            JFileChooser fileChooser = new JFileChooser();
            int value = fileChooser.showSaveDialog(Notepad.this);
            if (value == 0) {
                //2.创建一个File对象
                File file = fileChooser.getSelectedFile();
                try {
                    file.createNewFile();
                    String result = area.getText();
                    //3.创建输出流
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(result);
                    writer.flush();
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        exitItem.addActionListener(e -> {
            System.exit(0);
        });
    }

    private void addElements() {
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(layoutMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);

        JScrollPane scrollPane = new JScrollPane(area);
        this.add(scrollPane);
        this.pack();
    }

    private void setSelf() {
        Rectangle rec = new Rectangle();
        rec.x = 300;
        rec.y = 200;
        rec.height = 600;
        rec.width = 800;
        this.setBounds(rec);
    }
}
