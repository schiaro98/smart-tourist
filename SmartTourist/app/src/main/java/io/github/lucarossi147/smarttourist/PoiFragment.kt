package io.github.lucarossi147.smarttourist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import io.github.lucarossi147.smarttourist.Constants.ARG_USER
import io.github.lucarossi147.smarttourist.data.model.LoggedInUser
import io.github.lucarossi147.smarttourist.data.model.POI

private const val ARG_POI = "poi"

/**
 * A simple [Fragment] subclass.
 * Use the [PoiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PoiFragment : Fragment() {
    private var poi: POI? = null
    private var user: LoggedInUser? = null
    private var signEditText: EditText? = null
    private var signButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            poi = it.getParcelable(ARG_POI)
            user = it.getParcelable(ARG_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signButton = view.findViewById(R.id.signButton)
        signEditText = view.findViewById(R.id.editTextTextSignYourself)
        if (poi?.visited != null && poi?.visited == false) {
            signEditText?.visibility = View.VISIBLE
            signButton?.visibility = View.VISIBLE
        }

        val tv: TextView = view.findViewById(R.id.poiInfoTextView)
        tv.text = poi?.info

        signButton?.setOnClickListener {
            //remove sign yourself from UI
            // TODO: send signature and comment to server
            //if result is success remove editText and make a toast
            signEditText?.visibility = View.GONE
            signButton?.visibility = View.GONE

        }
        val goToSignatureButton: Button = view.findViewById(R.id.goToSignaturesButton)
        goToSignatureButton.setOnClickListener {
            // TODO: maybe ask to server for signature asynchronously in the onCreate and make
            //  fragment take a list as argument so user does not have to wait
            view.findNavController().navigate(R.id.signaturesFragment)
        }
        val backToMapButton: Button = view.findViewById(R.id.backToMapButton)
        backToMapButton.setOnClickListener {
            view.findNavController().navigate(R.id.mapsFragment)
        }
        poi?.pictures?.forEach {
            val iv = ImageView(context)
            val linearLayout: LinearLayout = view.findViewById(R.id.images_linear_layout)
            linearLayout.addView(iv)
            Picasso.get()
                .load(it)
                .resize(0, 400)
                .into(iv)
        }


    }
    companion object {
        /**
         * @param poi Point of interest to display.
         * @return A new instance of fragment PoiFragment.
         */
        @JvmStatic
        fun newInstance(poi: POI) =
            PoiFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_POI, poi)
                }
            }
    }
}