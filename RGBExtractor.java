
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RGBExtractor extends Component {

    JPanel panel = new JPanel(new BorderLayout(10, 10));

    class MyCanvasm extends JComponent {

        @Override
        public void paint(Graphics g) {
            Map.Entry<Integer, Integer> maxEntry = null;
            for (Map.Entry<Integer, Integer> entry : greenMap.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }
            int q = 1;
            g.setColor(Color.GREEN);
            for (Map.Entry<Integer, Integer> entry : greenMap.entrySet()) {

                g.fillRect(q * 3, (450 - entry.getValue() / 10), 1, entry.getValue() / 10);
                q++;
            }
        }
    }

    class MyCanvast extends JComponent {

        @Override
        public void paint(Graphics g) {
            Map.Entry<Integer, Integer> maxEntry = null;
            for (Map.Entry<Integer, Integer> entry : blueMap.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }
            int q = 1;
            g.setColor(Color.BLUE);
            for (Map.Entry<Integer, Integer> entry : blueMap.entrySet()) {
                g.fillRect(q * 3, (450 - entry.getValue() / 10), 1, entry.getValue() / 10);
                q++;
            }

        }
    }

    class MyCanvas extends JComponent {

        @Override
        public void paint(Graphics g) {
            Map.Entry<Integer, Integer> maxEntry = null;
            for (Map.Entry<Integer, Integer> entry : redMap.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }

            //   g.drawRect(10, 10, 200, 200);
            int q = 1;
            g.setColor(Color.RED);
            /*   Graphics2D g2 = (Graphics2D) g;
Font font = new Font(null, Font.PLAIN, 10);
AffineTransform affineTransform = new AffineTransform();
affineTransform.rotate(Math.toRadians(90), 0, 0);
Font rotatedFont = font.deriveFont(affineTransform);
g2.setFont(rotatedFont);
             g2.drawString(String.valueOf(q),q*10,0);*/

            for (Map.Entry<Integer, Integer> entry : redMap.entrySet()) {
                //   g.fillRect(2, 12, 10, 12);
                //  g.fillRect(128, 128, 128, 128);

                g.fillRect(q * 3, (450 - entry.getValue() / 10), 1, entry.getValue() / 10);

                //    g.drawString(String.valueOf(q), q*10,470) ;
                //  g.fillRect(q * 3, 1, 1, entry.getValue() / 10);
                q++;
                //System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
            }
            // q=0;
            /*  for (Map.Entry<Integer, Integer> entry : blueMap.entrySet()) {
             //   g.fillRect(2, 12, 10, 12);
             //   g.fillRect(15, 12, 13, 12);

                g.fillRect(q*3, 10, 1,entry.getValue()/10);

            }*/
            //  g.fillRect(1, 2, 10, 12);
            //  g.fillRect(1, 15, 10, 11);
            //punctul de sus ...punctul din dreapta...grosimea ...lungimea
        }
    }
    private HashMap<Integer, Integer> redMap = new HashMap<>();
    private HashMap<Integer, Integer> blueMap = new HashMap<>();
    private HashMap<Integer, Integer> greenMap = new HashMap<>();

    public static void main(String[] foo) {
        new RGBExtractor();
    }

    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        if (redMap.containsKey(red)) {
            redMap.put(red, redMap.get(red) + 1);
        } else {
            redMap.put(red, 1);
        }
        if (blueMap.containsKey(blue)) {
            blueMap.put(blue, blueMap.get(blue) + 1);
        } else {
            blueMap.put(blue, 1);
        }
        if (greenMap.containsKey(green)) {
            greenMap.put(green, greenMap.get(green) + 1);
        } else {
            greenMap.put(green, 1);
        }
        // System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }

    private void marchThroughImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        //  System.out.println("width, height: " + w + ", " + h);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                //  System.out.println("x,y: " + j + ", " + i);
                int pixel = image.getRGB(j, i);
                printPixelARGB(pixel);
                //    System.out.println("");
            }
        }
    }

    public RGBExtractor() {
        try {
            String path = "Image1.png";
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            marchThroughImage(image);

            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBounds(30, 30, 900, 500);
            window.getContentPane().add(new MyCanvas());
            window.setVisible(true);
            window.setResizable(false);
            JFrame windo = new JFrame();
            windo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windo.setBounds(30, 30, 900, 500);
            windo.getContentPane().add(new MyCanvast());
            windo.setVisible(true);
            windo.setResizable(false);
            JFrame wind = new JFrame();
            wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            wind.setBounds(30, 30, 900, 500);
            wind.getContentPane().add(new MyCanvasm());
            wind.setVisible(true);
            wind.setResizable(false);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

}
