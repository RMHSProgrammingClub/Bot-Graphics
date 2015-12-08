package com.n9mtq4.robotgame.graphics

import com.n9mtq4.patternimage.PatternImage
import com.n9mtq4.patternimage.ui.PatternImageContainer

/**
 * Created by will on 11/20/15 at 6:42 PM.
 * 
 * A [PatternImageContainer] that sends tick events
 * to the [GraphicsDisplay]
 * 
 * @author Will "n9Mtq4" Bresnahan
 */
class TickingPatternContainer(val gameDisplay: GraphicsDisplay, patternImage: PatternImage, debug: Boolean, ticksPerSecond: Double, scale: Int) : 
		PatternImageContainer(patternImage, debug, ticksPerSecond, scale) {
	
	override fun tick() {
		super.tick()
//		gameDisplay.tick()
	}
	
	override fun render() {
		super.render()
		gameDisplay.tick()
	}
	
}
