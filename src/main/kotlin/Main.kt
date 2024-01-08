import maze.GridInfo
import maze.stringifyGrid
import orientation.Position

fun main() {
    val gridInfo = GridInfo(Position(0, 0), Position(10, 10), 10, 10)
    val grid = gridInfo.buildGrid()
    println(stringifyGrid(grid))
}