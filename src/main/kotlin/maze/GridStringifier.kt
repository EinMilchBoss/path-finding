package maze

import orientation.Position
import orientation.getDirection
import kotlin.IllegalStateException

const val WALL = '#'
const val WAY = '.'

private class GridStringifier(private val grid: Grid) {
    private val totalWidth = grid.info.width.addWallOffset()
    private val totalHeight = grid.info.height.addWallOffset()
    private val tiles = CharArray(totalWidth * totalHeight) { WALL }

    fun stringify(): String {
        setWays()
        return tilesToString()
    }

    private fun setWays() {
        fun iterate(current: Tile) {
            setWay(current.position.addWallOffset())
            for (next in current.nextTiles) {
                val transition = between(current.position.addWallOffset(), next.position.addWallOffset())
                setWay(transition)

                iterate(next)
            }
        }
        iterate(grid.first)
    }

    private fun setWay(at: Position) {
        tiles[at.getIndex()] = WAY
    }

    private fun Position.getIndex(): Int = y * totalWidth + x

    private fun Position.addWallOffset(): Position = Position(x.addWallOffset(), y.addWallOffset())

    private fun Int.addWallOffset(): Int = 2 * this + 1

    private fun between(current: Position, next: Position): Position =
        try {
            val direction = getDirection(from = current, to = next)
            current.move(direction)
        } catch (e: NoSuchElementException) {
            throw IllegalStateException("Exectly one wall tile has to be between the current and the next one.", e)
        }

    private fun tilesToString(): String {
        val tilesAmount = totalWidth * totalHeight
        val lineBreaks = totalHeight - 1
        return buildString(tilesAmount + lineBreaks) {
            for (y in 0..<totalHeight) {
                for (x in 0..<totalWidth) {
                    append(tiles[Position(x, y).getIndex()])
                }
                appendLine()
            }
        }
    }
}

fun stringifyGrid(grid: Grid): String = GridStringifier(grid).stringify()