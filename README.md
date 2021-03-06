PlaceAutocomplete
========
Google Places Api Implementation

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/34c0864ec96f4ce8a094a60d040e7ff7)](https://www.codacy.com/app/Drabu/PlaceAutocomplete?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Drabu/PlaceAutocomplete&amp;utm_campaign=Badge_Grade)
[![](https://jitpack.io/v/Drabu/PlaceAutocomplete.svg)](https://jitpack.io/#Drabu/PlaceAutocomplete)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

After Google came up with the update that Play Services version of the Places SDK for Android (in Google Play Services 16.0.0) is deprecated as of January 29, 2019, and will be turned off on July 29, 2019.
which includes PlaceAutoComplete also to be removed from the library.I was using the same in our client project but after getting this news we had to immidiately switch to an alternative where i endded up creating this library,  PlacesAutocomplete provides you with an elegant user interface to choose a location and works the exact same as PlaceAutoComplete.

You can provide your own custom title message for the view 

You can also provide your current location and radius for location based results

This Kotlin Library is build with MVVM Archetecture and runs on top of RxJava 2. The library uses various Rx operators like debounce, throttlefirst operators to reduce network calls and supports landscape orientation   


### How it works

<p align="center">
    <img src="dry_run.gif" alt="Demonstartion image."/>
</p>

Configuration
-------------


Add it in your root build.gradle at the end of repositories:
    
    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
     }
Add the dependency: 

    dependencies {
    		//copy the version from jitpack badge	
		implementation 'com.github.Drabu:PlaceAutocomplete:x.x.x'
	 }

Mandatory Field: 
        
        val intent = Intent(this, SearchPlaceActivity::class.java)
        intent.putExtra(
            SearchPlacesStatusCodes.CONFIG,
            SearchPlaceActivity.Config(apiKey = API_KEY)
        )
	
Optional parameteres: 
        
    val intent = Intent(this, SearchPlaceActivity::class.java)
        intent.putExtra(
            SearchPlacesStatusCodes.CONFIG,
            SearchPlaceActivity.Config(
                apiKey = API_KEY,
                searchBarTitle = "Enter Source Location",
                location = "12.9716,77.5946",
                enclosingRadius = "500"
            )
        )


For build version greater LOLLIPOP, you can use Activity Transition like this: 

	val pair = Pair.create(searchLocationET as View, SearchPlacesStatusCodes.PLACEHOLDER_TRANSITION)
	val options = ActivityOptions.makeSceneTransitionAnimation(this, pair).toBundle()
	startActivityForResult(intent, 700, options)


#Example Kotlin Class: 

    import com.oneclickaway.opensource.placeautocomplete.api.bean.place_details.PlaceDetails
    import com.oneclickaway.opensource.placeautocomplete.components.SearchPlacesStatusCodes
    import com.oneclickaway.opensource.placeautocomplete.ui.SearchPlaceActivity

    class ExampleLocationSearch : AppCompatActivity() {

     lateinit var searchLocationET: EditText
     lateinit var placeDetailsTV: TextView
     var REQUEST_CODE = 700

     var API_KEY = BuildConfig.ApiKey

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_location_search)

        searchLocationET = findViewById(R.id.searchLocationBTN)
        placeDetailsTV = findViewById(R.id.resultPlaceDetailsTV)

        val intent = Intent(this, SearchPlaceActivity::class.java)
        intent.putExtra(
            SearchPlacesStatusCodes.CONFIG,
            SearchPlaceActivity.Config(apiKey = API_KEY, searchBarTitle = "Enter Source Location")
        )

        searchLocationET.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val pair = Pair.create(searchLocationET as View, SearchPlacesStatusCodes.PLACEHOLDER_TRANSITION)
                val options = ActivityOptions.makeSceneTransitionAnimation(this, pair).toBundle()
                startActivityForResult(intent, REQUEST_CODE, options)

            } else {
                startActivityForResult(intent, REQUEST_CODE)
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }


        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val placeDetails = data?.getParcelableExtra<PlaceDetails>(SearchPlacesStatusCodes.PLACE_DATA)

            searchLocationET.setText(placeDetails?.name)

            placeDetailsTV.text = placeDetails.toString()
            Log.i(javaClass.simpleName, "onActivityResult: ${placeDetails}  ")
        }
    }
    }
   
Usage
-----
-Minimum sdk 15
-Returns place information in onActivityResult

License
-----
	MIT License

	Copyright (c) 2019 Burhan ud din

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.

