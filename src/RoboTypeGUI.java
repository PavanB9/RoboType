import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.*;

public class RoboTypeGUI extends JFrame {
    private JTextArea inputTextArea;
    private JButton startButton;
    private JButton cancelButton;
    private JButton resetButton;
    private JLabel statusLabel;
    private JLabel countdownLabel;
    private JSlider speedSlider;
    private JCheckBox typoCheckBox;
    private JCheckBox deletionCheckBox;
    private boolean isTyping = false;
    private Robot robot;

    private static final int NORMAL_TYPING_CHANCE = 70;
    private static final int SLOW_TYPING_CHANCE = 88;
    private static final int TYPO_CHANCE = 95;
    private static final int LONG_BREAK_CHANCE = 97;
    private static final int MIN_DELETE_INDEX = 5;
    private static final int NORMAL_DELAY_MS = 40;
    private static final int SLOW_DELAY_MS = 150;
    private static final int VERY_SLOW_DELAY_MS = 400;
    private static final int DELETE_DELAY_MS = 60;
    private static final int TYPO_DELAY_MS = 150;
    private static final int LONG_BREAK_MS = 1000;
    private static final int PUNCTUATION_PAUSE_MS = 200;

    private static final Map<Character, Integer> CHAR_TO_KEYEVENT = new HashMap<>();

    static {
        for (char c = 'a'; c <= 'z'; c++) {
            CHAR_TO_KEYEVENT.put(c, KeyEvent.VK_A + (c - 'a'));
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            CHAR_TO_KEYEVENT.put(c, KeyEvent.VK_A + (c - 'A'));
        }
        for (char c = '0'; c <= '9'; c++) {
            CHAR_TO_KEYEVENT.put(c, KeyEvent.VK_0 + (c - '0'));
        }
        CHAR_TO_KEYEVENT.put(' ', KeyEvent.VK_SPACE);
        CHAR_TO_KEYEVENT.put('.', KeyEvent.VK_PERIOD);
    }

    public RoboTypeGUI() {
        setTitle("RoboType - Automated Typing Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(this, "Error: Could not initialize robot", "Error", JOptionPane.ERROR_MESSAGE);
        }

        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(250, 250, 250));

        // Title
        JLabel titleLabel = new JLabel("RoboType");
        titleLabel.setFont(new Font("System", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createVerticalStrut(8));

        JLabel subtitleLabel = new JLabel("Automated Typing Simulator");
        subtitleLabel.setFont(new Font("System", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(128, 128, 128));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subtitleLabel);

        mainPanel.add(Box.createVerticalStrut(28));

        // Input text area
        JLabel inputLabel = new JLabel("Text to Type:");
        inputLabel.setFont(new Font("System", Font.PLAIN, 13));
        inputLabel.setForeground(new Color(0, 0, 0));
        mainPanel.add(inputLabel);

        mainPanel.add(Box.createVerticalStrut(6));

        inputTextArea = new JTextArea(8, 40);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setFont(new Font("Menlo", Font.PLAIN, 13));
        inputTextArea.setForeground(new Color(0, 0, 0));
        inputTextArea.setBackground(new Color(255, 255, 255));
        inputTextArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        JScrollPane scrollPane = new JScrollPane(inputTextArea);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(scrollPane);

        mainPanel.add(Box.createVerticalStrut(20));

        // Speed slider
        JLabel speedLabel = new JLabel("Typing Speed:");
        speedLabel.setFont(new Font("System", Font.PLAIN, 13));
        speedLabel.setForeground(new Color(0, 0, 0));
        mainPanel.add(speedLabel);

        mainPanel.add(Box.createVerticalStrut(6));

        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BorderLayout(10, 0));
        speedPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        speedPanel.setBackground(new Color(250, 250, 250));

        speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 6, 3);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setLabelTable(speedSlider.createStandardLabels(1));
        speedSlider.setBackground(new Color(250, 250, 250));

        JLabel speedValueLabel = new JLabel("Normal");
        speedValueLabel.setFont(new Font("System", Font.PLAIN, 12));
        speedValueLabel.setForeground(new Color(128, 128, 128));
        speedValueLabel.setPreferredSize(new Dimension(80, 20));

        speedSlider.addChangeListener(e -> {
            String[] speeds = {"Very Slow", "Slow", "Normal", "Fast", "Very Fast", "Ultra Fast"};
            speedValueLabel.setText(speeds[speedSlider.getValue() - 1]);
        });

        speedPanel.add(speedSlider, BorderLayout.CENTER);
        speedPanel.add(speedValueLabel, BorderLayout.EAST);
        mainPanel.add(speedPanel);

        mainPanel.add(Box.createVerticalStrut(18));

        // Options
        JLabel optionsLabel = new JLabel("Options:");
        optionsLabel.setFont(new Font("System", Font.PLAIN, 13));
        optionsLabel.setForeground(new Color(0, 0, 0));
        mainPanel.add(optionsLabel);

        mainPanel.add(Box.createVerticalStrut(6));

        typoCheckBox = new JCheckBox("Include Typos");
        typoCheckBox.setSelected(true);
        typoCheckBox.setBackground(new Color(250, 250, 250));
        typoCheckBox.setFont(new Font("System", Font.PLAIN, 12));
        typoCheckBox.setForeground(new Color(0, 0, 0));
        mainPanel.add(typoCheckBox);

        deletionCheckBox = new JCheckBox("Include Random Deletions");
        deletionCheckBox.setSelected(true);
        deletionCheckBox.setBackground(new Color(250, 250, 250));
        deletionCheckBox.setFont(new Font("System", Font.PLAIN, 12));
        deletionCheckBox.setForeground(new Color(0, 0, 0));
        mainPanel.add(deletionCheckBox);

        mainPanel.add(Box.createVerticalStrut(20));

        // Countdown label
        countdownLabel = new JLabel("Ready to start");
        countdownLabel.setFont(new Font("System", Font.PLAIN, 12));
        countdownLabel.setForeground(new Color(128, 128, 128));
        countdownLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(countdownLabel);

        // Status label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("System", Font.PLAIN, 13));
        statusLabel.setForeground(new Color(76, 175, 80));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(statusLabel);

        mainPanel.add(Box.createVerticalStrut(20));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 0));
        buttonPanel.setBackground(new Color(250, 250, 250));

        startButton = new JButton("Start Typing");
        startButton.setFont(new Font("System", Font.BOLD, 13));
        startButton.setPreferredSize(new Dimension(130, 38));
        startButton.setBackground(new Color(0, 120, 215));
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.addActionListener(e -> startTyping());

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("System", Font.BOLD, 13));
        cancelButton.setPreferredSize(new Dimension(130, 38));
        cancelButton.setBackground(new Color(200, 200, 200));
        cancelButton.setForeground(new Color(0, 0, 0));
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setEnabled(false);
        cancelButton.addActionListener(e -> cancelTyping());

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("System", Font.BOLD, 13));
        resetButton.setPreferredSize(new Dimension(130, 38));
        resetButton.setBackground(new Color(100, 100, 100));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBorderPainted(false);
        resetButton.setFocusPainted(false);
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.addActionListener(e -> resetApp());

        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(resetButton);

        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    private void startTyping() {
        String text = inputTextArea.getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter text to type", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        isTyping = true;
        startButton.setEnabled(false);
        cancelButton.setEnabled(true);
        inputTextArea.setEnabled(false);
        speedSlider.setEnabled(false);
        typoCheckBox.setEnabled(false);
        deletionCheckBox.setEnabled(false);

        new Thread(() -> {
            try {
                // Countdown
                for (int i = 5; i > 0; i--) {
                    final int countdown = i;
                    SwingUtilities.invokeLater(() -> {
                        countdownLabel.setText("Starting in " + countdown + " seconds... Switch to target window!");
                        countdownLabel.setForeground(new Color(255, 152, 0));
                    });
                    Thread.sleep(1000);
                    if (!isTyping) return;
                }

                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Typing in progress...");
                    statusLabel.setForeground(new Color(76, 175, 80));
                    countdownLabel.setText("");
                });

                showFloatingStopButton();

                output(text);

                if (isTyping) {
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText("Typing completed!");
                        statusLabel.setForeground(new Color(76, 175, 80));
                        countdownLabel.setText("Ready to start");
                        countdownLabel.setForeground(new Color(100, 100, 100));
                    });
                }
            } catch (InterruptedException e) {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Typing cancelled");
                    statusLabel.setForeground(new Color(244, 67, 54));
                    countdownLabel.setText("Ready to start");
                    countdownLabel.setForeground(new Color(100, 100, 100));
                });
            } finally {
                boolean completedNaturally = isTyping;
                isTyping = false;
                SwingUtilities.invokeLater(() -> {
                    startButton.setEnabled(true);
                    cancelButton.setEnabled(false);
                    inputTextArea.setEnabled(true);
                    speedSlider.setEnabled(true);
                    typoCheckBox.setEnabled(true);
                    deletionCheckBox.setEnabled(true);
                    if (!completedNaturally && statusLabel.getText().isEmpty()) {
                        statusLabel.setText("Typing stopped");
                        statusLabel.setForeground(new Color(244, 67, 54));
                        countdownLabel.setText("Ready to start");
                        countdownLabel.setForeground(new Color(100, 100, 100));
                    }
                });
            }
        }).start();
    }

    private void showFloatingStopButton() {
        JFrame floatingWindow = new JFrame();
        floatingWindow.setUndecorated(true);
        floatingWindow.setAlwaysOnTop(true);
        floatingWindow.setSize(180, 90);
        floatingWindow.setLocationRelativeTo(null);
        floatingWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        floatingWindow.setBackground(new Color(250, 250, 250));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(250, 250, 250));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel label = new JLabel("Typing in Progress");
        label.setFont(new Font("System", Font.PLAIN, 12));
        label.setForeground(new Color(128, 128, 128));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton stopBtn = new JButton("Stop Typing");
        stopBtn.setFont(new Font("System", Font.BOLD, 12));
        stopBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopBtn.setMaximumSize(new Dimension(140, 36));
        stopBtn.setBackground(new Color(220, 50, 50));
        stopBtn.setForeground(Color.WHITE);
        stopBtn.setBorderPainted(false);
        stopBtn.setFocusPainted(false);
        stopBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stopBtn.addActionListener(e -> {
            isTyping = false;
            floatingWindow.dispose();
        });

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(stopBtn);

        floatingWindow.add(panel);
        floatingWindow.setVisible(true);

        // Auto-close when typing ends
        new Thread(() -> {
            while (isTyping) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
            SwingUtilities.invokeLater(() -> {
                if (floatingWindow.isVisible()) {
                    floatingWindow.dispose();
                }
            });
        }).start();
    }

    private void cancelTyping() {
        isTyping = false;
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText("Typing stopped");
            statusLabel.setForeground(new Color(244, 67, 54));
            countdownLabel.setText("Ready to start");
            countdownLabel.setForeground(new Color(100, 100, 100));
        });
    }

    private void resetApp() {
        isTyping = false;
        inputTextArea.setText("");
        inputTextArea.setEnabled(true);
        startButton.setEnabled(true);
        cancelButton.setEnabled(false);
        speedSlider.setEnabled(true);
        typoCheckBox.setEnabled(true);
        deletionCheckBox.setEnabled(true);
        statusLabel.setText("");
        countdownLabel.setText("Ready to start");
        countdownLabel.setForeground(new Color(100, 100, 100));
    }

    private void output(String text) throws InterruptedException {
        for (int i = 0; i < text.length() && isTyping; i++) {
            char ch = text.charAt(i);
            int random = (int) (Math.random() * 100);

            // Speed adjustment based on slider (1-6)
            int speedFactor = speedSlider.getValue();
            int baseDelay;
            switch (speedFactor) {
                case 1: baseDelay = 120; break;  // Very Slow
                case 2: baseDelay = 80;  break;  // Slow
                case 3: baseDelay = 40;  break;  // Normal
                case 4: baseDelay = 20;  break;  // Fast
                case 5: baseDelay = 10;  break;  // Very Fast
                case 6: baseDelay = 5;   break;  // Ultra Fast
                default: baseDelay = 40;
            }

            // Occasional long thinking pauses
            if (random >= LONG_BREAK_CHANCE) {
                sleep(LONG_BREAK_MS);
                i--; // Don't consume character
                continue;
            }

            // Occasional typos
            if (typoCheckBox.isSelected() && random >= TYPO_CHANCE && i < text.length() - 1) {
                type(robot, (char) (ch + 1));
                sleep(TYPO_DELAY_MS);
                robot.keyPress(KeyEvent.VK_BACK_SPACE);
                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                sleep(50);
                type(robot, ch);
                sleep(SLOW_DELAY_MS);
                continue;
            }

            // Type character based on speed
            int typingDelay;
            if (random < NORMAL_TYPING_CHANCE) {
                typingDelay = baseDelay + (int)(Math.random() * 20);
            } else if (random < SLOW_TYPING_CHANCE) {
                typingDelay = (int)(baseDelay * 2.5) + (int)(Math.random() * 30);
            } else {
                typingDelay = baseDelay + (int)(Math.random() * 20);
            }

            type(robot, ch);
            sleep(typingDelay);

            // Random deletion
            boolean isSpecialChar = ch == ' ' || ch == '\n' || ch == '\t' || ch == '.' || ch == ',' || ch == '!' || ch == '?' || ch == ';' || ch == ':';
            if (deletionCheckBox.isSelected() && !isSpecialChar && random >= TYPO_CHANCE - 20 && random < TYPO_CHANCE && i > MIN_DELETE_INDEX) {
                sleep(DELETE_DELAY_MS);
                robot.keyPress(KeyEvent.VK_BACK_SPACE);
                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                sleep(DELETE_DELAY_MS);
                type(robot, ch);
                sleep((int)(baseDelay * 1.5));
            }

            // Pause after punctuation
            if (ch == '.' || ch == '?' || ch == '!') {
                sleep(PUNCTUATION_PAUSE_MS);
            } else if (ch == ',' || ch == ';') {
                sleep(100);
            }
        }
    }

    private void sleep(long ms) throws InterruptedException {
        Thread.sleep(ms);
    }

    private void type(Robot robot, char ch) {
        if (ch == 'a') {
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
        } else if (ch == 'b') {
            robot.keyPress(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_B);
        } else if (ch == 'c') {
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
        } else if (ch == 'd') {
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
        } else if (ch == 'e') {
            robot.keyPress(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_E);
        } else if (ch == 'f') {
            robot.keyPress(KeyEvent.VK_F);
            robot.keyRelease(KeyEvent.VK_F);
        } else if (ch == 'g') {
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_G);
        } else if (ch == 'h') {
            robot.keyPress(KeyEvent.VK_H);
            robot.keyRelease(KeyEvent.VK_H);
        } else if (ch == 'i') {
            robot.keyPress(KeyEvent.VK_I);
            robot.keyRelease(KeyEvent.VK_I);
        } else if (ch == 'j') {
            robot.keyPress(KeyEvent.VK_J);
            robot.keyRelease(KeyEvent.VK_J);
        } else if (ch == 'k') {
            robot.keyPress(KeyEvent.VK_K);
            robot.keyRelease(KeyEvent.VK_K);
        } else if (ch == 'l') {
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
        } else if (ch == 'm') {
            robot.keyPress(KeyEvent.VK_M);
            robot.keyRelease(KeyEvent.VK_M);
        } else if (ch == 'n') {
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_N);
        } else if (ch == 'o') {
            robot.keyPress(KeyEvent.VK_O);
            robot.keyRelease(KeyEvent.VK_O);
        } else if (ch == 'p') {
            robot.keyPress(KeyEvent.VK_P);
            robot.keyRelease(KeyEvent.VK_P);
        } else if (ch == 'q') {
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
        } else if (ch == 'r') {
            robot.keyPress(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_R);
        } else if (ch == 's') {
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
        } else if (ch == 't') {
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_T);
        } else if (ch == 'u') {
            robot.keyPress(KeyEvent.VK_U);
            robot.keyRelease(KeyEvent.VK_U);
        } else if (ch == 'v') {
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
        } else if (ch == 'w') {
            robot.keyPress(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_W);
        } else if (ch == 'x') {
            robot.keyPress(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_X);
        } else if (ch == 'y') {
            robot.keyPress(KeyEvent.VK_Y);
            robot.keyRelease(KeyEvent.VK_Y);
        } else if (ch == 'z') {
            robot.keyPress(KeyEvent.VK_Z);
            robot.keyRelease(KeyEvent.VK_Z);
        } else if (ch == 'A') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'B') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'C') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'D') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'E') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'F') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_F);
            robot.keyRelease(KeyEvent.VK_F);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'G') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'H') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_H);
            robot.keyRelease(KeyEvent.VK_H);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'I') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_I);
            robot.keyRelease(KeyEvent.VK_I);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'J') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_J);
            robot.keyRelease(KeyEvent.VK_J);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'K') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_K);
            robot.keyRelease(KeyEvent.VK_K);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'L') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'M') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_M);
            robot.keyRelease(KeyEvent.VK_M);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'N') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'O') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_O);
            robot.keyRelease(KeyEvent.VK_O);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'P') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_P);
            robot.keyRelease(KeyEvent.VK_P);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'Q') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'R') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'S') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'T') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'U') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_U);
            robot.keyRelease(KeyEvent.VK_U);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'V') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'W') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'X') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'Y') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_Y);
            robot.keyRelease(KeyEvent.VK_Y);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'Z') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_Z);
            robot.keyRelease(KeyEvent.VK_Z);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '0') {
            robot.keyPress(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_0);
        } else if (ch == '1') {
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_1);
        } else if (ch == '2') {
            robot.keyPress(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_2);
        } else if (ch == '3') {
            robot.keyPress(KeyEvent.VK_3);
            robot.keyRelease(KeyEvent.VK_3);
        } else if (ch == '4') {
            robot.keyPress(KeyEvent.VK_4);
            robot.keyRelease(KeyEvent.VK_4);
        } else if (ch == '5') {
            robot.keyPress(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_5);
        } else if (ch == '6') {
            robot.keyPress(KeyEvent.VK_6);
            robot.keyRelease(KeyEvent.VK_6);
        } else if (ch == '7') {
            robot.keyPress(KeyEvent.VK_7);
            robot.keyRelease(KeyEvent.VK_7);
        } else if (ch == '8') {
            robot.keyPress(KeyEvent.VK_8);
            robot.keyRelease(KeyEvent.VK_8);
        } else if (ch == '9') {
            robot.keyPress(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_9);
        } else if (ch == '.') {
            robot.keyPress(KeyEvent.VK_PERIOD);
            robot.keyRelease(KeyEvent.VK_PERIOD);
        } else if (ch == ',') {
            robot.keyPress(KeyEvent.VK_COMMA);
            robot.keyRelease(KeyEvent.VK_COMMA);
        } else if (ch == '!') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '?') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_SLASH);
            robot.keyRelease(KeyEvent.VK_SLASH);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '-') {
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
        } else if (ch == '_') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '=') {
            robot.keyPress(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_EQUALS);
        } else if (ch == '+') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == ';') {
            robot.keyPress(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SEMICOLON);
        } else if (ch == ':') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '\'') {
            robot.keyPress(KeyEvent.VK_QUOTE);
            robot.keyRelease(KeyEvent.VK_QUOTE);
        } else if (ch == '\"') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_QUOTE);
            robot.keyRelease(KeyEvent.VK_QUOTE);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '(') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == ')') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '[') {
            robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
            robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
        } else if (ch == ']') {
            robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
        } else if (ch == '{') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
            robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '}') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '\\') {
            robot.keyPress(KeyEvent.VK_BACK_SLASH);
            robot.keyRelease(KeyEvent.VK_BACK_SLASH);
        } else if (ch == '|') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_BACK_SLASH);
            robot.keyRelease(KeyEvent.VK_BACK_SLASH);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '/') {
            robot.keyPress(KeyEvent.VK_SLASH);
            robot.keyRelease(KeyEvent.VK_SLASH);
        } else if (ch == '<') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_COMMA);
            robot.keyRelease(KeyEvent.VK_COMMA);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '>') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_PERIOD);
            robot.keyRelease(KeyEvent.VK_PERIOD);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == ' ') {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
        } else if (ch == '\n') {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } else if (ch == '\t') {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoboTypeGUI());
    }
}