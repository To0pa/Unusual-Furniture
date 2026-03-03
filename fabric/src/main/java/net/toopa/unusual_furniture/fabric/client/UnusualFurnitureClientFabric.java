package net.toopa.unusual_furniture.fabric.client;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

import net.minecraft.client.particle.ParticleProvider;

import net.toopa.unusual_furniture.client.UnusualFurnitureClient;

import net.fabricmc.api.ClientModInitializer;

import net.toopa.unusual_furniture.client.particle.FurnitureSmokeParticle;
import net.toopa.unusual_furniture.common.reg.UFParticleTypes;

public class UnusualFurnitureClientFabric implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		UnusualFurnitureClient.init();
	}
}
