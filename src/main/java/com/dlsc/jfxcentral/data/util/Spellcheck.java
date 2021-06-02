
package com.dlsc.jfxcentral.data.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Spellcheck {

    @SerializedName("suggestions")
    @Expose
    private List<Object> suggestions = null;

    public List<Object> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Object> suggestions) {
        this.suggestions = suggestions;
    }

}
