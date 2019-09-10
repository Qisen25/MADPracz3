package au.edu.curtin.madprac3;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectorFragment extends Fragment
{
    private StructureData structures;
    private Structure current;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.structures = StructureData.get();
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup ui, Bundle bundle)
    {
        View view = li.inflate(R.layout.fragment_selector, ui, false);
        RecyclerView recycView = (RecyclerView)view.findViewById(R.id.selectRecyclerView);
        recycView.setLayoutManager(new LinearLayoutManager(getActivity(),
                                            LinearLayoutManager.HORIZONTAL, false));

        StructureAdapter adapter = new StructureAdapter();

        recycView.setAdapter(adapter);

        return view;
    }

    private class StructureViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView structs;
        private TextView desc;
        private Structure bindData;


        public StructureViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_selection, parent, false));

            structs = (ImageView)itemView.findViewById(R.id.ui_elements);
            desc = (TextView)itemView.findViewById(R.id.ui_desc);

            structs.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    current = bindData;

//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), current.getLabel() + " selected", duration);
//
//                    toast.show();
                }
            });
        }


        public void bind(Structure struct)
        {
            bindData = struct;
            structs.setImageResource(struct.getDrawableId());
            desc.setText(struct.getLabel());
        }
    }

    private class StructureAdapter extends RecyclerView.Adapter<StructureViewHolder>
    {
        @Override
        public StructureViewHolder onCreateViewHolder(ViewGroup parent, int i)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());

            return new StructureViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(StructureViewHolder vh, int i)
        {
            vh.bind(structures.get(i));
        }

        @Override
        public int getItemCount()
        {
            return structures.size();
        }
    }

    public Structure getSelected()
    {
        return this.current;
    }
}
