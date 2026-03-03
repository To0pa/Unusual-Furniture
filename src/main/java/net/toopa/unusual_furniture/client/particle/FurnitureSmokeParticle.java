package net.toopa.unusual_furniture.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class FurnitureSmokeParticle extends TextureSheetParticle {
	private final SpriteSet spriteSet;
	private float angularVelocity;
	private float angularAcceleration;

	protected FurnitureSmokeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(1.5F, 1.5F);
		this.quadSize *= 1.3F;
		this.lifetime = Math.max(1, 20 + (this.random.nextInt(8) - 4));
		this.gravity = 0.12F;
		this.hasPhysics = true;
		this.xd = vx * 0.5;
		this.yd = vy * 0.5;
		this.zd = vz * 0.5;
		this.angularVelocity = 0.0F;
		this.angularAcceleration = 0.01F;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public void tick() {
		super.tick();
		this.oRoll = this.roll;
		this.roll = this.roll + this.angularVelocity;
		this.angularVelocity = this.angularVelocity + this.angularAcceleration;
		if (!this.removed) {
			this.setSprite(this.spriteSet.get(this.age / 2 % 12 + 1, 12));
		}
	}

	public static class FurnitureSmokeParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public FurnitureSmokeParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new FurnitureSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}
}
