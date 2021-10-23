package br.com.molero.motivation.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.molero.motivation.R
import br.com.molero.motivation.infra.MotivationConstants
import br.com.molero.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) supportActionBar!!.hide() // esconder Nome do APP

        buttonSave.setOnClickListener(this)

        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeString("", "")

        verifyName()
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSave) handleSave()

    }

    private fun verifyName(){
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if(name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish() // "matar" splash activity da memoria
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()
        if (name != "") {
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // "matar" splash activity da memoria
        } else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }

    }
}