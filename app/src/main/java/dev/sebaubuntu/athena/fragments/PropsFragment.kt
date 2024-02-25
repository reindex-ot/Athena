/*
 * SPDX-FileCopyrightText: 2023 Sebastiano Barezzi
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.sebaubuntu.athena.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dev.sebaubuntu.athena.recyclerview.PairAdapter
import dev.sebaubuntu.athena.recyclerview.PairLayoutManager
import dev.sebaubuntu.athena.viewmodels.PropsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PropsFragment : RecyclerViewFragment() {
    // View models
    private val model by viewModels<PropsViewModel>()

    // Recyclerview
    private val pairAdapter by lazy { PairAdapter() }
    private val gridLayoutManager by lazy { PairLayoutManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = pairAdapter
        recyclerView.layoutManager = gridLayoutManager

        viewLifecycleOwner.lifecycleScope.launch {
            val props = withContext(Dispatchers.IO) {
                model.props.toList()
            }
            pairAdapter.submitList(props)
        }
    }
}
