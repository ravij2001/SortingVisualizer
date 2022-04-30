import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.SwitchPoint;
import java.util.Random;

public class MyFrame extends JFrame implements ActionListener, ChangeListener {
    public static int frame_width = 1200;
    public static int frame_height = 750;
    public static boolean sorting = false;
    public static int selectedAlgorithm = 0;
    public int speed = 1;
    JTable infoTable;
    JButton resetButton, shuffleButton, sortButton;
    SortingPanel sortingPanel;
    JComboBox algorithms;
    JSlider sizeSlider, speedSlider;
    JLabel sizeLabel, speedLabel, sortLabal, shuffleLabel, newLabel;
    JLabel algoLabel, Cases, values;
    public SwingWorker<Void, Void> sorter, shuffler;
    /*int SIZE = 100;
    int BAR_WIDTH = 8;
    public int[] array = new int[SIZE];*/
    MyFrame() {

        /*JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setBounds(0, 0, 1200, 100);*/

        sortingPanel = new SortingPanel();
        sortingPanel.setBackground(new Color(123, 125, 125));
        sortingPanel.setBounds(0, 100, frame_width, 650);

        ImageIcon newIcon = new ImageIcon("C:\\Users\\Lucifer\\IdeaProjects\\SortingFromScrap\\src\\reload.png");
        Image newArrayimage = newIcon.getImage();
        Image newImage = newArrayimage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newArrayIcon = new ImageIcon(newImage);
        resetButton = new JButton(newArrayIcon);
        resetButton.setBounds(50, 25, 50, 50);
        //resetButton.setText("New Array");
        resetButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        resetButton.setBackground(new Color(123, 125, 125));
        resetButton.setBorder(new LineBorder(Color.BLACK, 2));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        newLabel = new JLabel();
        newLabel.setText("New Array");
        newLabel.setBounds(46, 80, 70, 15);

        ImageIcon shuffleIcon = new ImageIcon("C:\\Users\\Lucifer\\IdeaProjects\\SortingFromScrap\\src\\shuffle.png");
        Image shuffleImage = shuffleIcon.getImage();
        Image newShuffleImage = shuffleImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newShuffleIcon = new ImageIcon(newShuffleImage);
        shuffleButton = new JButton(newShuffleIcon);
        shuffleButton.setBounds(130, 25, 50, 50);
        //shuffleButton.setText("Shuffle");
        shuffleButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        shuffleButton.setBackground(new Color(123, 125, 125));
        shuffleButton.setFocusable(false);
        shuffleButton.setBorder(new LineBorder(Color.BLACK, 2));
        shuffleButton.addActionListener(this);
        shuffleLabel = new JLabel();
        shuffleLabel.setText("Shuffle");
        shuffleLabel.setBounds(133, 80, 70, 15);

        String[] algos = {"Insertion Sort", "Selection Sort", "Bubble Sort", "Quick Sort", "Merge Sort", "Heap Sort", "Radix Sort"};
        algorithms = new JComboBox(algos);
        algorithms.setBounds(210, 25, 120, 50);
        algorithms.setBackground(new Color(123, 125, 125));
        algorithms.setFocusable(false);
        algorithms.setBorder(new BevelBorder(BevelBorder.RAISED));
        algorithms.addActionListener(this);

        ImageIcon sortIcon = new ImageIcon("C:\\Users\\Lucifer\\IdeaProjects\\SortingFromScrap\\src\\sort.png");
        Image sortImage = sortIcon.getImage();
        Image newSortImage = sortImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newSortIcon = new ImageIcon(newSortImage);
        sortButton = new JButton(newSortIcon);
        sortButton.setBounds(380, 25, 50, 50);
        //sortButton.setText("Sort !");
        sortButton.setBackground(new Color(123, 125, 125));
        sortButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        sortButton.setBorder(new LineBorder(Color.BLACK, 2));
        sortButton.setFocusable(false);
        sortButton.addActionListener(this);
        sortLabal = new JLabel();
        sortLabal.setText("Sort !");
        sortLabal.setBounds(390, 80, 60, 15);

        sizeSlider = new JSlider(20, 300,  SortingPanel.SIZE);
        sizeSlider.setBounds(470, 45, 150, 40);
        sizeSlider.setMajorTickSpacing(60);
        sizeSlider.setMinorTickSpacing(20);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setBackground(new Color(123, 125, 125));
        sizeSlider.addChangeListener(this);

        speedSlider = new JSlider(1, 10, 1);
        speedSlider.setBounds(650, 42, 150, 20);
        speedSlider.setInverted(true);
        speedSlider.setBackground(new Color(123, 125, 125));
        speedSlider.addChangeListener(this);

        sizeLabel = new JLabel();
        sizeLabel.setText("Size = " + sizeSlider.getValue());
        sizeLabel.setBounds(520, 20, 70, 20);

        speedLabel = new JLabel();
        speedLabel.setText("Delay = " + (speedSlider.getValue()) + " ms");
        speedLabel.setBounds(690, 20, 80, 20);

        algoLabel = new JLabel();
        algoLabel.setText(algos[algorithms.getSelectedIndex()]);
        algoLabel.setBounds(920, 5, 200, 30);
        algoLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));

        Cases = new JLabel();
        Cases.setText("Best             Average             Worst");
        Cases.setBounds(870, 40, 250, 20);
        Cases.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        values = new JLabel();
        values.setText("O(n)               O(n^2)                O(n^2)");
        values.setBounds(865, 70, 300, 20);
        values.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Lucifer\\IdeaProjects\\SortingFromScrap\\src\\sort.png");

        setTitle("Sorting Visualizer");
        setIconImage(imageIcon.getImage());
        setSize(1213, frame_height);
        getContentPane().setBackground(new Color(123, 125, 125));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        add(sortingPanel);
        add(resetButton);
        add(newLabel);
        add(shuffleButton);
        add(shuffleLabel);
        add(algorithms);
        add(sortButton);
        add(sortLabal);
        add(sizeSlider);
        add(sizeLabel);
        add(speedSlider);
        add(speedLabel);
        add(algoLabel);
        add(Cases);
        add(values);
        resetArray();
    }

    public void resetArray() {
        for (int i = 0; i < SortingPanel.SIZE; i++) {
            SortingPanel.array[i] = (int) Math.floor(Math.random()*(600-40+1)+40);
        }
    }

    public void enableMenu () {
        sorting = false;
        resetButton.setEnabled(true);
        shuffleButton.setEnabled(true);
        sortButton.setEnabled(true);
        algorithms.setEnabled(true);
        sizeSlider.setEnabled(true);
    }

    public void disableMenu () {
        resetButton.setEnabled(false);
        shuffleButton.setEnabled(false);
        sortButton.setEnabled(false);
        algorithms.setEnabled(false);
        sizeSlider.setEnabled(false);
        sorting = true;
    }

    private void swap(int indexA, int indexB) {
        int temp = SortingPanel.array[indexA];
        SortingPanel.array[indexA] = SortingPanel.array[indexB];
        SortingPanel.array[indexB] = temp;
    }

    private void shuffleArray() {
        shuffler = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                int middle = SortingPanel.SIZE / 2;
                for (int i = 0, j = middle; i < middle; i++, j++) {
                    int random_index = new Random().nextInt(SortingPanel.SIZE);
                    swap(i, random_index);

                    random_index = new Random().nextInt(SortingPanel.SIZE);
                    swap(j, random_index);

                    Thread.sleep(10);
                    repaint();
                }
                return null;
            }
        };
        shuffler.execute();
    }

    public void sortArray () throws InterruptedException {
        switch (algorithms.getSelectedIndex()) {
            case 0 :
                selectedAlgorithm = 0;
                InsertionSort();
                break;
            case 1 :
                selectedAlgorithm = 1;
                SelectionSort();
                break;
            case 2 :
                selectedAlgorithm = 2;
                BubbleSort();
                break;
            case 3:
                selectedAlgorithm = 3;
                QuickSort();
                break;
            case 4:
                selectedAlgorithm = 4;
                MergeSort();
                break;
            case 5:
                selectedAlgorithm = 5;
                HeapSort();
                break;
            case 6:
                selectedAlgorithm = 6;
                RadixSort();
                break;
        }
    }

    public int getMax () {
        int max = -1;
        for (int i = 0; i< SortingPanel.SIZE; i++) {
            if(SortingPanel.array[i] > max) {
                max = SortingPanel.array[i];
            }
        }
        return max;
    }

    private void RadixSort() {
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int n = SortingPanel.SIZE;
                int pos;
                int max = getMax();
                for (pos = 1; max / pos > 0; pos = pos * 10) {
                    countSort(n, pos);
                }

                enableMenu();
                return null;
            }
        };
        sorter.execute();
    }

    public void countSort (int n, int pos) throws InterruptedException{
        int count[] = new int[10];
        int temp[] = new int[n];
        for (int i = 0; i<n; i++) {
            count[(SortingPanel.array[i]/pos) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] = count[i] + count[i-1];
        }

        for (int i = n-1; i>=0; i--) {
            temp[--count[(SortingPanel.array[i]/pos)%10]] = SortingPanel.array[i];
        }

        for (int i= 0; i< n; i++) {
            SortingPanel.array[i] = temp[i];
            Thread.sleep(speed*5);
            repaint();
        }
    }

    private void HeapSort() {
        sorter = new SwingWorker<> () {
            @Override
            protected Void doInBackground() throws Exception {
                heapSort();

                enableMenu();
                return null;
            }
        };
        sorter.execute();
    }

    private void QuickSort() {
        sorter = new SwingWorker<> () {
            @Override
            protected Void doInBackground() throws Exception {
                int lb, ub;
                lb = 0;
                ub = SortingPanel.SIZE - 1;
                quick_sort(lb, ub);

                SortingPanel.key = 0;
                SortingPanel.start = 0;
                SortingPanel.end = 0;

                enableMenu();
                return null;
            }
        };
        sorter.execute();
    }

    private void MergeSort () {
        sorter = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int lb = 0;
                int ub = SortingPanel.SIZE - 1;

                mergeSort(lb, ub);

                SortingPanel.traversing_index = 0;

                enableMenu();
                return null;
            }
        };
        sorter.execute();
    }

    private void mergeSort(int start, int end) {
        if(start == end) return;

        // add one since the last element is inclusive
        int lookup_length = end - start + 1;
        // start serves as offset
        int middle = ((lookup_length) / 2) + start;

        // subtract 1 to make middle exclusive on left and inclusive on right
        mergeSort(start, middle - 1);
        mergeSort(middle, end);

        //current_index = start;
        merge(start, middle, end);
    }

    private void merge(int start, int middle, int end) {
        int lookup_length = end - start + 1;
        int[] temp = new int[lookup_length];

        for(int i = 0; i < lookup_length; i++)
            temp[i] = SortingPanel.array[start + i];


        int temp_middle = middle - start;
        int a_ptr = start;
        int l_ptr = 0;
        int r_ptr = temp_middle;

        while(l_ptr < temp_middle && r_ptr < temp.length) {


            if(temp[l_ptr] < temp[r_ptr])
                SortingPanel.array[a_ptr] = temp[l_ptr++];
            else
                SortingPanel.array[a_ptr] = temp[r_ptr++];

            try {
                a_ptr++;
                repaint();
                Thread.sleep(speed);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        while(l_ptr < temp_middle) {

            try {
                repaint();
                Thread.sleep(speed*5);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            SortingPanel.array[a_ptr++] = temp[l_ptr++];
        }

        while(r_ptr < temp.length) {

            try {
                //selected_index	= r_ptr + start;
                repaint();
                Thread.sleep(speed*5);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            SortingPanel.array[a_ptr++] = temp[r_ptr++];
        }
    }

    public void heapSort() throws InterruptedException {
        int n = SortingPanel.SIZE;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            swap(0, i);
            repaint();
            Thread.sleep(speed*5);

            // call max heapify on the reduced heap
            heapify(i, 0);
        }
    }

    public void heapify(int n, int i) throws InterruptedException {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        while (l < n && SortingPanel.array[l] > SortingPanel.array[largest])
            largest = l;

        while (r < n && SortingPanel.array[r] > SortingPanel.array[largest])
            largest = r;

        if (largest != i) {
            swap(i, largest);
            Thread.sleep(speed*5);
            repaint();

            heapify(n, largest);
        }
    }

    private void InsertionSort() {
        sorter = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                for (SortingPanel.current_index = 1; SortingPanel.current_index < SortingPanel.SIZE; SortingPanel.current_index++) {
                    SortingPanel.traversing_index = SortingPanel.current_index;
                    while (SortingPanel.traversing_index > 0) {
                        if (SortingPanel.array[SortingPanel.traversing_index] < SortingPanel.array[SortingPanel.traversing_index - 1]) {
                            swap(SortingPanel.traversing_index, SortingPanel.traversing_index - 1);
                        }
                        SortingPanel.traversing_index--;
                        Thread.sleep(speed);
                        repaint();
                    }
                }
                SortingPanel.current_index = 0;
                SortingPanel.traversing_index = 0;

                enableMenu();

                return null;
            }
        };
        sorter.execute();
    }

    public void SelectionSort () {
        sorter = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                for (SortingPanel.current_index = 0; SortingPanel.current_index < SortingPanel.SIZE - 1; SortingPanel.current_index++) {
                    SortingPanel.min = SortingPanel.current_index;
                    SortingPanel.traversing_index = SortingPanel.current_index + 1;
                    for (SortingPanel.traversing_index = SortingPanel.current_index + 1; SortingPanel.traversing_index < SortingPanel.SIZE; SortingPanel.traversing_index++) {
                        if(SortingPanel.array[SortingPanel.traversing_index] < SortingPanel.array[SortingPanel.min]) {
                            SortingPanel.min = SortingPanel.traversing_index;
                        }
                        Thread.sleep(speed);
                        repaint();
                    }
                    if(SortingPanel.min!=SortingPanel.current_index) {
                        swap(SortingPanel.min, SortingPanel.current_index);
                    }
                }
                SortingPanel.current_index = 0;
                SortingPanel.traversing_index = 0;
                SortingPanel.min = 0;

                enableMenu();

                return null;
            }
        };
        sorter.execute();
    }

    public void BubbleSort () {
        sorter = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                for (int j = 0; j < SortingPanel.SIZE - 1; j++) {
                    for (int i = 0; i < SortingPanel.SIZE - 1; i++) {
                        SortingPanel.comp1 = i;
                        SortingPanel.comp2 = i + 1;
                        if (SortingPanel.array[i] > SortingPanel.array[i + 1]) {
                            Thread.sleep(speed);
                            repaint();
                            swap(i, i + 1);
                        }
                        Thread.sleep(speed);
                        repaint();
                    }
                }
                SortingPanel.comp1 = 0;
                SortingPanel.comp2 = 0;

                enableMenu();

                return null;
            }
        };
        sorter.execute();
    }

    public void quick_sort(int lb, int ub) throws InterruptedException {
        int location;
        if(lb < ub) {
            location = divide(lb, ub);
            quick_sort(lb, location - 1);
            quick_sort(location + 1, ub);
        }
    }

    public int divide(int lb, int ub) throws InterruptedException {
        float pivot;
        SortingPanel.key = lb;
        pivot = SortingPanel.array[lb];
        SortingPanel.start = lb;
        SortingPanel.end = ub;
        while (SortingPanel.start < SortingPanel.end) {
            while (SortingPanel.array[SortingPanel.start] <= pivot && SortingPanel.start + 1 < SortingPanel.SIZE) {
                SortingPanel.start++;
                Thread.sleep(speed*5);
                repaint();
            }
            while (SortingPanel.array[SortingPanel.end] > pivot) {
                SortingPanel.end--;
                Thread.sleep(speed*5);
                repaint();
            }
            if (SortingPanel.start < SortingPanel.end) {
                swap(SortingPanel.start, SortingPanel.end);
                Thread.sleep(speed*5);
                repaint();
            }
        }
        swap(lb, SortingPanel.end);
        Thread.sleep(speed*5);
        repaint();

        return SortingPanel.end;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetArray();
            repaint();
        }
        if (e.getSource() == shuffleButton) {
            shuffleArray();
            repaint();
        }
        if (e.getSource() == sortButton) {
            try {
                disableMenu();
                sortArray();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (e.getSource() == algorithms) {
            String[] algos = {"Insertion Sort", "Selection Sort", "Bubble Sort", "Quick Sort", "Merge Sort", "Heap Sort", "Radix Sort"};
            String[] comps = {"O(n)               O(n^2)                O(n^2)","O(n^2)               O(n^2)                O(n^2)",
                    "O(n)               O(n^2)                O(n^2)", "O(nlogn)            O(nlogn)            O(n^2)"
                ,"O(nlogn)           O(nlogn)             O(nlogn)", "O(nlogn)          O(nlogn)             O(nlogn)",
                    "O(nk)               O(nk)                O(nk)"};
            algoLabel.setText(algos[algorithms.getSelectedIndex()]);
            values.setText(comps[algorithms.getSelectedIndex()]);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == sizeSlider) {
            sizeLabel.setText("Size = " + sizeSlider.getValue());
            SortingPanel.SIZE = sizeSlider.getValue();
            SortingPanel.BAR_WIDTH = (float)(1200 - SortingPanel.SIZE * 2) / SortingPanel.SIZE;
            resetArray();
            repaint();
        }
        if (e.getSource() == speedSlider) {
            speedLabel.setText("Delay = " + (speedSlider.getValue()) + " ms");
            speed = speedSlider.getValue();
        }
    }
}
