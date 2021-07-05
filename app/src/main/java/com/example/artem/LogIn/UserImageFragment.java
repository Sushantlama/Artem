package com.example.artem.LogIn;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.artem.LogIn.data.User;
import com.example.artem.LogIn.data.UserImageCustomAdapter;
import com.example.artem.R;
import com.example.artem.databinding.FragmentUserImageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class UserImageFragment extends Fragment {

    FragmentUserImageBinding binding;
    ArrayList<Integer> arrayList;
    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth auth;
    String name,uid,profileimage,email;
    String password;
    int id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentUserImageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            name = bundle.getString("name","user");
            email=bundle.getString("email","user123@gmail.com");
            password=bundle.getString("password","123456");

        }

        binding.openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    binding.spinKitSighnup.setVisibility(View.VISIBLE);
                    binding.blurSighup.setVisibility(View.VISIBLE);
                    getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(), "verfication email sent to "+auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                    StorageReference reference=storage.getReference().child("Profiles").child(auth.getUid());
                                    reference.putFile(Uri.parse("android.resource://com.example.artem/"+id)).
                                            addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    showFailure(e);
                                                }
                                            }).
                                            addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                    if (task.isSuccessful()){
                                                        reference.getDownloadUrl().addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                showFailure(e);
                                                            }
                                                        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                            @Override
                                                            public void onSuccess(Uri uri) {
                                                                profileimage=uri.toString();
                                                                uid=auth.getUid();
                                                                email=auth.getCurrentUser().getEmail();
                                                                User user=new User(name,uid,profileimage,email);
                                                                database.getReference()
                                                                        .child("users")
                                                                        .child(uid)
                                                                        .setValue(user)
                                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                binding.blurSighup.setVisibility(View.INVISIBLE);
                                                                                binding.spinKitSighnup.setVisibility(View.INVISIBLE);
                                                                                Toast.makeText(getContext(), "Account Created", Toast.LENGTH_LONG).show();
                                                                                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                                                getParentFragmentManager().beginTransaction().replace(R.id.login_frame,new LoginFragment()).commit();
                                                                            }
                                                                        }).addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        showFailure(e);
                                                                    }
                                                                });
                                                            }

                                                        });
                                                    }
                                                }
                                            });




                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    binding.spinKitSighnup.setVisibility(View.GONE);
                                    binding.blurSighup.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), "error" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else{
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            binding.blurSighup.setVisibility(View.INVISIBLE);
                            binding.spinKitSighnup.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

    }
    private void showFailure(Exception e){
        Toast.makeText(getActivity(), "failure\t"+e+"\tplease try agian", Toast.LENGTH_SHORT).show();
        binding.blurSighup.setVisibility(View.INVISIBLE);
        binding.spinKitSighnup.setVisibility(View.INVISIBLE);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    private void showdialog() {
        final Dialog dialog = new Dialog(getActivity());
        arrayList=new ArrayList<>();
        dialog.setContentView(R.layout.image_chose);
        for(int i=0;i<12;i++){
            arrayList.add(i);
        }
        RecyclerView recyclerView=dialog.findViewById(R.id.recycle_image);
        UserImageCustomAdapter adapter=new UserImageCustomAdapter(getActivity(), arrayList, new UserImageCustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int Id) {
                id=Id;
                Glide.with(UserImageFragment.this.getActivity()).load(Id).error(R.drawable.user).apply(RequestOptions.circleCropTransform()).into(binding.avatar);
                dialog.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        dialog.show();




    }

}