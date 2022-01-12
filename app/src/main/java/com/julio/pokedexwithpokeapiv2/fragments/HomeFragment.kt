package com.julio.pokedexwithpokeapiv2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.dao.PokemonDaoEntity
import com.julio.pokedexwithpokeapiv2.databinding.FragmentHomeBinding
import com.julio.pokedexwithpokeapiv2.databinding.FragmentPokemonDisplayCardBinding
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import com.julio.pokedexwithpokeapiv2.ui.PokemonAdapter
import com.julio.pokedexwithpokeapiv2.utils.ImageDaoService
import com.julio.pokedexwithpokeapiv2.viewmodel.MainViewModel
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val view = binding.root

        return view




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel : MainViewModel by viewModel{
            parametersOf(MainRepository(view.context))
        }

        //Testing get pokemon evolutions
        mainViewModel.getPokemonEvolutions()


        val recyclerViewHomePokemon = view.findViewById<RecyclerView>(R.id.recycler_view_home_pokemon)


        mainViewModel.getPokemonById(35)

        mainViewModel.getPokemonList()

        mainViewModel.pokemonListResponse.observe(this, Observer {




            mainViewModel.updateLocalDb(it!!)



            val byteArray : ByteArray = byteArrayOf(1)


            val mockPokemon = PokemonDaoEntity(
                0,
                "mock",
                "mock",
                2,
                byteArray

            )

            //mainViewModel.insertPokemonIntoDb(mockPokemon)


            val pokeTest = it!!.results[0]

            val name = pokeTest.name
            Log.d("First pokemon" , name)


            val pokemon = pokeTest



            var pokemonId = pokemon.url.dropLastWhile { it.digitToIntOrNull() == null}
            pokemonId = pokemonId.dropWhile { it.digitToIntOrNull() == null}
            val list = pokemonId.split("pokemon")
            pokemonId = list[1].replace("/", "")

            Log.d("pokemon ID", pokemonId)


            recyclerViewHomePokemon.adapter = PokemonAdapter(view.context, it.results)






        })







        mainViewModel.pokemonImageLiveData.observe(this, Observer {

            Log.d("pegou imagem", mainViewModel.pokemonImageLiveData.value.toString())

        })



    }



}