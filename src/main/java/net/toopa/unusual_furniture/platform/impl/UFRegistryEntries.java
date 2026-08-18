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
package net.toopa.unusual_furniture.platform.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import net.toopa.unusual_furniture.platform.UFRegistryEntry;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class UFRegistryEntries<T> {

	// Internal list of registry entries
	private final List<UFRegistryEntry<T>> entries = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public <I extends T, E extends UFRegistryEntry<I>> E add(E entry) {
		entries.add((UFRegistryEntry<T>) entry);
		return entry;
	}

	public List<UFRegistryEntry<T>> getEntries() {
		return ImmutableList.copyOf(entries);
	}
}
