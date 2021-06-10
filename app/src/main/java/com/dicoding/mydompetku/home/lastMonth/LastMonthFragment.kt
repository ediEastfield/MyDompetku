package com.dicoding.mydompetku.home.lastMonth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mydompetku.R
import com.dicoding.mydompetku.core.data.Resource
import com.dicoding.mydompetku.core.ui.CashflowAdapter
import com.dicoding.mydompetku.databinding.FragmentLastMonthBinding
import com.dicoding.mydompetku.detail.cashflow.DetailCashflowActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class LastMonthFragment : Fragment() {

    private val lastMonthViewModel: LastMonthViewModel by viewModels()

    private var _binding: FragmentLastMonthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLastMonthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val cashflowAdapter = CashflowAdapter()
            cashflowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailCashflowActivity::class.java)
                intent.putExtra(DetailCashflowActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, -1)
            val dateFormat = SimpleDateFormat("MM", Locale.getDefault())
            val month = dateFormat.format(calendar.time)

            lastMonthViewModel.getLastMonthCashflow(month).observe( viewLifecycleOwner, { cashflow ->
                if (cashflow != null) {
                    when (cashflow) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            cashflowAdapter.setData(cashflow.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = cashflow.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvCashflow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = cashflowAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}