package com.chickenkiller.upods2.models;

import com.chickenkiller.upods2.interfaces.IFeaturableMediaItem;
import com.chickenkiller.upods2.interfaces.IPlayableMediaItem;
import com.chickenkiller.upods2.interfaces.ITrackable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alonzilberman on 8/24/15.
 */
public class Podcast extends MediaItem implements IFeaturableMediaItem, IPlayableMediaItem, ITrackable {
    protected String name;
    protected String censoredName;
    protected String artistName;
    protected String feedUrl;
    protected String imageUrl;
    protected String releaseDate;
    protected String explicitness;
    protected String trackCount;
    protected String country;
    protected String genre;

    protected ArrayList<Episod> episods;

    public Podcast(){
        super();
        this.episods = new ArrayList<>();
    }

    public Podcast(JSONObject jsonItem) {
        this();
        try {
            if (jsonItem.has("kind")) { //Itnes
                this.id = jsonItem.has("trackId") ? jsonItem.getInt("trackId") : 0;
                this.name = jsonItem.has("collectionName") ? jsonItem.getString("collectionName") : "";
                this.censoredName = jsonItem.has("collectionCensoredName") ? jsonItem.getString("collectionCensoredName") : "";
                this.artistName = jsonItem.has("artistName") ? jsonItem.getString("artistName") : "";
                this.feedUrl = jsonItem.has("feedUrl") ? jsonItem.getString("feedUrl") : "";
                this.imageUrl = jsonItem.has("artworkUrl100") ? jsonItem.getString("artworkUrl100") : "";
                if (this.imageUrl.isEmpty()) {
                    this.imageUrl = jsonItem.has("artworkUrl60") ? jsonItem.getString("artworkUrl60") : "";
                }
                this.country = jsonItem.has("country") ? jsonItem.getString("country") : "";
                this.releaseDate = jsonItem.has("releaseDate") ? jsonItem.getString("releaseDate") : "";
                this.explicitness = jsonItem.has("collectionExplicitness") ? jsonItem.getString("collectionExplicitness") : "";
                this.trackCount = jsonItem.has("trackCount") ? jsonItem.getString("trackCount") : "";
                this.genre = jsonItem.has("primaryGenreName") ? jsonItem.getString("primaryGenreName") : "";
            } else {//Our backend
                this.id = jsonItem.has("id") ? jsonItem.getInt("id") : 0;
                this.name = jsonItem.has("name") ? jsonItem.getString("name") : "";
                this.censoredName = jsonItem.has("censored_name") ? jsonItem.getString("censored_name") : "";
                this.artistName = jsonItem.has("artist_name") ? jsonItem.getString("artist_name") : "";
                this.feedUrl = jsonItem.has("feed_url") ? jsonItem.getString("feed_url") : "";
                this.imageUrl = jsonItem.has("image_url") ? jsonItem.getString("image_url") : "";
                this.country = jsonItem.has("country") ? jsonItem.getString("country") : "";
                this.releaseDate = jsonItem.has("release_date") ? jsonItem.getString("release_date") : "";
                this.explicitness = jsonItem.has("explicitness") ? jsonItem.getString("explicitness") : "";
                this.trackCount = jsonItem.has("track_count") ? jsonItem.getString("track_count") : "";
                this.genre = jsonItem.has("genre") ? jsonItem.getString("genre") : "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Podcast(Podcast podcast) {
        this.name = podcast.getName();
        this.censoredName = podcast.getCensoredName();
        this.artistName = podcast.getArtistName();
        this.feedUrl = podcast.getFeedUrl();
        this.imageUrl = podcast.getCoverImageUrl();
        this.releaseDate = podcast.getReleaseDate();
        this.explicitness = podcast.getExplicitness();
        this.trackCount = podcast.getTrackCount();
        this.country = podcast.getCountry();
        this.genre = podcast.getGenre();
        this.episods = new ArrayList<Episod>(podcast.episods);
    }

    public static ArrayList<Podcast> withJsonArray(JSONArray jsonPodcastsItems) {
        ArrayList<Podcast> items = new ArrayList<Podcast>();
        try {
            for (int i = 0; i < jsonPodcastsItems.length(); i++) {
                JSONObject podcastItem = (JSONObject) jsonPodcastsItems.get(i);
                items.add(new Podcast(podcastItem));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCoverImageUrl() {
        return imageUrl;
    }

    @Override
    public String getSubHeader() {
        return this.genre;
    }

    @Override
    public String getBottomHeader() {
        return this.country;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getStreamUrl() {
        for (Track episod: episods) {
            if(episod.isSelected){
                return episod.getMp3Url();
            }
        }
        return episods.get(0).getMp3Url();
    }

    @Override
    public boolean hasTracks() {
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCensoredName() {
        return censoredName;
    }

    public void setCensoredName(String censoredName) {
        this.censoredName = censoredName;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(String trackCount) {
        this.trackCount = trackCount;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getExplicitness() {
        return explicitness;
    }

    public void setExplicitness(String explicitness) {
        this.explicitness = explicitness;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void setTracks(ArrayList<? extends Track> tracks) {
        this.episods= (ArrayList<Episod>) tracks;
    }

    @Override
    public ArrayList<? extends Track> getTracks() {
        return this.episods;
    }

    @Override
    public String getTracksFeed() {
        return this.feedUrl;
    }

    @Override
    public void selectTrack(Track track) {
        for (Track episod: episods) {
            episod.isSelected = episod.equals(track) ? true : false;
        }
    }
}