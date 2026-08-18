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

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;

/**
 * Represents a single entry in a {@link UFRegistry}.
 *
 * <p>Each entry wraps an object of type {@code T} and provides a unique {@link ResourceLocation}.
 * Registry entries are lazily initialized via {@link #get()} and can be used in streams or added to
 * other registries.
 *
 * @param <T> The type of object stored in this registry entry.
 */
public interface UFRegistryEntry<T> extends Supplier<T> {

	/**
	 * Returns the object stored in this registry entry.
	 *
	 * <p>This may trigger lazy initialization depending on the implementation.
	 *
	 * @return The object of type {@code T} contained in this entry.
	 */
	@Override
	T get();

	/**
	 * Returns the unique identifier for this registry entry.
	 *
	 * <p>The identifier is typically namespaced (e.g., {@code "modid:item_name"}) and corresponds to
	 * the ID used when registering the entry.
	 *
	 * @return The {@link ResourceLocation} for this entry.
	 */
	ResourceLocation getId();
}
