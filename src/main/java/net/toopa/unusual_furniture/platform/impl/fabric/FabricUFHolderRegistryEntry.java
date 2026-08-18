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
import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;

import net.toopa.unusual_furniture.platform.UFHolderRegistryEntry;
import org.jetbrains.annotations.ApiStatus;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;

@ApiStatus.Internal
public class FabricUFHolderRegistryEntry<T> implements UFHolderRegistryEntry<T> {

	private final ResourceLocation id;
	private final Holder<T> value;

	private FabricUFHolderRegistryEntry(ResourceLocation id, Holder<T> value) {
		this.id = id;
		this.value = value;
	}

	public static <T, I extends T> FabricUFHolderRegistryEntry<T> of(
			Registry<T> registry, ResourceLocation id, Supplier<I> supplier) {
		return new FabricUFHolderRegistryEntry<>(id, Registry.registerForHolder(registry, id, supplier.get()));
	}

	@Override
	public Holder<T> holder() {
		return this.value;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}
}
//?}
