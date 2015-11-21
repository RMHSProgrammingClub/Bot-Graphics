package com.n9mtq4.robotgame.graphics.layers

import com.n9mtq4.patternimage.Pattern
import com.n9mtq4.patternimage.PatternImage
import com.n9mtq4.patternimage.colorgetter.ColorGetter
import com.n9mtq4.robotgame.graphics.DISPLAY_HEIGHT
import com.n9mtq4.robotgame.graphics.DISPLAY_WIDTH
import com.n9mtq4.robotgame.graphics.GraphicsDisplay
import com.n9mtq4.robotgame.graphics.SCALE
import com.n9mtq4.robotgame.graphics.SHOOTING_COLOR
import com.n9mtq4.robotgame.graphics.isInBounds

/**
 * Created by will on 11/20/15 at 7:02 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
class PixelRender(val graphicsDisplay: GraphicsDisplay) : ColorGetter {
	
	internal var pixels: Array<Int>
		
	init {
		
		this.pixels = Array(DISPLAY_HEIGHT * DISPLAY_WIDTH, { -1 })
		
	}
	
	override fun getColorAt(x: Int, y: Int, pattern: Pattern, image: PatternImage): Int {
		
		return pixels[DISPLAY_WIDTH * (x / SCALE) + (y / SCALE)] // TODO: is this math right?
		
	}
	
	internal fun clear() {
//		internalMap.map { -1 } // this only copies it
//		TODO: find a functional way to do this
		for (i in 0..pixels.size) {
			pixels[i] = -1
		}
	}
	
	internal fun setPixel(index: Int, color: Int) {
		pixels[index] = color
	}
	
	internal fun setPixel(x: Int, y: Int, color: Int) {
		setPixel(DISPLAY_WIDTH * x + y, color) // TODO: is this math right?
	}
	
	internal fun drawLine(x: Int, y: Int, angle: Int) {
//		TODO: is this math right?
//		for some reason, jake wants degrees
//		TODO: nag jake about changing it to radians
		val nx = Math.cos(Math.toDegrees(angle.toDouble()))
		val ny = Math.sin(Math.toDegrees(angle.toDouble()))
		var cx = x.toDouble()
		var cy = y.toDouble()
		while (isInBounds(cx, cy)) {
			setPixel(cx.toInt(), cy.toInt(), SHOOTING_COLOR)
			cx += nx
			cy += ny
		}
	}
	
}