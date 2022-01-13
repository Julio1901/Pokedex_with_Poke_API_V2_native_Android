package com.julio.pokedexwithpokeapiv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import com.julio.pokedexwithpokeapiv2.ui.PokemonEvolutionsAdapter
import com.julio.pokedexwithpokeapiv2.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class PokemonDetailFragment : Fragment() {


    private val args : PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView : ImageView = view.findViewById(R.id.pokemon_detail_image)
        val textViewName : TextView = view.findViewById(R.id.pokemon_detail_name)
        val textViewId : TextView = view.findViewById(R.id.pokemon_detail_id)
        val buttonBackToHome : Button = view.findViewById(R.id.btn_back_to_home)
        val recyclerViewEvolutions : RecyclerView = view.findViewById(R.id.recycler_view_evolutions)
        val textViewMaximumEvolution : TextView = view.findViewById(R.id.pokemon_detail_maximum_evolution_message)
        val imageViewMaximumEvolution : ImageView = view.findViewById(R.id.pokemon_detail_image_pokemon_maximum_evolution)
        val mainViewModel : MainViewModel by viewModel{
            parametersOf(MainRepository(view.context))
        }

        //Put transparency in imageview while there are pokemon evolutions to display
        imageViewMaximumEvolution.setImageResource(android.R.color.transparent)

        Glide.with(view).load(args.imageUrl).into(imageView)
        textViewName.text = args.name
        textViewId.text = args.id

        buttonBackToHome.setOnClickListener {
            val action = PokemonDetailFragmentDirections.actionFragmentDetailsToHome()
            view.findNavController().navigate(action)
        }

        mainViewModel.getPokemonEvolutions(args.id.toInt())

        mainViewModel.pokemonEvolutions.observe(this, Observer {

            recyclerViewEvolutions.adapter = PokemonEvolutionsAdapter(view.context, mainViewModel.pokemonEvolutions.value!! )

        })

        mainViewModel.pokemonMaximumEvolution.observe(this, Observer {
            textViewMaximumEvolution.text = resources.getText(R.string.maximum_evolution_message)
            val pikachuGif = "https://c.tenor.com/3IACtMvxwdsAAAAC/pikachu-happy.gif"
            Glide.with(view).load(pikachuGif).into(imageViewMaximumEvolution)

        })

    }

}