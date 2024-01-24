package com.gdd.presentation.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isNotEmpty
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import com.gdd.presentation.MainActivity
import com.gdd.presentation.R
import com.gdd.presentation.base.BaseFragment
import com.gdd.presentation.databinding.FragmentProfileBinding
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ProfileFragment_Genseong"

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::bind, R.layout.fragment_profile
) {
    private lateinit var mainActivity: MainActivity
    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var pwChangeDialog: AlertDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = _activity as MainActivity

        initView()
        registerListener()
        registerObserver()
    }

    private fun initView(){
    }

    private fun registerListener(){
        binding.llPwChange.setOnClickListener{
            showDeleteGroupDialog()
        }
    }

    private fun registerObserver(){

    }

    @SuppressLint("MissingInflatedId")
    private fun showDeleteGroupDialog(){
        val builder = AlertDialog.Builder(mainActivity)
        val view = LayoutInflater.from(requireContext()).inflate(
            R.layout.dialog_change_pw, mainActivity.findViewById(R.id.cl_change_pw_dialog)
        )

        val etOriginalPw = view.findViewById<TextInputLayout>(R.id.et_original_pw)
        val etNewPw = view.findViewById<TextInputLayout>(R.id.et_new_pw)
        val etNewPwRe = view.findViewById<TextInputLayout>(R.id.et_new_pw_re)
        val btnCancel = view.findViewById<TextView>(R.id.tv_cancel)
        val btnSave = view.findViewById<TextView>(R.id.tv_change)

        builder.setView(view)
        pwChangeDialog = builder.create()
        pwChangeDialog.apply {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
        }.show()

        etNewPw.editText!!.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()){
                    viewModel.isValidPw(p0!!.toString().trim())
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        //비밀번호 변경 다이얼로그 새로운 비밀번호 유효성 live data
        viewModel.pwValidateResult.observe(viewLifecycleOwner){
            if (it){
                etNewPw.helperText = resources.getString(R.string.signup_pw_et_valid)
            }else{
                etNewPw.error = resources.getString(R.string.signup_all_et_err)
            }
        }


        btnCancel.setOnClickListener {
            pwChangeDialog.dismiss()
        }

        btnSave.setOnClickListener {
            Log.d(TAG, "showDeleteGroupDialog: ${etOriginalPw.editText!!.text}, ${etNewPw.editText!!.text}, ${etNewPwRe.editText!!.text}")
            if (etOriginalPw.editText!!.text.isNotEmpty() && etNewPw.editText!!.text.isNotEmpty() && etNewPwRe.editText!!.text.isNotEmpty()){
                if (viewModel.pwValidateResult.value == true){
                    if (etNewPw.editText!!.text.toString() == etNewPwRe.editText!!.text.toString()){
                        //비밀번호 변경 로직 호출
                    }
                    else{
                        showToast(resources.getString(R.string.signup_repw_et_err))
                    }
                }else{
                    showToast(resources.getString(R.string.all_invalid_input))
                }
            }else{
                showToast(resources.getString(R.string.all_input_everything))
            }
        }
    }
}