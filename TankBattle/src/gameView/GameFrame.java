package gameView;

import gameControl.*;
import gameModel.*;


import javax.swing.*;

public class GameFrame {
    public static void main(String[] args) {
        // 创建游戏对象
        Game game = new Game();

        // 创建控制器
        GameController gameController = new GameController(game);

        // 创建游戏面板
        GamePanel gamePanel = new GamePanel(game);

        // 创建并设置 JFrame
        JFrame frame = new JFrame("坦克对战");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(gamePanel);
        frame.addKeyListener(gameController);  // 注册键盘监听器
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 游戏循环
        while (!game.isGameOver()) {
            game.update();  // 更新游戏逻辑
            gamePanel.repaint();  // 刷新界面
            try {
                Thread.sleep(16); // 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
