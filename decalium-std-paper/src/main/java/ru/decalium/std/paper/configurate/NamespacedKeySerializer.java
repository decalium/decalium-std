/*
 * decalium-items
 * Copyright © 2023 George Pronyuk <https://vk.com/gpronyuk>
 *
 * decalium-items is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * decalium-items is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with decalium-items. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Lesser General Public License.
 */
package ru.decalium.std.paper.configurate;

import org.bukkit.NamespacedKey;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public final class NamespacedKeySerializer implements TypeSerializer<NamespacedKey> {

	@Override
	public NamespacedKey deserialize(Type type, ConfigurationNode node) throws SerializationException {
		return NamespacedKey.fromString(node.require(String.class));
	}

	@Override
	public void serialize(Type type, @Nullable NamespacedKey obj, ConfigurationNode node) throws SerializationException {
		if(obj != null) node.set(obj.toString());
	}
}
