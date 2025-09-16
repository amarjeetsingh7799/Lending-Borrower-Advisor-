package com.lendingadvisor;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LendingAdvisorGUI {
    JFrame frame;
    JLabel backgroundLabel;
    JButton addBorrowerButton, addLenderButton, matchLoanButton, viewDataButton, exitButton;
    JLabel footerLabel;
    List<Borrower> borrowers = LendingAdvisor.borrowers;
    List<Lender> lenders = LendingAdvisor.lenders;
    int xPos = 600;

    public LendingAdvisorGUI() {
        frame = new JFrame("Lending Advisor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);

        JLabel label = new JLabel("Find the Best Loan or Lender in Just a Few Clicks!!");
        label.setBounds(50, 50, 1500, 50);
        label.setFont(new Font("Arial", Font.ITALIC, 40));
        label.setForeground(Color.RED);
        frame.add(label);

        ImageIcon background = new ImageIcon("images/Backimage.jpg");
        Image scaledImage = background.getImage().getScaledInstance(1600, 800, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 1600, 800);

        addBorrowerButton = new JButton("Add Borrower");
        addLenderButton = new JButton("Add Lender");
        matchLoanButton = new JButton("Match Loan");
        viewDataButton = new JButton("View Data");
        exitButton = new JButton("Exit");

        JButton[] buttons = {addBorrowerButton, addLenderButton, matchLoanButton, viewDataButton, exitButton};
        for (JButton button : buttons) {
            button.setFocusPainted(false);
            button.setBackground(new Color(0, 51, 102));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.setOpaque(true);
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(51, 153, 255));
                }
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(0, 51, 102));
                }
            });
        }

        addBorrowerButton.addActionListener(e -> AddBorrowerForm());
        addLenderButton.addActionListener(e -> AddLenderForm());
        matchLoanButton.addActionListener(e -> openMatchLoanForm());
        viewDataButton.addActionListener(e -> viewData());
        exitButton.addActionListener(e -> exitApplication());

        footerLabel = new JLabel("Designed by Nishant | Contact: support@example.com");
        footerLabel.setForeground(Color.BLACK);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setOpaque(true);
        footerLabel.setBackground(Color.WHITE);
        footerLabel.setBounds(xPos, 430, 400, 30);

        Timer timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xPos -= 2;
                if (xPos < -300) {
                    xPos = 600;
                }
                footerLabel.setBounds(xPos, 760, 400, 30);
                frame.repaint();
            }
        });
        timer.start();

        addBorrowerButton.setBounds(100, 250, 200, 40);
        addLenderButton.setBounds(100, 310, 200, 40);
        matchLoanButton.setBounds(100, 370, 200, 40);
        viewDataButton.setBounds(100, 420, 200, 40);
        exitButton.setBounds(100, 480, 200, 40);

        frame.add(addBorrowerButton);
        frame.add(addLenderButton);
        frame.add(matchLoanButton);
        frame.add(viewDataButton);
        frame.add(exitButton);
        frame.add(footerLabel);
        frame.add(backgroundLabel);
        frame.setVisible(true);
    }

    private void AddBorrowerForm() {
        JFrame borrowerFrame = new JFrame("Submit Borrower Details");
        borrowerFrame.setSize(400, 300);
        borrowerFrame.setLayout(new GridLayout(6, 2));
        borrowerFrame.setResizable(false);
        borrowerFrame.setLocationRelativeTo(frame);
        borrowerFrame.getContentPane().setBackground(new Color(144, 238, 144));

        JTextField nameField = new JTextField();
        JTextField loanAmountField = new JTextField();
       ```
