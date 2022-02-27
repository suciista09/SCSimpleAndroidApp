package com.example.first_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.first_android.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("CutPasteId", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //back button
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        //login button action
        binding.loginButton.setOnClickListener{
            //get email input
            val emailLoginInput = view.findViewById<EditText>(R.id.editTextEmailLogin)
            val emailLoginString = emailLoginInput.text.toString()

            //get password input
            val passwordLoginInput = view.findViewById<EditText>(R.id.editTextPasswordLogin)
            val passwordLoginString = passwordLoginInput.text.toString()

            //add some login
            if(emailLoginString == "suci@test.com" && passwordLoginString == "myPassword"){
                findNavController().navigate(R.id.action_SecondFragment_to_HomepageFragment)
            }else{
                //view.findViewById<TextView>(R.id.failedLoginTextview).requestFocus();
                view.findViewById<TextView>(R.id.failedLoginTextview).error = ""
                view.findViewById<TextView>(R.id.failedLoginTextview).text = "Error! email & password doesn't match"

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}