package com.william.lib
fun main() {

    println("================================")
    println("       WIZARD ADVENTURE!")
    println("================================")
    println()

    println("What's your name? ")
    print("Name: ")
    var name = readlnOrNull()?.trim()
    while (name.isNullOrEmpty()) {

        println(
            "Please Enter a Valid Name!"
        )

        print("What's your name? ")

        name =
            readlnOrNull()?.trim()
    }

    println()

    println("Good luck, $name! You're gonna need it!")
    val wizard = Wizard(name)
    while (wizard.hp > 0) {
        println()
        println("What're you going to do?")
        println("1. View Stats")
        println("2. Enter battle")
        println("3. Exit")

        print("Choose: ")

        val choice = readlnOrNull()

        when (choice) {
            "1" -> {
                viewStats(wizard)
            }
            "2" -> {
                enterBattle(wizard)
            }

            "3" -> {
                println("Thanks for playing!")
                break
            }

            else -> {
                println("Invalid choice!")
            }
        }
    }

    if (wizard.hp <= 0) {
        println()
        println("================================")
        println("            GAME OVER")
        println("================================")
    }
}
fun createEnemy(): Enemy {
    val types = listOf("Fire", "Water", "Grass")
    val randomType = types.random()
    return Enemy(randomType)
}
fun viewStats(wizard: Wizard) {
    while (true) {
        println()
        println("================================")
        println("       ${wizard.name}'s STATS")
        println("================================")

        println("HP: ${wizard.hp}/${wizard.maxHp}")
        println("Mana: ${wizard.mana}/${wizard.maxMana}")
        println("Kills needed to evolve: " + "${wizard.kills}/5")
        println("Mana Potions held: " + wizard.manaPotions)
        println("Health Potions held: " + wizard.healthPotions)
        if (wizard.isStrongWizard) {
            println("Lifesteal: ${wizard.lifesteal}")
        }
        println()
        println("1. Drink Mana Potion")
        println("2. Drink Health Potion")
        println("3. Rename self")
        println("4. Back")
        print("Choose: ")
        val choice = readlnOrNull()?.toIntOrNull()
        when (choice) {
            1 -> {
                wizard.drinkManaPotion()
            }


            2 -> {
                wizard.drinkHealthPotion()
            }

            3 -> {

                print("Enter new name: ")

                val newName =
                    readlnOrNull()?.trim()

                if (newName.isNullOrEmpty()) {

                    println(
                        "Name cannot be empty!"
                    )

                } else {

                    wizard.name = newName

                    println(
                        "Your name is now $newName!"
                    )
                }
            }

            4 -> {
                return
            }
            else -> {
                println("Invalid choice!")
            }
        }
    }
}

fun enterBattle(wizard: Wizard) {
    val enemy = createEnemy()
    println()
    println("================================")
    println("             BATTLE")
    println("================================")

    println("${wizard.name}")

    println("HP: ${wizard.hp}/${wizard.maxHp}")

    println("Mana: ${wizard.mana}/${wizard.maxMana}")

    println("HP Potions: ${wizard.healthPotions}")

    println("MP Potions: ${wizard.manaPotions}")

    println()

    println("${enemy.type}mon")

    println("HP: ${enemy.hp}")

    println("Type: ${enemy.type}")

    while (true) {
        if (wizard.hp <= 0) {

            println()
            println("================================")
            println("          YOU DIED")
            println("================================")

            return
        }

        if (enemy.hp <= 0) {
            println()
            println("You defeated the ${enemy.type}mon!")
            wizard.kills++

            println(
                "Kills: ${wizard.kills}/5"
            )

            if (wizard.isStrongWizard) {

                wizard.lifesteal++

                println(
                    "Lifesteal increased to " +
                            "${wizard.lifesteal}!"
                )
            }
            wizard.checkEvolution()

            return
        }

        println()
        println("--------------------------------")

        println("What will you do?")

        println("1. Fire Attack")
        println("2. Water Attack")
        println("3. Grass Attack")
        println("4. Drink potion")
        println("5. Run")

        print("Choose: ")

        var choice = readlnOrNull()?.toIntOrNull() ?: 0
        while (wizard.mana < 10 && choice > 0 && choice < 4) {
                println("Not enough mana!")
                print("Choose: ")
                choice = readlnOrNull()?.toIntOrNull() ?: 0
        }
        when (choice) {

            1 -> {
                wizard.attack(enemy, "Fire")
                if (enemy.hp > 0) {
                    enemy.attack(wizard)
                }
            }

            2 -> {
                wizard.attack(enemy, "Water")
                if (enemy.hp > 0) {
                    enemy.attack(wizard)
                }
            }

            3 -> {
                wizard.attack(enemy, "Grass")
                if (enemy.hp > 0) {
                    enemy.attack(wizard)
                }
            }

            4 -> {
                println()
                println("1. Health Potion")
                println("2. Mana Potion")

                print("Choose: ")

                val potion = readlnOrNull()

                when (potion) {

                    "1" -> {
                        wizard.drinkHealthPotion()
                    }

                    "2" -> {
                        wizard.drinkManaPotion()
                    }

                    else -> {
                        println(
                            "Invalid choice!"
                        )
                    }
                }
                if (enemy.hp > 0) {
                    enemy.attack(wizard)
                }
            }

            5 -> {
                println("You ran away from the battle!")
                return
            }

            else -> {
                println("Invalid choice!")
            }
        }

        println()
        println("${wizard.name} HP: " + "${wizard.hp}/${wizard.maxHp}")
        println("${wizard.name} Mana: " + "${wizard.mana}/${wizard.maxMana}")
        println("${enemy.type}mon HP: " + "${enemy.hp}")
    }
}

