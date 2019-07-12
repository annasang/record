package com.mercy.dona.hi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tagmanager.Container;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link sec.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link sec#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sec extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static String dumpy = "xyz";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button openlist;
    TextView contact;

    public sec() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sec.
     */
    // TODO: Rename and change types and number of parameters
    public static sec newInstance(String param1, String param2) {
        sec fragment = new sec();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //View view = View.inflate(R.layout.fragment_sec,Container,false);

      //  openlist = getView().findViewById(R.id.open);
        //contact = getView().findViewById(R.id.sel);

        Intent intent = getActivity().getIntent();
        String select = intent.getStringExtra("contact");



        contact.setText(select);


        /*openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), trusted_list.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"dasdas",Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.w("asdad","inside vewc");
        //openlist = view.findViewById(R.id.open);
        //contact = view.findViewById(R.id.sel);

       /* openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"dasdas",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), trusted_list.class);
                startActivity(intent);

            }
        });*/



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.w("da","inside cvew");
        View view = inflater.inflate(R.layout.fragment_sec, container, false);
      //  openlist = getView().findViewById(R.id.open);
        //contact = getView().findViewById(R.id.sel);


      /* openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), trusted_list.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"dasdas",Toast.LENGTH_SHORT).show();
            }
        });*/

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

  /* @Override public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void openList(){
        Toast.makeText(getContext(),"dasdas",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), trusted_list.class);
        startActivity(intent);
    }
}
