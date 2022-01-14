package com.julio.pokedexwithpokeapiv2.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.databinding.FragmentPokemonDetailBinding
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import com.julio.pokedexwithpokeapiv2.ui.PokemonEvolutionsAdapter
import com.julio.pokedexwithpokeapiv2.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class PokemonDetailFragment : Fragment() {

    private var _binding : FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!


    private val args : PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView : ImageView = binding.pokemonDetailImage
        val textViewName : TextView = binding.pokemonDetailName
        val textViewId : TextView = binding.pokemonDetailId
        val buttonBackToHome : ImageButton = binding.btnBackToHome
        val recyclerViewEvolutions : RecyclerView = binding.recyclerViewEvolutions
        val textViewMaximumEvolution : TextView = binding.pokemonDetailMaximumEvolutionMessage
        val imageViewMaximumEvolution : ImageView = binding.pokemonDetailImagePokemonMaximumEvolution

        val hpProgressBar : ProgressBar = binding.hpProgressBarCircle
        val hpPercentage : TextView = binding.hpPercentage

        val attackProgressBar : ProgressBar = binding.attackProgressBarCircle
        val attackPercentage : TextView = binding.attackPercentage

        val defenseProgressBar : ProgressBar = binding.defenseProgressBarCircle
        val defensePercentage : TextView = binding.defensePercentage

        val speedProgressBar : ProgressBar = binding.speedProgressBarCircle
        val speedPercentage : TextView = binding.speedPercentage

        val specialAttackProgressBar : ProgressBar = binding.specialAttackProgressBarCircle
        val specialAttackPercentage : TextView = binding.specialAttackPercentage

        val specialDefenseProgressBar : ProgressBar = binding.specialDefenseProgressBarCircle
        val specialDefensePercentage : TextView = binding.specialDefensePercentage

        val mainViewModel : MainViewModel by viewModel{
            parametersOf(MainRepository(view.context))
        }


        //TODO: Replace these simulated numbers with real numbers coming from the API
        updateProgressBar(hpProgressBar, hpPercentage, 70)
        updateProgressBar(attackProgressBar, attackPercentage, 30)
        updateProgressBar(defenseProgressBar,defensePercentage, 45)
        updateProgressBar(speedProgressBar, speedPercentage, 80)
        updateProgressBar(specialAttackProgressBar, specialAttackPercentage, 95)
        updateProgressBar(specialDefenseProgressBar, specialDefensePercentage, 57)


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



    fun updateProgressBar (progressBar : ProgressBar, textView: TextView, percentage: Int){

        //Set animation in progress bar
        val animation = ObjectAnimator.ofInt(progressBar, "progress", percentage)
        animation.setDuration(1250)
        animation.setInterpolator(DecelerateInterpolator())
        animation.start()

        //Update text view into progress bar
        textView.text = "$percentage%"
    }

}