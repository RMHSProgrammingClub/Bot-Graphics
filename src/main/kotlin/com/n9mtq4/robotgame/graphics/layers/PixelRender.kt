package com.n9mtq4.robotgame.graphics.layers

import com.n9mtq4.patternimage.Pattern
import com.n9mtq4.patternimage.PatternImage
import com.n9mtq4.patternimage.colorgetter.ColorGetter
import com.n9mtq4.robotgame.graphics.GraphicsDisplay
import com.n9mtq4.robotgame.graphics.constants.DISPLAY_HEIGHT
import com.n9mtq4.robotgame.graphics.constants.DISPLAY_WIDTH
import com.n9mtq4.robotgame.graphics.constants.SCALE
import com.n9mtq4.robotgame.graphics.constants.SHOOTING_COLOR
import com.n9mtq4.robotgame.graphics.utils.isInBounds

/**
 * Created by will on 11/20/15 at 7:02 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
class PixelRender(val graphicsDisplay: GraphicsDisplay) : ColorGetter {
	
	internal var pixels: Array<Int>
		
	init {
		
		this.pixels = Array(DISPLAY_HEIGHT * DISPLAY_WIDTH * SCALE, { -1 })
		
	}
	
	override fun getColorAt(x: Int, y: Int, pattern: Pattern, image: PatternImage): Int {
		
		return pixels[DISPLAY_WIDTH * (y / SCALE) + (x / SCALE)] // TODO: is this math right?
		
	}
	
	internal fun clear() {
//		internalMap.map { -1 } // this only copies it
//		TODO: find a functional way to do this
		for (i in 0..pixels.size - 1) {
			pixels[i] = -1
		}
	}
	
	internal fun setPixel(index: Int, color: Int) {
		pixels[index] = color
	}
	
	internal fun setPixel(x: Int, y: Int, color: Int) {
//		TODO: is this math right?
		setPixel(DISPLAY_WIDTH * y + x, color)
	}
	
	internal fun drawLine(x: Int, y: Int, angle: Int) {
//		TODO: is this math right?
//		for some reason, jake wants degrees
//		TODO: nag jake about changing it to radians
		val nx = Math.cos(Math.toRadians(angle.toDouble()))
		val ny = -Math.sin(Math.toRadians(angle.toDouble()))
		var cx: Double = x.toDouble()
		var cy: Double = y.toDouble()
		while (isInBounds(cx, cy)) {
			setPixel(cx.toInt(), cy.toInt(), SHOOTING_COLOR)
			cx += nx
			cy += ny
		}
	}
	
}
