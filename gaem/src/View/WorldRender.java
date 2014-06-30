package View;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import Models.Entity;
import Models.MoveableEntity;
import Models.Players.Player;
import View.Levels.level;
import View.Levels.level_1;

import com.amorphous.gaem.gaemMain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WorldRender {
	World world;
	public SpriteBatch batch;
	OrthographicCamera cam;
	FPSLogger logger = new FPSLogger();
	float width, height;
	ShapeRenderer sr;
	gaemMain game;
	ShapeRenderer shapeRender;
	
	BitmapFont Wintermute, Belly, Zero;
	Array<BitmapFont> fonts;
	Texture UEFHealth, blue_background, none;
	
	Iterator<MoveableEntity> eIter;
	MoveableEntity entity;
	Player player;
	
	Array<ParticleEmitter> particles;
	Array<String> particlePath;
	
	Array<Sprite> sprites;
	Iterator<ParticleEmitter> pIter;
	
	//public Array<Texture> textures;
	private HashMap<String, Texture> textures;
	private HashMap<String, animation> animations;
	
	Sprite particleSprite;
	
	public Hud hud;
	
	public WorldRender(World world, gaemMain game){
		this.world=world;
		this.game=game;
		
		world.setRender(this);
		width = 1000;
		height = 900;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, width, height);
		cam.update();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		UEFHealth = new Texture(Gdx.files.internal("data/texture/UEFHealth.png"));
		UEFHealth.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		blue_background = new Texture(Gdx.files.internal("data/texture/blue_background.png"));
		blue_background.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		none = new Texture(Gdx.files.internal("data/texture/NONE.jpg"));
		//enemyCTexture = new Texture("data/texture/enemyC.png");
		//enemyCTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		particles = new Array<ParticleEmitter>();
		
		
		shapeRender = new ShapeRenderer();
		
		animations = new HashMap<String, animation>();
		animations.put("boom", new animation("boom", 89));
		animations.put("eb", new animation("eb", 3));
		
		textures = new HashMap<String, Texture>();
		textures.put("NONE", new Texture(Gdx.files.internal("data/texture/NONE.jpg")));//RED SUN OVER PARADICE
		textures.put("player1", new Texture(Gdx.files.internal("data/texture/player1.png"))); //player textures
		textures.put("player2", new Texture(Gdx.files.internal("data/texture/player2.png")));
		textures.put("player3", new Texture(Gdx.files.internal("data/texture/player3.png")));
		textures.put("player4", new Texture(Gdx.files.internal("data/texture/player4.png")));
		
		textures.put("powerupSTR", new Texture(Gdx.files.internal("data/texture/PowerupSTR.png"))); //Power ups
		textures.put("powerupSPD", new Texture(Gdx.files.internal("data/texture/PowerupSPD.png")));
		
		textures.put("lightBasic", new Texture(Gdx.files.internal("data/texture/lightBasic.png"))); //enemy textures, starting with light
		textures.put("lightSpread", new Texture(Gdx.files.internal("data/texture/lightSpread.png")));
		textures.put("lightLaser", new Texture(Gdx.files.internal("data/texture/lightLaser.png")));
		textures.put("heavyBasic", new Texture(Gdx.files.internal("data/texture/heavyBasic.png"))); //heavy enemies
		textures.put("heavySpread", new Texture(Gdx.files.internal("data/texture/heavySpread.png"))); 
		textures.put("heavyLaser", new Texture(Gdx.files.internal("data/texture/heavyLaser.png")));
		textures.put("gunshipBasic", new Texture(Gdx.files.internal("data/texture/gunshipBasic.png")));//gunship & unique enemies enemies, starting with gunships
		textures.put("spaceMine1", new Texture(Gdx.files.internal("data/texture/spaceMine1.png")));
		textures.put("eTurret1", new Texture(Gdx.files.internal("data/texture/eTurret1.png"))); //turrets
		
		textures.put("boss1", new Texture(Gdx.files.internal("data/texture/BOSS1.png"))); //BOSSES, THE BEST PART OF THE GAME, starting with boss1
		textures.put("boss1turret", new Texture(Gdx.files.internal("data/texture/BOSS1turret.png")));
		textures.put("boss2", new Texture(Gdx.files.internal("data/texture/BOSS2.png"))); //BOSS2
		textures.put("boss4", new Texture(Gdx.files.internal("data/texture/BOSS4.png"))); //BOSS4
		textures.put("boss4armL", new Texture(Gdx.files.internal("data/texture/BOSS4armL.png")));
		textures.put("boss4armR", new Texture(Gdx.files.internal("data/texture/BOSS4armR.png")));
		textures.put("boss4Shield", new Texture(Gdx.files.internal("data/texture/BOSS4shield.png")));
		textures.put("boss4turret", new Texture(Gdx.files.internal("data/texture/BOSS4turret.png")));
		textures.put("boss4turretB", new Texture(Gdx.files.internal("data/texture/BOSS4turretB.png")));
		
		
		textures.put("enemyBullet", new Texture(Gdx.files.internal("data/texture/enemyBullet.png"))); //projectiles and stuff, starting with enemy bullet
		textures.put("enemyBulletTiny", new Texture(Gdx.files.internal("data/texture/enemybullettiny.png")));
		textures.put("enemyLaser", new Texture(Gdx.files.internal("data/texture/enemylaser.png"))); 
		textures.put("vanillaBullet", new Texture(Gdx.files.internal("data/texture/vanillabullet.png")));
		
		textures.put("star", new Texture(Gdx.files.internal("data/texture/star.png"))); //scenery and other background stuff, starting with everyone's favorite ball of death, a star
		textures.put("yellow", new Texture(Gdx.files.internal("data/texture/yellow.png")));
		textures.put("a1", new Texture(Gdx.files.internal("data/texture/a1.png"))); 
		textures.put("a2", new Texture(Gdx.files.internal("data/texture/a2.png")));
		textures.put("a3", new Texture(Gdx.files.internal("data/texture/a3.png")));
		textures.put("a4", new Texture(Gdx.files.internal("data/texture/a4.png")));
		textures.put("a5", new Texture(Gdx.files.internal("data/texture/a5.png")));
		textures.put("a6", new Texture(Gdx.files.internal("data/texture/a6.png")));
		textures.put("a7", new Texture(Gdx.files.internal("data/texture/a7.png")));
		textures.put("a8", new Texture(Gdx.files.internal("data/texture/a8.png")));
		
		
		
		
		textures.put("UEFHealth", new Texture(Gdx.files.internal("data/texture/UEFHealth.png"))); //Other things, like UI bits and shit
		textures.put("blue background", new Texture(Gdx.files.internal("data/texture/blue_background.png"))); //33
		

		textures.put("missile", new Texture(Gdx.files.internal("data/texture/missile.png"))); //41
		
		particlePath = new Array<String>();
		particlePath.add("data/particle/UefMetal"); //0
		particlePath.add("data/particle/explosion"); //1
		particlePath.add("data/particle/BulletImpact"); //2
		particlePath.add("data/particle/EnemyFire"); //3
		particlePath.add("data/particle/Player1Fire"); //4
		particlePath.add("data/particle/powerupSTR"); //5
		particlePath.add("data/particle/powerupSPD"); //6
		
		
		Texture particle = new Texture("data/particle/particle.png");
		particleSprite = new Sprite(particle);
		Texture UefMetalTex = new Texture(Gdx.files.internal("data/particle/UefMetal.png"));
		Sprite UefMetalSprite = new Sprite(UefMetalTex);
		Sprite Spark = new Sprite(new Texture(Gdx.files.internal("data/particle/spark.png")));
		Sprite STR = new Sprite(new Texture(Gdx.files.internal("data/particle/particleSTR.png")));
		Sprite SPD = new Sprite(new Texture(Gdx.files.internal("data/particle/particleSPD.png")));
		sprites = new Array<Sprite>();
		sprites.add(particleSprite); //0
		sprites.add(UefMetalSprite); //1
		sprites.add(Spark); //2
		sprites.add(STR); //3
		sprites.add(SPD); //4
		
		fonts = new Array<BitmapFont>();
		fonts.add(new BitmapFont(Gdx.files.internal("data/font/Wintermute.fnt"), true)); //0
		fonts.add(new BitmapFont(Gdx.files.internal("data/font/Belly.fnt"), true)); //1
		fonts.add(new BitmapFont(Gdx.files.internal("data/font/Zero.fnt"), true)); //2
		
		hud = new Hud(new Vector2(700,0), 300, 900, this);
	}
	
	public void render(){
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		renderActors(world.getActors());
		renderText();
		renderParticles();
		renderBars();
		hud.update();
		batch.end();
		
		if(gaemMain.DEBUG)
			debug();
	}

	private void renderBars() {
	}
	
	public void renderHealthBar(MoveableEntity entity, boolean top, float offset, float thickness, String index1, String index2){
		if(top && entity.render){
			batch.draw(textures.get(index2), entity.getPosition().x-entity.getWidth(), entity.getPosition().y-entity.getHeight()-offset, entity.getWidth()/2, entity.getHeight()/2, 
					entity.getWidth(), thickness, 1, 1, entity.getRotation(), 0, 0, 
					textures.get(index2).getWidth()*-1, textures.get(index2).getHeight(), false, false);
			
			batch.draw(textures.get(index1), entity.getPosition().x-entity.getWidth(), entity.getPosition().y-entity.getHeight()-offset, entity.getWidth()/2, entity.getHeight()/2, 
					entity.getWidth()*entity.getHealth()/entity.getMaxHealth()*-1, thickness, 1, 1, entity.getRotation(), 0, 0, 
					textures.get(index1).getWidth(), textures.get(index1).getHeight(), false, false);
			
		} else if (entity.render){
			batch.draw(textures.get(index2), entity.getPosition().x-entity.getWidth(), entity.getPosition().y+offset, entity.getWidth()/2, entity.getHeight()/2, 
					entity.getWidth()*-1, thickness, 1, 1, entity.getRotation(), 0, 0, 
					textures.get(index2).getWidth(), textures.get(index2).getHeight(), false, false);
			
			batch.draw(textures.get(index1), entity.getPosition().x-entity.getWidth(), entity.getPosition().y+offset, entity.getWidth()/2, entity.getHeight()/2, 
					entity.getWidth()*entity.getHealth()/entity.getMaxHealth()*-1, thickness, 1, 1, entity.getRotation(), 0, 0, 
					textures.get(index1).getWidth(), textures.get(index1).getHeight(), false, false);
		}
	}
	public void renderHealthBar(MoveableEntity entity, float thickness, float width, float x, float y, String index1, String index2){
		batch.draw(textures.get(index2), x, y, width/2, thickness/2, 
				width, thickness, 1, 1, 0, 0, 0, 
				textures.get(index2).getWidth(), textures.get(index2).getHeight(), false, false);
		if(entity.render){
			batch.draw(textures.get(index1), x, y, width/2, thickness/2, 
				width*entity.getHealth()/entity.getMaxHealth(), thickness, 1, 1, 0, 0, 0, 
				textures.get(index1).getWidth(), textures.get(index1).getHeight(), false, false);
		}
	}
	public void renderRespawnBar(Player player, float thickness, float width, float x, float y, String index1, String index2){
		batch.draw(textures.get(index1), x, y, width/2, thickness/2, 
				width*player.spawnTic/player.spawnTime, thickness, 1, 1, 0, 0, 0, 
				textures.get(index1).getWidth(), textures.get(index1).getHeight(), false, false);
	}
	public void renderProgressBar(level level, float width, float height, float x, float y, String index1, String index2){
		batch.draw(textures.get(index2), x, y, width/2, height/2, 
				width, height, 1, 1, 0, 0, 0, 
				textures.get(index2).getWidth(), textures.get(index2).getHeight(), false, false);
		batch.draw(textures.get(index1), x, y+height, width/2, height/2, 
				width, height*level.getCurrentTime()/level.getTimeLimit()*-1, 1, 1, 0, 0, 0, 
				textures.get(index1).getWidth(), textures.get(index1).getHeight(), false, false);
	}

	private void renderParticles() {
		for(int c = 0 ; c < particles.size ; c++){
			particles.get(c).draw(batch, Gdx.graphics.getDeltaTime());
		}
		pIter = particles.iterator();
		while(pIter.hasNext()){
			if(pIter.next().isComplete()){
				pIter.remove();
			}
		}
	}

	private void renderText() {

	}
	
	public void renderText(String text, int index, int x, int y){
		fonts.get(index).draw(batch, text, x, y);
	}
	
	public void renderText(String text, int index, int x, int y, float scale){
		fonts.get(index).setScale(scale);
		fonts.get(index).drawMultiLine(batch, text, x, y);
		fonts.get(index).setScale(1);
	}

	private void debug() {
		shapeRender.setProjectionMatrix(cam.combined);
		shapeRender.begin(ShapeType.Line);
		shapeRender.rect(world.player1.getBounds().x, world.player1.getBounds().y, world.player1.getBounds().width, world.player1.getBounds().height);
		for(MoveableEntity e: world.getActors()){
			if(!e.circle)
				shapeRender.rect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
			if(e.subObjects.size != 0){
				for(MoveableEntity s: e.subObjects){
					if(!s.circle)
						shapeRender.rect(s.getBounds().x, s.getBounds().y, s.getBounds().width, s.getBounds().height);
				}
			}
		}
		for(MoveableEntity e: world.getActors()){
			if(e.circle){
				shapeRender.circle(e.getCircle().x, e.getCircle().y, e.getCircle().radius);
			}
			if(e.subObjects.size != 0){
				for(MoveableEntity s: e.subObjects){
					if(s.circle){
						shapeRender.circle(s.getCircle().x, s.getCircle().y, s.getCircle().radius);
					}
				}
			}
		}
		batch.begin();
		batch.end();
		shapeRender.end();
		
	}

	private void renderActors(Array<MoveableEntity> actors) {
		eIter = actors.iterator();
		while(eIter.hasNext()){
			entity = eIter.next();
				if(entity.render && !entity.animate && entity.actorID != -1)
					entity.render(this);
				else if(entity.render && !entity.animate){
					draw(entity, none);
				}
		}
		eIter = actors.iterator();
		while(eIter.hasNext()){
			entity = eIter.next();
			if(entity.animate)
				entity.animate(this, world);
		}
	}

	public void draw(Entity actor, Texture texture) {
		batch.draw(texture, actor.getPosition().x, actor.getPosition().y, actor.getWidth()/2, actor.getHeight()/2, 
				actor.getWidth(), actor.getHeight(), 1, 1, actor.getRotation(), 0, 0, 
				texture.getWidth(), texture.getHeight(), false, false);
		
	}
	
	public void draw(Entity actor, Texture texture, Color color) {
		batch.setColor(new Color(color));
		batch.draw(texture, actor.getPosition().x, actor.getPosition().y, actor.getWidth()/2, actor.getHeight()/2, 
				actor.getWidth(), actor.getHeight(), 1, 1, actor.getRotation(), 0, 0, 
				texture.getWidth(), texture.getHeight(), false, false);
		batch.setColor(new Color(1,1,1,1));
	}
	
	public void draw(Entity actor, String texture) {
		batch.draw(textures.get(texture), actor.getPosition().x, actor.getPosition().y, actor.getWidth()/2, actor.getHeight()/2, 
				actor.getWidth(), actor.getHeight(), 1, 1, actor.getRotation(), 0, 0, 
				textures.get(texture).getWidth(), textures.get(texture).getHeight(), false, false);
	}
	
	public void draw(Entity actor, String texture, Color color) {
		batch.setColor(new Color(color));
		batch.draw(textures.get(texture), actor.getPosition().x, actor.getPosition().y, actor.getWidth()/2, actor.getHeight()/2, 
				actor.getWidth(), actor.getHeight(), 1, 1, actor.getRotation(), 0, 0, 
				textures.get(texture).getWidth(), textures.get(texture).getHeight(), false, false);
		batch.setColor(new Color(1,1,1,1));
	}
	
	public void addParticles(int particleIndex, int spriteIndex, float scale, Vector2 position){
		ParticleEmitter temp = new ParticleEmitter();
		try {
            temp.load(Gdx.files.internal(particlePath.get(particleIndex)).reader(2024));
		} catch (IOException e) {
            e.printStackTrace();
		}
		temp.setSprite(sprites.get(spriteIndex));
		temp.setPosition(position.x, position.y);
		if(scale != -1f)
			temp.getScale().setHigh(scale);
		temp.start();
		particles.add(temp);
	}

	public OrthographicCamera getCamera() {
		return cam;
	}

	public void animate(String animationNum, int currentFrame, Entity entity) {
		draw(entity, animations.get(animationNum).getFrame(currentFrame));
	}

	public animation getAnimation(String animationNum) {
		return animations.get(animationNum);
	}
	
	public void dispose(){
		
	}

}
