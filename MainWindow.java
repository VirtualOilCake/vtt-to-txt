//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.oilman.vtttotxt;

import com.oilman.vtttotxt.MainWindow.1;
import com.oilman.vtttotxt.MainWindow.2;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainWindow {
    static String inputFilePath;
    static String outputFilePath;

    public MainWindow() {
    }

    public void showUI() {
        JFrame window = new JFrame();
        window.setTitle("VTT转换");
        window.setSize(400, 500);
        window.setDefaultCloseOperation(3);
        window.setLocationRelativeTo((Component)null);
        FlowLayout flow = new FlowLayout();
        window.setLayout(flow);
        Dimension sizeForTexts = new Dimension(280, 30);
        JTextField inputTextField = new JTextField();
        inputTextField.setSize(200, 100);
        inputTextField.setPreferredSize(sizeForTexts);
        window.add(inputTextField);
        JLabel inputDescription = new JLabel();
        inputDescription.setText("输入地址");
        window.add(inputDescription);
        JTextField outputTextField = new JTextField();
        outputTextField.setSize(200, 100);
        outputTextField.setPreferredSize(sizeForTexts);
        outputTextField.setText("请将文件拖入上面输入框");
        window.add(outputTextField);
        JLabel outputDescription = new JLabel();
        outputDescription.setText("输出地址");
        window.add(outputDescription);
        inputTextField.setTransferHandler(new 1(this, outputTextField, inputTextField));
        JButton button = new JButton("转换");
        button.addActionListener(new 2(this));
        window.add(button);
        window.setVisible(true);
    }
}
