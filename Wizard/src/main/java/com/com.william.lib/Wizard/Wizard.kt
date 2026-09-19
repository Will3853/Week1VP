package com.william.lib

class Wizard(var name: String) {
    var maxHp = 50
    var hp = 50
    var maxMana = 30
    var mana = 30
    var kills = 0
    var manaPotions = 5
    var healthPotions = 5
    var isStrongWizard = false
    var lifesteal = 0

    fun getDamage(): Int {
        return if (isStrongWizard) {
            15
        } else {
            10
        }
    }
    fun getSpellCost(): Int {
        return 10
    }
    fun attack(enemy: Enemy, spellType: String)
    {
        if (mana < getSpellCost()) {
            println("Not enough mana!")
            return
        }
        mana -= getSpellCost()
        var damage = getDamage()
        if (spellType == "Fire" && enemy.type == "Grass") {
            damage *= 2
            println("Type advantage!")
        } else if (spellType == "Water" && enemy.type == "Fire") {
            damage *= 2
            println("Type advantage!")
        } else if (spellType == "Grass" && enemy.type == "Water") {
            damage *= 2
            println("Type advantage!")
        }
        enemy.hp -= damage
        println(
            "$spellType Attack dealt $damage damage!"
        )

        if (isStrongWizard) {
            hp += lifesteal
            if (hp > maxHp) {
                hp = maxHp
            }

            println(
                "Lifesteal restored $lifesteal HP!"
            )
        }
    }
    fun drinkHealthPotion() {
        if (healthPotions <= 0) {
            println("You don't have any Health Potions!")
            return
        }
        healthPotions--
        hp += 25
        if (hp > maxHp) {
            hp = maxHp
        }
        println(
            "Health Potion restored your HP!"
        )
    }
    fun drinkManaPotion() {
        if (manaPotions <= 0) {
            println("You don't have any Mana Potions!")
            return
        }
        manaPotions--
        mana += 15
        if (mana > maxMana) {
            mana = maxMana
        }
        println(
            "Mana Potion restored your Mana!"
        )
    }
    fun takeDamage(damage: Int) {

        hp -= damage

        if (hp < 0) {
            hp = 0
        }

        println(
            "$name received $damage damage!"
        )
    }
    fun checkEvolution() {

        if (!isStrongWizard && kills >= 5) {

            isStrongWizard = true

            maxHp = 75
            hp = 75

            maxMana = 45
            mana = 45

            lifesteal = 1

            println()
            println("================================")
            println("     YOU BECAME A STRONG WIZARD!")
            println("================================")
            println()

            println("Your HP increased to 75!")
            println("Your Mana increased to 45!")
            println("Your damage increased by 1.5x!")
            println("You gained Lifesteal!")
        }
    }
}