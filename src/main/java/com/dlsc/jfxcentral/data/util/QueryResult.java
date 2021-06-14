
package com.dlsc.jfxcentral.data.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryResult {

    @SerializedName("responseHeader")
    @Expose
    private ResponseHeader responseHeader;

    @SerializedName("response")
    @Expose
    private Response response;

    @SerializedName("spellcheck")
    @Expose
    private Spellcheck spellcheck;

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Spellcheck getSpellcheck() {
        return spellcheck;
    }

    public void setSpellcheck(Spellcheck spellcheck) {
        this.spellcheck = spellcheck;
    }

}
