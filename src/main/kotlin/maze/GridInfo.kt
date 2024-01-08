package maze

import orientation.Direction
import orientation.Position

data class GridInfo(val start: Position, val end: Position, val width: Int, val height: Int) {
    fun buildGrid(): Grid {
        val visited = mutableSetOf<Position>()
        fun iterate(current: Position): Tile {
            visited.add(current)

            if (current == end)
                return Tile(current, emptyList())

            val nextTiles = mutableListOf<Tile>()
            val randomizedDirections = Direction.entries.shuffled()
            for (direction in randomizedDirections) {
                val newPosition = current.move(direction)
                if (newPosition in visited || !hasPosition(newPosition))
                    continue

                val nextTile = iterate(newPosition)
                nextTiles.add(nextTile)
            }

            return Tile(current, nextTiles)

        }
        return Grid(this, iterate(start))
    }

    private fun hasPosition(position: Position): Boolean = position.x in 0..<width && position.y in 0..<height
}