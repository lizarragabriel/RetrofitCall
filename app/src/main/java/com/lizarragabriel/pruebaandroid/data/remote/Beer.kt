package com.lizarragabriel.pruebaandroid.data.remote

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,
    val image_url: String,
    var favorite: Boolean = false,
    val abv: Float,
    val ibu: Float,
    val target_fg: Float,
    val target_og: Float,
    val volume: Volume,
    val ingredients: Ingredient,
    val food_pairing: List<String>,
    val brewers_tips: String
)
data class Volume(
    val value: Int,
    val unit: String
)

data class Ingredient(
    val malt: List<Malt>,
    val hops: List<Hop>,
    val yeast: String
)

data class Malt(
    val name: String,
    val amount: Amount
)
data class Hop(
    val name: String,
    val amount: Amount,
    val add: String,
    val attribute: String
)
data class Amount(
    val value: Float,
    val unit: String
)

