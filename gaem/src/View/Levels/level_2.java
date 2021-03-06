package View.Levels;

import java.util.Random;

import Models.Star;
import Models.Enemies.GunshipBasic;
import Models.Enemies.HeavyBasic;
import Models.Enemies.HeavySpread;
import Models.Enemies.LightBasic;
import Models.Enemies.LightSpread;
import Models.Enemies.LightTiny;
import Models.Enemies.Bosses.BOSS2;
import View.World;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;

public class level_2 extends level
{
	Random rnd = new Random();
	boolean starttier2 = false;
	boolean PREPARE_TO_DIE = false;
	boolean musicstart = true;
	int boss_here = 0;
	int x;
	int y;
	int z;
	float time = 0;
	
	public level_2(World world) {
		super(world);
		timeLimit = 60*2;
	}
	
	@Override
	public void update() {
		world.timer.scheduleTask(new Task(){
			@Override
			public void run()
			{
				float size = rnd.nextFloat()*20; 
				if(size <= 25) //25
					world.background.get(9).insert(0, new Star(new Vector2(rnd.nextInt(700),0), size,size, new Vector2(0,size*4)));
				star = true;
			}
		} , 0.016f);
		star = false; 
		
		if(musicstart)
		{
			world.game.audio.loopMusic("level_2", 0.45f);
			musicstart = false;
			//world.actors.get(0).add(new BOSS2(new Vector2(350,-200)));
		}
		
		if(waveDone && time < timeLimit)
		{
			if(time < 30)
			{
				System.out.println("tier 1");
				System.out.println(time);
				y = com.badlogic.gdx.math.MathUtils.random(1, 10);
			}
			if(time >= 30 && !starttier2)
			{
				world.timer.scheduleTask(new Task() 
				{
					@Override
					public void run()
					{
						world.actors.get(0).add(new HeavyBasic(new Vector2(350,-200), 0));
						starttier2 = true;
						System.out.println(time);
					}
				} , 3.0f);
			}
			
			if(time >= 30 && starttier2)
			{
				System.out.println("tier 2 SONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
				System.out.println(time);
				z = com.badlogic.gdx.math.MathUtils.random(1, 4);
			}
				
			waveDone = false;
			HandleWaves(x);
		}
		
		if(time >= timeLimit && boss_here == 0)
		{
			world.game.audio.stopMusic("level_2");
			boss_here = 1;
		}
		if(boss_here == 1)
		{
			boss_here = 2;
			world.game.audio.loopMusic("level_2 boss", 0.45f);
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new BOSS2(new Vector2(350,-200)));
				}
			} , 1.0f);
		}
		
	}
	@Override
	protected void HandleWaves(int wave) 
	{
		if(y == 1)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 0));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 2));
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 3));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(250,-100), 10));
					world.actors.get(0).add(new LightBasic(new Vector2(450,-100), 11));
				}
			} , 3.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(350,-100), 1));
				}
			} , 5.1f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 8.1f;
					waveDone = true;
				}
			} , 8.1f);
			y = 0;
		}
		
		if(y == 2)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(350,-100), 1));
					world.actors.get(0).add(new LightSpread(new Vector2(350,-100), 1));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(300,-100), 2));
					world.actors.get(0).add(new LightBasic(new Vector2(400,-100), 3));
				}
			} , 1.5f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(100,-100), 4));
					world.actors.get(0).add(new LightSpread(new Vector2(600,-100), 5));
				}
			} , 4.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(300,-100), 5));
					world.actors.get(0).add(new LightBasic(new Vector2(400,-100), 4));
				}
			} , 6.3f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 9.3f;
					waveDone = true;
				}
			} , 9.3f);
			y = 0;
		}
		
		if(y == 3)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(-150,-100), 1));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(-150,-100), 1));
				}
			} , 1.2f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(-150,-100), 1));
				}
			} , 2.4f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(-150,-100), 1));
				}
			} , 3.6f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(-150,-100), 1));
				}
			} , 4.8f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(850,-100), 1));
				}
			} , 6.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(850,-100), 1));
				}
			} , 7.2f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(850,-100), 1));
				}
			} , 8.4f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(850,-100), 1));
				}
			} , 9.6f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(850,-100), 1));
				}
			} , 10.8f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 13.8f;
					waveDone = true;
				}
			} , 13.8f);
			y = 0;
		}
		
		if(y == 4)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 0));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(50,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(650,-100), 0));
				}
			} , 1.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(100,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(600,-100), 0));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(200,-100), 4));
					world.actors.get(0).add(new LightSpread(new Vector2(500,-100), 5));
				}
			} , 4.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 7.0f;
					waveDone = true;
				}
			} , 7.0f);
			y = 0;
		}
		
		if(y == 5)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(200,-100), 2));
					world.actors.get(0).add(new LightSpread(new Vector2(500,-100), 3));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(100,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(600,-100), 0));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(50,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(650,-100), 0));
				}
			} , 2.5f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(100,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(600,-100), 0));
				}
			} , 3.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 10));
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 11));
				}
			} , 5.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 7.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 10.0f;
					waveDone = true;
				}
			} , 10.0f);
			y = 0;
		}
		
		if(y == 6)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 4.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 6.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 8.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 11.0f;
					waveDone = true;
				}
			} , 11.0f);
			y = 0;
		}
		
		if(y == 7)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 7));
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 6));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(200,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(500,-100), 0));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(350,-100), 0));
				}
			} , 5.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 7.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 9.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 12.0f;
					waveDone = true;
				}
			} , 12.0f);
			y = 0;
		}
		
		if(y == 8)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(0,-100), 8));
					world.actors.get(0).add(new LightBasic(new Vector2(700,-100), 9));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(0,-100), 4));
					world.actors.get(0).add(new LightSpread(new Vector2(700,-100), 5));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 10));
				}
			} , 4.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 11));
				}
			} , 5.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 10));
				}
			} , 6.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 11));
				}
			} , 7.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 10));
				}
			} , 8.0f);
			
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 11.0f;
					waveDone = true;
				}
			} , 11.0f);
			y = 0;
		}
		
		if(y == 9)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(100,-100), 0));
					world.actors.get(0).add(new LightSpread(new Vector2(700,-100), 6));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(600,-100), 0));
					world.actors.get(0).add(new LightSpread(new Vector2(0,-100), 7));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(200,-100), 0));
					world.actors.get(0).add(new LightSpread(new Vector2(700,-100), 6));
				}
			} , 4.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(500,-100), 0));
					world.actors.get(0).add(new LightSpread(new Vector2(0,-100), 7));
				}
			} , 6.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(300,-100), 0));
					world.actors.get(0).add(new LightSpread(new Vector2(700,-100), 6));
				}
			} , 8.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(400,-100), 0));
					world.actors.get(0).add(new LightSpread(new Vector2(0,-100), 7));
				}
			} , 10.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(325,-100), 0));
					world.actors.get(0).add(new LightSpread(new Vector2(375,-100), 0));
				}
			} , 13.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 16.0f;
					waveDone = true;
				}
			} , 16.0f);
			y = 0;
		}
		
		if(y == 10)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(325,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(375,-100), 0));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(225,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(275,-100), 0));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(425,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(475,-100), 0));
				}
			} , 4.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(125,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(175,-100), 0));
				}
			} , 6.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(525,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(575,-100), 0));
				}
			} , 8.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 11.0f;
					waveDone = true;
				}
			} , 11.0f);
			y = 0;
		}
		
		/*if(y == #)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					
				}
			} , 0.0f);
			
			
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + #.#f;
					waveDone = true;
				}
			} , #.#f);
			y = 0;
		}*/
		
		if(z == 1)////////////////////////////////////////////////TIER 2 STARTS HERE, 30 WAVES IN TOTAL/////////////////////////////////////////////////////////////////////
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(525,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(575,-100), 0));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new HeavySpread(new Vector2(350,-100), 0));
				}
			} , 2.2f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(200,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(500,-100), 0));
				}
			} , 5.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(100,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(600,-100), 0));
				}
			} , 6.5f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 9.5f;
					waveDone = true;
				}
			} , 9.5f);
			z = 0;
		}
		
		if(z == 2)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(100,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(600,-100), 0));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(300,-100), 2));
					world.actors.get(0).add(new LightSpread(new Vector2(400,-100), 3));
				}
			} , 2.3f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new HeavyBasic(new Vector2(350,-100), 0));
				}
			} , 5.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new HeavyBasic(new Vector2(0,-100), 10));
					world.actors.get(0).add(new HeavyBasic(new Vector2(700,-100), 11));
				}
			} , 7.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 10.0f;
					waveDone = true;
				}
			} , 10.0f);
			z = 0;
		}
		
		if(z == 3)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new HeavyBasic(new Vector2(0,-100), 8));
				}
			} , 0.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new HeavyBasic(new Vector2(700,-100), 9));
				}
			} , 2.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new HeavyBasic(new Vector2(0,-100), 8));
				}
			} , 4.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new HeavyBasic(new Vector2(700,-100), 9));
				}
			} , 6.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(400,-100), 0));
					world.actors.get(0).add(new LightBasic(new Vector2(300,-100), 0));
				}
			} , 9.0f);
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightSpread(new Vector2(350,-100), 0));
				}
			} , 10.0f);
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + 13.0f;
					waveDone = true;
				}
			} , 13.0f);
			z = 0;
		}
		
		if(z == 4)
		{
			world.timer.scheduleTask(new Task()
			{
				@Override
				public void run()
				{
					world.actors.get(0).add(new LightBasic(new Vector2(350,-100), 1));
				}
			} , 2.3333335f);
			z = 0;
		}
		
		/*if(z == #)
		{
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					
				}
			} , 0.0f);
			
			
			
			world.timer.scheduleTask(new Task() 
			{
				@Override
				public void run()
				{
					time = time + #.#f;
					waveDone = true;
				}
			} , #.#f);
			z = 0;
		}*/
	}
	@Override
	public void start() {
		
	}
}
