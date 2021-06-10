package com.dicoding.mydompetku.category.expense

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mydompetku.R
import com.dicoding.mydompetku.category.debt.DebtCategoryFragment
import com.dicoding.mydompetku.core.data.Resource
import com.dicoding.mydompetku.core.ui.CategoryAdapter
import com.dicoding.mydompetku.databinding.FragmentExpenseCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseCategoryFragment : Fragment() {

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 120
    }

    private val expenseCategoryViewModel: ExpenseCategoryViewModel by viewModels()

    private var _binding: FragmentExpenseCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenseCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val categoryAdapter = CategoryAdapter()
            categoryAdapter.onItemClick = { selectedData ->
                val resultIntent = Intent()
                resultIntent.putExtra(DebtCategoryFragment.EXTRA_SELECTED_VALUE, selectedData)
                requireActivity().setResult(DebtCategoryFragment.RESULT_CODE, resultIntent)
                requireActivity().finish()
            }

            expenseCategoryViewModel.expenseCategory.observe(viewLifecycleOwner, { category ->
                if (category != null) {
                    when (category) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            categoryAdapter.setData(category.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = category.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvExpenseCategory) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = categoryAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}