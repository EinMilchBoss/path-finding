package orientation

import kotlin.math.absoluteValue

enum class Direction(val step: Vector) {
    UP(Vector(0, -1)),
    DOWN(Vector(0, 1)),
    RIGHT(Vector(1, 0)),
    LEFT(Vector(-1, 0));
}

fun getDirection(from: Position, to: Position): Direction {
    val vector = from.getVectorTo(to)
    val unitVector = vector.toUnitVector()
    return Direction.entries.first { it.step == unitVector }
}