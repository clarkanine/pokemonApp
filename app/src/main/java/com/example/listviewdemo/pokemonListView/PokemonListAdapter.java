package com.example.listviewdemo.pokemonListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewdemo.R;
import com.example.listviewdemo.util.ImageResourceUtil;
import com.example.listviewdemo.util.PokemonStringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.sargunvohra.lib.pokekotlin.model.PokemonEntry;

public class PokemonListAdapter<T> extends BaseAdapter implements Filterable {
    private Context context;
    private int resource;
    private List<PokemonEntry> origEntries;
    private List<PokemonEntry> entries;
    private Filter filter = new Filter() {
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            entries = (List<PokemonEntry>) results.values;
            PokemonListAdapter.this.notifyDataSetChanged();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            entries = origEntries.stream()
                .filter(mon -> mon.getPokemonSpecies().getName().contains(constraint))
                .collect(Collectors.toList());

            FilterResults results = new FilterResults();
            results.values = entries;
            results.count = entries.size();

            return results;
        }
    };

    public PokemonListAdapter(Context context, int resource, List<PokemonEntry> entries) {
        super();
        this.context = context;
        this.resource = resource;
        this.entries = new ArrayList<>();
        this.entries.addAll(entries);
        this.origEntries = new ArrayList<>();
        this.origEntries.addAll(entries);
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PokemonEntry entry = (PokemonEntry)getItem(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView textViewName = convertView.findViewById(R.id.pokemonName);
        textViewName.setText(PokemonStringUtil.format(entry.getPokemonSpecies().getName()));
        ImageView pokemonIcon = convertView.findViewById(R.id.pokemonIcon);

        pokemonIcon.setImageResource(ImageResourceUtil.getPokemonImageResourceId(entry.getPokemonSpecies().getId()));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

}
