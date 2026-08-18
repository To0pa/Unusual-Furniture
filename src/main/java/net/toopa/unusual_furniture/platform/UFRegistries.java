/*
 * Copyright 2026 macuguita
 *
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 *
 * Additional permission:
 * The copyright holder, macuguita, expressly permits this work to be
 * incorporated into the [PROJECT NAME] project and to be modified and
 * distributed as part of that project under the Creative Commons
 * Attribution-NoDerivatives 4.0 International License (CC BY-ND 4.0).
 * This additional permission applies only to the [PROJECT NAME] project.
 */
package net.toopa.unusual_furniture.platform;

import net.minecraft.core.Registry;

import net.toopa.unusual_furniture.CommonAbstraction;
import net.toopa.unusual_furniture.platform.impl.UFRegistryChild;

/**
 * Utility class for creating and managing {@link UFRegistry} instances.
 *
 * <p>Provides methods to create standard or child registries, making it easier to group registry
 * entries and integrate with Minecraft's built-in registries.
 *
 * <h2>Implementation note</h2>
 *
 * <p>On NeoForge, Yumi's {@code Registry.register} bypass allows skipping deferred registries in
 * favor of vanilla's {@link net.minecraft.core.Registry#register} directly. However, to minimize
 * platform-specific "hacks" and maintain cross-platform compatibility, this class wraps
 * registration behind {@link CommonAbstraction}, letting each platform handle the details
 * internally while exposing a single consistent API. Because of this you <strong>CANNOT</strong> use this
 * from a mod initializer provided by Yumi on Neoforge, in the future I might add another
 * implementation that allows this.
 */
public final class UFRegistries {

	private UFRegistries() {}

	/**
	 * Creates a <strong>child registry</strong> of the given parent {@link UFRegistry}.
	 *
	 * <p>Entries added to this child registry are automatically added to the parent, which is useful
	 * for logically grouping registry entries without duplicating code.
	 *
	 * @param parent The parent {@link UFRegistry} to which this child will belong.
	 * @param <T>    The type of entries stored in the registry.
	 * @return A new {@link UFRegistry} instance representing the child registry.
	 */
	public static <T> UFRegistry<T> create(UFRegistry<T> parent) {
		return new UFRegistryChild<>(parent);
	}

	/**
	 * Creates a new {@link UFRegistry} for a given Minecraft {@link Registry}.
	 *
	 * <p>This allows mod-specific registries to be created that integrate with Minecraft's built-in
	 * registries while keeping entries namespaced to the mod.
	 *
	 * @param registry The Minecraft {@link Registry} to wrap, e.g., from {@link
	 *                 net.minecraft.core.registries.BuiltInRegistries}.
	 * @param id       The namespace of the mod creating this registry.
	 * @param <T>      The type of entries stored in the registry.
	 * @return A new {@link UFRegistry} instance.
	 */
	public static <T> UFRegistry<T> create(Registry<T> registry, String id) {
		return CommonAbstraction.INSTANCE.createUFRegistry(registry, id);
	}
}
