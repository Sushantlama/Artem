package com.example.artem.LogIn;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.artem.R;
import com.example.artem.databinding.FragmentPhoneVerifyBinding;
import com.google.firebase.auth.FirebaseAuth;


public class PhoneVerifyFragment extends Fragment {

    FragmentPhoneVerifyBinding binding;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentPhoneVerifyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


        @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            auth=FirebaseAuth.getInstance();
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.edtEmail.getText().toString().trim();
                String password=binding.edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    binding.invalidEmail.setVisibility(View.VISIBLE);
                    binding.invalidEmail.setText("email is required!");
                    }
                else if(TextUtils.isEmpty(password)){
                    binding.invalidEmail.setVisibility(View.INVISIBLE);
                    binding.invalidPassword.setVisibility(View.VISIBLE);
                    binding.invalidPassword.setText("Password is required!");
                }
                else if (password.length()<6){
                    binding.invalidEmail.setVisibility(View.INVISIBLE);
                    binding.invalidPassword.setVisibility(View.VISIBLE);
                    binding.invalidPassword.setText("Password is less than 6 characters");
                }
                else{

                    binding.invalidPassword.setVisibility(View.INVISIBLE);
                    binding.invalidEmail.setVisibility(View.INVISIBLE);

//                    binding.blur.setVisibility(View.VISIBLE);
//                    binding.spinKit.setVisibility(View.VISIBLE);
//                    getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


                    SignUpFragment fragment=new SignUpFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("email",email);
                    bundle.putString("password",password);
                    fragment.setArguments(bundle);
                    getParentFragmentManager().beginTransaction().replace(R.id.login_frame,fragment).commit();
                    }


//

            }
        });
    }
}