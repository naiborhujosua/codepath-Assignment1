package id.naiborhujosua.android.flicks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.naiborhujosua.android.flicks.R;
import id.naiborhujosua.android.flicks.model.Movie;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by dell on 9/16/2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
  public MovieAdapter(@NonNull Context context, @NonNull List<Movie> movies) {
    super(context, android.R.layout.simple_list_item_1, movies);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    Movie movie = getItem(position);

    if (convertView == null){
      LayoutInflater inflater = LayoutInflater.from(getContext());
      convertView = inflater.inflate(R.layout.item_movies,parent,false);
    }

    ImageView ivImage =(ImageView) convertView.findViewById(R.id.ivImageMovie);
    TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
    TextView tvOverview =(TextView) convertView.findViewById(R.id.tvOverview);

    ivImage.setImageResource(0);
    tvTitle.setText(movie.getOriginalTitle());
    tvOverview.setText(movie.getOverview());

//    Picasso.with(getContext()).load(movie.getPosterPath()).fit()
//      .centerCrop().placeholder(R.drawable.the_power_of_film)
//      .error(R.drawable.small_movie_poster).into(ivImage);

    Picasso.with(getContext()).load(movie.getPosterPath())
      .transform(new RoundedCornersTransformation(10,10)).into(ivImage);
    return convertView;
  }



}
