package com.psci.astrea.astrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.psci.astrea.entity.TextureFactory;

import java.util.HashMap;

public class SpriteManager {

    private static SpriteManager instance;

    public static SpriteManager getInstance() {
        if (instance == null)
            instance = new SpriteManager();
        return instance;
    }

    private SpriteManager() {
        initialize();
    }

    private void initialize(){
        
    }

    /**
     * Factory pattern to create sprites. Note that the name is turned into lower case.
     * @param name
     * @return
     */
    private static MySprite createSprite(String name){
        MySprite sprite = null;
        name = name.toLowerCase();
        Texture texture = TextureFactory.createTexture(name);

        sprite = new MySprite(texture);
        sprite.setOriginCenter();
        return sprite;
    }


    public MySprite getSprite(String name){
        return createSprite(name);
    }


}
