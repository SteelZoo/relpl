package com.gdd.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.gdd.presentation.base.BaseActivity
import com.gdd.presentation.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "SplashActivity_Genseong"
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(
    ActivitySplashBinding::inflate
) {
    @Inject
    lateinit var prefManager: PrefManager

    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        prefManager.getAutoLoginState().let {
            if (it){
                val userId = prefManager.getUserId()
                viewModel.autoLogin(userId)
            }
            else{
                Handler().postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2500)
            }
        }

        viewModel.autoLogin.observe(this){
            if (it.isSuccess){
                Handler().postDelayed({
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("user", it.getOrNull())
                    }
                    showToast("자동 로그인 되었습니다")
                    startActivity(intent)
                    finish()
                }, 2000)
            }else{
                Handler().postDelayed({
                    val intent = Intent(this, LoginActivity::class.java).apply {
                        putExtra("user", it.getOrNull())
                    }
                    showToast("로그인 정보가 만료되었습니다. 다시 로그인해 주세요")
                    startActivity(intent)
                    finish()
                }, 2000)
            }
        }

    }
}