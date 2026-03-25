package edu.temple.dicethrow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO 1: Load fragment(s)
            - Show _only_ ButtonFragment if portrait
            - show _both_ fragments if Landscape
          */

        // Safety check: only perform the initial transaction if the app is starting fresh.
        // This prevents overlapping fragments during screen rotation.
        if (savedInstanceState == null) {
            if (findViewById<View>(R.id.container2) == null) {
                // Portrait logic
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, ButtonFragment())
                    .commit()
            } else {
                // Landscape logic
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, ButtonFragment())
                    .replace(R.id.container2, DieFragment())
                    .commit()
            }
        }
    } // onCreate ends here


    //TODO 2: switch fragments if die rolled and in portrait
      //(no need to switch fragments if Landscape)

    override fun buttonClicked() {
        // If container2 is missing, we are in Portrait and must swap the fragments
        if (findViewById<View>(R.id.container2) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, DieFragment())
                // Requirement: place on BackStack so it can be reversed
                .addToBackStack(null)
                .commit()
        }
        // Note: In Landscape, the DieFragment is already visible in container2.
        // Because you are using a ViewModel, the DieFragment will observe
        // the change and update itself automatically!
    }
}