package com.dicoding.mydompetku.category

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.mydompetku.category.debt.DebtCategoryFragment
import com.dicoding.mydompetku.category.expense.ExpenseCategoryFragment
import com.dicoding.mydompetku.category.income.IncomeCategoryFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = DebtCategoryFragment()
            1 -> fragment = ExpenseCategoryFragment()
            2 -> fragment = IncomeCategoryFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 3

}