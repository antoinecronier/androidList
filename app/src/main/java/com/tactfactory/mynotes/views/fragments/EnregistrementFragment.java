package com.tactfactory.mynotes.views.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tactfactory.mynotes.R;
import com.tactfactory.mynotes.views.recyclers.MyEnregistrementRecyclerViewAdapter;
import com.tactfactory.mynotes.dao.EnregistrementDAO;
import com.tactfactory.mynotes.entities.Enregistrement;
import com.tactfactory.mynotes.entities.Note;
import com.tactfactory.mynotes.entities.contracts.NoteContract;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class EnregistrementFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    private MyEnregistrementRecyclerViewAdapter adapter;

    public MyEnregistrementRecyclerViewAdapter getAdapter(){
        return adapter;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EnregistrementFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EnregistrementFragment newInstance(int columnCount) {
        EnregistrementFragment fragment = new EnregistrementFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enregistrement_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //Load enregistrement for current note
            EnregistrementDAO enregistrementDAO = new EnregistrementDAO(this.getActivity());
            Note note = (Note) this.getActivity().getIntent().getSerializableExtra(NoteContract.INTENT_NOTE);

            adapter = new MyEnregistrementRecyclerViewAdapter(enregistrementDAO.get(note.getId()), mListener);

            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void addOneItem() {
        this.getAdapter().getmValues().add(new Enregistrement());
        this.getAdapter().notifyDataSetChanged();
        recyclerView.scrollToPosition(this.getAdapter().getmValues().size()-1);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Enregistrement item);
    }
}
