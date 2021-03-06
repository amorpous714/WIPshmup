package Models.Weapons;

import Models.MoveableEntity;
import Models.Weapons.Projectiles.Bullet;
import Models.Weapons.Projectiles.DroneBullet;
import View.World;

import com.badlogic.gdx.math.Vector2;

public class DroneDEF extends Gun{
	public DroneDEF(int fireRate, int damage, int ammo)
	{
		this.fireRate = fireRate;
		this.damage = damage;
		this.ammo = ammo;
	}
	
	public DroneDEF(int fireRate, int damage)
	{
		this.fireRate = fireRate;
		this.damage = damage;
		this.ammo = -1;
	}
	
	public DroneDEF(){  
		fireRate = 0.123f;
		damage = 1;
		ammo = -1;
		name = "HELIX CANNON";
		slingable = false;
	}
	
	@Override
	protected void fire(World world, MoveableEntity entity){
		projectiles.add(new DroneBullet(new Vector2(entity.centerLocation.x-12, entity.centerLocation.y), new Vector2(0,-600)));
		projectiles.add(new DroneBullet(new Vector2(entity.centerLocation.x+12, entity.centerLocation.y), new Vector2(0,-600)));
		projectiles.add(new DroneBullet(new Vector2(entity.centerLocation.x-22, entity.centerLocation.y+10), new Vector2(0,-600)));
		projectiles.add(new DroneBullet(new Vector2(entity.centerLocation.x+22, entity.centerLocation.y+10), new Vector2(0,-600)));
		projectiles.add(new DroneBullet(new Vector2(entity.centerLocation.x-22, entity.centerLocation.y-10), new Vector2(0,-600)));
		projectiles.add(new DroneBullet(new Vector2(entity.centerLocation.x+22, entity.centerLocation.y-10), new Vector2(0,-600)));
		
		world.getRender().addParticles(4, 2, 2, new Vector2(entity.centerLocation.x-10, entity.centerLocation.y-10));
		world.getRender().addParticles(4, 2, 2, new Vector2(entity.centerLocation.x+10, entity.centerLocation.y-10));
		int x = com.badlogic.gdx.math.MathUtils.random(0, 3);
		world.game.audio.playSound("PlayerLaser"+com.badlogic.gdx.math.MathUtils.random(1, 4), 0.8f);
		super.fire(world, entity);
	}
}
