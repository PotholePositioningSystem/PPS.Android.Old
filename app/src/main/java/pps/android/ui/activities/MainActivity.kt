package pps.android.ui.activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import pps.android.BuildConfig
import pps.android.R
import pps.android.ui.adapters.ViewPagerAdapter
import pps.android.ui.fragments.DriverFragment
import pps.android.ui.fragments.PotholesFragment
import pps.android.ui.fragments.ProfileFragment
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var adapter: ViewPagerAdapter

    private val RC_SIGN_IN = 123

    @Inject
    lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager()
        setupNavBar()
    }

    private fun setupNavBar() {
        var prevMenuItem: MenuItem? = null

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_potholes -> viewPager!!.currentItem = 0
                R.id.action_driver -> viewPager!!.currentItem = 1
                R.id.action_profile -> viewPager!!.currentItem = 2
            }
            false
        }

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    bottomNavigation.menu.getItem(0).isChecked = false
                }
                bottomNavigation.menu.getItem(position).isChecked = true
                prevMenuItem = bottomNavigation.menu.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }

    private fun setupViewPager() {
        adapter = ViewPagerAdapter(supportFragmentManager)
        val potholesFragment = PotholesFragment()
        val driverFragment = DriverFragment()
        val profileFragment = ProfileFragment()

        adapter.addFragment(potholesFragment)
        adapter.addFragment(driverFragment)
        adapter.addFragment(profileFragment)

        viewPager.adapter = adapter
    }

    fun signIn(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
                        .setAvailableProviders(Arrays.asList(
                                AuthUI.IdpConfig.EmailBuilder().build(),
                                AuthUI.IdpConfig.PhoneBuilder().build(),
                                AuthUI.IdpConfig.GoogleBuilder().build(),
                                AuthUI.IdpConfig.FacebookBuilder().build(),
                                AuthUI.IdpConfig.TwitterBuilder().build()))
                        .build(),
                RC_SIGN_IN)
    }
}
