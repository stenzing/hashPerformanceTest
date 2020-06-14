package sg.test.listcompare;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static sg.test.listcompare.ComparatorLogic.generateList;

public class ListComparator extends JFrame {

    private JTextArea sizeA = new JTextArea("2");
    private JTextArea sizeB = new JTextArea("ds");
    private JComboBox<ComparatorLogic.Choice> listChoose = new JComboBox<>(ComparatorLogic.Choice.values());
    private JTextArea resultSize = new JTextArea();
    private JTextArea computationTime = new JTextArea();
    private final ComparatorLogic logic = new ComparatorLogic();

    ListComparator(){
        setLayout(new BorderLayout());
        JButton runButton=new JButton("Run");
        runButton.setPreferredSize(new Dimension(200, 100));
        runButton.addActionListener(e -> {
            int a = Integer.parseInt(sizeA.getText());
            int b = Integer.parseInt(sizeB.getText());
            int max = Math.max(a,b);
            List<Integer> listA = generateList(a, max);
            List<Integer> listB = generateList(b, max);

            try {
                long startTime = System.nanoTime();
                List<Integer> result = logic.compareList(listA, listB, (ComparatorLogic.Choice) listChoose.getSelectedItem());
                long runTime = System.nanoTime() - startTime;
                resultSize.setText(String.format("%d", result.size()));
                computationTime.setText(formatDuration(runTime)+" sec");
            } catch (Exception ex) {
                resultSize.setText(ex.getMessage());
            }
        });
        JPanel row1 = wrap(new JLabel("List Size A:"), sizeA);
        JPanel row2 = wrap(new JLabel("List Size B:"), sizeB);
        JPanel row3 = wrap(new JLabel("List to put into HashSet:"), listChoose);

        JPanel jPanelTop = new JPanel();
        jPanelTop.add(row1);
        jPanelTop.add(row2);
        jPanelTop.add(row3);
        jPanelTop.setLayout(new FlowLayout());
        jPanelTop.setPreferredSize(new Dimension(400,150));
        add(jPanelTop,BorderLayout.PAGE_START);
        add(runButton,BorderLayout.CENTER);
        JPanel jPanelBottom = new JPanel();
        jPanelBottom.setLayout(new FlowLayout());
        jPanelBottom.setPreferredSize(new Dimension(400,150));
        jPanelBottom.add(wrap(new JLabel("Result size:"), resultSize));
        jPanelBottom.add(wrap(new JLabel("Computation time:"), computationTime));
        add(jPanelBottom,BorderLayout.PAGE_END);
        setSize(400,500);
        setVisible(true);
    }

    private static String formatDuration(long l) {
        long secPart = TimeUnit.NANOSECONDS.toSeconds(l);
        long nanoPart =l - TimeUnit.SECONDS.toNanos(secPart);
        return String.format("%d.%09d", secPart, nanoPart);
    }

    private static JPanel  wrap(JComponent ...components) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(360,24));
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        for (JComponent component : components) {
            panel.add(component);
        }
        return panel;
    }

    public static void main(String[] args) {
        new ListComparator();
    }
}
