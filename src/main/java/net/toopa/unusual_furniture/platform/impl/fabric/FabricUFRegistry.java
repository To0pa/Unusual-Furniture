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
package net.toopa.unusual_furniture.platform.impl.fabric;

//? fabric {
import java.util.Collection;
import java.util.function.Supplier;

import net.toopa.unusual_furniture.platform.UFHolderRegistryEntry;
import net.toopa.unusual_furniture.platform.UFRegistry;
import net.toopa.unusual_furniture.platform.UFRegistryEntry;
import net.toopa.unusual_furniture.platform.impl.UFRegistryEntries;
import org.jetbrains.annotations.ApiStatus;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

@ApiStatus.Internal
public class FabricUFRegistry<T> implements UFRegistry<T> {

	private final UFRegistryEntries<T> entries = new UFRegistryEntries<>();
	private final Registry<T> registry;
	private final String id;

	public FabricUFRegistry(Registry<T> registry, String id) {
		this.registry = registry;
		this.id = id;
	}

	@Override
	public String namespace() {
		return this.id;
	}

	@Override
	public <I extends T> UFRegistryEntry<I> register(String id, Supplier<I> supplier) {
		return entries.add(
				FabricUFRegistryEntry.of(
						this.registry, ResourceLocation.fromNamespaceAndPath(this.id, id), supplier));
	}

	@Override
	public UFHolderRegistryEntry<T> registerForHolder(String id, Supplier<T> supplier) {
		return entries.add(
				FabricUFHolderRegistryEntry.of(
						this.registry, ResourceLocation.fromNamespaceAndPath(this.id, id), supplier));
	}

	@Override
	public Collection<UFRegistryEntry<T>> getEntries() {
		return this.entries.getEntries();
	}

	@Override
	public void init() {}
}
//?}
