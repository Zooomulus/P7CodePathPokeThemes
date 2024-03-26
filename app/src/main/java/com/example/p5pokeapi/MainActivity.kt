package com.example.p5pokeapi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var pokemonImage: ImageView
    private lateinit var pokemonName: TextView
    private lateinit var pokemonAbility: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokemonImage = findViewById(R.id.pokemon_image)
        pokemonName = findViewById(R.id.pokemon_name)
        pokemonAbility = findViewById(R.id.pokemon_ability)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            getNextPokemon()
        }
    }
    private fun getNextPokemon() {
        val client = AsyncHttpClient()
        val url = "https://pokeapi.co/api/v2/pokemon/${(1..898).random()}" // Random Pokemon ID between 1 and 898

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>?, response: JSONObject?) {
                //updating info
                val abilities = response?.getJSONArray("abilities")
                val ability = abilities?.getJSONObject(0)?.getJSONObject("ability")
                val abilityName = ability?.getString("name")
                val sprites = response?.getJSONObject("sprites")
                val imageUrl = sprites?.getString("front_default")

                pokemonName.text = response?.getString("name")
                pokemonAbility.text = abilityName

                //using glide here
                Glide.with(this@MainActivity)
                    .load(imageUrl)
                    .into(pokemonImage)
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>?, throwable: Throwable, errorResponse: JSONObject?) {
                Log.d("Pokemon Error", throwable?.message ?: "Unknown error")
            }
        })
    }
}