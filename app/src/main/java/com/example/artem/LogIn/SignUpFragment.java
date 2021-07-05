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
import com.example.artem.databinding.FragmentSignUpBinding;

import org.w3c.dom.Text;

public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;
    String email;
    String password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                email=bundle.getString("email","user123@gmail.com");
                password=bundle.getString("password","123456");
            }




            binding.userBtnSighup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.edtName.getText().toString();
                if (name.isEmpty()){
                    binding.invalidName.setText("User name is required");
                    binding.invalidName.setVisibility(View.VISIBLE);
                }
                else{
                    binding.invalidName.setVisibility(View.INVISIBLE);
                    UserImageFragment fragment=new UserImageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name",name);
                    if (!TextUtils.isEmpty(email)&&! TextUtils.isEmpty(password)){
                        bundle.putString("email",email);
                        bundle.putString("password",password);
                    }

                    fragment.setArguments(bundle);
                    getParentFragmentManager().beginTransaction().replace(R.id.login_frame,fragment).commit();


                }
            }
        });



    }
}