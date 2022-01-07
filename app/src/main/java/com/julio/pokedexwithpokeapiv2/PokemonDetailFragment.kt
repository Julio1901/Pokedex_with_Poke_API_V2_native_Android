package com.julio.pokedexwithpokeapiv2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide


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

        Glide.with(view).load(args.imageUrl).into(imageView)
        textViewName.text = args.name
        textViewId.text = args.id

        buttonBackToHome.setOnClickListener {
            val action = PokemonDetailFragmentDirections.actionFragmentDetailsToHome()
            view.findNavController().navigate(action)
        }

    }

}