package ru.cws.waveycapes;

import org.joml.Quaternionf;
import org.joml.Vector3f;

@FunctionalInterface
public interface Axis {
    Axis XN = radians -> new Quaternionf().rotationX(-radians);
    Axis XP = radians -> new Quaternionf().rotationX(radians);
    Axis YN = radians -> new Quaternionf().rotationY(-radians);
    Axis YP = radians -> new Quaternionf().rotationY(radians);
    Axis ZN = radians -> new Quaternionf().rotationZ(-radians);
    Axis ZP = radians -> new Quaternionf().rotationZ(radians);

    static Axis of(Vector3f axis) {
        return radians -> new Quaternionf().rotationAxis(radians, axis);
    }

    Quaternionf rotation(float radians);

    default Quaternionf rotationDegrees(float degrees) {
        return this.rotation(degrees * (float) (Math.PI / 180.0));
    }
}