package com.davidsgk.bullet360.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.davidsgk.bullet360.Bullet360;
import com.davidsgk.bullet360.TextureManager;
import com.davidsgk.bullet360.camera.OrthoCamera;

public class EntityManager {

    private final Array<Enemy> enemies = new Array<Enemy>();
    private final Array<Bullet> bullets = new Array<Bullet>();
    private final Player player;

    public EntityManager(int amount, OrthoCamera camera){
        player = new Player(new Vector2(Bullet360.WIDTH/2, Bullet360.HEIGHT/2), new Vector2(0, 0), this, camera);
        for(int i = 0; i < amount; i++){
            float x, y;
            if(MathUtils.random(0, 1) == 0){
                x = MathUtils.random(0, Bullet360.WIDTH - TextureManager.ENEMY.getWidth());
                if(MathUtils.random(0, 1) == 0){
                    y = MathUtils.random(Bullet360.HEIGHT, Bullet360.HEIGHT * 2);
                } else {
                    y = -MathUtils.random(TextureManager.ENEMY.getHeight(), Bullet360.HEIGHT);
                }
            } else {
                y = MathUtils.random(0, Bullet360.HEIGHT - TextureManager.ENEMY.getHeight());
                if(MathUtils.random(0, 1) == 0){
                    x = MathUtils.random(Bullet360.WIDTH, Bullet360.WIDTH * 2);
                } else {
                    x = -MathUtils.random(TextureManager.ENEMY.getWidth(), Bullet360.WIDTH);
                }
            }
            addEnemy(new Enemy(new Vector2(x, y), new Vector2(0, 0)));
        }
    }

    public void update(){
        for(Enemy e : enemies){
            e.track(player);
            e.update();
        }
        for(Bullet b : bullets){
            if(b.checkHorizontalEnd()){
                b.setPos(new Vector2(Bullet360.WIDTH - b.getPosition().x, b.getPosition().y));
            }
            if(b.checkVerticalEnd()){
                b.setPos(new Vector2(b.getPosition().x, Bullet360.HEIGHT - b.getPosition().y));
            }
            b.update();
        }
        player.update();
        checkCollisions();
    }

    public void render(SpriteBatch sb){
        for(Enemy e : enemies){
            e.render(sb);
        }
        for(Bullet b : bullets){
            b.render(sb);
        }
        player.render(sb);
    }

    private void checkCollisions(){
        for(Enemy e: enemies){
            for(Bullet b: bullets){
                /*if(e.getBounds().contains(b.getBounds())){
                    enemies.removeValue(e, false);
                    bullets.removeValue(b, false);
                }*/
                if(Intersector.overlaps(e.getBounds(), b.getBounds())){
                    enemies.removeValue(e, false);
                    bullets.removeValue(b, false);
                }
            }
        }
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public Player getPlayer(){
        return player;
    }

    public Array<Enemy> getEnemies(){
        return enemies;
    }

    public Array<Bullet> getBullets(){
        return bullets;
    }

    public boolean gameOver(){
        return getEnemies().size <= 0;
    }
}
