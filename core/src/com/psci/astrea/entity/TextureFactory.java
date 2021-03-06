package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureFactory {

    private static HashMap<String, Texture> textures = new HashMap<String, Texture>();

    public static Texture createTexture(String key) {
        String path = "";
        key = key.toLowerCase();
        Texture texture = textures.get(key);
        if (texture == null) {
            if (key.equals("sun")) {
                path = "img/sun.png";
            } else if (key.equals("rocket")) {
                path = "img/player.png";
            } else if (key.equals("asteroid")) {
                path = "img/asteroid.png";
            }  else if (key.equals("alien")) {
                path = "img/alien.png";
            }else if(key.equals("bullet")){
                path = "img/bullet.png";
            }else {
                return null;
            }


            texture = new Texture(Gdx.files.internal(path));
            textures.put(key, texture);
        }

        return texture;
    }

}
