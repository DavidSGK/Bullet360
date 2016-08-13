package com.davidsgk.bullet360;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.davidsgk.bullet360.camera.OrthoCamera;
import com.davidsgk.bullet360.entity.EntityManager;

public class WorldRenderer {

    private EntityManager entityManager;
    private OrthoCamera camera;

    ShapeRenderer debugRenderer = new ShapeRenderer();

    public WorldRenderer(EntityManager entityManager, OrthoCamera camera){
        this.entityManager = entityManager;
        this.camera = camera;
    }
}
