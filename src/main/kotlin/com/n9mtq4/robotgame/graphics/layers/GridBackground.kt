package com.n9mtq4.robotgame.graphics.layers

import com.n9mtq4.patternimage.Pattern
import com.n9mtq4.patternimage.PatternImage
import com.n9mtq4.patternimage.colorgetter.ColorGetter
import com.n9mtq4.robotgame.graphics.constants.BACKGROUND
import com.n9mtq4.robotgame.graphics.constants.GRID_HIGHLIGHT
import com.n9mtq4.robotgame.graphics.constants.GRID_INTERVAL
import com.n9mtq4.robotgame.graphics.constants.SCALE

/**
 * Created by will on 11/17/15 at 3:03 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
class GridBackground : ColorGetter {
	
	override fun getColorAt(x: Int, y: Int, pattern: Pattern, patternImage: PatternImage): Int {
		
		return when {
			x % (SCALE * GRID_INTERVAL) == 0 -> GRID_HIGHLIGHT
			y % (SCALE * GRID_INTERVAL) == 0 -> GRID_HIGHLIGHT
			else -> BACKGROUND
		}
		
	}
	
}
