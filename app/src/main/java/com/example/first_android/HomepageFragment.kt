package com.example.first_android

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.first_android.databinding.FragmentHomepageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomepageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

        binding.logoutButton.setOnClickListener{
            findNavController().navigate(R.id.action_HomepageFragment_to_FirstFragment)
        }

        binding.submitButton.setOnClickListener{
            if (isValidate()){
                Toast.makeText(this.context?.applicationContext, "validated", Toast.LENGTH_SHORT).show()
                binding.successSubmitTextView.text = "submit form success!"

                binding.userName.setText("")
                binding.userNameTextInputLayout.isErrorEnabled = false
                binding.email.setText("")
                binding.emailTextInputLayout.isErrorEnabled = false
                binding.password.setText("")
                binding.passwordTextInputLayout.isErrorEnabled = false
                binding.confirmPassword.setText("")
                binding.confirmPasswordTextInputLayout.isErrorEnabled = false
            }
        }

    }

    private fun isValidate(): Boolean {
        return validateUsername() &&
                validateEmail() &&
                validatePassword() &&
                validateConfirmPassword()
    }

    private fun setupListeners() {
        binding.userName.addTextChangedListener(TextFieldValidation(binding.userName))
        binding.email.addTextChangedListener(TextFieldValidation(binding.email))
        binding.password.addTextChangedListener(TextFieldValidation(binding.password))
        binding.confirmPassword.addTextChangedListener(TextFieldValidation(binding.confirmPassword))
    }

    inner class TextFieldValidation(private val view: View): TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            when (view.id){
                R.id.userName -> {
                    validateUsername()
                }
                R.id.email -> {
                    validateEmail()
                }
                R.id.password -> {
                    validatePassword()
                }
                R.id.confirmPassword -> {
                    validateConfirmPassword()
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {}


    }

    private fun validateConfirmPassword(): Boolean {
        when{
            binding.confirmPassword.text.toString().trim().isEmpty() -> {
                binding.confirmPasswordTextInputLayout.error = "Required Field!"
                binding.confirmPassword.requestFocus()
                return false
            }
            binding.confirmPassword.text.toString() != binding.password.text.toString() -> {
                binding.confirmPasswordTextInputLayout.error = "Password doesn't match"
                binding.confirmPassword.requestFocus()
                return false
            }
            else -> {
                binding.confirmPasswordTextInputLayout.isErrorEnabled = false
                return true
            }
        }

    }

    private fun validatePassword(): Boolean{
        when{
            binding.password.text.toString().trim().isEmpty() -> {
                binding.passwordTextInputLayout.error = "Required Field!"
                binding.password.requestFocus()
                return false
            }
            binding.password.text.toString().length < 6 -> {
                binding.passwordTextInputLayout.error = "password can not be less than 6"
                binding.password.requestFocus()
                return false
            }
            else -> {
                binding.passwordTextInputLayout.isErrorEnabled = false
                return true
            }
        }
    }

    private fun validateEmail(): Boolean {
        when{
            binding.email.text.toString().trim().isEmpty() -> {
                binding.emailTextInputLayout.error = "Required Field!"
                binding.email.requestFocus()
                return false
            }
            !isValidEmail(binding.email.text.toString()) -> {
                binding.emailTextInputLayout.error = "Invalid Email!"
                binding.email.requestFocus()
                return false
            }
            else -> {
                binding.emailTextInputLayout.isErrorEnabled = false
                return true
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validateUsername(): Boolean {
        when {
            binding.userName.text.toString().trim().isEmpty() -> {
                binding.userNameTextInputLayout.error = "Required Field!"
                binding.userName.requestFocus()
                return false
            }
            binding.userName.text.toString().contains(" ") -> {
                binding.userNameTextInputLayout.error = "Can not contain space"
                binding.userName.requestFocus()
                return false
            }
            else -> {
                binding.userNameTextInputLayout.isErrorEnabled = false
                return true
            }
        }
    }


}


