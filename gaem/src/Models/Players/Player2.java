package Models.Players;

import java.io.IOException;

import Models.Weapons.LaserDEF;
import Models.Weapons.SpreadDEF;
import Models.Weapons.VanillaDEF;
import Models.Weapons.VanillaSPD;
import Models.Weapons.VanillaSTR;
import View.World;
import View.WorldRender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.amorphous.gaem.GamepadHandler;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player2 extends Player{
	private ParticleEmitter fumes;
	
	public Player2(Vector2 position, float width, float height, float hitX,
			float hitY, Vector2 velocity, float rotation, int health) {
		super(position, width, height, hitX, hitY, velocity, rotation, health);
	}
	public Player2(Vector2 position, float width, float height, float hitX,
			float hitY) {
		super(position, width, height, hitX, hitY);
		actorID = 1;
		int ship = com.badlogic.gdx.math.MathUtils.random(1, 3);
		if(ship  == 1)
		{
			subObjects.add(new vanillaDECAL(new Vector2(), 2, this));
			texture = "vanilla";
			setHeight(60);// = 60;
			setWidth(60);// = 60;
			bounds.width = 45;
			bounds.height = 45;
			gun = new VanillaDEF();
		}
		if(ship  == 2)
		{
			subObjects.add(new laserDECAL(new Vector2(), 2, this));
			texture = "laser";
			setHeight(60);// = 60;
			setWidth(90);// = 60;
			bounds.width = 60;
			bounds.height = 45;
			gun = new LaserDEF();
		}
		if(ship  == 3)
		{
			subObjects.add(new spreadDECAL(new Vector2(), 2, this));
			texture = "spread";
			setHeight(70);// = 60;
			setWidth(40);// = 60;
			bounds.width = 30;
			bounds.height = 50;
			gun = new SpreadDEF();
		}
		//subObjects.add(new VanillaDECAL(new Vector2(), 2, this));
		fumes = new ParticleEmitter();
		try {
            fumes.load(Gdx.files.internal("data/particle/Player1Fumes").reader(2024));
		} catch (IOException e) {
            e.printStackTrace();
		}
		fumes.setPosition(position.x, position.y);
		Texture particle = new Texture("data/particle/particle.png");
		Sprite particleSprite = new Sprite(particle);
		fumes.setSprite(particleSprite);
		fumes.getScale().setHigh(8f);
		fumes.start();
		gun = new VanillaDEF();
	}
	
	public Player2(Vector2 position, float width, float height, float hitX,
			float hitY, SelectShip ship) {
		super(position, width, height, hitX, hitY, ship);
		
		actorID = 0;
		
		//subObjects.add(new VanillaDECAL(new Vector2(), 1, this));
		fumes = new ParticleEmitter();
		try {
            fumes.load(Gdx.files.internal("data/particle/Player1Fumes").reader(2024));
		} catch (IOException e) {
            e.printStackTrace();
		}
		fumes.setPosition(position.x, position.y);
		Texture particle = new Texture("data/particle/particle.png");
		Sprite particleSprite = new Sprite(particle);
		fumes.setSprite(particleSprite);
		fumes.getScale().setHigh(8f);
		fumes.start();
	}
	
	public void update(World world){
		if(health <= 0){
			//world.getRender().UefExplode(new Vector2((position.x+width/2),(position.y+height/2)));
			world.getRender().addParticles(1, 0, -1f, new Vector2(centerLocation.x, centerLocation.y));
			world.getRender().addParticles(0, 1, 18f, new Vector2(centerLocation.x, centerLocation.y));
			world.game.audio.playSound("Player1Explosion", 1);
			fumes.start();
		}
		fumes.setPosition(centerLocation.x, centerLocation.y+20);
		super.update(world);
	}
	
	@Override
	protected void applyPowerup(){
		switch(powerupID){
			case 0:
				gun = null;
				this.gun = new VanillaDEF();
				break;
			default:
				break;
		
			case 1:
				gun = null;
				this.gun = new VanillaSTR();
				break;
				
			case 2:
				gun = null;
				this.gun = new VanillaSPD();
				break;
		}
		super.applyPowerup();
	}
	
	@Override
	protected void HandleInput(World world) {
		if(world.keys[World.PLAYER_TWO_FIRE])
			gun.fire = true;
		else
			gun.fire = false;
		if(world.keys[World.PLAYER_TWO_UP])
			velocity.y = -Player.SPEED;
		if(world.keys[World.PLAYER_TWO_LEFT])
			velocity.x = -Player.SPEED;
		if(world.keys[World.PLAYER_TWO_DOWN])
			velocity.y = Player.SPEED;
		if(world.keys[World.PLAYER_TWO_RIGHT])
			velocity.x = Player.SPEED;
		
		if(!world.keys[World.PLAYER_TWO_UP] && !world.keys[World.PLAYER_TWO_DOWN])
			velocity.y = 0;
		if(!world.keys[World.PLAYER_TWO_LEFT] && !world.keys[World.PLAYER_TWO_RIGHT])
			velocity.x = 0;
		
	}
	
	@Override
	protected void handleGamepad(World world){
		if(world.controllerButtons[GamepadHandler.PLAYER_TWO_A])
			gun.fire = true;
		else
			gun.fire = false;
		if(world.controllerPOV[GamepadHandler.PLAYER_TWO_POV_UP])
			velocity.y = -Player.SPEED;
		if(world.controllerPOV[GamepadHandler.PLAYER_TWO_POV_LEFT])
			velocity.x = -Player.SPEED;
		if(world.controllerPOV[GamepadHandler.PLAYER_TWO_POV_DOWN])
			velocity.y = Player.SPEED;
		if(world.controllerPOV[GamepadHandler.PLAYER_TWO_POV_RIGHT])
			velocity.x = Player.SPEED;
		
		if(!world.controllerPOV[GamepadHandler.PLAYER_TWO_POV_UP] && !world.controllerPOV[GamepadHandler.PLAYER_TWO_POV_DOWN])
			velocity.y = 0;
		if(!world.controllerPOV[GamepadHandler.PLAYER_TWO_POV_LEFT] && !world.controllerPOV[GamepadHandler.PLAYER_TWO_POV_RIGHT])
			velocity.x = 0;
		
		if(world.controllerAxis[GamepadHandler.PLAYER_TWO_LSTICK_Y] != 0)
			velocity.y = Player.SPEED*world.controllerAxis[GamepadHandler.PLAYER_TWO_LSTICK_Y];
		if(world.controllerAxis[GamepadHandler.PLAYER_TWO_LSTICK_X] != 0)
			velocity.x = Player.SPEED*world.controllerAxis[GamepadHandler.PLAYER_TWO_LSTICK_X];
	}
	
	@Override
	public void render(WorldRender render){
		fumes.draw(render.batch, Gdx.graphics.getDeltaTime());
		super.render(render);
	}
	
	@Override
	public void damage(int i) 
	{
		super.damage(i);
	}
}
