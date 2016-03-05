/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisal.twitter.bean;

import br.unisal.twitter.dao.TweetDao;
import br.unisal.twitter.model.Tweet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import twitter4j.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author JETHER
 */
@ManagedBean(name = "twitterBean")
public class TwitterBean implements Serializable {

    private String twitt;
    private String twittsResult;
    private UIInput uiMsg;
    private UIInput uiResultMsg;
    private UIInput uiQuery;
    private UIInput uiResultQuery;
    private String twittQuery;
    private String twittsResultQuery;
    private final TweetDao dao = new TweetDao();
    private static final Logger LOG = Logger.getLogger(TwitterBean.class);

    public TwitterBean() {
    }

    public void postingToTwitter() {
        try {
            TwitterFactory TwitterFactory = new TwitterFactory();
            Twitter twitter = TwitterFactory.getSingleton();

            String message = getTwitt();
            Status status = twitter.updateStatus(message);

            /*String s = "status.toString() = " + status.toString()
             + "status.getInReplyToScreenName() = " + status.getInReplyToScreenName()
             + "status.getSource() = " + status.getSource()
             + "status.getText() = " + status.getText()
             + "status.getContributors() = " + Arrays.toString(status.getContributors())
             + "status.getCreatedAt() = " + status.getCreatedAt()
             + "status.getCurrentUserRetweetId() = " + status.getCurrentUserRetweetId()
             + "status.getGeoLocation() = " + status.getGeoLocation()
             + "status.getId() = " + status.getId()
             + "status.getInReplyToStatusId() = " + status.getInReplyToStatusId()
             + "status.getInReplyToUserId() = " + status.getInReplyToUserId()
             + "status.getPlace() = " + status.getPlace()
             + "status.getRetweetCount() = " + status.getRetweetCount()
             + "status.getRetweetedStatus() = " + status.getRetweetedStatus()
             + "status.getUser() = " + status.getUser()
             + "status.getAccessLevel() = " + status.getAccessLevel()
             + "status.getHashtagEntities() = " + Arrays.toString(status.getHashtagEntities())
             + "status.getMediaEntities() = " + Arrays.toString(status.getMediaEntities())
             + "status.getURLEntities() = " + Arrays.toString(status.getURLEntities())
             + "status.getUserMentionEntities() = " + Arrays.toString(status.getUserMentionEntities());*/
            String s = "status.getId() = " + status.getId()
                    + "\nstatus.getUser() = " + status.getUser().getName() + " - " + status.getUser().getScreenName()
                    + "\nstatus.getGeoLocation() = " + status.getGeoLocation()
                    + "\nstatus.getText() = " + status.getText();
            setTwittsResult(s);
            this.getUiMsg().setSubmittedValue("");
            this.getUiResultMsg().setSubmittedValue(getTwittsResult());
        } catch (TwitterException ex) {
            LOG.error("Erro no postingToTwitter() - " + ex.toString());
        }
    }

    public void consultarTweets() {
        TwitterFactory TwitterFactory = new TwitterFactory();
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> tweets = new ArrayList<>();
        Query query = new Query(getTwittQuery());
        try {
            QueryResult result = twitter.search(query);
            tweets.addAll(result.getTweets());
            StringBuilder builder = new StringBuilder();
            double lon = 0;
            double lat = 0;
            for (Status s : tweets) {
                if ((s.getGeoLocation() != null)) {
                    lon = s.getGeoLocation().getLongitude();
                    lat = s.getGeoLocation().getLatitude();
                }
                Tweet t = new Tweet(s.getUser().getName(), s.getUser().getLocation(),
                        s.getText(), s.getCreatedAt(), lat, lon);
                dao.insert(t);
                builder.append(t.toString());
            }
            this.getUiResultQuery().setSubmittedValue(builder.toString());
        } catch (TwitterException te) {
            System.out.println("Failed to search tweets: " + te.getMessage());            
        }
    }

    public String getTwitt() {
        return twitt;
    }

    public void setTwitt(String twitt) {
        this.twitt = twitt;
    }

    public UIInput getUiMsg() {
        return uiMsg;
    }

    public void setUiMsg(UIInput uiMsg) {
        this.uiMsg = uiMsg;
    }

    public UIInput getUiResultMsg() {
        return uiResultMsg;
    }

    public void setUiResultMsg(UIInput uiResultMsg) {
        this.uiResultMsg = uiResultMsg;
    }

    public String getTwittsResult() {
        return twittsResult;
    }

    public void setTwittsResult(String twittsResult) {
        this.twittsResult = twittsResult;
    }

    public UIInput getUiQuery() {
        return uiQuery;
    }

    public void setUiQuery(UIInput uiQuery) {
        this.uiQuery = uiQuery;
    }

    public UIInput getUiResultQuery() {
        return uiResultQuery;
    }

    public void setUiResultQuery(UIInput uiResultQuery) {
        this.uiResultQuery = uiResultQuery;
    }

    public String getTwittQuery() {
        return twittQuery;
    }

    public void setTwittQuery(String twittQuery) {
        this.twittQuery = twittQuery;
    }

    public String getTwittsResultQuery() {
        return twittsResultQuery;
    }

    public void setTwittsResultQuery(String twittsResultQuery) {
        this.twittsResultQuery = twittsResultQuery;
    }

}
