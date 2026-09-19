package com.william.lib

class Enemy(val type: String) {
    var hp = 30
    fun attack(wizard: Wizard) {
        println()
        println("$type Monster attacks!")
        wizard.takeDamage(10)
    }
}
