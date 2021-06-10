package com.dicoding.mydompetku.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dicoding.mydompetku.MainActivity
import com.dicoding.mydompetku.R
import com.dicoding.mydompetku.category.CategoryActivity
import com.dicoding.mydompetku.category.debt.DebtCategoryFragment
import com.dicoding.mydompetku.category.expense.ExpenseCategoryFragment
import com.dicoding.mydompetku.category.income.IncomeCategoryFragment
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.model.Category
import com.dicoding.mydompetku.databinding.ActivityEditCashflowBinding
import com.dicoding.mydompetku.utils.DatePickerFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class EditCashflowActivity : AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener {

    companion object {
        const val EXTRA_DATA = "extra_data"
        private const val DATE_PICKER_TAG = "date_picker"
        private const val REQUEST_CODE = 100
    }

    private val editCashflowViewModel: EditCashflowViewModel by viewModels()

    private lateinit var binding: ActivityEditCashflowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCashflowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Edit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val editCashflow = intent.getParcelableExtra<Cashflow>(EXTRA_DATA)
        showEditCashflow(editCashflow)

        binding.content.ibIcon.setOnClickListener(this)
        binding.content.ibDate.setOnClickListener(this)
        binding.content.btnSave.setOnClickListener(this)
    }

    private fun showEditCashflow(cashflow: Cashflow?) {
        cashflow?.let {
            Glide.with(this)
                .load(cashflow.icon)
                .into(binding.content.ibIcon)

            binding.content.tvCategory.text = cashflow.category
            binding.content.edtNote.setText(cashflow.note)
            binding.content.tvDate.text = cashflow.date
            binding.content.edtNominal.setText(cashflow.nominal.toString())

            binding.content.tvId.text = cashflow.cashflowId
            binding.content.tvIcon.text = cashflow.icon
            binding.content.tvJenis.text = cashflow.jenis
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_icon -> {
                val intent = Intent(this, CategoryActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
            R.id.ib_date -> {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }
            R.id.btn_save -> {

                val id = binding.content.tvId.text.toString()
                val icon = binding.content.tvIcon.text.toString()
                val category = binding.content.tvCategory.text.toString()
                val jenis = binding.content.tvJenis.text.toString()
                val note = binding.content.edtNote.text.toString()
                val date = binding.content.tvDate.text.toString()
                val nominal = binding.content.edtNominal.text.toString()

                val cashflow = Cashflow(
                    id, icon, category, jenis, note, date, nominal.toInt()
                )

                editCashflowViewModel.setUpdateCashflow(cashflow)
                Toast.makeText(this, "Berhasil di Edit", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            when (resultCode) {
                DebtCategoryFragment.RESULT_CODE -> {
                    val selectedValue = data?.getParcelableExtra<Category>(DebtCategoryFragment.EXTRA_SELECTED_VALUE)
                    Glide.with(this)
                        .load(selectedValue?.icon)
                        .into(binding.content.ibIcon)
                    binding.content.tvCategory.text = selectedValue?.title
                    binding.content.tvIcon.text = selectedValue?.icon
                    binding.content.tvJenis.text = selectedValue?.jenis
                }
                ExpenseCategoryFragment.RESULT_CODE -> {
                    val selectedValue = data?.getParcelableExtra<Category>(ExpenseCategoryFragment.EXTRA_SELECTED_VALUE)
                    Glide.with(this)
                        .load(selectedValue?.icon)
                        .into(binding.content.ibIcon)
                    binding.content.tvCategory.text = selectedValue?.title
                    binding.content.tvIcon.text = selectedValue?.icon
                    binding.content.tvJenis.text = selectedValue?.jenis
                }
                IncomeCategoryFragment.RESULT_CODE -> {
                    val selectedValue = data?.getParcelableExtra<Category>(IncomeCategoryFragment.EXTRA_SELECTED_VALUE)
                    Glide.with(this)
                        .load(selectedValue?.icon)
                        .into(binding.content.ibIcon)
                    binding.content.tvCategory.text = selectedValue?.title
                    binding.content.tvIcon.text = selectedValue?.icon
                    binding.content.tvJenis.text = selectedValue?.jenis
                }
            }
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        binding.content.tvDate.text = dateFormat.format(calendar.time)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}