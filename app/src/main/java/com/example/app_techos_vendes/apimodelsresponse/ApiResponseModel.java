package com.example.app_techos_vendes.apimodelsresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponseModel {
    @SerializedName("Error")
    @Expose
    private int Error;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("TypeMessage")
    @Expose
    private int TypeMessage;

    /**
     *
     * @return
     * The Error
     */
    public int getError() {
        return Error;
    }

    /**
     *
     * @param error
     * The Error
     */
    public void setError(int error) {
        Error = error;
    }

    /**
     *
     * @return
     * The Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     *
     * @param message
     * The Message
     */
    public void setMessage(String message) {
        Message = message;
    }

    /**
     *
     * @return
     * The TypeMessage
     */
    public int getTypeMessage() {
        return TypeMessage;
    }

    /**
     *
     * @param typeMessage
     * The Error
     */
    public void setTypeMessage(int typeMessage) {
        TypeMessage = typeMessage;
    }
}
