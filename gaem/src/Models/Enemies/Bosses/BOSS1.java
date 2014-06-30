package Models.Enemies.Bosses;


import Models.MoveableEntity;
import Models.Enemies.Enemy;
import Models.Weapons.Projectiles.EnemyBullet;
import View.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;

public class BOSS1 extends Enemy
{
	Vector2 aim;
	int move=1;
	int tic = 0;
    int firerate = 35;
    int fired = 0;
    int ping = 6;
    int x = 0;
    int y = 0;
    int z = 0;
    int ready_4_battle = 0;
    private boolean wait = true;
	
	public BOSS1(Vector2 position) 
	{
		super(position, 300, 180, 290, 170);
		health = 10000;
		score = 10000;
		tick = 0;
		velocity = new Vector2(0,150);
		texture = "boss1";
		subObjects.add(new BOSS1turret(new Vector2(100,0), 100, 100, 0, 0, this));
		subObjects.add(new BOSS1turret(new Vector2(-100,0), 100, 100, 0, 0, this));
	}
	
	@Override
	public void update(World world)
	{
		super.update(world);
		if(wait){
			if(ready_4_battle == 1)
			{
			if(ping > 5)
	        {
	            x = com.badlogic.gdx.math.MathUtils.random(1, 3);
	            ping = 0;
	        }
	        if(x == 1)
	        {
	            if(fired>firerate)
	            {
	            	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(100,600)));
	            	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25));
	            	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-100,600)));
	            	world.game.audio.playSound("EnemyLaser"+com.badlogic.gdx.math.MathUtils.random(1, 4), 0.8f);
	                fired=0;
	                ping++;
	            }
	            else
	            {
	                fired++;
	            }
	        }
	        if(x == 2)
	        {
	            if(fired>firerate)
	            {
	            	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-125,600)));
	            	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-75,600)));
	            	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(125,600)));
	            	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(75,600)));
	            	world.game.audio.playSound("EnemyLaser"+com.badlogic.gdx.math.MathUtils.random(1, 4), 0.8f);
	                fired=0;
	                ping++;
	            }
	            else
	            {
	                fired++;
	            }
	        }
	        if(x == 3)
	        {
	            if(fired>firerate)
	            {
	            	z = com.badlogic.gdx.math.MathUtils.random(0, 3);
	            	if(z == 0)
	            	{
	            		world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-360,350)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-340,360)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-320,370)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-300,380)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-280,390)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-260,400)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-240,410)));
	            	}
	            	if(z == 1)
	            	{
	            		world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-260,350)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-240,360)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-220,370)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-200,380)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-180,390)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-160,400)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(-140,410)));
	            	}
	            	if(z == 2)
	            	{
	            		world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(260,350)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(240,360)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(220,370)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(200,380)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(180,390)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(160,400)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(140,410)));
	            	}
	            	if(z == 3)
	            	{
	            		world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(360,350)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(340,360)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(320,370)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(300,380)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(280,390)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(260,400)));
	                	world.actors.add(new EnemyBullet(new Vector2(position.x+width/2,position.y+height),25,25,25,25, new Vector2(240,410)));
	            	}
	            	world.game.audio.playSound("EnemyLaser"+com.badlogic.gdx.math.MathUtils.random(1, 4), 0.8f);
	                fired=0;
	                ping++;
	            }
	            else
	            {
	                fired++;
	            }
	        }
			else
				tick++;
			}
			
	        if(move == 1)
			{
				if(position.y >= 200-width/2)
				{
					velocity.y = 0;
					velocity.x = 100;
					move = 0;
				}
			}		
	        if(position.x >= 580-width)
	        {
	            velocity.x = -100;
	        }
	        if(position.x <= 120)
	        {
	            velocity.x = 100;
	        }
	        
	        if(health <= 0)
	        	world.level.bossDead = true;
	        
			world.timer.scheduleTask(new Task(){

				@Override
				public void run() {
					wait = true;
				}
				
			}, 0.0166f);
			wait = false;
		}
	}
	
	@Override
	public void collidesWith(MoveableEntity e, World world)
	{
		if(e instanceof BOSS4shield)
		{
			ready_4_battle = 0;
		}
		else
		{
			ready_4_battle = 1;
		}
	}
}
