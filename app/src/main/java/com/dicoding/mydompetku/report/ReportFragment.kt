package com.dicoding.mydompetku.report

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.dicoding.mydompetku.R
import com.dicoding.mydompetku.databinding.FragmentReportBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportFragment : Fragment() {

    private val reportViewModel: ReportViewModel by viewModels()

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            reportViewModel.totalNominal("income").observe(viewLifecycleOwner, { nominal ->
                if (nominal != null) {
                    binding.nominalIncome.text = nominal.toString()
                } else {
                    binding.nominalIncome.text = getString(R.string.text_nominal)
                }
            })

            reportViewModel.reportData("income").observe(viewLifecycleOwner, { reportData ->
                if (reportData != null) {

                    val listPie = ArrayList<PieEntry>()

                    reportData.asSequence().forEach {
                        listPie.add(PieEntry(it.total.toFloat(), it.category))
                    }

                    val pieDataSet = PieDataSet(listPie,"")
                    pieDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

                    val pieData = PieData(pieDataSet)
                    pieData.setValueTextSize(12f)
                    binding.pieChartIncome.data = pieData

                    binding.pieChartIncome.setUsePercentValues(true)
                    binding.pieChartIncome.description.isEnabled = false
                    binding.pieChartIncome.setEntryLabelColor(R.color.colorTextTertiary)
                    binding.pieChartIncome.setEntryLabelTextSize(6f)
                    binding.pieChartIncome.animateY(1400, Easing.EaseInOutQuad)

                }
            })

            reportViewModel.totalNominal("expense").observe(viewLifecycleOwner, { nominal ->
                if (nominal != null) {
                    binding.nominalExpanse.text = nominal.toString()
                } else {
                    binding.nominalExpanse.text = getString(R.string.text_nominal)
                }
            })

            reportViewModel.reportData("expense").observe(viewLifecycleOwner, { reportData ->
                if (reportData != null) {

                    val listPie = ArrayList<PieEntry>()

                    reportData.asSequence().forEach {
                        listPie.add(PieEntry(it.total.toFloat(), it.category))
                    }

                    val pieDataSet = PieDataSet(listPie,"")
                    pieDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

                    val pieData = PieData(pieDataSet)
                    pieData.setValueTextSize(12f)
                    binding.pieChartExpanse.data = pieData

                    binding.pieChartExpanse.setUsePercentValues(true)
                    binding.pieChartExpanse.description.isEnabled = false
                    binding.pieChartExpanse.setEntryLabelColor(R.color.colorTextTertiary)
                    binding.pieChartExpanse.setEntryLabelTextSize(6f)
                    binding.pieChartExpanse.animateY(1400, Easing.EaseInOutQuad)

                }
            })

            reportViewModel.totalNominal("debt").observe(viewLifecycleOwner, { nominal ->
                if (nominal != null) {
                    binding.nominalDebt.text = nominal.toString()
                } else {
                    binding.nominalDebt.text = getString(R.string.text_nominal)
                }
            })

            reportViewModel.totalNominal("loan").observe(viewLifecycleOwner, { nominal ->
                if (nominal != null) {
                    binding.nominalLoan.text = nominal.toString()
                } else {
                    binding.nominalLoan.text = getString(R.string.text_nominal)
                }
            })
            
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}