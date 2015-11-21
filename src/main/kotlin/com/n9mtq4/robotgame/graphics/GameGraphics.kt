@file:JvmName("GameGraphics")

package com.n9mtq4.robotgame.graphics

/**
 * Created by will on 11/17/15 at 2:43 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
fun main(args: Array<String>) {
	
	GAME_DATA_FILE = args.joinToString(separator = " ")
	println("Reading data from: $GAME_DATA_FILE")
	
	val graphics = GraphicsDisplay()
	graphics.start()
	
}
