package gameView;

import javax.swing.*;

import gameModel.*;

import java.awt.*;

public class GamePanel extends JPanel{
    private Game game ;

    public GamePanel(Game game){
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 使用Game的draw方法来绘制所有内容
        Graphics2D g2D = (Graphics2D) g;
        game.draw(g2D);
    }
}
