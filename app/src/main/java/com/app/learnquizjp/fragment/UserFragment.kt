package com.app.learnquizjp.fragment

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.learnquizjp.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import kotlinx.android.synthetic.main.fragment_user.view.*
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import com.app.learnquizjp.R
import kotlinx.android.synthetic.main.dialog_update_password.*
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class UserFragment : Fragment(){

    private lateinit var auth : FirebaseAuth
    private lateinit var authListener : FirebaseAuth.AuthStateListener
    private lateinit var storage : FirebaseStorage
    private val REQUEST_CODE_IMAGE : Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_user, container, false)
        //Init Fire base Component
        auth = FirebaseAuth.getInstance()
        val user : FirebaseUser? = auth.currentUser
        storage = FirebaseStorage.getInstance()
        val storageRef = storage.getReferenceFromUrl("gs://learnquizjp.appspot.com/")
        authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(Intent(activity, LoginActivity::class.java))
                activity!!.finish()
            }
        }
        //Set avatar
//        var islandRef = storageRef.child("avatar.png")
//
//        Glide.with(activity!!)
//            .load(islandRef)
//            .into(view.imgUserAva)

        view.btnCamera.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE),REQUEST_CODE_IMAGE)
        }

        view.btnAdd.setOnClickListener {
//            var calendar = Calendar.getInstance()

            val mountainsRef = storageRef.child("avatar.png")
            // Get the data from an ImageView as bytes
            view.imgUserAva.isDrawingCacheEnabled = true
            view.imgUserAva.buildDrawingCache()
            val bitmap = (view.imgUserAva.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
                Toast.makeText(activity,getString(R.string.notify_upload_avatar_fail),Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(activity,getString(R.string.notify_upload_avatar_successful),Toast.LENGTH_SHORT).show()

            }
        }

        view.btnReset.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val inflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_update_password, null)
            dialog.setView(dialogView)
            val dialog_update_password : Dialog = dialog.show()
            val edtPassword : EditText =  dialog_update_password.edtPassword
            val btnReset : Button = dialog_update_password.btnReset
            val btnCancel : Button = dialog_update_password.btnCancel
            btnReset.setOnClickListener {
                if (user != null && edtPassword.text.toString().trim() != "") {
                    if (edtPassword.text.toString().trim().length < 6) {
                        edtPassword.error = getString(R.string.notify_length_pass)
                    } else {
                        user.updatePassword(edtPassword.text.toString().trim()).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(activity, getString(R.string.notify_change_password_successful), Toast.LENGTH_SHORT).show()
                                auth.signOut()
                            } else {
                                Toast.makeText(activity, getString(R.string.notify_change_password_fail), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else if (edtPassword.text.toString().trim() == "") {
                    edtPassword.error = getString(R.string.notify_input_new_password)
                }
            }
            btnCancel.setOnClickListener {
                dialog_update_password.dismiss()
            }

        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            var bitmap : Bitmap = data.extras.get("data") as Bitmap
            view!!.imgUserAva.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener { authListener }
    }

    override fun onStop() {
        super.onStop()
        if(authListener != null){
            auth.removeAuthStateListener { authListener }
        }
    }
}