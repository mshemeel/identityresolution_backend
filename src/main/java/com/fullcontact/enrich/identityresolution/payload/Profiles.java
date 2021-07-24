
package com.fullcontact.enrich.identityresolution.payload;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "twitter",
    "linkedin"
})
public class Profiles {

    @JsonProperty("twitter")
    private Twitter twitter;
    @JsonProperty("linkedin")
    private Linkedin linkedin;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("twitter")
    public Twitter getTwitter() {
        return twitter;
    }

    @JsonProperty("twitter")
    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    @JsonProperty("linkedin")
    public Linkedin getLinkedin() {
        return linkedin;
    }

    @JsonProperty("linkedin")
    public void setLinkedin(Linkedin linkedin) {
        this.linkedin = linkedin;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Profiles.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("twitter");
        sb.append('=');
        sb.append(((this.twitter == null)?"<null>":this.twitter));
        sb.append(',');
        sb.append("linkedin");
        sb.append('=');
        sb.append(((this.linkedin == null)?"<null>":this.linkedin));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.twitter == null)? 0 :this.twitter.hashCode()));
        result = ((result* 31)+((this.linkedin == null)? 0 :this.linkedin.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Profiles) == false) {
            return false;
        }
        Profiles rhs = ((Profiles) other);
        return ((((this.twitter == rhs.twitter)||((this.twitter!= null)&&this.twitter.equals(rhs.twitter)))&&((this.linkedin == rhs.linkedin)||((this.linkedin!= null)&&this.linkedin.equals(rhs.linkedin))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
