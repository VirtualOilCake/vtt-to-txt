package com.oilman.vtttotxt;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


public class MainWindow {
    static String inputFilePath, outputFilePath;

    public void showUI() {
        //使用系统主题
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //窗体类
        javax.swing.JFrame window = new javax.swing.JFrame();
        //标题
        window.setTitle("VTT转换");
        //窗体大小（具体值跟电脑显示器的像素有关，可调整到合适大小）
        window.setSize(400, 500);
        //设置退出进程的方法
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        //流式布局管理器
        java.awt.FlowLayout flow = new java.awt.FlowLayout();
        window.setLayout(flow);   //给窗体设置为流式布局——从左到右然后从上到下排列自己写的组件顺序


        //自定义文本框大小
        java.awt.Dimension sizeForTexts = new java.awt.Dimension(280, 30);

        //输入文件位置文本框
        JTextField inputTextField = new JTextField();
        inputTextField.setSize(200, 100);
        inputTextField.setPreferredSize(sizeForTexts);


        //////
        window.add(inputTextField);
        //后面的文本
        JLabel inputDescription = new JLabel();
        inputDescription.setText("输入地址");
        window.add(inputDescription);


        //输出文件文本框
        JTextField outputTextField = new JTextField();
        outputTextField.setSize(200, 100);
        outputTextField.setPreferredSize(sizeForTexts);
        outputTextField.setText("请将文件拖入上面输入框");
        window.add(outputTextField);

        //后面的文本
        JLabel outputDescription = new JLabel();
        outputDescription.setText("输出地址");
        window.add(outputDescription);

        //拖拽文件识别
        inputTextField.setTransferHandler(new TransferHandler() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);
                    inputFilePath = o.toString();
                    if (inputFilePath.startsWith("[")) {
                        inputFilePath = inputFilePath.substring(1);
                    }
                    if (inputFilePath.endsWith("]")) {
                        inputFilePath = inputFilePath.substring(0, inputFilePath.length() - 1);
                    }

                    //操作识别到的地址
                    if (inputFilePath.toLowerCase().endsWith(".vtt")) {
                        outputFilePath = inputFilePath.substring(0, inputFilePath.length() - 4) + ".txt";
                        outputTextField.setText(outputFilePath);
                    }
                    System.out.println(inputFilePath);
                    inputTextField.setText(inputFilePath);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean canImport(JComponent comp, DataFlavor[] flavors) {
                for (DataFlavor flavor : flavors) {
                    if (DataFlavor.javaFileListFlavor.equals(flavor)) {
                        return true;
                    }
                }
                return false;
            }
        });

        //按钮
        javax.swing.JButton button = new javax.swing.JButton("转换");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputFilePath!=null && outputFilePath!=null) {
                    Utils utils = new Utils();
                    String txtString;
                    List<String> vttList;
                    try {
                        vttList = utils.fileReader(inputFilePath);
                        txtString = utils.getTxt(vttList);
                        utils.fileWriter(txtString, outputFilePath);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        window.add(button);


        //设置可见
        window.setVisible(true);
    }
}