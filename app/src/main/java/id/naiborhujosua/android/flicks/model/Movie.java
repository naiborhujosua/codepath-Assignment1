package id.naiborhujosua.android.flicks.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell on 9/16/2017.
 */

public class Movie {
  public String getPosterPath() {
    return String.format("https://image.tmdb.org/t/p/w300/%s",posterPath);
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public String getOverview() {
    return overview;
  }

  String posterPath;
  String originalTitle;
  String overview;

  public Movie(JSONObject jsonObject) throws JSONException{
    this.posterPath = jsonObject.getString("poster_path");
    this.originalTitle = jsonObject.getString("original_title");
    this.overview = jsonObject.getString("overview");
  }

  public static ArrayList<Movie> fromJsonArray(JSONArray array) throws JSONException {
    ArrayList<Movie> results = new ArrayList<>();

    for (int x =0; x< array.length();x++){
      try {
        results.add(new Movie(array.getJSONObject(x)));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return results;
  }
}
