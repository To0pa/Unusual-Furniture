package net.toopa.unusual_furniture.common.utils;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class RegistryGroup<T> implements Iterable<Map.Entry<T, ResourceLocation>> {

	private final LinkedHashMap<T, ResourceLocation> entries = new LinkedHashMap<>();
	private final List<RegistryGroup<T>> children = new ArrayList<>();

	public RegistryGroup() {}

	public RegistryGroup<T> child() {
		RegistryGroup<T> c = new RegistryGroup<>();
		children.add(c);
		return c;
	}

	public RegistryGroup<T> add(T obj, ResourceLocation id) {
		entries.put(obj, id);
		return this;
	}

	public LinkedHashMap<T, ResourceLocation> entries() {
		return entries;
	}

	public List<RegistryGroup<T>> children() {
		return children;
	}

	public void forEachEntry(BiConsumer<T, ResourceLocation> consumer) {
		entries.forEach(consumer);
		for (RegistryGroup<T> c : children) {
			c.forEachEntry(consumer);
		}
	}

	public LinkedHashMap<T, ResourceLocation> flatten() {
		LinkedHashMap<T, ResourceLocation> out = new LinkedHashMap<>();
		collect(out);
		return out;
	}

	private void collect(LinkedHashMap<T, ResourceLocation> out) {
		out.putAll(entries);
		for (RegistryGroup<T> c : children) {
			c.collect(out);
		}
	}

	@Override
	public Iterator<Map.Entry<T, ResourceLocation>> iterator() {
		return flatten().entrySet().iterator();
	}
}
