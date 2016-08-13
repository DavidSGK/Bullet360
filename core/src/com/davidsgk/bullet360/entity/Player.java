package com.davidsgk.bullet360.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.davidsgk.bullet360.Bullet360;
import com.davidsgk.bullet360.TextureManager;
import com.davidsgk.bullet360.camera.OrthoCamera;

public class Player extends Entity {

    private final EntityManager entityManager;
    private final OrthoCamera camera;
    private long reload;

    public Player(Vector2 pos, Vector2 direction, EntityManager entityManager, OrthoCamera camera){
        super(TextureManager.PLAYER, pos, direction);
        this.entityManager = entityManager;
        this.camera = camera;
    }

    private float speed = 50;

    @Override
    public void update() {
        pos.add(direction); //change position based on velocity

        if(Math.sqrt(Math.pow(getDirection().x, 2) + Math.pow(getDirection().y, 2)) < 300 * Gdx.graphics.getDeltaTime()){
            if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.W)){
                addDirection(-speed / (float) Math.sqrt(2), speed / (float) Math.sqrt(2));
            } else if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.W)){
                addDirection(speed / (float) Math.sqrt(2), speed / (float) Math.sqrt(2));
            } else if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.S)){
                addDirection(-speed / (float) Math.sqrt(2), -speed / (float) Math.sqrt(2));
            } else if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.S)){
                addDirection(speed / (float) Math.sqrt(2), -speed / (float) Math.sqrt(2));
            } else if(Gdx.input.isKeyPressed(Input.Keys.W)){
                addDirection(0, speed);
            } else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                addDirection(-speed, 0);
            } else if(Gdx.input.isKeyPressed(Input.Keys.S)){
                addDirection(0, -speed);
            } else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                addDirection(speed, 0);
            }
        }

        addDirection(-getDirection().x * Gdx.graphics.getDeltaTime() * 200, -getDirection().y * Gdx.graphics.getDeltaTime() * 200);

        //proper touch/click coordinates
        Vector2 touch = new Vector2(0, 0);
        if(Gdx.input.isTouched()){
            touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
        }

        //shooting bullets
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            if(System.currentTimeMillis() - reload >= 250){
                Vector2 shootDirection = new Vector2(touch.x - pos.x, touch.y - pos.y);
                entityManager.addBullet(new Bullet(pos.cpy().add(texture.getWidth()/2, texture.getHeight()/2).sub(TextureManager.BULLET.getWidth()/2, TextureManager.BULLET.getHeight()/2), shootDirection.scl(1 / shootDirection.len() * 15)));
                reload = System.currentTimeMillis();
            }
        }
    }
}
