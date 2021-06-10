package com.dicoding.mydompetku.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dicoding.mydompetku.R
import com.dicoding.mydompetku.category.CategoryActivity
import com.dicoding.mydompetku.category.debt.DebtCategoryFragment
import com.dicoding.mydompetku.category.expense.ExpenseCategoryFragment
import com.dicoding.mydompetku.category.income.IncomeCategoryFragment
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.model.Category
import com.dicoding.mydompetku.databinding.ActivityAddNoteBinding
import com.dicoding.mydompetku.utils.DatePickerFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener {

    private val addNoteViewModel: AddNoteViewModel by viewModels()
    private lateinit var binding: ActivityAddNoteBinding

    companion object {
        private const val DATE_PICKER_TAG = "datePicker"
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Add Transaction"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.content.btnIcon.setOnClickListener(this)
        binding.content.btnDate.setOnClickListener(this)
        binding.content.btnSave.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_icon -> {
                val intent = Intent(this, CategoryActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
            R.id.btn_date -> {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }
            R.id.btn_save -> {

                val icon = binding.content.tvIcon.text.toString()

                val category = binding.content.tvCategory.text.toString()
                if (category.isEmpty()) {
                    Toast.makeText(this, "Category harus diisi", Toast.LENGTH_SHORT).show()
                    return
                }

                val jenis = binding.content.tvJenis.text.toString()

                val note = binding.content.edtNote.text.toString()
                if (note == "") {
                    Toast.makeText(this, "Note harus diisi", Toast.LENGTH_SHORT).show()
                    return
                }

                val date = binding.content.tvDate.text.toString()
                if (date.isEmpty()) {
                    Toast.makeText(this, "Date harus diisi", Toast.LENGTH_SHORT).show()
                    return
                }

                val nominal = binding.content.edtNominal.text.toString()
                if (nominal == "") {
                    Toast.makeText(this, "nominal harus diisi", Toast.LENGTH_SHORT).show()
                    return
                }

                val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
                val id = (1..16)
                    .map { charset.random() }
                    .joinToString("")

                val cashflow = Cashflow(
                    id, icon, category, jenis, note,date, nominal.toInt()
                )

                addNoteViewModel.insertCashflow(cashflow)
                Toast.makeText(this, "Berhasil di tambahkan", Toast.LENGTH_SHORT).show()
                finish()
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
                        .into(binding.content.btnIcon)
                    binding.content.tvCategory.text = selectedValue?.title
                    binding.content.tvIcon.text = selectedValue?.icon
                    binding.content.tvJenis.text = selectedValue?.jenis
                }
                ExpenseCategoryFragment.RESULT_CODE -> {
                    val selectedValue = data?.getParcelableExtra<Category>(ExpenseCategoryFragment.EXTRA_SELECTED_VALUE)
                    Glide.with(this)
                        .load(selectedValue?.icon)
                        .into(binding.content.btnIcon)
                    binding.content.tvCategory.text = selectedValue?.title
                    binding.content.tvIcon.text = selectedValue?.icon
                    binding.content.tvJenis.text = selectedValue?.jenis
                }
                IncomeCategoryFragment.RESULT_CODE -> {
                    val selectedValue = data?.getParcelableExtra<Category>(IncomeCategoryFragment.EXTRA_SELECTED_VALUE)
                    Glide.with(this)
                        .load(selectedValue?.icon)
                        .into(binding.content.btnIcon)
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