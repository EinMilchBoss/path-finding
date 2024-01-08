package maze

import orientation.Position

data class Tile(val position: Position, val nextTiles: List<Tile>)