package orientation

data class Position(val x: Int, val y: Int) {
    fun move(direction: Direction): Position {
        val (dx, dy) = direction.step
        return Position(x + dx, y + dy)
    }

    fun getVectorTo(other: Position): Vector = Vector(other.x - x, other.y - y)
}