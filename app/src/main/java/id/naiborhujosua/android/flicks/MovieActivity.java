package id.naiborhujosua.android.flicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.naiborhujosua.android.flicks.adapter.MovieAdapter;
import id.naiborhujosua.android.flicks.model.Movie;
import cz.msebera.android.httpclient.Header;

import static id.naiborhujosua.android.flicks.model.Movie.fromJsonArray;

public class MovieActivity extends AppCompatActivity {

  ArrayList<Movie> movies;
  MovieAdapter movieAdapter;
  ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(this,movies);
        lvItems.setAdapter(movieAdapter);
        String url ="https://api.themoviedb.org/3/movie/now_playing?api_key=f82db6035930b01dc3e74aceeb7aacd2";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
          JSONArray movieJsonResult = null;

          try {
            movieJsonResult = response.getJSONArray("results");
            movies.addAll(Movie.fromJsonArray(movieJsonResult));
            movieAdapter.notifyDataSetChanged();
            Log.d("DEBUT", "onSuccess: " + movies.toString());
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
          super.onFailure(statusCode, headers, responseString, throwable);
        }
      });
    }
}
