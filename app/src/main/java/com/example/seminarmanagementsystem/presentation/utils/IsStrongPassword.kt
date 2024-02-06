package com.example.seminarmanagementsystem.presentation.utils

fun isStrongPassword(password: String): Boolean {
    val minLength = 8
    val minLowercase = 1
    val minUppercase = 1
    val minNumbers = 1
    val minSymbols = 1
    val pointsPerUnique = 1.0
    val pointsPerRepeat = 0.5
    val pointsForContainingLower = 10.0
    val pointsForContainingUpper = 10.0
    val pointsForContainingNumber = 10.0
    val pointsForContainingSymbol = 10.0

    val uniqueChars = password.toSet().size.toDouble()
    val passwordLength = password.length.toDouble()
    var score = 0.0

    if (passwordLength >= minLength) {
        score += pointsPerUnique * uniqueChars
        score += pointsPerRepeat * (passwordLength - uniqueChars)
        score += pointsForContainingLower * (password.count { it.isLowerCase() }.toDouble())
        score += pointsForContainingUpper * (password.count { it.isUpperCase() }.toDouble())
        score += pointsForContainingNumber * (password.count { it.isDigit() }.toDouble())
        score += pointsForContainingSymbol * (password.count { !it.isLetterOrDigit() }.toDouble())


    }
    if (score >= minLowercase + minUppercase + minNumbers + minSymbols) {
        return true
    } else {
        return false
    }
}