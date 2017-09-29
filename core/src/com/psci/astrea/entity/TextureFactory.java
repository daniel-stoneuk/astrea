package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureFactory {
	
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();

	public static Texture createTexture(String key){
		String path = "";
		key = key.toLowerCase();
		Texture texture = (Texture)textures.get(key);
		if(texture == null){
			if(key.equals("star")){
				path = "img/star.png";
			}
			
			else{
				return null;
			}
			
			texture = new Texture(Gdx.files.internal(path));
			textures.put(key, texture);
		}
		
		return texture;
	}

}
