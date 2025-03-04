package gameControl;
import gameModel.*;
import objects.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import static objects.GameConstants.TANK_SPEED;

//WASD键控制玩家1移动 空格键控制子弹发射
//上下左右箭头控制玩家2移动 Enter键控制子弹发射
public class GameController extends KeyAdapter {
    private Game game;
    private HashMap<Integer,Boolean> player1Keys =new HashMap<>();
    private HashMap<Integer,Boolean> player2Keys =new HashMap<>();

    public GameController(Game game) {
        this.game = game;
        keyBinding();
    }

    //按键绑定
    private void keyBinding(){
        player1Keys.put(KeyEvent.VK_W,false);
        player1Keys.put(KeyEvent.VK_A,false);
        player1Keys.put(KeyEvent.VK_S,false);
        player1Keys.put(KeyEvent.VK_D,false);
        player2Keys.put(KeyEvent.VK_UP,false);
        player2Keys.put(KeyEvent.VK_DOWN,false);
        player2Keys.put(KeyEvent.VK_LEFT,false);
        player2Keys.put(KeyEvent.VK_RIGHT,false);
    }

    public void updateVelocity(Tank tank){
        HashMap<Integer, Boolean> keys;
        if (tank == game.getPlayer1()) {
            keys = player1Keys;
        } else if (tank == game.getPlayer2()) {
            keys = player2Keys;
        } else {
            return;
        }

        int dx = 0;
        int dy = 0;

        if (tank == game.getPlayer1()) {
            // 处理玩家1的WASD键
            if (keys.get(KeyEvent.VK_W)) dy -= 1;
            if (keys.get(KeyEvent.VK_S)) dy += 1;
            if (keys.get(KeyEvent.VK_A)) dx -= 1;
            if (keys.get(KeyEvent.VK_D)) dx += 1;
        } else {
            // 处理玩家2的箭头键
            if (keys.get(KeyEvent.VK_UP)) dy -= 1;
            if (keys.get(KeyEvent.VK_DOWN)) dy += 1;
            if (keys.get(KeyEvent.VK_LEFT)) dx -= 1;
            if (keys.get(KeyEvent.VK_RIGHT)) dx += 1;
        }

        if (dx == 0 && dy == 0) {
            tank.setMoving(false);
        } else {
            tank.getVelocity().setVelocityX(dx*TANK_SPEED);
            tank.getVelocity().setVelocityY(dy*TANK_SPEED);
            tank.setMoving(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (player1Keys.containsKey(keyCode)) {
            player1Keys.replace(keyCode, true);
            updateVelocity(game.getPlayer1());
        } else if (player2Keys.containsKey(keyCode)) {
            player2Keys.replace(keyCode, true);
            updateVelocity(game.getPlayer2());
        }else if(keyCode == KeyEvent.VK_SPACE){
            game.fire(game.getPlayer1());
        }else if(keyCode == KeyEvent.VK_ENTER){
            game.fire(game.getPlayer2());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (player1Keys.containsKey(keyCode)) {
            player1Keys.replace(keyCode, false);
            updateVelocity(game.getPlayer1());
        } else if (player2Keys.containsKey(keyCode)) {
            player2Keys.replace(keyCode, false);
            updateVelocity(game.getPlayer2());
        }
    }
}
