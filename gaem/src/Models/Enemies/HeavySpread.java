package Models.Enemies;

import Models.Weapons.Projectiles.EnemyBullet;
import View.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;

public class HeavySpread extends Enemy
{
	private boolean wait;

	public HeavySpread(Vector2 position, int AI)
	{
		super(position, 90, 60, 80, 50);
		health = 400;
		actorID = 6;
		texture = "heavySpread";
		score = 600;
	}
	@Override
	public void update(World world)
	{
		super.update(world);
		if(loaded)
		{
			world.actors.get(0).add(new EnemyBullet(new Vector2(centerLocation.x,centerLocation.y+25),25,25,25,25, new Vector2(100,600)));
			world.actors.get(0).add(new EnemyBullet(new Vector2(centerLocation.x,centerLocation.y+25),25,25,25,25));
			world.actors.get(0).add(new EnemyBullet(new Vector2(centerLocation.x,centerLocation.y+25),25,25,25,25, new Vector2(-100,600)));
			world.game.audio.playSound("EnemyLaser"+com.badlogic.gdx.math.MathUtils.random(1, 4), 0.8f);
			world.game.audio.playSound("EnemyLaser"+com.badlogic.gdx.math.MathUtils.random(1, 4), 0.8f);
			world.game.audio.playSound("EnemyLaser"+com.badlogic.gdx.math.MathUtils.random(1, 4), 0.8f);
			world.getRender().addParticles(3, 2, 2, new Vector2(centerLocation.x,centerLocation.y+25));
			world.getRender().addParticles(3, 2, 2, new Vector2(centerLocation.x,centerLocation.y+25));
			world.getRender().addParticles(3, 2, 2, new Vector2(centerLocation.x,centerLocation.y+25));
			
			world.timer.scheduleTask(new Task(){

				@Override
				public void run() {
					loaded = true;
				}
				
			}, 1.66f);
			loaded = false;	
		}
	}
	
	/*this code will be the new update code for objects that use the AI parameters. use switch(AI) to do the proper AI methods */
	@Override
	protected void handleAI(World world)
	{
		switch(AI)
		{ //AI VARIABLE SWITCH
		case 1: //enemy zig-zags
			if(wait){
				
				if(position.x >= 540-width)
		        {
		            velocity.x = velocity.x - 15;
		        }
		        if(position.x <= 160)
		        {
		            velocity.x = velocity.x + 15;
		        }
		        
		        if(position.x >= 699-width)
		        {
		            velocity.x = -100;
		        }
		        if(position.x <= 0)
		        {
		            velocity.x = 100;
		        }
				
				world.timer.scheduleTask(new Task(){

					@Override
					public void run() {
						wait = true;
					}
					
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 2: //enemy only zig-zags half the screen to the left
			if(wait){
				
				if(position.x >= 340-width)
		        {
		            velocity.x = velocity.x - 15;
		        }
		        if(position.x <= 160)
		        {
		            velocity.x = velocity.x + 15;
		        }
		        
		        if(position.x >= 699-width)
		        {
		            velocity.x = -300;
		        }
		        if(position.x <= 0)
		        {
		            velocity.x = 300;
		        }
				
				world.timer.scheduleTask(new Task(){

					@Override
					public void run() {
						wait = true;
					}
					
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 3: //enemy only zig-zags a little half the screen to the right
			if(wait){
				
				if(position.x >= 540-width)
		        {
		            velocity.x = velocity.x - 15;
		        }
		        if(position.x <= 360)
		        {
		            velocity.x = velocity.x + 15;
		        }
		        
		        if(position.x >= 699-width)
		        {
		            velocity.x = -300;
		        }
		        if(position.x <= 0)
		        {
		            velocity.x = 300;
		        }
				
				world.timer.scheduleTask(new Task(){

					@Override
					public void run() {
						wait = true;
					}
					
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 4: //enemy falls then flies right for a bit
			if(wait)
			{
				if(position.y >= 200-width)
		        {
		            velocity.x = 100;
		        }
				if(position.y >= 400-width)
		        {
		            velocity.x = 0;
		        }
				world.timer.scheduleTask(new Task()
				{
					@Override
					public void run() 
					{
						wait = true;
					}
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 5: //enemy falls then flies left for a bit
			if(wait)
			{
				if(position.y >= 200-width)
		        {
		            velocity.x = -100;
		        }
				if(position.y >= 400-width)
		        {
		            velocity.x = 0;
		        }
				world.timer.scheduleTask(new Task()
				{
					@Override
					public void run() 
					{
						wait = true;
					}
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 6: //enemy falls then flies left for a bit, then falls right
			if(wait)
			{
				if(position.y >= 200-width)
		        {
		            velocity.x = 190;
		        }
				if(position.y >= 400-width)
		        {
		            velocity.x = 0;
		        }
				if(position.y >= 500-width)
		        {
		            velocity.x = -190;
		        }
				if(position.y >= 600-width)
		        {
		            velocity.x = 0;
		        }
				world.timer.scheduleTask(new Task()
				{
					@Override
					public void run() 
					{
						wait = true;
					}
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 7: //enemy falls then flies right for a bit, then falls left
			if(wait)
			{
				if(position.y >= 200-width)
		        {
		            velocity.x = -190;
		        }
				if(position.y >= 400-width)
		        {
		            velocity.x = 0;
		        }
				if(position.y >= 500-width)
		        {
		            velocity.x = 190;
		        }
				if(position.y >= 600-width)
		        {
		            velocity.x = 0;
		        }
				world.timer.scheduleTask(new Task()
				{
					@Override
					public void run() 
					{
						wait = true;
					}
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 8: //enemy goes to the right
			if(wait)
			{
		        velocity.x = 250;
				world.timer.scheduleTask(new Task()
				{
					@Override
					public void run() 
					{
						wait = true;
					}
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 9: //enemy goes to the left
			if(wait)
			{
		        velocity.x = -250;
				world.timer.scheduleTask(new Task()
				{
					@Override
					public void run() 
					{
						wait = true;
					}
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 10: //enemy goes to the right, stops at the middle, then go right some more
			if(wait)
			{
		        velocity.x = 150;
		        
		        if(position.y >= 200-width)
		        {
		            velocity.x = 0;
		        }
				if(position.y >= 400-width)
		        {
		            velocity.x = 150;
		        }
		        
				world.timer.scheduleTask(new Task()
				{
					@Override
					public void run() 
					{
						wait = true;
					}
				}, 0.0166f);
				wait = false;
			}
			break;
			
		case 11: //enemy goes to the right, stops a little, then go right some more
			if(wait)
			{
		        velocity.x = -150;
		        
		        if(position.y >= 200-width)
		        {
		            velocity.x = 0;
		        }
				if(position.y >= 400-width)
		        {
		            velocity.x = -150;
		        }
		        
				world.timer.scheduleTask(new Task()
				{
					@Override
					public void run() 
					{
						wait = true;
					}
				}, 0.0166f);
				wait = false;
			}
			break;
			
		default:
			break;
		}
	}
	
	
	@Override
	protected void spawn(World world){
		world.timer.scheduleTask(new Task(){

			@Override
			public void run() {
				loaded = true;
			}
			
		}, com.badlogic.gdx.math.MathUtils.random(0, 1.66f));
		super.spawn(world);
	}

}
