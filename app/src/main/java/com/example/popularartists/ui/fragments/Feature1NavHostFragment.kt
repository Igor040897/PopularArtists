package com.example.popularartists.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class Feature1NavHostFragment
@Inject
constructor(
    private val feature1FragmentFactory: Feature1FragmentFactory
) : NavHostFragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        childFragmentManager.fragmentFactory = feature1FragmentFactory
        super.onAttach(context)
    }

    companion object {

        const val KEY_GRAPH_ID = "android-support-nav:fragment:graphId"

        @JvmStatic
        fun create(
            feature1FragmentFactory: Feature1FragmentFactory,
            @NavigationRes graphId: Int = 0
        ): Feature1NavHostFragment {
            var bundle: Bundle? = null
            if (graphId != 0) {
                bundle = Bundle()
                bundle.putInt(KEY_GRAPH_ID, graphId)
            }
            val result = Feature1NavHostFragment(feature1FragmentFactory)
            if (bundle != null) {
                result.arguments = bundle
            }
            return result
        }
    }

}








