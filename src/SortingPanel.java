import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class SortingPanel extends JPanel {
    public static int SIZE = 300;
    public static float BAR_WIDTH = (float)(1200 - SIZE*2)/SIZE;
    public static int[] array = new int[SIZE];
    public static int current_index, traversing_index, min, comp1, comp2, start, end, key;

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        //resetArray();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setFont(new Font("Hack", Font.BOLD, 12));
        Rectangle2D.Float bar;
        for (int i = 0; i < SIZE ; i++) {
            g.setColor(Color.BLACK);
            bar = new Rectangle2D.Float(i*BAR_WIDTH + 2*i, 0, BAR_WIDTH, array[i]);
            g2d.fill(bar);
            if (SIZE <= 40) {
                g2d.setColor(Color.WHITE);
                g2d.drawString(String.valueOf(array[i]), (i * BAR_WIDTH + 2*i) + BAR_WIDTH/4, 20);
            }
        }

        if (MyFrame.sorting) {
            if (MyFrame.selectedAlgorithm == 0) {
                g2d.setColor(Color.RED);
                bar = new Rectangle2D.Float(current_index * BAR_WIDTH + 2 * current_index, 0, BAR_WIDTH, array[current_index]);
                g2d.fill(bar);

                g2d.setColor(Color.MAGENTA);
                bar = new Rectangle2D.Float(traversing_index * BAR_WIDTH + 2 * traversing_index, 0, BAR_WIDTH, array[traversing_index]);
                g2d.fill(bar);
            }

            if (MyFrame.selectedAlgorithm == 1) {
                g2d.setColor(Color.RED);
                bar = new Rectangle2D.Float(current_index * BAR_WIDTH + 2 * current_index, 0, BAR_WIDTH, array[current_index]);
                g2d.fill(bar);

                g2d.setColor(Color.MAGENTA);
                bar = new Rectangle2D.Float(traversing_index * BAR_WIDTH + 2 * traversing_index, 0, BAR_WIDTH, array[traversing_index]);
                g2d.fill(bar);

                g2d.setColor(Color.BLUE);
                bar = new Rectangle2D.Float(min * BAR_WIDTH + 2 * min, 0, BAR_WIDTH, array[min]);
                g2d.fill(bar);
            }

            if (MyFrame.selectedAlgorithm == 2) {
                g2d.setColor(Color.ORANGE);
                bar = new Rectangle2D.Float(comp1 * BAR_WIDTH + 2 * comp1, 0, BAR_WIDTH, array[comp1]);
                g2d.fill(bar);

                g2d.setColor(Color.ORANGE);
                bar = new Rectangle2D.Float(comp2 * BAR_WIDTH + 2 * comp2, 0, BAR_WIDTH, array[comp2]);
                g2d.fill(bar);
            }

            if (MyFrame.selectedAlgorithm == 3) {
                g2d.setColor(Color.BLUE);
                bar = new Rectangle2D.Float(key * BAR_WIDTH + 2 * key, 0, BAR_WIDTH, array[key]);
                g2d.fill(bar);

                g2d.setColor(Color.RED);
                bar = new Rectangle2D.Float(start * BAR_WIDTH + 2 * start, 0, BAR_WIDTH, array[start]);
                g2d.fill(bar);

                g2d.setColor(Color.YELLOW);
                bar = new Rectangle2D.Float(end * BAR_WIDTH + 2 * end, 0, BAR_WIDTH, array[end]);
                g2d.fill(bar);
            }
        }
    }

}
