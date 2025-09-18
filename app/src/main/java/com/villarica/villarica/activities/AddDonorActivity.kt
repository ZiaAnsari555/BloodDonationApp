package com.villarica.villarica.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.github.dhaval2404.imagepicker.ImagePicker
import com.villarica.villarica.R
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.databinding.ActivityAddDonorBinding
import com.villarica.villarica.viewmodels.AddDonorViewModel
import com.villarica.villarica.viewmodels.AvailableDonorsViewModel
import java.io.File

class AddDonorActivity : BaseActivity() {
    private var binding: ActivityAddDonorBinding? = null
    private val viewModel: AddDonorViewModel by viewModels()
    private var city: String? = null
    private var bloodGroup: String? = null
    private var file: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDonorBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {
            toolbar.ivMenu.isVisible = true
            toolbar.ivMenu.setOnClickListener {
                finish()
            }
            toolbar.tvTitle.text = getString(R.string.add_donor)
            btnAddDonor.setOnClickListener {
                // Check for a valid city selection
                if (city.isNullOrEmpty()) {
                    Toast.makeText(
                        this@AddDonorActivity,
                        "Please select a city!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

// Check if a blood group is selected
                if (bloodGroup.isNullOrEmpty()) {
                    Toast.makeText(
                        this@AddDonorActivity,
                        "Please select a blood group!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

// Check if the donor's name is entered
                if (etName.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this@AddDonorActivity,
                        "Please enter your name!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

// Check if the phone number is entered
                if (etPhoneNumber.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this@AddDonorActivity,
                        "Please enter your phone number!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

// Check if a file (e.g., an image) is selected
                if (file == null) {
                    Toast.makeText(
                        this@AddDonorActivity,
                        "Please upload an image!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                showLoading()
                viewModel.addDonor(
                    file!!, etName.text.toString(),
                    bloodGroup!!, etPhoneNumber.text.toString(), city!!
                )
            }
            tvEnterCity.setOnClickListener {
                showCityDialog(this@AddDonorActivity) { selected ->
                    city = selected
                    tvEnterCity.text = city
                }
            }
            tvSelectBloodType.setOnClickListener {
                showBloodGroupDialog(this@AddDonorActivity) { selected ->
                    bloodGroup = selected
                    tvSelectBloodType.text = bloodGroup
                }
            }
            profileImage.setOnClickListener {
                showImagePickerDialog()
            }
        }

        viewModel.init(this)
    }

    private fun showBloodGroupDialog(context: Context, onSelected: (String) -> Unit) {
        val bloodGroups = arrayOf(
            "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"
        )
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select Blood Group")
        builder.setItems(bloodGroups) { dialog, which ->
            onSelected(bloodGroups[which])  // callback with selected value
            dialog.dismiss()
        }
        builder.show()
    }

    private fun showCityDialog(context: Context, onSelected: (String) -> Unit) {
        val cities = arrayOf(
            "Faisalabad"
        )
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select City")
        builder.setItems(cities) { dialog, which ->
            onSelected(cities[which])  // callback with selected value
            dialog.dismiss()
        }
        builder.show()
    }


    private fun AddDonorViewModel.init(lifecycleOwner: LifecycleOwner) {
        viewModel.response.observe(lifecycleOwner) {
            hideLoading()
            when (it.success) {
                true -> {
                    Toast.makeText(
                        this@AddDonorActivity,
                        "Donor Added Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }

                false -> {
                    Toast.makeText(
                        this@AddDonorActivity,
                        it.error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        viewModel.error.observe(lifecycleOwner){
            hideLoading()
            Toast.makeText(this@AddDonorActivity, it, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun startThisActivity(context: Context) {
            context.startActivity(Intent(context, AddDonorActivity::class.java))
        }
    }


    private fun showImagePickerDialog() {
        val options = arrayOf("Camera", "Gallery")

        AlertDialog.Builder(this)
            .setTitle("Select Option")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> checkCameraPermission()
                    1 -> checkGalleryPermission()
                }
            }
            .show()
    }

    private val requestCameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                openCamera()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val requestGalleryPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                openGallery()
            } else {
                Toast.makeText(this, "Gallery permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private fun checkCameraPermission() {
        requestCameraPermission.launch(Manifest.permission.CAMERA)
    }

    private fun checkGalleryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestGalleryPermission.launch(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            requestGalleryPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun openCamera() {
        ImagePicker.with(this)
            .cameraOnly()
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                imagePickerLauncher.launch(intent)
            }
    }

    private fun openGallery() {
        ImagePicker.with(this)
            .galleryOnly()
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                imagePickerLauncher.launch(intent)
            }
    }

    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val uri: Uri? = result.data?.data
                binding?.profileImage?.setImageURI(uri)
                file = uri?.let { uriToFile(it) }
                // ✅ use uri or file here
            } else if (result.resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(result.data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    fun Context.uriToFile(uri: Uri): File? {
        val inputStream = contentResolver.openInputStream(uri) ?: return null
        val file = File(cacheDir, "${System.currentTimeMillis()}.jpg")
        file.outputStream().use { output ->
            inputStream.copyTo(output)
        }
        return file
    }



}