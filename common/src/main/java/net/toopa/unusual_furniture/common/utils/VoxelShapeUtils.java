package net.toopa.unusual_furniture.common.utils;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VoxelShapeUtils {

	public static VoxelShape rotateVoxelShape(VoxelShape shape, Direction.Axis axis, int degrees) {
		int times = ((degrees % 360) + 360) % 360 / 90;
		if (times == 0 || shape.isEmpty()) return shape;

		VoxelShape result = shape;
		for (int i = 0; i < times; ++i) {
			VoxelShape rotated = Shapes.empty();
			for (AABB box : result.toAabbs()) {
				AABB rotatedBox = switch (axis) {
					case Y -> new AABB(
							1 - box.maxZ, box.minY, box.minX,
							1 - box.minZ, box.maxY, box.maxX
					);
					case X -> new AABB(
							box.minX, 1 - box.maxZ, box.minY,
							box.maxX, 1 - box.minZ, box.maxY
					);
					case Z -> new AABB(
							box.minY, box.minX, box.minZ,
							box.maxY, box.maxX, box.maxZ
					);
				};
				rotated = Shapes.or(rotated, Shapes.create(rotatedBox));
			}
			result = rotated;
		}
		return result;
	}

	public static VoxelShape mirrorVoxelShape(VoxelShape shape, Direction.Axis axis) {
		if (shape.isEmpty()) return shape;

		VoxelShape result = Shapes.empty();

		for (AABB box : shape.toAabbs()) {
			AABB mirrored = switch (axis) {
				case X -> new AABB(
						1 - box.maxX, box.minY, box.minZ,
						1 - box.minX, box.maxY, box.maxZ
				);
				case Y -> new AABB(
						box.minX, 1 - box.maxY, box.minZ,
						box.maxX, 1 - box.minY, box.maxZ
				);
				case Z -> new AABB(
						box.minX, box.minY, 1 - box.maxZ,
						box.maxX, box.maxY, 1 - box.minZ
				);
			};

			result = Shapes.or(result, Shapes.create(mirrored));
		}

		return result;
	}

	public static VoxelShape rotateVoxelShapeFromCornerFixed(VoxelShape shape, Direction.Axis axis, int degrees, BlockCorner corner) {
		int times = ((degrees % 360) + 360) % 360 / 90;
		if (times == 0 || shape.isEmpty()) return shape;

		VoxelShape result = shape;
		double pivotX = corner.x;
		double pivotZ = corner.z;

		for (int i = 0; i < times; i++) {
			VoxelShape rotated = Shapes.empty();
			for (AABB box : result.toAabbs()) {
				AABB translated = box.move(-pivotX, 0, -pivotZ);

				AABB rotatedBox = switch (axis) {
					case Y -> new AABB(
							-translated.maxZ, translated.minY, translated.minX,
							-translated.minZ, translated.maxY, translated.maxX
					);
					case X -> new AABB(
							translated.minX, -translated.maxZ, translated.minY,
							translated.maxX, -translated.minZ, translated.maxY
					);
					case Z -> new AABB(
							translated.minY, translated.minX, translated.minZ,
							translated.maxY, translated.maxX, translated.maxZ
					);
				};
				rotatedBox = rotatedBox.move(pivotX, 0, pivotZ);

				rotated = Shapes.or(rotated, Shapes.create(rotatedBox));
			}
			result = rotated;
		}
		AABB bounds = result.bounds();
		double offsetX = 0, offsetY = 0, offsetZ = 0;

		if (bounds.minX < 0) offsetX = -bounds.minX;
		else if (bounds.maxX > 1) offsetX = 1 - bounds.maxX;

		if (bounds.minY < 0) offsetY = -bounds.minY;
		else if (bounds.maxY > 1) offsetY = 1 - bounds.maxY;

		if (bounds.minZ < 0) offsetZ = -bounds.minZ;
		else if (bounds.maxZ > 1) offsetZ = 1 - bounds.maxZ;

		if (offsetX != 0 || offsetY != 0 || offsetZ != 0) {
			result = result.move(offsetX, offsetY, offsetZ);
		}
		return result;
	}

	public enum BlockCorner {
		NORTH_WEST(0, 0),
		NORTH_EAST(1, 0),
		SOUTH_WEST(0, 1),
		SOUTH_EAST(1, 1);

		public final double x;
		public final double z;

		BlockCorner(double x, double z) {
			this.x = x;
			this.z = z;
		}
	}
}
