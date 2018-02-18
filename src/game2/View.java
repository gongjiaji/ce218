package game2;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class View extends JComponent {
    // background color
    public static final Color BG_COLOR = Color.black;
    private Game game;
    Image im = Constants.MILKYWAY1;
    AffineTransform bgTransf;

    public View(Game game) {
        this.game = game;
        double imWidth = im.getWidth(null);
        double imHeight = im.getHeight(null);
        double stretch_x = (imWidth > Constants.FRAME_WIDTH ? 1 : Constants.FRAME_WIDTH / imWidth);
        double stretch_y = (imHeight > Constants.FRAME_HEIGHT ? 1 : Constants.FRAME_HEIGHT / imHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretch_x, stretch_y);
    }

    @Override
    public void paintComponent(Graphics g0) {
        if (Game.over) {
            Graphics2D g = (Graphics2D) g0;
            g.setColor(BG_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.white);

            g.setColor(Color.red);
            g.setFont(new Font("Monaca", Font.BOLD, 50));
            g.drawString(Game.over(), 0, getHeight() / 2);

            g.setColor(Color.white);
            g.setFont(new Font("Monaca", Font.ITALIC, 40));
            g.drawString("Press 'R' to restart", getWidth() / 2 - 180, getHeight() / 2 + 100);
        } else {
            Graphics2D g = (Graphics2D) g0;
            g.drawImage(im, bgTransf, null);
//            g.setColor(BG_COLOR);
//            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.white);
            g.drawString("Score: " + Game.score, 20, 20);
            // rare situation
            if (Game.life < 0) {
                Game.life = 0;
            }
            g.drawString("Life: " + Game.life, 100, 20);
            g.drawString("Level: " + Game.level, 180, 20);

            synchronized (Game.class) {
                for (GameObject object : Game.objects) {
                    object.draw(g);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}
