package com.volie.lolguidestats

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.volie.lolguidestats.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _mBinding: ActivityMainBinding? = null
    private val mBinding get() = _mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar_color)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        mBinding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            with(mBinding.bottomNavigationView) {
                visibility = when (destination.id) {
                    R.id.championsDetailsFragment -> View.GONE
                    R.id.selectLanguageFragment -> View.GONE
                    R.id.summonerSpellFragment -> View.GONE
                    R.id.itemFragment -> View.GONE
                    R.id.itemDetailsFragment -> View.GONE
                    R.id.profileIconFragment -> View.GONE
                    R.id.rankFragment -> View.GONE
                    R.id.rankDetailsFragment -> View.GONE
                    R.id.mapsFragment -> View.GONE
                    R.id.gameModeFragment -> View.GONE
                    R.id.modeDetailsFragment -> View.GONE
                    R.id.missionAssetsFragment -> View.GONE
                    else -> View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}