package com.example.mynewproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment


class LoginFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnmain = view.findViewById<Button>(R.id.mainBtn2)
        btnmain.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        val btnsubmit = view.findViewById<Button>(R.id.submitBtn)
        btnsubmit.setOnClickListener {
            collectUserData()
            Toast.makeText(activity, "Information sent!", Toast.LENGTH_SHORT).show()
            clearUserInputFields()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun collectUserData() {
        val nameEditText = requireView().findViewById<EditText>(R.id.names)
        val fullName = nameEditText.text.toString()
        val ageEditText = requireView().findViewById<EditText>(R.id.age)
        val age = ageEditText.text.toString()
        val phoneEditText = requireView().findViewById<EditText>(R.id.phone)
        val phone = phoneEditText.text.toString()
        val conditionsCheckBox = requireView().findViewById<CheckBox>(R.id.conditions)
        val conditionsChecked = conditionsCheckBox.isChecked
        val radioGroup = requireView().findViewById<RadioGroup>(R.id.radioGroup)
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        val selectedRadioButton = requireView().findViewById<RadioButton>(selectedRadioButtonId)
        var selectedRadioText = ""
        if (selectedRadioButton != null) {
            selectedRadioText = selectedRadioButton.text.toString()
        }
    }

    private fun clearUserInputFields() {
        val nameEditText = requireView().findViewById<EditText>(R.id.names)
        val ageEditText = requireView().findViewById<EditText>(R.id.age)
        val phoneEditText = requireView().findViewById<EditText>(R.id.phone)
        nameEditText.setText("")
        ageEditText.setText("")
        phoneEditText.setText("")
        val conditionsCheckBox = requireView().findViewById<CheckBox>(R.id.conditions)
        conditionsCheckBox.isChecked = false
        val radioGroup = requireView().findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.clearCheck()
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): LoginFragment {
            val fragment = LoginFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
