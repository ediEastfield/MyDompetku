package com.dicoding.mydompetku.detail.cashflow

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.mydompetku.R
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.databinding.ActivityDetailCashflowBinding
import com.dicoding.mydompetku.edit.EditCashflowActivity
import com.dicoding.mydompetku.utils.FormatRupiah
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCashflowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailCashflowViewModel: DetailCashflowViewModel by viewModels()

    private lateinit var binding: ActivityDetailCashflowBinding
    private lateinit var cashflow: Cashflow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCashflowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailCashflow = intent.getParcelableExtra<Cashflow>(EXTRA_DATA)
        showDetailCashflow(detailCashflow)

    }

    private fun showDetailCashflow(detailCashflow: Cashflow?) {
        detailCashflow?.let {
            Glide.with(this)
                .load(detailCashflow.icon)
                .into(binding.content.ivDetailIconCashflow)

            binding.content.tvDetailCategoryCashflow.text = detailCashflow.category
            binding.content.tvDetailNoteCashflow.text = detailCashflow.note
            binding.content.tvDetailNominalCashflow.text = FormatRupiah.formatRupiah(detailCashflow.nominal)
            if (detailCashflow.jenis == "income") {
                binding.content.tvDetailNominalCashflow.setTextColor(Color.parseColor("#2AA80A"))
            } else {
                binding.content.tvDetailNominalCashflow.setTextColor(Color.parseColor("#F41212"))
            }
            binding.content.tvDetailDateCashflow.text = detailCashflow.date

            cashflow = detailCashflow
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_cashflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.edit -> {
                val intent = Intent(this, EditCashflowActivity::class.java)
                intent.putExtra(EditCashflowActivity.EXTRA_DATA, cashflow)
                startActivity(intent)
            }
            R.id.delete -> {

                detailCashflowViewModel.setDeleteCashflow(cashflow)
                Toast.makeText(this, "Berhasil diHapus", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}