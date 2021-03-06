package com.n9mtq4.robotgame.graphics

import com.n9mtq4.kotlin.extlib.assertTrue
import com.n9mtq4.patternimage.Pattern
import com.n9mtq4.patternimage.PatternImage
import com.n9mtq4.patternimage.colorgetter.StaticColor
import com.n9mtq4.patternimage.ui.PatternImageContainer
import com.n9mtq4.robotgame.graphics.constants.BACKGROUND
import com.n9mtq4.robotgame.graphics.constants.BOX
import com.n9mtq4.robotgame.graphics.constants.DISPLAY_HEIGHT
import com.n9mtq4.robotgame.graphics.constants.DISPLAY_WIDTH
import com.n9mtq4.robotgame.graphics.constants.FLAG_COLOR
import com.n9mtq4.robotgame.graphics.constants.GAME_DATA_FILE
import com.n9mtq4.robotgame.graphics.constants.SCALE
import com.n9mtq4.robotgame.graphics.constants.TEAM_COLOR
import com.n9mtq4.robotgame.graphics.layers.GridBackground
import com.n9mtq4.robotgame.graphics.layers.PixelRender
import java.awt.Rectangle
import java.io.BufferedReader
import java.io.FileReader
import javax.swing.JFrame

/**
 * Created by will on 11/17/15 at 2:45 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
class GraphicsDisplay : PatternImage(DISPLAY_WIDTH * SCALE, DISPLAY_HEIGHT * SCALE) {
	
	val frame: JFrame
	val patternContainer: PatternImageContainer
	
	val fileReader: FileReader
	val bufferedReader: BufferedReader
	
	val pixelRender: PixelRender
	
	var gameEnded: Boolean
	var tick: Int
	
	/*
	* Initializes the frame
	* */
	init {
		
		this.tick = 0
		this.gameEnded = false
		
		this.frame = JFrame("Bot Game")
//		i know passing 'this' twice is messy, but its logical
//		TODO: change back to true
		this.patternContainer = TickingPatternContainer(this, this, true, PatternImageContainer.TICKS_DEFAULT, 1)
		
		frame.add(patternContainer)
		
		frame.pack()
		frame.isVisible = true
		frame.setLocationRelativeTo(null)
		
	}
	
	/*
	* Initializes the reading of the data file
	* */
	init {
		
		this.fileReader = FileReader(GAME_DATA_FILE)
		this.bufferedReader = BufferedReader(fileReader)
		
	}
	
	/*
	* Adds the rendering components
	* */
	init {
		
		setBackground(when {
			SCALE > 1 -> GridBackground()
			else -> StaticColor(BACKGROUND)
		})
		
		this.pixelRender = PixelRender(this)
		addPattern(Pattern(pixelRender, Rectangle(0, 0, width, height)))
		
	}
	
	/**
	 * Starts rendering the game
	 * */
	fun start() {
		patternContainer.start()
	}
	
	/**
	 * Handles updating the game display
	 * for each turn
	 * */
	internal fun tick() {
		
		if (gameEnded) return // don't render the game if its over
		
//		grab the turn data
		val mapData = bufferedReader.readLine()
		val shootingData = bufferedReader.readLine()
		
//		make sure the game is still going
//		TODO: game over logic
		if (mapData.startsWith("WIN") || mapData.startsWith("DRAW")) {
			gameOver(mapData)
			return
		}
		
		pixelRender.clear() // clear the previous turns map data
		
//		make sure the read map data is correct
		assertTrue(mapData.length == pixelRender.pixels.size / SCALE, 
				"Map Data isn't the correct length: expected: ${pixelRender.pixels.size / SCALE}, actual: ${mapData.length}")
		
//		add this turn's robots
		mapData.toCharArray().forEachIndexed { i, it ->
			if (it == '0') return@forEachIndexed // this is not the map data we are looking for, move along
			
//			start populating the map
			when (it) {
				'1' -> pixelRender.setPixel(i, TEAM_COLOR[1]) // team 1
				'2' -> pixelRender.setPixel(i, TEAM_COLOR[2]) // team 2
				'3' -> pixelRender.setPixel(i, BOX) // box
				'4' -> pixelRender.setPixel(i, BOX) // wall
				'5' -> pixelRender.setPixel(i, FLAG_COLOR) // flag
				else -> println("ERROR: unknown value in map: '$it'") // other!
			}
			
		}
		
//		parse the shooting data
		if (shootingData.trim() != "") {
			
			val shoots = shootingData.split(" ")
			shoots.map { it.split(",") }. // splits apart the values
					map { it.map { it.toInt() } }. // parses them all as ints
					forEach { // then goes through all the array of 3 ints
//						an array that looks like {x, y, deg}
						pixelRender.drawLine(it[0], it[1], it[2])
					}
			
		}
		
//		update tick
		tick++
		
	}
	
	/**
	 * Called when the game has ended
	 * */
	internal fun gameOver(win: String) {
		
		this.gameEnded = true // make sure everyone knows the game is over - stops the tick method
		println("Game is over! Data: $win") // print out info
		
		val winningTeam = if (win.trim() == "DRAW") 0 else win.substring("WIN".length).toInt() // set the winning team
		
		addPattern(Pattern(StaticColor(TEAM_COLOR[winningTeam]), Rectangle(0, 0, width, height))) // render the color of that team
		
	}
	
}
