package com.tactfactory.mynotes.views.recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tactfactory.mynotes.R;
import com.tactfactory.mynotes.dao.EnregistrementDAO;
import com.tactfactory.mynotes.entities.Note;
import com.tactfactory.mynotes.views.fragments.EnregistrementFragment.OnListFragmentInteractionListener;
import com.tactfactory.mynotes.entities.Enregistrement;

import java.util.List;

public class MyEnregistrementRecyclerViewAdapter extends RecyclerView.Adapter<MyEnregistrementRecyclerViewAdapter.ViewHolder> {

    private List<Enregistrement> mValues;
    private final OnListFragmentInteractionListener mListener;
    protected Context context;
    protected Note note;

    public List<Enregistrement> getmValues(){
        return mValues;
    }

    public MyEnregistrementRecyclerViewAdapter(List<Enregistrement> items, OnListFragmentInteractionListener listener, Note note, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
        this.note = note;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_enregistrement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getContenu());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText mContentView;
        public Enregistrement mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (EditText) view.findViewById(R.id.content);
            mContentView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mValues.get(getAdapterPosition()).setContenu(editable.toString());
                    EnregistrementDAO maDAO = new EnregistrementDAO(MyEnregistrementRecyclerViewAdapter.this.context);
                    for (Enregistrement enregistrement: mValues) {
                        if (enregistrement.getId() == null){
                            enregistrement.setNote_id(MyEnregistrementRecyclerViewAdapter.this.note.getId());
                            maDAO.insert(enregistrement);
                        }else{
                            maDAO.update(enregistrement);
                        }
                    }
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
