package com.example.artem.LogIn;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.artem.MainActivity;
import com.example.artem.R;
import com.example.artem.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


        @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        auth = FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        if (user!=null&&user.isEmailVerified()){
            Intent intent=new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        binding.btnSighup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.login_frame, new PhoneVerifyFragment()).commit();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pass = binding.edtPasswordLogin.getText().toString().trim();
                String email = binding.edtEmailLogin.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    binding.invalidEmailLogin.setVisibility(View.VISIBLE);
                    binding.invalidEmailLogin.setText("email is required!");
                } else if (TextUtils.isEmpty(Pass)) {
                    binding.invalidEmailLogin.setVisibility(View.INVISIBLE);
                    binding.invalidPasswordLogin.setVisibility(View.VISIBLE);
                    binding.invalidPasswordLogin.setText("Password is required!");
                }
                else  if (Pass.length()<6){
                    binding.invalidEmailLogin.setVisibility(View.INVISIBLE);
                    binding.invalidPasswordLogin.setVisibility(View.VISIBLE);
                    binding.invalidPasswordLogin.setText("Password is less than 6 characters");
                }
                else {
                    binding.invalidEmailLogin.setVisibility(View.INVISIBLE);
                    binding.invalidPasswordLogin.setVisibility(View.INVISIBLE);
                    binding.spinKitLogin.setVisibility(View.VISIBLE);
                    binding.blurLogin.setVisibility(View.VISIBLE);
                    getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    auth.signInWithEmailAndPassword(email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                if (auth.getCurrentUser().isEmailVerified()){
                                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    binding.spinKitLogin.setVisibility(View.GONE);
                                    binding.blurLogin.setVisibility(View.INVISIBLE);

                                    Intent intent=new Intent(getActivity(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                                else {
                                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    binding.spinKitLogin.setVisibility(View.GONE);
                                    binding.blurLogin.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), "user not verified"+user.getUid(), Toast.LENGTH_SHORT).show();

                                }

                            } else {
                                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                binding.spinKitLogin.setVisibility(View.GONE);
                                binding.blurLogin.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(), "error" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });



    }
}