package cam72cam.immersiverailroading.track;

import java.util.ArrayList;
import java.util.List;

import cam72cam.immersiverailroading.util.RailInfo;
import cam72cam.immersiverailroading.util.VecUtil;
import cam72cam.mod.math.Vec3d;
import cam72cam.mod.math.Vec3i;

public class BuilderCrossing extends BuilderBase {
	/*
	 * This is terrible
	 */

	public BuilderCrossing(RailInfo info, Vec3i pos) {
		super(info.withLength(3), pos);

		this.setParentPos(Vec3i.ZERO);
		
		tracks.add(new TrackRail(this, Vec3i.ZERO));
		int blocks = 1;
		for (int i = -blocks; i <=blocks; i ++) {
			for (int j = -blocks; j <=blocks; j ++) {
				if (i == 0 && j == 0) {
					continue;
				}
				tracks.add(new TrackGag(this, new Vec3i(i, 0, j)));
			}
		}
	}

	@Override
	public List<VecYawPitch> getRenderData() {
		
		List<VecYawPitch> data = new ArrayList<VecYawPitch>();
		float angle = 0;

		double trackOffset = (1-info.settings.gauge.scale())/4;
		
		Vec3d pos = VecUtil.rotateWrongYaw(Vec3d.ZERO, angle-90);
		data.add(new VecYawPitch(pos.x, pos.y, pos.z, -angle, 0, info.settings.length, "RAIL_RIGHT", "RAIL_LEFT"));
		
		for (double i = -trackOffset; i < info.settings.length - trackOffset; i+=info.settings.gauge.scale()) {
			pos = VecUtil.rotateWrongYaw(new Vec3d(0, 0, i-0.25), angle-90);
			data.add(new VecYawPitch(pos.x, pos.y, pos.z-1, -angle, "RAIL_BASE"));
		}
		angle -= 90;
		
		pos = VecUtil.rotateWrongYaw(new Vec3d(0, 0, 0), angle-90);
		data.add(new VecYawPitch(pos.x, pos.y, pos.z, -angle, 0, info.settings.length, "RAIL_RIGHT", "RAIL_LEFT"));
		for (double i = -trackOffset; i < info.settings.length - trackOffset; i+=info.settings.gauge.scale()) {
			pos = VecUtil.rotateWrongYaw(new Vec3d(0, 0, i-0.25), angle-90);
			data.add(new VecYawPitch(pos.x-1, pos.y, pos.z, -angle, "RAIL_BASE"));
		}
		
		return data;
	}

}
