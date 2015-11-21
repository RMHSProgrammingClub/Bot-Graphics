package com.n9mtq4.robotgame.graphics

/**
 * Created by will on 11/20/15 at 9:37 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
fun isInBounds(x: Double, y: Double) = isInBounds(x.toInt(), y.toInt())
fun isInBounds(x: Int, y: Int): Boolean {
	return x > 0 && y > 0 && x < DISPLAY_WIDTH && y < DISPLAY_HEIGHT
}
