package com.cubidevs.dccomics.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.navArgs
import com.cubidevs.dccomics.R
import com.cubidevs.dccomics.databinding.FragmentDetailBinding
import com.cubidevs.dccomics.main.MainActivity
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity?)?.showIcon()

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)

        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val superheroe = args.superheroe
        val latitud = superheroe.latitud
        val longitud = superheroe.longitud
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            launchMap(latitud, longitud)
        }

        with(detailBinding){
            nameTextView.text = superheroe.name
            aliasTextView.text = superheroe.alias
            cityTextView.text = superheroe.city
            heightTextView.text = superheroe.height.toString()
            powersTextView.text = superheroe.powers
            Picasso.get().load(superheroe.urlPicture).into(pictureImageView)

        }
    }

    private fun launchMap(lat: String, lon: String) {
        val gmmIntentUri = Uri.parse("geo:$lat,$lon")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}