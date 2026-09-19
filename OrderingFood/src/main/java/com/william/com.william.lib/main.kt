package com.william.test1


fun main() {

    val menu = mutableListOf<MenuItem>()

    val orders = mutableListOf<Order>()

    var id = 1

    while (true) {

        println()
        println("================================")
        println("       ORDERING SYSTEM")
        println("================================")
        println("1. Make Order")
        println("2. View Orders")
        println("3. View Menu")
        println("4. Add Menu")
        println("5. Edit Menu")
        println("6. Delete Menu")
        println("7. Exit")
        println("================================")
        print("Choose: ")

        val choice = readln().toInt()

        when (choice) {

            1 -> {

                if (menu.isEmpty()) {

                    println("Menu is empty.")
                    println("Please add a menu first.")

                    continue
                }

                print("Customer name: ")
                val customerName = readlnOrNull()?.trim()

                if (customerName.isNullOrEmpty()) {
                    println("Error: Customer name cannot be empty.")
                    continue
                }

                val orderItems = mutableListOf<OrderItem>()

                while (true) {

                    println()
                    println("----------- MENU -----------")

                    for (item in menu) {

                        println(
                            "${item.id}. ${item.name} - Rp ${item.price.toInt()}"
                        )
                    }

                    println("0. Finish Order")

                    print("Choose food ID: ")

                    val foodId =
                        readln().toInt()

                    if (foodId == 0) {
                        break
                    }

                    val selectedItem = menu.find { it.id == foodId }

                    if (selectedItem == null) {

                        println("Error: Food item not found.")
                        continue
                    }

                    print("Quantity: ")
                    val quantity = readlnOrNull()?.toIntOrNull()
                    if (quantity == null || quantity <= 0) {

                        println(
                            "Error: Quantity must be greater than 0."
                        )

                        continue
                    }

                    orderItems.add(
                        OrderItem(selectedItem, quantity)
                    )
                    println(
                        "${selectedItem.name} x$quantity added."
                    )
                }

                if (orderItems.isEmpty()) {

                    println("Error: No food selected.")
                    continue
                }

                var total = 0.0

                for (item in orderItems) {

                    total += item.menuItem.price * item.quantity
                }

                val order = Order(customerName, orderItems, total)

                orders.add(order)

                println()
                println("========== ORDER ==========")
                println("$customerName's ORDER")

                for (i in orderItems.indices) {

                    val item = orderItems[i]

                    println("${i + 1}. " + "${item.menuItem.name} " + "x${item.quantity}")

                    println("Rp ${(item.menuItem.price * item.quantity).toInt()}")
                }

                println("---------------------------")
                println("TOTAL: Rp ${total.toInt()}")
                println("Order successfully created!")
            }



            2 -> {

                println()
                println("========== ORDERS ==========")

                if (orders.isEmpty()) {

                    println("No orders have been made.")

                    continue
                }

                for (i in orders.indices) {

                    val order = orders[i]

                    println()
                    println("Order #${i + 1}")
                    println("Customer: ${order.customerName}")

                    for (item in order.items) {
                        println("- ${item.menuItem.name} " + "x${item.quantity}")
                    }

                    println(
                        "Total: Rp ${order.totalPrice.toInt()}"
                    )
                }
            }


            3 -> {

                println()
                println("========== MENU ==========")

                if (menu.isEmpty()) {

                    println("Menu is empty.")
                    continue
                }

                for (item in menu) {

                    println()
                    println("ID: ${item.id}")
                    println("Name: ${item.name}")
                    println("Description: ${item.description}")
                    println("Price: Rp ${item.price.toInt()}")
                }
            }

            4 -> {

                println()
                println("========== ADD MENU ==========")

                print("Food name: ")
                val name = readlnOrNull()?.trim()

                if (name.isNullOrEmpty()) {
                    println("Error: Food name cannot be empty.")
                    continue
                }

                print("Description: ")
                val description = readlnOrNull()?.trim()

                if (description.isNullOrEmpty()) {
                    println("Error: Description cannot be empty.")
                    continue
                }

                print("Price: ")

                val price = readlnOrNull()?.toDoubleOrNull()

                if (price == null || price <= 0) {

                    println("Error: Price must be greater than 0.")
                    continue
                }

                val newItem = MenuItem(id, name, description, price)
                menu.add(newItem)
                id++
                println("Menu item added successfully!")
            }

            5 -> {

                if (menu.isEmpty()) {
                    println("Menu is empty.")
                    continue
                }

                println()
                println("========== EDIT MENU ==========")
                for (item in menu) {

                    println(
                        "${item.id}. ${item.name}"
                    )
                }
                print("Enter food ID: ")

                val id = readlnOrNull()?.toIntOrNull()

                val item = menu.find { it.id == id }

                if (item == null) {
                    println("Error: Food item not found.")
                    continue
                }

                print("New name (${item.name}): ")

                val newName = readlnOrNull()?.trim()

                if (!newName.isNullOrEmpty()) {
                    item.name = newName
                }
                print(
                    "New description (${item.description}): "
                )
                val newDescription = readlnOrNull()?.trim()
                if (!newDescription.isNullOrEmpty()) {
                    item.description = newDescription
                }
                print(
                    "New price (${item.price}): "
                )

                val newPrice = readlnOrNull()
                if (!newPrice.isNullOrEmpty()) {
                    val price = newPrice.toDoubleOrNull()

                    if (price == null || price <= 0) {

                        println(
                            "Error: Invalid price."
                        )

                        continue
                    }
                    item.price = price
                }
                println("Menu updated successfully!")
            }

            6 -> {

                if (menu.isEmpty()) {
                    println("Menu is empty.")
                    continue
                }

                println()
                println("========== DELETE MENU ==========")

                for (item in menu) {
                    println(
                        "${item.id}. ${item.name}"
                    )
                }
                print("Enter food ID: ")
                val id = readlnOrNull()?.toIntOrNull()

                val item = menu.find { it.id == id }

                if (item == null) {
                    println("Error: Food item not found.")
                    continue
                }

                print("Are you sure you want to delete " + "${item.name}? (y/n): ")
                val confirmation =
                    readlnOrNull()

                if (confirmation?.lowercase() == "y") {

                    menu.remove(item)

                    println("Menu deleted successfully!")

                } else {
                    println("Delete cancelled.")
                }
            }
            7 -> {
                println()
                println("Thank you for using FELI!")

                break
            }
            else -> {

                println(
                    "Error: Please choose a number from 1-7."
                )
            }
        }
    }
}
